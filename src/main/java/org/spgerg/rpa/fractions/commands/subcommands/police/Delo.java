package org.spgerg.rpa.fractions.commands.subcommands.police;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;
import org.spgerg.rpa.fractions.model.serializable.DeloSerializble;
import org.spgerg.rpa.fractions.model.serializable.PlayerSerializable;

import java.util.Arrays;

public class Delo extends Subcommand {
    public Delo() {
        super("delo", "rpa.fractions.police.delo");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 3) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды delo: \n");
            builder.append("/police delo create <игрок> <причина>\n");
            builder.append("/police delo remove <игрок> <айди>\n");
            builder.append("/police delo show <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[2]);

        String uuid = target.getUniqueId().toString();

        PlayerSerializable targetSerializable = Main.config.getPlayers().get(uuid);

        if (args[1].equals("add")) {
            String reason = Utils.allArgs(3, args);

            Main.config.addDelo(target, new DeloSerializble(uuid, reason, targetSerializable.delos.size()));
        }
        else if(args[1].equals("remove")) {
            Integer id = 0;

            try {
                id = Integer.valueOf(args[3]);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();

                player.sendMessage("Неизвестный айди");

                return true;
            }

            Main.config.removeDelo(target, id);
        }
        else if(args[1].equals("show")) {
            Inventory inventory = Bukkit.createInventory(player, Utils.getRows(5));

            for (DeloSerializble delo : targetSerializable.delos) {
                ItemStack item = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName("Номер: " + delo.id);
                meta.setLore(Arrays.asList(delo.reason));

                item.setItemMeta(meta);

                inventory.addItem(item);
            }

            player.openInventory(inventory);
        }

        return true;
    }
}
