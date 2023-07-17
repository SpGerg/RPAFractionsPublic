package org.spgerg.rpa.fractions.commands.subcommands.police;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.utils.PlayerArrestUtils;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Cuffs extends Subcommand {
    public Cuffs() {
        super("cuffs", "rpa.fractions.police.cuffs");
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
                Utils.playerUtils.replace(target, new PlayerArrestUtils(player, null));
                Utils.playerUtils.replace(player, new PlayerArrestUtils(null, target));
            }

            return true;
        }

        Utils.playerUtils.put(target, new PlayerArrestUtils(player, null));
        Utils.playerUtils.put(player, new PlayerArrestUtils(null, target));

        return true;
    }
}
