package com.github.mjaroslav.angrysun.item;

import com.github.mjaroslav.angrysun.item.material.ThermalArmorMaterial;
import com.github.mjaroslav.angrysun.utils.ThermalUnderwearUtils;
import lombok.val;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThermalUnderwearItem extends DyeableArmorItem {
    public ThermalUnderwearItem(EquipmentSlot slot, Settings settings) {
        super(ThermalArmorMaterial.INSTANCE, slot, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        val itemStack = user.getStackInHand(hand);
        val equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);
        if (user.isSneaking()) {
            if (ThermalUnderwearUtils.addThermalUnderwear(user, itemStack, equipmentSlot)) {
                itemStack.setCount(0);
                return TypedActionResult.success(itemStack, world.isClient());
            } else return TypedActionResult.fail(itemStack);
        } else return super.use(world, user, hand);
    }

    @Override
    public int getColor(ItemStack stack) {
        val nbtCompound = stack.getSubTag("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 0xFFFFFF;
    }
}
