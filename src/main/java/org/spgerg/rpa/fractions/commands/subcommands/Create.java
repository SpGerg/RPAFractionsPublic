package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Config;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.model.serializable.FractionSerializable;

import java.util.ArrayList;
import java.util.HashMap;

public class Create extends Subcommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getPermission() {
        return "rpa.fractons.create";
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды create: \n");
            builder.append("/fractions create <название фракции>.\n");
            builder.append("Название фракции обязательно должно быть на английском!\n");
            builder.append("Стартовую роль можно настроить в файле fractions.json (default_post).\n");
            builder.append("Бля надо уже пожрать, я заебался. @SpGerg\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Main.config.createFraction(new FractionSerializable(args[1], "", "", new ArrayList<>(), new HashMap<>()));

        return true;
    }
}
