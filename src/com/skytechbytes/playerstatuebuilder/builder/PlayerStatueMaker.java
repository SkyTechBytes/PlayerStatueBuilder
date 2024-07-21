package com.skytechbytes.playerstatuebuilder.builder;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.skytechbytes.playerstatuebuilder.StatueArgs;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.skytechbytes.playerstatuebuilder.Log;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;
/**
 * 
 * @author SkyTechBytes
 * This is where the magic happens- all the mappings!
 *
 */
public class PlayerStatueMaker extends StatueMaker {
	
	public static HashMap<String,Long> cooldowns = new HashMap<>();
	
	private final Player p;
	private final boolean quote;

	public PlayerStatueMaker(Player p, String mode, BufferedImage bi, boolean quote, StatueArgs params) {
		super( new Location(
				p.getLocation().getWorld(), 
				p.getLocation().getBlockX(), 
				p.getLocation().getBlockY() + 3, 
				p.getLocation().getBlockZ()), 
			   getDirection(p.getLocation().getYaw()), mode, bi, params);

		this.p = p;
		this.quote = quote;
	}
	/**
	 * Converts a rotation to a cardinal direction name.
	 * From sk89qs's command book plugin
	 * @param rot rotation
	 * @return direction, like North, East, South, or West
	 */
	private static String getDirection(double rot) {
		if (rot < 0) {
			rot += 360;
		}
		if (0 <= rot && rot < 45) {
			return "North";
		} else if (45 <= rot && rot < 135) {
			return "East";
		}else if (135 <= rot && rot < 225) {
			return "South";
		} else if (225 <= rot && rot < 315) {
			return "West";
		} else if (315 <= rot && rot <= 360.0) {
			return "North";
		} else {
			Log.log("The Player's direction is somehow negative or greater than 360. Might want to report this.");
			return "North";
		}
		
	}
	public boolean hasCooldown(Player p) {
		if (p.hasPermission("playerstatuebuilderx.noWait")) {
			return false;
		}
		if (cooldowns.get(p.getName()) == null) {
			return false;
		}
		//cooldown has passed
		if (cooldowns.get(p.getName()) < System.currentTimeMillis()) {
			return false;
		}
		//millis
		long timeLeft =  cooldowns.get(p.getName()) - System.currentTimeMillis();
		
		long min = timeLeft / 60000;
		long sec = (timeLeft % 60000)/1000;
		
		p.sendMessage(ChatColor.RED + "You've created a statue recently. Please wait at least " + min + " minutes and " + sec + " seconds.");
		return true;
	}
	@Override
	protected void generateStatueSchematic() throws Exception {
		/*
		 * Special orientation flags
		 */
		if (getParams().hasFlag("xy") || getParams().hasFlag("xz") || getParams().hasFlag("yz")) {
			if (!p.hasPermission("playerstatuebuilderx.specialOrientations")) {
				p.sendMessage(ChatColor.RED + "You are not allowed to issue this command with xy|xz|yz. Omit that term and run it again.");
				throw new Exception("Insufficient Permissions");
			}
		}
		
		super.generateStatueSchematic();
	}
	/**
	 * Actually make the statue from the sketchpad
	 */
	@Override
	protected void createStatue() {

		if (quote) {
			SchematicUtil.query(this.getSchematic(), p);
			return;
		}

		boolean canBuild = SchematicUtil.canBuild(this.getSchematic(), p);

		if (!canBuild) {
			p.sendMessage(ChatColor.RED + "Insufficient build permissions. Move to a place where you're allowed to build.");
			return;
		}
		
		if (hasCooldown(p)) {
			return;
		}
		
		Log.log("Trying to remove items...");
		boolean takeMaterials = SchematicUtil.removeItemsOrAlert(this.getSchematic(), p);
		
		if (!takeMaterials) {
			Log.log("Insufficient materials");
			return;
		}

		Log.log("Creating Structure...");

		getSchematic().createSchematic(false, !p.hasPermission("playerstatuebuilderx.override"));

		cooldowns.put(p.getName(), System.currentTimeMillis() + PlayerStatueBuilder.instance.getConfig().getInt("cooldown") * 60000L);
		
		p.sendMessage(ChatColor.GREEN + "Statue Created!");

	}
	@Override
	public void run() {
		
		try {
			if (this.getImage() == null) {
				throw new Exception("Failed to obtain that player's skin. Please check spelling or try again later.");
			}
			super.generateStatueSchematic();
			this.createStatue();
		} catch (Exception e) {
			e.printStackTrace();
			p.sendMessage(ChatColor.RED + "Error! " + e.getMessage());
		}
	}
	
	
}
