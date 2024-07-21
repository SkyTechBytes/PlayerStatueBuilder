package com.skytechbytes.playerstatuebuilder;

import java.awt.image.BufferedImage;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;
import com.skytechbytes.playerstatuebuilder.builder.StatueMaker;

public class StatueBuildTask extends BukkitRunnable {
	private final String identifier;
	private final StatueMaker callback;
	private final CommandSender sender;
	public StatueBuildTask(String identifier, StatueMaker callback, CommandSender sender) {
		this.identifier = identifier;
		this.callback = callback;
		this.sender = sender;
	}


	@Override
	public void run() {
		try {
			BufferedImage bi = ImageUtil.deepCopy(Util.getSkinImage(identifier));
			
			callback.setImage(bi);

			callback.runTask(PlayerStatueBuilder.instance);
		} catch (Exception e) {
			Log.log(e.getMessage());
			if (sender != null) {
				sender.sendMessage(ChatColor.RED + "Error! " + e.getMessage());
			}
		}
	}

}
