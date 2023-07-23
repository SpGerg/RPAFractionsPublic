package org.spgerg.rpa.fractions.commands.subcommands.criminal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spgerg.rpa.fractions.Main;
import org.spgerg.rpa.fractions.commands.subcommands.Subcommand;
import org.spgerg.rpa.fractions.model.serializable.PlayerSerializable;
import org.spgerg.rpa.fractions.utils.Utils;

import java.util.List;

public class Raid extends Subcommand {

    public Raid() {
        super("raid", "rpa.fractions.criminal.raid");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length <= 1) {
            StringBuilder builder = new StringBuilder();
            builder.append("Использование команды raid: \n");
            builder.append("/criminal raid <тип>\n");

            player.sendMessage(builder.toString());

            return true;
        }

        if (args[0].equals("raid")) {
            if (args[1].equals("start")) {
                player.sendMessage("Рейд начат.");

                new BukkitRunnable() {
                    int count = 0;

                    @Override
                    public void run() {
                        if (count >= 10) {
                            return;
                        }

                        List<Player> playersInRegion = Utils.getPlayersInRegion(Main.config.raid_region_name);

                        for (Player player : playersInRegion) {
                            if (player.hasPermission("rpa.fractions.criminal.give")) {
                                player.getInventory().addItem(new ItemStack(Material.valueOf(Main.config.raid_reward)));
                            }
                        }

                        count++;
                    }
                }.runTaskTimer(Main.instance, 0, 600L);
            }
        }



        return true;
    }
}
