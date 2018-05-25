package mjaroslav.mcmods.angrysun.common.config;

import java.io.File;
import java.util.List;

import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import mjaroslav.mcmods.angrysun.lib.ModInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
    public static Configuration instance;

    public static void preInit(FMLPreInitializationEvent event) {
        if (instance == null)
            instance = new Configuration(new File(event.getModConfigurationDirectory() + "/" + ModInfo.MODID + ".cfg"));
        try {
            instance.load();
        } catch (Exception e) {
            ModInfo.LOG.error("Unable to load configuration!");
        } finally {
            if (instance.hasChanged()) {
                instance.save();
            }
        }
        sync();
        MinecraftForge.EVENT_BUS.register(new Config());
    }

    public static void sync() {
        ConfigInfo.read(instance);
        if (instance.hasChanged())
            instance.save();
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (event.getModID().equals(ModInfo.MODID)) {
            sync();
            ModInfo.LOG.info("Configuration reloaded");
        }
    }

    public static List<IConfigElement> getElements() {
        return new ConfigElement(instance.getCategory(ConfigInfo.GENERAL)).getChildElements();
    }
}
