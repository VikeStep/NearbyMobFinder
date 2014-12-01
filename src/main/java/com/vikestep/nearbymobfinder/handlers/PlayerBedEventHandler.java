package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.configuration.Settings;
import com.vikestep.nearbymobfinder.util.NearbyMobHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class PlayerBedEventHandler
{
    @SubscribeEvent
    public void onPlayerSleepInBedEvent (PlayerSleepInBedEvent event)
    {
        List<EntityMob> list = NearbyMobHelper.findNearbyMobs(event.entityPlayer, event.pos);
        //Checks for nearby mobs, night time, player distance from bed, and config to fire message.
        if (Settings.enableNearbyMobCheckAtBed && !list.isEmpty() && !event.entityPlayer.worldObj.isDaytime() && !(Math.abs(event.entityPlayer.posX - (double)event.pos.getX()) > 3.0D || Math.abs(event.entityPlayer.posY - (double)event.pos.getY()) > 2.0D || Math.abs(event.entityPlayer.posZ - (double)event.pos.getZ()) > 3.0D))
        {
            //TickHandler is used so that the nearby mobs message occurs the tick after the "You can't sleep right now..." message appears
            TickHandler.playerRequesting = event.entityPlayer;
            TickHandler.nearbyMobList = list;
        }
    }
}
