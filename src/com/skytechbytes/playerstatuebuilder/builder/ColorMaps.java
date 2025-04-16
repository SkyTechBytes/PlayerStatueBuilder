package com.skytechbytes.playerstatuebuilder.builder;

import org.bukkit.Material;

import java.awt.*;
import java.util.List;
import java.util.*;
/**
 * 
 * @author SkyTechBytes
 * The wool color codes are from the wiki, and the other color codes I did painstakingly by hand.
 * If you use these, please credit thx
 *
 */
public class ColorMaps {
	static Map<Color,Material> x = new HashMap<>();
	static Map<Color,Material> p = new HashMap<>();
	static Map<Color,Material> t = new HashMap<>();
	static Map<Color,Material> c = new HashMap<>();
	static Map<Color,Material> g = new HashMap<>();
	static Map<Color,Material> w = new HashMap<>();
	static Map<Color,Material> a = new HashMap<>();
	
	static List<Map<Color,Material>> colorMaps = new ArrayList<>();
	
	static List<Integer> activeColorMaps = new ArrayList<>();

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
		p.put(new Color(184,101,53),Material.ACACIA_PLANKS);
		p.put(new Color(131, 68, 50), Material.MANGROVE_PLANKS);
		
		/*
		p.put(new Color(214, 189, 85), Material.BAMBOO_PLANKS);
		p.put(new Color(222, 161, 154), Material.CHERRY_PLANKS);
		p.put(new Color(126, 54, 81), Material.CRIMSON_PLANKS);
		p.put(new Color(50, 134, 132), Material.WARPED_PLANKS);
		*/
		
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
		
		//https://github.com/AxoSpyeyes/skin2sta2/blob/main/skintostatue.py
		
