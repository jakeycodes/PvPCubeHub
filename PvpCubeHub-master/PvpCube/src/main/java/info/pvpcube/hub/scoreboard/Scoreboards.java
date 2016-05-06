package info.pvpcube.hub.scoreboard;

import java.util.HashMap;
import info.pvpcube.framework.api.MallPlayer;
import info.pvpcube.framework.api.PermissionSet;
import info.pvpcube.framework.api.Rank;
import info.pvpcube.framework.database.Servers;
import info.pvpcube.framework.messaging.Messaging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 *
 * @author Rushmead
 */
public class Scoreboards {
    
    public static HashMap<String, org.bukkit.scoreboard.Scoreboard> boards = new HashMap<String, org.bukkit.scoreboard.Scoreboard>();
    
    public static void update(Player p) {
        
        if (!(boards.containsKey(p.getName()))) {
            org.bukkit.scoreboard.Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
            boards.put(p.getName(), board);
        }
        
        Scoreboard board = boards.get(p.getName());
        Objective o = board.getObjective(DisplaySlot.SIDEBAR);
        if (o == null) {
            o = board.registerNewObjective(p.getName(), "dummy");
        }
        
        MallPlayer mp = MallPlayer.getPlayer(p.getDisplayName());
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        o.setDisplayName(ChatColor.BLUE + "   " + Messaging.colorizeMessage("&3PvPCube&6Network") + "   ");

        //Player Spawn Scoreboard
        for (String entry : board.getEntries()) {
            board.resetScores(entry);
        }
        o.getScore(mp.getRank().getColor() + p.getDisplayName()).setScore(15);
        o.getScore(Messaging.colorizeMessage("&3" + "&L" + "Rank")).setScore(14);
        o.getScore(Messaging.colorizeMessage("&5" + "&L") + mp.getRank().getColor() + mp.getRank().getName()).setScore(13);
        o.getScore(Messaging.colorizeMessage("&3" + "&L") + "Coins").setScore(12);
        o.getScore(Messaging.colorizeMessage("&5" + "&L") + String.valueOf(mp.getCurrency())).setScore(11);
        o.getScore(Messaging.colorizeMessage("&3" + "&L") + "Server").setScore(10);
        o.getScore(Messaging.colorizeMessage("&5" + "&L") + Servers.getServerData().getName()).setScore(9);
        for (Rank r : Rank.values()) {
            if (board.getTeam(r.getName()) == null) {
                if (r.getName().equalsIgnoreCase("Loyalty Customer")) {
                    if (board.getTeam("L Customer") == null) {
                        Team t = board.registerNewTeam("L Customer");
                        t.setPrefix(r.getColor() + "[Loyalty] ");
                    }
                } else {
                    Team t = board.registerNewTeam(r.getName());
                    t.setPrefix(r.getColor() + "[" + r.getName() + "] ");
                }
            }
            
        }
        for (Player ps : Bukkit.getOnlinePlayers()) {
            if (MallPlayer.getPlayer(ps.getName()).getRank().getPermissions().getPower() > PermissionSet.SPECIAL.getPower()) {
                if (board.getTeam(MallPlayer.getPlayer(ps.getName()).getRank().getName()) != null) {
                    Team t = board.getTeam(MallPlayer.getPlayer(ps.getName()).getRank().getName());
                    if (!board.getTeam(MallPlayer.getPlayer(ps.getName()).getRank().getName()).hasEntry(ps.getName())) {
                        board.getTeam(MallPlayer.getPlayer(ps.getName()).getRank().getName()).addEntry(ps.getName());
                    }
                } else if (board.getTeam("L Customer") != null) {
                    Team t = board.getTeam("L Customer");
                    System.out.print((t.getPrefix() + ps.getName()).length());
                    if (!board.getTeam("L Customer").hasEntry(ps.getName())) {
                        System.out.print((t.getPrefix() + ps.getName()).length());
                        board.getTeam("L Customer").addEntry(ps.getName());
                    }
                } else {
                    Team t = board.registerNewTeam(MallPlayer.getPlayer(ps.getName()).getRank().getName());
                    t.setPrefix(MallPlayer.getPlayer(ps.getName()).getRank().getColor() + "[" + MallPlayer.getPlayer(ps.getName()).getRank().getName() + "]");
                }
                
            }
        }
        boards.put(p.getName(), board);
        p.setScoreboard(board);
    }
    
    public static void delete(Player p) {
        boards.remove(p.getName());
        
    }
}
