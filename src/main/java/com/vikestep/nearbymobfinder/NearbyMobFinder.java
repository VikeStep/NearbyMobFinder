package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.proxy.IProxy;
import com.vikestep.nearbymobfinder.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
    }
}
