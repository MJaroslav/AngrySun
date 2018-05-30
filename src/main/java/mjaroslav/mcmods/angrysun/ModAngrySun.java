package mjaroslav.mcmods.angrysun;

import static mjaroslav.mcmods.angrysun.lib.ModInfo.CLIENTPROXY;
import static mjaroslav.mcmods.angrysun.lib.ModInfo.COMMONPROXY;
import static mjaroslav.mcmods.angrysun.lib.ModInfo.GUIFACTORY;
import static mjaroslav.mcmods.angrysun.lib.ModInfo.MODID;
import static mjaroslav.mcmods.angrysun.lib.ModInfo.NAME;
import static mjaroslav.mcmods.angrysun.lib.ModInfo.VERSION;

import mjaroslav.mcmods.angrysun.common.CommonProxy;
import mjaroslav.mcmods.angrysun.common.item.ItemThermalUnderwear;
import mjaroslav.mcmods.angrysun.init.ItemRegistry;
import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MODID, name = NAME, version = VERSION, guiFactory = GUIFACTORY)
public class ModAngrySun {
	@SidedProxy(clientSide = CLIENTPROXY, serverSide = COMMONPROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static boolean addThermalUnderwearToArmor(EntityPlayer player, ItemStack stack) {
		if (!stack.isEmpty()) {
			if (stack.getItem() == ItemRegistry.thermalUnderwearHead) {
				ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
				if (!head.isEmpty() && !(head.getItem() instanceof ItemThermalUnderwear)) {
					if (!head.hasTagCompound())
						head.setTagCompound(new NBTTagCompound());
					NBTTagCompound thermal = head.getTagCompound().hasKey(ItemThermalUnderwear.THERMALUNDERWEAR)
							? head.getTagCompound().getCompoundTag(ItemThermalUnderwear.THERMALUNDERWEAR)
							: new NBTTagCompound();
					if (!thermal.hasKey(ItemThermalUnderwear.DAMAGE)
							|| thermal.getInteger(ItemThermalUnderwear.DAMAGE) <= stack.getMaxDamage() / 2) {
						thermal.setInteger(ItemThermalUnderwear.DAMAGE, stack.getMaxDamage() - stack.getItemDamage());
						head.getTagCompound().setTag(ItemThermalUnderwear.THERMALUNDERWEAR, thermal);
						return true;
					}
				}
			} else if (stack.getItem() == ItemRegistry.thermalUnderwearChest) {
				ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
				if (!chest.isEmpty() && !(chest.getItem() instanceof ItemThermalUnderwear)) {
					if (!chest.hasTagCompound())
						chest.setTagCompound(new NBTTagCompound());
					NBTTagCompound thermal = chest.getTagCompound().hasKey(ItemThermalUnderwear.THERMALUNDERWEAR)
							? chest.getTagCompound().getCompoundTag(ItemThermalUnderwear.THERMALUNDERWEAR)
							: new NBTTagCompound();
					if (!thermal.hasKey(ItemThermalUnderwear.DAMAGE)
							|| thermal.getInteger(ItemThermalUnderwear.DAMAGE) <= stack.getMaxDamage() / 2) {
						thermal.setInteger(ItemThermalUnderwear.DAMAGE, stack.getMaxDamage() - stack.getItemDamage());
						chest.getTagCompound().setTag(ItemThermalUnderwear.THERMALUNDERWEAR, thermal);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean armorHasThermalUnderwear(ItemStack stack) {
		return !stack.isEmpty() && (stack.getItem() instanceof ItemThermalUnderwear
				|| ((stack.getItem() instanceof ItemArmor) && ((!ConfigInfo.thermalunderwear) || (stack.hasTagCompound()
						&& stack.getTagCompound().hasKey(ItemThermalUnderwear.THERMALUNDERWEAR)))));
	}

	public static void damageThermalUnderwear(EntityPlayer player, ItemStack stack) {
		if (ConfigInfo.thermalunderwear && ConfigInfo.damage)
			if (stack.getItem() instanceof ItemThermalUnderwear) {
				stack.setItemDamage(stack.getItemDamage() + player.world.rand.nextInt(ConfigInfo.damageValue));
				if (stack.getItemDamage() >= stack.getMaxDamage()) {
					player.renderBrokenItemStack(stack);
					player.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
				}
			} else if (stack.hasTagCompound() && stack.getTagCompound().hasKey(ItemThermalUnderwear.THERMALUNDERWEAR)) {
				NBTTagCompound thermal = (NBTTagCompound) stack.getTagCompound()
						.getTag(ItemThermalUnderwear.THERMALUNDERWEAR);
				thermal.setInteger(ItemThermalUnderwear.DAMAGE, thermal.getInteger(ItemThermalUnderwear.DAMAGE)
						- player.world.rand.nextInt(ConfigInfo.damageValue));
				if (thermal.getInteger(ItemThermalUnderwear.DAMAGE) <= 0)
					stack.getTagCompound().removeTag(ItemThermalUnderwear.THERMALUNDERWEAR);
				else
					stack.getTagCompound().setTag(ItemThermalUnderwear.THERMALUNDERWEAR, thermal);
			}
	}

	public static boolean canSunDamage(EntityPlayer player) {
		if (!player.isCreative()) {
			boolean flag = true, flag1 = true;
			ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if (armorHasThermalUnderwear(head)) {
				damageThermalUnderwear(player, head);
				flag = false;
			}
			if (armorHasThermalUnderwear(chest)) {
				damageThermalUnderwear(player, chest);
				flag1 = false;
			}
			return ConfigInfo.cloak ? (flag1 || flag) : flag;
		} else
			return false;
	}
}
