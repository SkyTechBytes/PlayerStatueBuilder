package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.builder.PlayerStatueMaker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author SkyTechBytes
 *
 */
public class CommandStatue implements CommandExecutor {

	public CommandStatue() {
	}

	@Override
	public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		if (arg0 instanceof Player p) {

			if (!p.hasPermission("playerstatuebuilderx.createStatue")) {
				arg0.sendMessage(ChatColor.RED + "Insufficient permissions.");
				return true;
			}
			
			List<String> flags = Arrays.asList(arg3);
			
			try {
				p.sendMessage(ChatColor.YELLOW + "Crunching numbers... please wait.");
				
				String name = p.getName();
				
				if (arg3.length >= 1) {
					name = arg3[0];
					
				}
				if (Util.isDiskSkin(name)) {
					if (!p.hasPermission("playerstatuebuilderx.custom")) {
						throw new Exception("Insufficient permissions to create custom statues!");
					}
				}
				
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
						new StatueBuildTask(name, new PlayerStatueMaker(p,"slim",null,false,params)).runTaskAsynchronously(PlayerStatueBuilder.instance);
					} else if (flags.contains("legacy")) {
						new StatueBuildTask(name, new PlayerStatueMaker(p,"legacy",null,false,params)).runTaskAsynchronously(PlayerStatueBuilder.instance);
					} else if (flags.contains("default")) {
						new StatueBuildTask(name, new PlayerStatueMaker(p,"default",null,false,params)).runTaskAsynchronously(PlayerStatueBuilder.instance);
					}
				} else {
					new StatueBuildTask(name, new PlayerStatueMaker(p,"default",null,false,params)).runTaskAsynchronously(PlayerStatueBuilder.instance);
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
