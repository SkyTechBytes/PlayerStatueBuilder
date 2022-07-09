package com.skytechbytes.api;

import java.util.LinkedHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class PlayerStatueBuilderAPIExamples {
	public static void examples() {
		World w = Bukkit.getServer().getWorld("world");
		try {
			PlayerStatueBuilderAPI.createStatue("SkyTechBytes", new Location(w, 0, 80, 0), "South", "default", "glass", "head", "diamond_helmet");
			
			LinkedHashMap<String, Float> params = new LinkedHashMap<>();
			params.put("hue", 0.4f);
			params.put("planks", 0f);
			params.put("diamond_boots", 0f);
			params.put("xz", 0f);
			PlayerStatueBuilderAPI.createStatue("SkyTechBytes", new Location(w, 0, 80, 20), "North", "default", params);
			
			PlayerStatueBuilderAPI.createStatue("SkyTechBytes", new Location(w, 0, 80, 20), "North", "default", "planks", "gray", "body", "head", "left_arm", "right_arm");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
