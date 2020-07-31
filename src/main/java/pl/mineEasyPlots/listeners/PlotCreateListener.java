package pl.mineEasyPlots.listeners;

import com.destroystokyo.paper.Title;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.utils.ColorUtil;
import pl.mineEasyPlots.utils.RegionUtil;

public class PlotCreateListener implements Listener {

    @EventHandler
    private void event(BlockPlaceEvent e) {

        if (e.getBlock().getType() == Config.getPlotBlock()) {

            Block b = e.getBlock();
            Location min = new Location(b.getWorld(), b.getX() - Config.getPlotSize(), 0, b.getZ() - Config.getPlotSize());
            Location max = new Location(b.getWorld(), b.getX() + Config.getPlotSize(), 255, b.getZ() + Config.getPlotSize());

            Player p = e.getPlayer();

            if (RegionUtil.createRegion(e.getPlayer(), min.getBlockX(), min.getBlockZ(), max.getBlockX(), max.getBlockZ())) {
                ColorUtil.sendMsg(p, Messages.getMessage("createPlot"));
            } else {
                e.setCancelled(true);
            }
        }

    }
}
