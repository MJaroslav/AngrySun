package mjaroslav.mcmods.angrysun.client.gui;

import java.util.Set;

import mjaroslav.mcmods.angrysun.common.config.Config;
import mjaroslav.mcmods.angrysun.lib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class GuiFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ModGuiConfig(parentScreen);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	public static class ModGuiConfig extends GuiConfig {
		public ModGuiConfig(GuiScreen parentScreen) {
			super(parentScreen, Config.getElements(), ModInfo.MODID, true, true, ModInfo.NAME);
		}
	}
}
