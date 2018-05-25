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
                "Alternative check of the sun. If there is any block above your head, you will not burn.",
                GENERAL + ".alt_solar_checking");
        thermalunderwear = config.getBoolean("enable_thermal_underwear", GENERAL, true,
                "Use thermal underwear. If it's disabled, then any armor is used.",
                GENERAL + ".enable_thermal_underwear");
        cloak = config.getBoolean("use_cloack", THERMALUNDERWEAR, true, "Use the cloak in conjunction with the hood.",
                THERMALUNDERWEAR + ".use_cloack");
        damage = config.getBoolean("damage", THERMALUNDERWEAR, true, "To cause damage to thermal underwear.",
                THERMALUNDERWEAR + ".damage");
        craft = config.getBoolean("use_recipe", THERMALUNDERWEAR, true,
                "Use the standard recipe for crafting thermal underwear.", THERMALUNDERWEAR + ".use_recipe");
        damageValue = config.getInt("damage_value", THERMALUNDERWEAR, 2, 1, 64,
                "Maximum random damage value when thermal underwear using.", THERMALUNDERWEAR + "damage_value");
        useNausea = config.getBoolean("use_nausea", EFFECTS, true,
                "Use the nausea effect when the player is under the sun.", EFFECTS + ".use_nausea");
        useBlindness = config.getBoolean("use_blindness", EFFECTS, true,
                "Use the blindness effect when the player is under the sun.", EFFECTS + ".use_blindness");
        useWeakness = config.getBoolean("use_weakness", EFFECTS, true,
                "Use the weakness effect when the player is under the sun.", EFFECTS + ".use_weakness");
        durability = config.getInt("durability", THERMALUNDERWEAR, 80, 20, 200,
                "Strength factor of hood (11) and cloak (16).", THERMALUNDERWEAR + ".durability");
        effects = config.getBoolean("use_effects", GENERAL, true, "Use effects when the player is under the sun.",
                GENERAL + "use_effects");
    }
}
