package info.pvpcube.hub.inventorys;

import info.pvpcube.hub.player.HubPlayer;
import info.pvpcube.framework.api.MallInventory;
import info.pvpcube.framework.util.ItemUtil;
import info.pvpcube.hub.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by alex_ on 08/04/2016.
 */
public class TrailsInventory extends MallInventory {

    @Override
    public String getName() {
        return "Trails";
    }

    @Override
    public void open(Player p) {
        Inventory inv = Bukkit.createInventory(p, 9, ChatColor.BLUE + "Trails");
        inv.setItem(1, ItemUtil.createItem(Material.TNT, "&4Explosion", "&aMake farts behind you"));
        inv.setItem(2, ItemUtil.createItem(Material.FIREWORK, "&4Firework", "&aFirework farts"));
        inv.setItem(3, ItemUtil.createItem(Material.FLINT_AND_STEEL, "&4Fire", "&aFlaming farts"));
        inv.setItem(4, ItemUtil.createItem(Material.SKULL_ITEM, "&4Love", "&aShow the world your feelings"));
        inv.setItem(5, ItemUtil.createItem(Material.LAVA_BUCKET, "&4Lava", "&aOuch that burns"));
        inv.setItem(6, ItemUtil.createItem(Material.WOOL, "&4Smoke", "&aI dunno"));
        inv.setItem(7, ItemUtil.createItem(Material.WATER_BUCKET, "&4Water", "&aDrowning in puns?"));
        p.openInventory(inv);
    }

    @Override
    public void click(Player p, int slot) {
        switch (slot) {
            case 2:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.EXPLOSION_NORMAL, 5);
            case 3:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.FIREWORKS_SPARK, 5);
            case 4:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.FLAME, 5);
            case 5:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.HEART, 5);
            case 6:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.LAVA, 5);
            case 7:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.SMOKE_NORMAL, 5);
            case 8:
                HubPlayer.addTrail(p.getDisplayName(), ParticleEffect.WATER_SPLASH, 5);
            default:
                return;
        }

    }
}
