package info.pvpcube.hub.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParkourPlayer {

    private static HashMap<String, ParkourPlayer> players = new HashMap<>();

    public static HashMap<String, ParkourPlayer> getPlayers() {
        return players;
    }

    public static ParkourPlayer getPlayer(String name) {
        if (!players.containsKey(name)) {
            return null;
        }
        return players.get(name);
    }

    public static ParkourPlayer createPlayer(Player p) {
        if (!players.containsKey(p.getDisplayName())) {
            ParkourPlayer player = new ParkourPlayer(p.getUniqueId(), p.getDisplayName());
            players.put(p.getDisplayName(), player);
            return player;
        } else {
            return getPlayer(p.getDisplayName());
        }

    }

    public static void removePlayer(String name) {
        if (players.containsKey(name)) {
            players.remove(name);

        }
    }

    private UUID uuid;
    private String userName;

    private long startTime;
    private long timeTaken;

    public ParkourPlayer(UUID uuid, String userName) {
        this.uuid = uuid;
        this.userName = userName;
        this.startTime = 0;
    }

    public boolean hasStarted() {
        return startTime > 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUserName() {
        return this.userName;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void finish() {
        this.timeTaken = System.currentTimeMillis() - this.startTime;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public String getTimeString() {
        String time = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(this.timeTaken),
                TimeUnit.MILLISECONDS.toSeconds(this.timeTaken) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.timeTaken)),
                this.timeTaken -
                        TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(this.timeTaken)) -
                        TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(this.timeTaken) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.timeTaken))));
        return time.substring(0, time.length() - 1);
    }

}
