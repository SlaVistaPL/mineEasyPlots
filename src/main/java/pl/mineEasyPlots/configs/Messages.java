package pl.mineEasyPlots.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.mineEasyPlots.Main;
import pl.mineEasyPlots.utils.ColorUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Messages {

    public static HashMap<String, String> messages = new HashMap<String, String>();

    public static void loadMessages() {
        File m = new File(Main.getInst().getDataFolder(), "messages.yml");
        if (!m.exists()) {
            Main.getInst().saveResource("messages.yml", true);
        }

        messages.clear();
    }

    public static String getMessage(String mess) {

        String msg = messages.get(mess);

        if (msg != null) {
            return ColorUtil.fix(msg);
        } else {

            File file = new File(Main.getInst().getDataFolder() + "/messages.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            msg = config.getString("messages." + mess);

            if (msg != null) {
                messages.put(mess, msg);
                return ColorUtil.fix(msg);
            } else {
                config.set("messages." + mess, "&cPlease write message in messages.yml - &7" + mess);

                try {
                    config.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return ColorUtil.fix(" &cError placeholder message: &7" + mess + " &c, set placeholder in messages config.");
            }
        }
    }
}