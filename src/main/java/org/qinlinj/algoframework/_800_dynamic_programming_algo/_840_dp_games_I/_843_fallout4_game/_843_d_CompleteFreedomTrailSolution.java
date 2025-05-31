package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

/**
 * COMPLETE FREEDOM TRAIL SOLUTION WITH COMPREHENSIVE ANALYSIS
 * <p>
 * Problem: Given a circular ring of characters and a key string,
 * find minimum operations to spell the key by rotating ring and pressing button.
 * <p>
 * Solution Approach:
 * 1. DP State: (current_ring_position, current_key_index)
 * 2. Transition: For each occurrence of key[j], try both rotation directions
 * 3. Cost: rotation_steps + 1 (button press) + future_cost
 * 4. Optimization: Memoization to avoid recomputing same states
 * <p>
 * Key Insights:
 * - Multiple occurrences of same character create choice points
 * - Must consider global optimization, not just local minimum distance
 * - Circular nature means rotation can go both clockwise/counterclockwise
 * <p>
 * Time Complexity: O(ring.length × key.length × avg_char_occurrences)
 * Space Complexity: O(ring.length × key.length) for memoization
 * <p>
 * LeetCode 514: Freedom Trail (Hard)
 */

import java.util.*;

public class _843_d_CompleteFreedomTrailSolution {

    /**
     * Main demonstration method
     */
    public static void main(String[] args) {
        // Run comprehensive tests
        TestSuite testSuite = new TestSuite();
        testSuite.runAllTests();

        System.out.println("=".repeat(60));

        // Educational insights
        EducationalDemo.demonstrateKeyInsights();

        System.out.println("\n=== SUMMARY ===");
        System.out.println("Freedom Trail teaches us:");
        System.out.println("1. Circular structures need special distance calculations");
        System.out.println("2. Multiple choices per state require careful optimization");
        System.out.println("3. Global optimization often differs from local greedy choices");
        System.out.println("4. Memoization transforms exponential to polynomial complexity");
        System.out.println("5. State design determines solution correctness and efficiency");
    }

    /**
     * Main solution class for Freedom Trail problem
     */
    public static class FreedomTrailSolver {
        // Character to positions mapping for quick lookup
        private Map<Character, List<Integer>> charToIndex;
        // Memoization table to store computed results
        private int[][] memo;

        /**
         * Main entry point for solving Freedom Trail problem
         * @param ring circular ring of characters
         * @param key target string to spell
         * @return minimum operations needed
         */
        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();

            // Initialize memoization table
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            // Build character to positions mapping
            buildCharacterMap(ring);

            // Start DP from position 0 (12 o'clock) and key index 0
            return dp(ring, 0, key, 0);
        }

        /**
         * Build mapping from characters to their positions in the ring
         */
        private void buildCharacterMap(String ring) {
            charToIndex = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
        }

        /**
         * DP function with memoization
         * @param ring the circular ring string
         * @param i current position of pointer on ring
         * @param key the target string to spell
         * @param j current index in key string
         * @return minimum operations to spell key[j..] starting from ring[i]
         */
        private int dp(String ring, int i, String key, int j) {
            // Base case: finished spelling the entire key
            if (j == key.length()) {
                return 0;
            }

            // Check memoization table
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            int n = ring.length();
            int result = Integer.MAX_VALUE;
            char targetChar = key.charAt(j);

            // Try all positions where target character appears
            List<Integer> positions = charToIndex.get(targetChar);
            for (int k : positions) {
                // Calculate rotation cost to reach position k from position i
                int rotationCost = calculateRotationCost(i, k, n);

                // Recursively solve for remaining key string
                int futureCost = dp(ring, k, key, j + 1);

                // Total cost = rotation + button press + future operations
                int totalCost = rotationCost + 1 + futureCost;
                result = Math.min(result, totalCost);
            }

            // Store result in memoization table
            memo[i][j] = result;
            return result;
        }

