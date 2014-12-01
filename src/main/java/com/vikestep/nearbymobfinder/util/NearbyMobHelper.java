package com.vikestep.nearbymobfinder.util;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

import java.util.List;

public class NearbyMobHelper
{
    //Returns a list of nearby mobs in a 16*16*10 (x*z*y) rectangular prism around a BlockPos pos
    public static List<EntityMob> findNearbyMobs(EntityPlayer player, BlockPos pos)
    {
        return findNearbyMobs(player, pos.getX(), pos.getY(), pos.getZ());
    }

    //Returns a list of nearby mobs in a 16*16*10 (x*z*y) rectangular prism around (x, y, z) coordinates
    public static List<EntityMob> findNearbyMobs(EntityPlayer player, double x, double y, double z)
    {
        double d0 = 8.0D;
        double d1 = 5.0D;
        return player.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(x - d0, y - d1, z - d0, x + d0, y + d1, z + d0));
    }
}
