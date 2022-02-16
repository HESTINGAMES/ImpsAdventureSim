package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.enums.ETarotEffectType;
import com.hestingames.impsadventuresim.simulator.Player;

public class PlayerCardCondition extends PlayerConditions {
    ETarotEffectType card;

    public PlayerCardCondition(Player p, ETarotEffectType card) {
        super(p);
        this.card = card;
    }
    @Override
    public boolean evaluate() {
        return player.buff == card;
    }
}
