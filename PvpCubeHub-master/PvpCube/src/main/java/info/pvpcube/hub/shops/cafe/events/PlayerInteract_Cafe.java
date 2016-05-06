package info.pvpcube.hub.shops.cafe.events;

/**
 *
 * @author Rushmead
 */
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.hub.inventorys.HubInventorys;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteract_Cafe implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            Entity entity = e.getRightClicked();
            if (ChatColor.stripColor(entity.getCustomName()).contains("Kevin")) {
                e.setCancelled(true);
                if (ChatColor.stripColor(entity.getCustomName()).contains("Busy")) {
                    Messaging.sendCustomRawMessage(e.getPlayer(), "&3&lKevin: &6I'm busy!");
                    return;
                }
                HubInventorys.getKevinInventory().open(e.getPlayer());
            }
        }

    }

}
