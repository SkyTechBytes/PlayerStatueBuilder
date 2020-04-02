package com.skytechbytes.builder;

import java.util.HashMap;
import java.util.Stack;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.skytechbytes.testplugin.Log;
import com.skytechbytes.testplugin.PlayerStatuePlugin;
/**
 * 
 * @author SkyTechBytes
 * "This is by far the worst code I've ever seen"
 * "But it works"
 *
 */
public class Schematic {
	public static Stack<Schematic> history = new Stack<>();
	/*
	 * Hashception
	 */

	private HashMap<Integer,HashMap<Integer,HashMap<Integer,MaterialHolder>>> matrix = new HashMap<>();

	public Schematic () {

	}

	int maxX = Integer.MIN_VALUE;
	int maxY = Integer.MIN_VALUE;
	int maxZ = Integer.MIN_VALUE;
	int minX = Integer.MAX_VALUE;
	int minY = Integer.MAX_VALUE;
	int minZ = Integer.MAX_VALUE;
	/**
	 * You can put in world coordinates or small relative coordinates- it really shouldn't matter
	 * @param x
	 * @param y
	 * @param z
	 * @param m
	 */
	public void setBlockAt(int x, int y, int z, Material m) {

		if (x > maxX) maxX = x;
		if (y > maxY) maxY = y;
		if (z > maxZ) maxZ = z;
		if (x < minX) minX = x;
		if (y < minY) minY = y;
		if (z < minZ) minZ = z;

		if (matrix.containsKey(z) == false) {
			matrix.put(z, new HashMap<Integer,HashMap<Integer,MaterialHolder>>() );
		}
		if (matrix.get(z).containsKey(y) == false) {
			matrix.get(z).put(y, new HashMap<Integer,MaterialHolder>());
		}
		if (matrix.get(z).get(y).containsKey(x) == false) {
			matrix.get(z).get(y).put(z, new MaterialHolder(Material.AIR));
		}

		matrix.get(z).get(y).put(x, new MaterialHolder(m));
	}
	public boolean createStatue(World w, Player p, boolean eraseMode) {

		for (int keyZ : matrix.keySet()) {
			for (int keyY : matrix.get(keyZ).keySet()) {
				for (int keyX : matrix.get(keyZ).get(keyY).keySet()) {
					MaterialHolder mat = matrix.get(keyZ).get(keyY).get(keyX);
					//Make sure we aren't doing anything we shouldn't

					if (mat.getM().equals(Material.AIR)) {
						continue;
					}
					//WARNING: DO NOT CHANGE
					Block b = w.getBlockAt(new Location(w,keyX,keyY+3,keyZ));
					if (p.hasPermission("playerstatuebuilderx.override") == false) {
						//We only replace AIR blocks in the world!
						if (!b.getType().equals(Material.AIR)) {
							continue;
						}
					}
					/*
					 * So if the material previously successfully changed and you want to erase and the material is the same
					 * material it was changed to previously, then we will revert it back to an AIR block, which it must
					 * have been (when building the statue, the plugin does not modify non-air blocks)
					 * If this were somehow triggered too early, nothing would happen as all mat.isSuccess() is false by default 
					 * Please note that you'll also need the "override" permission to overwrite any non-air blocks
					 */
					if (eraseMode == true && 
							mat.isSuccess() == true && 
							b.getBlockData().getMaterial().equals(matrix.get(keyZ).get(keyY).get(keyX).getM())) 
					{
						b.setType(Material.AIR);
						continue;
					} else if (eraseMode == true) {
						continue;
					}

					b.setType(matrix.get(keyZ).get(keyY).get(keyX).getM());
					mat.setSuccess(true);
				}
			}
		}
		Log.log("Statue Created");
		return true;
	}
	public boolean canBuild(World w, Player p) {
		try {
			//WARNING: DO NOT CHANGE
			return PlayerStatuePlugin.wgw.canBuild(new Location(w,minX,minY+3,+minZ), new Location(w,maxX,+maxY+3,maxZ), p);
		} catch (Exception e) {
			return true;
		}
	}
	public boolean removeItems(Player p) {

		if (p.hasPermission("playerstatuebuilderx.bypass")) {
			return true;
		}

		HashMap<Material,Integer> blocks = query(p);

		boolean hasRequired = true;

		for (Material key : blocks.keySet()) {

			if (p.getInventory().contains(key, blocks.get(key)) == false) {
				hasRequired = false;
			}
		}

		if (hasRequired == true) {
			p.sendMessage(ChatColor.GREEN + "You have all required materials!");
			for (Material key : blocks.keySet()) {
				p.getInventory().removeItem(new ItemStack(key,blocks.get(key)));
			}
			p.updateInventory();

			return true;
		}
		return false;
	}
	public HashMap<Material,Integer> query(Player p) {
		boolean hasRequired = true;
		int count = 0;
		HashMap<Material,Integer> blocks = new HashMap<>();
		for (int keyZ : matrix.keySet()) {
			for (int keyY : matrix.get(keyZ).keySet()) {
				for (int keyX : matrix.get(keyZ).get(keyY).keySet()) {
					Material temp = matrix.get(keyZ).get(keyY).get(keyX).getM();
					if (temp.equals(Material.AIR)) {
						continue;
					}
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
			if (p.getInventory().contains(key, blocks.get(key)) == false) {
				hasRequired = false;
			}
		}

		p.sendMessage(need);

		if (hasRequired == false) {
			p.sendMessage(ChatColor.YELLOW + "You don't have all the materials to make that statue right now... "
					+ "when you do, make sure you run this command in an open space. You will be at the base of the statue "
					+ "and the statue will be facing towards you.");
		}

		return blocks;

	}

}
