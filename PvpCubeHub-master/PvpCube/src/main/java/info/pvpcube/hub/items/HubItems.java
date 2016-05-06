/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.items;

import info.pvpcube.framework.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Rushmead
 */
public class HubItems {

    private ItemStack minigame = ItemUtil.createItem(Material.RECORD_6, "&r&aNavigation", "&r&6Click me for navigation!");
    private ItemStack trails = ItemUtil.createItem(Material.REDSTONE, "&r&2Trails", "&r&6Click me to open the trails");

    public ItemStack getTrails() {
        return trails;
    }

    public ItemStack getMinigame() {
        return minigame;
    }

    public void hubInventory(Player p) {
        p.getInventory().clear();
        p.getInventory().addItem(minigame);
        p.getInventory().addItem(trails);
    }
}
