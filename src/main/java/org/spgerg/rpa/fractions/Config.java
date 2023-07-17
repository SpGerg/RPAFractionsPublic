package org.spgerg.rpa.fractions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.spgerg.rpa.fractions.model.FractionsModel;
import org.spgerg.rpa.fractions.model.PlayersModel;
import org.spgerg.rpa.fractions.model.serializable.*;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class Config {

    private File configFile;

    private File fractionsFile;

    private File playersFile;

    private YamlConfiguration configYaml;

    public final String arrest_region_name;

    public final String jail_region_name;

    public final List<Integer> arrest_position;

    public final List<Integer> arrest_leave_position;

    public final int one_star_arrest_time;

    public final List<String> search_items;

    private Gson gson;

    public Config() {
        configFile = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
        fractionsFile = new File(Main.instance.getDataFolder() + File.separator +  "fractions.json");
        playersFile = new File(Main.instance.getDataFolder() + File.separator + "players.json");

        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        configYaml = YamlConfiguration.loadConfiguration(configFile);

        if (!fractionsFile.exists()) {
            Main.instance.saveResource("fractions.json", false);
        }

        if (!playersFile.exists()) {
            Main.instance.saveResource("players.json", false);
        }

        arrest_region_name = configYaml.getString("arrest_region_name");
        jail_region_name = configYaml.getString("jail_region_name");
        arrest_position = (List<Integer>) configYaml.getList("arrest_position");
        arrest_leave_position = (List<Integer>) configYaml.getList("arrest_leave_position");
        one_star_arrest_time = configYaml.getInt("one_star_arrest_time");
        search_items = (List<String>) configYaml.getList("search_items");
    }

    public void addDelo(Player player, DeloSerializble deloSerializble) {
        Map<String, PlayerSerializable> players = getPlayers();

        PlayerSerializable playerSerializable = players.get(player.getUniqueId().toString());

        playerSerializable.delos.add(deloSerializble);

        players.replace(player.getUniqueId().toString(), playerSerializable);

        savePlayersMap(players);
    }

    public void removeDelo(Player player, int id) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        PlayerSerializable playerSerializable = players.get(uuid);
        playerSerializable.delos.remove(id);

        for (DeloSerializble delo : playerSerializable.delos) {
            if (delo.id > id) {
                delo.id -= 1;
            }
        }

        players.replace(uuid, playerSerializable);

        savePlayersMap(players);
    }

    public void addPost(PostSerializable postSerializable) {
        Map<String, FractionSerializable> fractions = getFractions();

        if (!fractions.containsKey(postSerializable.fraction)) return;

        if (fractions.get(postSerializable.fraction).posts.containsKey(postSerializable.name)) return;

        fractions.get(postSerializable.fraction).posts.put(postSerializable.name, postSerializable);

        saveFractionsMap(fractions);
    }

    public void createFraction(FractionSerializable fractionSerializable) {
        Map<String, FractionSerializable> fractions = getFractions();

        if (fractions.containsKey(fractionSerializable.name)) return;

        fractions.put(fractionSerializable.name, fractionSerializable);

        saveFractionsMap(fractions);
    }

    public void addWanted(Player player, int amount) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        players.get(uuid).wanted += amount;

        savePlayersMap(players);
    }

    public void removeWanted(Player player) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        players.get(uuid).wanted = 0;

        savePlayersMap(players);
    }

    public void setArrestTime(Player player, int amount) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        players.get(uuid).arrestTime = amount;

        savePlayersMap(players);
    }

    public int getArrestTime(Player player) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        return players.get(uuid).arrestTime;
    }

    public void minusArrestTime(Player player, int amount) {
        Map<String, PlayerSerializable> players = getPlayers();

        String uuid = player.getUniqueId().toString();

        players.get(uuid).arrestTime -= amount;

        savePlayersMap(players);
    }

    public void addMemberToFraction(Player player, String name) {
        Map<String, FractionSerializable> fractions = getFractions();

        Map<String, PlayerSerializable> players = getPlayers();

        PlayerSerializable playerSerializable = null;

        String uuid = player.getUniqueId().toString();

        playerSerializable = players.get(uuid);
        playerSerializable.fraction = name;
        playerSerializable.post = fractions.get(name).default_post;

        if (!fractions.containsKey(name)) return;
        if (fractions.get(name).members.contains(players.get(uuid))) return;

        fractions.get(name).members.add(playerSerializable);

        players.replace(uuid, playerSerializable);

        saveFractionsMap(fractions);
        savePlayersMap(players);
    }

    public void savePlayersMap(Map<String, PlayerSerializable> players) {
        PlayersModel model = new PlayersModel();
        model.setPlayers(players);

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(playersFile.toPath()), "Windows-1251"))) {
            gson.toJson(model, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFractionsMap(Map<String, FractionSerializable> players) {
        FractionsModel model = new FractionsModel();
        model.setFractions(players);

        try(Writer writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(fractionsFile.toPath()), "Windows-1251"))) {
            gson.toJson(model, writer);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FractionSerializable getPlayerFraction(Player player) {
        FractionsModel fractionModel = new FractionsModel();

        String uuid = player.getUniqueId().toString();

        try(Reader reader = new FileReader(fractionsFile)) {
            fractionModel = gson.fromJson(reader, FractionsModel.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return fractionModel.getFractions().get(getPlayers().get(uuid).fraction);
    }

    public Map<String, PlayerSerializable> getPlayers() {
        PlayersModel copy = new PlayersModel();

        try(Reader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(playersFile.toPath()), "Windows-1251"))) {
            copy = gson.fromJson(reader, PlayersModel.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return copy.getPlayers();
    }

    public Map<String, FractionSerializable> getFractions() {
        FractionsModel copy = new FractionsModel();

        try(Reader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(fractionsFile.toPath()), "Windows-1251"))) {
            copy = gson.fromJson(reader, FractionsModel.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return copy.getFractions();
    }
}
