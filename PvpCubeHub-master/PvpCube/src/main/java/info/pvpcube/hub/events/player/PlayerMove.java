package info.pvpcube.hub.events.player;

import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.messaging.Messaging;
import info.pvpcube.hub.Hub;
import info.pvpcube.hub.player.ParkourPlayer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

/**
 * Created by alex_ on 03/04/2016.
 */
public class PlayerMove implements Listener {
    
    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getBlock().getType() == Material.GOLD_PLATE) {
            if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.DIAMOND_BLOCK) {
                e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.5).setY(1));
            } else if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.IRON_BLOCK) {
                if (ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()) != null
                        && !ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).hasStarted()) {
                    if (e.getPlayer().getGameMode() == GameMode.CREATIVE
                            && !(MallPlayer.getPlayer(e.getPlayer().getDisplayName()).getPermissions().getPower() == PermissionSet.ALL.getPower())) {
                        e.getPlayer().setGameMode(GameMode.SURVIVAL);
                    }
                    ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).start();
                    Messaging.sendMessage(e.getPlayer(), "hub.parkour.started");
                }
            } else if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_BLOCK) {
                if (ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()) != null) {
                    if (!ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).hasStarted()
                            || (e.getPlayer().getGameMode() == GameMode.CREATIVE
                            && !(MallPlayer.getPlayer(e.getPlayer().getDisplayName()).getPermissions().getPower() == PermissionSet.ALL.getPower()))) {
                        Messaging.sendMessage(e.getPlayer(), "hub.parkour.error");
                    } else {
                        ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).finish();
                        
                        Messaging.sendMessage(e.getPlayer(), "hub.parkour.finished", ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).getTimeString());
                        
                        if (!Hub.getInstance().getLeaderboard().isEmpty()) {
                            boolean updated = false;
                            ArrayList<ParkourPlayer> leaders = Hub.getInstance().getLeaderboard();
                            for (int i = 0; i < leaders.size(); i++) {
                                if (ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).getTimeTaken() < leaders.get(i).getTimeTaken()) {
                                    leaders.add(i, ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()));
                                    updated = true;
                                    
                                    Messaging.broadcastMessage("hub.parkour.new", e.getPlayer().getDisplayName(), (i + 1), ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()).getTimeString());
                                    break;
                                }
                                
                            }
                            
                            if (leaders.size() <= 5) {
                                leaders.add(ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()));
                            }
                            
                            if (updated) {
                                Hub.getInstance().setLeaderboard(leaders);
                                Thread t1 = new Thread(new Runnable() {
                                    public void run() {
                                        Hub.getInstance().updateLeaderboard();
                                    }
                                });
                                t1.start();
                            }
                        }
                        
                        Hub.getInstance().getHubItems().hubInventory(e.getPlayer());
                        e.getPlayer().teleport(Hub.getInstance().getSpawn());
                        
                        ParkourPlayer.removePlayer(e.getPlayer().getDisplayName());
                        
                    }
                }
            }
        } else if (e.getPlayer().getLocation().getY() < 65
                && ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()) != null) {
            e.getPlayer().teleport(Hub.getInstance().getParkourSpawn());
        }
    }
}
