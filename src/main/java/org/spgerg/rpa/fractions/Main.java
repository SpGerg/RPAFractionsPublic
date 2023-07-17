package org.spgerg.rpa.fractions;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.spgerg.rpa.fractions.commands.*;
import org.spgerg.rpa.fractions.events.inventory.InventoryClick;
import org.spgerg.rpa.fractions.events.player.PlayerJoin;
import org.spgerg.rpa.fractions.events.player.PlayerMove;
import org.spgerg.rpa.fractions.placeholders.Test;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static Config config;

    public static Economy econ = null;
    public static Chat chat = null;

    @Override
    public void onEnable() {
        instance = this;

        config = new Config();

        instance.getCommand("fractions").setExecutor(new Fractions());
        instance.getCommand("police").setExecutor(new Police());
        instance.getCommand("smi").setExecutor(new Smi());

        instance.getCommand("leader").setExecutor(new Leader());
        instance.getCommand("radio").setExecutor(new Radio());
        instance.getCommand("radio-nrp").setExecutor(new RadioNonRp());
        instance.getCommand("nrp").setExecutor(new NonRp());
        instance.getCommand("whisper").setExecutor(new Whisper());
        instance.getCommand("shout").setExecutor(new Shout());

        instance.getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        instance.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        instance.getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        saveDefaultConfig();

        if (!setupEconomy()) {
            getLogger().info("Vault не найден.");

            getServer().getPluginManager().disablePlugin(this);

            return;
        }
        setupChat();
        setupPlaceholders();
    }

    private void setupPlaceholders() {
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            //Registering placeholder will be use here
            new Test(this).register();
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
