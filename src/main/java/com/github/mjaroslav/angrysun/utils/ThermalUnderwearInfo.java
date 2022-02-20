package com.github.mjaroslav.angrysun.utils;

import lombok.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

@Getter
@Setter
public final class ThermalUnderwearInfo {
    public static final String TAG_THERMALUNDERWEAR = "thermalunderwear";
    public static final String TAG_DAMAGE = "damage";
    public static final String TAG_MAX_DAMAGE = "maxdamage";

    private int damage;
    @Range(from = 1, to = Integer.MAX_VALUE)
    private int maxDamage;

    @Setter(AccessLevel.NONE)
    private boolean generated;

    private ThermalUnderwearInfo(@NotNull ItemStack itemStack) {
        var nbt = itemStack.getTag();
        if (nbt == null) {
            nbt = new NbtCompound();
            generated = true;
        }
        generated = generated || !nbt.contains(TAG_THERMALUNDERWEAR);
        nbt = nbt.getCompound(TAG_THERMALUNDERWEAR);
        damage = nbt.getInt(TAG_DAMAGE);
        maxDamage = Math.max(1, nbt.getInt(TAG_MAX_DAMAGE));
    }

    public void setDamage(int damage) {
        this.damage = damage;
        if (this.damage < 0)
            this.damage = 0;
    }

    private ThermalUnderwearInfo(@NotNull NbtCompound rootTag) {
        val thermalUnderwearTag = rootTag.getCompound(TAG_THERMALUNDERWEAR);
        damage = thermalUnderwearTag.getInt(TAG_DAMAGE);
        maxDamage = Math.max(1, thermalUnderwearTag.getInt(TAG_MAX_DAMAGE));
        generated = !rootTag.contains(TAG_THERMALUNDERWEAR);
    }

    public void saveToStack(@NotNull ItemStack itemStack) {
        var nbt = itemStack.getTag();
        if (nbt == null)
            nbt = new NbtCompound();
        if (damage <= 0) {
            nbt.remove(TAG_DAMAGE);
            itemStack.setTag(nbt.isEmpty() ? null : nbt);
        } else {
            var thermalUnderwear = nbt.getCompound(TAG_THERMALUNDERWEAR);
            thermalUnderwear.putInt(TAG_MAX_DAMAGE, maxDamage);
            thermalUnderwear.putInt(TAG_DAMAGE, damage);
            itemStack.setTag(nbt);
        }
    }

    public void saveToNbt(@NotNull NbtCompound rootTag) {
        if (damage <= 0)
            rootTag.remove(TAG_THERMALUNDERWEAR);
        else {
            val thermalUnderwear = rootTag.getCompound(TAG_THERMALUNDERWEAR);
            thermalUnderwear.putInt(TAG_DAMAGE, damage);
            thermalUnderwear.putInt(TAG_MAX_DAMAGE, maxDamage);
            rootTag.put(TAG_THERMALUNDERWEAR, thermalUnderwear);
        }
    }

    public static ThermalUnderwearInfo loadFromStack(@NotNull ItemStack itemStack) {
        return new ThermalUnderwearInfo(itemStack);
    }

    public static ThermalUnderwearInfo loadFromNbt(@NotNull NbtCompound rootTag) {
        return new ThermalUnderwearInfo(rootTag);
    }
}
