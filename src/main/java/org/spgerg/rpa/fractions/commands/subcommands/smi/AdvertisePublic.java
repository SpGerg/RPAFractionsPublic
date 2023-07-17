package org.spgerg.rpa.fractions.commands.subcommands.smi;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class AdvertisePublic extends Subcommand {

    public AdvertisePublic() {
        super("anpublic", "rpa.fractions.smi.adpublic");
    }

    @Override
    public boolean execute(Player player, String[] args) {

        new AdvertiseEdit().execute(player, args);

        return true;
    }
}
