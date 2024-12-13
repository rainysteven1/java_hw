package com.cuhksz;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Test;

public class TestAddSpraseMatrix {

    @Test
    public void testParse() {
        String input = "2 ";
        Pattern pattern = Pattern.compile("(\\d+):([^\\s]+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String number = matcher.group(1);
            String stringPart = matcher.group(2);
            System.out.println("Number: " + number + ", String Part: " + stringPart);
        }
    }
}
