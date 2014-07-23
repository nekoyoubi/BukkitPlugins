package me.Nekoyoubi.Blessings;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static me.Nekoyoubi.Blessings.Nekoyoubi.*;

public class Favor {

	/**
	 * The action to perform (e.g. "give", "spawn", "heal", "ill", etc...).
	 */
	public String action;
	/**
	 * The data (if any) associated with the action (e.g. "14" for the RED in wool).
	 */
	public String data;
	/**
	 * The targets to be effected by the action (i.e. "player", "random", "world). Stack values with a semicolon (e.g. "player;random;random").
	 */
	public String targets;
	/**
	 * The chance (in 1000) that this favor will be granted.
	 */
	public Integer level;
	/**
	 * The desired level that this favor be granted.
	 */
	public Integer chance;
	/**
	 * Represents a unit of favor from a god. Consider it a gift or blessing.
	 * @param level The desired level that this favor be granted.
	 * @param chance The chance (in 1000) that the favor will be granted.
	 * @param action The action to perform (e.g. "give", "spawn", "heal", "ill", etc...)
	 * @param targets The targets to be effected by the action (i.e. "player", "random", "world). Stack values with a semicolon (e.g. "player;random;random").
	 * @param data The data (if any) associated with the action (e.g. "14" for the RED in wool).
	 */
	public Favor(Integer level, Integer chance, String action, String targets, String data) {
		this.level = level;
		this.chance = chance;
		this.action = action;
		this.targets = targets;
		this.data = data;
	}
	/**
	 * Processes a blessing's favor for a player (e.g. gives items, spawns mobs, etc...).
	 * @param player The player receiving the favor.
	 * @param shrine The shrine that the player just used.
	 */
	@SuppressWarnings("deprecation")
	public void process(Player player, Block shrine, God god) {
		// Determine targets and establish the list of players to effect.
		ArrayList<Player> effectTargets = getTargets(player);
		if (this.action.equalsIgnoreCase("give")) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			Pattern ip = Pattern.compile("^([A-Z_]+)(:(\\d+))?(([xr])((\\d+)-(\\d+)))?$");
			for (String i : this.data.split(";")) {
				Matcher im = ip.matcher(i);
				if (im.find()) {
					String id = im.group(1);
					Short data = im.group(3) != null
						? Short.parseShort(im.group(3))
						: 0;
					Integer min = im.group(7) != null
						? Integer.parseInt(im.group(7))
						: 1;
					Integer max = im.group(8) != null
						? Integer.parseInt(im.group(8))
						: 1;
					boolean isRando = im.group(5) != null && im.group(5).equals("r");
					Random rando = new Random();
					Integer quantity = getQuantity(player, rando, min, max, isRando);
                    ItemStack item = null;
                    switch (Material.getMaterial(id)) {

                        case IRON_SWORD:
                            item = getEnchSword(Material.IRON_SWORD);
                            break;
                        case IRON_SPADE:
                            item = getEnchSpade(Material.IRON_SPADE);
                            break;
                        case IRON_PICKAXE:
                            item = getEnchPick(Material.IRON_PICKAXE);
                            break;
                        case IRON_AXE:
                            item = getEnchAxe(Material.IRON_AXE);
                            break;

                        case WOOD_SWORD:
                            item = getEnchSword(Material.WOOD_SWORD);
                            break;
                        case WOOD_SPADE:
                            item = getEnchSpade(Material.WOOD_SPADE);
                            break;
                        case WOOD_PICKAXE:
                            item = getEnchPick(Material.WOOD_PICKAXE);
                            break;
                        case WOOD_AXE:
                            item = getEnchAxe(Material.WOOD_AXE);
                            break;

                        case STONE_SWORD:
                            item = getEnchSword(Material.STONE_SWORD);
                            break;
                        case STONE_SPADE:
                            item = getEnchSpade(Material.STONE_SPADE);
                            break;
                        case STONE_PICKAXE:
                            item = getEnchPick(Material.STONE_PICKAXE);
                            break;
                        case STONE_AXE:
                            item = getEnchAxe(Material.STONE_AXE);
                            break;

                        case DIAMOND_SWORD:
                            item = getEnchSword(Material.DIAMOND_SWORD);
                            break;
                        case DIAMOND_SPADE:
                            item = getEnchSpade(Material.DIAMOND_SPADE);
                            break;
                        case DIAMOND_PICKAXE:
                            item = getEnchPick(Material.DIAMOND_PICKAXE);
                            break;
                        case DIAMOND_AXE:
                            item = getEnchAxe(Material.DIAMOND_AXE);
                            break;

                        case GOLD_SWORD:
                            item = getEnchSword(Material.GOLD_SWORD);
                            break;
                        case GOLD_SPADE:
                            item = getEnchSpade(Material.GOLD_SPADE);
                            break;
                        case GOLD_PICKAXE:
                            item = getEnchPick(Material.GOLD_PICKAXE);
                            break;
                        case GOLD_AXE:
                            item = getEnchAxe(Material.GOLD_AXE);
                            break;

                        case WOOD_HOE:
                            item = getEnchHoe(Material.WOOD_HOE);
                            break;
                        case STONE_HOE:
                            item = getEnchHoe(Material.STONE_HOE);
                            break;
                        case IRON_HOE:
                            item = getEnchHoe(Material.IRON_HOE);
                            break;
                        case DIAMOND_HOE:
                            item = getEnchHoe(Material.DIAMOND_HOE);
                            break;
                        case GOLD_HOE:
                            item = getEnchHoe(Material.GOLD_HOE);
                            break;

                        case LEATHER_HELMET:
                            item = getEnchHelmet(Material.LEATHER_HELMET);
                            break;
                        case LEATHER_CHESTPLATE:
                            item = getEnchChest(Material.LEATHER_CHESTPLATE);
                            break;
                        case LEATHER_LEGGINGS:
                            item = getEnchLegs(Material.LEATHER_LEGGINGS);
                            break;
                        case LEATHER_BOOTS:
                            item = getEnchBoots(Material.LEATHER_BOOTS);
                            break;

                        case CHAINMAIL_HELMET:
                            item = getEnchHelmet(Material.CHAINMAIL_HELMET);
                            break;
                        case CHAINMAIL_CHESTPLATE:
                            item = getEnchChest(Material.CHAINMAIL_CHESTPLATE);
                            break;
                        case CHAINMAIL_LEGGINGS:
                            item = getEnchLegs(Material.CHAINMAIL_LEGGINGS);
                            break;
                        case CHAINMAIL_BOOTS:
                            item = getEnchBoots(Material.CHAINMAIL_BOOTS);
                            break;

                        case IRON_HELMET:
                            item = getEnchHelmet(Material.IRON_HELMET);
                            break;
                        case IRON_CHESTPLATE:
                            item = getEnchChest(Material.IRON_CHESTPLATE);
                            break;
                        case IRON_LEGGINGS:
                            item = getEnchLegs(Material.IRON_LEGGINGS);
                            break;
                        case IRON_BOOTS:
                            item = getEnchBoots(Material.IRON_BOOTS);
                            break;

                        case DIAMOND_HELMET:
                            item = getEnchHelmet(Material.DIAMOND_HELMET);
                            break;
                        case DIAMOND_CHESTPLATE:
                            item = getEnchChest(Material.DIAMOND_CHESTPLATE);
                            break;
                        case DIAMOND_LEGGINGS:
                            item = getEnchLegs(Material.DIAMOND_LEGGINGS);
                            break;
                        case DIAMOND_BOOTS:
                            item = getEnchBoots(Material.DIAMOND_BOOTS);
                            break;

                        case GOLD_HELMET:
                            item = getEnchHelmet(Material.GOLD_HELMET);
                            break;
                        case GOLD_CHESTPLATE:
                            item = getEnchChest(Material.GOLD_CHESTPLATE);
                            break;
                        case GOLD_LEGGINGS:
                            item = getEnchLegs(Material.GOLD_LEGGINGS);
                            break;
                        case GOLD_BOOTS:
                            item = getEnchBoots(Material.GOLD_BOOTS);
                            break;

                        case BOW:
                            item = getEnchBow(Material.BOW);
                            break;

                        case FISHING_ROD:
                            item = getEnchRod(Material.FISHING_ROD);
                            break;

                        case SHEARS:
                            item = getEnchShears(Material.SHEARS);
                            break;

                        case FLINT_AND_STEEL:
                            item = getEnchFlint(Material.FLINT_AND_STEEL);
                            break;

                        case CARROT_STICK:
                            item = getEnchCarrot(Material.CARROT_STICK);
                            break;

                    }
                    if (item != null)
                        items.add(item);
                    else if (quantity > 0)
                        items.add(new ItemStack(Material.getMaterial(id), quantity, data));
				}
			}
			for (Player target : effectTargets) {
				for (ItemStack itemStack : items) {
					if (target.getInventory().firstEmpty()<0)
						target.getWorld().dropItem(target.getLocation(), itemStack);
					else {
						target.getInventory().addItem(itemStack);
						target.updateInventory();
					}
					Nekoyoubi.sendMessage(target, god.colorName()+" has given you "+
							itemStack.getAmount()+" "+
							Nekoyoubi.titleCase(itemStack.getType().toString().replace("_"," ")).replace("Tnt", "TNT")+".");
				}
			}
		} else if (this.action.equalsIgnoreCase("spawn")) {
			Random rando = new Random();
			Pattern sp = Pattern.compile("^(\\w+)(\\s(\\d+)(-(\\d+)(r?))?)?$");
			Matcher sm = sp.matcher(this.data);
			if (sm.find()) {
				String mob = sm.group(1);
				Integer min = sm.group(3) != null
					? Integer.parseInt(sm.group(3))
					: 1;
				Integer max = sm.group(5) != null
					? Integer.parseInt(sm.group(5))
					: 1;
				boolean isRando = sm.group(6) != null && sm.group(6).equals("r");
				Integer quantity = getQuantity(player, rando, min, max, isRando);
				
				for (Player target : effectTargets) {
					if (mob.equalsIgnoreCase("tamedwolf")) {
						for (int i = 0; i < quantity; i++) {
							Wolf wolf = (Wolf)target.getWorld().spawnCreature(target.getLocation().add(rando.nextInt(8)-4, 0, rando.nextInt(8)-4), CreatureType.WOLF);
							wolf.setOwner(target);
							wolf.setTamed(true);
						}
						if (target == player)
							Nekoyoubi.sendMessage(target, god.colorName()+" has blessed you with companionship.");
						else
							Nekoyoubi.sendMessage(target, player.getDisplayName()+" has asked "+god.colorName()+" to send you aid.");
					} else if (mob.equalsIgnoreCase("angrywolf")) {
						for (int i = 0; i < quantity; i++) {
							Wolf wolf = (Wolf)target.getWorld().spawnCreature(target.getLocation().add(rando.nextInt(16)-8, 0, rando.nextInt(16)-8), CreatureType.WOLF);
							wolf.setTarget(player);
							wolf.setAngry(true);
						}
						if (target == player)
							Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Enjoy your company.");
						else
							Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought down the wrath of "+god.colorName()+".");
					}
				}
			}
		} else if (this.action.equalsIgnoreCase("heal")) {
			for (Player target : effectTargets) {
				target.setHealth(Integer.parseInt(this.data));
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" has healed your wounds.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has asked "+god.colorName()+" to heal your wounds.");
			}
		} else if (this.action.equalsIgnoreCase("feed")) {
			for (Player target : effectTargets) {
				target.setFoodLevel(Integer.parseInt(this.data));
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" has soothed your hunger.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has asked "+god.colorName()+" to soothe your hunger.");
			}			
		} else if (this.action.equalsIgnoreCase("hunger")) {
			for (Player target : effectTargets) {
				target.setFoodLevel(Integer.parseInt(this.data));
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Feast on your own famine.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has angered "+god.colorName()+".");
			}			
		} else if (this.action.equalsIgnoreCase("hurt")) {
			for (Player target : effectTargets) {
				target.damage(Integer.parseInt(this.data), player);
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Your life was forfeit.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought the wrath of "+god.colorName()+" upon you.");
			}
		} else if (this.action.equalsIgnoreCase("burn")) {
			for (Player target : effectTargets) {
				target.setFireTicks(Integer.parseInt(this.data)*20);
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Enjoy your rewards.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought the flames of "+god.colorName()+" upon you.");
			}
		} else if (this.action.equalsIgnoreCase("dig")) {
			for (Player target : effectTargets) {
				int dist = (this.data.length()>0) ? Integer.parseInt(this.data) : 3;
				Location tloc = target.getLocation();
				for (int u = 0; u < dist; u++) {
					Block under = tloc.getBlock().getRelative(0, -(u+1), 0);
					if (	under.getType() != Material.AIR &&
							under.getType() != Material.BEDROCK) {
						target.getWorld().dropItem(under.getLocation(), new ItemStack(under.getType()));
						under.setType(Material.AIR);
					}
				}
					if (target == player)
						Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. To the underworld with you!");
					else
						Nekoyoubi.sendMessage(target, player.getDisplayName()+" has convinced "+god.colorName()+" to send you to the underworld.");
			}
		} else if (this.action.equalsIgnoreCase("stormy")) {
			if (player.getWorld().getWeatherDuration() == 0) {
				player.getWorld().setStorm(true);
				for (Player target : effectTargets) {
					if (target == player)
						Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. The skies rumble and riot above you.");
					else
						Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought the tempests of "+god.colorName()+" upon you.");
				}
			}
		} else if (this.action.equalsIgnoreCase("sunny")) {
			if (player.getWorld().getWeatherDuration() > 0) {
				player.getWorld().setStorm(false);
				for (Player target : effectTargets) {
					if (target == player)
						Nekoyoubi.sendMessage(target, god.colorName()+" has lifted the clouds in your honor.");
					else
						Nekoyoubi.sendMessage(target, player.getDisplayName()+" has asked "+god.colorName()+" to bring out the sun.");
				}
			}
		} else if (this.action.equalsIgnoreCase("shock")) {
			for (Player target : effectTargets) {
				player.getWorld().strikeLightning(target.getLocation());
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Cower in the might of "+god.colorName()+"!");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought the wrath of "+god.colorName()+" upon you.");
			}
		} else if (this.action.equalsIgnoreCase("weaken")) {
			for (Player target : effectTargets) {
				PlayerInventory inv = target.getInventory();
                Random rando = new Random();
                // HACK: should really do something better than a rando(50), but it's what i have in the moment
				inv.getHelmet().setDurability((short)(inv.getHelmet().getDurability()+rando.nextInt(50)));
                inv.getChestplate().setDurability((short)(inv.getChestplate().getDurability()+rando.nextInt(50)));
                inv.getLeggings().setDurability((short)(inv.getLeggings().getDurability()+rando.nextInt(50)));
                inv.getBoots().setDurability((short) (inv.getBoots().getDurability()+rando.nextInt(50)));
				target.updateInventory();
				if (target == player)
					Nekoyoubi.sendMessage(target, god.colorName()+" was not amused. Your armor was your aegis.");
				else
					Nekoyoubi.sendMessage(target, player.getDisplayName()+" has brought the wrath of "+god.colorName()+" upon you.");
			}
		}
	}
	private int getQuantity(Player player, Random rando, Integer min,
			Integer max, boolean isRando) {
		return isRando
			? rando.nextInt(max-min)+min
			: (int)Math.round(((double)min+((double)(max-min)*((double)player.getLevel()/20.0))));
	}
	
	/**
	 * Determines the list of players to be effected by a blessing (or curse).
	 * @param player The player instigating the blessing.
	 * @return Returns an array of players to effect with a favor.
	 */
	private ArrayList<Player> getTargets(Player player) {
		ArrayList<Player> effectTargets = new ArrayList<Player>();
		for (String t : this.targets.split(";")) {
			if (t.equalsIgnoreCase("player")) {
				effectTargets.add(player);
			} else if (t.equalsIgnoreCase("random")) {
				Player rando = Nekoyoubi.randomPlayerInWorld(player.getWorld());
				if (!effectTargets.contains(rando)) effectTargets.add(rando);
			} else if (t.equalsIgnoreCase("world")) {
				effectTargets.clear();
				effectTargets.addAll(player.getWorld().getPlayers());
			} else if (t.equalsIgnoreCase("server")) {
				// TODO Think long and hard about this one... smells horribad.
			}
		}
		return effectTargets;
	}
}
