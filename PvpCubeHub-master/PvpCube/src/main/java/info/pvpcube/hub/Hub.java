package info.pvpcube.hub;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.pvpcube.hub.events.framework.OneSecondTimer;
import info.pvpcube.hub.events.framework.OneSecondTrail;
import info.pvpcube.hub.events.player.*;
import info.pvpcube.hub.player.ParkourPlayer;
import info.pvpcube.framework.database.logs.L;
import info.pvpcube.framework.database.logs.LL;
import info.pvpcube.framework.database.logs.Trigger;
import info.pvpcube.hub.entities.HubVillagers;
import info.pvpcube.hub.events.framework.FrameworkEnable;
import info.pvpcube.hub.events.other.WeatherChange;
import info.pvpcube.hub.items.HubItems;
import info.pvpcube.hub.shops.cafe.events.InventoryClick_Cafe;
import info.pvpcube.hub.shops.cafe.events.PlayerInteract_Cafe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Rushmead
 */
//This means that its a bukkit plugin 
public class Hub extends JavaPlugin {

    //This is a Instance. The way of getting this class from external classes
    private static Hub instance;

    //Simple method
    public static Hub getInstance() {
        return instance;
    }
    public Logger logger;

    private ArrayList<ParkourPlayer> leaderboard;
    private HubVillagers hubVillagers;
    private HubItems hubItems;

    public ArrayList<ParkourPlayer> getLeaderboard() {
        if (!leaderboard.isEmpty()) {
            return leaderboard;
        }
        return null;
    }

    public void setLeaderboard(ArrayList<ParkourPlayer> board) {
        this.leaderboard = board;
    }

    private ArrayList<Location> heads;

    public ArrayList<Location> getHeads() {
        return this.heads;
    }

    private ArrayList<Location> signs;

    public ArrayList<Location> getSigns() {
        return this.signs;
    }

    private Location spawn;
    private Location parkourSpawn;

    public Location getSpawn() {
        return spawn;
    }

    public Location getParkourSpawn() {
        return parkourSpawn;
    }

    private ArrayList<String> help;

    public ArrayList<String> getHelp() {
        return help;
    }

    //Any code called here means that it will happen upon the server starting
    @Override
    public void onEnable() {
        instance = this;
        logger = Bukkit.getLogger();
        spawn = new Location(Bukkit.getWorld("world"), -16.5, 61.0, -40.5, 0.0f, -20.0f);
        parkourSpawn = new Location(Bukkit.getWorld("world"), -16.5, 66.0, 15.5, 0.0f, 0.0f);
        hubVillagers = new HubVillagers();
        heads = new ArrayList<Location>();
        signs = new ArrayList<Location>();
        hubItems = new HubItems();
        heads.add(new Location(Bukkit.getWorld("world"), -16.5, 74.0, 17.5));
        heads.add(new Location(Bukkit.getWorld("world"), -17.5, 73.0, 17.5));
        heads.add(new Location(Bukkit.getWorld("world"), -15.5, 73.0, 17.5));
        heads.add(new Location(Bukkit.getWorld("world"), -18.5, 72.0, 17.5));
        heads.add(new Location(Bukkit.getWorld("world"), -14.5, 72.0, 17.5));

        signs.add(new Location(Bukkit.getWorld("world"), -16.5, 73.0, 16.5));
        signs.add(new Location(Bukkit.getWorld("world"), -17.5, 72.0, 16.5));
        signs.add(new Location(Bukkit.getWorld("world"), -15.5, 72.0, 16.5));
        signs.add(new Location(Bukkit.getWorld("world"), -18.5, 71.0, 16.5));
        signs.add(new Location(Bukkit.getWorld("world"), -14.5, 71.0, 16.5));

        //This is how you register events and other things
        /*
         E.G pm.registerEvents(new PlayerChat(), this);
         */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new FrameworkEnable(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new OneSecondTimer(), this);
        pm.registerEvents(new ItemDrop(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new PlayerDamage(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new WeatherChange(), this);
        pm.registerEvents(new Gamemode(), this);
        pm.registerEvents(new OneSecondTrail(), this);
        pm.registerEvents(new PlayerInteract_Cafe(), this);
        pm.registerEvents(new InventoryClick_Cafe(), this);
        pm.registerEvents(new PlayerLogin(), this);
        hubVillagers.spawnCraftyNPC(new Location(getServer().getWorlds().get(0), 61, 61, -19), "&3&lKevin - &cClick to order");
        createConfig();
        importLeaderboard();
        importHelp();
        getServer().getWorlds().get(0).setTime(0);
        getServer().getWorlds().get(0).setGameRuleValue("doDaylightCycle", "false");

    }

    public HubItems getHubItems() {
        return hubItems;
    }

    //Any code called here means that it will happen upon the server stopping
    @Override
    public void onDisable() {
        saveConfig();
    }

    public HubVillagers getHubVillagers() {
        return hubVillagers;
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                logger.info("Config.yml not found, creating it now!");
                saveDefaultConfig();
            } else {
                logger.info("Config.yml found, loading");
            }
        } catch (Exception e) {
            L.ogError(LL.ERROR, Trigger.LOAD, "Error creating config", "Error importing config");
        }
    }

