package org.qinlinj.practical.string;

public class KMP3 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] next = getNext(pattern.toCharArray());

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }

            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }

    private int[] getNext(char[] pattern) {
        int n = pattern.length;
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];

        next[0] = -1;

        for (int j = 1; j < n - 1; j++) {
            int pre = next[j - 1];

        }

        return next;
    }
}
