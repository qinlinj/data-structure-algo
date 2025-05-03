package org.qinlinj.javautil._100_java_basic._120_operator;

public class TernaryOperatorUtil {
    public static void main(String[] args) {
        // (true/false) ? (true result A) : (false result B)
        // int a = (false ? 2 : 3)
        // System.out.println(a);
        int a = 2;
        int b = 3;
        int c = (a > b) ? a : b;
        System.out.println(c);

        int x = Math.max(5, 6);
        System.out.println(x);
    }
}
