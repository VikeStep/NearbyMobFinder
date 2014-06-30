package com.vikestep.nearbymobfinder.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class TickHandler
{
    public static List<EntityMob> nearbyMobList = null;
    public static EntityPlayer playerRequesting = null;

    @SubscribeEvent
    public void postChatUpdate(ClientTickEvent event)
    {
        if(nearbyMobList != null)
        {
            int size = nearbyMobList.size();
            for (int i = 0; i < size; i++)
            {
                EntityMob mobFound = nearbyMobList.get(i);
                String CHAT_MESSAGE = mobFound.func_145748_c_().getFormattedText() + ": " + Math.floor(mobFound.posX) + ", " + Math.floor(mobFound.posY) + ", " + Math.floor(mobFound.posZ);
                ChatComponentText component = new ChatComponentText(CHAT_MESSAGE);
                playerRequesting.addChatComponentMessage(component);
            }
            nearbyMobList = null;
            playerRequesting = null;
        }
    }
}