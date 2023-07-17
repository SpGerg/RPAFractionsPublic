package org.spgerg.rpa.fractions.commands.subcommands.police;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Search extends Subcommand {

    public Search() {
        super("search", "rpa.fractions.police.search");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды search: \n");
            builder.append("/police search <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден");

            return true;
        }

        for (String name : Main.config.search_items) {
            player.sendMessage(name);
        }

        for (ItemStack itemStack : target.getInventory()) {
            if (itemStack == null) continue;

            if (Main.config.search_items.contains(itemStack.getType().toString())) {
                player.getInventory().addItem(itemStack);
                target.getInventory().remove(itemStack);

                player.sendMessage("У игрока изьято: " + itemStack.getItemMeta().getDisplayName());
                target.sendMessage("У вас изьято: " + itemStack.getItemMeta().getDisplayName());
            }
        }

        return true;
    }
}
