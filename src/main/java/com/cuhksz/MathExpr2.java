package com.cuhksz;

import java.util.*;

public class MathExpr2 {

    private static Map<String, Integer> OPERATORS = new HashMap<>();
    private static final Set<String> FUNCTIONS = new HashSet<>();
    private static final char SPLIT = '.';

    private static void init() {
        OPERATORS.put("(", 0);
        OPERATORS.put("+", 1);
        OPERATORS.put("-", 1);
        OPERATORS.put("*", 2);
        OPERATORS.put("/", 2);
        OPERATORS.put("neg", 3);

        FUNCTIONS.add("sin");
        FUNCTIONS.add("cos");
        FUNCTIONS.add("tan");
        FUNCTIONS.add("sqrt");
    }

    private static void evaluate(Stack<String> s1, Stack<String> s2) {
        String op = s2.pop();
        if (FUNCTIONS.contains(op)) {
            double a = Double.parseDouble(s1.pop());
            double result = switch (op) {
                case "sin" -> Math.sin(a);
                case "cos" -> Math.cos(a);
                case "tan" -> Math.tan(a);
                case "sqrt" -> Math.sqrt(a);
                default -> throw new RuntimeException();
            };
            s1.push(String.valueOf(result));
        } else if (op.equals("neg")) {
            s1.push(String.format("-%s", s1.pop()));
        } else {
            double b = Double.parseDouble(s1.pop());
            double a = Double.parseDouble(s1.pop());
            double result = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> throw new RuntimeException();
            };
            s1.push(String.valueOf(result));
        }
    }

    public static double parse(String str) {
        init();

        List<String> tokens = new ArrayList<>();
        // 操作数栈
        Stack<String> s1 = new Stack<>();
        // 操作符栈
        Stack<String> s2 = new Stack<>();
        StringBuilder number = new StringBuilder();
        StringBuilder function = new StringBuilder();
        // 判断负号作用
        boolean prevWasParen = true;
        int i = 0;

        str = str.replaceAll(" ", "");

        while (i < str.length()) {
            char c = str.charAt(i);

            if (Character.isDigit(c) || c == SPLIT) {
                number.append(c);
                prevWasParen = false;
            } else if (Character.isLetter(c)) {
                function.append(c);
                prevWasParen = false;
            } else if (c == '(' || c == ')') {
                if (!function.isEmpty()) {
                    tokens.add(function.toString());
                    function = new StringBuilder();
                } else if (!number.isEmpty()) {
                    tokens.add(number.toString());
                    number = new StringBuilder();
                }
                tokens.add(Character.toString(c));
                prevWasParen = c == '(';
            } else {
                if (!number.isEmpty()) {
                    tokens.add(number.toString());
                    number = new StringBuilder();
                }
                tokens.add(c == '-' && prevWasParen ? "neg" : Character.toString(c));
                prevWasParen = true;
            }
            i++;
        }

        if (!number.isEmpty()) {
            tokens.add(number.toString());
        }

        for (String token : tokens) {
            if ("(".equals(token) || FUNCTIONS.contains(token)) {
                s2.push(token);
            } else if (")".equals(token)) {
                while (!s2.isEmpty() && !"(".equals(s2.peek())) {
                    evaluate(s1, s2);
                }
                s2.pop();
                if (!s2.isEmpty() && FUNCTIONS.contains(s2.peek())) {
                    evaluate(s1, s2);
                }
            } else if (OPERATORS.containsKey(token)) {
                // 遇到操作符时，比较优先级并执行必要操作
                while (!s2.isEmpty() && OPERATORS.get(token) <= OPERATORS.get(s2.peek())) {
                    evaluate(s1, s2);
                }
                s2.push(token);
            } else {
                s1.push(token);
            }
        }

        while (!s2.isEmpty()) {
            evaluate(s1, s2);
        }

        return Double.parseDouble(s1.pop());
    }

    public static void main(String[] args) {
        // String expr0 = "-3+4/(2.5+3.7)";
        // String expr1 = "(-3+4)/2.5+3.9";
        // String expr2 = "1.2-3.5*5.2-13.2";
        // String expr3 = "(2+ 3.50)*4*sqrt( sin(1.5))";
        // String expr4 = "1+2.0*sin(37+(25*3))";
        // String expr5 = "-sin(3.5-sqrt(4)) + cos(tan(2.5))";
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            double result = parse(input.nextLine());
            System.out.println(String.valueOf(Math.round(result)));
        }
        input.close();
    }
}
