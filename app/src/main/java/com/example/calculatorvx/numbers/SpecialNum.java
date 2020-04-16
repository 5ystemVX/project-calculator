package com.example.calculatorvx.numbers;

/**
 * 这个类存储各种特殊的常数，这些常数在表达式简化时被保留
 */
public class SpecialNum extends LongNum {
    private SpecialNum(String value) {
        super(value);
    }

    public static SpecialNum PAI() {
        return new SpecialNum("3.141592653589");
    }

    public static SpecialNum E() {
        return new SpecialNum("2.718281828459");
    }
}
