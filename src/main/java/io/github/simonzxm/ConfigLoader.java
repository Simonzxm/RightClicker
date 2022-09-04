package io.github.simonzxm;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader {
    private static Configuration config;
    private static Logger logger;
    public static int beforeWaitTime;
    public static int afterWaitTime;

    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    public static void load()
    {
        beforeWaitTime = config.get(Configuration.CATEGORY_GENERAL, "beforeWaitTime", 100, "Time to Wait before Right Click (ms)").getInt();
        afterWaitTime = config.get(Configuration.CATEGORY_GENERAL, "afterWaitTime", 100, "Time to Wait after Right Click (ms)").getInt();

        config.save();
        logger.info("RightClicker Config Loaded!");
    }

    public static Logger logger()
    {
        return logger;
    }
}
