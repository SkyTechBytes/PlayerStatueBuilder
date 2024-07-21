package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.builder.PlayerStatueMaker;
import com.skytechbytes.playerstatuebuilder.builder.StatueMaker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
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
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		if (!sender.hasPermission("playerstatuebuilderx.createStatue")) {
			sender.sendMessage(ChatColor.RED + "Insufficient permissions.");
			return true;
		}

		List<String> tokens = Arrays.asList(arg3);

		try {
			String name = sender.getName();

			if (arg3.length >= 1) {
				name = arg3[0];
			}
			if (Util.isDiskSkin(name)) {
				if (!sender.hasPermission("playerstatuebuilderx.custom")) {
					throw new PlayerStatueBuilderException("Insufficient permissions to create custom statues!");
				}
			}

			/*
			 * Parse parameters
			 */
			StatueArgs params = new StatueArgs(tokens);

			String mode = "default";
			if (params.hasFlag("slim") || params.hasFlag("legacy") || params.hasFlag("default")) {
				if (params.hasFlag("slim")) {
					mode = "slim";
				} else if (params.hasFlag("legacy")) {
					mode = "legacy";
				} else if (params.hasFlag("default")) {
					mode = "default";
				}
			}

			if (sender instanceof Player p) {
				if (params.isSet("x") || params.isSet("y") || params.isSet("z") || params.isSet("direction") || params.isSet("world")) {
					throw new PlayerStatueBuilderException("Players cannot directly set x:, y:, z:, direction:, or world:");
				}
				sender.sendMessage(ChatColor.YELLOW + "Crunching numbers... please wait.");
				new StatueBuildTask(name, new PlayerStatueMaker(p, mode, null, false, params), p).runTaskAsynchronously(PlayerStatueBuilder.instance);
			} else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
				if (!params.isSet("x") || !params.isSet("y") || !params.isSet("z") || !params.isSet("direction") || !params.isSet("world")) {
					throw new PlayerStatueBuilderException("Console users must pass in x:<x> y:<y> z:<z> direction:[North|South|East|West] world:<name> to the command");
				}
				World selectedWorld = Bukkit.getWorld(params.getWorld());
				if (selectedWorld == null) {
					throw new PlayerStatueBuilderException("Invalid world name");
				}
				Location origin = new Location(selectedWorld, params.getX(), params.getY(), params.getZ());

				new StatueBuildTask(name, new StatueMaker(origin, params.getDirection(), mode, Util.getSkinImage(name), params), sender).runTaskAsynchronously(PlayerStatueBuilder.instance);
			}
		} catch (PlayerStatueBuilderException psbe) {
			sender.sendMessage(ChatColor.RED + "Error! " + psbe.getMessage());
			return false;
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Error! " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
