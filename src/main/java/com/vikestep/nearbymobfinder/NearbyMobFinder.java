package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.proxy.IProxy;
import com.vikestep.nearbymobfinder.reference.Reference;
import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class NearbyMobFinder
{
    @Mod.Instance(Reference.MOD_ID)
    public static NearbyMobFinder instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_PATH, serverSide = Reference.SERVER_PROXY_PATH)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.registerEventHandlers();

        Settings.config = new Configuration(event.getSuggestedConfigurationFile());
        Settings.showMobsAtAnyTimeOfDay = Settings.config.get("Client-side", "showMobsAllTime", false).getBoolean(false);
        if(Settings.config.hasChanged())
            Settings.config.save();
    }
}
