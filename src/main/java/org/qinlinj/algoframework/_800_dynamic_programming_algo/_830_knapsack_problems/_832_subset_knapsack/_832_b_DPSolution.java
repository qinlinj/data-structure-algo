package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._832_subset_knapsack;

public class _832_b_DPSolution {
    /**
     * Determines if the array can be partitioned into two equal sum subsets
     * using a 2D dynamic programming approach
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If sum is odd, we cannot partition into equal subsets
        if (sum % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int target = sum / 2;

        // Create DP table
        // dp[i][j] = true if a subset of the first i items can sum up to j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base case: we can always form a sum of 0 by not selecting any element
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // If current item's value exceeds the current target sum
                if (j < nums[i - 1]) {
                    // We can't include this item, so result is same as without it
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // We have two choices: include the current item or exclude it
                    // dp[i-1][j] = exclude current item
                    // dp[i-1][j-nums[i-1]] = include current item
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        // The answer is in dp[n][target]
        return dp[n][target];
    }

    /**
     * Visualizes the DP table construction for better understanding
     */
    public static void visualizeDPTable(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            System.out.println("\nSum is odd, cannot be partitioned into equal subsets.");
            return;
        }

        int n = nums.length;
        int target = sum / 2;

        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        System.out.println("\nDP Table Construction for " + java.util.Arrays.toString(nums) + ":");
        System.out.println("Target sum = " + target);
        System.out.println("\nInitial DP table (after setting base case dp[i][0] = true):");
        printDPTable(dp, n, target, nums);

        // Fill the DP table and visualize each step
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    System.out.printf("dp[%d][%d] = dp[%d][%d] = %b (can't include item %d with value %d)\n",
                            i, j, i - 1, j, dp[i][j], i, nums[i - 1]);
                } else {
                    boolean exclude = dp[i - 1][j];
                    boolean include = dp[i - 1][j - nums[i - 1]];
                    dp[i][j] = exclude || include;

                    System.out.printf("dp[%d][%d] = dp[%d][%d] OR dp[%d][%d] = %b OR %b = %b\n",
                            i, j, i - 1, j, i - 1, j - nums[i - 1], exclude, include, dp[i][j]);

                    if (dp[i][j]) {
                        if (include) {
                            System.out.printf("  (We can make sum %d by including item %d with value %d)\n",
                                    j, i, nums[i - 1]);
                        } else {
                            System.out.printf("  (We can make sum %d without including item %d)\n",
                                    j, i);
                        }
                    }
                }
            }

            System.out.println("\nDP table after considering item " + i + " (value = " + nums[i - 1] + "):");
            printDPTable(dp, n, target, nums);
        }

        System.out.println("\nFinal result: dp[" + n + "][" + target + "] = " + dp[n][target]);

        if (dp[n][target]) {
            // Trace back to find which items are included in the subset
            System.out.println("\nOne possible partition:");
            traceSubset(dp, nums, target);
        }
    }
}
