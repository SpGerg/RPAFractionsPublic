package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.commands.subcommands.criminal.Raid;
import org.spgerg.rpa.fractions.commands.subcommands.criminal.Rob;
import org.spgerg.rpa.fractions.commands.subcommands.police.*;
import org.spgerg.rpa.fractions.commands.subcommands.school.Educate;
import org.spgerg.rpa.fractions.commands.subcommands.smi.Advertise;
import org.spgerg.rpa.fractions.commands.subcommands.smi.AdvertiseEdit;

import java.util.Arrays;
import java.util.List;

public abstract class Subcommand {

    public static List<Subcommand> fractionsSubcommands = Arrays.asList(new Invite(), new Create(), new Add(), new Set());

    public static List<Subcommand> policeSubcommands = Arrays.asList(new Arrest(), new Cuffs(), new Wanted(), new Search(), new Delo());

    public static List<Subcommand> schoolSubcommands = Arrays.asList(new Educate());

    public static List<Subcommand> smiSubcommands = Arrays.asList(new Advertise(), new AdvertiseEdit());

    public static List<Subcommand> criminalSubcommands = Arrays.asList(new Raid(), new Rob());

    public final String name;

    public final String permission;

    protected Subcommand(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public abstract boolean execute(Player player, String[] args);

}
