package org.spgerg.rpa.fractions.commands.subcommands.police;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Wanted extends Subcommand {
    public Wanted() {
        super("wanted", "rpa.fractions.police.wanted");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 3) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды wanted: \n");
            builder.append("/police wanted add <игрок> <кол-во>\n");
            builder.append("/police wanted remove <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[2]);

        int amount = 0;

        try {
            if (!args[1].equals("remove")) {
                amount = Integer.valueOf(args[3]);
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();

            player.sendMessage("Неверное количество розыска.");

            return true;
        }

        if (target == null) {
            player.sendMessage("Игрок не найден");

            return true;
        }

        if (args[1].equals("add")) {
            Main.config.addWanted(target, amount);

            player.sendMessage("Игроку " + target.getName() + " выдано " + amount + " звёзды");

            return true;
        }
        else if(args[1].equals("remove")) {
            Main.config.removeWanted(target);

            player.sendMessage("Игрок " + target.getName() + " теперь не в розыске!");

            return true;
        }

        player.sendMessage("Команда не найдена");

        return true;
    }
}
