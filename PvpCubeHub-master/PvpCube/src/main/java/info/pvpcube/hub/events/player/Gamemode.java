package info.pvpcube.hub.events.player;

import info.pvpcube.hub.player.ParkourPlayer;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class Gamemode implements Listener {

    @EventHandler
    public void gamemode(PlayerGameModeChangeEvent e) {
        if (MallPlayer.getPlayer(e.getPlayer().getDisplayName()).getPermissions().getPower() == PermissionSet.ALL.getPower()) {
            return;
        }
        if (ParkourPlayer.getPlayer(e.getPlayer().getDisplayName()) != null
                && e.getNewGameMode() == GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
}
