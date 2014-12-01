package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.handlers.TickHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ServerProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers ()
    {
        FMLCommonHandler.instance().bus().register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
    }
}
