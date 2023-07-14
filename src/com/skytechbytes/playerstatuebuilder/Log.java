package com.skytechbytes.playerstatuebuilder;

import java.util.logging.Level;

public class Log {
	public static void log(String s) {
		PlayerStatueBuilder.getPlugin(PlayerStatueBuilder.class).getLogger().log(Level.INFO, s);
	}
}
