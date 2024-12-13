package com.cuhksz;

import org.junit.*;;

public class TestMathExpr2 {

    @Test
    public void testParse() {
        System.out.println(MathExpr2.parse("-3 + 4 * 2 / ( 1 - 5 )")); // -5
    }

}
