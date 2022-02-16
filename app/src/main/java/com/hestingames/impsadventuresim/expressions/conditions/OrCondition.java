package com.hestingames.impsadventuresim.expressions.conditions;

public class OrCondition extends BinaryOperator {
    public OrCondition(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public boolean evaluate() {
        return left.evaluate() || right.evaluate();
    }
}
