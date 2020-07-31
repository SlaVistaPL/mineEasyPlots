package pl.mineEasyPlots.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.entity.Player;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;

public class RegionUtil {


    public static boolean createRegion(Player p, int minX, int minZ, int maxX, int maxZ) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));

        if (regions == null) {
            return false;
        }


        int plotLimit = 0;
        int playerPlotLimit;
        if (p.hasPermission("mineeasyplots.vip")) {
            playerPlotLimit = Config.getPlotsVipLimit();
        } else {
            playerPlotLimit = Config.getPlotsPlayerLimit();
        }

        for (ProtectedRegion pr : regions.getRegions().values()) {
            if (pr.getOwners().contains(p.getName().toLowerCase())) {
                plotLimit++;
            }
        }

        if (plotLimit >= playerPlotLimit) {
            ColorUtil.sendMsg(p, Messages.getMessage("limitPlots").replace("{limit}", String.valueOf(playerPlotLimit)));
            return false;
        }


        //create region
        BlockVector3 min = BlockVector3.at(minX, 0, minZ);
        BlockVector3 max = BlockVector3.at(maxX, 255, maxZ);
        String regionId = regions.size() + "-mineEasyPlots-" + p.getName();
        ProtectedRegion region = new ProtectedCuboidRegion(regionId, min, max);


        //set owner
        DefaultDomain owners = region.getOwners();
        owners.addPlayer(p.getName());

        region.setOwners(owners);

        region.setFlag(Flags.DENY_MESSAGE, Messages.getMessage("denyFlags"));
        region.setFlag(Flags.GREET_TITLE, Messages.getMessage("entryPlot").replace("{plotOwner}", p.getName()));
        region.setFlag(Flags.FAREWELL_TITLE, Messages.getMessage("exitPlot").replace("{plotOwner}", p.getName()));
        region.setFlag(Flags.GREET_MESSAGE, Messages.getMessage("entryPlot").replace("{plotOwner}", p.getName()));
        region.setFlag(Flags.FAREWELL_MESSAGE, Messages.getMessage("exitPlot").replace("{plotOwner}", p.getName()));


        for (ProtectedRegion rg : region.getIntersectingRegions(regions.getRegions().values())) {
            ColorUtil.sendMsg(p, Messages.getMessage("errorCreatePlot"));
            return false;
        }

        //add region
        regions.addRegion(region);


        return true;
    }

}
