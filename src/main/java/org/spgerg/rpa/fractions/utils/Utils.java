package org.spgerg.rpa.fractions.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.spgerg.rpa.fractions.Main;

import java.util.*;

public class Utils {

    public static HashMap<Player, PlayerArrestUtils> playerUtils = new HashMap<>();

    public static List<PlayerAdsUtils> advertises = new ArrayList<>();

    public static void createInventoryWithAds(Player player, String title) {
        Inventory inventory = Bukkit.createInventory(player, Utils.getRows(5), title);

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
    }

    public static String allArgs(int start , String[] args){
        StringBuilder temp = new StringBuilder();
        for(int i = start ; i < args.length ; i++){
            temp.append(args[i]).append(" ");
        }
        return temp.toString().trim();
    }

    public static boolean isPlayerInRegion(Player p, String region) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(p.getLocation()));
        for (ProtectedRegion pr : set) if (pr.getId().equalsIgnoreCase(region)) return true;
        return false;
    }

    public static List<Player> getPlayersInRegion(String region) {
        List<Player> result = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (isPlayerInRegion(player, region)) {
                result.add(player);
            }
        }

        return result;
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
