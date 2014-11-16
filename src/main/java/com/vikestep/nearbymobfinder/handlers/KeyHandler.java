package com.vikestep.nearbymobfinder.handlers;

import com.vikestep.nearbymobfinder.configuration.Settings;
import com.vikestep.nearbymobfinder.reference.Keybindings;
import com.vikestep.nearbymobfinder.util.NearbyMobHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

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
            if (Settings.enableNearbyMobCheckAllTime && list.size() != 0)
            {
                ChatComponentText warning = new ChatComponentText("Nearby Mobs:");
                player.addChatComponentMessage(warning);
                for (int i = 0; i < list.size(); i++)
                {
                    EntityMob mobFound = list.get(i);
                    String CHAT_MESSAGE = mobFound.getCommandSenderName() + " x: " + Math.floor(mobFound.posX) + ", z: " + Math.floor(mobFound.posZ) + " (y: " + Math.floor(mobFound.posY) + ")";
                    ChatComponentText mobLocation = new ChatComponentText(CHAT_MESSAGE);
                    player.addChatComponentMessage(mobLocation);
                }
            }
            else if(Settings.enableNearbyMobCheckAllTime)
            {
                ChatComponentText warning = new ChatComponentText("Nearby Mobs:");
                player.addChatComponentMessage(warning);
                ChatComponentText message = new ChatComponentText("No Mobs Found Nearby");
                player.addChatComponentMessage(message);
            }
        }
    }
}
