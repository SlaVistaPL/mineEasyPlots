package pl.mineEasyPlots.configs;

import pl.mineEasyPlots.Main;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUpdater {

    // Version: 1.1
    public static void checkConfig(){
        if(Main.getInst().getConfig().get("plotLimits.default") == null){
            File old = new File(Main.getInst().getDataFolder(), "config.yml");
            old.renameTo(new File(Main.getInst().getDataFolder(), "config.yml.old"));
            Main.getInst().saveDefaultConfig();
            Logger logger = Main.getInst().getLogger();
            logger.log(Level.WARNING, "[Updater] Saved default config from jar.");
            logger.log(Level.WARNING, "[Updater] Old config saved to " + old.getAbsolutePath() + ".");
        }
    }
    // Version: 1.1
    public static void checkMessages(){
        if(Messages.messages.get("reloadSuccess") == null){
            File old = new File(Main.getInst().getDataFolder(), "messages.yml");
            old.renameTo(new File(Main.getInst().getDataFolder(), "messages.yml.old"));
            Main.getInst().saveResource("messages.yml", false);
            Logger logger = Main.getInst().getLogger();
            logger.log(Level.WARNING, "[Updater] Saved default messages from jar.");
            logger.log(Level.WARNING, "[Updater] Old messages saved to " + old.getAbsolutePath() + ".");
        }
    }
}
