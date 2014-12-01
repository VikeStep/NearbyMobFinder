package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.configuration.Settings;
import com.vikestep.nearbymobfinder.reference.Keybindings;
import com.vikestep.nearbymobfinder.util.NearbyMobHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.List;

public class KeyHandler
{
    @SubscribeEvent
    public void onKeyEvent(InputEvent.KeyInputEvent event)
    {
        if(Keybindings.findMobs.isPressed())
        {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
            List<EntityMob> list = NearbyMobHelper.findNearbyMobs(player, player.posX, player.posY, player.posZ);
            //If enabled in config
            if (Settings.enableNearbyMobCheckAllTime)
            {
                ChatComponentText preMessage = new ChatComponentText("Nearby Mobs:");
                player.addChatComponentMessage(preMessage);
                if (list.size() != 0)
                {
                    for (EntityMob mobFound : list)
                    {
                        String MOB_LOCATION = mobFound.getName() + " x: " + Math.floor(mobFound.posX) + ", z: " + Math.floor(mobFound.posZ) + " (y: " + Math.floor(mobFound.posY) + ")";
                        ChatComponentText mobLocation = new ChatComponentText(MOB_LOCATION);
                        player.addChatComponentMessage(mobLocation);
                    }
                } else {
                    ChatComponentText message = new ChatComponentText("No Mobs Found Nearby");
                    player.addChatComponentMessage(message);
                }
            }
        }
    }
}
