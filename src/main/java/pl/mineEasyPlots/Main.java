package pl.mineEasyPlots;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mineEasyPlots.configs.Config;
import pl.mineEasyPlots.configs.Messages;
import pl.mineEasyPlots.listeners.BlockPlaceListener;

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
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
