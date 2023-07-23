package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;

public class Set extends Subcommand {

    public Set() {
        super("set", "rpa.fractions.set");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды set: \n");
            builder.append("/fractions set <игрок> <должность>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        Main.config.setPost(target, args[2]);

        return true;
    }
}
