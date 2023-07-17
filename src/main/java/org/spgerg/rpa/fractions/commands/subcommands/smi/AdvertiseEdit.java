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
        Inventory inventory = Bukkit.createInventory(player, Utils.getRows(5), "Обьявления");

        for (PlayerAdsUtils ads : Utils.advertises) {
            OfflinePlayer _player = Bukkit.getPlayer(UUID.fromString(ads.uuid));
            ads.edited = player.getUniqueId().toString();

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
            skullMeta.setLore(Arrays.asList(ads.message));
            skullMeta.setOwningPlayer(_player);
            skullMeta.setDisplayName(String.valueOf(ads.id));
            head.setItemMeta(skullMeta);
            inventory.addItem(head);
        }

        player.openInventory(inventory);

        return true;
    }
}
