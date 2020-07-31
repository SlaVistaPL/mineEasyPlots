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

    public static HashMap<String, String> messages = new HashMap<>();

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

            if (mess.equalsIgnoreCase("commandHelp")) {
                List<String> join = config.getStringList("messages.commandHelp");
                StringBuilder sb = new StringBuilder();
                for (String s : join) {
                    sb.append(s).append(";");
                }
                if (!messages.containsKey(mess)) {
                    messages.put(mess, sb.toString());
                }
                return sb.toString();
            }

            if (mess.equalsIgnoreCase("commandAdminHelp")) {
                List<String> join = config.getStringList("messages.commandAdminHelp");
                StringBuilder sb = new StringBuilder();
                for (String s : join) {
                    sb.append(s).append(";");
                }
                if (!messages.containsKey(mess)) {
                    messages.put(mess, sb.toString());
                }
                return sb.toString();
            }

            if (mess.equalsIgnoreCase("plotInfo")) {
                List<String> join = config.getStringList("messages.plotInfo");
                StringBuilder sb = new StringBuilder();
                for (String s : join) {
                    sb.append(s).append(";");
                }
                if (!messages.containsKey(mess)) {
                    messages.put(mess, sb.toString());
                }
                return sb.toString();
            }

            if (mess.equalsIgnoreCase("plotLoreInGui")) {
                List<String> join = config.getStringList("messages.plotLoreInGui");
                StringBuilder sb = new StringBuilder();
                for (String s : join) {
                    sb.append(s).append(";");
                }
                if (!messages.containsKey(mess)) {
                    messages.put(mess, sb.toString());
                }
                return sb.toString();
            }

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
