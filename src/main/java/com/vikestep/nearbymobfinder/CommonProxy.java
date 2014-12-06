package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.common.configuration.ConfigurationHandler;
import com.vikestep.nearbymobfinder.common.handlers.PlayerSleepInBedHandler;
import com.vikestep.nearbymobfinder.common.handlers.ServerTickHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy
{
    public void registerEventHandlers()
    {
        FMLCommonHandler.instance().bus().register(new ServerTickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerSleepInBedHandler());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    }

    public void registerKeyBindings()
    {
        //Key Bindings are not registered on the server
    }
}
