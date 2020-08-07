package pl.mineEasyPlots.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlotTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String alias, String[] args) {


        if(args.length <=1){
            List<String> old = new ArrayList<>();
            List<String> tab = new ArrayList<>();
            old.add("list");
            old.add("info");
            old.add("visualize");
            old.add("add");
            old.add("kick");
            old.add("limit");
            StringUtil.copyPartialMatches(args[0], old, tab);
            Collections.sort(tab);
            return tab;
        }

        if (args.length == 2) {
            List<String> tab = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                tab.add(p.getName());
            }
            return tab;
        }

        return new ArrayList<>();


    }
}
