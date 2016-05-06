package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.cubePlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.hub.inventorys.HubInventorys;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by alex_ on 03/04/2016.
 */
public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryEvent(InventoryClickEvent e) {
        if (ChatColor.stripColor(e.getClickedInventory().getTitle()).equalsIgnoreCase(HubInventorys.getMinigamesInventory().getName())) {
            HubInventorys.getMinigamesInventory().click((Player) e.getWhoClicked(), e.getSlot());
            e.setCancelled(true);
        }

        if (ChatColor.stripColor(e.getClickedInventory().getTitle()).equalsIgnoreCase(HubInventorys.getTrailsInventory().getName())) {
            HubInventorys.getTrailsInventory().click((Player) e.getWhoClicked(), e.getSlot());
            e.setCancelled(true);
        }
        if (MallPlayer.getPlayer(e.getWhoClicked().getName()).getPermissions().getPower() < PermissionSet.JNR_STAFF.getPower()) {
            e.setCancelled(true);
        }
    }
}
