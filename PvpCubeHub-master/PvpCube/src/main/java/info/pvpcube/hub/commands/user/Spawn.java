package info.pvpcube.hub.commands.user;

import info.pvpcube.hub.Hub;
import info.pvpcube.hub.player.ParkourPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (ParkourPlayer.getPlayer(((Player) sender).getDisplayName()) != null) {
            ParkourPlayer.removePlayer(((Player) sender).getDisplayName());
        }

        Hub.getInstance().getHubItems().hubInventory((Player) sender);
        ((Player) sender).teleport(Hub.getInstance().getSpawn());
        return false;
    }
}
