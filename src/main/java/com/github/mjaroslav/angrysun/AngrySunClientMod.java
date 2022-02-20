package com.github.mjaroslav.angrysun;

import com.github.mjaroslav.angrysun.item.ModItems;
import com.github.mjaroslav.angrysun.lib.ModInfo;
import com.github.mjaroslav.angrysun.utils.ThermalUnderwearInfo;
import lombok.val;
import lombok.var;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class AngrySunClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            val info = ThermalUnderwearInfo.loadFromStack(stack);
            if (!info.isGenerated()) {
                var damage = Math.round(info.getDamage() * 100f / info.getMaxDamage()) + "%";
                if (context.isAdvanced())
                    damage = info.getDamage() + "/" + info.getMaxDamage();
                lines.add(new TranslatableText("tooltip.angrysun.thermalunderwear.damage", damage));
            }
        });
        ArmorRenderingRegistry.registerSimpleTexture(new Identifier(ModInfo.MOD_ID, "thermalunderwear"),
                ModItems.THERMAL_CHEST, ModItems.THERMAL_HEAD);
    }
}
