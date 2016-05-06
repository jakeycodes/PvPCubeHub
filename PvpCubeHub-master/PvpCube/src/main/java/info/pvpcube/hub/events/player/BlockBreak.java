package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BlockBreak implements Listener {

    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        if (MallPlayer.getPlayer(e.getPlayer().getName()).getPermissions().getPower() < PermissionSet.JNR_STAFF.getPower()) {
            e.setCancelled(true);
        }
    }
}
