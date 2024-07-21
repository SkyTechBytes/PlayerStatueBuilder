package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;
import com.skytechbytes.playerstatuebuilder.builder.StatueMaker;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.image.BufferedImage;

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

		} catch (PlayerStatueBuilderException psbe) {
			if (sender != null) {
				sender.sendMessage(ChatColor.RED + "Error! " + psbe.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (sender != null) {
				sender.sendMessage(ChatColor.RED + "Error! " + e.getMessage());
			}
		}
	}

}
