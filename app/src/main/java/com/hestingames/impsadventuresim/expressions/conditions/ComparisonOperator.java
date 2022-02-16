package com.hestingames.impsadventuresim.expressions.conditions;

import java.util.Comparator;

public abstract class ComparisonOperator<T> implements Expression {
    T left;
    T right;
    public ComparisonOperator(T leftValue, T rightValue) {
        left = leftValue;
        right = rightValue;
    }
}
