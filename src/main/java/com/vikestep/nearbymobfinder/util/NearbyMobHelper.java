package com.vikestep.nearbymobfinder.util;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import java.util.List;

public class NearbyMobHelper
{
    public static List<EntityMob> findNearbyMobsBed(EntityPlayer player, BlockPos pos)
    {
        double d0 = 8.0D;
        double d1 = 5.0D;
        List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB((double) pos.getX() - d0, (double) pos.getY() - d1, (double) pos.getZ() - d0, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d0));
        return list;
    }

    public static List<EntityMob> findNearbyMobsPlayer(EntityPlayer player, double x, double y, double z)
    {
        double d0 = 8.0D;
        double d1 = 5.0D;
        List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB((double) x - d0, (double) y - d1, (double) z - d0, (double) x + d0, (double) y + d1, (double) z + d0));
        return list;
    }
}
