package com.skytechbytes.playerstatuebuilder;

import com.skytechbytes.playerstatuebuilder.builder.Schematic;
import com.skytechbytes.playerstatuebuilder.builder.SchematicUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author SkyTechBytes
 *
 */
public class CommandUndostatue implements CommandExecutor {
	public CommandUndostatue() {
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
		try {
			if (!sender.hasPermission("playerstatuebuilderx.undo")) {
				throw new PlayerStatueBuilderException("Insufficient permissions.");
			}

			Schematic s = null;

			if (Schematic.history.size() > 0) {
				s = Schematic.history.pop();
			}

			if (s == null) {
				throw new PlayerStatueBuilderException("There is no statue to undo right now.");
			}

			if (sender instanceof Player p) {
				boolean canBuild = SchematicUtil.canBuild(s, p);
				if (!canBuild) {
					throw new PlayerStatueBuilderException("Insufficient build permissions. That statue is in a protected location!");
				}

				s.createSchematic(true, !p.hasPermission("playerstatuebuilderx.override"));
			} else if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
				s.createSchematic(true, !sender.hasPermission("playerstatuebuilderx.override"));
			}
			sender.sendMessage(ChatColor.GREEN + "Undo successful.");
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
