package io.github.teambanhammer.simplehomes;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

public interface ISubCommand {

    String getCmdName();

    String getPermission();

    String getCmdDescription();

    String getSubCommand();

    String getUsage();

    HashMap<Boolean, String> isCorrectUsage(String[] args, CommandSender sender);

    boolean isMainCommand();

    boolean fire(String[] args, CommandSender sender);

    default String[] advancedUsages() {
        return new String[0];
    }

}
