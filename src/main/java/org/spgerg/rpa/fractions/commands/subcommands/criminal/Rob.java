package org.spgerg.rpa.fractions.commands.subcommands.criminal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Rob extends Subcommand {

    public Rob() {
        super("rob", "rpa.fractions.criminal.rob");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length <= 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды rob: \n");
            builder.append("/criminal rob <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        Main.econ.withdrawPlayer(target, (Main.econ.getBalance(target) / 100) * 30);

        return true;
    }
}
