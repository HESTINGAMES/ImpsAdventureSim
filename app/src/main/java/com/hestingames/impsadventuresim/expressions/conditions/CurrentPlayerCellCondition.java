package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.simulator.Player;

public class CurrentPlayerCellCondition extends PlayerConditions {
    int position;
    public CurrentPlayerCellCondition(Player p, int position) {
        super(p);
        this.position = position;
    }

    @Override
    public boolean evaluate() {
        return player.PlayerPosition() == position;
    }
}
