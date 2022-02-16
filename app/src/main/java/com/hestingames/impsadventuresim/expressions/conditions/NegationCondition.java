package com.hestingames.impsadventuresim.expressions.conditions;

public class NegationCondition implements Expression {
    private final Expression expression;

    public NegationCondition(Expression expr) {
        this.expression = expr;
    }
    @Override
    public boolean evaluate() {
        return !expression.evaluate();
    }
}
