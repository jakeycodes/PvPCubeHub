package info.pvpcube.hub.events.player;

import info.pvpcube.hub.player.HubPlayer;
import info.pvpcube.hub.player.ParkourPlayer;
import info.pvpcube.hub.scoreboard.Scoreboards;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Scoreboards.delete(e.getPlayer());
        ParkourPlayer.removePlayer(e.getPlayer().getDisplayName());
        HubPlayer.removePlayer(e.getPlayer().getDisplayName());
    }
}
