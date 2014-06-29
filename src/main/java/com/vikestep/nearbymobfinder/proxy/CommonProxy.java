package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.handlers.TickHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy implements IProxy
{
    public void registerEventHandlers()
    {
        TickHandler thandler = new TickHandler();
        FMLCommonHandler.instance().bus().register(thandler);
        MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
        //MinecraftForge.EVENT_BUS.register(thandler);
    }
}
