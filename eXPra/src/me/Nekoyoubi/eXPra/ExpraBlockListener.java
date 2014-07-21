package me.Nekoyoubi.eXPra;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ExpraBlockListener implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		Block block = event.getBlockPlaced();
		if (Expra.defaultLightingAmount > 0 && Nekoyoubi.hasPermission(player, "expra.award.explore")) {
			byte newLight = block.getLightLevel();
			byte oldLight = event.getBlockReplacedState().getLightLevel();
			if (newLight > oldLight) {
				float diff = ((float)oldLight / (float)newLight);
				if (diff < .5f) {
					boolean award = true;
					if (Expra.playerLit.containsKey(player.getName()+":"+player.getWorld().getName())) {
						Location lastLit = Expra.playerLit.get(player.getName()+":"+player.getWorld().getName());
						if (lastLit.distance(block.getLocation()) < 5) award = false;
					}
					Expra.playerLit.put(player.getName()+":"+player.getWorld().getName(), block.getLocation());
					if (award) processAward(player, Expra.defaultLightingRatio, Expra.defaultLightingAmount);
				}
			}
		}
		if (!Nekoyoubi.hasPermission(player, "expra.award.place")) return;
		
		Integer amount = Expra.defaultBlockPlaceAmount;
		Integer ratio = Expra.defaultBlockPlaceRatio;
		String[] keys = new String[] {
			String.valueOf(block.getTypeId())+":"+String.valueOf(block.getData()),
			String.valueOf(block.getTypeId())
		};
		if (Expra.overridePlace.containsKey(keys[0])) {
			amount = Integer.parseInt(Expra.overridePlace.get(keys[0]).split("@")[0]);
			ratio = Integer.parseInt(Expra.overridePlace.get(keys[0]).split("@")[1]);
		} else if (Expra.overridePlace.containsKey(keys[1])) {
			amount = Integer.parseInt(Expra.overridePlace.get(keys[1]).split("@")[0]);
			ratio = Integer.parseInt(Expra.overridePlace.get(keys[1]).split("@")[1]);		
		}
		processAward(player, ratio, amount);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (!Nekoyoubi.hasPermission(player, "expra.award.break")) return;
		if (Expra.disabledPlayers.contains(player.getName())) return;
		World world = player.getWorld();
		if (Expra.disabledWorlds.contains(world.getName())) return;
		Block block = event.getBlock();
		Integer amount = Expra.defaultBlockBreakAmount;
		Integer ratio = Expra.defaultBlockBreakRatio;
		String[] keys = new String[] {
			String.valueOf(block.getTypeId())+":"+String.valueOf(block.getData()),
			String.valueOf(block.getTypeId())
		};
		if (Expra.overrideBreak.containsKey(keys[0])) {
			amount = Integer.parseInt(Expra.overrideBreak.get(keys[0]).split("@")[0]);
			ratio = Integer.parseInt(Expra.overrideBreak.get(keys[0]).split("@")[1]);
		} else if (Expra.overrideBreak.containsKey(keys[1])) {
			amount = Integer.parseInt(Expra.overrideBreak.get(keys[1]).split("@")[0]);
			ratio = Integer.parseInt(Expra.overrideBreak.get(keys[1]).split("@")[1]);		
		}
		processAward(player, ratio, amount);
	}
	
	public void processAward(Player player, Integer ratio, Integer amount) {
		if (ratio == 0) return;
		else if (Expra.rando.nextInt(ratio)==0) {
			ExperienceOrb xp = player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(amount);
		}
	}
}
