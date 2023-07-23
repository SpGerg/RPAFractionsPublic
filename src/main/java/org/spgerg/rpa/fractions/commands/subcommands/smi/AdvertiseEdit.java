package org.spgerg.rpa.fractions.commands.subcommands.smi;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.spgerg.rpa.fractions.utils.PlayerAdsUtils;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

import java.util.Arrays;
import java.util.UUID;

public class AdvertiseEdit extends Subcommand {

    public AdvertiseEdit() {
        super("anedit", "rpa.fractions.smi.anedit");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        Inventory inventory = Bukkit.createInventory(player, Utils.getRows(1), "Меню управления обьявлений");

        ItemStack edit = new ItemStack(Material.BLUE_STAINED_GLASS);
        ItemStack accept = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS);

        inventory.setItem(0, edit);
        inventory.setItem(4, accept);
        inventory.setItem(8, cancel);

        player.openInventory(inventory);

        return true;
    }
}
