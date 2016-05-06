/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.events.entities;

import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.hub.entities.VillagerNPC;
import info.pvpcube.hub.inventorys.HubInventorys;
import net.minecraft.server.v1_9_R1.EntityVillager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class EntityInteract implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {

    }

}
