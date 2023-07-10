package org.spgerg.rpa.fractions.commands.subcommands.police;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.PlayerUtils;
import org.spgerg.rpa.fractions.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Cuffs extends Subcommand {
    @Override
    public String getName() {
        return "cuffs";
    }

    @Override
    public String getPermission() {
        return "rpa.fractions.police.cuffs";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды cuffs: \n");
            builder.append("/police cuffs <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        if (player.getLocation().distance(target.getLocation()) > 2) {
            player.sendMessage("Расстояние слишком большое");

            return true;
        }

        if (Utils.playerUtils.containsKey(player)) {
            if (Utils.playerUtils.get(player).toFollow != null || Utils.playerUtils.get(player).handCuffsTarget != null) {
                Utils.playerUtils.remove(target);
                Utils.playerUtils.remove(player);
            }
            else {
                Utils.playerUtils.replace(target, new PlayerUtils(player, null));
                Utils.playerUtils.replace(player, new PlayerUtils(null, target));
            }

            return true;
        }

        Utils.playerUtils.put(target, new PlayerUtils(player, null));
        Utils.playerUtils.put(player, new PlayerUtils(null, target));

        return true;
    }
}
