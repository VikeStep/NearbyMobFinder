package com.vikestep.nearbymobfinder.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import java.util.List;

public class PlayerBedEventHandler
{
    @SubscribeEvent
    public void onPlayerSleepInBedEvent (PlayerSleepInBedEvent event)
    {
        int bedX = event.x;
        int bedY = event.y;
        int bedZ = event.z;
        EntityPlayer playerAttemptingToSleep = event.entityPlayer;

        double d0 = 8.0D;
        double d1 = 5.0D;
        List<EntityMob> list = playerAttemptingToSleep.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)bedX - d0, (double)bedY - d1, (double)bedZ - d0, (double)bedX + d0, (double)bedY + d1, (double)bedZ + d0));
        if (!list.isEmpty() && !playerAttemptingToSleep.worldObj.isDaytime() && !(Math.abs(playerAttemptingToSleep.posX - (double)bedX) > 3.0D || Math.abs(playerAttemptingToSleep.posY - (double)bedY) > 2.0D || Math.abs(playerAttemptingToSleep.posZ - (double)bedZ) > 3.0D))
        {
            TickHandler.playerAttemptingToSleep = playerAttemptingToSleep;
            TickHandler.nearbyMobList = list;
        }
    }
}
