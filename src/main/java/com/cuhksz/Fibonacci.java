package com.cuhksz;

import java.util.Scanner;

/**
 * @author rainy
 * @version 1.0
 * @date 2024-10-11
 * @description
 */
public class Fibonacci {

    public static final int N_MAX = 18;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        int[] array = new int[N_MAX];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < N_MAX; i++) {
            array[i] = array[i - 2] + array[i - 1];
        }

        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            for (int j = n - 1; j >= n - d; j--) {
                System.out.printf(j != n - d ? "%d, " : "%d\n", array[j]);
            }
        }
    }
}


