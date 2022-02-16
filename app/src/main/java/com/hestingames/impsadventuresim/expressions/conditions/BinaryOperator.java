package com.hestingames.impsadventuresim.expressions.conditions;

public abstract class BinaryOperator implements Expression {
    protected Expression right;
    protected Expression left;

    public BinaryOperator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
