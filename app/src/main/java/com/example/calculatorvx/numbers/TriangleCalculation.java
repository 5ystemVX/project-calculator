package com.example.calculatorvx.numbers;

import java.math.BigDecimal;

public class TriangleCalculation extends Calculation {
    private static boolean useRadian = false;
    public static final int SIN = 1, COS = 2, TAN = 3, ASIN = 4, ACOS = 5, ATAN = 6;
    private int type;

    private ExpressionUnit base;

    private TriangleCalculation() {
    }

    public TriangleCalculation(ExpressionUnit base, int mode) {
        this.base = base;
        this.type = mode;
    }

    @Override
    public ExpressionUnit deepCopy() {
        TriangleCalculation a = new TriangleCalculation();
        a.base = this.base.deepCopy();
        a.type = this.type;
        return a;
    }

    @Override
    public ExpressionUnit simplify() {
        this.base.simplify();
        return this;
    }

    @Override
    public boolean isConserved() {
        return true;
    }

    @Override
    public LongNum calculateNum() {
        double basenum = this.base.calculateNum().getRaw().doubleValue();
        double rad;
        if (useRadian) {
            rad = basenum;
        } else {
            rad = basenum / 180 * 3.141592653589;
        }
        switch (type) {
            case SIN:
                return new LongNum(new BigDecimal(Math.sin(rad)));
            case COS:
                return new LongNum(new BigDecimal(Math.cos(rad)));
            case TAN:
                return new LongNum(new BigDecimal(Math.tan(rad)));
            case ASIN:
                return new LongNum(new BigDecimal(Math.asin(basenum)));
            case ACOS:
                return new LongNum(new BigDecimal(Math.acos(basenum)));
            case ATAN:
                return new LongNum(new BigDecimal(Math.atan(basenum)));
        }
        return null;
    }

    @Override
    public String getValue() {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case SIN:
                builder.append("sin(");
            case COS:
                builder.append("cos(");
            case TAN:
                builder.append("tan(");
            case ASIN:
                builder.append("arcsin(");
            case ACOS:
                builder.append("arccos(");
            case ATAN:
                builder.append("arctan(");
        }
        builder.append(base.getValue());
        builder.append(")");
        return builder.toString();
    }
}
