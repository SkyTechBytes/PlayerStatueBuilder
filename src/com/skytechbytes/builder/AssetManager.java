package com.skytechbytes.builder;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.skytechbytes.testplugin.Log;
/**
 * Overlay Armor Textures are by SmashPortal https://www.planetminecraft.com/skin/18-diamond-armor-overlay-skin/
 * 
 * @author SkyTechBytes
 *
 */
public class AssetManager {
	public static HashMap<String,BufferedImage> armor = new HashMap<>();
	public static HashMap<String,BufferedImage> items = new HashMap<>();
	private static String[] armor_materials = {"chainmail","iron","golden","diamond"};
	public static void initialize() {
		
		
		for (String material : armor_materials) {
			
			load(material + "_armor",armor);
			
			//get sub images
			load(material + "_helmet",armor,material + "_armor",new Rectangle(32,0,32,16));
			load(material + "_chestplate",armor,material + "_armor",new Rectangle(16,32,48,32));
			load(material + "_boots",armor,material + "_armor",new Rectangle(0,32,16,32));
		}
		
		
	}
	public static void load(String name, HashMap<String,BufferedImage> group) {
		try {
			String file = "/" + name + ".png";
			Log.log("Loading texture " + file);
			group.put(name, ImageIO.read(AssetManager.class.getResourceAsStream(file)));
		} catch (Exception e) {
			Log.log("Failed to load texture " + name + ". Restart the server to fix.");
		}
	}
	/*
	 * So it's like a spritesheet
	 */
	public static void load(String name, HashMap<String,BufferedImage> group, String baseImage, Rectangle... subregions) {
		BufferedImage base = group.get(baseImage);
		
		if (base == null) return;
		
		//Don't forget the A in ARGB (we have alpha)
		BufferedImage subImage = new BufferedImage(base.getWidth(),base.getHeight(),BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = subImage.getGraphics();
		
		for (Rectangle region : subregions) {
			
			g.drawImage(
					base.getSubimage((int)region.getX(), (int)region.getY(),
							(int)region.getWidth(), 
							(int)region.getHeight()),
					(int)region.getX(), (int)region.getY(), 
					null);
			
		}
		
		//definitely does something
		g.dispose();
		
		group.put(name, subImage);
		
	}
}
