package com.skytechbytes.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
/**
 * 
 * @author SkyTechBytes
 * The wool color codes are from the wiki, and the other color codes I did painstakingly by hand.
 *
 */
public class ColorMaps {
	static Map<Color,Material> x = new HashMap<Color,Material>();
	static Map<Color,Material> p = new HashMap<Color,Material>();
	static Map<Color,Material> t = new HashMap<Color,Material>();
	static Map<Color,Material> c = new HashMap<Color,Material>();
	static Map<Color,Material> g = new HashMap<Color,Material>();
	static Map<Color,Material> w = new HashMap<Color,Material>();
	
	static List<Map<Color,Material>> colorMaps = new ArrayList<>();
	
	static List<Integer> activeColorMaps = new ArrayList<>();

	static Map<Integer,Material> qMap = new HashMap<Integer,Material>();
	public ColorMaps() {

	}
	static {
		x.put(new Color(0xE9ECEC), Material.WHITE_WOOL);
		x.put(new Color(0xF07613), Material.ORANGE_WOOL);
		x.put(new Color(0xBD4483), Material.MAGENTA_WOOL);
		x.put(new Color(0x3AAFD9), Material.LIGHT_BLUE_WOOL);
		x.put(new Color(0xF8C627), Material.YELLOW_WOOL);
		x.put(new Color(0x70B919), Material.LIME_WOOL);
		x.put(new Color(0xED8DAC), Material.PINK_WOOL);
		x.put(new Color(0x3E4447), Material.GRAY_WOOL);
		x.put(new Color(0x8E8E86), Material.LIGHT_GRAY_WOOL);
		x.put(new Color(0x158991), Material.CYAN_WOOL);
		x.put(new Color(0x35399D), Material.PURPLE_WOOL);
		x.put(new Color(0x35399D), Material.BLUE_WOOL);
		x.put(new Color(0x724728), Material.BROWN_WOOL);
		x.put(new Color(0x546D1B), Material.GREEN_WOOL);
		x.put(new Color(0xA12722), Material.RED_WOOL);
		x.put(new Color(0x141519), Material.BLACK_WOOL);

		p.put(new Color(157,130,72),Material.OAK_PLANKS);
		p.put(new Color(184,167,113),Material.BIRCH_PLANKS);
		p.put(new Color(126,91,48),Material.SPRUCE_PLANKS);
		p.put(new Color(175,126,89),Material.JUNGLE_PLANKS);
		p.put(new Color(38,19,2),Material.DARK_OAK_PLANKS);
		p.put(new Color(184,101,53),Material.OAK_PLANKS);

		t.put(new Color(147,88,61),Material.TERRACOTTA);
		t.put(new Color(72,57,89),Material.BLUE_TERRACOTTA);
		t.put(new Color(73,45,27),Material.BROWN_TERRACOTTA);
		t.put(new Color(71,78,34),Material.GREEN_TERRACOTTA);
		t.put(new Color(139,53,39),Material.RED_TERRACOTTA);
		t.put(new Color(27,12,4),Material.BLACK_TERRACOTTA);
		t.put(new Color(210,180,161),Material.WHITE_TERRACOTTA);
		t.put(new Color(158,79,27),Material.ORANGE_TERRACOTTA);
		t.put(new Color(151,88,107),Material.MAGENTA_TERRACOTTA);
		t.put(new Color(112,107,136),Material.LIGHT_BLUE_TERRACOTTA);
		t.put(new Color(184,132,25),Material.YELLOW_TERRACOTTA);
		t.put(new Color(98,112,41),Material.LIME_TERRACOTTA);
		t.put(new Color(158,74,73),Material.PINK_TERRACOTTA);
		t.put(new Color(51,32,24),Material.GRAY_TERRACOTTA);
		t.put(new Color(136,105,96),Material.LIGHT_GRAY_TERRACOTTA);
		t.put(new Color(88,88,88),Material.CYAN_TERRACOTTA);
		t.put(new Color(120,68,84),Material.PURPLE_TERRACOTTA);
		
		c.put(new Color(0xd0d6d7), Material.WHITE_CONCRETE);
		c.put(new Color(0xe26100), Material.ORANGE_CONCRETE);
		c.put(new Color(0xaa2da0), Material.MAGENTA_CONCRETE);
		c.put(new Color(0x1e88c6), Material.LIGHT_BLUE_CONCRETE);
		c.put(new Color(0xf3b10d), Material.YELLOW_CONCRETE);
		c.put(new Color(0x5fab12), Material.LIME_CONCRETE);
		c.put(new Color(0xd6648f), Material.PINK_CONCRETE);
		c.put(new Color(0x33373b), Material.GRAY_CONCRETE);
		c.put(new Color(0x7f7f75), Material.LIGHT_GRAY_CONCRETE);
		c.put(new Color(0x0f798a), Material.CYAN_CONCRETE);
		c.put(new Color(0x282a90), Material.BLUE_CONCRETE);
		c.put(new Color(0x64199d), Material.PURPLE_CONCRETE);
		c.put(new Color(0x485b1f), Material.GREEN_CONCRETE);
		c.put(new Color(0x613a1a), Material.BROWN_CONCRETE);
		c.put(new Color(0x901d1d), Material.RED_CONCRETE);
		c.put(new Color(0x010205), Material.BLACK_CONCRETE);
		
		g.put(new Color(0xf7f8f8), Material.WHITE_STAINED_GLASS);
		g.put(new Color(0xdea877), Material.ORANGE_STAINED_GLASS);
		g.put(new Color(0xc78adf), Material.MAGENTA_STAINED_GLASS);
		g.put(new Color(0x99b8df), Material.LIGHT_BLUE_STAINED_GLASS);
		g.put(new Color(0xe7e877), Material.YELLOW_STAINED_GLASS);
		g.put(new Color(0xa7d867), Material.LIME_STAINED_GLASS);
		g.put(new Color(0xf0a8bf), Material.PINK_STAINED_GLASS);
		g.put(new Color(0x898a8a), Material.GRAY_STAINED_GLASS);
		g.put(new Color(0x898a8a), Material.LIGHT_GRAY_STAINED_GLASS);
		g.put(new Color(0x89a8b8), Material.CYAN_STAINED_GLASS);
		g.put(new Color(0xa77ec8), Material.PURPLE_STAINED_GLASS);
		g.put(new Color(0x697cc5), Material.BLUE_STAINED_GLASS);
		g.put(new Color(0x998a77), Material.BROWN_STAINED_GLASS);
		g.put(new Color(0x99a877), Material.GREEN_STAINED_GLASS);
		g.put(new Color(0xa44949), Material.RED_STAINED_GLASS);
		g.put(new Color(0x666767), Material.BLACK_STAINED_GLASS);
		
		w.put(new Color(0xE9ECEC), Material.WHITE_WOOL);
		w.put(new Color(0x3E4447), Material.GRAY_WOOL);
		w.put(new Color(0x141519), Material.BLACK_WOOL);
		w.put(new Color(0x8E8E86), Material.LIGHT_GRAY_WOOL);
		w.put(new Color(210,180,161),Material.WHITE_TERRACOTTA);
		w.put(new Color(51,32,24),Material.GRAY_TERRACOTTA);
		w.put(new Color(136,105,96),Material.LIGHT_GRAY_TERRACOTTA);
		w.put(new Color(88,88,88),Material.CYAN_TERRACOTTA);
		w.put(new Color(27,12,4),Material.BLACK_TERRACOTTA);
		w.put(new Color(0xd0d6d7), Material.WHITE_CONCRETE);
		w.put(new Color(0x33373b), Material.GRAY_CONCRETE);
		w.put(new Color(0x7f7f75), Material.LIGHT_GRAY_CONCRETE);
		w.put(new Color(0x010205), Material.BLACK_CONCRETE);
		w.put(new Color(0x7c7c7c), Material.STONE);
		
		colorMaps.add(x);
		colorMaps.add(p);
		colorMaps.add(t);
		colorMaps.add(c);
		colorMaps.add(g);
		colorMaps.add(w);

	}
	public static Material getMatchingMaterial(int r, int g, int b, int alpha) {
		if (alpha < 255) {
			return Material.AIR;
		}
		Material temp = Material.AIR;
		int smallestDifference = Integer.MAX_VALUE;
		for (int i = 0 ; i < colorMaps.size() ; i++) {
			if (!activeColorMaps.contains(i)) {
				continue;
			}
			Map<Color,Material> x = colorMaps.get(i);
			Set<Color> keys = x.keySet();
			for(Color key: keys){
				int difference = (int) (Math.pow(key.getRed()-r,2) + Math.pow(key.getGreen()-g,2) + Math.pow(key.getBlue()-b,2));
				if (difference < smallestDifference) {
					temp = x.get(key);
					smallestDifference = difference;
				}
			}
		}
		return temp;
	}
	public static List<Integer> getActiveColorMaps() {
		return activeColorMaps;
	}
	
}
