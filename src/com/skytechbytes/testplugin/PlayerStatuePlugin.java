package com.skytechbytes.testplugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStatuePlugin extends JavaPlugin {

	public PlayerStatuePlugin() {
		// TODO Auto-generated constructor stub
	}
	 // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	this.getCommand("statue").setExecutor(new CommandStatue());


    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
