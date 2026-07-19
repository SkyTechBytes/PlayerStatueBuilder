package com.skytechbytes.playerstatuebuilder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
/**
 * @author SkyTechBytes
 */
public class StatueConfiguration {
	private final Plugin p = PlayerStatueBuilder.instance;

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
        
        ArrayList<String> headerContents = new ArrayList<>();
        headerContents.add("PlayerStatueBuilderX Configuration File");
        
        c.options().setHeader(headerContents);
       
        c.addDefault("rate", 16);
        c.addDefault("cooldown", 1);
        c.addDefault("charge", "DIAMOND");
        c.addDefault("exact", false);
        c.addDefault("priceRate", 0);
       
        c.options().copyDefaults(true);
       
        p.saveConfig();
       
        p.reloadConfig();
    }
}
