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
    /**
     * Runs a simple test with predefined strings to verify correct functionality
     * of all algorithms.
     */
    private static void runSimpleTest() {
        String mainStr = "justin, shake your code";
        String patternStr = "your";

        System.out.println("=== Simple Test with Predefined Strings ===");
        System.out.println("Main string: \"" + mainStr + "\"");
        System.out.println("Pattern: \"" + patternStr + "\"");

        // Java's built-in indexOf
        int index = mainStr.indexOf(patternStr);
        System.out.println("Java indexOf result: " + index);

        // Initialize algorithm instances
        BF2 bf = new BF2();
        RK3 rk = new RK3();
        BM2 bm = new BM2();
        KMP1 kmp = new KMP1();

        // Call each algorithm's indexOf method
        int bfResult = bf.indexOf(mainStr, patternStr);
        int rkResult = rk.indexOf(mainStr, patternStr);
        int bmResult = bm.indexOf(mainStr, patternStr);
        int kmpResult = kmp.indexOf(mainStr, patternStr);

        // Print results
        System.out.println("BF result: " + bfResult);
        System.out.println("RK result: " + rkResult);
        System.out.println("BM result: " + bmResult);
        System.out.println("KMP result: " + kmpResult);

        // Verify all results match
        boolean allMatch = (index == bfResult && index == rkResult &&
                index == bmResult && index == kmpResult);
        System.out.println("All results match: " + allMatch);
        System.out.println();
    }
}