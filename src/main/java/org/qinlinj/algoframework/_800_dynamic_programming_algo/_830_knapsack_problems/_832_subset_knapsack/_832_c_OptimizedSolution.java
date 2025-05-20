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

    /**
     * Optimized 1D DP solution with reduced space complexity
     */
    public static boolean canPartitionOptimized(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If sum is odd, return false
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        // Create 1D DP array
        boolean[] dp = new boolean[target + 1];

        // Base case: can make sum=0 with empty subset
        dp[0] = true;

        // Process each item
        for (int i = 0; i < nums.length; i++) {
            // IMPORTANT: Iterate from target down to nums[i]
            // This ensures we don't use the same item multiple times
            for (int j = target; j >= nums[i]; j--) {
                // dp[j] = can make sum j without current item
                // dp[j-nums[i]] = can make sum (j-nums[i]) without current item
                // If we can make (j-nums[i]) without the current item,
                // then we can make j by including the current item
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }

    /**
     * Compares memory usage of 2D and 1D approaches
     */
    private static void compareMemoryUsage(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            System.out.println("\nSum is odd, cannot proceed with memory comparison.");
            return;
        }

        int n = nums.length;
        int target = sum / 2;

        // Calculate memory for 2D approach
        int memory2D = (n + 1) * (target + 1) * 1; // 1 byte for boolean

        // Calculate memory for 1D approach
        int memory1D = (target + 1) * 1; // 1 byte for boolean

        System.out.println("\nMemory Usage Comparison:");
        System.out.println("2D DP array: " + memory2D + " bytes");
        System.out.println("1D DP array: " + memory1D + " bytes");
        System.out.println("Memory reduction: " + (100 - (memory1D * 100.0 / memory2D)) + "%");
    }

}
