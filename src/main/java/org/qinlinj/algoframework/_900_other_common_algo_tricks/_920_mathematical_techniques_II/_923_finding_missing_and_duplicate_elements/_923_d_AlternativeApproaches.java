package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

/*
 * ALTERNATIVE APPROACHES FOR FINDING DUPLICATE AND MISSING ELEMENTS
 *
 * This class demonstrates three main approaches to solve the problem:
 * 1. Sorting Approach - Sort array and find inconsistencies
 * 2. XOR (Bit Manipulation) - Use XOR properties to cancel out pairs
 * 3. Mathematical Approach - Use sum formulas to calculate differences
 *
 * Each approach has different trade-offs in terms of:
 * - Time complexity
 * - Space complexity
 * - Modification of input array
 * - Implementation complexity
 *
 * Common pattern: Element-index correspondence is key to efficient solutions
 * for array problems where elements have natural ordering [1..N]
 */

import java.util.*;

public class _923_d_AlternativeApproaches {

    /**
     * Approach 1: Sorting Method
     * Time: O(N log N), Space: O(1) if in-place sort allowed
     */
    public static int[] findErrorNumsSorting(int[] nums) {
        Arrays.sort(nums);
        int duplicate = -1;
        int missing = 1;  // Start checking from 1

        // Find duplicate
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                duplicate = nums[i];
                break;
            }
        }

        // Find missing by checking sequence
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == missing) {
                missing++;  // This number exists, check next
            }
        }

        return new int[]{duplicate, missing};
    }

    /**
     * Approach 2: XOR Bit Manipulation
     * Uses property: a ^ a = 0, a ^ 0 = a
     * Time: O(N), Space: O(1)
     */
    public static int[] findErrorNumsXOR(int[] nums) {
        int n = nums.length;
        int xorAll = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xorAll ^= i;
        }

        // XOR all numbers in the array
        for (int num : nums) {
            xorAll ^= num;
        }

        // xorAll now contains (missing ^ duplicate)
        // Need additional logic to separate them

        // Find rightmost set bit to distinguish missing and duplicate
        int rightmostBit = xorAll & (-xorAll);

        int type1 = 0, type2 = 0;

        // Divide numbers into two groups based on rightmost bit
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostBit) == 0) {
                type1 ^= i;
            } else {
                type2 ^= i;
            }
        }

        for (int num : nums) {
            if ((num & rightmostBit) == 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }

        // One of type1/type2 is duplicate, other is missing
        // Check which one appears in the array
        for (int num : nums) {
            if (num == type1) {
                return new int[]{type1, type2};  // type1 is duplicate
            }
        }

        return new int[]{type2, type1};  // type2 is duplicate
    }

    /**
     * Approach 3: Mathematical Sum Method
     * Uses sum formulas to calculate the difference
     * Time: O(N), Space: O(1)
     */
    public static int[] findErrorNumsMath(int[] nums) {
        int n = nums.length;

        // Calculate expected sum: 1 + 2 + ... + n = n(n+1)/2
        long expectedSum = (long) n * (n + 1) / 2;

        // Calculate expected sum of squares: 1² + 2² + ... + n²
        long expectedSumSquares = (long) n * (n + 1) * (2 * n + 1) / 6;

        // Calculate actual sums
        long actualSum = 0;
        long actualSumSquares = 0;

        for (int num : nums) {
            actualSum += num;
            actualSumSquares += (long) num * num;
        }

        // Let duplicate = d, missing = m
        // actualSum - expectedSum = d - m
        // actualSumSquares - expectedSumSquares = d² - m² = (d-m)(d+m)

        long diff = actualSum - expectedSum;          // d - m
        long sumDM = (actualSumSquares - expectedSumSquares) / diff;  // d + m

        int duplicate = (int) ((diff + sumDM) / 2);
        int missing = (int) (sumDM - duplicate);

        return new int[]{duplicate, missing};
    }

    /**
     * Demonstrates all approaches with timing and comparison
     */
    public static void compareApproaches() {
        System.out.println("=== Comparing Different Approaches ===");

        int[] testArray = {1, 2, 2, 4};
        System.out.println("Test array: " + Arrays.toString(testArray));
        System.out.println("Expected result: [2, 3]\n");

        // Test each approach
        System.out.println("1. Sorting Approach:");
        int[] result1 = findErrorNumsSorting(testArray.clone());
        System.out.println("   Result: " + Arrays.toString(result1));
        System.out.println("   Time: O(N log N), Space: O(1)");
        System.out.println("   Pros: Simple, intuitive");
        System.out.println("   Cons: Slower due to sorting\n");

        System.out.println("2. XOR Bit Manipulation:");
        int[] result2 = findErrorNumsXOR(testArray.clone());
        System.out.println("   Result: " + Arrays.toString(result2));
        System.out.println("   Time: O(N), Space: O(1)");
        System.out.println("   Pros: Very efficient, doesn't modify input");
        System.out.println("   Cons: Complex bit manipulation logic\n");

        System.out.println("3. Mathematical Sum Method:");
        int[] result3 = findErrorNumsMath(testArray.clone());
        System.out.println("   Result: " + Arrays.toString(result3));
        System.out.println("   Time: O(N), Space: O(1)");
        System.out.println("   Pros: Clean mathematical approach");
        System.out.println("   Cons: Potential overflow with large numbers\n");
    }

    /**
     * Detailed explanation of XOR approach
     */
    public static void explainXORApproach() {
        System.out.println("=== XOR Approach Detailed Explanation ===");

        int[] nums = {1, 2, 2, 4};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Should be: [1, 2, 3, 4]");

        System.out.println("\nXOR Properties:");
        System.out.println("- a ^ a = 0 (same numbers cancel out)");
        System.out.println("- a ^ 0 = a (XOR with 0 gives original)");
        System.out.println("- XOR is commutative and associative");

        System.out.println("\nStep-by-step XOR process:");

        int xorResult = 0;
        System.out.print("XOR of expected [1,2,3,4]: ");
        for (int i = 1; i <= 4; i++) {
            xorResult ^= i;
            System.out.print(i + (i < 4 ? " ^ " : " = " + xorResult));
        }
        System.out.println();

        System.out.print("XOR of actual [1,2,2,4]: ");
        for (int i = 0; i < nums.length; i++) {
            xorResult ^= nums[i];
            System.out.print(nums[i] + (i < nums.length - 1 ? " ^ " : " = " + xorResult));
        }
        System.out.println();

        System.out.println("\nResult " + xorResult + " represents (missing ^ duplicate) = (3 ^ 2) = " + (3 ^ 2));
        System.out.println("Additional bit manipulation needed to separate 3 and 2");
    }

    /**
     * Explains why mathematical approach works
     */
    public static void explainMathApproach() {
        System.out.println("\n=== Mathematical Approach Explanation ===");

        int[] nums = {1, 2, 2, 4};
        int n = nums.length;

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Let duplicate = d, missing = m");

        // Calculate sums
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = Arrays.stream(nums).sum();

        long expectedSumSq = (long) n * (n + 1) * (2 * n + 1) / 6;
        long actualSumSq = Arrays.stream(nums).mapToLong(x -> (long) x * x).sum();

        System.out.println("\nSum calculations:");
        System.out.println("Expected sum (1+2+3+4): " + expectedSum);
        System.out.println("Actual sum (1+2+2+4): " + actualSum);
        System.out.println("Difference (d - m): " + (actualSum - expectedSum));

        System.out.println("\nSum of squares:");
        System.out.println("Expected (1²+2²+3²+4²): " + expectedSumSq);
        System.out.println("Actual (1²+2²+2²+4²): " + actualSumSq);
        System.out.println("Difference (d² - m²): " + (actualSumSq - expectedSumSq));

        long diff = actualSum - expectedSum;
        long sumDM = (actualSumSq - expectedSumSq) / diff;

        System.out.println("\nSolving system of equations:");
        System.out.println("d - m = " + diff);
        System.out.println("d + m = " + sumDM + " (from (d²-m²)/(d-m))");

        int duplicate = (int) ((diff + sumDM) / 2);
        int missing = (int) (sumDM - duplicate);

        System.out.println("Therefore: d = " + duplicate + ", m = " + missing);
    }

    /**
     * Performance comparison with larger datasets
     */
    public static void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");

        int[] sizes = {1000, 10000, 100000};

        for (int size : sizes) {
            System.out.println("\nArray size: " + size);

            // Create test array with duplicate and missing
            int[] testArray = new int[size];
            for (int i = 0; i < size; i++) {
                testArray[i] = i + 1;
            }
            testArray[size - 1] = 1;  // Make 1 duplicate, size missing

            // Timing each approach
            long startTime, endTime;

            // Sorting approach
            startTime = System.nanoTime();
            findErrorNumsSorting(testArray.clone());
            endTime = System.nanoTime();
            System.out.printf("   Sorting: %.2f ms\n", (endTime - startTime) / 1_000_000.0);

            // XOR approach
            startTime = System.nanoTime();
            findErrorNumsXOR(testArray.clone());
            endTime = System.nanoTime();
            System.out.printf("   XOR: %.2f ms\n", (endTime - startTime) / 1_000_000.0);

            // Math approach
            startTime = System.nanoTime();
            findErrorNumsMath(testArray.clone());
            endTime = System.nanoTime();
            System.out.printf("   Math: %.2f ms\n", (endTime - startTime) / 1_000_000.0);
        }
    }

    /**
     * Summary of all approaches with recommendations
     */
    public static void summarizeApproaches() {
        System.out.println("\n=== Approach Summary and Recommendations ===");

        System.out.println("┌─────────────────┬─────────────┬─────────────┬─────────────────┬─────────────────┐");
        System.out.println("│ Approach        │ Time        │ Space       │ Pros            │ Cons            │");
        System.out.println("├─────────────────┼─────────────┼─────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ HashMap         │ O(N)        │ O(N)        │ Simple, clear   │ Extra space     │");
        System.out.println("│ Sorting         │ O(N log N)  │ O(1)        │ Intuitive       │ Slower          │");
        System.out.println("│ Negative Mark   │ O(N)        │ O(1)        │ Optimal         │ Modifies input  │");
        System.out.println("│ XOR             │ O(N)        │ O(1)        │ No modification │ Complex logic   │");
        System.out.println("│ Mathematical    │ O(N)        │ O(1)        │ Clean math      │ Overflow risk   │");
        System.out.println("└─────────────────┴─────────────┴─────────────┴─────────────────┴─────────────────┘");

        System.out.println("\nRecommendations:");
        System.out.println("• Use NEGATIVE MARKING for interviews (optimal time/space)");
        System.out.println("• Use HASHMAP for clarity and when space isn't critical");
        System.out.println("• Use XOR for immutable input requirements");
        System.out.println("• Use MATHEMATICAL for mathematical elegance");
        System.out.println("• Avoid SORTING unless simplicity is paramount");

        System.out.println("\nKey Pattern Recognition:");
        System.out.println("• Element-index correspondence problems often have O(1) space solutions");
        System.out.println("• Consider using array indices as implicit hash table");
        System.out.println("• Negative marking works when elements are positive");
        System.out.println("• XOR useful when dealing with pairs and missing elements");
    }

    public static void main(String[] args) {
        compareApproaches();
        explainXORApproach();
        explainMathApproach();
        performanceComparison();
        summarizeApproaches();
    }
}