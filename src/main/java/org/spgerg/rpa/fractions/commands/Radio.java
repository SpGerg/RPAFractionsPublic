package org.spgerg.rpa.fractions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.Utils;
import org.spgerg.rpa.fractions.model.serializable.FractionSerializable;

public class Radio implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        FractionSerializable playerFraction = Main.config.getPlayerFraction(player);

        if (args.length == 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды radio: \n");
            builder.append("/radio <сообшение>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            FractionSerializable fractionSerializable = Main.config.getPlayerFraction(onlinePlayer);

            if (fractionSerializable.name.equals(playerFraction.name)) {
                onlinePlayer.sendMessage("Чат фракции[" + player.getName() + "]: " + Utils.allArgs(0, args));
            }
        }

        return true;
    }
}
