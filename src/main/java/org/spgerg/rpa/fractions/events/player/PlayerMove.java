package org.spgerg.rpa.fractions.events.player;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.Utils;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (Utils.playerUtils.containsKey(player)) {
            if (Utils.playerUtils.get(player).toFollow != null) {
                player.teleport(Utils.playerUtils.get(player).toFollow.getLocation());

                return;
            }

            if (Utils.playerUtils.get(player).handCuffsTarget != null) {
                Utils.playerUtils.get(player).handCuffsTarget.teleport(player);
            }
        }

        if (Main.config.getArrestTime(player) > 0 && !Utils.isPlayerInRegion(player, Main.config.jail_region_name)) {
            player.teleport(Utils.listToLocation(player.getWorld(), Main.config.arrest_position));

            player.sendMessage("Вы арестованы!");
        }
    }



}
