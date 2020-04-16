package com.example.calculatorvx.numbers;

/**
 * 二元运算符集合的抽象类
 */
public abstract class BinaryCalculation extends Calculation {
    protected ExpressionUnit operator1 = null;
    protected ExpressionUnit operator2 = null;

    public ExpressionUnit getOperator1() {
        return operator1;
    }

    public ExpressionUnit getOperator2() {
        return operator2;
    }

    public void setOperator1(ExpressionUnit operator1) {
        this.operator1 = operator1;
    }

    public void setOperator2(ExpressionUnit operator2) {
        this.operator2 = operator2;
    }

}
