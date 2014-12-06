package com.vikestep.nearbymobfinder.common.handlers;

import com.vikestep.nearbymobfinder.common.util.NearbyMobLocator;
import com.vikestep.nearbymobfinder.common.reference.Settings;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerSleepInBedHandler
{
    @SubscribeEvent
    public void onPlayerSleepEvent(PlayerSleepInBedEvent event)
    {
        if (Settings.checkAtBed && NearbyMobLocator.areConditionsMet(event.entityPlayer, event.pos))
        {
            ServerTickHandler.playerRequesting = event.entityPlayer;
            ServerTickHandler.nearbyMobList = NearbyMobLocator.findNearbyMobs(event.entityPlayer, event.pos);
        }
    }
}