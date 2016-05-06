package info.pvpcube.hub.events.player;

import java.util.ArrayList;
import java.util.List;

import info.pvpcube.hub.player.HubPlayer;
import info.pvpcube.hub.scoreboard.Scoreboards;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.entities.EntityFakePlayer;
import info.pvpcube.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Hub.getInstance().getHubItems().hubInventory(e.getPlayer());
        e.getPlayer().teleport(Hub.getInstance().getSpawn());
        e.getPlayer().setLevel(MallPlayer.getPlayer(e.getPlayer().getName()).getLevel());
        e.getPlayer().setExp(MallPlayer.getPlayer(e.getPlayer().getName()).getXp() / 100);
        if (MallPlayer.getPlayer(e.getPlayer().getName()).getPermissions().getPower() < PermissionSet.JNR_STAFF.getPower()) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboards.update(p);
        }
//        List<String> list = (List<String>) Hub.getInstance().getConfig().getList("npcs");
//        if (list == null) {
//            list = new ArrayList<>();
//            Hub.getInstance().getConfig().set("npcs", list);
//        }
//        for (String serialisedNPC : list) {
//            String splits[] = serialisedNPC.split(";");
//            Location l = new Location(Bukkit.getWorld(splits[5]), Double.valueOf(splits[0]), Double.valueOf(splits[1]), Double.valueOf(splits[2]), Float.parseFloat(splits[3]), Float.parseFloat(splits[4]));
//            EntityFakePlayer fakePlayer = EntityFakePlayer.deserializeNPC(serialisedNPC);
//            fakePlayer.create(e.getPlayer(), l);
//        }
    }
}
