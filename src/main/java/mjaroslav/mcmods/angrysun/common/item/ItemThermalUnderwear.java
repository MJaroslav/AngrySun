package mjaroslav.mcmods.angrysun.common.item;

import mjaroslav.mcmods.angrysun.ModAngrySun;
import mjaroslav.mcmods.angrysun.init.ItemRegistry;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemThermalUnderwear extends ItemArmor {
    public static final String THERMALUNDERWEAR = "thermalunderwear";
    public static final String DAMAGE = "damage";

    public ItemThermalUnderwear(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(ItemRegistry.THERMAL, renderIndexIn, equipmentSlotIn);
        setRegistryName("thermalunderwear_" + equipmentSlotIn.getName());
        setUnlocalizedName("thermalunderwear_" + equipmentSlotIn.getName());
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return nbttagcompound != null && nbttagcompound.hasKey("display", 10)
                ? nbttagcompound.getCompoundTag("display").hasKey("color", 3) : false;
    }

    @Override
    public int getColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3)) {
                return nbttagcompound1.getInteger("color");
            }
        }
        return 0xFFFFFF;
    }

    @Override
    public void removeColor(ItemStack stack) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1.hasKey("color")) {
                nbttagcompound1.removeTag("color");
            }
        }
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
        if (!nbttagcompound.hasKey("display", 10)) {
            nbttagcompound.setTag("display", nbttagcompound1);
        }
        nbttagcompound1.setInteger("color", color);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);
        if (playerIn.isSneaking()) {
            if (ModAngrySun.addThermalUnderwearToArmor(playerIn, playerIn.getHeldItem(handIn))) {
                itemstack.setCount(0);
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
            } else
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        } else if (itemstack1.isEmpty()) {
            playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
            itemstack.setCount(0);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        } else {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
            float hitY, float hitZ, EnumHand hand) {
        ItemStack stack = player.getHeldItemMainhand();
        IBlockState state = world.getBlockState(pos);
        if (!stack.isEmpty())
            if (world.getBlockState(pos).getBlock() == Blocks.CAULDRON) {
                int i = ((Integer) world.getBlockState(pos).getValue(BlockCauldron.LEVEL)).intValue();
                if (i > 0 && stack.getItem() instanceof ItemThermalUnderwear) {
                    ItemThermalUnderwear itemarmor = (ItemThermalUnderwear) stack.getItem();
                    if (itemarmor.hasColor(stack) && !world.isRemote) {
                        itemarmor.removeColor(stack);
                        ((BlockCauldron) state.getBlock()).setWaterLevel(world, pos, state, i - 1);
                        player.addStat(StatList.ARMOR_CLEANED);
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }
}
