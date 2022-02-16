package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.enums.StrategyEnum;
import com.hestingames.impsadventuresim.expressions.dice.Dice;
import com.hestingames.impsadventuresim.simulator.Board;
import com.hestingames.impsadventuresim.simulator.Player;

import java.util.function.BinaryOperator;

public abstract class DiceAmountSelector {
    protected DiceType dice;
    protected Expression condition;
    protected Player player;
    protected DiceAmountSelector next;
    protected StrategyEnum name;
    boolean active;

    public DiceAmountSelector(Player p, DiceType dice, StrategyEnum name) {
        player = p;
        this.dice = dice;
        this.name = name;
        active = false;
    }

    public void setCondition(Expression cond) {
        condition = cond;
    }

    public void setNext(DiceAmountSelector next) {
        this.next = next;
    }

    public void setActive(boolean status, StrategyEnum name) {
        if(this.name.equals(name)) {
            active = status;
            return;
        }
        if(next != null)
            next.setActive(status, name);
    }

    public boolean apply() {
        if(!active)
            return false;
        if(condition == null)
            return player.HasDice(dice);
        return player.HasDice(dice) && condition.evaluate();
    }

    public DiceAmountSelector evaluate() {
        if(apply())
            return this;
        if(next == null)
            return null;
        return next.evaluate();
    }

    public int defaultNormal() {
        return 1 + player.simulator.nextInt(6);
    }

    public abstract int get();

    public DiceType diceType() {
        return dice;
    }
}
