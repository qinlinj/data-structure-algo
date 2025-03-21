package org.qinlinj.practical.string;

import java.util.*;

public class BM1 {

    public static void main(String[] args) {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    private int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        Map<Character, Integer> bc = genBadCharIndexMap(pattern);
        int i = 0;

        
        return -1;
    }

    private Map<Character, Integer> genBadCharIndexMap(String pattern) {

    }
}
