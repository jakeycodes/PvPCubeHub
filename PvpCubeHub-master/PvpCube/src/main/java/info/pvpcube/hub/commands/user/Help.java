package info.pvpcube.hub.commands.user;

import info.pvpcube.hub.Hub;
import info.pvpcube.framework.messaging.Messaging;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Bukkit.broadcastMessage("Test");
        ArrayList<String> help = Hub.getInstance().getHelp();
        for (String h : help) {
            System.out.print(h);
            Messaging.sendCustomRawMessage(sender, h);
        }
        return true;
    }
}
