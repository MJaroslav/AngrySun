package mjaroslav.mcmods.angrysun.init;

import mjaroslav.mcmods.angrysun.common.item.ItemThermalUnderwear;
import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import mjaroslav.mcmods.angrysun.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRegistry {
    public static ArmorMaterial THERMAL;

    public static Item thermalUnderwearHead;
    public static Item thermalUnderwearChest;
    public static Item stitchedReed;

    public static void register() {
        THERMAL = EnumHelper.addArmorMaterial(ModInfo.MODID + ":thermal", ModInfo.MODID + ":thermal",
                ConfigInfo.durability, new int[] { 1, 2, 1, 1 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
        if (ConfigInfo.thermalunderwear) {
            thermalUnderwearHead = new ItemThermalUnderwear(1, EntityEquipmentSlot.HEAD);
            setRegister(thermalUnderwearHead);
            if (ConfigInfo.cloak) {
                thermalUnderwearChest = new ItemThermalUnderwear(1, EntityEquipmentSlot.CHEST);
                setRegister(thermalUnderwearChest);
            }
            if (ConfigInfo.craft) {
                stitchedReed = new Item().setCreativeTab(CreativeTabs.MATERIALS).setRegistryName("stitched_reed")
                        .setUnlocalizedName("stitched_reed");
                setRegister(stitchedReed);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        if (ConfigInfo.thermalunderwear) {
            setRender(thermalUnderwearHead);
            if (ConfigInfo.cloak)
                setRender(thermalUnderwearChest);
            if (ConfigInfo.craft)
                setRender(stitchedReed);
        }
    }

    private static void setRegister(Item item) {
        ForgeRegistries.ITEMS.register(item);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
