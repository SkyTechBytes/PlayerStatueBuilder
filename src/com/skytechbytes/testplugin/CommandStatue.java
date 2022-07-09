package com.skytechbytes.testplugin;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skytechbytes.builder.PlayerStatueMaker;
/**
 * 
 * @author SkyTechBytes
 * Thank you SparklingComet for the mojang api (No longer used, but still, it was quite useful!)
 *
 */
public class CommandStatue implements CommandExecutor {
	
	public static Map<String,BufferedImage> cache = new HashMap<>();

	public CommandStatue() {
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
				
				
				BufferedImage bi = Util.getSkinImage(p, arg3);
				/*
				 * Type of skin flags
				 */
				LinkedHashMap<String, Float> params = new LinkedHashMap<>();
				/*
				 * Tokenize flags with the format TAG:VALUE <-- Float values only!
				 */
				for (String flagToken : flags) {
					String[] tokenized = flagToken.split(":");
					if (tokenized.length == 2) {
						try {
							params.put(tokenized[0], Float.parseFloat(tokenized[1]));
						} catch (Exception e) {
							throw new Exception("Invalid non-number parameter value after ':': " + tokenized[1]);
						}
					} else if (tokenized.length == 0){
						throw new Exception("Invalid ':' parameter");
					} else {
						params.put(tokenized[0], 0f);
					}
				}
				if (flags.contains("slim") || flags.contains("legacy") || flags.contains("default")) {
					if (flags.contains("slim")) {
						new PlayerStatueMaker(p,"slim",bi,false,params).runTask(PlayerStatuePlugin.instance);
					} else if (flags.contains("legacy")) {
						new PlayerStatueMaker(p,"legacy",bi,false,params).runTask(PlayerStatuePlugin.instance);
					} else if (flags.contains("default")) {
						new PlayerStatueMaker(p,"default",bi,false,params).runTask(PlayerStatuePlugin.instance);
					}
				} else {
					new PlayerStatueMaker(p,"default",bi,false,params).runTask(PlayerStatuePlugin.instance);
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
