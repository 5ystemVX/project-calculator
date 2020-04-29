package com.example.calculatorvx;

import com.example.calculatorvx.numbers.simplify.Div;
import com.example.calculatorvx.numbers.simplify.Expression;
import com.example.calculatorvx.numbers.simplify.Factorial;
import com.example.calculatorvx.numbers.simplify.Minus;
import com.example.calculatorvx.numbers.simplify.Mult;
import com.example.calculatorvx.numbers.simplify.Num;
import com.example.calculatorvx.numbers.simplify.Plus;
import com.example.calculatorvx.numbers.simplify.Pow;
import com.example.calculatorvx.numbers.simplify.Triangle;
import com.example.calculatorvx.numbers.simplify.Var;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MainFrame {
    private boolean secondon = false;
    private String numberBuffer;

    private ArrayList<String> buttonRecord = new ArrayList<>();
    private MemModule history = new MemModule();
    private String display = "";

    public String printSelf() {
        StringBuilder builder = new StringBuilder();
        for (String element : buttonRecord
        ) {
            if (element.length() < 2)
                builder.append(element);
            else {
                if (element.equals("int2"))
                    builder.append("2");
                else {
                    builder.append(" ");
                    builder.append(element);
                    builder.append("(");
                }

            }
        }
        return builder.toString();
    }

    public String printSimply() throws Exception {
        if (this.buttonRecord.isEmpty()) {
            return "";
        }
        try {
            Expression expression = parseExpression(buildExpression(identifyNumbers()));
            this.buttonRecord.clear();
            Expression expression1 = expression.simplify();
            return expression1.toString();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 记录按键的主函数
     *
     * @param button 1
     */


    public void buttonPressed(String button) {
        if (button == null) {
            return;
        }
        switch (button) {
            case "2nd":
                this.secondon = !this.secondon;
                break;
            case "sin":
                if (secondon)
                    buttonRecord.add("as");
                else
                    buttonRecord.add("s");
                break;
            case "cos":
                if (secondon)
                    buttonRecord.add("ac");
                else
                    buttonRecord.add("c");
                break;
            case "tan":
                if (secondon)
                    buttonRecord.add("at");
                else
                    buttonRecord.add("t");
                break;
            case "log":
                if (secondon) {
                    buttonRecord.add("log");
                    buttonRecord.add("10");
                } else {
                    buttonRecord.add("log");
                    buttonRecord.add("e");
                }
                break;
            case "xpow2":
                buttonRecord.add("^");
                buttonRecord.add("int2");
                break;
            case "xpowy":
                buttonRecord.add("^");
                break;
            case "xfracy":
                buttonRecord.add("frac");
                break;
            case "clear":
                buttonRecord.clear();
                display = "";
                break;
            case "oops":
                buttonRecord.remove(buttonRecord.size() - 1);
                display = display.substring(0, display.length() - 1);
                break;
            case "equal":
                break;
            default:
                buttonRecord.add(button);
                display += button;
        }

    }

    private ArrayList<String> identifyNumbers() {
        ArrayList<String> output = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (String unit : this.buttonRecord
        ) {
            switch (unit) {
                case "10":
                    output.add("10");
                    break;
                case "int2":
                    output.add("2");
                    break;
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                case ".":
                    builder.append(unit);
                    break;
                default:
                    if (builder.length() > 0) {
                        output.add(builder.toString());
                        builder.delete(0, builder.length());
                    }
                    output.add(unit);
            }
        }
        if (builder.length() > 0)
            output.add(builder.toString());
        return output;
    }

    public ArrayList<String> parseInput() {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder connector = new StringBuilder();
        for (String unit : buttonRecord
        ) {
            switch (unit) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                case ".":
                    connector.append(unit);
                default:
                    if (connector.length() > 0) {
                        result.add(connector.toString());
                        connector = new StringBuilder();
                    }
                    result.add(unit);
            }
        }
        return result;
    }

    private ArrayList buildExpression(ArrayList<String> input) {
        Stack<String> symbols = new Stack<>();
        ArrayList<String> stack = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        //Character代表字符类，表示对单个字符进行操作，其基本数据类型为char。
        //Integer为复杂数据类型，是int的封装类，其初始值为null
        map.put("(", 5);//定义符号的优先级,从上到下优先级减小
        map.put(")", 5);
        map.put("!", 4);
        map.put("s", 3);//sin
        map.put("c", 3);//cos
        map.put("t", 3);//tan
        map.put("as", 3);//arcsin
        map.put("ac", 3);//arccos
        map.put("at", 3);//arctan
        map.put("^", 2);
        map.put("*", 1);
        map.put("/", 1);
        map.put("frac", 1);
        map.put("+", 0);
        map.put("-", 0);
        int cycles = input.size();
        for (int i = 0; i < cycles; i++) {
            String element = input.get(i);
            if (map.containsKey(element)) {
                if (element.equals(")")) {
                    try {
                        while (!symbols.peek().equals("(")) {
                            stack.add(symbols.pop());
                        }
                        symbols.pop();
                    } catch (EmptyStackException e) {
                        continue;
                    }
                }
                if (symbols.empty()) {
                    symbols.push(element);
                } else if (map.get(symbols.peek()) < map.get(element)) {
                    symbols.push(element);
                } else {
                    stack.add(symbols.pop());
                    symbols.push(element);
                }
            } else {
                stack.add(element);
            }
        }
        while (!symbols.empty()) {
            if (symbols.peek().equals("(")) {
                symbols.pop();
            } else {
                stack.add(symbols.pop());
            }
        }
        return stack;
    }


    private Expression parseExpression(ArrayList<String> suffix) throws Exception {
        ArrayList<Object> start = new ArrayList<>();
        ArrayList<Object> output = new ArrayList<>();
        Expression result;
        for (String element : suffix
        ) {
            if (element.equals("pai") || element.equals("e")) {
                start.add(new Var(element));
                continue;
            }
            try {
                double a = Double.parseDouble(element);
                start.add(new Num(a));
            } catch (NumberFormatException e) {
                start.add(element);
            }
        }
        try {
            while (start.size() > 1) {
                int cycles = start.size();
                for (int i = 0; i < cycles; i++) {
                    if (start.get(i) instanceof String) {
                        //符号
                        String opreator = (String) start.get(i);
                        switch (opreator) {
                            case "!":
                                output.add(new Factorial((int) start.get(output.size() - 1)));
                                output.remove(output.size() - 2);
                                break;
                            case "s":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.SIN));
                                output.remove(output.size() - 2);
                                break;
                            case "c":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.COS));
                                output.remove(output.size() - 2);
                                break;
                            case "t":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.TAN));
                                output.remove(output.size() - 2);
                                break;
                            case "as":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.ARC_SIN));
                                output.remove(output.size() - 2);

                                break;
                            case "ac":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.ARC_COS));
                                output.remove(output.size() - 2);
                                break;
                            case "at":
                                output.add(new Triangle((Expression) output.get(output.size() - 1), Triangle.ARC_TAN));
                                output.remove(output.size() - 2);
                                break;
                            case "+":
                                output.add(new Plus((Expression) output.get(output.size() - 2), (Expression) output.get((output.size() - 1))));
                                output.remove(output.size() - 2);
                                output.remove(output.size() - 2);

                                break;
                            case "-":
                                output.add(new Minus((Expression) output.get(output.size() - 2), (Expression) output.get((output.size() - 1))));
                                output.remove(output.size() - 2);
                                output.remove(output.size() - 2);

                                break;
                            case "*":
                                output.add(new Mult((Expression) output.get(output.size() - 2), (Expression) output.get((output.size() - 1))));
                                output.remove(output.size() - 2);
                                output.remove(output.size() - 2);
                                break;
                            case "frac":
                            case "/":
                                output.add(new Div((Expression) output.get(output.size() - 2), (Expression) output.get((output.size() - 1))));
                                output.remove(output.size() - 2);
                                output.remove(output.size() - 2);
                                break;
                            case "^":
                                output.add(new Pow((Expression) output.get(output.size() - 2), (Expression) output.get((output.size() - 1))));
                                output.remove(output.size() - 2);
                                output.remove(output.size() - 2);
                        }
                    } else {
                        output.add(start.get(i));
                    }
                }
                start = output;
                output = new ArrayList<>();
            }
            return (Expression) start.get(0);
        } catch (Exception e) {
            throw new Exception("invalid expression");
        }
    }
}

