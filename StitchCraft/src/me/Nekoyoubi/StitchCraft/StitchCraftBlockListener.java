package me.Nekoyoubi.StitchCraft;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


import org.bukkit.Material;

public class StitchCraftBlockListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block placed = event.getBlock();
        if (StitchCraft.sandToGlassEnabled) {
            if (placed.getType() == Material.SAND) {
                if (Nekoyoubi.blockIsTouchingMaterial(placed, Material.LAVA) ||
                    Nekoyoubi.blockIsTouchingMaterial(placed, Material.STATIONARY_LAVA)) {
                    placed.setType(Material.GLASS);
                }
            }
        }
    }
}
