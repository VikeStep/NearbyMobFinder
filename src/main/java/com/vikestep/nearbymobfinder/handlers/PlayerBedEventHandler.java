package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import java.util.List;

public class PlayerBedEventHandler
{

    @SuppressWarnings("unchecked")
    private void setupTickHandler(int bedX, int bedY, int bedZ, EntityPlayer playerAttemptingToSleep) {
        double d0 = 8.0D;
        double d1 = 5.0D;
        boolean isTimeOfDayOk = Settings.showMobsAtAnyTimeOfDay || !playerAttemptingToSleep.worldObj.isDaytime();
        List<EntityMob> list = playerAttemptingToSleep.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)bedX - d0, (double)bedY - d1, (double)bedZ - d0, (double)bedX + d0, (double)bedY + d1, (double)bedZ + d0));
        if (!list.isEmpty() && isTimeOfDayOk && !(Math.abs(playerAttemptingToSleep.posX - (double)bedX) > 3.0D || Math.abs(playerAttemptingToSleep.posY - (double)bedY) > 2.0D || Math.abs(playerAttemptingToSleep.posZ - (double)bedZ) > 3.0D))
        {
            TickHandler.playerAttemptingToSleep = playerAttemptingToSleep;
            TickHandler.nearbyMobList = list;
        }
    }

    @SubscribeEvent
    public void onPlayerClickOnBedEvent (PlayerInteractEvent event)
    {
        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
            return;
        if (event.useBlock == Event.Result.DENY)
            return;
        if (!event.world.getBlock(event.x, event.y, event.z).getUnlocalizedName().equals(Blocks.bed.getUnlocalizedName()))
            return;
        if (event.entityPlayer instanceof EntityClientPlayerMP)
            return;
        setupTickHandler(event.x, event.y, event.z, event.entityPlayer);
    }
}
