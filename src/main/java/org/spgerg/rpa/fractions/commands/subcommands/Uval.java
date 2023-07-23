package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;

public class Uval extends Subcommand {

    public Uval() {
        super("uval", "rpa.fractions.uval");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды uval: \n");
            builder.append("/fractions uval <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        target.sendMessage("Ты уволен НАХУЙ");

        Main.config.removeMemberFromFraction(target);

        return true;
    }
}
