package me.Nekoyoubi.Blessings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Blessings extends JavaPlugin {

    private final Listener playerListener = new BlessingsPlayerListener();
    private final Listener blockListener = new BlessingsBlockListener();
    
    public static ArrayList<God> gods = new ArrayList<God>();
    public static Material shrineMaterial = Material.DIAMOND_BLOCK;


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
        //pm.registerEvent(Type.PLAYER_LOGIN, playerListener, Priority.Low, this);
		System.out.println(this + " is now enabled.");
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	Player player = (sender instanceof Player) ? (Player)sender : null;
    	if(cmd.getName().equalsIgnoreCase("blessings") && player != null) {
    		if (args.length == 0) {
	    		Nekoyoubi.sendMessage(player, "Usage Information:");
 	 	    	Nekoyoubi.sendMessage(player, "</faith - See your current level.", true);
 	 	    	Nekoyoubi.sendMessage(player, "</blessings gods - Lists the gods present in your world.", true);
  	 	    	Nekoyoubi.sendMessage(player, "</blessings [godname] - Gives information about a god.", true);
    		}
    		else if (args[0].equalsIgnoreCase("gods")) {
	    		Nekoyoubi.sendMessage(player, "Gods currently present in %WORLD%:");
	    		for (God god : gods)
	 	    		Nekoyoubi.sendMessage(player, "<"+god.colorName()+" - "+god.description, true);
    		}
    		else if (args[0].equalsIgnoreCase("reload")) {
    			loadConfiguration();
    			Nekoyoubi.sendMessage(player, "The Blessings configuration was reloaded.");
    		}
    		else {
	    		for (God god : gods) {
	    			if (god.displayName.replace(" ", "").equalsIgnoreCase(args[0])) {
	    				Nekoyoubi.sendMessage(player, "Information on "+god.colorName()+": ");
	    				Nekoyoubi.sendMessage(player, "<"+god.colorName()+" - "+god.description, true);
	    				String bases = "Shrine bases: ";
	    				for (Material base : god.shrineBases)
	    					bases += Nekoyoubi.titleCase(base.toString().replace("_"," ")).replace("Tnt", "TNT")+", ";
	    				bases = bases.substring(0, bases.length()-2);
	    				Nekoyoubi.sendMessage(player, "<"+bases, true);
	    			}
	    		}
    		}
    		//Nekoyoubi.sendMessage(player, "You currently have "+(player.getLevel()>20?"20":player.getLevel()) +" level"+(player.getLevel()==1?"":"s")+" of faith to offer.");
    		return true;
    	} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
    	else if(cmd.getName().equalsIgnoreCase("faith") && player != null) {
    		Nekoyoubi.sendMessage(player, "You currently have "+(player.getLevel()>20?"20":player.getLevel()) +" level"+(player.getLevel()==1?"":"s")+" of faith to offer.");
    		return true;
    	} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
    	return false; 
    }

    public void loadConfiguration() {
    	getConfig().options().copyDefaults(true);
     	saveConfig();
     	reloadConfig();
     	gods = new ArrayList<God>();
        if (getConfig().get("shrine") != null) {
            shrineMaterial = Material.getMaterial(getConfig().get("shrine").toString());
        }
    	List<String> godList = Nekoyoubi.castList(String.class, getConfig().getList("gods.list"));
    	for (String name : godList) {
			loadGod(name);
		}
    }

	private void loadGod(String name) {
		FileConfiguration config = getConfig();
		God god = new God();
		god.displayName = config.getString("gods."+name+".name");
		god.colorCode = config.getString("gods."+name+".color");
		god.description = config.getString("gods."+name+".description");
		List<String> bases = Nekoyoubi.castList(String.class, config.getList("gods."+name+".bases"));
		god.shrineBases = new ArrayList<Material>();
		for (String id : bases) god.shrineBases.add(Material.getMaterial(id));
		god.angersBelow = config.getInt("gods."+name+".angersBelow");
		god.ignoresBelow = config.getInt("gods."+name+".ignoresBelow");
		god.msgPlease = config.getString("gods."+name+".messages.please");
		god.msgAnger = config.getString("gods."+name+".messages.anger");
		god.msgIgnore = config.getString("gods."+name+".messages.ignore");
		god.favors = new ArrayList<Favor>();
		List<String> favors = Nekoyoubi.castList(String.class, config.getList("gods."+name+".blessings"));
		for (String favorCommand : favors) {
			//System.out.println(this + " - " + favorCommand);
			Pattern fp = Pattern.compile("^(\\d+)@(\\d+)\\s(\\w+)\\s([\\w;]+?)\\s(.*)$");
			Matcher fm = fp.matcher(favorCommand);
			if (fm.find()) {
				Favor favor = new Favor(
					Integer.parseInt(fm.group(1)), // The intended level.
					Integer.parseInt(fm.group(2)), // The chances (in 1000).
					fm.group(3), // The blessing.
					fm.group(4), // The targets.
					fm.group(5)); // The data.
				god.favors.add(favor);
			}
		}
		god.curses = new ArrayList<Favor>();
		List<String> curses = Nekoyoubi.castList(String.class, config.getList("gods."+name+".curses"));
		for (String curseCommand : curses) {
			Pattern cp = Pattern.compile("^(\\d+)\\s(\\w+)\\s([\\w;]+)\\s(.*)$");
			Matcher cm = cp.matcher(curseCommand);
			if (cm.find()) {
				Favor curse = new Favor(
					0, // Curses are level independent.
					Integer.parseInt(cm.group(1)), // The chances (in 1000).
					cm.group(2), // The curse.
					cm.group(3), // The targets.
					cm.group(4)); // The data.
				god.curses.add(curse);
			}
		}
		gods.add(god);
	}
}
