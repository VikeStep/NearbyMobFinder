package com.vikestep.nearbymobfinder.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import java.util.List;

public class PlayerBedEventHandler
{
    private static boolean isDelaying = false;
    private static boolean delayState = false;
    private static boolean messageSendable = false;

    @SubscribeEvent
    public void onPlayerSleepInBedEvent (PlayerSleepInBedEvent event)
    {
        int bedX = event.x;
        int bedY = event.y;
        int bedZ = event.z;
        EntityPlayer playerAttemptingToSleep = event.entityPlayer;

        double d0 = 8.0D;
        double d1 = 5.0D;
        List<EntityMob> list = playerAttemptingToSleep.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getAABBPool().getAABB((double)bedX - d0, (double)bedY - d1, (double)bedZ - d0, (double)bedX + d0, (double)bedY + d1, (double)bedZ + d0));
        if (!list.isEmpty() && !playerAttemptingToSleep.worldObj.isDaytime())
        {
            int size = list.size();
            for (int i = 1; i < size; i++)
            {
                EntityMob mobFound = list.get(i);
                String CHAT_MESSAGE = mobFound.func_145748_c_().getFormattedText() + ": " + Math.floor(mobFound.posX) + ", " + Math.floor(mobFound.posY) + ", " + Math.floor(mobFound.posZ);
                ChatComponentText component = new ChatComponentText(CHAT_MESSAGE);
                playerAttemptingToSleep.addChatComponentMessage(component);
            }
        }
    }
}
