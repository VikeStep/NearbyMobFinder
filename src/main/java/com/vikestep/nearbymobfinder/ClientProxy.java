package com.vikestep.nearbymobfinder;

import com.vikestep.nearbymobfinder.client.handlers.KeyInputHandler;
import com.vikestep.nearbymobfinder.client.reference.Keys;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

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
