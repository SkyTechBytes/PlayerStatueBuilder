package com.skytechbytes.playerstatuebuilder.support;

import com.plotsquared.bukkit.BukkitPlatform;
import com.plotsquared.bukkit.util.BukkitUtil;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.skytechbytes.playerstatuebuilder.Log;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlotSquaredWrapper {
    private BukkitPlatform plotSquared = null;

    public PlotSquaredWrapper() throws Exception
    {
        this.plotSquared = (BukkitPlatform) PlayerStatueBuilder.instance.getServer().getPluginManager().getPlugin("PlotSquared");

        if (plotSquared == null) {
            throw new Exception();
        } else {
            Log.log("PlotSquared detected!");
        }
    }

    public boolean canBuild(Location lesserCorner, Location greaterCorner, Player creatingPlayer)
    {
        // dependency failed to load, assume ok to build
        if (plotSquared == null) {
            return true;
        } else {
            Log.log("Checking permissions");
        }
        // only build if player is in a plot owned by themselves
        try
        {
            com.plotsquared.core.location.Location plotSquaredlesserCorner = BukkitUtil.adapt(lesserCorner);
            com.plotsquared.core.location.Location plotSquaredGreaterCorner = BukkitUtil.adapt(greaterCorner);

            Plot plot = Plot.getPlot(plotSquaredlesserCorner);
            if (plot == null) {
                return false; // possibly a road, they aren't on a plot owned by themselves
            }
            if (!plot.isOwner(creatingPlayer.getUniqueId()) && !plot.isAdded(creatingPlayer.getUniqueId())) {
                return false; // neither added to the plot nor the owner of the plot
            }

            // CuboidRegion statueBoundingBox = new CuboidRegion(BlockVector3.at(lesserCorner.getBlockX(), lesserCorner.getBlockY(), lesserCorner.getBlockZ()),
            //        BlockVector3.at(greaterCorner.getBlockX(), greaterCorner.getBlockY(), greaterCorner.getBlockZ()));

            boolean statueContainedInOneRegion = false;
            for (CuboidRegion region: plot.getRegions()) {
                // The statue's bounding area must fit fully into a SINGLE region, even if the regions are next to each other
                BlockVector3 regionLesserCorner = region.getMinimumPoint();
                BlockVector3 regionGreaterCorner = region.getMaximumPoint();

                // just to be sure the lesser corner truly is lesser than the greater corner
                assert regionLesserCorner.getBlockX() <= regionGreaterCorner.getBlockX();
                assert regionLesserCorner.getBlockY() <= regionGreaterCorner.getBlockY();
                assert regionLesserCorner.getBlockZ() <= regionGreaterCorner.getBlockZ();
                assert lesserCorner.getBlockX() <= greaterCorner.getBlockX();
                assert lesserCorner.getBlockY() <= greaterCorner.getBlockY();
                assert lesserCorner.getBlockZ() <= greaterCorner.getBlockZ();

                if (
                    // statue's min x is still greater than the permitted region's minimum x bound
                        lesserCorner.getBlockX() > regionLesserCorner.getBlockX() &&
                                lesserCorner.getBlockY() > regionLesserCorner.getBlockY() &&
                                lesserCorner.getBlockZ() > regionLesserCorner.getBlockZ() &&
                                // statue's max x is still smaller than the region's maximum x bound
                                greaterCorner.getBlockX() < regionGreaterCorner.getBlockX() &&
                                greaterCorner.getBlockY() < regionGreaterCorner.getBlockY() &&
                                greaterCorner.getBlockZ() < regionGreaterCorner.getBlockZ()
                ) {
                    // fully contained! If the statue fits in just one of the regions, it's okay since all regions have the same flags
                    statueContainedInOneRegion = true;
                }
            }
            return statueContainedInOneRegion;
        }
        catch (Throwable sandal)
        {
            sandal.printStackTrace();
        }
        // plot squared loaded but something else went wrong
        return false;
    }
}
