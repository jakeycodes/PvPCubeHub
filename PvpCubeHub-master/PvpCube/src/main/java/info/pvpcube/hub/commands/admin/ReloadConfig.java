/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.commands.admin;

import info.pvpcube.hub.Hub;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.messaging.Messaging;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (MallPlayer.getPlayer(p.getName()).getPermissions().getPower() != PermissionSet.ALL.getPower()) {
                Messaging.sendMessage(p, "global.command.notallowed");
                return false;
            }
        }

        Hub.getInstance().reloadConfig();
        return true;
    }

}
