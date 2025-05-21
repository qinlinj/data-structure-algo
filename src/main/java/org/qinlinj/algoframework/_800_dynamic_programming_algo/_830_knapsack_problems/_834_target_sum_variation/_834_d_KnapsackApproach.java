package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_d_KnapsackApproach {
    /**
     * Main solution method using the subset sum / knapsack approach
     *
     * @param nums   array of non-negative integers
     * @param target the target sum to reach
     * @return number of ways to reach the target
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // Calculate sum of array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Edge cases:
        // 1. If target > sum, impossible to reach
        // 2. If (sum + target) is odd, impossible to have an integer solution for subset sum
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }

        // Calculate the subset sum we need to find
        int subsetSum = (sum + target) / 2;

        // Find number of subsets with the calculated sum
        return countSubsetSum(nums, subsetSum);
    }

    /**
     * 2D dynamic programming approach to count subsets with a given sum
     *
     * @param nums array of non-negative integers
     * @param sum  the target subset sum
     * @return number of ways to form the subset sum
     */
    private static int countSubsetSum(int[] nums, int sum) {
        int n = nums.length;

        // dp[i][j] = number of ways to make sum j using first i elements
        int[][] dp = new int[n + 1][sum + 1];

        // Base case: empty subset can form sum 0 in exactly 1 way
        dp[0][0] = 1;

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                // Current element is nums[i-1] (0-indexed array)

                // Don't include current element
                dp[i][j] = dp[i - 1][j];

                // Include current element if possible
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    /**
     * Optimized 1D dynamic programming approach to count subsets with a given sum
     *
     * @param nums array of non-negative integers
     * @param sum  the target subset sum
     * @return number of ways to form the subset sum
     */
    private static int countSubsetSumOptimized(int[] nums, int sum) {
        // dp[j] = number of ways to make sum j
        int[] dp = new int[sum + 1];

        // Base case
        dp[0] = 1;

        // Fill the dp array
        for (int i = 0; i < nums.length; i++) {
            // Important: traverse from right to left to avoid counting the same element multiple times
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[sum];
    }

    /**
     * Optimized solution using the 1D approach
     */
    public static int findTargetSumWaysOptimized(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (sum + target) / 2;
        return countSubsetSumOptimized(nums, subsetSum);
    }

    /**
     * Helper method to print the 2D DP table for visualization
     */
    private static void printDPTable(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];

        // Initialize
        dp[0][0] = 1;

        System.out.println("DP Table Calculation:");
        System.out.println("nums = " + java.util.Arrays.toString(nums));
        System.out.println("target subset sum = " + sum);

        // Print initial state
        System.out.println("\nInitial DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Fill and print each step
        for (int i = 1; i <= n; i++) {
            System.out.println("\nAfter processing nums[" + (i - 1) + "] = " + nums[i - 1] + ":");

            for (int j = 0; j <= sum; j++) {
                // Don't include current element
                dp[i][j] = dp[i - 1][j];

                // Include current element if possible
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }

                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nFinal result: " + dp[n][sum]);
    }
}
