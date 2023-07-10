package org.spgerg.rpa.fractions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.Utils;
import org.spgerg.rpa.fractions.model.serializable.FractionSerializable;

public class Leader implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды leader: \n");
            builder.append("/leader <сообшение>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            FractionSerializable fractionSerializable = Main.config.getPlayerFraction(onlinePlayer);

            if (fractionSerializable.leader.equals(onlinePlayer.getUniqueId().toString())) {
                onlinePlayer.sendMessage("Чат лидеров[" + player.getName() + "]: " + Utils.allArgs(0, args));
            }
        }

        return true;
    }
}
