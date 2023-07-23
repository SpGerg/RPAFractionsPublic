package org.spgerg.rpa.fractions.events.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.spgerg.rpa.fractions.Conversations;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.PlayerAdsUtils;
import org.spgerg.rpa.fractions.utils.Utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

public class InventoryClick implements Listener {

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        String title = event.getView().getTitle();

        if (inventory == null) return;

        ItemStack[] content = event.getClickedInventory().getContents();

        if (content == null) return;

        ItemStack currentItem = event.getCurrentItem();

        Player player = Bukkit.getPlayer(event.getWhoClicked().getName());

        if (currentItem == null) return;

        if (title.equals("Приглашение во фракцию")) {
            if (currentItem.getType().equals(Material.RED_STAINED_GLASS)) {
                return;
            }
            else {
                Main.config.addMemberToFraction(player, inventory.getItem(4).getItemMeta().getDisplayName());
            }
            player.closeInventory();

            event.setCancelled(true);
        }
        else if (title.equals("Обьявления на изменение")) {
            OfflinePlayer owner = ((SkullMeta)currentItem.getItemMeta()).getOwningPlayer();
            String ownerUUID = owner.getUniqueId().toString();

            ConversationFactory factory = new ConversationFactory(Main.instance);
            factory.withFirstPrompt(Conversations.adEdit);

            Conversation conversation = factory.buildConversation(player);
            conversation.getContext().setSessionData("owner", ownerUUID);
            conversation.getContext().setSessionData("edited", player.getUniqueId().toString());
            conversation.getContext().setSessionData("id", currentItem.getItemMeta().getDisplayName());

            conversation.begin();

            event.setCancelled(true);
        }
        else if (title.equals("Обьявления на публикацию")) {
            int id = 0;

            for (int i = 0;i < Utils.advertises.size();i++) {
                PlayerAdsUtils ad = Utils.advertises.get(i);

                if (ad.id.equals(Integer.valueOf(currentItem.getItemMeta().getDisplayName()))) {
                    id = ad.id;

                    Utils.advertises.remove(i);
                }
            }

            OfflinePlayer owner = ((SkullMeta)currentItem.getItemMeta()).getOwningPlayer();

            //OfflinePlayer edited = Bukkit.getPlayer(UUID.fromString(Utils.advertises.get(index).edited));

            StringBuilder builder = new StringBuilder();
            builder.append("---------------------------\n");
            builder.append("---: " + Utils.advertises.get(id).message + " :---\n");
            //builder.append("Отредактировано: " + edited.getName() + "\n");
            builder.append("Автор: " + owner.getName() + "\n");
            builder.append("---------------------------\n");

            Bukkit.getServer().broadcastMessage(builder.toString());

            Utils.advertises.remove(id);

            for (int i = 0;i < Utils.advertises.size();i++) {
                if (id < Utils.advertises.get(i).id) {
                    Utils.advertises.get(i).id -= 1;
                }
            }

            event.getClickedInventory().remove(currentItem);

            event.setCancelled(true);
        }
        else if (title.equals("Обьявления на отказ")) {
            OfflinePlayer owner = ((SkullMeta)currentItem.getItemMeta()).getOwningPlayer();

            event.getClickedInventory().remove(currentItem);

            int id = 0;

            for (int i = 0;i < Utils.advertises.size();i++) {
                PlayerAdsUtils ad = Utils.advertises.get(i);

                if (ad.id.equals(Integer.valueOf(currentItem.getItemMeta().getDisplayName()))) {
                    id = ad.id;

                    Utils.advertises.remove(i);
                }
            }

            for (int i = 0;i < Utils.advertises.size();i++) {
                if (id < Utils.advertises.get(i).id) {
                    Utils.advertises.get(i).id -= 1;
                }
            }

            event.setCancelled(true);
        }
        else if (title.equals("Меню управления обьявлений")) {
            Material type = currentItem.getType();

            player.closeInventory();

            if (type.equals(Material.BLUE_STAINED_GLASS)) {
                Utils.createInventoryWithAds(player, "Обьявления на изменение");
            }
            else if (type.equals(Material.GREEN_STAINED_GLASS)) {
                Utils.createInventoryWithAds(player, "Обьявления на публикацию");
            }
            else if (type.equals(Material.RED_STAINED_GLASS)) {
                Utils.createInventoryWithAds(player, "Обьявления на отказ");
            }

            event.setCancelled(true);
        }
    }
}
