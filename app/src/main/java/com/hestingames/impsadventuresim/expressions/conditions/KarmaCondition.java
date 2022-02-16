package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.simulator.Player;

public class KarmaCondition extends PlayerConditions {
    public KarmaCondition(Player p) {
        super(p);
    }

    @Override
    public boolean evaluate() {
        return player.HasKarma();
    }
}
