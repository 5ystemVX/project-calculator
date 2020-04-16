package com.example.calculatorvx.numbers;

/**
 * 运算基类
 * 计算器中各种单元运算的基类，根据不同的运算结构单独实现子类
 */

public abstract class Calculation extends ExpressionUnit implements ExpressionUnitInterface {
    public abstract ExpressionUnit simplify();
}
