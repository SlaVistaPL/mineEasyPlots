package pl.mineEasyPlots.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.utils.ColorUtil;

public class PlotDeleteListener implements Listener {

    @EventHandler
    private void event(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(p.getWorld())).getApplicableRegions(BlockVector3.at(b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()));

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

            if (!region.getOwners().contains(p.getName()) || !p.hasPermission("mineEasyPlots.admin")) {
                e.setCancelled(true);
                continue;
            }

            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regionsList = container.get(BukkitAdapter.adapt(p.getWorld()));

            if (regionsList == null) {
                continue;
            }

            regionsList.removeRegion(region.getId());
            ColorUtil.sendMsg(p, Messages.getMessage("successDeletePlot"));

        }

    }
}
