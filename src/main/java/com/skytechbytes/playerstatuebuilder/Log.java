package com.skytechbytes.playerstatuebuilder;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Log {
	public static void log(String s) {
		try {
			JavaPlugin.getPlugin(PlayerStatueBuilder.class).getLogger().log(Level.INFO, s);
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("[PlayerStatueBuilder] " + s);
		}
	}
}
