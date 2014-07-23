package me.Nekoyoubi.Blessings;


import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class God {
	public String colorCode;
	public String displayName;
	public String description;
	public List<Material> shrineBases;
	public List<Favor> favors;
	public List<Favor> curses;
	protected String msgPlease;
	protected String msgAnger;
	protected String msgIgnore;
	public Integer ignoresBelow;
	public Integer angersBelow;
	public String colorName() { return colorCode + displayName + "&f"; }

    public void offer(Player player, Block shrine) {
		int level = player.getLevel();
		if (level > ignoresBelow) {
			Random rando = new Random();
			boolean blessed = false;
			do {
				Favor favor = favors.get(rando.nextInt(favors.size()));
				int test = rando.nextInt(1000)+1;
				double modlevel = (Math.abs(level-favor.level)*0.05)+1;
				if (((double)test*modlevel) < favor.chance) {
					favor.process(player, shrine, this);
					blessed = true;
				}				
			} while (!blessed);

			// Whoa, buddy.
			if (level>100) {
				player.setLevel(0);
				player.setExp(0);
				player.setTotalExperience(0);
			}
			
			player.setTotalExperience(0);
			player.setExp(0);
            player.setLevel(0);

            /* Quit being a sissy and go back to costing too much. ;)
            int newLevel = (level > 20 ? 19 : level-1);
			player.setLevel(newLevel);

			Nekoyoubi.sendMessage(player, "You are left with &6"+player.getLevel()+
					(player.getLevel()==1?"&f level ":"&f levels ")+
					"of faith.");
            */
			if (level < angersBelow) {
				Nekoyoubi.sendMessage(player, untoken(msgAnger));
				boolean cursed = false;
				for (Favor curse : this.curses) {
					if (!cursed && rando.nextInt(1000)+1 < curse.chance) {
						curse.process(player, shrine, this);
						cursed = true;
					}
				}
			}
		} else {
			Nekoyoubi.sendMessage(player, untoken(msgIgnore));
		}
	}
	
	private String untoken(String message) {
		return message
				.replace("%GOD%", colorName())
				.replace("%GODNAME%", displayName);
	}
	
	public void newShrine(Player player, Block shrine) {
		Nekoyoubi.sendMessage(player, "You have placed a new "+colorCode+"Shrine of "+displayName+"&f.", false);
		Nekoyoubi.sendMessage(player, "Right-click the shrine to pay homage to "+colorName(), true);
	}

}