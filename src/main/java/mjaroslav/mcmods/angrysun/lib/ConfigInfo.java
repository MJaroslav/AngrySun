package mjaroslav.mcmods.angrysun.lib;

import net.minecraftforge.common.config.Configuration;

public class ConfigInfo {
	public static final String GENERAL = "general";
	public static final String EFFECTS = GENERAL + ".effects";
	public static final String THERMALUNDERWEAR = GENERAL + ".thermalunderwear";

	/**
	 * Alternative check of the sun. If there is any block above your head, you
	 * will not burn.
	 */
	public static boolean altCheck;
	/**
	 * Use thermal underwear. If it's disabled, then any armor is used.
	 */
	public static boolean thermalunderwear;
	/**
	 * Use the cloak in conjunction with the hood.
	 */
	public static boolean cloak;
	/**
	 * To cause damage to thermal underwear.
	 */
	public static boolean damage;
	/**
	 * Use the standard recipe for crafting thermal underwear.
	 */
	public static boolean craft;
	/**
	 * Maximum random damage value when thermal underwear using.
	 */
	public static int damageValue;
	/**
	 * Use the nausea effect when the player is under the sun.
	 */
	public static boolean useNausea;
	/**
	 * Use the blindness effect when the player is under the sun.
	 */
	public static boolean useBlindness;
	/**
	 * Use the weakness effect when the player is under the sun.
	 */
	public static boolean useWeakness;
	/**
	 * Strength factor of hood (11) and cloak (16).
	 */
	public static int durability;
	/**
	 * Use effects when the player is under the sun.
	 */
	public static boolean effects;

	public static void read(Configuration config) {
		altCheck = config.getBoolean("alt_solar_checking", GENERAL, true,
				"Alternative check of the sun. If there is any block above your head, you will not burn.");
		thermalunderwear = config
				.get(GENERAL, "enable_thermal_underwear", true,
						"Use thermal underwear. If it's disabled, then any armor is used.")
				.setRequiresMcRestart(true).getBoolean();
		cloak = config.get(THERMALUNDERWEAR, "use_cloack", true, "Use the cloak in conjunction with the hood.")
				.setRequiresMcRestart(true).getBoolean();
		damage = config.getBoolean("damage", THERMALUNDERWEAR, true, "To cause damage to thermal underwear.");
		craft = config
				.get(THERMALUNDERWEAR, "use_recipe", true, "Use the standard recipe for crafting thermal underwear.")
				.setRequiresMcRestart(true).getBoolean();
		damageValue = config.getInt("damage_value", THERMALUNDERWEAR, 2, 1, 64,
				"Maximum random damage value when thermal underwear using.");
		useNausea = config.getBoolean("use_nausea", EFFECTS, true,
				"Use the nausea effect when the player is under the sun.");
		useBlindness = config.getBoolean("use_blindness", EFFECTS, true,
				"Use the blindness effect when the player is under the sun.");
		useWeakness = config.getBoolean("use_weakness", EFFECTS, true,
				"Use the weakness effect when the player is under the sun.");
		durability = config.get(THERMALUNDERWEAR, "durability", 80, "Strength factor of hood (11) and cloak (16).")
				.setMinValue(20).setMaxValue(200).setRequiresMcRestart(true).getInt();
		effects = config.getBoolean("use_effects", GENERAL, true, "Use effects when the player is under the sun.");
	}
}
