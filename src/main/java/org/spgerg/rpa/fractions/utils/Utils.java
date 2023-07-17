package org.spgerg.rpa.fractions.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.spgerg.rpa.fractions.Main;

import java.util.*;

public class Utils {

    public static HashMap<Player, PlayerArrestUtils> playerUtils = new HashMap<>();

    public static List<PlayerAdsUtils> advertises = new ArrayList<>();

    public static String allArgs(int start , String[] args){
        StringBuilder temp = new StringBuilder();
        for(int i = start ; i < args.length ; i++){
            temp.append(args[i]).append(" ");
        }
        return temp.toString().trim();
    }

    public static Location listToLocation(World world, List<Integer> list) {
        return new org.bukkit.Location(world,
                list.get(0),
                list.get(1),
                list.get(2));
    }

    public static void playerArrestRunnable(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Main.config.getArrestTime(player) <= 0 || !player.isOnline()) {
                    player.teleport(Utils.listToLocation(player.getWorld(), Main.config.arrest_leave_position));

                    Main.config.removeWanted(player);

                    Main.instance.getServer().getScheduler().cancelTask(this.getTaskId());

                    return;
                }
                Main.config.minusArrestTime(player, 10);
            }
        }.runTaskTimer(Main.instance, 0, 10L);
    }

    public static Integer getRows(int rows) {
        return rows * 9;
    }
}
