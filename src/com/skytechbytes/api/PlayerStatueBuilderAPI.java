package com.skytechbytes.api;

import java.util.LinkedHashMap;

import org.bukkit.Location;

import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;
import com.skytechbytes.playerstatuebuilder.StatueBuildTask;
import com.skytechbytes.playerstatuebuilder.Util;
import com.skytechbytes.playerstatuebuilder.builder.StatueMaker;

public class PlayerStatueBuilderAPI {
	/**
	 * Programmatically creates a player statue at location "origin"
	 * @param skinIdentifier A player name, or a period "." immediately followed by a skin image file name in the plugin's data folder
	 * @param origin The foot of the player statue, where the statue is built
	 * @param direction One of "North" "South" "East" "West" (the statue will face in this direction)
	 * @param mode One of "slim" "legacy" or "default"
	 * @param params Other parameters such as "glass" "concrete" "xz" "planks" "head" "left_leg" "diamond_armor"
	 * 	parameters without a value (like those previously mentioned) you can set to 0f or any float value or use the other API method to just list them out
	 * The last parameter is just a list of whatever parameters you want left over (see plugin page for all available keywords)
	 * @throws Exception 
	 * @throws IllegalStateException 
	 * @throws IllegalArgumentException 
	 */
	public static void createStatue(String skinIdentifier, Location origin, String direction, String mode, LinkedHashMap<String, Float> params) 
			throws IllegalArgumentException, IllegalStateException, Exception {
		new StatueBuildTask(skinIdentifier, new StatueMaker(origin, direction, mode, Util.getSkinImage(skinIdentifier), params), null).runTaskAsynchronously(PlayerStatueBuilder.instance);
	}
	/**
	 * Programmatically creates a player statue at location "origin"
	 * @param skinIdentifier A player name, or a period "." immediately followed by a skin image file name in the plugin's data folder
	 * @param origin The foot of the player statue, where the statue is built
	 * @param direction One of "North" "South" "East" "West" (the statue will face in this direction)
	 * @param mode One of "slim" "legacy" or "default"
	 * @param params Other parameters such as "glass" "concrete" "xz" "planks" "head" "left_leg" "diamond_armor"
	 * So you could do createStatue("SkyTechBytes", locationObj, "North", "default", "glass", "xz", "head", "iron_helmet")
	 * The last parameter is just a list of whatever parameters you want left over (see plugin page for all available keywords)
	 * @throws Exception 
	 * @throws IllegalStateException 
	 * @throws IllegalArgumentException 
	 */
	public static void createStatue(String skinIdentifier, Location origin, String direction, String mode, String... params) 
			throws IllegalArgumentException, IllegalStateException, Exception {
		LinkedHashMap<String, Float> temp = new LinkedHashMap<>();
		for (String param : params) {
			temp.put(param, 0f);
		}
		createStatue(skinIdentifier, origin, direction, mode, temp);
	}
}
