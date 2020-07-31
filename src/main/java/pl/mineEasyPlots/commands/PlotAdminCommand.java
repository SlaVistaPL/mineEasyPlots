package pl.mineEasyPlots.commands;

import com.google.common.collect.Lists;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.utils.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class PlotAdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage("Only player can use this command!");
            return false;
        }

        Player p = (Player) s;

        if (!p.hasPermission("mineEasyPlots.admin")) {
            ColorUtil.sendMsg(p, Messages.getMessage("no-permissions"));
            return false;
        }

        if (args.length == 0) {

            String[] m = Messages.getMessage("commandAdminHelp").split(";");

            for (String st : m) {
                ColorUtil.sendMsg(p, st);
            }

            return false;
        }


        if (args[0].equalsIgnoreCase("list")) {

            if (args.length != 2) {
                ColorUtil.sendMsg(p, Messages.getMessage("correctCommand") + "/plot list <nick>");
                return false;
            }


            Inventory inv = Bukkit.createInventory(null, 9 * 6, Messages.getMessage("plotListGuiName") + args[1]);

            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get(BukkitAdapter.adapt(p.getWorld()));

            List<ItemStack> regionsItems = new ArrayList<>();

            int plotId = 1;

            for (ProtectedRegion region : regions.getRegions().values()) {

                if (region.getId().contains(args[1].toLowerCase())) {


                    int x = region.getMaximumPoint().getBlockX() - Config.getPlotSize();
                    int z = region.getMaximumPoint().getBlockZ() - Config.getPlotSize();

                    ItemStack is = new ItemStack(Config.getPlotBlock());
                    ItemMeta im = is.getItemMeta();
                    im.setDisplayName(ColorUtil.fix(Messages.getMessage("plotNameInGui") + plotId));


                    List<String> lore = Lists.newArrayList();

                    String[] m = Messages.getMessage("plotLoreInGui").split(";");


                    StringBuilder sb = new StringBuilder();
                    if (region.getMembers().getPlayers().isEmpty()) {
                        sb.append("&cempty");
                    } else {
                        for (String mem : region.getMembers().getPlayers()) {
                            sb.append(mem).append(", ");
                        }
                    }

                    for (String st : m) {
                        st = st.replace("{plotMembers}", sb.toString());
                        st = st.replace("{plotCenter}", "x: " + x + " y: ~ z: " + z);
                        lore.add(ColorUtil.fix(st));
                    }


                    im.setLore(lore);
                    is.setItemMeta(im);

                    if (regionsItems.size() < 9 * 6) {
                        regionsItems.add(is);
                        plotId++;
                    }


                }
            }

            for (int i = 0; i < regionsItems.size(); i++) {
                inv.setItem(i, regionsItems.get(i));
            }


            p.openInventory(inv);
            return false;
        }

        if (args[0].equalsIgnoreCase("add")) {

            if (args.length < 2) {
                ColorUtil.sendMsg(p, Messages.getMessage("correctCommand") + "/plot add <nick>");
                return false;
            }


            ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(p.getWorld())).getApplicableRegions(BlockVector3.at(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()));


            for (ProtectedRegion region : regions) {

                Player playerAdd = Bukkit.getPlayer(args[1]);

                if (playerAdd == null) {
                    ColorUtil.sendMsg(p, Messages.getMessage("addPlayerIsOffline"));
                    continue;
                }

                region.getMembers().addPlayer(playerAdd.getName());
                ColorUtil.sendMsg(p, Messages.getMessage("successAddPlayer").replace("{player}", playerAdd.getName()));
                ColorUtil.sendMsg(playerAdd, Messages.getMessage("successAddPlayerInfo").replace("{owner}", args[1]));

            }
            return false;

        }

        if (args[0].equalsIgnoreCase("kick")) {

            if (args.length < 2) {
                ColorUtil.sendMsg(p, Messages.getMessage("correctCommand") + "/plot kick <nick>");
                return false;
            }


            ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(p.getWorld())).getApplicableRegions(BlockVector3.at(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()));


            for (ProtectedRegion region : regions) {

                if (!region.getMembers().contains(args[1])) {
                    ColorUtil.sendMsg(p, Messages.getMessage("playerIsNotMember"));
                    continue;
                }

                Player playerKick = Bukkit.getPlayer(args[1]);


                region.getMembers().removePlayer(args[1]);
                ColorUtil.sendMsg(p, Messages.getMessage("successKickPlayer").replace("{player}", args[1]));

                if (playerKick != null) {
                    ColorUtil.sendMsg(playerKick, Messages.getMessage("successKickPlayerInfo").replace("{owner}", args[1]));
                }
            }

            return false;
        }

        return false;
    }
}
