package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
    }
}
