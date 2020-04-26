package com.example.calculatorvx.numbers.binary;

import android.util.Log;

import com.example.calculatorvx.numbers.Calculation;
import com.example.calculatorvx.numbers.ExpressionUnit;
import com.example.calculatorvx.numbers.LongNum;

/**
 * 加减法类
 */
public class AddSubCalculation extends Calculation {

    private ExpressionUnit[] ops = new ExpressionUnit[2];
    private boolean isAdd = true;

    private AddSubCalculation() {
    }

    public AddSubCalculation(ExpressionUnit op1, ExpressionUnit op2, boolean add) {
        this.ops[0] = op1;
        this.ops[1] = op2;
        this.isAdd = add;
    }

    public ExpressionUnit deepCopy() {
        AddSubCalculation a = new AddSubCalculation();
        a.ops[0] = this.ops[0].deepCopy();
        a.ops[1] = this.ops[1].deepCopy();
        a.isAdd = this.isAdd;
        return a;
    }

    public boolean isAdd() {
        return isAdd;
    }

    /*@Override
    public ExpressionUnit simplify() {
        ops[0].simplify();
        ops[1].simplify();
        boolean compress0 = this.ops[0].isConserved();
        boolean compress1 = this.ops[1].isConserved();
        if (compress0 && compress1) {
            if (ops[0].getValue().equals(ops[1].getValue())) {
                if (isAdd) {
                    return (ExpressionUnit) new MuitiplyCalculation();//TODO
                } else {
                    return new LongNum("0");
                }
            } else {
                return this;
            }
        } else if (compress0 || compress1) {
            return this;
        } else {
            //重点
            int longNumCount = 0;
            int addCount = 0;
            int multipleCount = 0;
            for (ExpressionUnit ex : ops) {
                if (ex instanceof LongNum) longNumCount++;
                if (ex instanceof AddSubCalculation) addCount++;
                if (ex instanceof MuitiplyCalculation) multipleCount++;
            }
            if (longNumCount == 2) {
                if (isAdd) return LongNum.add((LongNum) ops[0], (LongNum) ops[1]);
                else return LongNum.sub((LongNum) ops[0], (LongNum) ops[1]));
            }
            if (addCount == 2 || longNumCount == 1 && addCount == 1) {
                ArrayList<ExpressionUnit> addsubs
                for (ExpressionUnit ex:ops
                     ) {
                    if(ex instanceof LongNum)
                }
            }

        }
        return null;
    }*/

    @Override
    public ExpressionUnit simplify() {
        ops[0].simplify();
        ops[1].simplify();
        boolean compress0 = this.ops[0].isConserved();
        boolean compress1 = this.ops[1].isConserved();
        if (compress0 && compress1) {
            if (ops[0].getValue().equals(ops[1].getValue())) {
                if (isAdd) {
                    return (ExpressionUnit) new MuitiplyCalculation(
                            new LongNum("2"), ops[0].deepCopy(), true);
                } else {
                    return new LongNum("0");
                }
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    @Override
    public boolean isConserved() {
        return false;
    }

    @Override
    public String getValue() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(ops[0].getValue());
            if (isAdd) builder.append(" + ");
            else builder.append(" - ");
            builder.append(ops[1].getValue());
            return builder.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("TAG", "getValue: error");
            return null;
        }
    }

    @Override
    public LongNum calculateNum() {
        if (isAdd) return LongNum.add(ops[0].calculateNum(), ops[1].calculateNum());
        else return LongNum.sub(ops[0].calculateNum(), ops[1].calculateNum());
    }
}
