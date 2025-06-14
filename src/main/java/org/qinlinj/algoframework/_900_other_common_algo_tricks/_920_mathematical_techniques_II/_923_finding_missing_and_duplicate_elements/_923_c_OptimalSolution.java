package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

/*
 * OPTIMAL O(1) SPACE SOLUTION
 *
 * This class implements the space-efficient solution using negative marking
 * to find duplicate and missing elements without extra space.
 *
 * Algorithm Principle:
 * - Use the array itself as a hash table
 * - Mark visited indices by making corresponding elements negative
 * - If we try to mark an already negative element -> duplicate found
 * - After processing, positive elements indicate missing numbers
 *
 * Time Complexity: O(N) - two passes through the array
 * Space Complexity: O(1) - only uses the input array for marking
 *
 * Key Steps:
 * 1. For each element, calculate its target index (element - 1)
 * 2. If nums[target_index] is negative -> duplicate found
 * 3. Otherwise, mark nums[target_index] as negative
 * 4. Find the index with positive value -> missing element
 */

import java.util.*;

public class _923_c_OptimalSolution {

    /**
     * Optimal solution with O(1) space complexity
     *
     * @param nums array with one duplicate and one missing element
     * @return array containing [duplicate, missing]
     */
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int duplicate = -1;

        // Step 1: Find duplicate by negative marking
        for (int i = 0; i < n; i++) {
            int element = Math.abs(nums[i]);  // Get original value (ignore sign)
            int targetIndex = element - 1;    // Convert to 0-based index

            if (nums[targetIndex] < 0) {
                // Already marked negative = duplicate found
                duplicate = element;
            } else {
                // First visit, mark as negative
                nums[targetIndex] *= -1;
            }
        }

        // Step 2: Find missing by looking for positive values
        int missing = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                missing = i + 1;  // Convert back to 1-based
                break;
            }
        }

        return new int[]{duplicate, missing};
    }

    /**
     * Detailed step-by-step demonstration of the algorithm
     */
    public static int[] findErrorNumsDetailed(int[] nums) {
        System.out.println("=== Optimal Algorithm Step-by-Step ===");
        System.out.println("Input: " + Arrays.toString(nums));

        int n = nums.length;
        int duplicate = -1;

        System.out.println("\n--- Phase 1: Finding Duplicate ---");

        for (int i = 0; i < n; i++) {
            int element = Math.abs(nums[i]);
            int targetIndex = element - 1;

            System.out.print("Step " + (i + 1) + ": Element " + element +
                    " -> Target Index " + targetIndex);

            if (nums[targetIndex] < 0) {
                duplicate = element;
                System.out.println(" -> Already negative! DUPLICATE = " + duplicate);
            } else {
                nums[targetIndex] *= -1;
                System.out.println(" -> Marked negative");
            }

            System.out.println("     Array: " + Arrays.toString(nums));
        }

        System.out.println("\n--- Phase 2: Finding Missing ---");
        int missing = -1;

        for (int i = 0; i < n; i++) {
            System.out.print("Index " + i + ": value = " + nums[i]);

            if (nums[i] > 0) {
                missing = i + 1;
                System.out.println(" -> POSITIVE! Missing element = " + missing);
            } else {
                System.out.println(" -> negative (visited)");
            }
        }

        System.out.println("\nResult: [" + duplicate + ", " + missing + "]");
        return new int[]{duplicate, missing};
    }

    /**
     * Demonstrates why the algorithm works with visual representation
     */
    public static void visualizeAlgorithm() {
        System.out.println("\n=== Algorithm Visualization ===");

        int[] nums = {1, 2, 2, 4};
        System.out.println("Problem: " + Arrays.toString(nums));
        System.out.println("Should contain: [1, 2, 3, 4]");
        System.out.println("Issue: 2 appears twice, 3 is missing");

        System.out.println("\nVisualization of marking process:");
        System.out.println("Array indices: [0] [1] [2] [3]");
        System.out.println("Should contain: 1   2   3   4");
        System.out.println("Actually has:   1   2   2   4");

        int[] working = nums.clone();
        System.out.println("\nInitial state: " + Arrays.toString(working));

        // Process each element
        String[] states = new String[nums.length + 1];
        states[0] = Arrays.toString(working);

        for (int i = 0; i < nums.length; i++) {
            int element = Math.abs(working[i]);
            int targetIndex = element - 1;

            System.out.println("\nProcessing element " + element + ":");
            System.out.println("  Maps to index " + targetIndex);

            if (working[targetIndex] < 0) {
                System.out.println("  Index " + targetIndex + " already marked -> DUPLICATE!");
            } else {
                working[targetIndex] *= -1;
                System.out.println("  Marking index " + targetIndex + " as visited");
            }

            states[i + 1] = Arrays.toString(working);
            System.out.println("  State: " + states[i + 1]);
        }

        System.out.println("\nFinal analysis:");
        for (int i = 0; i < working.length; i++) {
            if (working[i] > 0) {
                System.out.println("  Index " + i + " not visited -> Element " + (i + 1) + " missing");
            }
        }
    }

    /**
     * Test the algorithm with multiple cases
     */
    public static void runTests() {
        System.out.println("\n=== Testing Multiple Cases ===");

        int[][] testCases = {
                {1, 2, 2, 4},    // duplicate: 2, missing: 3
                {1, 1},          // duplicate: 1, missing: 2
                {3, 2, 2},       // duplicate: 2, missing: 1
                {2, 2},          // duplicate: 2, missing: 1
                {1, 2, 3, 4, 4}  // duplicate: 4, missing: 5
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] test = testCases[i].clone();  // Clone to preserve original
            int[] result = findErrorNums(test);

            System.out.println("Test " + (i + 1) + ": " + Arrays.toString(testCases[i]) +
                    " -> [" + result[0] + ", " + result[1] + "]");
        }
    }

    public static void main(String[] args) {
        // Basic test
        int[] test = {1, 2, 2, 4};
        int[] result = findErrorNums(test.clone());
        System.out.println("Quick test: " + Arrays.toString(test) + " -> " + Arrays.toString(result));

        // Detailed demonstration
        int[] detailedTest = {1, 2, 2, 4};
        findErrorNumsDetailed(detailedTest);

        // Visual explanation
        visualizeAlgorithm();

        // Multiple test cases
        runTests();

        System.out.println("\n=== Algorithm Summary ===");
        System.out.println("Time Complexity: O(N) - two passes through array");
        System.out.println("Space Complexity: O(1) - uses input array for marking");
        System.out.println("Key insight: Use negative values to mark visited indices");
    }
}