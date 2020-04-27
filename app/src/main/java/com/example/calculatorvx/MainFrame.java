package com.example.calculatorvx;

import java.util.ArrayList;
import java.util.Stack;

public class MainFrame {
    private ArrayList<String> buttonRecord = new ArrayList<>();
    private MemModule history = new MemModule();
    private String display = "";

    /**
     * 记录按键的主函数
     *
     * @param button 1
     */
    public void buttonPressed(String button) {
        switch (button) {
            case "reset":
                buttonRecord.clear();
                display = "";
                break;
            case "oops":
                buttonRecord.remove(buttonRecord.size() - 1);
                display = display.substring(0, display.length() - 1);
                break;
            case "=":
                parseInput();
                break;
            default:
                buttonRecord.add(button);
                display += button;
        }
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
                        connector.delete(0, connector.length() - 1);
                    }
                    result.add(unit);
            }
        }
        return result;
    }

    private void buildExpression(ArrayList<String> input) {
        Stack<String> symbols = new Stack<>();
        Stack<String> stack = new Stack<>();

        int cycles = input.size();
        for (int i = 0; i < cycles; i++) {
            String element = input.get(i);
            switch (element) {
                case "-":
                case "+":
                case "(":
                    if (symbols.empty()) {
                        symbols.push(element);
                    } else {
                        if ("(".equals(symbols.peek())) {
                            symbols.push(element);
                        } else {
                            stack.push(symbols.pop());
                            symbols.push(element);
                        }
                    }

                case "*":
                case "/":
                    if (symbols.empty()) {
                        symbols.push(element);
                    } else {
                        switch (symbols.peek()) {
                            case "+":
                            case "-":
                            case "(":
                                symbols.push(element);
                                break;
                            default:
                                stack.push(symbols.pop());
                                symbols.push(element);
                        }
                    }
                case "^":
                    if (symbols.empty()) {
                        symbols.push(element);
                    } else {
                        switch (symbols.peek()) {
                            case "+":
                            case "-":
                            case "(":
                                symbols.push(element);
                                break;
                            default:
                                stack.push(symbols.pop());
                                symbols.push(element);
                        }
                    }
                case ")":
                case "sqrt":
                case "sin":
                case "cos":
                case "tan":
                case "asin":
                case "acos":
                case "atan":
                default://numbers
                    stack.push(element);
            }
        }
    }
}
