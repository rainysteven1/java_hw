package com.cuhksz.matrix;

import java.util.*;
import java.util.regex.*;

/**
 * This class reads two matrices (of the same size) from the input, add the two
 * input matrices, and output the result matrix.
 *
 * @author Rainy
 * @since 2024-12-13
 */
public class AddSparseMatrix {

    private static final int MATRIX_NUM = 2;
    private static final Pattern COL_PATTERN = Pattern.compile("(\\d+):(\\S+)");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<List<int[]>>> matrices = new ArrayList<>(MATRIX_NUM);

        int rows = 0, cols = 0;
        for (int i = 0; i < MATRIX_NUM; i++) {
            rows = scanner.nextInt();
            cols = scanner.nextInt();

            scanner.nextLine();
            matrices.add(readMatrix(scanner, rows));
        }

        List<List<int[]>> result = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            result.add(new ArrayList<>());
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
            List<int[]> triples = new ArrayList<>();

            while (colMatcher.find()) {
                int col = Integer.parseInt(colMatcher.group(1));
                int num = Integer.parseInt(colMatcher.group(2));
                triples.add(new int[] { col, num, 0 });
            }

            matrix.add(triples);
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
        List<List<int[]>> matrix1 = matrices.get(0);
        List<List<int[]>> matrix2 = matrices.get(1);

        for (int i = 0; i < matrix1.size(); i++) {
            List<int[]> row1 = matrix1.get(i);
            List<int[]> row2 = matrix2.get(i);
            List<int[]> resultRow = result.get(i);

            int p1 = 0, p2 = 0;
            while (p1 < row1.size() || p2 < row2.size()) {
                boolean isRow1Valid = p1 < row1.size();
                boolean isRow2Valid = p2 < row2.size();

                // Case 1: Take value from the first matrix
                if (!isRow2Valid || (isRow1Valid && row1.get(p1)[0] < row2.get(p2)[0])) {
                    resultRow.add(new int[] { row1.get(p1)[0], row1.get(p1)[1] });
                    p1++;
                }
                // Case 2: Take value from the second matrix
                else if (!isRow1Valid || row1.get(p1)[0] > row2.get(p2)[0]) {
                    resultRow.add(new int[] { row2.get(p2)[0], row2.get(p2)[1] });
                    p2++;
                }
                // Case 3: When the column numbers of the two matrices are the same, they need
                // to be added together
                else {
                    resultRow.add(new int[] { row1.get(p1)[0], row1.get(p1)[1] + row2.get(p2)[1] });
                    p1++;
                    p2++;
                }
            }
        }
    }

}
