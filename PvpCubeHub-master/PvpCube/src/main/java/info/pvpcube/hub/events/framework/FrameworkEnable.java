package info.pvpcube.hub.events.framework;

import java.util.List;

import info.pvpcube.hub.Hub;
import info.pvpcube.hub.commands.admin.CreateHologram;
import info.pvpcube.hub.commands.admin.CreateNPC;
import info.pvpcube.hub.commands.admin.ReloadConfig;
import info.pvpcube.hub.commands.user.Parkour;
import info.pvpcube.hub.commands.user.Spawn;
import info.pvpcube.framework.api.MallCommand;
import info.pvpcube.hub.commands.user.Help;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import info.pvpcube.framework.customevents.FrameworkEnableEvent;
import info.pvpcube.framework.util.Hologram;
import info.pvpcube.framework.util.Holograms;

public class FrameworkEnable implements Listener {

    @EventHandler
    public void onFrameworkEnable(FrameworkEnableEvent e) {

        System.out.print("Registering Commands");
        MallCommand.registerCommand("hub", "spawn", new Spawn());
        MallCommand.registerCommand("hub", "hologram", new CreateHologram());

        MallCommand.registerCommand("hub", "parkour", new Parkour());
        MallCommand.registerCommand("hub", "npc", new CreateNPC());
        MallCommand.registerCommand("hub", "reloadconfig", new ReloadConfig());

        MallCommand.registerCommand("hub", "help", new Help());
        List<String> list = (List<String>) Hub.getInstance().getConfig().getList("holograms");
        for (String serializedHologram : list) {
            Hologram h = Holograms.getDeserializedHologram(serializedHologram);
            Holograms.createHologram(h.getText(), h.getLocation());
        }
    }
}
