package io.github.simonzxm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = RightClicker.MODID , version = RightClicker.VERSION)
public class RightClicker {
    public static final String MODID = "rightclicker";
    public static final String VERSION = "1.1.0";
    public static KeyBinding[] keyBindings = new KeyBinding[9];

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new rcCommand());
        MinecraftForge.EVENT_BUS.register(this);

        //register keybindings
        keyBindings[0] = new KeyBinding("Right Click Hotbar Slot 1", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[1] = new KeyBinding("Right Click Hotbar Slot 2", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[2] = new KeyBinding("Right Click Hotbar Slot 3", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[3] = new KeyBinding("Right Click Hotbar Slot 4", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[4] = new KeyBinding("Right Click Hotbar Slot 5", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[5] = new KeyBinding("Right Click Hotbar Slot 6", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[6] = new KeyBinding("Right Click Hotbar Slot 7", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[7] = new KeyBinding("Right Click Hotbar Slot 8", Keyboard.KEY_NONE, "RightClicker");
        keyBindings[8] = new KeyBinding("Right Click Hotbar Slot 9", Keyboard.KEY_NONE, "RightClicker");
        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
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
}
