package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.configuration.Settings;
import com.vikestep.nearbymobfinder.reference.Keybindings;
import com.vikestep.nearbymobfinder.util.NearbyMobHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class KeyHandler
{
    @SubscribeEvent
    public void onKeyEvent(InputEvent.KeyInputEvent event)
    {
        if(Keybindings.findMobs.isPressed())
        {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            List<EntityMob> list = NearbyMobHelper.findNearbyMobs(player, player.posX, player.posY, player.posZ);
            if (Settings.enableNearbyMobCheckAllTime && !list.isEmpty())
            {
                TickHandler.playerRequesting = player;
                TickHandler.nearbyMobList = list;
            }
        }
    }
}
