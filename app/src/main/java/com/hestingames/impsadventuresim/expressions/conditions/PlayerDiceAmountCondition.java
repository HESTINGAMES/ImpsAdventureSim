package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.expressions.Operator;
import com.hestingames.impsadventuresim.simulator.Player;

public class PlayerDiceAmountCondition extends PlayerConditions {
    private final Integer amount;
    private final DiceType type;
    private final Operator operator;

    public PlayerDiceAmountCondition(Player p, DiceType type, Operator op, int amount) {
        super(p);
        this.type = type;
        this.amount = amount;
        this.operator = op;
    }

    @Override
    public boolean evaluate() {
        int r = player.DiceAmount(type).compareTo(amount);
        if(r == 0)
            return operator.getValue() >= 1 && operator.getValue() <= 3;
        if(r < 0)
            return operator.getValue() <= 1;
        return operator.getValue() >= 3;
    }
}
