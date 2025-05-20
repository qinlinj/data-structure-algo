package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._822_maximum_subarray;

/**
 * Prefix Sum Approach to the Maximum Subarray Problem
 * <p>
 * Key Insight:
 * Using prefix sums, we can efficiently calculate the sum of any subarray in O(1) time.
 * If we want to find the maximum subarray ending at index i, we need to find the smallest
 * prefix sum before index i and subtract it from the prefix sum at index i.
 * <p>
 * Algorithm Steps:
 * 1. Calculate the prefix sum array: preSum[i] = sum of nums[0...i-1]
 * (Note: preSum[0] = 0 as a placeholder)
 * 2. For each position i, the maximum subarray ending at i has sum:
 * preSum[i+1] - min(preSum[0...i])
 * 3. The global maximum is the maximum of all these sums
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(n) for the prefix sum array
 * <p>
 * This approach leverages the mathematical property that the sum of subarray nums[j...i]
 * can be calculated as preSum[i+1] - preSum[j].
 */
public class _822_d_MaxSubArrayPrefixSum {

    /**
     * Main method to demonstrate the prefix sum approach
     */
    public static void main(String[] args) {
        _822_d_MaxSubArrayPrefixSum solution = new _822_d_MaxSubArrayPrefixSum();

        // Example with visualization
        int[] nums = {-3, 1, 3, -1, 2, -4, 2};

        System.out.println("Input array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Calculate result
        int result = solution.maxSubArray(nums);

        // Visualization
        System.out.println("\nPrefix Sum Array Visualization:");
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        System.out.println("preSum[0] = 0 (placeholder)");

        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            System.out.println("preSum[" + i + "] = " + preSum[i] + " (sum of first " + i + " elements)");
        }

        System.out.println("\nFinding Maximum Subarray:");
        int maxSum = Integer.MIN_VALUE;
        int minPrefixSum = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            minPrefixSum = Math.min(minPrefixSum, preSum[i]);
            int currentMax = preSum[i + 1] - minPrefixSum;

            System.out.println("At index " + i + " (value " + nums[i] + "):");
            System.out.println("  Min prefix sum seen so far: " + minPrefixSum);
            System.out.println("  Current subarray sum: " + currentMax + " = " + preSum[i + 1] + " - " + minPrefixSum);

            maxSum = Math.max(maxSum, currentMax);
            System.out.println("  Max subarray sum so far: " + maxSum);
        }

        System.out.println("\nFinal result: " + result);
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;

        // Create prefix sum array with one extra element (preSum[0] = 0)
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Calculate prefix sums
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        int minPrefixSum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Update the minimum prefix sum seen so far
            minPrefixSum = Math.min(minPrefixSum, preSum[i]);

            // Calculate max subarray sum ending at index i
            // preSum[i+1] is the sum of nums[0...i]
            // minPrefixSum is the minimum of preSum[0...i-1]
            maxSum = Math.max(maxSum, preSum[i + 1] - minPrefixSum);
        }

        return maxSum;
    }
}