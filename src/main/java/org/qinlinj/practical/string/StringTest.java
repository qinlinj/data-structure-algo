package org.qinlinj.practical.string;

/**
 * String Matching Algorithms Performance Benchmark
 * <p>
 * This class benchmarks the performance of various string matching algorithms:
 * 1. Java's built-in indexOf
 * 2. Brute Force (BF)
 * 3. Rabin-Karp (RK)
 * 4. Boyer-Moore (BM)
 * 5. Knuth-Morris-Pratt (KMP)
 * <p>
 * The benchmark uses randomly generated strings and patterns to provide a fair
 * comparison across different algorithms and input sizes.
 */
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

        System.out.println("BF res: " + index1);
        System.out.println("RK res: " + index2);
        System.out.println("BM res: " + index3);
        System.out.println("KMP res: " + index4);
    }
}