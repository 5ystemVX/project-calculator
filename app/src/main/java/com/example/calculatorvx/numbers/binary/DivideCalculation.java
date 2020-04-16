package com.example.calculatorvx.numbers.binary;

import com.example.calculatorvx.numbers.BinaryCalculation;
import com.example.calculatorvx.numbers.ExpressionUnit;

/**
 * 除法类
 */
public class DivideCalculation extends BinaryCalculation {
    /*
    逻辑格式基本为 （operator1） /（operator2）
     */

    /**
     * 化简自身至最简形式
     *
     * @return
     */
    public ExpressionUnit simplify() {
        DivideCalculation result = new DivideCalculation();
        result.setOperator1(this.operator1.simplify());
        result.setOperator2(this.operator2.simplify());
        //化简开始
        //todo 化简过程
        return null;
    }

    @Override
    public ExpressionUnit deepCopy() {
        DivideCalculation add = new DivideCalculation();
        add.setOperator1(this.operator1.deepCopy());
        add.setOperator2(this.operator2.deepCopy());
        return add;
    }
}
