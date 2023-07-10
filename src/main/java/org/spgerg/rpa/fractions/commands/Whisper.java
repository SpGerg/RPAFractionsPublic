package org.spgerg.rpa.fractions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Utils;

public class Whisper implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды whisper: \n");
            builder.append("/whisper <сообшение>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        player.chat("Шепчет: " + Utils.allArgs(0, args));

        return true;
    }
}
