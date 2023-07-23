package org.spgerg.rpa.fractions.commands.subcommands.criminal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class RpKill extends Subcommand {

    public RpKill() {
        super("rpkill", "rpa.fractions.criminal.rpkill");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length <= 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды rpkill: \n");
            builder.append("/criminal rpkill <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        Main.config.resetPlayer(target);

        target.damage(10000);

        return true;
    }

}
