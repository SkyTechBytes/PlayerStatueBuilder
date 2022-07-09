package com.skytechbytes.testplugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class StatueTabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
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
					a.add("slim");
					a.add("legacy");
					a.add("default");
					a.add("hue:");
					a.add("iron_armor");
					a.add("diamond_armor");
					a.add("chainmail_armor");
					a.add("golden_armor");
					if (p.hasPermission("playerstatuebuilderx.specialOrientations")) {
						a.add("xy");
						a.add("xz");
						a.add("yz");
					}
					//Remove auto complete options we already used
					for (String param : hs) {
						a.remove(param);
					}
					return new ArrayList<String>(a);
				}
			}
		}
		return null;
	}

}
