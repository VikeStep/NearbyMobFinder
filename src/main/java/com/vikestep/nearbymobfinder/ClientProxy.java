package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.client.handlers.KeyInputHandler;
import com.vikestep.nearbymobfinder.client.reference.Keys;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keys.findMobs);
    }
}
