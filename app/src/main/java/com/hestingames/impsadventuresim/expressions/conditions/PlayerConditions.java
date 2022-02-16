package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.simulator.Player;

public abstract class PlayerConditions implements Expression {
    protected Player player;

    public PlayerConditions(Player p) {
        player = p;
    }
}
