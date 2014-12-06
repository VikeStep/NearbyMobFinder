package com.vikestep.nearbymobfinder.client.handlers;

import com.vikestep.nearbymobfinder.client.reference.Keys;
import com.vikestep.nearbymobfinder.common.reference.Settings;
import com.vikestep.nearbymobfinder.common.util.NearbyMobLocator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.List;

public class KeyInputHandler
{
    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if(Keys.findMobs.isPressed())
        {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            List<EntityMob> list = NearbyMobLocator.findNearbyMobs(player, player.posX, player.posY, player.posZ);
            //If enabled in config
            if (Settings.checkAllTime)
            {
                ChatComponentText preMessage = new ChatComponentText("Nearby Mobs:");
                player.addChatComponentMessage(preMessage);
                if (list.size() != 0)
                {
                    for (EntityMob mobFound : list)
                    {
                        String MOB_LOCATION = mobFound.getName() + " x: " + (int) mobFound.posX + ", z: " + (int) mobFound.posZ + " (y: " + (int) mobFound.posY + ")";
                        ChatComponentText mobLocation = new ChatComponentText(MOB_LOCATION);
                        player.addChatComponentMessage(mobLocation);
                    }
                }
                else
                {
                    ChatComponentText message = new ChatComponentText("No Mobs Found Nearby");
                    player.addChatComponentMessage(message);
                }
            }
        }
    }
}
