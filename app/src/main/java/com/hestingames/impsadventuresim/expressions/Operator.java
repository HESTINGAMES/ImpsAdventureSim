package com.hestingames.impsadventuresim.expressions;

public enum Operator {
    Less(0),
    LessEqual(1),
    Equal(2),
    Greater(3),
    GreaterEqual(4);

    private Integer id;

    Operator(int value) {
        this.id = value;
    }

    public Integer getValue() {
        return id;
    }
}
