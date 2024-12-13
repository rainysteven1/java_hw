package com.cuhksz;

import java.util.Scanner;

/**
 * @author rainy
 */
public class AsciiArt1 {

    private static final int HEIGHT = 7;

    private static final String[][] LETTERS = {
            {"   ###    ", "  ## ##   ", " ##   ##  ", "##     ## ", "######### ", "##     ## ", "##     ## "}, // A
            {"########  ", "##     ## ", "##     ## ", "########  ", "##     ## ", "##     ## ", "########  "}, // B
            {" ######  ", "##    ## ", "##       ", "##       ", "##       ", "##    ## ", " ######  "}, // C
            {"########  ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "########  "}, // D
            {"######## ", "##       ", " ##   ##  ", "######   ", "##       ", "##       ", "######## "}, // E
            {"######## ", "##       ", " ##   ##  ", "######   ", "##       ", "##       ", "##       "}, // F
            {" ######   ", " ######   ", "##        ", "##   #### ", "##    ##  ", "##    ##  ", " ######   "}, // G
            {"##     ## ", "##     ## ", "##     ## ", "######### ", "##     ## ", "##     ## ", "##     ## "}, // H
            {"#### ", " ##  ", " ##  ", " ##  ", " ##  ", " ##  ", "#### "}, // I
            {"      ## ", "      ## ", "      ## ", "      ## ", "##    ## ", "##    ## ", " ######  "}, // J
            {"##    ## ", "##   ##  ", "##  ##   ", "#####    ", "##  ##   ", "##   ##  ", "##    ## "}, // K
            {"##       ", "##       ", "##       ", "##       ", "##       ", "##       ", "######## "}, // L
            {"##     ## ", "###   ### ", "#### #### ", "## ### ## ", "##     ## ", "##     ## ", "##     ## "}, // M
            {"##    ## ", "###   ## ", "####  ## ", "## ## ## ", "##  #### ", "##   ### ", "##    ## "}, // N
            {" #######  ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", " #######  "}, // O
            {"########  ", "##     ## ", "##     ## ", "########  ", "##        ", "##        ", "##        "}, // P
            {" #######  ", "##     ## ", "##     ## ", "##     ## ", "##  ## ## ", "##    ##  ", " ##### ## "}, // Q
            {"########  ", "##     ## ", "##     ## ", "########  ", "##   ##   ", "##    ##  ", "##     ## "}, // R
            {" ######  ", "##    ## ", "##       ", " ######  ", "      ## ", "##    ## ", " ######  "}, // S
            {"######## ", "   ##    ", "   ##    ", "   ##    ", "   ##    ", "   ##    ", "   ##    "}, // T
            {"##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", " #######  "}, // U
            {"##     ## ", "##     ## ", "##     ## ", "##     ## ", " ##   ##  ", "  ## ##   ", "   ###    "}, // V
            {"##      ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", " ###  ###  "}, // W
            {"##     ## ", " ##   ##  ", "  ## ##   ", "   ###    ", "  ## ##   ", " ##   ##  ", "##     ## "}, // X
            {"##    ## ", " ##  ##  ", "  ####   ", "   ##    ", "   ##    ", "   ##    ", "   ##    "}, // Y
            {"######## ", "     ##  ", "    ##   ", "   ##    ", "  ##     ", " ##      ", "######## "} // Z
    };
    static Scanner scanner = new Scanner(System.in);

    private static char process(String[] input, int begin, int end) {
        char output = '0';

        for (int i = 0; i < LETTERS.length; i++) {
            boolean flag = true;
            for (int j = 0; j < HEIGHT; j++) {
                // 判断每一行的字符串是否与矩阵中的字符串相同
                flag = flag & (input[j].substring(begin, end).equals(LETTERS[i][j]));
            }
            if (flag) {
                output = (char) (i + 65);
                break;
            }
        }

        return output;
    }


    public static void main(String[] args) {
        int numWords = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numWords; i++) {
            scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            String[] asciiArt = new String[HEIGHT];
            for (int j = 0; j < HEIGHT; j++) {
                asciiArt[j] = scanner.nextLine();
            }
            int width = asciiArt[0].length(), begin, end = 0;

            for (int j = 0; j < width; j++) {
                // 获取间隔的索引
                boolean isGap = true;
                for (int k = 0; k < HEIGHT; k++) {
                    if (asciiArt[k].charAt(j) != ' ') {
                        isGap = false;
                        break;
                    }
                }
                // 传递索引给process函数
                if (isGap) {
                    begin = end;
                    end = j + 1;
                    sb.append(process(asciiArt, begin, end));
                }
            }

            System.out.println(sb);
        }
        scanner.close();
    }
}
