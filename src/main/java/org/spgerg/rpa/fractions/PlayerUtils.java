package org.spgerg.rpa.fractions;

import org.bukkit.entity.Player;

public class PlayerUtils {

    public Player handCuffsTarget;

    public Player toFollow;


    public PlayerUtils(Player toFollow, Player handCuffsTarget) {
        this.toFollow = toFollow;
        this.handCuffsTarget = handCuffsTarget;
    }
}
