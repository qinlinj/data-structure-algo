package org.qinlinj.practical.string;

public class RK1 {

    public static void main(String[] args) {
        RK1 b = new RK1();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    private int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

    }
}
