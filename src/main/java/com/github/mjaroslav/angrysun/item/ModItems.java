package com.github.mjaroslav.angrysun.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ModItems {
    public static final Item THERMAL_HEAD = new ThermalUnderwearItem(EquipmentSlot.HEAD,
            new FabricItemSettings().group(ItemGroup.COMBAT));

    public static final Item THERMAL_CHEST = new ThermalUnderwearItem(EquipmentSlot.CHEST,
            new FabricItemSettings().group(ItemGroup.COMBAT));

    public static final Item STITCHED_REED = new Item(new FabricItemSettings().group(ItemGroup.MISC));
}
