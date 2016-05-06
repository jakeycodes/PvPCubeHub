package info.pvpcube.hub.entities;

import info.pvpcube.framework.messaging.Messaging;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import net.minecraft.server.v1_9_R1.EntityHuman;
import net.minecraft.server.v1_9_R1.EntityVillager;
import net.minecraft.server.v1_9_R1.PathEntity;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_9_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_9_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class VillagerNPC extends EntityVillager {

    private Location spawn;
    private boolean moving = false;
    private Location currentlyMovingTo;

    public VillagerNPC(World world) {
        super(world);
        clearGoals();
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3.0F));
    }

    private void clearGoals() {
        LinkedHashSet<?> goalB = (LinkedHashSet<?>) getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();
        LinkedHashSet<?> goalC = (LinkedHashSet<?>) getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();
        LinkedHashSet<?> targetB = (LinkedHashSet<?>) getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        LinkedHashSet<?> targetC = (LinkedHashSet<?>) getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();
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

    public VillagerNPC spawn(Location loc, String name) {
        spawn = loc;
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        Villager merchant = (Villager) this.getBukkitEntity();
        merchant.setAdult();
        merchant.setBreed(false);
        merchant.setCustomName(Messaging.colorizeMessage(name));
        merchant.setCustomNameVisible(true);
        merchant.setHealth(20D);
        merchant.setRemoveWhenFarAway(false);
        nmsWorld.addEntity(this, SpawnReason.CUSTOM);
        return this;
    }

    public void moveToBlock(Location l, float speed) {
        moving = true;
        currentlyMovingTo = l;
        setPositionRotation(locX, locY, locZ, l.getYaw(), l.getPitch());
        ;
        NBTTagCompound tag = new NBTTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        this.c(tag);
        tag.setInt("NoAI", 0);
        this.f(tag);
        Villager merchant = (Villager) this.getBukkitEntity();
        merchant.getLocation().setDirection(l.getDirection());
        PathEntity pathEntity = this.navigation.a(l.getX(), l.getY(), l.getZ());

        getNavigation().a(pathEntity, speed);
    }

    @Override
    public void g(double deltaX, double deltaY, double deltaZ) {
    }

}
