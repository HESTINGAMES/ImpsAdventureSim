package com.hestingames.impsadventuresim.expressions.conditions;

public class AndCondition extends BinaryOperator {
    public AndCondition(Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public boolean evaluate() {
        return left.evaluate() && right.evaluate();
    }
}
