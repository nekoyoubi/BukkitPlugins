package me.Nekoyoubi.StitchCraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 * Author: Lance May (@Nekoyoubi)
 * Date: 7/23/14
 * ------------------------------
 * Base class for the catch-all plugin for Stitch Gaming's Minecraft server.
 */

public class StitchCraft extends JavaPlugin {

    public static boolean autoTorchEnabled = true;
    public static boolean autoTorchOnByDefault = false;
    public static int autoTorchLightLevel = 7;

    public static boolean sandToGlassEnabled = true;

    private final Listener playerListener = new StitchCraftPlayerListener();
    private final Listener blockListener = new StitchCraftBlockListener();

    public static Map<String, Integer> playersAutoTorching = new HashMap<String, Integer>();

    public static boolean testing = false;

    @Override
    public void onDisable() {
        System.out.println(this + " is now disabled.");
    }

    @Override
    public void onEnable() {
        loadConfiguration();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(blockListener, this);
        for (Player player : getServer().getOnlinePlayers()) {
            if (!StitchCraft.playersAutoTorching.containsKey(player.getName()))
                StitchCraft.playersAutoTorching.put(player.getName(),
                        autoTorchOnByDefault ? autoTorchLightLevel : -1);
        }
        //pm.registerEvents(blockListener, this);
        //pm.registerEvent(Type.PLAYER_LOGIN, playerListener, Priority.Low, this);
        System.out.println(this + " is now enabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = (sender instanceof Player) ? (Player)sender : null;
        String playerName = "Server";
        if (player != null) playerName = player.getName();
        if (cmd.getName().equalsIgnoreCase("stitchcraft") && player != null) {
            //if (args.length == 0) {
                Nekoyoubi.sendMessage(player, "StitchCraft is currently running with the following mods:");
                if (autoTorchEnabled) Nekoyoubi.sendMessage(player, "- Automatic torch placement - /autotorch", true);
                if (sandToGlassEnabled) Nekoyoubi.sendMessage(player, "- Sand touching lava turns to glass", true);
                return true;
            //} else {
            //    // Feed command specifics (default usage doc)
            //}
        } else if (cmd.getName().equalsIgnoreCase("autotorch") && player != null) {
            if (args.length == 0) {
                if (playersAutoTorching.get(playerName) > -1) {
                    playersAutoTorching.put(playerName, -1);
                } else {
                    playersAutoTorching.put(playerName, autoTorchLightLevel);
                }
                Nekoyoubi.sendMessage(player, "Automatic torch placement is now " +
                        (playersAutoTorching.get(playerName) > -1
                                ? ChatColor.GREEN+"on"+ChatColor.WHITE+" ("+playersAutoTorching.get(playerName)+")"
                                : ChatColor.RED+"off"+ChatColor.WHITE)
                        + ".");
                return true;
            } else {
                playersAutoTorching.put(playerName, parseInt(args[0]));
                Nekoyoubi.sendMessage(player, "Automatic torch placement is now " +
                        (playersAutoTorching.get(player.getName()) > -1
                                ? ChatColor.GREEN + "on" + ChatColor.WHITE + " (" + playersAutoTorching.get(playerName) + ")"
                                : ChatColor.RED + "off" + ChatColor.WHITE)
                        + ".");
                return true;
            }
        }
        return false;
    }

    public void loadConfiguration() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        autoTorchEnabled = getConfig().getBoolean("autotorch.enabled", true);
        autoTorchOnByDefault = getConfig().getBoolean("autotorch.onbydefault", false);
        autoTorchLightLevel = getConfig().getInt("autotorch.lightlevel", 7);
        sandToGlassEnabled = getConfig().getBoolean("sandtoglass.enabled", true);

        /*
        gods = new ArrayList<God>();
        if (getConfig().get("shrine") != null) {
            shrineMaterial = Material.getMaterial(getConfig().get("shrine").toString());
        }
        List<String> godList = Nekoyoubi.castList(String.class, getConfig().getList("gods.list"));
        for (String name : godList) {
            loadGod(name);
        }
        */
    }

}
