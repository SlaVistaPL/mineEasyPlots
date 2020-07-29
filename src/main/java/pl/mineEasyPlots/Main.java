package pl.mineEasyPlots;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mineEasyPlots.commands.PlotCommand;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.listeners.BlockBreakListener;
import pl.mineEasyPlots.listeners.BlockPlaceListener;
import pl.mineEasyPlots.listeners.InventoryClickListener;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInst() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Messages.loadMessages();
        saveDefaultConfig();
        Config.load();

        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        getCommand("plot").setExecutor(new PlotCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
