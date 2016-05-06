package info.pvpcube.hub.inventorys;

import info.pvpcube.framework.api.MallInventory;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.framework.util.ItemUtil;
import info.pvpcube.hub.Hub;
import info.pvpcube.hub.util.SkullUtil;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MinigameInventory extends MallInventory {

    @Override
    public String getName() {
        return "Minigames";
    }

    @Override
    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, ChatColor.BLUE + "Navigation!");

        ItemStack comingSoon = ItemUtil.createItem(Material.IRON_FENCE, "&4Coming Soon", "&6Shop coming soon!");
        ItemStack spawn = ItemUtil.createItem(Material.WEB, "&aSpawn", "&6Goto spawn!");
        ItemStack parkour = ItemUtil.createItem(Material.FEATHER, "&aParkour", "&6Goto parkour");
        ItemStack coffee = SkullUtil.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFjZWRkNTdlYzFiM2FjMTQ1NDQ2MjZjYzZiNGJjZGJkYzM1MTNmMzlhOTFjYzM3YTA0OGE5ZmQyNDRkNGQifX19", "&aCraftyCoffee", Arrays.asList(Messaging.colorizeMessage("&6Goto Crafty Coffee!")));
        ItemStack buildapet = ItemUtil.createItem(Material.FEATHER, "&aBuild'A'Pet", "&6Goto build a pet!");

        //Left Shops
        inv.setItem(10, comingSoon);
        inv.setItem(11, coffee);
        inv.setItem(28, comingSoon);
        inv.setItem(29, buildapet);
        //Bottom Shops
        inv.setItem(39, comingSoon);
        inv.setItem(48, comingSoon);
        inv.setItem(41, comingSoon);
        inv.setItem(50, comingSoon);
        //Right Shops
        inv.setItem(15, comingSoon);
        inv.setItem(16, comingSoon);
        inv.setItem(33, comingSoon);
        inv.setItem(34, comingSoon);
        //Top
        inv.setItem(4, parkour);
        inv.setItem(22, spawn);
        p.openInventory(inv);
    }

    @Override
    public void click(Player p, int slot) {
        if (slot == 4) {
            p.performCommand("parkour");
        }
        if (slot == 22) {
            p.teleport(Hub.getInstance().getSpawn());
        }
    }

}
