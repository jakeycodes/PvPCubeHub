package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.hub.Hub;
import info.pvpcube.hub.inventorys.HubInventorys;
import info.pvpcube.hub.player.HubPlayer;
import info.pvpcube.hub.scoreboard.Scoreboards;
import info.pvpcube.hub.util.ParticleEffect;
import info.pvpcube.hub.util.SkullUtil;
import java.util.Arrays;
import net.minecraft.server.v1_9_R1.PacketPlayOutEntityDestroy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 *
 * @author Rushmead
 */
public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType() == Material.DARK_OAK_STAIRS) {
                    Arrow mineCart = (Arrow) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation(), EntityType.ARROW);
                    mineCart.teleport(e.getClickedBlock().getLocation());
                    e.getPlayer().teleport(mineCart);
                    mineCart.setPassenger(e.getPlayer());
                    mineCart.setVelocity(new Vector(0, 0, 0));
                    mineCart.setInvulnerable(true);
                    ((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(mineCart.getEntityId()));
                }
                if (e.getClickedBlock().getType() == Material.CAULDRON) {
                    if (e.getItem() != null) {
                        if (e.getItem().getItemMeta().hasDisplayName() && ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).contains("Empty cup")) {
                            e.getPlayer().getInventory().remove(e.getItem());
                            MallPlayer mp = MallPlayer.getPlayer(e.getPlayer().getName());
                            mp.addMoney(1);
                            Scoreboards.update(e.getPlayer());
                            Messaging.sendCustomMessage(e.getPlayer(), "&6You were rewarded &c&l1&r&6 coin for putting you're rubbish in the bin!");
                        }
                    }
                }
            }
            if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Navigation")) {
                    HubInventorys.getMinigamesInventory().open(e.getPlayer());
                }
            }
            if (e.getItem() != null
                    && e.getItem().getType() == Material.COBBLESTONE) {
                HubInventorys.getTrailsInventory().open(e.getPlayer());

            }
            if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Quit")) {
                    e.getPlayer().performCommand("spawn");
                }

            }
            if (e.getItem() != null
                    && e.getItem().getType() == Material.COBBLESTONE_STAIRS) {
                if (e.getPlayer().isSneaking()) {
                    ParticleEffect.FLAME.display(0, 0, -0.5F, 1, 100, e.getPlayer().getLocation(), 100);
                }
            }
            if (e.getItem() != null
                    && e.getItem().getType() == Material.DIAMOND) {
                HubPlayer.addTrail(e.getPlayer().getDisplayName(), ParticleEffect.HEART, 5);
            }
            if (e.getPlayer().isSneaking()) {
                if (e.getItem() != null) {
                    if (e.getItem().getType() == Material.SKULL_ITEM) {
                        if (e.getItem().getItemMeta().hasDisplayName()) {
                            if (ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).contains("Cup")) {
                                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_GENERIC_DRINK, 1F, 0);
                                ParticleEffect.WATER_SPLASH.display(0, 1, 0, 10, 50, e.getPlayer().getLocation(), 200D);
                                MallPlayer.getPlayer(e.getPlayer().getName()).setXp((int) (MallPlayer.getPlayer(e.getPlayer().getName()).getXp() + 10));

                                Hub.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(Hub.getInstance(), new Runnable() {

                                    @Override
                                    public void run() {
                                        Hub.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(Hub.getInstance(), new Runnable() {
                                            @Override
                                            public void run() {
                                                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 0);
                                            }
                                        }, 20 * 2);
                                        final ItemStack cup = SkullUtil.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4MDk4ODlkYTM2YWRmNTYxODU4MWJiZjdiNjZkMmQ4ODM5ZTJlYjcyNTRjMzMzMmU0ZjNhMjMwZmEifX19", "&3Empty cup", Arrays.asList(Messaging.colorizeMessage("&6Put me in the bin for a reward!")));
                                        e.getPlayer().getInventory().setItemInMainHand(cup);
                                    }
                                }, 20 * 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
