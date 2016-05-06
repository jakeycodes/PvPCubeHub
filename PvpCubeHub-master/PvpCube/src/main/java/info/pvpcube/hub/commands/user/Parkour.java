package info.pvpcube.hub.commands.user;

import info.pvpcube.hub.Hub;
import info.pvpcube.hub.player.ParkourPlayer;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.framework.util.ItemUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Parkour implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (ParkourPlayer.getPlayer(((Player) sender).getDisplayName()) != null) {
            //TODO tell player already in game
            return false;
        }
        if (MallPlayer.getPlayer(((Player)sender).getDisplayName()).getPermissions().getPower() != PermissionSet.ALL.getPower()) {
            ((Player) sender).setGameMode(GameMode.SURVIVAL);
        }
        ((Player) sender).getInventory().clear();
        ((Player) sender).getInventory().addItem(ItemUtil.createItem(Material.BARRIER, "&r&aQuit", "Click me to quit the parkour!"));
        ParkourPlayer.createPlayer((Player) sender);
        ((Player) sender).teleport(Hub.getInstance().getParkourSpawn());
        Messaging.sendMessage(sender, "hub.parkour.teleported");
        return false;
    }

}
