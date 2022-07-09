package com.skytechbytes.builder;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.skytechbytes.testplugin.PlayerStatuePlugin;

public class SchematicUtil {
	public static boolean canBuild(Schematic s, Player p) {
		try {
			//WARNING: DO NOT CHANGE	
			Location lesserCorner = new Location(s.getWorld(), s.getMinX(), s.getMinY(), s.getMinZ());
			Location greaterCorner = new Location(s.getWorld(), s.getMaxX(), s.getMaxY(), s.getMaxZ());
			return PlayerStatuePlugin.wgw.canBuild(lesserCorner, greaterCorner, p);
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean removeItemsOrAlert(Schematic s, Player p) {

		if (p.hasPermission("playerstatuebuilderx.bypass")) {
			return true;
		}
		
		boolean hasRequired = true;
		
		
		
		HashMap<Material,Integer> blocks = query(s, p);
		
		//query the price after block query to ensure correct number of blocks
		if (queryPrice(s, p) == false || getPrice(s) < 0) {
			hasRequired = false;
		}

		for (Material key : blocks.keySet()) {

			if (p.getInventory().contains(key, blocks.get(key)) == false) {
				hasRequired = false;
			}
		}
		
		if (hasRequired == false) {
			p.sendMessage(ChatColor.RED + "You don't have all the materials to make that statue right now... "
					+ "when you do, make sure you run this command in an open space. You will be at the base of the statue "
					+ "and the statue will be facing towards you.");
		}

		if (hasRequired == true) {
			p.sendMessage(ChatColor.GREEN + "You have all required materials!");
			for (Material key : blocks.keySet()) {
				p.getInventory().removeItem(new ItemStack(key,blocks.get(key)));
			}
			p.updateInventory();
			
			if (PlayerStatuePlugin.vw != null) {
				PlayerStatuePlugin.vw.getEconomy().withdrawPlayer(p, getPrice(s));
				p.sendMessage(ChatColor.GREEN + "$" + getPrice(s) + " has been removed from your account"); 
			}
			
			return true;
		}
		return false;
	}
	private static double getPrice(Schematic s) {
		return PlayerStatuePlugin.instance.getConfig().getDouble("priceRate") * s.getCount();
	}
	private static boolean queryPrice(Schematic s, Player p) {
		if (PlayerStatuePlugin.vw != null) {
			p.sendMessage(ChatColor.AQUA + "You will need $" + getPrice(s) + " as well.");
			
			if (!PlayerStatuePlugin.vw.getEconomy().has(p, getPrice(s))) {
				p.sendMessage(ChatColor.RED + "You do not have enough money!");
				return false;  
			}
			
		}
		return true;
	}
	public static HashMap<Material,Integer> query(Schematic s, Player p) {
		int count = 0;
		HashMap<Material,Integer> blocks = new HashMap<>();
		for (int keyZ : s.getMatrix().keySet()) {
			for (int keyY : s.getMatrix().get(keyZ).keySet()) {
				for (int keyX : s.getMatrix().get(keyZ).get(keyY).keySet()) {
					Material temp = s.getMatrix().get(keyZ).get(keyY).get(keyX).getM();
					if (temp.equals(Material.AIR)) {
						continue;
					}
					
					if(PlayerStatuePlugin.instance.getConfig().getBoolean("exact") == false) {
					
						if (Tag.WOOL.isTagged(temp)) {
							temp = Material.WHITE_WOOL;
						} else if (Tag.PLANKS.isTagged(temp)) {
							temp = Material.OAK_PLANKS;
						} else if (temp.toString().endsWith("TERRACOTTA")) {
							temp = Material.TERRACOTTA;
						} else if (temp.toString().endsWith("CONCRETE")) {
							temp = Material.WHITE_CONCRETE;
						} else if (temp.toString().endsWith("GLASS")) {
							temp = Material.GLASS;
						}
					}
					
					if (blocks.containsKey(temp)) {
						blocks.put(temp,blocks.get(temp)+1);
					} else {
						blocks.put(temp,1);
					}
					count++;
				}
			}
		}
		Material charge = Material.matchMaterial(PlayerStatuePlugin.instance.getConfig().getString("charge"));
		int rate = PlayerStatuePlugin.instance.getConfig().getInt("rate");
		if (rate > 0 && charge != null) {
			blocks.put(charge, count/rate);
		} else {
			blocks.put(Material.EMERALD, count/10);
		}

		String need = ChatColor.AQUA + "To make this player statue, you need: \n";
		for (Material key : blocks.keySet()) {
			need+=(key + ": " + blocks.get(key) + "\n");

		}
		
		
		
		p.sendMessage(need);

		

		return blocks;

	}
}