        /**
         * Calculate minimum rotation steps between two positions on circular ring
         * @param from starting position
         * @param to target position
         * @param ringSize size of the ring
         * @return minimum rotation steps (clockwise or counterclockwise)
         */
        private int calculateRotationCost(int from, int to, int ringSize) {
            int clockwise = Math.abs(to - from);
            int counterclockwise = ringSize - clockwise;
            return Math.min(clockwise, counterclockwise);
        }
    }

    /**
     * Enhanced solution with detailed tracing and analysis
     */
    public static class TracingFreedomTrailSolver extends FreedomTrailSolver {
        private boolean enableTracing = false;
        private int recursionDepth = 0;

        public TracingFreedomTrailSolver(boolean enableTracing) {
            this.enableTracing = enableTracing;
        }

        @Override
        public int findRotateSteps(String ring, String key) {
            if (enableTracing) {
                System.out.println("=== Solving Freedom Trail ===");
                System.out.println("Ring: \"" + ring + "\"");
                System.out.println("Key: \"" + key + "\"");
                System.out.println();
            }
            return super.findRotateSteps(ring, key);
        }

        protected int dp(String ring, int i, String key, int j) {
            if (enableTracing) {
                String indent = "  ".repeat(recursionDepth);
                System.out.println(indent + "dp(i=" + i + ", j=" + j + ") -> " +
                        "ring[" + i + "]='" + ring.charAt(i) + "', " +
                        "remaining=\"" + key.substring(j) + "\"");
            }

            recursionDepth++;
            int result = super.dp(ring, i, key, j);
            recursionDepth--;

            if (enableTracing) {
                String indent = "  ".repeat(recursionDepth);
                System.out.println(indent + "-> returns " + result);
            }

            return result;
        }

        protected int calculateRotationCost(int from, int to, int ringSize) {
            int cost = super.calculateRotationCost(from, to, ringSize);

            if (enableTracing) {
                String indent = "  ".repeat(recursionDepth + 1);
                int clockwise = Math.abs(to - from);
                int counterclockwise = ringSize - clockwise;
                String direction = (clockwise <= counterclockwise) ? "clockwise" : "counterclockwise";
                System.out.println(indent + "Rotate from " + from + " to " + to +
                        ": " + cost + " steps " + direction);
            }

            return cost;
        }
    }

    /**
     * Comprehensive test suite
     */
    public static class TestSuite {

        public void runAllTests() {
            System.out.println("=== Freedom Trail Test Suite ===\n");

            testBasicExample();
            testEdgeCases();
            testComplexExample();
            performanceTest();
            analyzeComplexity();
        }

        private void testBasicExample() {
            System.out.println("1. BASIC EXAMPLE TEST");
            System.out.println("--------------------");

            FreedomTrailSolver solver = new FreedomTrailSolver();
            String ring = "godding";
            String key = "gd";

            int result = solver.findRotateSteps(ring, key);
            System.out.println("Ring: \"" + ring + "\"");
            System.out.println("Key: \"" + key + "\"");
            System.out.println("Result: " + result);
            System.out.println("Expected: 4");
            System.out.println("Test passed: " + (result == 4));

            // Show detailed trace
            System.out.println("\nDetailed trace:");
            TracingFreedomTrailSolver tracingSolver = new TracingFreedomTrailSolver(true);
            tracingSolver.findRotateSteps(ring, key);
            System.out.println();
        }

        private void testEdgeCases() {
            System.out.println("2. EDGE CASES TEST");
            System.out.println("------------------");

            FreedomTrailSolver solver = new FreedomTrailSolver();

            // Single character cases
            testCase(solver, "a", "a", 1, "Single char, no rotation");
            testCase(solver, "ab", "b", 2, "Single char, one rotation");

            // Multiple occurrences
            testCase(solver, "abcabc", "abc", 4, "Multiple occurrences");
            testCase(solver, "ababab", "abab", 4, "Alternating pattern");

            // Circular optimization
            testCase(solver, "abcdefg", "ga", 4, "Circular wrapping");

            System.out.println();
        }

        private void testComplexExample() {
            System.out.println("3. COMPLEX EXAMPLE TEST");
            System.out.println("-----------------------");

            FreedomTrailSolver solver = new FreedomTrailSolver();
            String ring = "caotmcaataijjxi";
            String key = "oatjiioicitatajtijciocjcaaxaaatmctxjjjjtxjjjjtcaacttaxmaacjcocaja";

            System.out.println("Ring: \"" + ring + "\" (length: " + ring.length() + ")");
            System.out.println("Key: \"" + key + "\" (length: " + key.length() + ")");

            long startTime = System.nanoTime();
            int result = solver.findRotateSteps(ring, key);
            long endTime = System.nanoTime();

            System.out.println("Result: " + result);
            System.out.println("Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            System.out.println();
        }

        private void testCase(FreedomTrailSolver solver, String ring, String key,
                              int expected, String description) {
            int result = solver.findRotateSteps(ring, key);
            System.out.printf("%-25s: ring=\"%s\", key=\"%s\" -> %d (expected %d) %s\n",
                    description, ring, key, result, expected,
                    result == expected ? "✓" : "✗");
        }

        private void performanceTest() {
            System.out.println("4. PERFORMANCE TEST");
            System.out.println("-------------------");

            FreedomTrailSolver solver = new FreedomTrailSolver();

            // Generate test cases of increasing size
            int[] ringSizes = {10, 20, 50, 100};
            int[] keySizes = {10, 20, 50, 100};

            for (int ringSize : ringSizes) {
                for (int keySize : keySizes) {
                    String ring = generateRandomRing(ringSize);
                    String key = generateRandomKey(keySize, ring);

                    long startTime = System.nanoTime();
                    int result = solver.findRotateSteps(ring, key);
                    long endTime = System.nanoTime();

                    double timeMs = (endTime - startTime) / 1_000_000.0;
                    System.out.printf("Ring=%d, Key=%d: %d ops in %.2f ms\n",
                            ringSize, keySize, result, timeMs);
                }
            }
            System.out.println();
        }

        private String generateRandomRing(int size) {
            StringBuilder sb = new StringBuilder();
            Random rand = new Random(42); // Fixed seed for reproducibility
            for (int i = 0; i < size; i++) {
                sb.append((char) ('a' + rand.nextInt(26)));
            }
            return sb.toString();
        }

        private String generateRandomKey(int size, String ring) {
            StringBuilder sb = new StringBuilder();
            Random rand = new Random(42);
            for (int i = 0; i < size; i++) {
                int ringPos = rand.nextInt(ring.length());
                sb.append(ring.charAt(ringPos));
            }
            return sb.toString();
        }

        private void analyzeComplexity() {
            System.out.println("5. COMPLEXITY ANALYSIS");
            System.out.println("----------------------");
            System.out.println("Time Complexity: O(R × K × C)");
            System.out.println("  R = ring length");
            System.out.println("  K = key length");
            System.out.println("  C = average character occurrences in ring");
            System.out.println();
            System.out.println("Space Complexity: O(R × K)");
            System.out.println("  Memoization table size");
            System.out.println();
            System.out.println("Optimization techniques:");
            System.out.println("- Memoization eliminates overlapping subproblems");
            System.out.println("- Character-to-positions mapping for O(1) lookup");
            System.out.println("- Circular distance calculation optimization");
            System.out.println();
        }
    }

    /**
     * Educational demonstrations
     */
    public static class EducationalDemo {

        public static void demonstrateKeyInsights() {
            System.out.println("=== Key Algorithmic Insights ===");

            explainCircularOptimization();
            explainGlobalVsLocalOptimization();
            explainMemoizationBenefit();
            compareWithSimilarProblems();
        }

        private static void explainCircularOptimization() {
            System.out.println("\n1. Circular Ring Optimization:");
            System.out.println("------------------------------");
            System.out.println("Ring: \"abcdef\" (positions 0,1,2,3,4,5)");
            System.out.println("From position 1 to position 5:");
            System.out.println("- Clockwise: |5-1| = 4 steps");
            System.out.println("- Counterclockwise: 6-4 = 2 steps ← Better!");
            System.out.println("Formula: min(|to-from|, ring_size - |to-from|)");
        }

        private static void explainGlobalVsLocalOptimization() {
            System.out.println("\n2. Global vs Local Optimization:");
            System.out.println("--------------------------------");
            System.out.println("Ring: \"aaabbb\", Key: \"ab\"");
            System.out.println("Multiple 'a' positions: 0,1,2");
            System.out.println("Multiple 'b' positions: 3,4,5");
            System.out.println();
            System.out.println("Local thinking: Choose nearest 'a' and nearest 'b'");
            System.out.println("Global thinking: Choose 'a' and 'b' that minimize TOTAL cost");
            System.out.println("Why global wins: Future moves affect current best choice");
        }

        private static void explainMemoizationBenefit() {
            System.out.println("\n3. Memoization Benefit:");
            System.out.println("-----------------------");
            System.out.println("Without memoization: Exponential time O(C^K)");
            System.out.println("With memoization: Polynomial time O(R×K×C)");
            System.out.println("Example: Ring size 10, Key length 10, 2 chars avg");
            System.out.println("- Without: 2^10 = 1,024 operations");
            System.out.println("- With: 10×10×2 = 200 operations");
        }

        private static void compareWithSimilarProblems() {
            System.out.println("\n4. Similar Problems Comparison:");
            System.out.println("-------------------------------");
            System.out.println("Minimum Path Sum: Forward DP, single path");
            System.out.println("Edit Distance: 2D DP, character alignment");
            System.out.println("Freedom Trail: Circular DP, multiple choices per state");
            System.out.println("Coin Change: 1D DP, unlimited use of choices");
            System.out.println();
            System.out.println("Common pattern: State + Choices + Optimization");
        }
    }
}