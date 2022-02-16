package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.enums.StrategyEnum;
import com.hestingames.impsadventuresim.positions.SpecialDice;
import com.hestingames.impsadventuresim.simulator.Board;
import com.hestingames.impsadventuresim.simulator.Player;

import java.util.Set;

public class GoToPosition extends DiceAmountSelector {
    Board b;
    EPositionType desiredCell;
    int position;
    public GoToPosition(Player p, Board board, EPositionType cell, StrategyEnum name) {
        super(p, DiceType.SpecialDice, name);
        desiredCell = cell;
        b = board;
    }

    @Override
    public boolean apply() {
        if(!super.apply() || player.HasKarma())
            return false;
        IPosition[] board = b.GetBoard();
        int currentPosition = b.PlayerPosition();
        for (int j = currentPosition + 1; j <= currentPosition + 6; j++) {
            if(desiredCell == board[j % board.length].getType())
            {
                position = j - currentPosition;
                return true;
            }
        }
        return false;
    }

    @Override
    public int get() {
        return position;
    }
}
