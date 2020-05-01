package com.skytechbytes.testplugin;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skytechbytes.builder.C;
import com.skytechbytes.builder.ColorMaps;
import com.skytechbytes.builder.FaceBuilder;
import com.skytechbytes.builder.StatueMaker;
/**
 * 
 * @author SkyTechBytes
 * Thank you SparklingComet for the mojang api (No longer used, but still, it was quite useful!)
 *
 */
public class CommandStatue implements CommandExecutor {
	
	public static Map<String,BufferedImage> cache = new HashMap<>();

	public CommandStatue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player p = (Player) arg0;

			if (!p.hasPermission("playerstatuebuilderx.createStatue")) {
				arg0.sendMessage(ChatColor.RED + "Insufficient permissions.");
				return false;
			}
			
			List<String> flags = Arrays.asList(arg3);
			
			try {
				p.sendMessage(ChatColor.YELLOW + "Crunching numbers... please wait.");
				/*
				 * Special orientation flags
				 */
				if (flags.contains("xy") || flags.contains("xz") || flags.contains("yz")) {
					if (!p.hasPermission("playerstatuebuilderx.specialOrientations")) {
						p.sendMessage(ChatColor.RED + "You are not allowed to issue this command with xy|xz|yz. Omit that term and run it again.");
						throw new Exception("Insufficient Permissions");
					}
					if (flags.contains("xy")) {
						FaceBuilder.master_orientation = 0;
					} else if (flags.contains("xz")) {
						FaceBuilder.master_orientation = 2;
					} else if (flags.contains("yz")) {
						FaceBuilder.master_orientation = 1;
					} 
				} else {
					FaceBuilder.master_orientation = 0;
				}
				
				
				BufferedImage bi = Util.getSkinImage(p, arg3);
				
				
				/*
				 * Types of blocks flags
				 */
				
				ColorMaps.getActiveColorMaps().clear();
				
				if (flags.contains("wool")) ColorMaps.getActiveColorMaps().add(C.WOOL);
				
				if (flags.contains("planks")) ColorMaps.getActiveColorMaps().add(C.PLANKS);
				
				if (flags.contains("terracotta")) ColorMaps.getActiveColorMaps().add(C.TERRACOTTA);
				
				if (flags.contains("concrete")) ColorMaps.getActiveColorMaps().add(C.CONCRETE);
				
				if (flags.contains("glass")) ColorMaps.getActiveColorMaps().add(C.GLASS);
				
				if (flags.contains("gray")) ColorMaps.getActiveColorMaps().add(C.GRAY);
				
				if (ColorMaps.getActiveColorMaps().size() == 0) {
					ColorMaps.getActiveColorMaps().add(C.WOOL);
					ColorMaps.getActiveColorMaps().add(C.PLANKS);
					ColorMaps.getActiveColorMaps().add(C.TERRACOTTA);
					ColorMaps.getActiveColorMaps().add(C.CONCRETE);
				}
				
				/*
				 * Type of skin flags
				 */
				if (flags.contains("slim") || flags.contains("legacy") || flags.contains("default")) {
					if (flags.contains("slim")) {
						new StatueMaker(p.getWorld(),p,"slim",bi,false,flags).runTask(PlayerStatuePlugin.instance);
					} else if (flags.contains("legacy")) {
						new StatueMaker(p.getWorld(),p,"legacy",bi,false,flags).runTask(PlayerStatuePlugin.instance);
					} else if (flags.contains("default")) {
						new StatueMaker(p.getWorld(),p,"default",bi,false,flags).runTask(PlayerStatuePlugin.instance);
					}
				} else {
					new StatueMaker(p.getWorld(),p,"default",bi,false,flags).runTask(PlayerStatuePlugin.instance);
				}

			} catch (Exception e) {
				arg0.sendMessage(ChatColor.RED + "Error! " + e.getMessage());

				return false;
			}
			return true;
		}
		return false;
	}

}
