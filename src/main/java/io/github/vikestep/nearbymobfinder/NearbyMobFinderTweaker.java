package io.github.vikestep.nearbymobfinder;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class NearbyMobFinderTweaker implements ITweaker
{
    public static final Logger LOGGER = LogManager.getLogger("Nearby Mob Finder");

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile)
    {

    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader)
    {
        classLoader.registerTransformer(NearbyMobFinderTransformer.class.getName());
    }

    @Override
    public String getLaunchTarget()
    {
        return null;
    }

    @Override
    public String[] getLaunchArguments()
    {
        return new String[0];
    }
}
