package com.skytechbytes.playerstatuebuilder.builder;

import java.util.HashMap;

import com.skytechbytes.playerstatuebuilder.Log;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;
/**
 * 
 * @author SkyTechBytes
 *
 */
public class SchematicUtil {
	public static boolean canBuild(Schematic s, Player p) {
		//WARNING: DO NOT CHANGE
		Location lesserCorner = new Location(s.getWorld(), s.getMinX(), s.getMinY(), s.getMinZ());
		Location greaterCorner = new Location(s.getWorld(), s.getMaxX(), s.getMaxY(), s.getMaxZ());

		boolean canBuild = true;
		// if worldguard detected, check if we can build here
		if (PlayerStatueBuilder.wgw != null) {
			canBuild = PlayerStatueBuilder.wgw.canBuild(lesserCorner, greaterCorner, p);
			Log.log("WorldGuard can build: " + canBuild);
		}
		// if plot squared detected, also check if we can build here
		if (PlayerStatueBuilder.plotw != null) {
			boolean plotSquaredCanBuild = PlayerStatueBuilder.plotw.canBuild(lesserCorner, greaterCorner, p);
			canBuild = canBuild && plotSquaredCanBuild;
			Log.log("PlotSquared can build: " + plotSquaredCanBuild);
		}
		return canBuild;
	}

	public static boolean removeItemsOrAlert(Schematic s, Player p) {

		if (p.hasPermission("playerstatuebuilderx.bypass")) {
			return true;
		}
		
		boolean hasRequired = true;
		
		
		
		HashMap<Material,Integer> blocks = query(s, p);
		
		//query the price after block query to ensure correct number of blocks
		if (!queryPrice(s, p) || getPrice(s) < 0) {
			hasRequired = false;
		}

		for (Material key : blocks.keySet()) {

			if (!p.getInventory().contains(key, blocks.get(key))) {
				hasRequired = false;
			}
		}
		
		if (!hasRequired) {
			p.sendMessage(ChatColor.RED + "You don't have all the materials to make that statue right now... "
					+ "when you do, make sure you run this command in an open space. You will be at the base of the statue "
					+ "and the statue will be facing towards you.");
		}

		if (hasRequired) {
			p.sendMessage(ChatColor.GREEN + "You have all required materials!");
			for (Material key : blocks.keySet()) {
				p.getInventory().removeItem(new ItemStack(key,blocks.get(key)));
			}
			p.updateInventory();
			
			if (PlayerStatueBuilder.vw != null) {
				PlayerStatueBuilder.vw.getEconomy().withdrawPlayer(p, getPrice(s));
				p.sendMessage(ChatColor.GREEN + "$" + getPrice(s) + " has been removed from your account"); 
			}
			
			return true;
		}
		return false;
	}
	private static double getPrice(Schematic s) {
		return PlayerStatueBuilder.instance.getConfig().getDouble("priceRate") * s.getCount();
	}
	private static boolean queryPrice(Schematic s, Player p) {
		if (PlayerStatueBuilder.vw != null) {
			p.sendMessage(ChatColor.AQUA + "You will need $" + getPrice(s) + " as well.");
			
			if (!PlayerStatueBuilder.vw.getEconomy().has(p, getPrice(s))) {
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
					
					if (!PlayerStatueBuilder.instance.getConfig().getBoolean("exact")) {
					
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
		Material charge = Material.matchMaterial(PlayerStatueBuilder.instance.getConfig().getString("charge"));
		int rate = PlayerStatueBuilder.instance.getConfig().getInt("rate");
		if (rate > 0 && charge != null) {
			blocks.put(charge, count/rate);
		} else {
			blocks.put(Material.EMERALD, count/10);
		}

		StringBuilder need = new StringBuilder(ChatColor.AQUA + "To make this player statue, you need: \n");
		for (Material key : blocks.keySet()) {
			need.append(key).append(": ").append(blocks.get(key)).append("\n");

		}
		
		
		
		p.sendMessage(need.toString());

		

		return blocks;

	}
}
