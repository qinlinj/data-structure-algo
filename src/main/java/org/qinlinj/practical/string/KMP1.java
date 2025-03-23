package org.qinlinj.practical.string;

import java.util.*;

public class KMP1 {
    public static void main(String[] args) {
        KMP1 b = new KMP1();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

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

    private int[] getNext(char[] pattern) { // O(n^3)
        int n = pattern.length;
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];
        Arrays.fill(next, -1);

        for (int i = 1; i < n - 1; i++) {
            String goodPrefix = new String(pattern, 0, i + 1);

            for (int j = i; j > 0; j--) {
                String suffix = goodPrefix.substring(j);
                int k;
                for (k = 0; k < suffix.length(); k++) {
                    if (suffix.charAt(k) != goodPrefix.charAt(k)) {
                        break;
                    }
                }

                if (k == suffix.length()) {
                    next[i] = Math.max(k - 1, next[i]);
                }
            }
        }

        return next;
    }
}