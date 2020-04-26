package com.example.calculatorvx.numbers;

public interface ExpressionUnitInterface {
    public ExpressionUnit deepCopy();

    public ExpressionUnit simplify();

    public boolean isConserved();

    public LongNum calculateNum();

    public String getValue();
}
