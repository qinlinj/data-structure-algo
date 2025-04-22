package org.qinlinj.algoframework._200_core_framework._210_greedy_algo_framework;

import java.util.*;

/**
 * Jump Game II - Greedy Algorithm Solution
 * ========================================
 * <p>
 * This class demonstrates solving the "Jump Game II" problem (LeetCode 45) using
 * both dynamic programming and greedy algorithm approaches, highlighting the
 * efficiency gains from identifying the greedy choice property.
 * <p>
 * Problem Description:
 * Given an array where each element represents the maximum jump distance from that position,
 * find the minimum number of jumps needed to reach the last index.
 * <p>
 * Key Insights:
 * <p>
 * 1. Dynamic Programming Approach:
 * - Define dp[i] as the minimum jumps needed to reach the end from position i
 * - For each position, try all possible jumps and choose the minimum
 * - Time complexity: O(n²) - Often too slow for large inputs
 * <p>
 * 2. Greedy Algorithm Optimization:
 * - Identify that we don't need to try all jumps at each position
 * - Instead, track the current jump's end boundary and the farthest position reachable
 * - When we reach the current jump's boundary, make a new jump to the farthest position
 * - Time complexity: O(n) - Dramatic improvement
 * <p>
 * 3. Greedy Choice Property Analysis:
 * - At each step, we don't need to recursively compute results for all possible next positions
 * - We can directly choose the position that allows us to reach the farthest
 * - This local optimal choice leads to the global optimal solution
 * <p>
 * General Approach to Greedy Problems:
 * 1. Always start with the brute force (exhaustive enumeration) approach
 * 2. Optimize using dynamic programming if overlapping subproblems exist
 * 3. If still too slow, look for greedy choice properties to avoid full enumeration
 * 4. Verify if local optimal choices lead to the global optimal solution
 */
public class GreedyJumpGameII {
    /**
     * Dynamic Programming solution for Jump Game II
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static int jumpDP(int[] nums) {
        int n = nums.length;
        // Memoization array to store minimum jumps from each position
        int[] memo = new int[n];
        // Initialize with n (representing infinity since we need at most n-1 jumps)
        Arrays.fill(memo, n);

        return dpHelper(nums, 0, memo);
    }

    /**
     * Helper function for dynamic programming approach
     * Definition: minimum jumps needed to reach the end from position p
     */
    private static int dpHelper(int[] nums, int p, int[] memo) {
        int n = nums.length;

        // Base case: already at or beyond the last position
        if (p >= n - 1) {
            return 0;
        }

        // Return cached result if available
        if (memo[p] != n) {
            return memo[p];
        }

        int steps = nums[p];
        // Try all possible jumps from current position
        for (int i = 1; i <= steps; i++) {
            // Calculate result for the subproblem (jumping i steps)
            int subProblem = dpHelper(nums, p + i, memo);
            // Update with minimum jumps
            memo[p] = Math.min(memo[p], subProblem + 1);
        }

        return memo[p];
    }

    /**
     * Greedy Algorithm solution for Jump Game II
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int jumpGreedy(int[] nums) {
        int n = nums.length;
        // If array has only one element, no jumps needed
        if (n == 1) return 0;

        int jumps = 0;       // Number of jumps made
        int end = 0;          // End of current jump range
        int farthest = 0;     // Farthest position reachable so far

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest position we can reach
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of current jump range
            if (i == end) {
                // Make a jump
                jumps++;
                // Update the end of new jump range
                end = farthest;

                // If we can already reach the last index, no need to continue
                if (end >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    /**
     * Demonstrates both approaches and compares their performance
     */
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {2, 3, 1, 1, 4},      // Example 1: Expected output = 2
                {2, 3, 0, 1, 4},      // Example 2: Expected output = 2
                {1, 2, 3, 4, 5},      // Linear increases: Expected output = 3
                {5, 4, 3, 2, 1},      // Decreasing values: Expected output = 1
                {1, 1, 1, 1, 1},      // Constant values: Expected output = 4
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}  // Large values: Expected output = 1
        };

        System.out.println("Jump Game II - Solution Comparison\n");
        System.out.println("Dynamic Programming vs Greedy Algorithm");
        System.out.println("=====================================");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];

            System.out.print("Test case " + (i + 1) + ": [");
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[j]);
                if (j < nums.length - 1) System.out.print(", ");
            }
            System.out.println("]");

            // Measure time for DP solution
            long startDP = System.nanoTime();
            int dpResult = jumpDP(nums);
            long endDP = System.nanoTime();
            double dpTime = (endDP - startDP) / 1_000_000.0;

            // Measure time for Greedy solution
            long startGreedy = System.nanoTime();
            int greedyResult = jumpGreedy(nums);
            long endGreedy = System.nanoTime();
            double greedyTime = (endGreedy - startGreedy) / 1_000_000.0;

            System.out.println("DP Solution: " + dpResult + " jumps (took " + dpTime + " ms)");
            System.out.println("Greedy Solution: " + greedyResult + " jumps (took " + greedyTime + " ms)");
            System.out.println("Speed Improvement: " + (dpTime / greedyTime) + "x faster");
            System.out.println("-------------------------------------");
        }

        // Create a larger test case to demonstrate performance difference more clearly
        int[] largeTestCase = new int[1000];
        for (int i = 0; i < largeTestCase.length; i++) {
            largeTestCase[i] = 1;  // Each position can jump 1 step
        }

        System.out.println("\nLarge Test Case (n=1000, all 1's)");
        System.out.println("Expected output: 999 jumps (one per position)");

        // Only measure greedy for large case (DP would be too slow)
        long startGreedy = System.nanoTime();
        int greedyResult = jumpGreedy(largeTestCase);
        long endGreedy = System.nanoTime();
        double greedyTime = (endGreedy - startGreedy) / 1_000_000.0;

        System.out.println("Greedy Solution: " + greedyResult + " jumps (took " + greedyTime + " ms)");
        System.out.println("DP Solution would be prohibitively slow for this case (O(n²))");

        System.out.println("\nConclusion:");
        System.out.println("1. Both approaches give the correct answer, but with vastly different performance.");
        System.out.println("2. For this problem, the greedy approach is dramatically more efficient (O(n) vs O(n²)).");
        System.out.println("3. The key insight is recognizing we don't need to try all possible jumps at each position.");
        System.out.println("4. Instead, we can keep track of the furthest reachable position and make optimal local choices.");
        System.out.println("5. This demonstrates the power of identifying the greedy choice property in a problem.");
    }
}
