package io.github.teambanhammer.simplehomes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class main_class extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("SimpleHomes is now enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("SimpleHomes is now disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("home")){
            if (args.length == 0) {
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home home (home name) " + ChatColor.DARK_AQUA + "Teleports you to your home");
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home set (home name) " + ChatColor.DARK_AQUA + "Creates your home");
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home del (home name) " + ChatColor.DARK_AQUA + "Deletes your home");
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home homes " + ChatColor.DARK_AQUA + "Opens a GUI which lists up your homes");
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home favorite (home name) " + ChatColor.DARK_AQUA + "Adds your home to your favorites");
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home unfavorite (home name) " + ChatColor.DARK_AQUA + "Removes your home from your favorites");
            } else {
                if (args[0].equalsIgnoreCase("admin")){
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin set (username) (home name) " + ChatColor.DARK_RED + "Creates a home where you're standing for the player you've mentioned");
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin del (username) (home name) " + ChatColor.DARK_RED + "Deletes a home of a player");
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin home (username) (home name) " + ChatColor.DARK_RED + "Teleports you to a player's home");
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin list (username) " + ChatColor.DARK_RED + "Sends you a player's list of homes");
                }
            }

        }
        return true;
    }
}