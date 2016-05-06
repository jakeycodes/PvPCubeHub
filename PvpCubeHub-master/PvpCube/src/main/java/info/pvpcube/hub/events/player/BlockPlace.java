package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPlace implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        if (MallPlayer.getPlayer(e.getPlayer().getName()).getPermissions().getPower() < PermissionSet.JNR_STAFF.getPower()) {
            e.setCancelled(true);
        }
    }
}
