package org.qinlinj.practical.string;

public class BF1 {
    public static void main(String[] args) {
        BF1 b = new BF1();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        for (int i = 0; i < m; i++) {
            int k = i;
            for (int j = 0; j < n; j++) {

            }
        }

        return -1;
    }
}
