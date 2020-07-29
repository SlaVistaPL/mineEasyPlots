package pl.mineEasyPlots.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.mineEasyPlots.configs.Messages;

public class InventoryClickListener implements Listener {

    @EventHandler
    private void event(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(Messages.getMessage("plotListGuiName"))) {
            e.setCancelled(true);
        }
    }
}
