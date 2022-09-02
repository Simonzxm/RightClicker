package io.github.simonzxm;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = RightClicker.MODID , version = RightClicker.VERSION)
public class RightClicker {
    public static final String MODID = "rightclicker";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new rcCommand());
    }
}
