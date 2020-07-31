package pl.mineEasyPlots;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mineEasyPlots.commands.PlotAdminCommand;
import pl.mineEasyPlots.commands.PlotAdminTabCompleter;
import pl.mineEasyPlots.commands.PlotCommand;
import pl.mineEasyPlots.commands.PlotTabCompleter;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.listeners.*;
import pl.mineEasyPlots.runnable.PlotVisualizeRunnable;

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

        Bukkit.getPluginManager().registerEvents(new PlotCreateListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlotDeleteListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectPlotBlockListeners(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("plot").setExecutor(new PlotCommand());
        getCommand("plot").setTabCompleter(new PlotTabCompleter());
        getCommand("plotadmin").setExecutor(new PlotAdminCommand());
        getCommand("plotadmin").setTabCompleter(new PlotAdminTabCompleter());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PlotVisualizeRunnable(), 20, 20);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
