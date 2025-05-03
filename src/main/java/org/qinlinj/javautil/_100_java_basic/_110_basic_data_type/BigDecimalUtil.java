package org.qinlinj.javautil._100_java_basic._110_basic_data_type;

import java.math.BigDecimal;

public class BigDecimalUtil {
    public static void main(String[] args) {
        // using BigDecimal to avoid calculation error
        double a = 1.0;
        double b = 0.9;
        System.out.println(a - b);

        BigDecimal x = new BigDecimal("1.0");
        BigDecimal y = new BigDecimal("0.9");
        System.out.println(x.subtract(y));

        // storage range > double
        double a1 = 1.7976931348623157E308;
        System.out.println(a1);
        System.out.println(Double.MAX_VALUE);

        BigDecimal b1 = new BigDecimal("1.7976931348623157E30891234");
        System.out.println(b1);
    }
}
