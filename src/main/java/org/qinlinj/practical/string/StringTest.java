package org.qinlinj.practical.string;

public class StringTest {
    public static void main(String[] args) {
        String mainStr = "justin, shake your code";
        String patternStr = "your";

        int index = mainStr.indexOf(patternStr);
        System.out.println(index);
    }
}