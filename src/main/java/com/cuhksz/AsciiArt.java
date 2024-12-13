package com.cuhksz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author rainy
 * @version 1.0
 * @date 2024-10-30
 */
public class AsciiArt {

    private static final int HEIGHT = 7;

    private static final HashMap<Integer, Integer> MAP = new HashMap<>();

    private static final String INPUT_FILE = "ascii_art/sample_input.txt";

    private static void initMap() {
        MAP.put(296, 0);
        MAP.put(607, 1);
        MAP.put(114, 2);
        MAP.put(282, 3);
        MAP.put(411, 4);
        MAP.put(180, 5);
        MAP.put(264, 6);
        MAP.put(126, 7);
        MAP.put(78, 8);
        MAP.put(63, 9);
        MAP.put(90, 10);
        MAP.put(55, 11);
        MAP.put(197, 12);
        MAP.put(214, 13);
        MAP.put(248, 14);
        MAP.put(222, 15);
        MAP.put(306, 16);
        MAP.put(314, 17);
        MAP.put(311, 18);
        MAP.put(301, 19);
        MAP.put(108, 20);
        MAP.put(129, 21);
        MAP.put(143, 22);
        MAP.put(186, 23);
        MAP.put(135, 24);
        MAP.put(280, 25);
    }

    private static int sumRow(char[][] letters, int row, int front, int back) {
        int sum = 0;
        for (int i = front; i < back; i++) {
            sum += letters[row][i] == ' ' ? 0 : 1;
        }
        return sum;
    }

    private static int sumColumn(char[][] letters, int column) {
        int sum = 0;
        for (int i = 0; i < HEIGHT; i++) {
            sum += letters[i][column] == ' ' ? 0 : 1;
        }
        return sum;
    }

    private static String processLetters(char[][] letters) {
        int width = letters[0].length, front, back = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            boolean isGap = true;
            for (int j = 0; j < HEIGHT; j++) {
                if (letters[j][i] != ' ') {
                    isGap = false;
                    break;
                }
            }
            if (isGap) {
                front = back + 1;
                back = i;
                int f1 = sumRow(letters, 0, front, back) + sumRow(letters, HEIGHT - 1, front, back);
                int f2 = sumRow(letters, 3, front, back) + sumRow(letters, 4, front, back);
                int f3 = sumColumn(letters, front + 3);
                int f4 = f1 * f2 * f3 + f1 + f2 + f3;
                sb.append((char) (MAP.get(f4) + 65));
            }
        }
        return sb.toString();
    }

    private static void readSampleInput() {
        try (InputStream input = AsciiArt.class.getClassLoader().getResourceAsStream(INPUT_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            int numWords = Integer.parseInt(reader.readLine());
            String[] words = new String[numWords];
            char[][] letters = new char[HEIGHT][];
            int wCount = 0, hCount = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    letters[hCount++] = line.toCharArray();
                    if (hCount == HEIGHT) {
                        words[wCount++] = processLetters(letters);
                        letters = new char[HEIGHT][];
                        hCount = 0;
                    }
                }
            }
            for (int i = 0; i < numWords; i++) {
                System.out.printf("%s\n\n", words[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initMap();
        readSampleInput();
    }
}
