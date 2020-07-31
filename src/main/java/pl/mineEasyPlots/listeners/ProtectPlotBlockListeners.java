package pl.mineEasyPlots.listeners;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.mineEasyPlots.configs.Config;

public class ProtectPlotBlockListeners implements Listener {


    @EventHandler
    private void event(EntityChangeBlockEvent e) {

        Block b = e.getBlock();

        ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(b.getWorld())).getApplicableRegions(BlockVector3.at(b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()));

        for (ProtectedRegion region : regions) {

            if (b.getType() != Config.getPlotBlock()) {
                continue;
            }

            if (b.getLocation().getBlockX() != (region.getMaximumPoint().getBlockX() - Config.getPlotSize())) {
                continue;
            }

            if (b.getLocation().getBlockZ() != (region.getMaximumPoint().getBlockZ() - Config.getPlotSize())) {
                continue;
            }

            e.setCancelled(true);
        }
    }


    @EventHandler
    private void event(BlockBurnEvent e) {

        Block b = e.getBlock();

        ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(b.getWorld())).getApplicableRegions(BlockVector3.at(b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()));

        for (ProtectedRegion region : regions) {

            if (b.getType() != Config.getPlotBlock()) {
                continue;
            }

            if (b.getLocation().getBlockX() != (region.getMaximumPoint().getBlockX() - Config.getPlotSize())) {
                continue;
            }

            if (b.getLocation().getBlockZ() != (region.getMaximumPoint().getBlockZ() - Config.getPlotSize())) {
                continue;
            }

            e.setCancelled(true);
        }
    }

    @EventHandler
    private void event1(EntityExplodeEvent e) {

        for (Block b : e.blockList()) {

            ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(b.getWorld())).getApplicableRegions(BlockVector3.at(b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()));

            for (ProtectedRegion region : regions) {

                if (b.getType() != Config.getPlotBlock()) {
                    continue;
                }

                if (b.getLocation().getBlockX() != (region.getMaximumPoint().getBlockX() - Config.getPlotSize())) {
                    continue;
                }

                if (b.getLocation().getBlockZ() != (region.getMaximumPoint().getBlockZ() - Config.getPlotSize())) {
                    continue;
                }

                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void event1(BlockPistonExtendEvent e) {
        for (Block b : e.getBlocks()) {

            ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(b.getWorld())).getApplicableRegions(BlockVector3.at(b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()));

            for (ProtectedRegion region : regions) {

                if (b.getType() != Config.getPlotBlock()) {
                    continue;
                }

                if (b.getLocation().getBlockX() != (region.getMaximumPoint().getBlockX() - Config.getPlotSize())) {
                    continue;
                }

                if (b.getLocation().getBlockZ() != (region.getMaximumPoint().getBlockZ() - Config.getPlotSize())) {
                    continue;
                }

                e.setCancelled(true);
            }
        }
    }
}
