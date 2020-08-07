package pl.mineEasyPlots;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mineEasyPlots.commands.PlotAdminCommand;
import pl.mineEasyPlots.commands.PlotAdminTabCompleter;
import pl.mineEasyPlots.commands.PlotCommand;
import pl.mineEasyPlots.commands.PlotTabCompleter;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.FileUpdater;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.listeners.*;
import pl.mineEasyPlots.runnable.PlotVisualizeRunnable;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInst() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Logger logger = this.getLogger();

        Messages.loadMessages();
        saveDefaultConfig();
        FileUpdater.checkConfig();
        FileUpdater.checkMessages();
        Config.load();

        if(!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")){
            logger.log(Level.SEVERE, ChatColor.RED + "Dependency WorldGuard not found!\nDisabling...");
            getPluginLoader().disablePlugin(this);
            return;
        }

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

        int pluginId = 8367;
        new Metrics(this, pluginId);

        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "           _            _____               ______ _       _       \n");
        logger.log(Level.INFO, "          (_)          |  ___|              | ___ \\ |     | |      \n");
        logger.log(Level.INFO, " _ __ ___  _ _ __   ___| |__  __ _ ___ _   _| |_/ / | ___ | |_ ___ \n");
        logger.log(Level.INFO, "| '_ ` _ \\| | '_ \\ / _ \\  __|/ _` / __| | | |  __/| |/ _ \\| __/ __|\n");
        logger.log(Level.INFO, "| | | | | | | | | |  __/ |__| (_| \\__ \\ |_| | |   | | (_) | |_\\__ \\\n");
        logger.log(Level.INFO, "|_| |_| |_|_|_| |_|\\___\\____/\\__,_|___/\\__, \\_|   |_|\\___/ \\__|___/\n");
        logger.log(Level.INFO, "                                        __/ |                      \n");
        logger.log(Level.INFO, "                                       |___/                       ");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "Author: _arturek");
        logger.log(Level.INFO, "Collaborators: Nikox3003");
        logger.log(Level.INFO, "Version: " + this.getDescription().getVersion());
        logger.log(Level.INFO, "Web: https://minecodes.pl/");
        logger.log(Level.INFO, "Discord: https://discord.com/invite/XQtBtRj");
        logger.log(Level.INFO, "");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
