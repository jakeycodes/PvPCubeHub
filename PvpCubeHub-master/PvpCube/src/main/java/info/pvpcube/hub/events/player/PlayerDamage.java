package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import net.minecraft.server.v1_9_R1.EntityArmorStand;
import net.minecraft.server.v1_9_R1.EntityItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;


public class PlayerDamage implements Listener {

    @EventHandler
    public void playerDamage(PlayerItemDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void entityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof EntityArmorStand || e.getEntity() instanceof EntityItemFrame) {
            e.setCancelled(false);
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void blockDamage(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof EntityArmorStand || e.getEntity() instanceof EntityItemFrame) {
            e.setCancelled(false);
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void moreDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof EntityArmorStand || e.getEntity() instanceof EntityItemFrame) {
            e.setCancelled(false);
            return;
        }
        e.setCancelled(true);
    }
}
