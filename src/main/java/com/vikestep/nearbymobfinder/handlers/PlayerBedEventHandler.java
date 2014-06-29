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
        List<EntityMob> list = playerAttemptingToSleep.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getAABBPool().getAABB((double)bedX - d0, (double)bedY - d1, (double)bedZ - d0, (double)bedX + d0, (double)bedY + d1, (double)bedZ + d0));
        if (!list.isEmpty() && !playerAttemptingToSleep.worldObj.isDaytime())
        {
            TickHandler.playerAttemptingToSleep = playerAttemptingToSleep;
            TickHandler.nearbyMobList = list;
        }
    }
}
