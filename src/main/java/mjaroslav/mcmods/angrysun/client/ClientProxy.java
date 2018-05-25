package mjaroslav.mcmods.angrysun.client;

import mjaroslav.mcmods.angrysun.common.CommonProxy;
import mjaroslav.mcmods.angrysun.common.item.ItemThermalUnderwear;
import mjaroslav.mcmods.angrysun.init.ItemRegistry;
import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ItemRegistry.registerRender();
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : ((ItemThermalUnderwear) stack.getItem()).getColor(stack);
            }
		}, ConfigInfo.cloak ? new Item[] { ItemRegistry.thermalUnderwearHead, ItemRegistry.thermalUnderwearChest }
				: new Item[] { ItemRegistry.thermalUnderwearHead });
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
