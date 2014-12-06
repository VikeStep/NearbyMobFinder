package com.vikestep.nearbymobfinder.client.reference;

import com.vikestep.nearbymobfinder.common.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keys
{
    public static KeyBinding findMobs = new KeyBinding(Names.Keys.FIND_NEARBY_MOBS, Keyboard.KEY_U, Names.Keys.CATEGORY);
}
