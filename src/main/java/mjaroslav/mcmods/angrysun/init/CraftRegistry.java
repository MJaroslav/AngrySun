package mjaroslav.mcmods.angrysun.init;

import mjaroslav.mcmods.angrysun.lib.ConfigInfo;
import mjaroslav.mcmods.angrysun.lib.ModInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;

public class CraftRegistry {
    public static void register() {
        if (ConfigInfo.craft) {
            registerRecipes("stitched_reed");
            registerRecipes("thermalunderwear_head");
            if (ConfigInfo.cloak)
                registerRecipes("thermalunderwear_chest");
        }
    }

    private static void registerRecipes(String name) {
        CraftingHelper.register(new ResourceLocation(ModInfo.MODID, name),
                (IRecipeFactory) (context, json) -> CraftingHelper.getRecipe(json, context));
    }
}
