package com.cuhksz.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class reads two matrices (of the same size) from the input, add the two
 * input matrices, and output the result matrix.
 *
 * @author Rainy
 * @since 2024-12-13
 */
public class AddSpraseMatrix {

    private static final int MATRIX_NUM = 2;
    private static final Pattern COL_PATTERN = Pattern.compile("(\\d+):(\\S+)");

    public static void main(String[] args) {
        int rows = 0, cols = 0;
        Scanner scanner = new Scanner(System.in);
        List<List<List<int[]>>> matrices = new ArrayList<>(MATRIX_NUM);

        for (int i = 0; i < MATRIX_NUM; i++) {
            rows = scanner.nextInt();
            cols = scanner.nextInt();

            scanner.nextLine();
            matrices.add(readMatrix(scanner, rows));
        }


        List<List<int[]>> result = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            result.add(new ArrayList<>()); // 初始化每个子列表
        }

        add(matrices, result);
        printMatrix(result, rows, cols);

        scanner.close();
    }

    private static List<List<int[]>> readMatrix(Scanner scanner, int rows) {
        List<List<int[]>> matrix = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            String input = scanner.nextLine();
            Matcher colMatcher = COL_PATTERN.matcher(input);
            List<int[]> tuples = new ArrayList<>();

            while (colMatcher.find()) {
                int col = Integer.parseInt(colMatcher.group(1));
                int num = Integer.parseInt(colMatcher.group(2));
                tuples.add(new int[]{col, num});
            }

            matrix.add(tuples);
        }

        return matrix;
    }

    private static void printMatrix(List<List<int[]>> result, int rows, int cols) {
        StringBuilder sb;

        System.out.printf("%d %d\n", rows, cols);
        for (int i = 0; i < rows; i++) {
            sb = new StringBuilder();

            sb.append(i);
            for (int[] tuple : result.get(i)) {
                sb.append(String.format(" %d:%d", tuple[0], tuple[1]));
            }
            System.out.println(sb);
        }
    }

    private static void add(List<List<List<int[]>>> matrices, List<List<int[]>> result) {
        addMatrices(matrices.get(0), matrices.get(1), result);
        addMatrices(matrices.get(1), matrices.get(0), result);
    }

    private static void addMatrices(List<List<int[]>> matrix1, List<List<int[]>> matrix2, List<List<int[]>> result) {
        assert matrix1.size() == matrix2.size() && matrix1.size() == result.size();

        for (int i = 0; i < matrix1.size(); i++) {
            if (matrix2.get(i).isEmpty()) {
                result.set(i, new ArrayList<>(matrix1.get(i)));
                continue;
            }

            for (int[] t1 : matrix1.get(i)) {
                boolean flag = false;

                for (int[] t2 : matrix2.get(i)) {
                    if (t1[0] == t2[0]) {
                        flag = true;
                        result.get(i).add(new int[]{t1[0], t1[1] + t2[1]});
                    }
                }

                if (!flag) {
                    result.get(i).add(t1.clone());
                }
            }
        }
    }

}