    private void importHelp() {
        File file = new File(getDataFolder(), "help.txt");
        try {
            if (!file.exists()) {
                logger.info("help.txt not found, crap!");
            } else {
                logger.info("help.txt found, loading");
                help = new ArrayList<String>();
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    help.add(line);
                }
            }

        } catch (Exception e) {
            L.ogError(LL.ERROR, Trigger.LOAD, "Error importing help", "Error importing help");
        }
    }

    private void importLeaderboard() {
        try {
            File file = new File(getDataFolder(), "leaderboard.json");
            if (!file.exists()) {
                logger.info("leaderboard.json not found, creating it now!");
                file.createNewFile();
                leaderboard = new ArrayList<ParkourPlayer>();
            } else {
                logger.info("leaderboard.json found, loading");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                Gson gson = new GsonBuilder().create();
                leaderboard = new ArrayList<ParkourPlayer>(Arrays.asList(gson.fromJson(reader, ParkourPlayer[].class)));
                reader.close();
            }
        } catch (Exception e) {
            L.ogError(LL.ERROR, Trigger.LOAD, "Error importing leaderboard", "Error importing leaderboard");
        }
    }

    public void updateLeaderboard() {
        while (Hub.getInstance().getLeaderboard().size() > 5) {
            this.leaderboard.remove(5);
        }
        for (int i = 0; i < leaderboard.size(); i++) {
            ParkourPlayer p = leaderboard.get(i);
            Skull skull = (Skull) Bukkit.getWorld("world").getBlockAt(heads.get(i)).getState();
            skull.setOwner(p.getUserName());
            skull.update();

            Sign sign = (Sign) Bukkit.getWorld("world").getBlockAt(signs.get(i)).getState();
            sign.setLine(0, ChatColor.RED + "" + (i + 1));
            sign.setLine(1, ChatColor.GREEN + "===============");
            sign.setLine(2, ChatColor.BOLD + p.getUserName());
            sign.setLine(3, ChatColor.LIGHT_PURPLE + p.getTimeString());

            org.bukkit.material.Sign matSign = new org.bukkit.material.Sign(Material.WALL_SIGN);
            matSign.setFacingDirection(BlockFace.NORTH);
            sign.setData(matSign);
            sign.update();
        }
        Gson obj = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(getDataFolder(), "leaderboard.json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(obj.toJson(leaderboard));
            writer.close();
        } catch (Exception e) {
            L.ogError(LL.ERROR, Trigger.LOAD, "Error saving leaderboard", "Error saving leaderboard");
        }
    }
}
