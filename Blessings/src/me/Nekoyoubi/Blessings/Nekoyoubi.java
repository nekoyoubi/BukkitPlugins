package me.Nekoyoubi.Blessings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Nekoyoubi {
    private static String chatStart = ChatColor.GOLD + "Blessings" + ChatColor.WHITE + ": ";
    
    public static void sendMessage(Player player, String message) {
        Nekoyoubi.sendMessage(player, message, false);
    }
	public static void sendMessage(Player player, String message, boolean nextline) {
		if (nextline) {
			if (message.startsWith("<"))
				message = message.substring(1);
			else {
				message = "             "+message;
			}
		}
		else {
			message = chatStart+message;
		}
		player.sendMessage(message
        	.replaceAll("(&([a-f0-9]))", "\u00A7$2")
        	.replaceAll("%WORLD%", titleCase(player.getWorld().getName())));
	}
	public static Player randomPlayerInWorld(World world) {
		if (world.getPlayers().size() > 0) {
			return world.getPlayers().get(new Random().nextInt(world.getPlayers().size()));
		} else {
			return null;		
		}
	}

	public static String titleCase(String text){
        String result = "";
        for (int i = 0; i < text.length(); i++){
            String next = text.substring(i, i + 1);
            result += (i == 0) ? next.toUpperCase() : next.toLowerCase();
        }
        return result;
    }
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}

    public static ItemStack getEnchSword(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 7;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_ALL, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_UNDEAD, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.KNOCKBACK, rando.nextInt(2)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.FIRE_ASPECT, rando.nextInt(2)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LOOT_BONUS_MOBS, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchAxe(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 7;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_ALL, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DAMAGE_UNDEAD, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DIG_SPEED, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchPick(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 4;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DIG_SPEED, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchSpade(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 4;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DIG_SPEED, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchShears(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 3;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DIG_SPEED, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.SILK_TOUCH, 1);
        return item;
    }

    public static ItemStack getEnchHoe(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 1;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchFlint(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 1;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchCarrot(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 1;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchBow(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 5;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.ARROW_DAMAGE, rando.nextInt(5)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.ARROW_KNOCKBACK, rando.nextInt(2)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.ARROW_FIRE, rando.nextInt(1)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.ARROW_INFINITE, rando.nextInt(1)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchRod(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 3;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LUCK, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.LURE, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchHelmet(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 8;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_FIRE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_PROJECTILE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.OXYGEN, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.WATER_WORKER, rando.nextInt(1)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.THORNS, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchChest(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 6;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_FIRE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_PROJECTILE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.THORNS, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchLegs(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 6;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_FIRE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_PROJECTILE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.THORNS, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

    public static ItemStack getEnchBoots(Material itemType) {
        Random rando = new Random();
        ItemStack item = new ItemStack(itemType);
        Integer rate = 7;
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_FIRE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_FALL, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.PROTECTION_PROJECTILE, rando.nextInt(4)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.THORNS, rando.nextInt(3)+1);
        if (rando.nextInt(rate)==0) item.addEnchantment(Enchantment.DURABILITY, rando.nextInt(3)+1);
        return item;
    }

}
