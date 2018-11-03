package io.github.teambanhammer.simplehomes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class main_class extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("home").setExecutor(new Subcommands());
        System.out.println("SimpleHomes is now enabled.");
        try {
            Config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        System.out.println("SimpleHomes is now disabled.");
    }
}