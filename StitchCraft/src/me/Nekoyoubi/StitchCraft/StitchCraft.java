package me.Nekoyoubi.StitchCraft;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Author: Lance May (@Nekoyoubi)
 * Date: 7/23/14
 * ------------------------------
 * Base class for the catch-all plugin for Stitch Gaming's Minecraft server.
 */

public class StitchCraft extends JavaPlugin {

    private final Listener playerListener = new StitchCraftPlayerListener();

    public static boolean testing = false;

    @Override
    public void onDisable() {
        System.out.println(this + " is now disabled.");
    }

    @Override
    public void onEnable() {
        //loadConfiguration();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        //pm.registerEvents(blockListener, this);
        //pm.registerEvent(Type.PLAYER_LOGIN, playerListener, Priority.Low, this);
        System.out.println(this + " is now enabled.");
    }


}
