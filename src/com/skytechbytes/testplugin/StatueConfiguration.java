package com.skytechbytes.testplugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class StatueConfiguration {
	private Plugin p = PlayerStatuePlugin.instance;
	
	public FileConfiguration getConfig() {
		return p.getConfig();
	}
	public void createConfig() {
        try {
            if (!p.getDataFolder().exists()) {
                p.getDataFolder().mkdirs();
            }
            File file = new File(p.getDataFolder(), "config.yml");
            if (!file.exists()) {
                Log.log("Config.yml not found, creating!");
                this.loadDefaultConfigFile();
            } else {
                Log.log("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        

    }
	private void loadDefaultConfigFile() {
	       
        FileConfiguration c = p.getConfig();
       
        c.options().header("PlayerStatueBuilderX Configuration File");
       
        c.addDefault("rate", 16);
        c.addDefault("cooldown", 5);
        c.addDefault("charge", "DIAMOND");
       
        c.options().copyDefaults(true);
       
        p.saveConfig();
       
        p.reloadConfig();
    }
}
