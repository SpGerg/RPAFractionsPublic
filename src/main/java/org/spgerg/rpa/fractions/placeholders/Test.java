package org.spgerg.rpa.fractions.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.spgerg.rpa.fractions.Main;

public class Test extends PlaceholderExpansion {

    private final Main plugin;

    public Test(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "test_rpa_placeholder";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SpGerg";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @NotNull boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (player == null || !player.isOnline()) {
            identifier = "";

            return identifier;
        }

        identifier = "Тут чёто написано ))))";

        return identifier;
    }
}
