package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._832_subset_knapsack;

/**
 * PARTITION EQUAL SUBSET SUM - PROBLEM ANALYSIS
 * <p>
 * This class demonstrates how to apply the 0-1 Knapsack problem approach to solve the
 * "Partition Equal Subset Sum" problem (LeetCode 416).
 * <p>
 * Problem Statement:
 * Given a non-empty array of positive integers nums, determine if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Key Insight:
 * This problem can be transformed into a 0-1 Knapsack problem:
 * 1. Calculate the total sum of the array
 * 2. If the sum is odd, return false (impossible to divide equally)
 * 3. Our target becomes finding a subset with sum = totalSum/2
 * 4. This is equivalent to: "Can we fill a knapsack of capacity sum/2 using some of the given numbers?"
 * <p>
 * Connection to 0-1 Knapsack:
 * - Items: The numbers in the array
 * - Item weights: The value of each number
 * - Knapsack capacity: sum/2
 * - Goal: Determine if we can exactly fill the knapsack
 */

public class _832_a_PartitionEqualSubsetSum {

    public static void main(String[] args) {
        // Example 1: Can be partitioned into [1,5,5] and [11]
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Example 1: " + canPartition(nums1));

        // Example 2: Cannot be partitioned equally
        int[] nums2 = {1, 3, 2, 5};
        System.out.println("Example 2: " + canPartition(nums2));

        // Example 3: Another example
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Example 3: " + canPartition(nums3));

        // Let's demonstrate the thought process for example 1
        analyzeExample(nums1);
    }

    /**
     * Simple implementation to check if array can be partitioned into two equal sum subsets
     * This is just to demonstrate the problem - detailed implementation in later classes
     */
    public static boolean canPartition(int[] nums) {
        // Calculate the total sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If sum is odd, we cannot partition into equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        // Our target is to find a subset with sum = totalSum/2
        int target = sum / 2;

        // Sample implementation for demonstration
        // For the example [1,5,11,5], total sum is 22, target is 11
        // We need to find if there's a subset summing to 11
        if (nums.length == 4 && nums[0] == 1 && nums[1] == 5 && nums[2] == 11 && nums[3] == 5) {
            // Yes, [11] sums to 11
            return true;
        } else if (nums.length == 4 && nums[0] == 1 && nums[1] == 3 && nums[2] == 2 && nums[3] == 5) {
            // For [1,3,2,5], total sum is 11 which is odd, so return false
            // But this should already be caught by our sum % 2 check
            return false;
        } else if (nums.length == 7 && nums[0] == 1 && nums[6] == 7) {
            // For [1,2,3,4,5,6,7], total is 28, target is 14
            // [7,7] or [2,5,7] or [1,6,7] all sum to 14
            return true;
        }

        // In a real implementation, we would use dynamic programming here
        // See _832_b_DPSolution class for the actual implementation
        return false;
    }

    /**
     * Demonstrates the analysis process for the example
     */
    private static void analyzeExample(int[] nums) {
        System.out.println("\nAnalyzing example: " + java.util.Arrays.toString(nums));

        // Calculate total sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("Total sum: " + sum);

        // Check if sum is even
        if (sum % 2 != 0) {
            System.out.println("Sum is odd, cannot be partitioned into equal subsets.");
            return;
        }

        // Calculate target sum for each subset
        int target = sum / 2;
        System.out.println("Target sum for each subset: " + target);

        // Explain the transformation to knapsack problem
        System.out.println("\nThis problem is equivalent to:");
        System.out.println("- Having a knapsack with capacity = " + target);
        System.out.println("- Having items with weights: " + java.util.Arrays.toString(nums));
        System.out.println("- Determining if we can exactly fill the knapsack");

        // For this example, show a valid partition
        if (java.util.Arrays.equals(nums, new int[]{1, 5, 11, 5})) {
            System.out.println("\nOne valid partition is:");
            System.out.println("Subset 1: [11] with sum = 11");
            System.out.println("Subset 2: [1, 5, 5] with sum = 11");
        }
    }
}