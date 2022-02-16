package com.hestingames.impsadventuresim.expressions.dice;

import com.hestingames.impsadventuresim.enums.DiceType;

import java.util.Random;

public class NormalDice implements Dice {
    Random random;
    public NormalDice() {
        random = new Random();
    }
    @Override
    public int ThrowResult() {
        return 1 + random.nextInt(6);
    }

    @Override
    public DiceType getType() {
        return DiceType.NormalDice;
    }
}
