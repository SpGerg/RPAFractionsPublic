package org.spgerg.rpa.fractions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Smi implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            Main.instance.getLogger().info("В консоли нельзя.");

            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Команды: ");

            for (Subcommand subcommand : Subcommand.smiSubcommands) {
                player.sendMessage(subcommand.name);
            }
        }

        for (Subcommand subcommand : Subcommand.smiSubcommands) {
            if (subcommand.name.equals(args[0]) && player.hasPermission(subcommand.permission)) return subcommand.execute(player, args);
        }

        player.sendMessage("Команда не найдена");

        return true;
    }
}
