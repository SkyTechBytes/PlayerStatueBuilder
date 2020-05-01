package com.skytechbytes.testplugin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.skytechbytes.builder.ImageUtil;
/**
 * 
 * @author SkyTechBytes
 * Big thanks to Crafatar and Playerdb.co for an amazing api to use for names and skins
 *
 */
public class Util {
	private static Map<String,BufferedImage> cache = new HashMap<>();
	
	
	public static BufferedImage getSkinImage(Player p, String[] arg3) throws Exception {
		
		String name = p.getName();
		
		if (arg3.length >= 1) {
			name = arg3[0];
			
		}
		
		BufferedImage bi = null;
		if (!cache.containsKey(name)) {
			//don't want those creepy little errors.
			if (name.startsWith(".") && name.length() > 1) {
				
				if (!p.hasPermission("playerstatuebuilderx.custom")) {
					throw new Exception("Insufficient permissions to create custom statues!");
				}
				
				
				/*
				 * The skin is on the local disk in the plugin's data folder.
				 */
				//Remove the weirdness at the front
				String fileName = name.substring(1);
				
				Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
				boolean hasSpecialChar = pattern.matcher(fileName).find();
				
				if (hasSpecialChar) {
					if (p.isOp()) p.sendMessage(ChatColor.RED + "Admins: make sure your skin image is alphanumeric only");
					throw new Exception("Your skin name can't have a special character (don't include .png).");
				}
				
				if (!(fileName.endsWith(".png") || fileName.endsWith(".PNG"))) {
					fileName = fileName + ".png";
				}
				
				
				try {
				
					Path customFile = Paths.get(PlayerStatuePlugin.instance.getDataFolder().toURI().resolve(fileName));
				
				
					bi = ImageIO.read(customFile.toFile());
					
					if (bi == null) {
						throw new Exception();
					}
				} catch (Exception e) {
					throw new Exception("Unable to load file from plugin data folder. Make sure the name is spelled correctly and the skin"
							+ " image file is in the same folder as the config file.");
				}
				
			
			} else {
			
				/*
				 * If the skin has no prefix, it is a normal skin that must be retrieved from the cloud.
				 */
				try {
					
					String json = APIWrapper.readJsonFromUrl("https://playerdb.co/api/player/minecraft/" + name);					
					
					String uuid = json.substring(json.indexOf("raw_id\":")+9, json.indexOf("raw_id\":")+9+32);
					
					
					System.out.println(uuid);
					URL URL  = new URL("https://crafatar.com/skins/" + uuid);
		
					bi = ImageIO.read(URL);
					
					
				
				} catch (IOException e) {
					//We know what went wrong (500 error)
					if (e.getMessage().contains("code: 500")) {
						throw new Exception("The player you specified likely does not exist. ");
					} else {
						e.printStackTrace();
						throw new Exception("Something is wrong with getting the skin from the API (IO Exception). The API servers may be down (Please try again later). ");
					}
				} catch (Exception e) {
					
					//We don't know what went wrong.
					e.printStackTrace();
					throw new Exception("The API servers may be down. Please try again later: " + e.getMessage());
				}
			
			}
			
			cache.put(name, bi);
		} else {
			bi = cache.get(name);
		}
		
		return ImageUtil.deepCopy(bi);
	}
}
