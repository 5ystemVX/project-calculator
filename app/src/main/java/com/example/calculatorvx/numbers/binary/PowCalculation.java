package com.example.calculatorvx.numbers.binary;

import com.example.calculatorvx.numbers.Calculation;
import com.example.calculatorvx.numbers.ExpressionUnit;
import com.example.calculatorvx.numbers.LongNum;

public class PowCalculation extends Calculation {

    private ExpressionUnit base;
    private int top;

    public PowCalculation(ExpressionUnit base, int top) {
        this.base = base;
        this.top = top;
    }

    @Override
    public ExpressionUnit deepCopy() {
        PowCalculation a = new PowCalculation();
        a.top = this.top;
        a.base = this.base.deepCopy();
        return a;
    }

    @Override
    public ExpressionUnit simplify() {
        if (base instanceof LongNum) {
            return this.calculateNum();
        } else {
            return this;
        }
    }

    @Override
    public boolean isConserved() {
        return false;
    }

    @Override
    public LongNum calculateNum() {
        return LongNum.pow(base.calculateNum(), top);
    }

    @Override
    public String getValue() {
        return "(" + base.getValue() + ")^" + String.valueOf(top);
    }
}
