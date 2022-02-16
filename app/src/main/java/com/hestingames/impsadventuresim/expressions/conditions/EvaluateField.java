package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.simulator.Board;

public class EvaluateField implements Expression {
    Board board;
    int steps;
    EPositionType position;

    public EvaluateField(Board board, int steps, EPositionType cell) {
        this.board = board;
        this.steps = steps;
        this.position = cell;
    }
    @Override
    public boolean evaluate() {
        IPosition[] b = board.GetBoard();
        for (int i = 1; i <= steps; i++) {
            if(b[(board.PlayerPosition() + i) % b.length].getType() == position) {
                return true;
            }
        }
        return false;
    }
}
