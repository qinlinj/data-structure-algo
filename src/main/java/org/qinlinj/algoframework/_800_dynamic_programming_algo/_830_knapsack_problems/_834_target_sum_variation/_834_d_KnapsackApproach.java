package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

/**
 * Knapsack Approach to Target Sum Problem
 * <p>
 * Key Points:
 * - The Target Sum problem can be transformed into a subset sum problem (a variation of the knapsack problem)
 * - Mathematical derivation:
 * If we split the array into two subsets P (positive) and N (negative):
 * sum(P) - sum(N) = target
 * sum(P) + sum(N) = sum(nums)
 * Solving these equations: sum(P) = (target + sum(nums)) / 2
 * - The problem becomes: count subsets with sum equal to (target + sum(nums)) / 2
 * - This is a classic 0-1 knapsack problem pattern
 * - We can solve it using a 2D dynamic programming approach
 * - Further optimization: reduce to a 1D array with careful traversal order
 * - Time complexity: O(n*sum), Space complexity: optimized to O(sum)
 */
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

    /**
     * Demonstration of the knapsack/subset sum approach
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println("=== Knapsack/Subset Sum Approach ===");
        System.out.println("Input: [1, 1, 1, 1, 1], target = 3");

        int ways = findTargetSumWays(nums, target);
        System.out.println("Number of ways (2D approach): " + ways);

        ways = findTargetSumWaysOptimized(nums, target);
        System.out.println("Number of ways (1D optimized approach): " + ways);

        // Calculate subset sum for visualization
        int sum = 0;
        for (int num : nums) sum += num;
        int subsetSum = (sum + target) / 2;

        // Visualize the DP table calculation
        System.out.println("\nVisualization of the DP calculation:");
        printDPTable(nums, subsetSum);

        System.out.println("\nMathematical Transformation:");
        System.out.println("1. Let P be the subset with + sign, N be the subset with - sign");
        System.out.println("2. We know: sum(P) - sum(N) = target");
        System.out.println("3. We also know: sum(P) + sum(N) = sum(nums)");
        System.out.println("4. Solving these equations: sum(P) = (target + sum(nums)) / 2");
        System.out.println("5. So we need to find subsets with sum = " + subsetSum);

        System.out.println("\nTime complexity: O(n*sum)");
        System.out.println("Space complexity: O(sum) with 1D optimization");
    }
}