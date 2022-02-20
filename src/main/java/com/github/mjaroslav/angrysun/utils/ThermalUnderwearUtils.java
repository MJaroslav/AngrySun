package com.github.mjaroslav.angrysun.utils;

import com.github.mjaroslav.angrysun.item.ModItems;
import com.github.mjaroslav.angrysun.item.ThermalUnderwearItem;
import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ThermalUnderwearUtils {
    public boolean addThermalUnderwear(@NotNull LivingEntity user, @NotNull ItemStack itemStack, @NotNull EquipmentSlot slot) {
        if (!itemStack.isEmpty()) {
            val armorStack = user.getEquippedStack(slot);
            if (!armorStack.isEmpty() && armorStack.getItem() != ModItems.THERMAL_HEAD) {
                val info = ThermalUnderwearInfo.loadFromStack(armorStack);
                info.setDamage(itemStack.getMaxDamage() - itemStack.getDamage());
                info.setMaxDamage(itemStack.getMaxDamage());
                info.saveToStack(armorStack);
                return true;
            }
        }
        return false;
    }

    public boolean stackHasThermalUnderwear(@NotNull ItemStack itemStack) {
        return !itemStack.isEmpty() && (itemStack.getItem() instanceof ThermalUnderwearItem
                || !ThermalUnderwearInfo.loadFromStack(itemStack).isGenerated());
    }

    public boolean checkForSurvival(@NotNull LivingEntity user) {
        return !(user instanceof PlayerEntity) || !((PlayerEntity) user).isCreative() && !user.isSpectator();
    }

    public boolean canDamageThermalUnderwear(@NotNull LivingEntity user) {
        if (!checkForSurvival(user))
            return false;
        var flag = true;
        var flag1 = true;
        val head = user.getEquippedStack(EquipmentSlot.HEAD);
        val chest = user.getEquippedStack(EquipmentSlot.CHEST);
        if (stackHasThermalUnderwear(head)) {
            damageThermalUnderwear(user, head, EquipmentSlot.HEAD);
            flag = false;
        }
        if (stackHasThermalUnderwear(chest)) {
            damageThermalUnderwear(user, chest, EquipmentSlot.CHEST);
            flag1 = false;
        }
        // TODO: two required
        return flag1 || flag;
    }

    public void damageThermalUnderwear(@NotNull LivingEntity user, @NotNull ItemStack itemStack, @NotNull EquipmentSlot slot) {
        if (itemStack.getItem() instanceof ThermalUnderwearItem) {
            // TODO: random
            itemStack.setDamage(itemStack.getDamage() + user.world.random.nextInt(3));
            if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                itemStack.setDamage(0);
                user.sendEquipmentBreakStatus(slot);
                user.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
            }
        } else {
            val info = ThermalUnderwearInfo.loadFromStack(itemStack);
            if (!info.isGenerated()) {
                info.setDamage(info.getDamage() - user.world.random.nextInt(3));
                info.saveToStack(itemStack);
            }
        }
    }
}
