package mjaroslav.mcmods.angrysun.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModInfo {
	public static final String MODID = "angrysun";
	public static final String NAME = "Angry Sun";
	public static final String VERSION = "1.0.0";
	public static final String CLIENTPROXY = "mjaroslav.mcmods.angrysun.client.ClientProxy";
	public static final String COMMONPROXY = "mjaroslav.mcmods.angrysun.common.CommonProxy";
	public static final String GUIFACTORY = "mjaroslav.mcmods.angrysun.client.gui.GuiFactory";
	public static final Logger LOG = LogManager.getLogger(NAME);
}
