package org.qinlinj.practical.string;

public class KMP {
    public static void main(String[] args) {

    }

    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        return -1;
    }

    private int[] getNext(char[] pattern) { // O(n^3)

    }
}