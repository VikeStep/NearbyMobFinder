package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.KeyHandler;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.handlers.TickHandler;
import com.vikestep.nearbymobfinder.reference.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerEventHandlers ()
    {
        ClientRegistry.registerKeyBinding(Keybindings.findMobs);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
        FMLCommonHandler.instance().bus().register(new TickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
    }
}
