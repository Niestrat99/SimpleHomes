package io.github.teambanhammer.simplehomes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Homes {

    public static File ConfigFile = new File("plugins/SimpleHomes","homes.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void save() throws IOException {
        Config.save(ConfigFile);
    }public static void setHome(Player player, String homename, Location location) throws IOException {
        Config.set(player.getUniqueId().toString()+"."+homename+".x",location.getBlockX());
        Config.set(player.getUniqueId().toString()+"."+homename+".y",location.getBlockY());
        Config.set(player.getUniqueId().toString()+"."+homename+".z",location.getBlockZ());
        Config.set(player.getUniqueId().toString()+"."+homename+".world",location.getWorld().getName());
        save();
    }public static HashMap<String,Location> getHomes(Player player){
        HashMap<String,Location> homes = new HashMap<>();
        for (String home:Config.getConfigurationSection(player.getUniqueId().toString()).getKeys(false)) {
            Location location = new Location(Bukkit.getWorld(Config.getString(player.getUniqueId().toString() + "." + home + ".world")), // Gets world from name
                    Config.getInt(player.getUniqueId().toString() + "." + home + ".x"), // Gets X value
                            Config.getInt(player.getUniqueId().toString() + "." + home + ".y"), // Gets Y value
                                    Config.getInt(player.getUniqueId().toString() + "." + home + ".z")); // Gets Z value
            homes.put(home,location);
        }
        return homes;
    }public static void delHome(Player player, String homename) throws IOException {
        Config.set(player.getUniqueId().toString()+"."+homename,null);
        save();
    }
}
