package com.skytechbytes.support;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.bukkit.BukkitPlayer;
import com.sk89q.worldguard.internal.permission.RegionPermissionModel;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.skytechbytes.testplugin.PlayerStatuePlugin;
/**
 * 
 * From ryanhamshire's GriefProtection Plugin
 * "What's the point in re-inventing the wheel?"
 *
 */
public class WorldGuardWrapper
{
    private WorldGuardPlugin worldGuard = null;

    public WorldGuardWrapper() 
    {
    	try {
    		this.worldGuard = (WorldGuardPlugin)PlayerStatuePlugin.instance.getServer().getPluginManager().getPlugin("WorldGuard");
    		System.out.println("Worldguard detected!");
    	} catch (Exception e) {
    		System.out.println("Worldguard not detected.");
    	}
    }

    public boolean canBuild(Location lesserCorner, Location greaterCorner, Player creatingPlayer)
    {
    	if (worldGuard == null) {
    		return true;
    	} else {
    		System.out.println("Checking permissions");
    	}
        try
        {
            BukkitPlayer localPlayer = new BukkitPlayer(this.worldGuard, creatingPlayer);
            World world = WorldGuard.getInstance().getPlatform().getMatcher().getWorldByName(lesserCorner.getWorld().getName());

            if(new RegionPermissionModel(localPlayer).mayIgnoreRegionProtection(world)) return true;

            RegionManager manager =  WorldGuard.getInstance().getPlatform().getRegionContainer().get(world);

            if(manager != null)
            {
                ProtectedCuboidRegion tempRegion = new ProtectedCuboidRegion(
                        "GP_TEMP",
                        BlockVector3.at(lesserCorner.getX(), 0, lesserCorner.getZ()),
                        BlockVector3.at(greaterCorner.getX(), world.getMaxY(), greaterCorner.getZ()));

                ApplicableRegionSet overlaps = manager.getApplicableRegions(tempRegion);
                for (ProtectedRegion r : overlaps.getRegions()) {
                    if (!manager.getApplicableRegions(r).testState(localPlayer, Flags.BUILD)) {
                    	System.out.println("Can't Build!");
                        return false;
                    }
                }
                return true;
            }

            return true;
        }
        catch (Throwable shoe)
        {
        	shoe.printStackTrace();
        }
		return false;

    }
}