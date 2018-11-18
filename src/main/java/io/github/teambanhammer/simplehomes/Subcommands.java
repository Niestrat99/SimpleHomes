package io.github.teambanhammer.simplehomes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Subcommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home home (home name) " + ChatColor.DARK_AQUA + "Teleports you to your home");
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home set (home name) " + ChatColor.DARK_AQUA + "Creates your home");
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home del (home name) " + ChatColor.DARK_AQUA + "Deletes your home");
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home homes " + ChatColor.DARK_AQUA + "Opens a GUI which lists up your homes");
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home favorite (home name) " + ChatColor.DARK_AQUA + "Adds your home to your favorites");
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "/home unfavorite (home name) " + ChatColor.DARK_AQUA + "Removes your home from your favorites");
        } else {
            if (strings[0].equalsIgnoreCase("admin")){
                if (strings.length >1) {
                    if (strings[1].equalsIgnoreCase("set")){
                        if (sender instanceof Player){
                            Player player = (Player)sender;
                            Location home = player.getLocation();
                            if (strings.length>2){
                                Player target = Bukkit.getOfflinePlayer(strings[2]).getPlayer();
                                if (target == null){
                                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.RED + "Player not found!");
                                    return false;
                                } else {
                                    if (strings.length>3){
                                        String name = strings[3];
                                      if (Homes.getHomes(target).containsKey(name)){
                                          sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.YELLOW + target.getName() + " " + ChatColor.RED + "already has a home called " + ChatColor.YELLOW + "" + name + "" + ChatColor.RED + "!" );
                                          return false;
                                        } else {
                                          try {
                                              Homes.setHome(target,strings[3],home);
                                              sender.sendMessage(ChatColor.GREEN + "Successfully set the home " + ChatColor.YELLOW + name + ChatColor.GREEN + " for " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + "!");
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                    }
                                }
                            }
                        }
                    }
                }
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin set (username) (home name) " + ChatColor.DARK_RED + "Creates a home where you're standing for the player you've mentioned");
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin del (username) (home name) " + ChatColor.DARK_RED + "Deletes a home of a player");
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin home (username) (home name) " + ChatColor.DARK_RED + "Teleports you to a player's home");
                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "/home admin list (username) " + ChatColor.DARK_RED + "Sends you a player's list of homes");
            } else if (strings[0].equalsIgnoreCase("set")) {
                if (sender instanceof Player){
                    Player player = (Player)sender;
                    Location home = player.getLocation();
                    if (strings.length>1){
                        if (strings[1].length()<13){
                            if (Homes.getHomes(player).containsKey(strings[1])) {
                                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.RED + "You already have a home called " + ChatColor.YELLOW + strings[1] + ChatColor.GREEN + "!");
                            } else {
                                try {
                                    Homes.setHome(player,strings[1],home);
                                    sender.sendMessage(ChatColor.GREEN + "Successfully set the home " + ChatColor.YELLOW + strings[1] + ChatColor.GREEN + "!");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }else{
                            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.RED + "The home name may not be longer than 12 characters!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR: " + ChatColor.RED + "You have to give the home a name!");
                    }
                }
            } else if (strings[0].equalsIgnoreCase("del")){
                if (sender instanceof Player){
                    Player player = (Player)sender;
                    if (strings.length>1){
                        if (Homes.getHomes(player).containsKey(strings[1])){
                            try {
                                Homes.delHome(player,strings[1]);
                                sender.sendMessage(ChatColor.GREEN + "Successfully deleted " + ChatColor.YELLOW + strings[1] + ChatColor.GREEN + "!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR " + ChatColor.RED + "This home doesn't exist!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR " + ChatColor.RED + "You have to mention a home name!");
                    }
                }
            } else if (strings[0].equalsIgnoreCase("home")){
                if (sender instanceof Player){
                    Player player = (Player)sender;
                    if (strings.length>1){
                        if (Homes.getHomes(player).containsKey(strings[1])){
                            Location location = Homes.getHomes(player).get(strings[1]);
                            player.teleport(location);
                            sender.sendMessage(ChatColor.GREEN + "Successfully teleported to " + ChatColor.YELLOW + strings[1] + ChatColor.GREEN + "!");
                        }else{
                            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR " + ChatColor.RED + "This home doesn't exist!");
                        }
                    }else{
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "ERROR " + ChatColor.RED + "You have to mention a home name!");
                    }
                }
            } else if (strings[0].equalsIgnoreCase("list")){
                if (sender instanceof Player){
                    Player player = (Player)sender;
                    StringBuilder hlist = new StringBuilder();
                    hlist.append(ChatColor.AQUA + "" + ChatColor.BOLD + "Homes: " + ChatColor.WHITE);
                    for (String home:Homes.getHomes(player).keySet()){
                        hlist.append(home + ", ");
                    }
                    sender.sendMessage(hlist.toString());
                }
            }
        } return false;
    }
}
