package org.spgerg.rpa.fractions.events.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.Utils;

public class PlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (Utils.isPlayerInRegion(player, Main.config.radio_region_name)) {
            Bukkit.broadcastMessage("Радио[" + player.getName() + "]: " + event.getMessage());

            event.setCancelled(true);
        }
    }

}
