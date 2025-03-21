package org.qinlinj.practical.string;

public class RK2 {

    public static void main(String[] args) {
        RK2 b = new RK2();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    private int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] hashCodes = new int[m - n + 1];
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));

        for (int i = 1; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        int hashCode = calFirstSubStrHashCode(pattern);

        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                int k = i;
                for (int j = 0; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    if (j == n - 1) return i;
                }
            }
        }
        return -1;
    }

    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            hashCode += (str.charAt(n - i - 1) - 'a');
        }

        return hashCode;
    }

    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i + n - 1) - 'a');
    }
}
