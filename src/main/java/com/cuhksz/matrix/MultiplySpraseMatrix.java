package com.cuhksz.matrix;

import java.util.*;
import java.util.regex.*;

/**
 * This class reads two matrices from the input multiplies the two input
 * matrices, and output the result matrix.
 *
 * @author Rainy
 * @since 2024-12-13
 */
public class MultiplySpraseMatrix {

    private static final Pattern COL_PATTERN = Pattern.compile("(\\d+):(\\S+)");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        scanner.nextLine();
        List<List<int[]>> matrix1 = readRowMatrix(scanner, rows1);

        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();
        assert cols1 == rows2;
        scanner.nextLine();
        List<List<int[]>> matrix2 = readColMatrix(scanner, rows2, cols2);

        List<List<int[]>> result = new ArrayList<>(rows1);
        for (int i = 0; i < rows1; i++) {
            result.add(new ArrayList<>());
        }

        multiplyMatrix(matrix1, matrix2, result);

        printMatrix(result, rows1, cols2);

        scanner.close();
    }

    private static List<List<int[]>> readRowMatrix(Scanner scanner, int rows) {
        List<List<int[]>> matrix = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            String input = scanner.nextLine();
            Matcher colMatcher = COL_PATTERN.matcher(input);
            List<int[]> tuples = new ArrayList<>();

            while (colMatcher.find()) {
                int col = Integer.parseInt(colMatcher.group(1));
                int num = Integer.parseInt(colMatcher.group(2));
                tuples.add(new int[] { col, num });
            }

            matrix.add(tuples);
        }

        return matrix;
    }

    private static List<List<int[]>> readColMatrix(Scanner scanner, int rows, int cols) {
        List<List<int[]>> matrix = new ArrayList<>(cols);

        for (int i = 0; i < cols; i++) {
            matrix.add(new ArrayList<>());
        }

        for (int i = 0; i < rows; i++) {
            String input = scanner.nextLine();
            Matcher colMatcher = COL_PATTERN.matcher(input);

            while (colMatcher.find()) {
                int col = Integer.parseInt(colMatcher.group(1));
                int num = Integer.parseInt(colMatcher.group(2));
                matrix.get(col - 1).add(new int[] { i + 1, num });
            }
        }

        return matrix;
    }

    private static void printMatrix(List<List<int[]>> result, int rows, int cols) {
        StringBuilder sb;

        System.out.printf("%d %d\n", rows, cols);
        for (int i = 0; i < rows; i++) {
            sb = new StringBuilder();

            sb.append(i + 1);
            for (int[] tuple : result.get(i)) {
                sb.append(String.format(" %d:%d", tuple[0], tuple[1]));
            }
            System.out.println(sb);
        }
    }

    private static void multiplyMatrix(List<List<int[]>> matrix1, List<List<int[]>> matrix2, List<List<int[]>> result) {
        for (int i = 0; i < matrix1.size(); i++) {
            for (int j = 0; j < matrix2.size(); j++) {
                int p1 = 0, p2 = 0;
                int sum = 0;

                while (p1 < matrix1.get(i).size() && p2 < matrix2.get(j).size()) {
                    int[] t1 = matrix1.get(i).get(p1);
                    int[] t2 = matrix2.get(j).get(p2);

                    if (t1[0] < t2[0]) {
                        p1++;
                    } else if (t2[0] < t1[0]) {
                        p2++;
                    } else {
                        sum += t1[1] * t2[1];
                        p1++;
                        p2++;
                    }
                }

                if (sum != 0) {
                    result.get(i).add(new int[] { j + 1, sum });
                }
            }
        }
    }

}
