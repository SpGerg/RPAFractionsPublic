package org.spgerg.rpa.fractions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.utils.Utils;

public class NonRp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды nrp: \n");
            builder.append("/nrp <сообшение>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        player.chat(ChatColor.GRAY + "(( " + ChatColor.RESET + Utils.allArgs(0, args) + ChatColor.GRAY + " ))");

        return true;
    }
}
