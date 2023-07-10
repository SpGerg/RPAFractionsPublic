package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.model.serializable.PostSerializable;

import java.util.ArrayList;

public class Add extends Subcommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getPermission() {
        return "rpa.fractions.add";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1 || args.length == 2) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды add: \n");
            builder.append("/fractions add <название фракции> <название должности>\n");
            builder.append("Название должности обязательно должно быть на английском!\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Main.config.addPost(new PostSerializable(args[2], args[1], new ArrayList<>()));

        return true;
    }
}
