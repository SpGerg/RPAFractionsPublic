package org.spgerg.rpa.fractions.utils;

import org.bukkit.entity.Player;

public class PlayerArrestUtils {
    public Player handCuffsTarget;

    public Player toFollow;


    public PlayerArrestUtils(Player toFollow, Player handCuffsTarget) {
        this.toFollow = toFollow;
        this.handCuffsTarget = handCuffsTarget;
    }
}