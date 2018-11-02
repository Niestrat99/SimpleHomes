package io.github.teambanhammer.simplehomes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File ConfigFile = new File("plugins/SimpleHomes","config.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void save() throws IOException {
        Config.save(ConfigFile);
    }

    public static void setOnline(Player player,Boolean online) throws IOException {
        Config.set(player.getName() + ".Online", online);
        save();
    }

    public static Boolean getOnline(Player player){
        return Config.getBoolean(player.getName()+".Online");
    }


}
