package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

import java.util.*;

public class _923_a_ProblemIntroduction {
    /**
     * Basic solution using HashMap to track number frequencies
     *
     * @param nums array with one duplicate and one missing number
     * @return array containing [duplicate, missing]
     */
    public static int[] findErrorNumsBasic(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();

        // Count frequency of each number in the array
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int duplicate = -1;
        int missing = -1;

        // Check numbers from 1 to n to find duplicate and missing
        for (int i = 1; i <= n; i++) {
            int frequency = count.getOrDefault(i, 0);
            if (frequency == 2) {
                duplicate = i;  // Found the duplicate number
            } else if (frequency == 0) {
                missing = i;    // Found the missing number
            }
        }

        return new int[]{duplicate, missing};
    }


    /**
     * Demonstrates the basic approach with detailed step-by-step output
     */
    public static void demonstrateBasicApproach(int[] nums) {
        System.out.println("=== Basic HashMap Approach Demo ===");
        System.out.println("Input array: " + Arrays.toString(nums));

        Map<Integer, Integer> count = new HashMap<>();

        // Step 1: Count frequencies
        System.out.println("\nStep 1: Counting frequencies");
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            System.out.println("  Number " + num + " appears " + count.get(num) + " times");
        }

        // Step 2: Find duplicate and missing
        System.out.println("\nStep 2: Analyzing frequencies for numbers 1 to " + nums.length);
        int duplicate = -1, missing = -1;

        for (int i = 1; i <= nums.length; i++) {
            int frequency = count.getOrDefault(i, 0);
            System.out.print("  Number " + i + ": frequency = " + frequency);

            if (frequency == 2) {
                duplicate = i;
                System.out.print(" -> DUPLICATE found!");
            } else if (frequency == 0) {
                missing = i;
                System.out.print(" -> MISSING found!");
            }
            System.out.println();
        }

        System.out.println("\nResult: [" + duplicate + ", " + missing + "]");
        System.out.println("Space complexity: O(N) due to HashMap");
        System.out.println("Time complexity: O(N)");
    }

    public static void main(String[] args) {
        // Test case 1: [1,2,2,4] -> [2,3]
        int[] test1 = {1, 2, 2, 4};
        int[] result1 = findErrorNumsBasic(test1);
        System.out.println("Test 1: " + Arrays.toString(test1) + " -> " + Arrays.toString(result1));

        // Test case 2: [1,1] -> [1,2]
        int[] test2 = {1, 1};
        int[] result2 = findErrorNumsBasic(test2);
        System.out.println("Test 2: " + Arrays.toString(test2) + " -> " + Arrays.toString(result2));

        // Test case 3: [3,2,2] -> [2,1]
        int[] test3 = {3, 2, 2};
        int[] result3 = findErrorNumsBasic(test3);
        System.out.println("Test 3: " + Arrays.toString(test3) + " -> " + Arrays.toString(result3));

        System.out.println("\n" + "=".repeat(50));

        // Detailed demonstration
        demonstrateBasicApproach(test1);
    }
}
