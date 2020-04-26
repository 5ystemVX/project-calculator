package com.example.calculatorvx.numbers.binary;

import android.util.Log;

import com.example.calculatorvx.numbers.ExpressionUnit;
import com.example.calculatorvx.numbers.LongNum;

/**
 * 乘法类
 */
public class MuitiplyCalculation extends ExpressionUnit {
    /*
    逻辑格式基本为 （operator1） * （operator2）
     */
    private ExpressionUnit[] ops = new ExpressionUnit[2];
    private boolean isMul = true;

    private MuitiplyCalculation() {
    }

    public MuitiplyCalculation(ExpressionUnit op1, ExpressionUnit op2, boolean isMul) {
        this.ops[0] = op1;
        this.ops[1] = op2;
        this.isMul = isMul;
    }

    /**
     * 化简自身至最简形式
     *
     * @return
     */
    public ExpressionUnit simplify() {
        ops[0].simplify();
        ops[1].simplify();
        boolean compress0 = this.ops[0].isConserved();
        boolean compress1 = this.ops[1].isConserved();
        if (compress0 && compress1) {
            if (ops[0].getValue().equals(ops[1].getValue())) {
                if (isMul) {
                    return (ExpressionUnit) new PowCalculation(
                            ops[0].deepCopy(), 2);
                } else {
                    return new LongNum("1");
                }
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    @Override
    public ExpressionUnit deepCopy() {
        MuitiplyCalculation a = new MuitiplyCalculation();
        a.ops[0] = this.ops[0].deepCopy();
        a.ops[1] = this.ops[1].deepCopy();
        a.isMul = this.isMul;
        return a;
    }

    @Override
    public boolean isConserved() {
        return false;
    }

    @Override
    public LongNum calculateNum() {
        if (isMul) return LongNum.mul(ops[0].calculateNum(), ops[1].calculateNum());
        else return LongNum.divide(ops[0].calculateNum(), ops[1].calculateNum());
    }

    @Override
    public String getValue() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(ops[0].getValue());
            if (isMul) builder.append(" * ");
            else builder.append(" / ");
            builder.append(ops[1].getValue());
            return builder.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("TAG", "getValue: error");
            return null;
        }
    }
}
