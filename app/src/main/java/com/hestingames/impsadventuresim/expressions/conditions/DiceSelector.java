package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.enums.DiceType;

public class DiceSelector implements DiceCondition {
    Expression special;
    public DiceSelector(Expression specialSelector) {
        special = specialSelector;
    }
    @Override
    public DiceType getDice() {
        if(special.evaluate())
            return DiceType.SpecialDice;
        return DiceType.NormalDice;
    }
}
