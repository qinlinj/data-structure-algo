package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

/**
 * Partition to K Equal Sum Subsets - Introduction
 * <p>
 * This class introduces the "Partition to K Equal Sum Subsets" problem (LeetCode 698).
 * <p>
 * Problem:
 * Given an array of integers nums and an integer k, determine if it's possible to divide
 * the array into k non-empty subsets whose sums are all equal.
 * <p>
 * Key concepts:
 * - This is a classic backtracking problem where we need to explore different ways to
 * distribute elements into k groups
 * - We can think of this as putting n numbers into k "buckets" where each bucket must
 * have the same sum
 * - There are two main perspectives we can use to approach this problem:
 * 1. From the elements' perspective: decide which bucket each element goes into
 * 2. From the buckets' perspective: decide which elements to put in each bucket
 * <p>
 * Why we need backtracking:
 * - Unlike the "Partition Equal Subset Sum" problem (dividing into 2 equal subsets),
 * this problem with k subsets can't be efficiently solved with dynamic programming
 * - When k = 2, we can use DP because we only need to find one subset with sum = totalSum/2,
 * and the other subset automatically satisfies the condition
 * - When k > 2, we need to track multiple subsets simultaneously, making it harder to define
 * a clear DP state, so we use backtracking to explore all possibilities
 */

import java.util.*;

public class _734_a_PartitionKSubsetsIntro {

    /**
     * Main method to demonstrate the problem
     */
    public static void main(String[] args) {
        _734_a_PartitionKSubsetsIntro solution = new _734_a_PartitionKSubsetsIntro();

        System.out.println("Partition to K Equal Sum Subsets Problem (LeetCode 698)");
        System.out.println("=======================================================");
        System.out.println("Given an array of integers and a number k, determine if it's possible");
        System.out.println("to divide the array into k non-empty subsets with equal sums.\n");

        solution.demonstrateExample();

        System.out.println("\nApproaches to solve this problem:");
        System.out.println("1. Element's perspective: decide which bucket each element goes into");
        System.out.println("2. Bucket's perspective: decide which elements to include in each bucket");
        System.out.println("\nBoth approaches use backtracking, but with different time complexities:");
        System.out.println("- Element's perspective: O(k^n) - each of n elements has k choices");
        System.out.println("- Bucket's perspective: O(k*2^n) - for each of k buckets, each element is either included or not");
    }

    /**
     * Basic implementation of canPartitionKSubsets to validate if an array
     * can be divided into k subsets with equal sum
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        // Calculate the sum of all elements
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum cannot be divided evenly by k, return false
        if (sum % k != 0) return false;

        // Calculate the target sum for each subset
        int targetSum = sum / k;

        // Create an array to track the current sum of each subset
        int[] buckets = new int[k];

        // Start the backtracking process
        return canPartition(nums, 0, buckets, targetSum);
    }

    /**
     * Simple backtracking approach from the elements' perspective
     * This method attempts to assign each element to one of k buckets
     */
    private boolean canPartition(int[] nums, int index, int[] buckets, int targetSum) {
        // Base case: all elements have been placed in buckets
        if (index == nums.length) {
            // Check if all buckets have the target sum
            for (int bucket : buckets) {
                if (bucket != targetSum) {
                    return false;
                }
            }
            return true;
        }

        // Try to place the current element in each bucket
        for (int i = 0; i < buckets.length; i++) {
            // Skip if adding the current element would exceed the target sum
            if (buckets[i] + nums[index] > targetSum) {
                continue;
            }

            // Place the element in the current bucket
            buckets[i] += nums[index];

            // Recursively try to place the next element
            if (canPartition(nums, index + 1, buckets, targetSum)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            buckets[i] -= nums[index];
        }

        // Cannot partition the array with the current arrangement
        return false;
    }

    /**
     * Helper method to demonstrate the problem with an example
     */
    public void demonstrateExample() {
        int[] example1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        int targetSum1 = Arrays.stream(example1).sum() / k1;

        System.out.println("Example 1:");
        System.out.println("nums = " + Arrays.toString(example1));
        System.out.println("k = " + k1);
        System.out.println("Total sum = " + Arrays.stream(example1).sum());
        System.out.println("Target sum for each subset = " + targetSum1);
        System.out.println("Can partition into " + k1 + " equal subsets? " +
                canPartitionKSubsets(example1, k1));

        // Example of a possible partition:
        System.out.println("One possible partition:");
        System.out.println("Subset 1: [4, 1] (sum = 5)");
        System.out.println("Subset 2: [3, 2] (sum = 5)");
        System.out.println("Subset 3: [3, 2] (sum = 5)");
        System.out.println("Subset 4: [5] (sum = 5)");

        System.out.println("\nExample 2:");
        int[] example2 = {1, 2, 3, 4};
        int k2 = 3;
        System.out.println("nums = " + Arrays.toString(example2));
        System.out.println("k = " + k2);
        System.out.println("Total sum = " + Arrays.stream(example2).sum());
        System.out.println("Can partition into " + k2 + " equal subsets? " +
                canPartitionKSubsets(example2, k2));
    }
}