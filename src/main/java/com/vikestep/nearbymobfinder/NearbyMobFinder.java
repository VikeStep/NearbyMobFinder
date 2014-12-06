package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.common.configuration.ConfigurationHandler;
import com.vikestep.nearbymobfinder.common.reference.ModInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class NearbyMobFinder
{
    @Mod.Instance(ModInfo.MOD_ID)
    public static NearbyMobFinder instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_PATH, serverSide = ModInfo.SERVER_PROXY_PATH)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        proxy.registerKeyBindings();
        proxy.registerEventHandlers();
    }
}
