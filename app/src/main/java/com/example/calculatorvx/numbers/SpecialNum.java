package com.example.calculatorvx.numbers;

/**
 * 这个类存储各种特殊的常数，这些常数在表达式简化时被保留
 */
public class SpecialNum extends LongNum {
    private SpecialNum(String value) {
        super(value);
    }

    public static SpecialNum PAI() {
        SpecialNum num = new SpecialNum("3.141592653589");
        num.isConserved = true;
        return num;
    }

    public static SpecialNum E() {
        SpecialNum num = new SpecialNum("2.718281828459");
        num.isConserved = true;
        return num;
    }

}
