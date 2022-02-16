package com.hestingames.impsadventuresim.expressions.conditions;

import com.hestingames.impsadventuresim.expressions.Operator;

public class IntComparer extends ComparisonOperator<Integer> {
    Operator operator;
    public IntComparer(Integer leftValue, Integer rightValue, Operator op) {
        super(leftValue, rightValue);
        operator = op;
    }

    @Override
    public boolean evaluate() {
        int comparison = left.compareTo(right);
        if(comparison == 0 && (operator == Operator.Equal || operator == Operator.LessEqual))
            return true;
//        if(comparison == 1 && )
        return false;
    }
}
