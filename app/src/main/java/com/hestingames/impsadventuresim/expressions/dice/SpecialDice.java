package com.hestingames.impsadventuresim.expressions.dice;

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.expressions.conditions.DiceAmountSelector;

public abstract class SpecialDice implements Dice {
    @Override
    public DiceType getType() {
        return DiceType.SpecialDice;
    }
}
