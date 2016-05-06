/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.commands.admin;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.framework.npcs.NPCManager;
import info.pvpcube.hub.Hub;
import info.pvpcube.hub.entities.EntityTypes;
import info.pvpcube.hub.entities.VillagerNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;

public class CreateNPC implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length == 0 || args.length < 1) {
            Messaging.sendMessage(sender, "global.command.incorrectusage", "/npc <type> - Types: security");
            return false;
        }
        final Player p = (Player) sender;
        if (MallPlayer.getPlayer(p.getName()).getPermissions().getPower() != PermissionSet.ALL.getPower()) {
            Messaging.sendMessage(p, "global.command.notallowed");
            return false;
        }
        if (args[0].equalsIgnoreCase("security")) {
            List<String> list = (List<String>) Hub.getInstance().getConfig().getList("npcs");
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(NPCManager.getMallSecurity().serializeNPC(p.getLocation()));
            Hub.getInstance().getConfig().set("npcs", list);
            NPCManager.getMallSecurity().create(p, p.getLocation());
            return true;
        } else if (args[0].equalsIgnoreCase("kevin")) {

            
            return true;
        } else {

            Messaging.sendCustomMessage(p, "&4No such type {0}", args[0]);
            return false;
        }
    }

}
