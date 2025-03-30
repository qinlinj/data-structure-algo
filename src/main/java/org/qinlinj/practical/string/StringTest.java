package org.qinlinj.practical.string;

import java.util.*;
import java.util.function.*;

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

    // Constants for benchmark configuration
    private static final int DEFAULT_ITERATIONS = 100;
    private static final int[] TEXT_SIZES = {1000, 10000, 100000, 1000000};
    private static final int[] PATTERN_SIZES = {3, 10, 20, 100};

    public static void main(String[] args) {
        // Test with a simple predefined example first
        runSimpleTest();

        // Then run comprehensive performance benchmarks with random data
        runPerformanceBenchmarks();
    }

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
        RK2 rk = new RK2();
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

    /**
     * Runs comprehensive performance benchmarks with random data across various text
     * and pattern sizes.
     */
    private static void runPerformanceBenchmarks() {
        // Initialize algorithm instances
        BF2 bf = new BF2();
        RK2 rk = new RK2();
        BM2 bm = new BM2();
        KMP1 kmp = new KMP1();

        System.out.println("=== Performance Benchmark with Random Strings ===");

        // Test with different text sizes
        for (int textSize : TEXT_SIZES) {
            // Test with different pattern sizes
            for (int patternSize : PATTERN_SIZES) {
                // Skip invalid combinations
                if (patternSize > textSize) continue;

                System.out.println("\nText size: " + textSize + ", Pattern size: " + patternSize);
                System.out.println("---------------------------------------------------");

                // Generate random text and patterns
                TestData[] testData = generateTestData(DEFAULT_ITERATIONS, textSize, patternSize);

                // Benchmark Java's built-in indexOf
                long javaTime = benchmarkJavaIndexOf(testData);
                System.out.printf("Java indexOf: %d ms%n", javaTime);

                // Benchmark BF algorithm
                long bfTime = benchmarkAlgorithm(testData, bf::indexOf);
                System.out.printf("BF algorithm: %d ms (%.2fx Java)%n",
                        bfTime, (double) bfTime / javaTime);

                // Benchmark RK algorithm
                long rkTime = benchmarkAlgorithm(testData, rk::indexOf);
                System.out.printf("RK algorithm: %d ms (%.2fx Java)%n",
                        rkTime, (double) rkTime / javaTime);

                // Benchmark BM algorithm
                long bmTime = benchmarkAlgorithm(testData, bm::indexOf);
                System.out.printf("BM algorithm: %d ms (%.2fx Java)%n",
                        bmTime, (double) bmTime / javaTime);

                // Benchmark KMP algorithm
                long kmpTime = benchmarkAlgorithm(testData, kmp::indexOf);
                System.out.printf("KMP algorithm: %d ms (%.2fx Java)%n",
                        kmpTime, (double) kmpTime / javaTime);

                // Print the fastest algorithm
                String fastest = getFastestAlgorithm(javaTime, bfTime, rkTime, bmTime, kmpTime);
                System.out.println("Fastest algorithm: " + fastest);
            }
        }
    }

    /**
     * Generates an array of test data with random strings.
     * <p>
     * Each test case includes:
     * - A randomly generated text string of specified size
     * - A pattern (either inserted at a random position or generated separately)
     * - The expected index of the pattern in the text
     *
     * @param count       Number of test cases to generate
     * @param textSize    Size of each text string
     * @param patternSize Size of each pattern string
     * @return Array of TestData objects
     */
    private static TestData[] generateTestData(int count, int textSize, int patternSize) {
        TestData[] data = new TestData[count];
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // Every other test case has the pattern actually appear in the text
            boolean insertPattern = (i % 2 == 0);

            // Generate random text
            StringBuilder textBuilder = new StringBuilder(textSize);
            for (int j = 0; j < textSize; j++) {
                // Generate lowercase letters
                char c = (char) ('a' + random.nextInt(26));
                textBuilder.append(c);
            }

            String text = textBuilder.toString();
            String pattern;
            int expectedIndex;

            if (insertPattern) {
                // Insert the pattern at a random position
                int insertPosition = random.nextInt(textSize - patternSize + 1);

                // Generate a random pattern
                StringBuilder patternBuilder = new StringBuilder(patternSize);
                for (int j = 0; j < patternSize; j++) {
                    char c = (char) ('a' + random.nextInt(26));
                    patternBuilder.append(c);
                }
                pattern = patternBuilder.toString();

                // Replace the portion of the text with the pattern
                StringBuilder modifiedTextBuilder = new StringBuilder(text);
                for (int j = 0; j < patternSize; j++) {
                    modifiedTextBuilder.setCharAt(insertPosition + j, pattern.charAt(j));
                }
                text = modifiedTextBuilder.toString();
                expectedIndex = insertPosition;
            } else {
                // Generate a pattern that doesn't appear in the text
                StringBuilder patternBuilder = new StringBuilder(patternSize);
                for (int j = 0; j < patternSize; j++) {
                    // Use uppercase letters which won't be in our lowercase text
                    char c = (char) ('A' + random.nextInt(26));
                    patternBuilder.append(c);
                }
                pattern = patternBuilder.toString();
                expectedIndex = -1;
            }

            data[i] = new TestData(text, pattern, expectedIndex);
        }

        return data;
    }

    /**
     * Benchmarks Java's built-in indexOf method using the test data.
     *
     * @param testData Array of test cases
     * @return Execution time in milliseconds
     */
    private static long benchmarkJavaIndexOf(TestData[] testData) {
        long startTime = System.currentTimeMillis();

        for (TestData data : testData) {
            int result = data.text.indexOf(data.pattern);
            // Verify the result matches the expected index
            assert result == data.expectedIndex :
                    "Java indexOf returned " + result + " but expected " + data.expectedIndex;
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * Benchmarks a string matching algorithm using the test data.
     *
     * @param testData    Array of test cases
     * @param indexOfFunc Function reference to the algorithm's indexOf method
     * @return Execution time in milliseconds
     */
    private static long benchmarkAlgorithm(TestData[] testData,
                                           BiFunction<String, String, Integer> indexOfFunc) {
        long startTime = System.currentTimeMillis();

        for (TestData data : testData) {
            int result = indexOfFunc.apply(data.text, data.pattern);
            // Verify the result matches the expected index
            assert result == data.expectedIndex :
                    "Algorithm returned " + result + " but expected " + data.expectedIndex;
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * Determines which algorithm performed the fastest based on execution times.
     *
     * @param javaTime Execution time for Java's indexOf
     * @param bfTime   Execution time for Brute Force algorithm
     * @param rkTime   Execution time for Rabin-Karp algorithm
     * @param bmTime   Execution time for Boyer-Moore algorithm
     * @param kmpTime  Execution time for KMP algorithm
     * @return Name of the fastest algorithm
     */
    private static String getFastestAlgorithm(long javaTime, long bfTime,
                                              long rkTime, long bmTime, long kmpTime) {
        long minTime = Math.min(Math.min(Math.min(Math.min(javaTime, bfTime), rkTime), bmTime), kmpTime);

        if (minTime == javaTime) return "Java indexOf";
        if (minTime == bfTime) return "Brute Force";
        if (minTime == rkTime) return "Rabin-Karp";
        if (minTime == bmTime) return "Boyer-Moore";
        return "KMP";
    }

    /**
     * Data class representing a test case with a text string, pattern, and expected result.
     */
    private static class TestData {
        String text;
        String pattern;
        int expectedIndex;

        TestData(String text, String pattern, int expectedIndex) {
            this.text = text;
            this.pattern = pattern;
            this.expectedIndex = expectedIndex;
        }
    }
}