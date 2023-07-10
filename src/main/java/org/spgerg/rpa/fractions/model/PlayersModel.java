package org.spgerg.rpa.fractions.model;

import com.google.gson.annotations.SerializedName;
import org.spgerg.rpa.fractions.model.serializable.PlayerSerializable;

import java.util.HashMap;
import java.util.Map;

public class PlayersModel {

    @SerializedName("players")
    private Map<String, PlayerSerializable> players;

    public void setPlayers(Map<String, PlayerSerializable> players) {
        this.players = players;
    }

    public Map<String, PlayerSerializable> getPlayers() {
        if (players == null) players = new HashMap<>();

        return players;
    }
}
