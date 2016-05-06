package info.pvpcube.hub.shops.cafe.events;

/**
 *
 * @author Rushmead
 */
import info.pvpcube.hub.inventorys.HubInventorys;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick_Cafe implements Listener {

    @EventHandler
    public void inventoryClickCafe(InventoryClickEvent e) {
        System.out.print(e.getClickedInventory().getTitle());
        if (ChatColor.stripColor(e.getClickedInventory().getTitle()).equalsIgnoreCase(HubInventorys.getKevinInventory().getName())) {
            HubInventorys.getKevinInventory().click((Player) e.getWhoClicked(), e.getSlot());
            e.setCancelled(true);
        }

    }

}
