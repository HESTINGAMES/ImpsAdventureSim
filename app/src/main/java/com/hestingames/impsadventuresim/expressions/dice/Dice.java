package com.hestingames.impsadventuresim.expressions.dice;

import com.hestingames.impsadventuresim.enums.DiceType;

public interface Dice {
    int ThrowResult();
    DiceType getType();
}
