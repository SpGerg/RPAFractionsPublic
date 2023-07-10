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
import org.spgerg.rpa.fractions.PlayerUtils;
import org.spgerg.rpa.fractions.Utils;

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

        if (Main.config.getArrestTime(player) > 0 && !isInRegion(player, Main.config.jail_region_name)) {
            player.teleport(new Location(player.getWorld(),
                    Main.config.arrest_position.get(0),
                    Main.config.arrest_position.get(1),
                    Main.config.arrest_position.get(2)));

            player.sendMessage("Вы арестованы!");
        }
    }

    public static boolean isInRegion(Player p, String region) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(p.getLocation()));
        for (ProtectedRegion pr : set) if (pr.getId().equalsIgnoreCase(region)) return true;
        return false;
    }

}
