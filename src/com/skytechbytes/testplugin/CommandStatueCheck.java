package com.skytechbytes.testplugin;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;

import com.skytechbytes.builder.FaceBuilder;
import com.skytechbytes.builder.StatueMaker;
/**
 * 
 * @author SkyTechBytes
 * Thank you SparklingComet for the mojang api
 *
 */
public class CommandStatueCheck implements CommandExecutor {
	
	private Map<String,BufferedImage> cache = new HashMap<>();

	public CommandStatueCheck() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player p = (Player) arg0;

			if (!p.hasPermission("playerstatuecreatorx.createStatue")) {
				arg0.sendMessage(ChatColor.RED + "Insufficient permissions.");
				return true;
			}
			
			try {
				
				if (arg3.length >= 3) {
					if (arg3[2].equals("xy")) {
						FaceBuilder.master_orientation = 0;
					} else if (arg3[2].equals("xz")) {
						FaceBuilder.master_orientation = 2;
					} else if (arg3[2].equals("yz")) {
						FaceBuilder.master_orientation = 1;
					} else {
						throw new Exception();
					}
				} else {
					FaceBuilder.master_orientation = 0;
				}
				
				Mojang api = new Mojang().connect();
				api.connect();
				String name = p.getName();
				
				if (arg3.length >= 1) {
					name = arg3[0];
					
				}
				
				BufferedImage bi = null;
				if (!cache.containsKey(name)) {
					PlayerProfile pp = api.getPlayerProfile(api.getUUIDOfUsername(name));
	
					Optional<URL> URL  = pp.getTextures().get().getSkin();
	
					bi = ImageIO.read(URL.get());
					
					cache.put(name, bi);
				} else {
					bi = cache.get(name);
				}
				
				
				
				if (arg3.length >= 2) {
					if (arg3[1].equals("slim")) {
						new StatueMaker(p.getWorld(),p,"slim",bi,true).runTask(PlayerStatuePlugin.instance);
					} else if (arg3[1].equals("legacy")) {
						new StatueMaker(p.getWorld(),p,"legacy",bi,true).runTask(PlayerStatuePlugin.instance);
					} else if (arg3[1].equals("default")) {
						new StatueMaker(p.getWorld(),p,"default",bi,true).runTask(PlayerStatuePlugin.instance);
					} else {
						throw new Exception();
					}
				} else {
					new StatueMaker(p.getWorld(),p,"default",bi,true).runTask(PlayerStatuePlugin.instance);
				}

			} catch (Exception e) {
				arg0.sendMessage(ChatColor.RED + "Invalid arguments or the player requested does not exist! Usage: /statue <Username> [default|slim|legacy] [xy|xz|yz]");
				//e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
