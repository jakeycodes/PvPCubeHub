/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.entities;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;

public class HubVillagers {

    private VillagerNPC craftyNPC = null;

    public void spawnCraftyNPC(Location loc, String name) {
        craftyNPC = (VillagerNPC) EntityTypes.spawnEntity(new VillagerNPC(((CraftWorld) loc.getWorld()).getHandle()), loc, name);
    }

    public VillagerNPC getCraftyNPC() {
        return craftyNPC;
    }

}
