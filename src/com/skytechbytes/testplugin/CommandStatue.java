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
import com.skytechbytes.builder.Splicer;
/**
 * 
 * @author SkyTechBytes
 * Thank you SparklingComet for the mojang api
 *
 */
public class CommandStatue implements CommandExecutor {
	
	private Map<String,BufferedImage> cache = new HashMap<>();
	
	public CommandStatue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player p = (Player) arg0;

			if (!p.isOp()) return false;
			
			try {
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
				
				if (arg3.length >= 2) {
					if (arg3[1].equals("slim")) {
						new Splicer().makeSlimStatue(p.getLocation(),bi);
					} else if (arg3[1].equals("legacy")) {
						new Splicer().makeLegacyStatue(p.getLocation(),bi);
					} else if (arg3[1].equals("default")) {
						new Splicer().makeStatue(p.getLocation(),bi);
					} else {
						throw new Exception();
					}
				} else {
					new Splicer().makeStatue(p.getLocation(),bi);
				}

			} catch (Exception e) {
				arg0.sendMessage(ChatColor.RED + "Invalid arguments or the player requested does not exist! Usage: /statue <Username> [default|slim|legacy] [xy|xz|yz]");
			}
			return true;
		}
		return false;
	}

}
