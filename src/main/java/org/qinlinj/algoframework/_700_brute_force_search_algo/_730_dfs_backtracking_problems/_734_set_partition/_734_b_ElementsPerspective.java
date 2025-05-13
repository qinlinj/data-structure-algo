package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

/**
 * Partition to K Equal Sum Subsets - Elements Perspective
 * <p>
 * This class implements the first approach to solving the partition to k equal
 * sum subsets problem: viewing the problem from the elements' perspective.
 * <p>
 * In this approach:
 * - We iterate through each element in the array
 * - For each element, we decide which of the k buckets to place it in
 * - We use backtracking to explore all possible placements
 * <p>
 * Time Complexity: O(k^n) where n is the number of elements
 * Each of the n elements has k possible buckets, leading to k^n combinations
 * <p>
 * Optimization:
 * - We sort the array in descending order before backtracking
 * - This helps trigger the "bucket overflow" condition earlier in the recursion
 * which leads to more effective pruning
 */

import java.util.*;

public class _734_b_ElementsPerspective {

    /**
     * Main method to run the solution
     */
    public static void main(String[] args) {
        _734_b_ElementsPerspective solution = new _734_b_ElementsPerspective();

        System.out.println("Partition to K Equal Sum Subsets - Elements Perspective");
        System.out.println("=======================================================");
        System.out.println("This approach iterates through each element and decides which bucket to place it in.\n");

        solution.runExamples();
        solution.traceExecution();

        System.out.println("\nTime Complexity Analysis:");
        System.out.println("- Each of the n elements has k choices (buckets)");
        System.out.println("- This leads to a time complexity of O(k^n)");
        System.out.println("- With sorting optimization: O(n log n + k^n) ≈ O(k^n) for large n");
        System.out.println("\nOptimizations implemented:");
        System.out.println("1. Sort the array in descending order to trigger pruning earlier");
        System.out.println("2. Skip redundant empty buckets when backtracking fails");
    }

    /**
     * Determines if the array can be partitioned into k subsets with equal sum
     * using the elements' perspective approach
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
        int target = sum / k;

        // Create an array to track the current sum of each subset
        int[] buckets = new int[k];

        // Optimization: Sort the array in descending order
        // This helps with pruning by making it more likely to hit the
        // "bucket overflow" condition earlier in the recursion
        Arrays.sort(nums);
        reverseArray(nums);

        // Start the backtracking process
        return backtrack(nums, 0, buckets, target);
    }

    /**
     * Backtracking method to place each element in one of the k buckets
     *
     * @param nums Array of numbers to partition
     * @param index Current index in the nums array being considered
     * @param buckets Array tracking the sum in each bucket
     * @param target Target sum for each bucket
     * @return true if a valid partition exists
     */
    private boolean backtrack(int[] nums, int index, int[] buckets, int target) {
        // Base case: all elements have been placed in buckets
        if (index == nums.length) {
            // Check if all buckets have the target sum
            for (int bucket : buckets) {
                if (bucket != target) {
                    return false;
                }
            }
            return true;
        }

        // Try to place the current element in each bucket
        for (int i = 0; i < buckets.length; i++) {
            // Skip if adding the current element would exceed the target sum
            if (buckets[i] + nums[index] > target) {
                continue;
            }

            // Place the element in the current bucket
            buckets[i] += nums[index];

            // Recursively try to place the next element
            if (backtrack(nums, index + 1, buckets, target)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            buckets[i] -= nums[index];

            // Optimization: If this bucket is empty, break
            // If placing the element in an empty bucket didn't work,
            // placing it in another empty bucket won't work either
            if (buckets[i] == 0) {
                break;
            }
        }

        // Cannot partition the array with the current arrangement
        return false;
    }

    /**
     * Helper method to reverse an array (used to sort in descending order)
     */
    private void reverseArray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Helper method to demonstrate the solution with examples
     */
    public void runExamples() {
        // Example 1
        int[] nums1 = {4, 3, 2, 3, 5, 2, 1};
        int k1 = 4;
        System.out.println("Example 1: " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Can partition: " + canPartitionKSubsets(nums1, k1));

        // Example 2
        int[] nums2 = {1, 2, 3, 4};
        int k2 = 3;
        System.out.println("Example 2: " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Can partition: " + canPartitionKSubsets(nums2, k2));

        // Example 3 - this will be more challenging
        int[] nums3 = {2, 2, 2, 2, 3, 4, 5};
        int k3 = 4;
        System.out.println("Example 3: " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Can partition: " + canPartitionKSubsets(nums3, k3));
    }

    /**
     * Trace the execution of the backtracking algorithm for a simple example
     */
    public void traceExecution() {
        int[] nums = {1, 2, 3, 4};
        int k = 2;
        int target = 5; // Sum is 10, so each subset should sum to 5

        System.out.println("\nTracing execution for nums = " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("Total sum = 10, target for each subset = " + target);

        // Manually trace a possible solution path
        System.out.println("\nPossible solution path:");
        System.out.println("1. Place 4 in bucket 1: [4, 0]");
        System.out.println("2. Place 3 in bucket 2: [4, 3]");
        System.out.println("3. Place 2 in bucket 2: [4, 5] (bucket 2 is full)");
        System.out.println("4. Place 1 in bucket 1: [5, 5] (both buckets full)");
        System.out.println("Solution found: The array can be partitioned into [4, 1] and [3, 2]");
    }
}
