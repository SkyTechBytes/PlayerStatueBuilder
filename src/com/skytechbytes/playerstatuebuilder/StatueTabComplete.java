package com.skytechbytes.playerstatuebuilder;

import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StatueTabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
		if (sender instanceof Player p) {
			if (command.getName().equalsIgnoreCase("statue")) {
				if (args.length > 1) {
					List<String> list = Lists.asList("", args);
					HashSet<String> hs = new HashSet<>(list);
					HashSet<String> a = new HashSet<>();
					a.add("glass");
					a.add("concrete");
					a.add("terracotta");
					a.add("planks");
					a.add("gray");
					a.add("wool");
					a.add("all");
					a.add("slim");
					a.add("legacy");
					a.add("default");
					a.add("hue:");
					a.add("contrast:");
					a.add("posterize:");
					a.add("saturation:");
					a.add("brightness:");
					a.add("iron_armor");
					a.add("diamond_armor");
					a.add("chainmail_armor");
					a.add("golden_armor");
					a.add("quote");

					a.add("head");
					a.add("body");
					a.add("left_arm");
					a.add("right_arm");
					a.add("left_leg");
					a.add("right_leg");

					if (p.hasPermission("playerstatuebuilderx.specialOrientations")) {
						a.add("xy");
						a.add("xz");
						a.add("yz");
					}
					//Remove auto complete options we already used
					for (String param : hs) {
						a.remove(param);
					}
					return new ArrayList<>(a);
				}
			}
		}
		return null;
	}

}
