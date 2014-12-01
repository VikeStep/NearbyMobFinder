package com.vikestep.nearbymobfinder.handlers;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class TickHandler
{
    public static List<EntityMob> nearbyMobList = null;
    public static EntityPlayer playerRequesting = null;

    //Subscribing to server tick since the PlayerSleepInBed event only fires on server side when connected to a server
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        //Null check in case although this should not occur
        if(nearbyMobList != null)
        {
            for (EntityMob mobFound : nearbyMobList)
            {
                String MOB_LOCATION = mobFound.getName() + " x: " + Math.floor(mobFound.posX) + ", z: " + Math.floor(mobFound.posZ) + " (y: " + Math.floor(mobFound.posY) + ")";
                ChatComponentText mobLocation = new ChatComponentText(MOB_LOCATION);
                playerRequesting.addChatComponentMessage(mobLocation);
            }
            nearbyMobList = null;
            playerRequesting = null;
        }
    }
}
