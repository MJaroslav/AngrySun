package mjaroslav.mcmods.angrysun.common.handler;

import mjaroslav.mcmods.angrysun.ModAngrySun;
import mjaroslav.mcmods.angrysun.common.item.ItemThermalUnderwear;
import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {
    @SubscribeEvent
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        if (!event.getItemStack().isEmpty() && event.getItemStack().hasTagCompound()
                && event.getItemStack().getTagCompound().hasKey(ItemThermalUnderwear.THERMALUNDERWEAR)) {
            NBTTagCompound thermal = (NBTTagCompound) event.getItemStack().getTagCompound()
                    .getTag(ItemThermalUnderwear.THERMALUNDERWEAR);
            event.getToolTip().add(1,
                    I18n.format("tooltip.thermalunderwear.damage", thermal.getInteger(ItemThermalUnderwear.DAMAGE)));
        }
    }
}
