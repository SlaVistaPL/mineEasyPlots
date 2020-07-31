package pl.mineEasyPlots.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.mineEasyPlots.Main;
import pl.mineEasyPlots.updater.Updater;
import pl.mineEasyPlots.utils.ColorUtil;

public class PlayerJoinListener implements Listener {

    @EventHandler
    private void event(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Updater spu = new Updater(Main.getInst(), "https://arturekdev.github.io/mineEasyPlots/mineEasyPlots.html");
        if (p.hasPermission("mineEasyPlots.admin") && spu.needsUpdate()) {
            p.sendMessage(ColorUtil.fix(" &6&lmineEasyPlots &8>> &7Plugin has new version! &e&l" + spu.getNewVersion() + " &7Download now!"));
        }
    }
}
