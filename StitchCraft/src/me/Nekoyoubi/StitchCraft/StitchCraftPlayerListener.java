package me.Nekoyoubi.StitchCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Author: Lance May (@Nekoyoubi)
 * Date: 7/23/14
 * ------------------------------
 */

public class StitchCraftPlayerListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block standingOn = event.getTo().getBlock();
        if (standingOn.getLightLevel() <= 0 &&
                standingOn.getRelative(BlockFace.UP).getType() == Material.AIR &&
                event.getPlayer().getInventory().contains(Material.TORCH)) {
            standingOn.getRelative(BlockFace.UP).setType(Material.TORCH);
        }

    }

}
