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
        
        for (int i = 0; i < m; i++) {
            int k = i;
            for (int j = 0; j < n; j++) {
                if (k < m && pattern.charAt(j) == mainStr.charAt(k)) {
                    k++;
                    if (j == n - 1) return i;
                } else {
                    break;
                }
            }
        }

        return -1;
    }
}
