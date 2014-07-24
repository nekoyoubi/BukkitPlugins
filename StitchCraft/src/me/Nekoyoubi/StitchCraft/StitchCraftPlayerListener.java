package me.Nekoyoubi.StitchCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Torch;

/**
 * Author: Lance May (@Nekoyoubi)
 * Date: 7/23/14
 * ------------------------------
 */

public class StitchCraftPlayerListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!StitchCraft.autoTorchEnabled) return;
        if (!StitchCraft.playersAutoTorching.containsKey(event.getPlayer().getName()))
            StitchCraft.playersAutoTorching.put(event.getPlayer().getName(), -1);
        if (StitchCraft.playersAutoTorching.get(event.getPlayer().getName()) > -1) {
            Block standingOn = event.getTo().getBlock();
            Block below = standingOn.getRelative(BlockFace.DOWN);
            Material belowType = standingOn.getRelative(BlockFace.DOWN).getType();
            if (standingOn.getLightLevel() <= StitchCraft.playersAutoTorching.get(event.getPlayer().getName()) &&
                    (
                        standingOn.getType() == Material.AIR &&
                        belowType != Material.AIR &&
                        !below.isLiquid() &&
                        belowType != Material.LEAVES &&
                        belowType != Material.LEAVES_2 &&
                        belowType != Material.SANDSTONE_STAIRS &&
                        belowType != Material.ACACIA_STAIRS &&
                        belowType != Material.BIRCH_WOOD_STAIRS &&
                        belowType != Material.BRICK_STAIRS &&
                        belowType != Material.COBBLESTONE_STAIRS &&
                        belowType != Material.DARK_OAK_STAIRS &&
                        belowType != Material.JUNGLE_WOOD_STAIRS &&
                        belowType != Material.NETHER_BRICK_STAIRS &&
                        belowType != Material.SMOOTH_STAIRS &&
                        belowType != Material.SPRUCE_WOOD_STAIRS &&
                        belowType != Material.WOOD_STAIRS &&
                        belowType != Material.ICE &&
                        belowType != Material.STEP &&
                        belowType != Material.SOIL &&
                        belowType != Material.GLOWSTONE &&
                        belowType != Material.TNT &&
                        belowType != Material.PISTON_BASE &&
                        belowType != Material.PISTON_EXTENSION &&
                        belowType != Material.PISTON_STICKY_BASE
                    ) &&
                    event.getPlayer().getInventory().contains(Material.TORCH)
                    ) {
                standingOn.setType(Material.TORCH);
                // HACK not good, but the only way we have for now
                Torch torch = new Torch(Material.TORCH);
                torch.setFacingDirection(BlockFace.UP);
                standingOn.setData(torch.getData());
                ItemStack invTorch = new ItemStack(Material.TORCH, 1);
                event.getPlayer().getInventory().removeItem(invTorch);
            }
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (!StitchCraft.playersAutoTorching.containsKey(event.getPlayer().getName()))
            StitchCraft.playersAutoTorching.put(event.getPlayer().getName(), -1);
    }
}
