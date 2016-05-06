/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.pvpcube.hub.entities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.v1_9_R1.Entity;
import net.minecraft.server.v1_9_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;


public enum EntityTypes {
    //NAME("Entity name", Entity ID, yourcustomclass.class);
    HUB_VILLAGER("Hub Villager", 120, VillagerNPC.class); //You can add as many as you want.
    private String name;
    private int id;
    private Class<? extends Entity> custom;

    private EntityTypes(String name, int id, Class<? extends Entity> custom) {
        this.name = name;
        this.id = id;
        this.custom = custom;
        addToMaps(custom, name, id);
    }

    public static Entity spawnEntity(Entity entity, Location loc, String name) {
        if (entity instanceof VillagerNPC) {
            VillagerNPC villagerNPC = (VillagerNPC) entity;
            return villagerNPC.spawn(loc, name);
        }
        return null;
    }

    private static void addToMaps(Class clazz, String name, int id) {
        //getPrivateField is the method from above.
        //Remove the lines with // in front of them if you want to override default entities (You'd have to remove the default entity from the map first though).
        ((Map) getPrivateField("c", net.minecraft.server.v1_9_R1.EntityTypes.class, null)).put(name, clazz);
        ((Map) getPrivateField("d", net.minecraft.server.v1_9_R1.EntityTypes.class, null)).put(clazz, name);
        //((Map)getPrivateField("e", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(Integer.valueOf(id), clazz);
        ((Map) getPrivateField("f", net.minecraft.server.v1_9_R1.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
        //((Map)getPrivateField("g", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(name, Integer.valueOf(id));
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
