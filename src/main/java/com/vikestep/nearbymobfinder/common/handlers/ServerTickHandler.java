package com.vikestep.nearbymobfinder.common.handlers;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

import java.util.List;

public class ServerTickHandler
{
    public static List<EntityMob> nearbyMobList = null;
    public static EntityPlayer playerRequesting = null;

    //Subscribing to server tick since the PlayerSleepInBed event only fires on server side when connected to a server
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if(nearbyMobList != null)
        {
            for (EntityMob mobFound : nearbyMobList)
            {
                String MOB_LOCATION = mobFound.getCommandSenderName() + " x: " + (int) mobFound.posX + ", z: " + (int) mobFound.posZ + " (y: " + (int) mobFound.posY + ")";
                ChatComponentText mobLocation = new ChatComponentText(MOB_LOCATION);
                playerRequesting.addChatComponentMessage(mobLocation);
            }
            nearbyMobList = null;
            playerRequesting = null;
        }
    }
}
