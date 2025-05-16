package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._812_longest_increasing_subsequence;

/**
 * Longest Increasing Subsequence - Dynamic Programming Approach
 * ===========================================================
 * <p>
 * This class demonstrates how to solve the Longest Increasing Subsequence (LIS) problem
 * using dynamic programming. The LIS problem asks for the length of the longest subsequence
 * of an array where all elements are in strictly increasing order.
 * <p>
 * Key Concepts:
 * <p>
 * 1. Mathematical Induction in DP:
 * - Assume we know solutions for smaller subproblems
 * - Use these solutions to derive the solution for the current state
 * <p>
 * 2. DP Array Definition:
 * - dp[i] = length of the longest increasing subsequence ending at index i
 * - This definition is critical for correctly formulating the state transition
 * <p>
 * 3. State Transition:
 * - For each position i, we look at all previous positions j where nums[j] < nums[i]
 * - We can extend those subsequences by adding nums[i], resulting in dp[j] + 1
 * - We take the maximum of all these possible extensions: dp[i] = max(dp[i], dp[j] + 1)
 * <p>
 * 4. Base Case:
 * - dp[i] = 1 for all i (every element by itself is a valid subsequence of length 1)
 * <p>
 * 5. Time Complexity: O(n²) - for each position we examine all previous positions
 * Space Complexity: O(n) - for the dp array
 * <p>
 * This approach exemplifies the fundamental DP process:
 * 1. Define what the dp array represents
 * 2. Use mathematical induction to establish state transitions
 * 3. Initialize with base cases
 * 4. Iterate through all states to build the solution
 */
public class _812_a_LongestIncreasingSubsequenceDP {

    public static void main(String[] args) {
        _812_a_LongestIncreasingSubsequenceDP solution = new _812_a_LongestIncreasingSubsequenceDP();

        // Example 1 from LeetCode: [10,9,2,5,3,7,101,18]
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Example 1: " + solution.lengthOfLIS(nums1)); // Expected: 4

        // Example 2 from LeetCode: [0,1,0,3,2,3]
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Example 2: " + solution.lengthOfLIS(nums2)); // Expected: 4

        // Example 3 from LeetCode: [7,7,7,7,7,7,7]
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Example 3: " + solution.lengthOfLIS(nums3)); // Expected: 1

        // Demonstrative example with step-by-step explanation
        System.out.println("\n--- Step-by-Step Demonstration ---");
        int[] demonstrativeExample = {2, 6, 3, 4, 1, 2, 9, 5, 8};
        solution.demonstrateLIS(demonstrativeExample);
    }

    /**
     * Find the length of the longest increasing subsequence in the given array
     * using a dynamic programming approach.
     *
     * @param nums array of integers
     * @return length of the longest increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        // Edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Define dp array: dp[i] = length of LIS ending at index i
        int[] dp = new int[nums.length];

        // Base case: each element by itself forms an LIS of length 1
        java.util.Arrays.fill(dp, 1);

        // State transition: build solution from smaller subproblems
        for (int i = 0; i < nums.length; i++) {
            // Look at all previous elements
            for (int j = 0; j < i; j++) {
                // If the current element is greater, we can extend the subsequence
                if (nums[i] > nums[j]) {
                    // Update if adding this element to the previous subsequence creates a longer one
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the maximum value in the dp array
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    /**
     * Demonstrates the execution of the lengthOfLIS algorithm with step-by-step visualization
     * of the dp array's evolution.
     */
    public void demonstrateLIS(int[] nums) {
        System.out.println("Finding LIS for array: " + java.util.Arrays.toString(nums));

        int[] dp = new int[nums.length];
        java.util.Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.println("\nProcessing element at index " + i + " (value: " + nums[i] + ")");

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int oldDp = dp[i];
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                    if (dp[i] > oldDp) {
                        System.out.println("  Extended LIS from index " + j + " (" + nums[j] + "): " +
                                "dp[" + i + "] updated from " + oldDp + " to " + dp[i]);
                    }
                }
            }

            System.out.println("  Current dp array: " + java.util.Arrays.toString(dp));
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        System.out.println("\nFinal dp array: " + java.util.Arrays.toString(dp));
        System.out.println("Length of LIS: " + maxLength);
    }
}
