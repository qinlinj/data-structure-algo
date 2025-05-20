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

    /**
     * Visualizes the 1D DP array updates during the algorithm execution
     */
    private static void visualize1DDP(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            System.out.println("\nSum is odd, cannot be partitioned into equal subsets.");
            return;
        }

        int target = sum / 2;

        // Create 1D DP array
        boolean[] dp = new boolean[target + 1];

        // Base case
        dp[0] = true;

        System.out.println("\n1D DP Array Visualization for " + java.util.Arrays.toString(nums) + ":");
        System.out.println("Target sum = " + target);

        System.out.println("\nInitial DP array (after setting base case dp[0] = true):");
        printDPArray(dp);

        // Process each item and visualize
        for (int i = 0; i < nums.length; i++) {
            System.out.println("\nProcessing item " + (i + 1) + " with value " + nums[i] + ":");

            // Important: We need to create a copy of dp for visualization purposes
            // In the actual algorithm, we directly update the array
            boolean[] dpCopy = dp.clone();

            // Iterate from target down to nums[i]
            for (int j = target; j >= nums[i]; j--) {
                if (!dp[j] && dp[j - nums[i]]) {
                    dpCopy[j] = true;
                    System.out.printf("dp[%d] = true (can make sum %d by adding %d to sum %d)\n",
                            j, j, nums[i], j - nums[i]);
                }
            }

            // Update dp array
            dp = dpCopy;

            System.out.println("\nDP array after processing item " + (i + 1) + ":");
            printDPArray(dp);
        }

        System.out.println("\nFinal result: dp[" + target + "] = " + dp[target]);

        if (dp[target]) {
            System.out.println("The array can be partitioned into two equal sum subsets!");

            // Find one possible partition (this is more complex with 1D DP)
            // We'll need to work backward from the result
            findPartition(nums, target);
        } else {
            System.out.println("The array cannot be partitioned into two equal sum subsets.");
        }
    }

    /**
     * Helper method to print the 1D DP array
     */
    private static void printDPArray(boolean[] dp) {
        System.out.print("Sum: ");
        for (int j = 0; j < dp.length; j++) {
            System.out.printf("%3d ", j);
        }
        System.out.println();

        System.out.print("    ");
        for (int j = 0; j < dp.length; j++) {
            System.out.printf("%3s ", dp[j] ? "T" : "F");
        }
        System.out.println();
    }

    /**
     * Attempts to find one possible partition by working backward from target
     * This is more complex with 1D DP since we don't store the full state history
     */
    private static void findPartition(int[] nums, int target) {
        // Re-run the algorithm to check if partition is possible
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // Store which items contribute to each sum
        // This is a map from sum -> item index that was added to achieve this sum
        int[][] contributors = new int[target + 1][nums.length];
        int[] contributorCounts = new int[target + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                if (!dp[j] && dp[j - nums[i]]) {
                    dp[j] = true;

                    // Record that item i contributed to sum j
                    contributors[j][contributorCounts[j]] = i;
                    contributorCounts[j]++;
                }
            }
        }

        if (!dp[target]) {
            System.out.println("No solution found.");
            return;
        }

        // Trace back from target to find one subset
        boolean[] included = new boolean[nums.length];
        int remainingTarget = target;

        while (remainingTarget > 0) {
            // Get the last item that contributed to this sum
            int itemIdx = contributors[remainingTarget][contributorCounts[remainingTarget] - 1];
            included[itemIdx] = true;
            remainingTarget -= nums[itemIdx];
        }

        // Print the two subsets
        System.out.print("Subset 1: [");
        boolean first = true;
        for (int i = 0; i < nums.length; i++) {
            if (included[i]) {
                if (!first) System.out.print(", ");
                System.out.print(nums[i]);
                first = false;
            }
        }
        System.out.println("]");

        System.out.print("Subset 2: [");
        first = true;
        for (int i = 0; i < nums.length; i++) {
            if (!included[i]) {
                if (!first) System.out.print(", ");
                System.out.print(nums[i]);
                first = false;
            }
        }
        System.out.println("]");
    }
}
