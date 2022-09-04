package io.github.simonzxm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;

@Mod(modid = RightClicker.MODID , version = RightClicker.VERSION , guiFactory = "io.github.simonzxm.config.rcGuiFactory")
public class RightClicker {
    public static final String MODID = "rightclicker";
    public static final String VERSION = "1.2.2";
    public static KeyBinding[] keyBindings = new KeyBinding[9];

    public static int beforeWaitTime;
    public static int afterWaitTime;

    @Mod.Instance
    public static RightClicker instance;

    public Configuration config;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new rcCommand());
        MinecraftForge.EVENT_BUS.register(this);

        //register keybindings
        for (int i = 0 ; i < 9 ; i++) {
        keyBindings[i] = new KeyBinding("Right Click Hotbar Slot " + (i + 1), Keyboard.KEY_NONE, "RightClicker");
    }
        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        config = new Configuration(new File(event.getModConfigurationDirectory(), "rightclicker.cfg"));
        saveConfig();
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        for (int i = 0 ; i < 9 ; i++) {
            if (keyBindings[i].isPressed()) {
                rcCommand.num = i + 1;
                rcCommand.slot = player.inventory.currentItem;//get current slot number
                rcThread rcThread = new rcThread();
                rcThread.start();
            }
        }
    }

    private void saveConfig() {

        beforeWaitTime = config.getInt("beforeWaitTime", Configuration.CATEGORY_GENERAL, 100, 0, Integer.MAX_VALUE, "Time to Wait before Right Click (ms)");
        afterWaitTime = config.getInt("afterWaitTime", Configuration.CATEGORY_GENERAL, 100, 0, Integer.MAX_VALUE, "Time to Wait after Right Click (ms)");

        config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(MODID)) {
            saveConfig();
        }
    }
}
