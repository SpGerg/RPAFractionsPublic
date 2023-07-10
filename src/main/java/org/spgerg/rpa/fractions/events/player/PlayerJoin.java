package org.spgerg.rpa.fractions.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.Utils;
import org.spgerg.rpa.fractions.model.serializable.PlayerSerializable;

import java.util.ArrayList;
import java.util.Map;

public class PlayerJoin implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Map<String, PlayerSerializable> players = Main.config.getPlayers();

        if (players.containsKey(player.getUniqueId().toString())) {
            if (Main.config.getArrestTime(player) > 0) {
                Utils.playerArrestRunnable(player);
            }

            return;
        }

        players.put(player.getUniqueId().toString(), new PlayerSerializable(player.getName(), "", "", 0, 0, new ArrayList<>()));

        Main.config.savePlayersMap(players);
    }
}
