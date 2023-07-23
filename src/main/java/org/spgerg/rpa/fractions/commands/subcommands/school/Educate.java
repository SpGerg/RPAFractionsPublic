package org.spgerg.rpa.fractions.commands.subcommands.school;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Educate extends Subcommand {
    public Educate() {
        super("educate", "rpa.fractions.school.educate");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды educate: \n");
            builder.append("/school educate <игрок> <образование>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        Main.config.setEducate(target, args[2]);

        return true;
    }
}
