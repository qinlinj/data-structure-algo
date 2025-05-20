package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._822_maximum_subarray;

/**
 * Dynamic Programming Approach to the Maximum Subarray Problem
 * <p>
 * Key Insight:
 * The DP approach defines the state dp[i] as "the maximum subarray sum ending at index i".
 * This definition allows us to build a recurrence relation.
 * <p>
 * Algorithm Steps:
 * 1. Define dp[i] = maximum sum of subarray ending at index i
 * 2. The recurrence relation: dp[i] = max(nums[i], nums[i] + dp[i-1])
 * - Either start a new subarray with the current element
 * - Or extend the previous subarray to include the current element
 * 3. The base case: dp[0] = nums[0]
 * 4. The final answer is the maximum value in the dp array
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(n) for the basic solution, but can be optimized to O(1)
 * <p>
 * Note: This approach differs from the traditional DP definition where dp[i] would represent
 * "the maximum subarray sum in nums[0...i]". That definition wouldn't work here because we
 * cannot easily establish a recurrence relation.
 */
public class _822_c_MaxSubArrayDynamicProgramming {

    /**
     * Main method to demonstrate the DP approach
     */
    public static void main(String[] args) {
        _822_c_MaxSubArrayDynamicProgramming solution = new _822_c_MaxSubArrayDynamicProgramming();

        // Example with visualization
        int[] nums = {-3, 1, 3, -1, 2, -4, 2};

        System.out.println("Input array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Regular DP approach
        int result1 = solution.maxSubArray(nums);
        System.out.println("Result using DP with O(n) space: " + result1);

        // Space-optimized DP approach
        int result2 = solution.maxSubArrayOptimized(nums);
        System.out.println("Result using DP with O(1) space: " + result2);

        // Visualize the DP array
        System.out.println("\nDP array visualization:");
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        System.out.println("dp[0] = " + dp[0] + " (Base case: just take the first element)");

        for (int i = 1; i < nums.length; i++) {
            int takeAlone = nums[i];
            int takeWithPrevious = nums[i] + dp[i - 1];
            dp[i] = Math.max(takeAlone, takeWithPrevious);

            System.out.println("dp[" + i + "] = max(" + takeAlone + ", " + takeWithPrevious + ") = " + dp[i]);
        }

        int maxInDp = Integer.MIN_VALUE;
        for (int val : dp) {
            maxInDp = Math.max(maxInDp, val);
        }

        System.out.println("\nFinal result (max value in dp array): " + maxInDp);
    }

    /**
     * Original DP solution with O(n) space complexity
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // Define dp[i] as the maximum sum of subarray ending at index i
        int[] dp = new int[n];

        // Base case
        dp[0] = nums[0];

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }

        // Find the maximum value in the dp array
        int maxSum = Integer.MIN_VALUE;
        for (int sum : dp) {
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    /**
     * Optimized DP solution with O(1) space complexity
     */
    public int maxSubArrayOptimized(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // Only need to keep track of the previous dp value
        int prevMax = nums[0];
        int maxSum = prevMax;

        for (int i = 1; i < n; i++) {
            // Calculate current dp value
            int currMax = Math.max(nums[i], nums[i] + prevMax);
            // Update the maximum sum
            maxSum = Math.max(maxSum, currMax);
            // Update previous dp value for next iteration
            prevMax = currMax;
        }

        return maxSum;
    }
}