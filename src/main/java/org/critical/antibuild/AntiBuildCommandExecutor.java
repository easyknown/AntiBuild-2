package org.critical.antibuild;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiBuildCommandExecutor implements CommandExecutor {
    private final AntiBuild plugin;

    public AntiBuildCommandExecutor(AntiBuild plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /antibuild <lock|unlock|reload> [world]");
            return true;
        }

        String subcommand = args[0];
        if (subcommand.equalsIgnoreCase("lock")) {
            if (args.length != 2) {
                sender.sendMessage("Usage: /antibuild lock <world>");
                return true;
            }

            String worldName = args[1];
            plugin.addLockedWorld(worldName);
            sender.sendMessage("World " + worldName + " has been locked.");
        } else if (subcommand.equalsIgnoreCase("unlock")) {
            if (args.length != 2) {
                sender.sendMessage("Usage: /antibuild unlock <world>");
                return true;
            }

            String worldName = args[1];
            plugin.removeLockedWorld(worldName);
            sender.sendMessage("World " + worldName + " has been unlocked.");
        } else if (subcommand.equalsIgnoreCase("reload")) {
            plugin.createLockedWorldsConfig();
            sender.sendMessage("AntiBuild configuration reloaded.");
        } else {
            sender.sendMessage("Unknown command. Usage: /antibuild <lock|unlock|reload> [world]");
        }

        return true;
    }
}
