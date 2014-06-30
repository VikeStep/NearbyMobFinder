package com.vikestep.nearbymobfinder.configuration;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigurationHandler
{
    private static Configuration config;

    private static final String CATEGORY_GENERAL = "General";

    public static void loadConfigs(File configPath)
    {
        config = new Configuration(configPath);

        new ConfigCategory(CATEGORY_GENERAL);

        config.load();

        Property propEnableNearbyMobCheckAtBed = config.get(CATEGORY_GENERAL,"enableNearbyMobCheckAtBed", true);
        propEnableNearbyMobCheckAtBed.comment = "Set to true to enable the nearby mod checker when using a bed at night time";
        Settings.enableNearbyMobCheckAtBed = propEnableNearbyMobCheckAtBed.getBoolean(true);

        Property propEnableNearbyMobCheckAllTime = config.get(CATEGORY_GENERAL,"enableNearbyMobCheckAllTime", false);
        propEnableNearbyMobCheckAllTime.comment = "Set to true to enable the use of a keybind to check for nearby mobs at all times";
        Settings.enableNearbyMobCheckAllTime = propEnableNearbyMobCheckAllTime.getBoolean(false);

        config.save();
    }
}
