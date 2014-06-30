package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.configuration.Settings;
import com.vikestep.nearbymobfinder.util.NearbyMobHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import java.util.List;

public class PlayerBedEventHandler
{
    @SubscribeEvent
    public void onPlayerSleepInBedEvent (PlayerSleepInBedEvent event)
    {
        List<EntityMob> list = NearbyMobHelper.findNearbyMobs(event.entityPlayer, event.x, event.y, event.z);
        if (Settings.enableNearbyMobCheckAtBed && !list.isEmpty() && !event.entityPlayer.worldObj.isDaytime() && !(Math.abs(event.entityPlayer.posX - (double)event.x) > 3.0D || Math.abs(event.entityPlayer.posY - (double)event.y) > 2.0D || Math.abs(event.entityPlayer.posZ - (double)event.z) > 3.0D))
        {
            TickHandler.playerRequesting = event.entityPlayer;
            TickHandler.nearbyMobList = list;
        }
    }
}