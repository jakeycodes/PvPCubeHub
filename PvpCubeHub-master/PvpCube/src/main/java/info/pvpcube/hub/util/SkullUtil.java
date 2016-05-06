
package info.pvpcube.hub.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import info.pvpcube.framework.messaging.Messaging;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author Rushmead
 */
public class SkullUtil {

    public static ItemStack getSkull(String textureID, String name, List<String> lore) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setDisplayName(Messaging.colorizeMessage(name));
        headMeta.setLore(lore);
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", textureID));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
}
