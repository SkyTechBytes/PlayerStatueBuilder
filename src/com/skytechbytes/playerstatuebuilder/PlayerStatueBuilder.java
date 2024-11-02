package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.builder.AssetManager;
import com.skytechbytes.playerstatuebuilder.support.PlotSquaredWrapper;
import com.skytechbytes.playerstatuebuilder.support.VaultWrapper;
import com.skytechbytes.playerstatuebuilder.support.WorldGuardWrapper;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author SkyTechBytes
 *
 */
public class PlayerStatueBuilder extends JavaPlugin {
	public static PlayerStatueBuilder instance;
	public static WorldGuardWrapper wgw = null;
	public static VaultWrapper vw = null;
	public static PlotSquaredWrapper plotw = null;
	public static StatueConfiguration sc;
	public static AtomicInteger statueCount = new AtomicInteger(0);
	public PlayerStatueBuilder() {
	}
	 // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	instance = this;
    	
    	sc = new StatueConfiguration();
    	sc.createConfig();
    	
    	try {
    		wgw = new WorldGuardWrapper();
    	} catch (Throwable fit) {
    		Log.log("Worldguard not detected. You MUST have WorldGuard if you want PlayerStatueBuilderX to respect claim/region protections.");
    		wgw = null;
    	}
    	
    	try {
    		vw = new VaultWrapper();
    	} catch (Throwable rock) {
    		vw = null;
    	}

		try {
			plotw = new PlotSquaredWrapper();
		} catch (Throwable stick) {
			Log.log("PlotSquared not detected. The plugin MUST detect PlotSquared if you want the plugin to respect plot squared protections.");
			plotw = null;
		}
    	
    	AssetManager.initialize();
    	
    	this.getCommand("statue").setExecutor(new CommandStatue());
    	this.getCommand("statue").setTabCompleter(new StatueTabComplete());
    	
    	this.getCommand("undostatue").setExecutor(new CommandUndostatue());
    	
    	// Bstats begins here
    	int pluginId = 7093;
    	Metrics m = new Metrics(this,pluginId);

		m.addCustomChart(new Metrics.SingleLineChart("statues", () -> {
			int statuesCreated = statueCount.get();
			// reset number of statues made up to this point
			statueCount.set(0);
			return statuesCreated;
		}));
    	// int version = Metrics.B_STATS_VERSION;
    	
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
