package org.qinlinj.practical.string;

public class StringTest {
    public static void main(String[] args) {
        String mainStr = "justin, shake your code";
        String patternStr = "your";

        int index = mainStr.indexOf(patternStr);
        System.out.println("Java indexOf res: " + index);
        // BF、RK、BM、KMP
        BF2 bf = new BF2();
        RK3 rk = new RK3();
        BM2 bm = new BM2();
        KMP1 kmp = new KMP1();

        int index1 = mainStr.indexOf(patternStr);
        int index2 = mainStr.indexOf(patternStr);
        int index3 = mainStr.indexOf(patternStr);
        int index4 = mainStr.indexOf(patternStr);
    }
}