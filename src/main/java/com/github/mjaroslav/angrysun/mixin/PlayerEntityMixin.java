package com.github.mjaroslav.angrysun.mixin;

import com.github.mjaroslav.angrysun.utils.ThermalUnderwearUtils;
import lombok.val;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo callback) {
        if (world.isDay() && !world.isClient) {
            val f = getBrightnessAtEyes();
            if (f > 0.5F && world.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                // TODO: alt check
                val flag = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, getBlockPos()).getY() >= getY();
                if (flag && ThermalUnderwearUtils.canDamageThermalUnderwear(this)) {
                    // TODO: effects
                    if (true) {
                        if (true)
                            addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 160));
                        if (true)
                            addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 160));
                        if (true)
                            addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 160));
                    }
                    setFireTicks(160);
                }
            }
        }
    }
}
