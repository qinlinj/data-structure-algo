package org.qinlinj.practical.string;

public class BF2 {
    public static void main(String[] args) {
        BF2 b = new BF2();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        char first = pattern.charAt(0);
        for (int i = 0; i < m; i++) {
            if (mainStr.charAt(i) != first) {
                while (++i < m && mainStr.charAt(i) != first) ;
            }

            if (i < m) {
                int k = i + 1;
                int j = 1;
                
            }
        }

        return -1;
    }
}
