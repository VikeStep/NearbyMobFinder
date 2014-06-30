package com.vikestep.nearbymobfinder.reference;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybindings
{
    public static KeyBinding findMobs;

    public static void registerKeybindings()
    {
        findMobs = new KeyBinding("Find Nearby Mobs", Keyboard.KEY_U, "Nearby Mob Finder");
        ClientRegistry.registerKeyBinding(findMobs);
    }
}
