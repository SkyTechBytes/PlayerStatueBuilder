package com.skytechbytes.playerstatuebuilder;

import java.awt.image.BufferedImage;

import org.bukkit.scheduler.BukkitRunnable;

import com.skytechbytes.playerstatuebuilder.builder.ImageUtil;
import com.skytechbytes.playerstatuebuilder.builder.StatueMaker;

public class StatueBuildTask extends BukkitRunnable {
	private String identifier;
	private StatueMaker callback;
	public StatueBuildTask(String identifier, StatueMaker callback) {
		this.identifier = identifier;
		this.callback = callback;
	}
	@Override
	public void run() {
		try {
			BufferedImage bi = ImageUtil.deepCopy(Util.getSkinImage(identifier));
			
			callback.setImage(bi);
		} catch (Exception e) {
			Log.log(e.getMessage());
		}
		callback.runTask(PlayerStatueBuilder.instance);
	}

}