		a.put(new Color(168, 90, 50), Material.ACACIA_PLANKS);
		a.put(new Color(103, 96, 86), Material.ACACIA_WOOD);
		a.put(new Color(136, 136, 136), Material.ANDESITE);
		a.put(new Color(192, 175, 121), Material.BIRCH_PLANKS);
		a.put(new Color(216, 215, 210), Material.BIRCH_WOOD);
		a.put(new Color(42, 35, 40), Material.BLACKSTONE);
		a.put(new Color(8, 10, 15), Material.BLACK_CONCRETE);
		a.put(new Color(67, 30, 32), Material.BLACK_GLAZED_TERRACOTTA);
		a.put(new Color(37, 22, 16), Material.BLACK_TERRACOTTA);
		a.put(new Color(20, 21, 25), Material.BLACK_WOOL);
		a.put(new Color(44, 46, 143), Material.BLUE_CONCRETE);
		a.put(new Color(47, 64, 139), Material.BLUE_GLAZED_TERRACOTTA);
		a.put(new Color(116, 167, 253), Material.BLUE_ICE);
		a.put(new Color(74, 59, 91), Material.BLUE_TERRACOTTA);
		a.put(new Color(53, 57, 157), Material.BLUE_WOOL);
		a.put(new Color(150, 97, 83), Material.BRICKS);
		a.put(new Color(96, 59, 31), Material.BROWN_CONCRETE);
		a.put(new Color(119, 106, 85), Material.BROWN_GLAZED_TERRACOTTA);
		a.put(new Color(149, 111, 81), Material.BROWN_MUSHROOM_BLOCK);
		a.put(new Color(77, 51, 35), Material.BROWN_TERRACOTTA);
		a.put(new Color(114, 71, 40), Material.BROWN_WOOL);
		a.put(new Color(47, 23, 28), Material.CHISELED_NETHER_BRICKS);
		a.put(new Color(53, 48, 56), Material.CHISELED_POLISHED_BLACKSTONE);
		a.put(new Color(231, 226, 218), Material.CHISELED_QUARTZ_BLOCK);
		a.put(new Color(183, 96, 27), Material.CHISELED_RED_SANDSTONE);
		a.put(new Color(216, 202, 155), Material.CHISELED_SANDSTONE);
		a.put(new Color(119, 118, 119), Material.CHISELED_STONE_BRICKS);
		a.put(new Color(160, 166, 179), Material.CLAY);
		a.put(new Color(16, 15, 15), Material.COAL_BLOCK);
		a.put(new Color(116, 116, 116), Material.COAL_ORE);
		a.put(new Color(119, 85, 59), Material.COARSE_DIRT);
		a.put(new Color(127, 127, 127), Material.COBBLESTONE);
		a.put(new Color(40, 20, 23), Material.CRACKED_NETHER_BRICKS);
		a.put(new Color(43, 37, 43), Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
		a.put(new Color(118, 117, 118), Material.CRACKED_STONE_BRICKS);
		a.put(new Color(92, 25, 29), Material.CRIMSON_HYPHAE);
		a.put(new Color(101, 48, 70), Material.CRIMSON_PLANKS);
		a.put(new Color(32, 10, 60), Material.CRYING_OBSIDIAN);
		a.put(new Color(21, 119, 136), Material.CYAN_CONCRETE);
		a.put(new Color(52, 118, 125), Material.CYAN_GLAZED_TERRACOTTA);
		a.put(new Color(86, 91, 91), Material.CYAN_TERRACOTTA);
		a.put(new Color(21, 137, 145), Material.CYAN_WOOL);
		a.put(new Color(66, 43, 20), Material.DARK_OAK_PLANKS);
		a.put(new Color(60, 46, 26), Material.DARK_OAK_WOOD);
		a.put(new Color(51, 91, 75), Material.DARK_PRISMARINE);
		a.put(new Color(124, 117, 114), Material.DEAD_BRAIN_CORAL_BLOCK);
		a.put(new Color(131, 123, 119), Material.DEAD_BUBBLE_CORAL_BLOCK);
		a.put(new Color(131, 123, 119), Material.DEAD_FIRE_CORAL_BLOCK);
		a.put(new Color(133, 126, 122), Material.DEAD_HORN_CORAL_BLOCK);
		a.put(new Color(130, 123, 119), Material.DEAD_TUBE_CORAL_BLOCK);
		a.put(new Color(98, 237, 228), Material.DIAMOND_BLOCK);
		a.put(new Color(125, 142, 141), Material.DIAMOND_ORE);
		a.put(new Color(188, 188, 188), Material.DIORITE);
		a.put(new Color(134, 96, 67), Material.DIRT);
		a.put(new Color(42, 203, 87), Material.EMERALD_BLOCK);
		a.put(new Color(117, 136, 124), Material.EMERALD_ORE);
		a.put(new Color(219, 222, 158), Material.END_STONE);
		a.put(new Color(218, 224, 162), Material.END_STONE_BRICKS);
		a.put(new Color(140, 149, 151, 64), Material.GLASS);
		a.put(new Color(246, 208, 61), Material.GOLD_BLOCK);
		a.put(new Color(143, 140, 125), Material.GOLD_ORE);
		a.put(new Color(149, 103, 85), Material.GRANITE);
		a.put(new Color(54, 57, 61), Material.GRAY_CONCRETE);
		a.put(new Color(83, 90, 93), Material.GRAY_GLAZED_TERRACOTTA);
		a.put(new Color(57, 42, 35), Material.GRAY_TERRACOTTA);
		a.put(new Color(62, 68, 71), Material.GRAY_WOOL);
		a.put(new Color(73, 91, 36), Material.GREEN_CONCRETE);
		a.put(new Color(117, 142, 67), Material.GREEN_GLAZED_TERRACOTTA);
		a.put(new Color(76, 83, 42), Material.GREEN_TERRACOTTA);
		a.put(new Color(84, 109, 27), Material.GREEN_WOOL);
		a.put(new Color(229, 148, 29), Material.HONEYCOMB_BLOCK);
		a.put(new Color(145, 183, 253, 190), Material.ICE);
		a.put(new Color(220, 220, 220), Material.IRON_BLOCK);
		a.put(new Color(136, 130, 127), Material.IRON_ORE);
		a.put(new Color(160, 115, 80), Material.JUNGLE_PLANKS);
		a.put(new Color(85, 67, 25), Material.JUNGLE_WOOD);
		a.put(new Color(30, 67, 140), Material.LAPIS_BLOCK);
		a.put(new Color(99, 110, 132), Material.LAPIS_ORE);
		a.put(new Color(35, 137, 198), Material.LIGHT_BLUE_CONCRETE);
		a.put(new Color(94, 164, 208), Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
		a.put(new Color(113, 108, 137), Material.LIGHT_BLUE_TERRACOTTA);
		a.put(new Color(58, 175, 217), Material.LIGHT_BLUE_WOOL);
		a.put(new Color(125, 125, 115), Material.LIGHT_GRAY_CONCRETE);
		a.put(new Color(144, 166, 167), Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
		a.put(new Color(135, 106, 97), Material.LIGHT_GRAY_TERRACOTTA);
		a.put(new Color(142, 142, 134), Material.LIGHT_GRAY_WOOL);
		a.put(new Color(94, 168, 24), Material.LIME_CONCRETE);
		a.put(new Color(162, 197, 55), Material.LIME_GLAZED_TERRACOTTA);
		a.put(new Color(103, 117, 52), Material.LIME_TERRACOTTA);
		a.put(new Color(112, 185, 25), Material.LIME_WOOL);
		a.put(new Color(169, 48, 159), Material.MAGENTA_CONCRETE);
		a.put(new Color(208, 100, 191), Material.MAGENTA_GLAZED_TERRACOTTA);
		a.put(new Color(149, 88, 108), Material.MAGENTA_TERRACOTTA);
		a.put(new Color(189, 68, 179), Material.MAGENTA_WOOL);
		a.put(new Color(114, 146, 30), Material.MELON);
		a.put(new Color(110, 118, 94), Material.MOSSY_COBBLESTONE);
		a.put(new Color(115, 121, 105), Material.MOSSY_STONE_BRICKS);
		a.put(new Color(66, 61, 63), Material.NETHERITE_BLOCK);
		a.put(new Color(97, 38, 38), Material.NETHERRACK);
		a.put(new Color(44, 21, 26), Material.NETHER_BRICKS);
		a.put(new Color(115, 54, 42), Material.NETHER_GOLD_ORE);
		a.put(new Color(117, 65, 62), Material.NETHER_QUARTZ_ORE);
		a.put(new Color(114, 2, 2), Material.NETHER_WART_BLOCK);
		a.put(new Color(88, 58, 40), Material.NOTE_BLOCK);
		a.put(new Color(162, 130, 78), Material.OAK_PLANKS);
		a.put(new Color(109, 85, 50), Material.OAK_WOOD);
		a.put(new Color(15, 10, 24), Material.OBSIDIAN);
		a.put(new Color(224, 97, 0), Material.ORANGE_CONCRETE);
		a.put(new Color(154, 147, 91), Material.ORANGE_GLAZED_TERRACOTTA);
		a.put(new Color(161, 83, 37), Material.ORANGE_TERRACOTTA);
		a.put(new Color(240, 118, 19), Material.ORANGE_WOOL);
		a.put(new Color(141, 180, 250), Material.PACKED_ICE);
		a.put(new Color(213, 101, 142), Material.PINK_CONCRETE);
		a.put(new Color(235, 154, 181), Material.PINK_GLAZED_TERRACOTTA);
		a.put(new Color(161, 78, 78), Material.PINK_TERRACOTTA);
		a.put(new Color(237, 141, 172), Material.PINK_WOOL);
		a.put(new Color(132, 134, 133), Material.POLISHED_ANDESITE);
		a.put(new Color(53, 48, 56), Material.POLISHED_BLACKSTONE);
		a.put(new Color(46, 41, 48), Material.POLISHED_BLACKSTONE_BRICKS);
		a.put(new Color(192, 193, 194), Material.POLISHED_DIORITE);
		a.put(new Color(154, 106, 89), Material.POLISHED_GRANITE);
		a.put(new Color(99, 171, 158), Material.PRISMARINE_BRICKS);
		a.put(new Color(195, 114, 24), Material.PUMPKIN);
		a.put(new Color(100, 31, 156), Material.PURPLE_CONCRETE);
		a.put(new Color(109, 48, 152), Material.PURPLE_GLAZED_TERRACOTTA);
		a.put(new Color(118, 70, 86), Material.PURPLE_TERRACOTTA);
		a.put(new Color(121, 42, 172), Material.PURPLE_WOOL);
		a.put(new Color(169, 125, 169), Material.PURPUR_BLOCK);
		a.put(new Color(171, 128, 171), Material.PURPUR_PILLAR);
		a.put(new Color(236, 230, 223), Material.QUARTZ_BLOCK);
		a.put(new Color(234, 229, 221), Material.QUARTZ_BRICKS);
		a.put(new Color(235, 229, 222), Material.QUARTZ_PILLAR);
		a.put(new Color(175, 24, 5), Material.REDSTONE_BLOCK);
		a.put(new Color(95, 54, 30), Material.REDSTONE_LAMP);
		a.put(new Color(133, 107, 107), Material.REDSTONE_ORE);
		a.put(new Color(142, 32, 32), Material.RED_CONCRETE);
		a.put(new Color(181, 59, 53), Material.RED_GLAZED_TERRACOTTA);
		a.put(new Color(200, 46, 45), Material.RED_MUSHROOM_BLOCK);
		a.put(new Color(69, 7, 9), Material.RED_NETHER_BRICKS);
		a.put(new Color(143, 61, 46), Material.RED_TERRACOTTA);
		a.put(new Color(160, 39, 34), Material.RED_WOOL);
		a.put(new Color(158, 158, 158), Material.SMOOTH_STONE);
		a.put(new Color(249, 254, 254), Material.SNOW_BLOCK);
		a.put(new Color(81, 62, 50), Material.SOUL_SAND);
		a.put(new Color(75, 57, 46), Material.SOUL_SOIL);
		a.put(new Color(195, 192, 74), Material.SPONGE);
		a.put(new Color(58, 37, 16), Material.SPRUCE_WOOD);
		a.put(new Color(114, 84, 48), Material.SPRUCE_PLANKS);
		a.put(new Color(125, 125, 125), Material.STONE);
		a.put(new Color(122, 121, 122), Material.STONE_BRICKS);
		a.put(new Color(174, 92, 59), Material.STRIPPED_ACACIA_WOOD);
		a.put(new Color(196, 176, 118), Material.STRIPPED_BIRCH_WOOD);
		a.put(new Color(137, 57, 90), Material.STRIPPED_CRIMSON_HYPHAE);
		a.put(new Color(96, 76, 49), Material.STRIPPED_DARK_OAK_WOOD);
		a.put(new Color(171, 132, 84), Material.STRIPPED_JUNGLE_WOOD);
		a.put(new Color(177, 144, 86), Material.STRIPPED_OAK_WOOD);
		a.put(new Color(115, 89, 52), Material.STRIPPED_SPRUCE_WOOD);
		a.put(new Color(57, 150, 147), Material.STRIPPED_WARPED_HYPHAE);
		a.put(new Color(152, 94, 67), Material.TERRACOTTA);
		a.put(new Color(58, 58, 77), Material.WARPED_HYPHAE);
		a.put(new Color(43, 104, 99), Material.WARPED_PLANKS);
		a.put(new Color(22, 119, 121), Material.WARPED_WART_BLOCK);
		a.put(new Color(171, 181, 70), Material.WET_SPONGE);
		a.put(new Color(207, 213, 214), Material.WHITE_CONCRETE);
		a.put(new Color(188, 212, 202), Material.WHITE_GLAZED_TERRACOTTA);
		a.put(new Color(209, 178, 161), Material.WHITE_TERRACOTTA);
		a.put(new Color(233, 236, 236), Material.WHITE_WOOL);
		a.put(new Color(240, 175, 21), Material.YELLOW_CONCRETE);
		a.put(new Color(234, 192, 88), Material.YELLOW_GLAZED_TERRACOTTA);
		a.put(new Color(186, 133, 35), Material.YELLOW_TERRACOTTA);
		a.put(new Color(248, 197, 39), Material.YELLOW_WOOL);

		/*
		f.put(new Color(25, 26, 31), Material.BLACK_CONCRETE_POWDER);
		f.put(new Color(125, 84, 53), Material.BROWN_CONCRETE_POWDER);
		f.put(new Color(70, 73, 166), Material.BLUE_CONCRETE_POWDER);
		f.put(new Color(36, 147, 157), Material.CYAN_CONCRETE_POWDER);
		f.put(new Color(131, 127, 126), Material.GRAVEL);
		f.put(new Color(76, 81, 84), Material.GRAY_CONCRETE_POWDER);
		f.put(new Color(97, 119, 44), Material.GREEN_CONCRETE_POWDER);
		f.put(new Color(74, 180, 213), Material.LIGHT_BLUE_CONCRETE_POWDER);
		f.put(new Color(154, 154, 148), Material.LIGHT_GRAY_CONCRETE_POWDER);
		f.put(new Color(125, 189, 41), Material.LIME_CONCRETE_POWDER);
		f.put(new Color(192, 83, 184), Material.MAGENTA_CONCRETE_POWDER);
		f.put(new Color(227, 131, 31), Material.ORANGE_CONCRETE_POWDER);
		f.put(new Color(228, 153, 181), Material.PINK_CONCRETE_POWDER);
		f.put(new Color(131, 55, 177), Material.PURPLE_CONCRETE_POWDER);
		f.put(new Color(168, 54, 50), Material.RED_CONCRETE_POWDER);
		f.put(new Color(190, 102, 33), Material.RED_SAND);
		f.put(new Color(219, 207, 163), Material.SAND);
  		f.put(new Color(225, 227, 227), Material.WHITE_CONCRETE_POWDER);
		f.put(new Color(232, 199, 54), Material.YELLOW_CONCRETE_POWDER);
  		*/

		/*
  		a.put(new Color(229, 176, 168), Material.TARGET);
		*/
		
		colorMaps.add(x);
		colorMaps.add(p);
		colorMaps.add(t);
		colorMaps.add(c);
		colorMaps.add(g);
		colorMaps.add(w);
		colorMaps.add(a);

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
