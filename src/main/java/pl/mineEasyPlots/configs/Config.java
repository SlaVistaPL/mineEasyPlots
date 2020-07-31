package pl.mineEasyPlots.configs;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import pl.mineEasyPlots.Main;

import java.io.File;
import java.io.IOException;

public class Config {

    private static int plotSize;
    private static Material plotBlock;
    private static int plotsPlayerLimit;
    private static int plotsVipLimit;

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
        FileConfiguration cfg = Main.getInst().getConfig();
        File f = new File(Main.getInst().getDataFolder(), "config.yml");
        try {
            try {
                cfg.load(f);
            } catch (InvalidConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {

        plotSize = getConfig().getInt("plotSize");
        plotsPlayerLimit = getConfig().getInt("plotsPlayerLimit");
        plotsVipLimit = getConfig().getInt("plotsVipLimit");
        plotBlock = Material.getMaterial(getConfig().getString("plotBlock"));


    }

    public static int getPlotSize() {
        return plotSize;
    }

    public static Material getPlotBlock() {
        return plotBlock;
    }

    public static int getPlotsPlayerLimit() {
        return plotsPlayerLimit;
    }

    public static int getPlotsVipLimit() {
        return plotsVipLimit;
    }
}
