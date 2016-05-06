

package info.pvpcube.hub.player;

import info.pvpcube.hub.util.ParticleEffect;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 *
 * @author Rushmead
 */
public class HubPlayer {

    private static HashMap<String, HubPlayer> players = new HashMap<>();

    public static HashMap<String, HubPlayer> getPlayers() {
        return players;
    }

    public static HubPlayer getHubPlayer(String name) {
        if (!players.containsKey(name)) {
            return null;
        }
        return players.get(name);
    }

    public static HubPlayer createPlayer(Player p) {
        if (!players.containsKey(p.getDisplayName())) {
            HubPlayer player = new HubPlayer(p);
            players.put(p.getDisplayName(), player);
            return player;
        } else {
            return getHubPlayer(p.getDisplayName());
        }

    }

    public static void removePlayer(String name) {
        if (players.containsKey(name)) {
            players.remove(name);

        }
    }

    public static void addTrail(String name, ParticleEffect effect, int time) {
        if (players.containsKey(name)
                && players.get(name).getTime() == 0) {
            players.get(name).setEffect(effect);
            players.get(name).setTime(time);
        }
    }

    public static void reduceTrailTime(String name) {
        if (players.containsKey(name)
                && players.get(name).getTime() != 0) {
            players.get(name).setTime(-1);
        }
    }

    private String username;
    private Player player;
    private ParticleEffect effect;
    private int time;

    public HubPlayer(Player player) {
        this.player = player;
        this.username = player.getDisplayName();
        this.effect = null;
        this.time = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ParticleEffect getEffect() {
        return this.effect;
    }

    public void setEffect(ParticleEffect effect) {
        this.effect = effect;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void reduceTime() {
        --time;
    }

    @Override
    public String toString() {
        return player.getDisplayName() + " " + effect.toString() + " " + time;
    }

}
