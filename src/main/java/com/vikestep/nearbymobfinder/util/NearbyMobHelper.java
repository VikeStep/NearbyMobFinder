package com.vikestep.nearbymobfinder.util;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class NearbyMobHelper
{
    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z)
    {
        double d0 = 8.0D;
        double d1 = 5.0D;
        List<EntityMob> list = player.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double) x - d0, (double) y - d1, (double) z - d0, (double) x + d0, (double) y + d1, (double) z + d0));
        return list;
    }
}
