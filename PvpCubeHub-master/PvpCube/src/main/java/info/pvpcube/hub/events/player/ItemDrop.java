package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        if (MallPlayer.getPlayer(e.getPlayer().getName()).getPermissions().getPower() != PermissionSet.ALL.getPower()) {
            e.setCancelled(true);
        }
    }
}
