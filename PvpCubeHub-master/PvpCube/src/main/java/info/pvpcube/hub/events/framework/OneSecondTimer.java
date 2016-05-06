package info.pvpcube.hub.events.framework;

import java.util.Arrays;
import java.util.List;
import info.pvpcube.framework.customevents.OneSecondEvent;
import info.pvpcube.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class OneSecondTimer implements Listener {
    
    public static int time = 0;
    private int data = 0;
    private List<Integer> colorList = Arrays.asList(1, 2, 3, 4, 5, 6, 9, 10, 11, 14);
    
    @EventHandler
    public void onSecond(OneSecondEvent e) {
        time++;
        if (time % 5 == 0) {
            Block b = Bukkit.getWorlds().get(0).getBlockAt(-17, 59, -44);
            if (b != null && b.getType() == Material.BEACON) {
                
                Location location = b.getLocation().add(0, 1, 0);
                data++;
                if (data == colorList.size()) {
                    data = 0;
                    return;
                }
                int ourColor = colorList.get(data);
                location.getBlock().setType(Material.STAINED_GLASS);
                location.getBlock().setData((byte) ourColor);
            }
        }
    }
}
