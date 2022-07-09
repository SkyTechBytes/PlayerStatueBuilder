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
		
		if (isDiskSkin(name)) {
			if (!p.hasPermission("playerstatuebuilderx.custom")) {
				throw new Exception("Insufficient permissions to create custom statues!");
			}
		}
		bi = getSkinImage(name);
		
		
		return ImageUtil.deepCopy(bi);
	}
	public static BufferedImage getSkinImage(String name) throws Exception{
		BufferedImage bi = null;
		if (!cache.containsKey(name)) {
			if (isDiskSkin(name)) {
				bi = getDiskSkinImage(name);
			} else {
				bi = getCloudSkinImage(name);
			}
		} else {
			bi = cache.get(name);
		}
		return bi;
	}
	public static boolean isDiskSkin(String name) {
		return name.startsWith(".") && name.length() > 1;
	}
	public static BufferedImage getDiskSkinImage(String name) throws Exception {
		
		
		BufferedImage bi = null;
		/*
		 * The skin is on the local disk in the plugin's data folder.
		 */
		//Remove the weirdness at the front
		String fileName = name.substring(1);
		
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		boolean hasSpecialChar = pattern.matcher(fileName).find();
		
		if (hasSpecialChar) {
			throw new Exception("Admins: make sure your skin image is alphanumeric only; Your skin name can't have a special character (don't include .png).");
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
		return ImageUtil.deepCopy(bi);
	}
	public static BufferedImage getCloudSkinImage(String name) throws Exception {
		BufferedImage bi = null;
	
		/*
		 * If the skin has no prefix, it is a normal skin that must be retrieved from the cloud.
		 */
		try {
			
			String json = APIWrapper.readJsonFromUrl("https://playerdb.co/api/player/minecraft/" + name);					
			
			String uuid = json.substring(json.indexOf("raw_id\":")+9, json.indexOf("raw_id\":")+9+32);
			
			
			Log.log(uuid);
			URL URL  = new URL("https://crafatar.com/skins/" + uuid);
			bi = ImageIO.read(URL);
			
		} catch (IOException e) {
			//We know what went wrong (500 error)
			if (e.getMessage().contains("code: 500")) {
				throw new Exception("The player you specified likely does not exist. ");
			} else {
				//Try fallback/backup API
				try {
					bi = APIWrapper.readFallback(name);
				} catch (Exception ee) {
					ee.printStackTrace();
					throw new Exception("Could not obtain skin from the API or backup API. Please try again later.");
				}
			}
		} catch (Exception e) {
			//We don't know what went wrong.
			e.printStackTrace();
			throw new Exception("The API servers may be down. Please try again later: " + e.getMessage());
		}
		return ImageUtil.deepCopy(bi);
	}
}
