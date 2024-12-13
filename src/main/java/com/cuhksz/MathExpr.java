package com.cuhksz;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author rainy
 * @version 1.0
 * @date 2024-10-12
 */
public class MathExpr {
    static Scanner input = new Scanner(System.in);

    static final int MIN = 0;

    static final int MAX = 500;

    static final String[] OPERATORS = new String[]{"+", "-", "*", "/"};

    private static boolean checkInteger(int n) {
        return n >= MIN && n <= MAX;
    }

    private static boolean checkOperator(String s) {
        return Arrays.asList(OPERATORS).contains(s);
    }

    public static void parseLine(String s1, String s2, String s3) {
        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s3);
        assert checkInteger(a) && checkInteger(b);
        assert checkOperator(s2);

        int result;
        switch (s2) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            default -> {
                if (b != 0) {
                    result = Math.floorDiv(a, b);
                } else {
                    System.out.println("invalid");
                    return;
                }
            }
        }
        System.out.printf("%d %s %d = %d\n", a, s2, b, result);
    }

    public static void main(String[] args) throws Exception {
        int lineNumber = Integer.parseInt(input.nextLine());
        for (int i = 0; i < lineNumber; i++) {
            String s = input.nextLine();
            String[] t = s.split(" ");
            MathExpr.parseLine(t[0], t[1], t[2]);
        }
    }
}
