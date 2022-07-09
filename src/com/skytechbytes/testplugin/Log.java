package com.skytechbytes.testplugin;

import java.util.logging.Level;

public class Log {
	public static void log(String s) {
		PlayerStatuePlugin.getPlugin(PlayerStatuePlugin.class).getLogger().log(Level.INFO, s);
	}
}
