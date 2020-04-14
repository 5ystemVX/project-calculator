package com.example.calculatorvx.numbers;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestLongNum {
    private String[] validSequence = {"003", "0", ".456", "123", "-123", "123.456", "-123.456", "1.23e10", "-.23E10", "14500E-3", "14500e3"};
    private String[] result1 = {"3", "0", "0.456", "123", "-123", "123.456", "-123.456", "12300000000", "-2300000000", "14.5", "14500000"};
    private String[] invalidSequence = {
            "",
            "123.",
            "E10",
            "abcde",
            "123.e5",
    };

    @Test
    public void testInput() {
        //测试排错能力
        LongNum test = new LongNum("0");
        boolean flag;
        for (int i = 0; i < validSequence.length; i++) {
            flag = test.setValue(validSequence[i]);
            assertTrue(flag);
            assertEquals(test.printValue(false), result1[i]);
            System.out.println(test.printValue(true));
        }

        for (String number : invalidSequence) {
            flag = test.setValue(number);
            assertFalse(flag);
        }


    }
}
