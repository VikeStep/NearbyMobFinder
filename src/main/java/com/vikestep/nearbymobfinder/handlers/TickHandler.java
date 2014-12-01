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

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        if(nearbyMobList != null)
        {
            for (int i = 0; i < nearbyMobList.size(); i++)
            {
                EntityMob mobFound = nearbyMobList.get(i);
                String CHAT_MESSAGE = mobFound.getName() + " x: " + Math.floor(mobFound.posX) + ", z: " + Math.floor(mobFound.posZ) + " (y: " + Math.floor(mobFound.posY) + ")";
                ChatComponentText component = new ChatComponentText(CHAT_MESSAGE);
                playerRequesting.addChatComponentMessage(component);
            }
            nearbyMobList = null;
            playerRequesting = null;
        }
    }
}
