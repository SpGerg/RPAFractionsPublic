package org.spgerg.rpa.fractions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

import java.util.Objects;

public class Fractions implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            Main.instance.getLogger().info("В консоли нельзя.");

            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Команды: ");

            for (Subcommand subcommand : Subcommand.fractionsSubcommands) {
                player.sendMessage(subcommand.name);
            }
        }

        for (Subcommand subcommand : Subcommand.fractionsSubcommands) {
            if (subcommand.name.equals(args[0]) && player.hasPermission(subcommand.permission)) return subcommand.execute(player, args);
        }

        player.sendMessage("Команда не найдена");

        return true;
    }
}
