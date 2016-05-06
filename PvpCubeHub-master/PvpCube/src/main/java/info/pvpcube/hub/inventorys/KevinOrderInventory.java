/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.inventorys;

import info.pvpcube.framework.api.MallInventory;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.framework.util.ItemUtil;
import info.pvpcube.hub.Hub;
import info.pvpcube.hub.scoreboard.Scoreboards;
import info.pvpcube.hub.util.SkullUtil;
import java.util.Arrays;
import net.minecraft.server.v1_9_R1.EnumItemSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class KevinOrderInventory extends MallInventory {

    @Override
    public String getName() {
        return "Crafty Coffees - Order";
    }

    @Override
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, ChatColor.BLUE + "Crafty Coffees - Order");

        ItemStack comingSoon = ItemUtil.createItem(Material.IRON_FENCE, "&4Coming Soon", "&aBoop. Coming soon!");
        ItemStack coffee = SkullUtil.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFjZWRkNTdlYzFiM2FjMTQ1NDQ2MjZjYzZiNGJjZGJkYzM1MTNmMzlhOTFjYzM3YTA0OGE5ZmQyNDRkNGQifX19", "&3Cup of Coffee", Arrays.asList(Messaging.colorizeMessage("&62 Coins")));

        inv.setItem(3, comingSoon);
        inv.setItem(4, coffee);
        inv.setItem(5, comingSoon);
        player.openInventory(inv);
    }

    @Override
    public void click(final Player player, int i) {
        if (!ChatColor.stripColor(Hub.getInstance().getHubVillagers().getCraftyNPC().getCustomName()).equalsIgnoreCase("&3&lKevin - &7Busy")) {
            if (i == 4) {
                if (player.getOpenInventory().getItem(i).getItemMeta().getDisplayName().contains("Coffee")) {
                    MallPlayer mp = MallPlayer.getPlayer(player.getName());
                    if (mp.purchase(2)) {
                        Scoreboards.update(player);
                        Messaging.sendCustomMessage(player, "&6Ordered Coffee for 2 Coins");
                        Messaging.sendCustomRawMessage(player, "&3&lKevin: &6One Coffee, coming up!");
                        player.closeInventory();
                        Location loc = new Location(Hub.getInstance().getServer().getWorlds().get(0), 63, 61, -15);
                        loc.setPitch(0);
                        loc.setYaw(90);

                        Hub.getInstance().getHubVillagers().getCraftyNPC().moveToBlock(loc, 0.7F);
                        Hub.getInstance().getHubVillagers().getCraftyNPC().setCustomName(Messaging.colorizeMessage("&3&lKevin - &7Busy"));
                        Hub.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(Hub.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                final ItemStack coffee = SkullUtil.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTFjZWRkNTdlYzFiM2FjMTQ1NDQ2MjZjYzZiNGJjZGJkYzM1MTNmMzlhOTFjYzM3YTA0OGE5ZmQyNDRkNGQifX19", "&3Cup of Coffee", Arrays.asList(Messaging.colorizeMessage("&6Shift right click to drink!")));
                                Hub.getInstance().getHubVillagers().getCraftyNPC().setEquipment(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(coffee));
                                Hub.getInstance().getHubVillagers().getCraftyNPC().moveToBlock(new Location(Hub.getInstance().getServer().getWorlds().get(0), 61, 61, -19), 0.7F);

                                Hub.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(Hub.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {

                                        player.getInventory().addItem(coffee);
                                        Hub.getInstance().getHubVillagers().getCraftyNPC().setCustomName(Messaging.colorizeMessage("&3&lKevin - &cClick to order"));
                                        Messaging.sendCustomRawMessage(player, "&3&lKevin: &6There we go, one coffee. &lNEXT!");
                                    }

                                }, 20 * 2);

                            }
                        }, 20 * 10);
                    } else {
                        Messaging.sendCustomRawMessage(player, "&3&lKevin: &6Not enough coins!");
                    }
                }
            }
        } else {
            Messaging.sendCustomRawMessage(player, "&3&lKevin: &6I'm busy!");
        }
    }

}
