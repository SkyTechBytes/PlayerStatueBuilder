package com.skytechbytes.testplugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.skytechbytes.support.WorldGuardWrapper;
/**
 * 
 * @author SkyTechBytes
 *
 */
public class PlayerStatuePlugin extends JavaPlugin {
	public static PlayerStatuePlugin instance;
	public static WorldGuardWrapper wgw;
	public static StatueConfiguration sc;
	public PlayerStatuePlugin() {
		// TODO Auto-generated constructor stub
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
    		wgw = null;
    	}
    	this.getCommand("statue").setExecutor(new CommandStatue());


    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
