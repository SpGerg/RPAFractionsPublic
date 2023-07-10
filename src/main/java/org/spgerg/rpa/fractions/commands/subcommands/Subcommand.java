package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.commands.subcommands.police.*;

import java.util.Arrays;
import java.util.List;

public abstract class Subcommand {

    public static List<Subcommand> fractionsSubcommands = Arrays.asList(new Invite(), new Create(), new Add());

    public static List<Subcommand> policeSubcommands = Arrays.asList(new Arrest(), new Cuffs(), new Wanted(), new Search(), new Delo());

    public abstract String getName();

    public abstract String getPermission();

    public abstract boolean execute(Player player, String[] args);

}
