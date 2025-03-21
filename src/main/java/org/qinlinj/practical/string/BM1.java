package org.qinlinj.practical.string;

public class BM1 {

    public static void main(String[] args) {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    private boolean indexOf(String mainStr, String patternStr) {
        
    }
}
