package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.commands.subcommands.police.*;
import org.spgerg.rpa.fractions.commands.subcommands.smi.Advertise;
import org.spgerg.rpa.fractions.commands.subcommands.smi.AdvertiseEdit;
import org.spgerg.rpa.fractions.commands.subcommands.smi.AdvertisePublic;

import java.util.Arrays;
import java.util.List;

public abstract class Subcommand {

    public static List<Subcommand> fractionsSubcommands = Arrays.asList(new Invite(), new Create(), new Add());

    public static List<Subcommand> policeSubcommands = Arrays.asList(new Arrest(), new Cuffs(), new Wanted(), new Search(), new Delo());

    public static List<Subcommand> smiSubcommands = Arrays.asList(new Advertise(), new AdvertiseEdit(), new AdvertisePublic());

    public final String name;

    public final String permission;

    protected Subcommand(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract boolean execute(Player player, String[] args);

}
