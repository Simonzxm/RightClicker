package io.github.simonzxm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class rcCommand extends CommandBase {

    @Override
    public String getCommandName(){
        return "rightclicker";
    }

    @Override
    public List<String> getCommandAliases(){
        return Collections.singletonList("rc");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return
                "Usage: /rightclicker <slot>\n" +
                        "Aliases: /rc <slot>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public static int slot;
    public static int num;

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayer player = (EntityPlayer) sender;

        if (args.length == 1) {
            num = Integer.parseInt(args[0]);

            if (num >= 1 & num <= 9) {
                slot = player.inventory.currentItem;//get current slot number
                rcThread rcThread = new rcThread();
                rcThread.start();

            } else {
                player.addChatMessage(new ChatComponentText("Invalid Slot!"));
            }
        } else {
            player.addChatMessage(new ChatComponentText(
                    "Right Clicker by Simonzxm\n" +
                    "-2 ways to use-\n" +
                    "1. Type /rightclicker <slot> or /rc <slot>\n" +
                    "2. Configure keybindings in Controls menu"));
        }
    }
}

class rcThread extends Thread{

    @Override
    public void run() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        //change slot to Slot
        for (int i = 0; i < Math.abs(rcCommand.num - rcCommand.slot - 1); i++) {
            player.inventory.changeCurrentItem(rcCommand.slot - rcCommand.num + 1);
        }

        try {
            sleep(ConfigLoader.beforeWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //simulate right click
        final Minecraft minecraft = Minecraft.getMinecraft();
        KeyBinding.onTick(minecraft.gameSettings.keyBindUseItem.getKeyCode());

        try {
            sleep(ConfigLoader.afterWaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //change slot to primary
        for (int j = 0; j < Math.abs(rcCommand.slot - rcCommand.num + 1); j++) {
            player.inventory.changeCurrentItem(rcCommand.num - rcCommand.slot - 1);
        }

    }
}
