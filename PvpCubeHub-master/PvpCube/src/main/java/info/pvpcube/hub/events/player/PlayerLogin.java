package info.pvpcube.hub.events.player;

import info.pvpcube.hub.player.HubPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;


public class PlayerLogin implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        HubPlayer.createPlayer(e.getPlayer());
    }

}
