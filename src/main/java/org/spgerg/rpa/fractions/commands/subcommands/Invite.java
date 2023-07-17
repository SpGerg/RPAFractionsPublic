package org.spgerg.rpa.fractions.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.model.serializable.FractionSerializable;

public class Invite extends Subcommand {
    public Invite() {
        super("invite", "rpa.fractions.invite");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды invite: \n");
            builder.append("/fractions invite <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        Inventory inventory = Bukkit.createInventory(player, Utils.getRows(1), "Приглашение во фракцию");

        FractionSerializable fractionSerializable = Main.config.getPlayerFraction(player);

        if (fractionSerializable == null) {
            player.sendMessage("Ты чё дурак что-ли?");

            return true;
        }

        ItemStack info = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemMeta infoMeta = info.getItemMeta();
        infoMeta.setDisplayName(fractionSerializable.name);
        info.setItemMeta(infoMeta);

        ItemStack accept = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemMeta acceptMeta = accept.getItemMeta();
        acceptMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2Принять"));
        accept.setItemMeta(acceptMeta);

        ItemStack decline = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta declineMeta = accept.getItemMeta();
        declineMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Отказ"));
        decline.setItemMeta(declineMeta);

        inventory.setItem(0, accept);
        inventory.setItem(4, info);
        inventory.setItem(8, decline);

        target.openInventory(inventory);

        return true;
    }
}
