package info.pvpcube.hub.events.framework;

import info.pvpcube.hub.player.HubPlayer;
import info.pvpcube.framework.customevents.OneSecondEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneSecondTrail implements Listener {

    @EventHandler
    public void oneSecondEvent(OneSecondEvent e) {
        HashMap<String, HubPlayer> map = HubPlayer.getPlayers();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            HubPlayer p = (HubPlayer) pair.getValue();

            //ParticleEffect.FLAME.display(0, 0, -0.5F, 1, 100, e.getPlayer().getLocation(), 100);

            if (p.getTime() != 0) {
                p.getEffect().display(0.5f, 0.5f, 0.5f, 1, 3, Bukkit.getPlayer(p.getUsername()).getLocation(), 100);

                HubPlayer.getHubPlayer(p.getUsername()).reduceTime();

                if (p.getTime() == 0) {
                    HubPlayer.removePlayer(p.getPlayer().getDisplayName());
                }
            }

        }
    }
}
