package io.github.teambanhammer.simplehomes;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class ExampleSubCommand implements ISubCommand {
    @Override
    public String getCmdName() { // Command name
        return "hello";
    }

    @Override
    public String getPermission() { // Command permission
        return "plugin.hello";
    }

    @Override
    public String getCmdDescription() {
        return "This command says hi ;3"; // Command description
    }

    @Override
    public String getSubCommand() {
        return "hello"; // Use this if you want a tab completer
    }

    @Override
    public String getUsage() { // How to use the command
        return "/hello";
    }

    @Override
    public HashMap<Boolean, String> isCorrectUsage(String[] args, CommandSender sender) { // Ignore this, this is advanced rubbish
        return null;
    }

    @Override
    public boolean isMainCommand() { // Is it a subcommand?
        return false;
    }

    @Override
    public boolean fire(String[] args, CommandSender sender) { // The code that runs when the command is fired.
        sender.sendMessage("Hello!");
        return false;
    }
}
