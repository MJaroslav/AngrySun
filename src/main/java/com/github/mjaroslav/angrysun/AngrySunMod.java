package com.github.mjaroslav.angrysun;

import com.github.mjaroslav.angrysun.item.ModItems;
import com.github.mjaroslav.angrysun.lib.ModInfo;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AngrySunMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, "stitched_reed"),
                ModItems.STITCHED_REED);
        Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, "thermalunderwear_head"),
                ModItems.THERMAL_HEAD);
        Registry.register(Registry.ITEM, new Identifier(ModInfo.MOD_ID, "thermalunderwear_chest"),
                ModItems.THERMAL_CHEST);
    }
}
