package com.skytechbytes.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.skytechbytes.builder.Schematic;
import com.skytechbytes.builder.SchematicUtil;
/**
 * 
 * @author SkyTechBytes
 *
 */
public class CommandUndostatue implements CommandExecutor {
	public CommandUndostatue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player p = (Player) arg0;

			if (!p.hasPermission("playerstatuebuilderx.undo")) {
				arg0.sendMessage(ChatColor.RED + "Insufficient permissions.");
				return false;
			}
			
			try {
				Schematic s = null;
				
				if (Schematic.history.size() > 0) {
					s = Schematic.history.pop();
				}
				
				if (s == null) {
					throw new Exception("There is no statue to undo right now.");
				}
				boolean canBuild = SchematicUtil.canBuild(s, p);

				if (canBuild == false) {
					throw new Exception("Insufficient build permissions. That statue is in a protected location!");
				}
				
				s.createSchematic(true, p.hasPermission("playerstatuebuilderx.override") == false);
				
				arg0.sendMessage(ChatColor.GREEN + "Undo successful.");
				
			} catch (Exception e) {
				arg0.sendMessage(ChatColor.RED + "Error! " + e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}

}
