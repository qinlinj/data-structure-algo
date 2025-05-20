package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._832_subset_knapsack;

public class _832_c_OptimizedSolution {
    /**
     * Original 2D DP solution (for comparison)
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int target = sum / 2;

        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

}
