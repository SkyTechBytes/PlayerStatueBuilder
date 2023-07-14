package com.skytechbytes.playerstatuebuilder.builder;

import java.util.HashMap;
import java.util.Stack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.skytechbytes.playerstatuebuilder.Log;
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
	private World w;
	private int count = 0; //Count of non-air blocks
	public Schematic (World w) {
		this.w = w;
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
			matrix.get(z).get(y).put(x, new MaterialHolder(Material.AIR));
		}
		
		Material previous = matrix.get(z).get(y).get(x).getM();
		MaterialHolder replacement = new MaterialHolder(m);
		matrix.get(z).get(y).put(x, replacement);
		if (previous == Material.AIR && replacement.getM() != Material.AIR) {
			count++;
		}
		
	}
	public boolean createSchematic(boolean eraseMode, boolean replaceAirOnly) {

		for (int keyZ : matrix.keySet()) {
			for (int keyY : matrix.get(keyZ).keySet()) {
				for (int keyX : matrix.get(keyZ).get(keyY).keySet()) {
					MaterialHolder mat = matrix.get(keyZ).get(keyY).get(keyX);
					//Make sure we aren't doing anything we shouldn't

					if (mat.getM().equals(Material.AIR)) {
						continue;
					}
					//WARNING: DO NOT CHANGE
					Block b = w.getBlockAt(new Location(w,keyX,keyY,keyZ));
					if (replaceAirOnly) {
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
		if (eraseMode == false) {
			Log.log("Statue Created");
	
			Schematic.history.add(this);
		}
		return true;
	}
	public HashMap<Integer, HashMap<Integer, HashMap<Integer, MaterialHolder>>> getMatrix() {
		return matrix;
	}
	public int getCount() {
		return count;
	}
	public int getMaxX() {
		return maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public int getMaxZ() {
		return maxZ;
	}
	public int getMinX() {
		return minX;
	}
	public int getMinY() {
		return minY;
	}
	public int getMinZ() {
		return minZ;
	}
	public World getWorld() {
		return this.w;
	}
	


}
