package org.qinlinj.practical.string;

public class KMP3 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        return -1;
    }
}
