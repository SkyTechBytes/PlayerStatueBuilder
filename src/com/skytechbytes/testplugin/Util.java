package com.skytechbytes.testplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Util {
	public static final List<Material> UNBREAKABLE = new ArrayList<Material>();
	static {
		UNBREAKABLE.add(Material.BARRIER);
		UNBREAKABLE.add(Material.BEDROCK);
		UNBREAKABLE.add(Material.COMMAND_BLOCK);
		UNBREAKABLE.add(Material.END_GATEWAY);
		UNBREAKABLE.add(Material.END_PORTAL);
		UNBREAKABLE.add(Material.END_PORTAL_FRAME);
		UNBREAKABLE.add(Material.JIGSAW);
		UNBREAKABLE.add(Material.STRUCTURE_BLOCK);
		UNBREAKABLE.add(Material.ANVIL);
		UNBREAKABLE.add(Material.ENCHANTING_TABLE);
		UNBREAKABLE.add(Material.OBSIDIAN);
		UNBREAKABLE.add(Material.ENDER_CHEST);
		UNBREAKABLE.add(Material.NETHER_PORTAL);
		UNBREAKABLE.add(Material.CHEST);
		UNBREAKABLE.add(Material.SHULKER_BOX);
		UNBREAKABLE.add(Material.BARREL);
	}
	public static boolean isSensitive(Material m) {
		return UNBREAKABLE.contains(m);
	}
}
