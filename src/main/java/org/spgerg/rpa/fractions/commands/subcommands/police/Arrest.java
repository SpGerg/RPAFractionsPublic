package org.spgerg.rpa.fractions.commands.subcommands.police;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.utils.Utils;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;

public class Arrest extends Subcommand {
    public Arrest() {
        super("arrest", "rpa.fractions.police.arrest");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length == 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды arrest: \n");
            builder.append("/police arrest <игрок>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            player.sendMessage("Игрок не найден.");

            return true;
        }

        Location loc = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(loc);

        for (ProtectedRegion region : set) {
            if (region.getId().equals(Main.config.arrest_region_name)) {
                target.teleport(Utils.listToLocation(target.getWorld(), Main.config.arrest_position));

                if (Main.config.one_star_arrest_time == 0) {
                    player.sendMessage("Игрок не в розыске!");

                    return true;
                }

                Main.config.setArrestTime(target, Main.config.one_star_arrest_time * Main.config.getPlayers().get(target.getUniqueId().toString()).wanted);

                Utils.playerArrestRunnable(target);

                return true;
            }
            else {
                player.sendMessage("Вы должны находиться в специальном регионе!");

                return true;
            }
        }

        return true;
    }
}
