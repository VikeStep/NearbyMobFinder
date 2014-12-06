package com.vikestep.nearbymobfinder.common.configuration;

import com.vikestep.nearbymobfinder.common.reference.ModInfo;
import com.vikestep.nearbymobfinder.common.reference.Settings;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration config;

    public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        String CATEGORY = Configuration.CATEGORY_GENERAL;
        String COMMENT;

        COMMENT = "Set to true to enable the nearby mod checker when using a bed at night time";
        Settings.checkAtBed = config.getBoolean("checkAtBed", CATEGORY, true, COMMENT);
        COMMENT = "Set to true to enable the use of a key binding to check for nearby mobs at all times";
        Settings.checkAllTime = config.getBoolean("checkAllTime", CATEGORY, false, COMMENT);

        if (config.hasChanged())
        {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(ModInfo.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
