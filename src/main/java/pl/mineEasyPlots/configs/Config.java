package pl.mineEasyPlots.configs;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import pl.mineEasyPlots.Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Config {

    private static int plotSize;
    private static Material plotBlock;
    private static HashMap<String, Integer> plotLimits;

    public static FileConfiguration getConfig() {
        return Main.getInst().getConfig();
    }

    public static void save() {
        FileConfiguration cfg = Main.getInst().getConfig();
        File f = new File(Main.getInst().getDataFolder(), "config.yml");
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        Main.getInst().reloadConfig();
        load();
    }

    public static void load() {

        plotLimits = new HashMap<>();
        for(String section : getConfig().getConfigurationSection("plotLimits").getKeys(false)){
            plotLimits.put(section, getConfig().getInt("plotLimits." + section));
        }
        plotSize = getConfig().getInt("plotSize");
        plotBlock = Material.getMaterial(getConfig().getString("plotBlock"));


    }

    public static int getPlotSize() {
        return plotSize;
    }

    public static Material getPlotBlock() {
        return plotBlock;
    }

    public static HashMap<String, Integer> getPlotLimits() {
        return plotLimits;
    }
}
