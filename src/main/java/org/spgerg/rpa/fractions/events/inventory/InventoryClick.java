package org.spgerg.rpa.fractions.events.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.spgerg.rpa.fractions.Main;

public class InventoryClick implements Listener {

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (event.getView().getTitle() == "Приглашение во фракцию") {
            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());

            if (event.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS)) {
                return;
            }
            else {
                Main.config.addMemberToFraction(player, inventory.getItem(4).getItemMeta().getDisplayName());
            }

            event.setCancelled(true);
            player.closeInventory();
        }
    }

}
