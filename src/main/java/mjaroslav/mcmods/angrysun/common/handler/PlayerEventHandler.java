package mjaroslav.mcmods.angrysun.common.handler;

import mjaroslav.mcmods.angrysun.ModAngrySun;
import mjaroslav.mcmods.angrysun.common.item.ItemThermalUnderwear;
import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerEventHandler {
    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (event.player.world.isDaytime() && !event.player.world.isRemote) {
            float f = event.player.getBrightness();
            if (f > 0.5F && event.player.world.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                boolean flag = false;
                if (ConfigInfo.altCheck)
                    flag = !((event.player.world.getPrecipitationHeight(event.player.getPosition())
                            .getY() >= event.player.getEyeHeight() + event.player.posY)
                            || event.player.world.isRainingAt(new BlockPos(event.player.posX,
                                    event.player.posY + (double) event.player.getEyeHeight(), event.player.posZ)));
                else
                    flag = event.player.world.canSeeSky(event.player.getPosition());
                if (flag && ModAngrySun.canSunDamage(event.player)) {
                    if (ConfigInfo.effects) {
                        if (ConfigInfo.useNausea)
                            event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 160));
                        if (ConfigInfo.useBlindness)
                            event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 160));
                        if (ConfigInfo.useWeakness)
                            event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 160));
                    }
                    event.player.setFire(8);
                }
            }
        }
    }
}
