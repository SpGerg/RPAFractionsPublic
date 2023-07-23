package org.spgerg.rpa.fractions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "rpa";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SpGerg";
    }


    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.equals("educate")) {
            return Main.config.getPlayers().get(player.getUniqueId().toString()).educate;
        }
        else if (identifier.equals("armybilet")) {
            return Main.config.getPlayers().get(player.getUniqueId().toString()).isArmy == true ? "Есть" : "Нет";
        }

        return "";
    }
}
