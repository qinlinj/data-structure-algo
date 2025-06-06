package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * XOR APPLICATIONS - FINDING SINGLE NUMBERS
 * <p>
 * This class demonstrates powerful applications of XOR operation for solving
 * problems involving finding unique elements in arrays.
 * <p>
 * Key XOR Properties:
 * 1. a ^ a = 0 (any number XOR with itself equals 0)
 * 2. a ^ 0 = a (any number XOR with 0 equals itself)
 * 3. XOR is commutative: a ^ b = b ^ a
 * 4. XOR is associative: (a ^ b) ^ c = a ^ (b ^ c)
 * 5. XOR is its own inverse: a ^ b ^ b = a
 * <p>
 * Applications:
 * 1. Find single number in array where all others appear twice
 * 2. Find missing number in sequence
 * 3. Detect pairs and eliminate duplicates
 * <p>
 * Why XOR works:
 * - All paired numbers cancel out (become 0)
 * - Single number remains (0 ^ single = single)
 * <p>
 * Time Complexity: O(n) for single pass through array
 * Space Complexity: O(1) - no extra space needed
 */
public class _912_d_XORApplications {

    public static void main(String[] args) {
        _912_d_XORApplications xorApp = new _912_d_XORApplications();

        // Demonstrate single number finding
        int[] singleTest = {2, 2, 1};
        xorApp.demonstrateSingleNumber(singleTest);

        int[] singleTest2 = {4, 1, 2, 1, 2};
        xorApp.demonstrateSingleNumber(singleTest2);

        // Demonstrate missing number finding
        int[] missingTest = {3, 0, 1};
        xorApp.demonstrateMissingNumber(missingTest);

        int[] missingTest2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        xorApp.demonstrateMissingNumber(missingTest2);

        // Explain mechanism
        xorApp.explainXORMechanism();

        // Performance comparison
        int[] perfTest = new int[10000];
        for (int i = 0; i < 9999; i++) {
            perfTest[i] = i;
        }
        perfTest[9999] = 10000; // Missing 9999
        xorApp.performanceComparison(perfTest);

        // Test edge cases
        xorApp.testEdgeCases();

        // LeetCode examples
        System.out.println("=== LeetCode Examples ===");

        System.out.println("LeetCode 136 - Single Number:");
        int[][] leetcode136 = {{2, 2, 1}, {4, 1, 2, 1, 2}, {1}};
        for (int[] test : leetcode136) {
            int result = xorApp.singleNumber(test);
            System.out.printf("Input: %s, Output: %d\n",
                    java.util.Arrays.toString(test), result);
        }

        System.out.println("\nLeetCode 268 - Missing Number:");
        int[][] leetcode268 = {{3, 0, 1}, {0, 1}, {9, 6, 4, 2, 3, 5, 7, 0, 1}, {0}};
        for (int[] test : leetcode268) {
            int result = xorApp.missingNumber(test);
            System.out.printf("Input: %s, Output: %d\n",
                    java.util.Arrays.toString(test), result);
        }

        System.out.println("\n=== Key Advantages ===");
        System.out.println("1. Time Complexity: O(n) - single pass");
        System.out.println("2. Space Complexity: O(1) - no extra storage");
        System.out.println("3. Simple and elegant solution");
        System.out.println("4. No risk of integer overflow (unlike sum method)");
        System.out.println("5. Works with negative numbers too");
        System.out.println("6. XOR properties naturally solve the cancellation problem");
    }

    /**
     * Finds the single number that appears once while others appear twice
     * LeetCode 136: Single Number
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    /**
     * Finds the missing number in range [0, n]
     * LeetCode 268: Missing Number
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = 0;

        // XOR with the extra index n
        result ^= n;

        // XOR all indices and array elements
        for (int i = 0; i < n; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }

    /**
     * Alternative missing number solution using sum formula
     */
    public int missingNumberSum(int[] nums) {
        int n = nums.length;
        // Expected sum: 0 + 1 + 2 + ... + n = n*(n+1)/2
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return (int) (expectedSum - actualSum);
    }

    /**
     * Demonstrates step-by-step XOR process for single number
     */
    public void demonstrateSingleNumber(int[] nums) {
        System.out.println("=== Finding Single Number ===");
        System.out.println("Array: " + java.util.Arrays.toString(nums));

        int result = 0;
        System.out.printf("Initial result: %d (binary: %s)\n", result, Integer.toBinaryString(result));

        for (int i = 0; i < nums.length; i++) {
            int prev = result;
            result ^= nums[i];
            System.out.printf("Step %d: %d ^ %d = %d (binary: %s ^ %s = %s)\n",
                    i + 1, prev, nums[i], result,
                    Integer.toBinaryString(prev),
                    Integer.toBinaryString(nums[i]),
                    Integer.toBinaryString(result));
        }

        System.out.printf("Single number: %d\n\n", result);
    }

