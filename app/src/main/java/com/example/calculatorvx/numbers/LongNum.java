package com.example.calculatorvx.numbers;

import java.security.spec.InvalidParameterSpecException;
import java.util.regex.Pattern;

/**
 * 用于表示超出int上限的超长num的类
 */
public class LongNum extends ExpressionUnit implements ExpressionUnitInterface {
    /**
     * strLiteralValue<br>
     * 超长数的有效数字,无正负号，无小数点<br>
     * eg："12345678987654321"<br>
     */
    private String strLiteralValue = "0";
    /**
     * blnIsNegative<br>
     * 是否负数，包括0<br>
     */
    private boolean blnIsNegative = false;
    /**
     * intDecimalDigitCount<br>
     * 小数点后位数，值<=0表示整数<br>
     */
    private int intDecimalDigitCount = 0;

    /**
     * intDigitCount<br>
     * 返回总位数（含小数点后），方便计算<br>
     */
    private int DigitCount() {
        return strLiteralValue.length();
    }

    /**
     * 构造函数
     *
     * @param value 数字的字面值
     */
    public LongNum(String value) {
        this.setValue(value);
    }

    /**
     * 默认构造函数,赋值为0
     */
    public LongNum() {
    }

    /**
     * 为超长数字赋值<br>
     *
     * @param value 字面值
     * @return 修改是否成功, 恒为true
     */
    public boolean setValue(int value) {
        blnIsNegative = (value < 0);
        strLiteralValue = String.valueOf(value).replace("-", "");
        intDecimalDigitCount = 0;
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
        String dummy = value.replace(" ", "");//内部复制变量，去空格
        /*
        !!!注意检测非法输入
         */
        //检测正负
        if (dummy.startsWith("-")) {
            blnIsNegative = true;
            dummy = value.substring(1);
        } else {
            blnIsNegative = false;
        }

        String[] patternPack = {    //合法字符串的格式
                "^(0+)|(0*\\.0+)$",//00.0
                "^(\\d+)|(\\d*\\.\\d+)$",//-123，-123.456
                "^((\\d+)|(\\d*\\.\\d+))[eE]-?\\d+$" //123.456E7
        };

        if (Pattern.matches(patternPack[0], dummy)) {
            //处理全是0的串
            this.blnIsNegative = false;
            this.intDecimalDigitCount = 0;
            this.strLiteralValue = "0";
            return true;
        } else if (Pattern.matches(patternPack[1], dummy)) {
            //处理普通串
            if (dummy.contains(".")) {
                this.intDecimalDigitCount = dummy.length() - dummy.indexOf(".") - 1;
                this.strLiteralValue = dummy.replace(".", "").replaceAll("^0*", "");
            } else {
                this.intDecimalDigitCount = 0;
                this.strLiteralValue = dummy.replaceAll("^0*", "");
            }
            //正规化
            while (strLiteralValue.endsWith("0")) {
                intDecimalDigitCount--;
                strLiteralValue = strLiteralValue.substring(0, strLiteralValue.length() - 1);
            }
            return true;
        } else if (Pattern.matches(patternPack[2], dummy)) {
            //处理科学计数法
            String[] parts = dummy.split("[eE]");
            //  指数计数
            int exponent = Integer.parseInt(parts[1]);
            //  处理底数
            if (parts[0].contains(".")) {
                this.intDecimalDigitCount = parts[0].length() - parts[0].indexOf(".") - 1 - exponent;
                this.strLiteralValue = parts[0].replace(".", "").replaceAll("^0*", "");
            } else {
                this.intDecimalDigitCount = -exponent;
                this.strLiteralValue = parts[0].replaceAll("^0*", "");
            }
            //正规化
            while (strLiteralValue.endsWith("0")) {
                intDecimalDigitCount--;
                strLiteralValue = strLiteralValue.substring(0, strLiteralValue.length() - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 测试用方法
     *
     * @return 含有所有内部变量值的字符串
     */
    public String debugDump() {
        StringBuilder builder = new StringBuilder();
        builder.append("LiteralValue-");
        if (blnIsNegative) {
            builder.append('-');
        }
        builder.append(strLiteralValue);
        builder.append(",DecimalDigitCount-");
        builder.append(intDecimalDigitCount);
        return builder.toString();
    }

    @Override
    public String toString() {
        return this.toString(false);
    }

    /**
     * 字符化输出
     *
     * @param in_science 是否以科学计数法的形式输出
     * @return 字符化输出
     */
    public String toString(boolean in_science) {

        if (strLiteralValue.equals("0"))
            return "0";
        StringBuilder temp = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();
        if (blnIsNegative) {
            resultBuilder.append('-');
        }
        if (in_science) {
            resultBuilder.append(strLiteralValue.substring(0, 1));
            if (temp.length() > 1) {
                resultBuilder.append('.');
                resultBuilder.append(strLiteralValue.substring(1).replaceAll("0*$", ""));
            }
            int exponent = DigitCount() - 1 - intDecimalDigitCount;
            resultBuilder.append('e');
            resultBuilder.append(exponent);

            return resultBuilder.toString();
        } else {//
            //前后补0
            for (int i = 0; i < intDecimalDigitCount - DigitCount() + 1; i++) {
                temp.append('0');
            }
            temp.append(strLiteralValue);
            for (int i = 0; i < -intDecimalDigitCount; i++) {
                temp.append('0');
            }
            String finalvalue = temp.toString();

            if (intDecimalDigitCount <= 0) {
                resultBuilder.append(finalvalue);
            } else {
                resultBuilder.append(finalvalue.substring(0, finalvalue.length() - intDecimalDigitCount));
                resultBuilder.append('.');
                resultBuilder.append(finalvalue.substring(finalvalue.length() - intDecimalDigitCount));
            }

            return resultBuilder.toString();
        }
    }

    /**
     * 取绝对值
     *
     * @param value 输入值
     * @return 值为绝对值的新实例
     */
    public static LongNum abs(LongNum value) {
        String a = value.toString(false);
        if (a.startsWith("-")) {
            a = a.substring(1);
        }
        return new LongNum(a);
    }

    /**
     * 深度复制
     *
     * @param num 被复制项
     * @return 一模一样的新实例
     */
    public static LongNum deepCopy(LongNum num) {
        LongNum a = new LongNum();
        a.strLiteralValue = num.strLiteralValue;
        a.blnIsNegative = num.blnIsNegative;
        a.intDecimalDigitCount = num.intDecimalDigitCount;
        return a;
    }

    /**
     * 取相反数
     *
     * @param num 输入值
     * @return 值为相反数的新实例
     */
    public static LongNum reverse(LongNum num) {
        LongNum a = LongNum.deepCopy(num);
        if (!a.strLiteralValue.equals("0"))
            a.blnIsNegative = !a.blnIsNegative;
        return a;
    }

    @Override
    public ExpressionUnit deepCopy() {
        return LongNum.deepCopy(this);
    }

    @Override
    public ExpressionUnit simplify() {
        return this;
    }
}
