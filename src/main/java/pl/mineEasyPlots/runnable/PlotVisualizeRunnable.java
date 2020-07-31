package pl.mineEasyPlots.runnable;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import pl.mineEasyPlots.managers.UserManager;
import pl.mineEasyPlots.objects.User;

public class PlotVisualizeRunnable implements Runnable {

    @Override
    public void run() {
        if (!UserManager.getUsers().isEmpty()) {
            for (User u : UserManager.getUsers()) {

                if (!u.isVisualized()) {
                    continue;
                }

                Player p = Bukkit.getPlayer(u.getName());

                if (p == null) {
                    continue;
                }

                ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(p.getWorld())).getApplicableRegions(BlockVector3.at(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()));


                for (ProtectedRegion region : regions) {

                    Location loc1 = BukkitAdapter.adapt(p.getWorld(), region.getMaximumPoint());
                    Location loc2 = BukkitAdapter.adapt(p.getWorld(), region.getMinimumPoint());

                    double minX = Math.min(loc1.getX(), loc2.getX());
                    double minY = Math.min(loc1.getY(), loc2.getY());
                    double minZ = Math.min(loc1.getZ(), loc2.getZ());
                    double maxX = Math.max(loc1.getX(), loc2.getX());
                    double maxY = Math.max(loc1.getY(), loc2.getY());
                    double maxZ = Math.max(loc1.getZ(), loc2.getZ());

                    for (double x = minX; x <= maxX; x++) {
                        for (double y = minY; y <= maxY; y++) {
                            for (double z = minZ; z <= maxZ; z++) {
                                int components = 0;
                                if (x == minX || x == maxX)
                                    components++;
                                if (y == minY || y == maxY)
                                    components++;
                                if (z == minZ || z == maxZ)
                                    components++;
                                if (components >= 2) {

                                    int red;
                                    int green;

                                    if (region.getOwners().contains(p.getName()) || region.getMembers().contains(p.getName())) {
                                        red = 0;
                                        green = 245;
                                    } else {
                                        red = 245;
                                        green = 0;
                                    }

                                    Location l1 = new Location(p.getWorld(), x + 0.4, p.getLocation().getY() + 0.5, z);
                                    p.spawnParticle(Particle.REDSTONE, l1, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                    Location l2 = new Location(p.getWorld(), x - 0.4, p.getLocation().getY() + 0.5, z);
                                    p.spawnParticle(Particle.REDSTONE, l2, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                    Location l3 = new Location(p.getWorld(), x, p.getLocation().getY() + 0.5, z + 0.4);
                                    p.spawnParticle(Particle.REDSTONE, l3, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                    Location l4 = new Location(p.getWorld(), x, p.getLocation().getY() + 0.5, z - 0.4);
                                    p.spawnParticle(Particle.REDSTONE, l4, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                    Location l5 = new Location(p.getWorld(), x, p.getLocation().getY() + 0.9, z - 0.4);
                                    p.spawnParticle(Particle.REDSTONE, l5, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                    Location l6 = new Location(p.getWorld(), x, p.getLocation().getY() + 0.1, z - 0.4);
                                    p.spawnParticle(Particle.REDSTONE, l6, 0, 0, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(red, green, 0), 1));

                                }
                            }
                        }
                    }
                }
            }
        }

    }

}
