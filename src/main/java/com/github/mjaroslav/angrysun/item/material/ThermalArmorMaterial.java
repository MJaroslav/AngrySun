package com.github.mjaroslav.angrysun.item.material;

import com.github.mjaroslav.angrysun.item.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class ThermalArmorMaterial implements ArmorMaterial {
    public static final ThermalArmorMaterial INSTANCE = new ThermalArmorMaterial();

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.LEATHER.getDurability(slot);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.LEATHER.getProtectionAmount(slot);
    }

    @Override
    public int getEnchantability() {
        return ArmorMaterials.LEATHER.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return ArmorMaterials.LEATHER.getEquipSound();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofStacks(new ItemStack(ModItems.STITCHED_REED, 1));
    }

    @Override
    public String getName() {
        return "thermalunderwear";
    }

    @Override
    public float getToughness() {
        return ArmorMaterials.LEATHER.getToughness();
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
