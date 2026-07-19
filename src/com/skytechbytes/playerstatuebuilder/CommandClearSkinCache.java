package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.lookup.LookupUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author SkyTechBytes
 *
 */
public class CommandClearSkinCache implements CommandExecutor {
	public CommandClearSkinCache() {
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		try {
			if (!sender.hasPermission("playerstatuebuilderx.clearCache")) {
				throw new PlayerStatueBuilderException("Insufficient permissions.");
			}
			LookupUtils.clearSkinCache();
			sender.sendMessage(ChatColor.GREEN + "Cleared skin cache!");
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
