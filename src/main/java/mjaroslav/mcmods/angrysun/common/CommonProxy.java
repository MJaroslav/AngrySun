package mjaroslav.mcmods.angrysun.common;

import mjaroslav.mcmods.angrysun.common.config.Config;
import mjaroslav.mcmods.angrysun.common.handler.PlayerEventHandler;
import mjaroslav.mcmods.angrysun.common.item.crafting.RecipeThermalUnderwearDyes;
import mjaroslav.mcmods.angrysun.init.CraftRegistry;
import mjaroslav.mcmods.angrysun.init.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Config.preInit(event);
        ItemRegistry.register();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {
        ForgeRegistries.RECIPES.register(new RecipeThermalUnderwearDyes().setRegistryName("thermalunderweardyes"));
        CraftRegistry.register();
    }
}
