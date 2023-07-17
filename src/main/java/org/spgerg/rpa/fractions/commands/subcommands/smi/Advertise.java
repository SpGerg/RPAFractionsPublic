package org.spgerg.rpa.fractions.commands.subcommands.smi;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.utils.PlayerAdsUtils;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Advertise extends Subcommand {

    public Advertise() {
        super("an", "rpa.fractions.smi.advertise");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды an: \n");
            builder.append("/smi an <сообщение>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Utils.advertises.add(new PlayerAdsUtils(player.getUniqueId().toString(), Utils.advertises.size(), Utils.allArgs(1, args)));

        return true;
    }
}
