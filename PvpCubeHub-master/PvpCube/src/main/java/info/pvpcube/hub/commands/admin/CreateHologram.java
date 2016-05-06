package info.pvpcube.hub.commands.admin;

import java.util.ArrayList;
import java.util.List;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.framework.util.Holograms;
import info.pvpcube.hub.Hub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Rushmead
 */
public class CreateHologram implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (args.length == 0 || args.length < 1) {
            Messaging.sendMessage(sender, "global.command.incorrectusage", "/hologram <text>");
            return false;
        }
        Player p = (Player) sender;
        if (MallPlayer.getPlayer(p.getName()).getPermissions().getPower() != PermissionSet.ALL.getPower()) {
            Messaging.sendMessage(p, "global.command.notallowed");
            return false;
        }
        String text = "";
        for (int i = 0; i < args.length; i++) {
            text += " " + args[i];
        }
        List<String> list = (List<String>) Hub.getInstance().getConfig().getList("holograms");
        if (list == null) {
            list = new ArrayList<>();
        }
        text = Messaging.colorizeMessage(text);
        String uuid = Holograms.createHologram(text, p.getLocation());
        list.add(Holograms.getSerializedHologram(Holograms.holograms.get(uuid)));
        Hub.getInstance().getConfig().set("holograms", list);
        return true;
    }

}
