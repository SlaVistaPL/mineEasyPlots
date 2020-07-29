package pl.mineEasyPlots.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorUtil {

    public static String fix(final String text) {
        return ChatColor.translateAlternateColorCodes('&',
                text.replace("<<", "«").replace(">>", "»"));
    }

    public static void sendMsg(Player p, String msg) {
        p.sendMessage(fix(msg));
    }

    public static void sendMsg(CommandSender p, String msg) {
        p.sendMessage(fix(msg));
    }
}