    /**
     * Demonstrates missing number detection with XOR
     */
    public void demonstrateMissingNumber(int[] nums) {
        System.out.println("=== Finding Missing Number ===");
        System.out.println("Array: " + java.util.Arrays.toString(nums));

        int n = nums.length;
        System.out.printf("Expected range: [0, %d]\n", n);

        // Create complete pairing visualization
        System.out.println("\nPairing elements with indices:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Index %d pairs with element %d\n", i, nums[i]);
        }
        System.out.printf("Index %d has no pair (this is our missing number)\n", n);

        // Show XOR process
        int result = 0;

        // XOR with extra index
        result ^= n;
        System.out.printf("\nXOR with extra index %d: result = %d\n", n, result);

        // XOR with indices and elements
        for (int i = 0; i < n; i++) {
            int prev = result;
            result ^= i;
            System.out.printf("XOR with index %d: %d ^ %d = %d\n", i, prev, i, result);

            prev = result;
            result ^= nums[i];
            System.out.printf("XOR with element %d: %d ^ %d = %d\n", nums[i], prev, nums[i], result);
        }

        System.out.printf("Missing number: %d\n\n", result);
    }

    /**
     * Explains why XOR works for these problems
     */
    public void explainXORMechanism() {
        System.out.println("=== Why XOR Works ===");
        System.out.println("XOR Properties:");
        System.out.println("1. a ^ a = 0 (self-cancellation)");
        System.out.println("2. a ^ 0 = a (identity)");
        System.out.println("3. Commutative: a ^ b = b ^ a");
        System.out.println("4. Associative: (a ^ b) ^ c = a ^ (b ^ c)");

        System.out.println("\nFor Single Number problem:");
        System.out.println("- All duplicate numbers cancel out: a ^ a = 0");
        System.out.println("- Single number remains: 0 ^ single = single");

        System.out.println("\nFor Missing Number problem:");
        System.out.println("- Pair each index with corresponding element");
        System.out.println("- Missing element's index has no pair");
        System.out.println("- All pairs cancel out, leaving missing number");

        // Demonstrate with example
        System.out.println("\nExample: [0,1,3] missing 2");
        System.out.println("Indices: 0,1,2,3 | Elements: 0,1,3");
        System.out.println("XOR all: 0^1^2^3^0^1^3 = (0^0)^(1^1)^(3^3)^2 = 0^0^0^2 = 2");
        System.out.println();
    }

    /**
     * Performance comparison between XOR and other methods
     */
    public void performanceComparison(int[] nums) {
        System.out.println("=== Performance Comparison for Missing Number ===");

        // Method 1: XOR
        long startTime = System.nanoTime();
        int xorResult = missingNumber(nums);
        long xorTime = System.nanoTime() - startTime;

        // Method 2: Sum formula
        startTime = System.nanoTime();
        int sumResult = missingNumberSum(nums);
        long sumTime = System.nanoTime() - startTime;

        // Method 3: HashSet (for comparison)
        startTime = System.nanoTime();
        int hashResult = missingNumberHashSet(nums);
        long hashTime = System.nanoTime() - startTime;

        System.out.printf("XOR method: %d ns, result: %d\n", xorTime, xorResult);
        System.out.printf("Sum method: %d ns, result: %d\n", sumTime, sumResult);
        System.out.printf("HashSet method: %d ns, result: %d\n", hashTime, hashResult);
        System.out.printf("All results match: %b\n",
                xorResult == sumResult && sumResult == hashResult);
        System.out.println();
    }

    /**
     * HashSet solution for missing number (for comparison)
     */
    private int missingNumberHashSet(int[] nums) {
        java.util.Set<Integer> numSet = new java.util.HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Tests edge cases for XOR solutions
     */
    public void testEdgeCases() {
        System.out.println("=== Edge Cases ===");

        // Single Number edge cases
        System.out.println("Single Number edge cases:");
        int[][] singleTests = {
                {1},           // Only one element
                {2, 2, 1},     // Standard case
                {4, 1, 2, 1, 2}, // Multiple pairs
                {1, 3, 1, 3, 2, 2, 4} // Larger array
        };

        for (int[] test : singleTests) {
            int result = singleNumber(test);
            System.out.printf("Array: %s -> Single: %d\n",
                    java.util.Arrays.toString(test), result);
        }

        // Missing Number edge cases
        System.out.println("\nMissing Number edge cases:");
        int[][] missingTests = {
                {0},           // Missing 1
                {1},           // Missing 0
                {3, 0, 1},     // Missing 2
                {9, 6, 4, 2, 3, 5, 7, 0, 1}, // Missing 8
                {0}            // Missing 1
        };

        for (int[] test : missingTests) {
            int result = missingNumber(test);
            System.out.printf("Array: %s -> Missing: %d\n",
                    java.util.Arrays.toString(test), result);
        }
        System.out.println();
    }
}