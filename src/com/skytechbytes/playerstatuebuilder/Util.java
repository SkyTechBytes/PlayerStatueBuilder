package com.skytechbytes.playerstatuebuilder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;
/**
 * 
 * @author SkyTechBytes
 * Big thanks to Crafatar and Playerdb.co for an amazing api to use for names and skins
 *
 */
public class Util {
	private static final Map<String,BufferedImage> cache = new ConcurrentHashMap<>();
	

	public static BufferedImage getSkinImage(String name) throws Exception {
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
		// Remove the weirdness at the front
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
		
			Path customFile = Paths.get(PlayerStatueBuilder.instance.getDataFolder().toURI().resolve(fileName));
		
		
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
	private static String getUUID(String name) throws Exception {
		String uuid = "";
		try {
			String json = APIWrapper.readJsonFromUrl("https://playerdb.co/api/player/minecraft/" + name);					

			uuid = json.substring(json.indexOf("raw_id\":")+9, json.indexOf("raw_id\":")+9+32);

			Log.log(uuid);
		} catch (Exception e) {
			throw new Exception("Failed to lookup uuid, likely because player specified does not exist.");
			
		}
		return uuid;
	}
	public static BufferedImage getCloudSkinImage(String name) throws Exception {
		BufferedImage bi;
	
		/*
		 * If the skin has no prefix, it is a normal skin that must be retrieved from the cloud.
		 */
			
		try {
			URL url1 = new URL("https://mineskin.eu/download/" + name);
			
			bi = ImageIO.read(url1);
			if (bi != null) {
				return ImageUtil.deepCopy(bi);
			}
		} catch (IOException e) {
			Log.log("Could not obtain skin from mineskin.eu");
		}
		
		/*
		 * Obtain player UUID to try alternate endpoints
		 */
		String uuid = getUUID(name);
		
		/*
		 * Try endpoints that require player uuid
		 */
		String[] endpoints = { "https://mc-heads.net/download/", "https://crafatar.com/skins/" };
		for (String endpoint : endpoints) {
			try {
				URL url = new URL(endpoint + uuid);
				bi = ImageIO.read(url);
				if (bi != null) {
					return ImageUtil.deepCopy(bi);
				}
			} catch (IOException e) {
				Log.log("Could not obtain skin from " + endpoint);
			}
		}
		
		throw new Exception("Could not obtain skin from the API or backup API. Please try again later.");
	}
}
