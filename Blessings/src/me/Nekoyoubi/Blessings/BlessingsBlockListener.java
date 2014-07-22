package me.Nekoyoubi.Blessings;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlessingsBlockListener implements Listener {
	
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
    	Block shrine = event.getBlock();
        if (shrine.getType() == Material.GOLD_BLOCK) {
            Player player = event.getPlayer();
            Block under = shrine.getRelative(0, -1, 0);
            for (God god : Blessings.gods) {
				if (god.shrineBases.contains(under.getType())) {
					god.newShrine(player, shrine);
				}
			}
        }
    }
}
