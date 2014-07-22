package me.Nekoyoubi.Blessings;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class BlessingsPlayerListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Player player = event.getPlayer();
        Block shrine = event.getClickedBlock();
        if (shrine.getType() == Material.GOLD_BLOCK) {
        	event.setCancelled(true);
            //if (player.getLevel() <= 0 && player.getExperience() <= 0) return;
            Block under = shrine.getRelative(0, -1, 0);
            for (God god : Blessings.gods) {
				if (god.shrineBases.contains(under.getType())) {
					god.offer(player, shrine);
				}
			}
        }
        
        // ***** TESTING (REMEMBER TO DISABLE) ***** //
        else if (Blessings.testing && shrine.getType() == Material.BOOKSHELF) {
        	event.setCancelled(true);
            player.setExp(player.getExp() + 10);
            Nekoyoubi.sendMessage(player, "Cheating will get you everywhere. ;) - "+player.getLevel()+"@"+player.getExp()+"("+player.getTotalExperience()+")");
        }
        else if (Blessings.testing && shrine.getType() == Material.BEDROCK) {
        	event.setCancelled(true);
        	player.setLevel(0);
        	player.setExp(0);
        	player.setTotalExperience(0);
            player.getInventory().clear();
            player.updateInventory();
            Nekoyoubi.sendMessage(player, "Inventory and XP cleared.");
        }

	}
}
