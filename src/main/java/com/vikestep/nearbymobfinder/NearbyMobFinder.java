package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.configuration.ConfigurationHandler;
import com.vikestep.nearbymobfinder.proxy.IProxy;
import com.vikestep.nearbymobfinder.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
        ConfigurationHandler.loadConfigs(event.getSuggestedConfigurationFile());
        proxy.registerEventHandlers();
    }
}
