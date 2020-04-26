package com.example.calculatorvx.numbers;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 用于表示超出int上限的超长num的类
 */
public class LongNum extends ExpressionUnit implements ExpressionUnitInterface {

    protected boolean isConserved = false;//简化时是否保留

    private BigDecimal value = new BigDecimal(0);

    public LongNum(String value) {
        this.value = new BigDecimal(value);
    }

    /**
     * 默认构造函数,赋值为0
     */
    public LongNum() {
    }

    public LongNum(BigDecimal value) {
        this.value = value;
    }

    public static LongNum sub(LongNum op1, LongNum op2) {
        BigDecimal result = op1.value.subtract(op2.value);
        return new LongNum(result);
    }

    public boolean isConserved() {
        return isConserved;
    }

    /**
     * 为超长数字赋值<br>
     *
     * @param value 字面值
     * @return 修改是否成功, 恒为true
     */
    public boolean setValue(int value) {
        this.value = new BigDecimal(value);
        return true;
    }

    /**
     * <p>为超长数字赋值，接受10进制纯字面值和科学计数法值<br>
     * (形如 -123.456 或者 2.333e9 )</p>
     *
     * @param value 字面值
     * @return 修改是否成功
     */
    public boolean setValue(String value) {
        try {
            this.value = new BigDecimal(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String toSting() {
        return value.toString();
    }

    @Override
    public String getValue() {
        return this.value.toString();
    }

    /**
     * 取绝对值
     *
     * @param num 输入值
     * @return 值为绝对值的新实例
     */
    public static LongNum abs(LongNum num) {
        LongNum temp = deepCopy(num);
        temp.value = temp.value.abs();
        return temp;
    }

    /**
     * 深度复制
     *
     * @param num 被复制项
     * @return 一模一样的新实例
     */
    public static LongNum deepCopy(LongNum num) {
        LongNum a = new LongNum();
        a.value = new BigDecimal(num.value.toString());
        return a;
    }

    /**
     * 取相反数
     *
     * @param num 输入值
     * @return 值为相反数的新实例
     */
    public static LongNum negate(LongNum num) {
        LongNum temp = deepCopy(num);
        temp.value = temp.value.negate();
        return temp;
    }

    @Override
    public ExpressionUnit deepCopy() {
        return LongNum.deepCopy(this);
    }

    @Override
    public ExpressionUnit simplify() {
        return this;
    }

    public BigDecimal getRaw() {
        return this.value;
    }

    public static LongNum add(LongNum op1, LongNum op2) {
        BigDecimal result = op1.value.add(op2.value);
        return new LongNum(result);
    }

    public static LongNum mul(LongNum op1, LongNum op2) {
        BigDecimal result = op1.value.multiply(op2.value);
        return new LongNum(result);
    }

    public static LongNum divide(LongNum op1, LongNum op2) {
        BigDecimal result = op1.value.divide(op2.value);
        return new LongNum(result);
    }

    public static LongNum pow(LongNum op1, int op2) {
        BigDecimal result = op1.value.pow(op2);
        return new LongNum(result);
    }

    public static LongNum sqrt(LongNum op1) {
        double result = Math.sqrt(op1.value.doubleValue());
        LongNum out = new LongNum();
        out.value = new BigDecimal(result);
        return out;
    }

    @Override
    public LongNum calculateNum() {
        return this;
    }
}
