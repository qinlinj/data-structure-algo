package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework; /**
 * JUMP GAME - LeetCode 55 (Greedy Algorithm Example)
 * <p>
 * Problem: Given array nums where nums[i] represents maximum jump length from index i,
 * determine if you can reach the last index.
 * <p>
 * Key Concepts:
 * 1. Brute Force Approach: Explore all possible jump paths (exponential time)
 * 2. Greedy Insight: At each position, we only care about the farthest reachable position
 * 3. Greedy Choice Property: Always track maximum reachable distance
 * 4. Local Optimal: Choose the jump that extends farthest reach
 * 5. Global Optimal: If farthest reach >= last index, return true
 * <p>
 * Algorithm Evolution:
 * - Brute Force: O(k^n) where k is average jump length, n is array length
 * - Dynamic Programming: O(n²) with memoization
 * - Greedy Algorithm: O(n) time, O(1) space
 * <p>
 * Greedy Strategy:
 * - Track farthest reachable position while iterating
 * - If current position > farthest, we're stuck (return false)
 * - If farthest >= last index, we can reach the end (return true)
 * <p>
 * Why Greedy Works:
 * - We don't need to know exact path, just if destination is reachable
 * - At each step, extending maximum reach is always optimal
 * - Local optimal choices (max reach) lead to global optimal solution
 */

import java.util.*;

public class _871_c_JumpGameSolution {

    /**
     * Brute Force Solution - Explores all possible paths
     * Time Complexity: O(k^n) where k is average jump distance
     * Space Complexity: O(n) for recursion stack
     * <p>
     * This approach times out for large inputs but shows the exhaustive nature
     */
    public static boolean canJumpBruteForce(int[] nums) {
        return canJumpHelper(nums, 0);
    }

    private static boolean canJumpHelper(int[] nums, int position) {
        // Base case: reached or passed the last index
        if (position >= nums.length - 1) {
            return true;
        }

        // Try all possible jumps from current position
        int maxJump = nums[position];
        for (int jump = 1; jump <= maxJump; jump++) {
            if (canJumpHelper(nums, position + jump)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Dynamic Programming Solution with Memoization
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static boolean canJumpDP(int[] nums) {
        Boolean[] memo = new Boolean[nums.length];
        return canJumpDPHelper(nums, 0, memo);
    }

    private static boolean canJumpDPHelper(int[] nums, int position, Boolean[] memo) {
        if (position >= nums.length - 1) {
            return true;
        }

        if (memo[position] != null) {
            return memo[position];
        }

        int maxJump = nums[position];
        for (int jump = 1; jump <= maxJump; jump++) {
            if (canJumpDPHelper(nums, position + jump, memo)) {
                memo[position] = true;
                return true;
            }
        }

        memo[position] = false;
        return false;
    }

    /**
     * Greedy Solution - Optimal approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * <p>
     * Key insight: We only need to track the farthest reachable position
     */
    public static boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        int farthest = 0;

        // Iterate through array (except last element)
        for (int i = 0; i < n - 1; i++) {
            // Update farthest reachable position
            farthest = Math.max(farthest, i + nums[i]);

            // If we can't progress further, we're stuck
            if (farthest <= i) {
                return false;
            }
        }

        // Check if we can reach the last index
        return farthest >= n - 1;
    }

    /**
     * Alternative Greedy Implementation - Bottom-up approach
     * Tracks the leftmost "good" position that can reach the end
     */
    public static boolean canJumpGreedyBottomUp(int[] nums) {
        int n = nums.length;
        int lastGoodIndex = n - 1;

        // Work backwards from the end
        for (int i = n - 2; i >= 0; i--) {
            // Check if current position can reach the last good position
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }

        return lastGoodIndex == 0;
    }

    /**
     * Demonstrate the algorithm with step-by-step visualization
     */
    public static void demonstrateAlgorithm(int[] nums) {
        System.out.println("=== Jump Game Algorithm Demonstration ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println();

        // Show greedy algorithm step by step
        int n = nums.length;
        int farthest = 0;
        boolean canReach = true;

        System.out.println("Step-by-step Greedy Algorithm:");
        System.out.println("Position | Value | Farthest | Status");
        System.out.println("---------|-------|----------|--------");

        for (int i = 0; i < n - 1; i++) {
            int newFarthest = Math.max(farthest, i + nums[i]);
            String status = "OK";

            if (newFarthest <= i && nums[i] == 0) {
                status = "STUCK";
                canReach = false;
            }

            System.out.printf("   %2d    |   %2d  |    %2d    | %s%n",
                    i, nums[i], newFarthest, status);

            farthest = newFarthest;

            if (!canReach) break;
        }

        System.out.println();
        System.out.println("Final Result: " + (farthest >= n - 1 ? "CAN" : "CANNOT") + " reach the end");
        System.out.println("Last index: " + (n - 1) + ", Farthest reachable: " + farthest);
    }

    /**
     * Performance comparison of all approaches
     */
    public static void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");

        // Test cases
        int[][] testCases = {
                {2, 3, 1, 1, 4},        // Can reach
                {3, 2, 1, 0, 4},        // Cannot reach
                {2, 0, 0},              // Cannot reach
                {1, 1, 1, 1, 1},        // Can reach
                {5, 4, 3, 2, 1, 0}      // Can reach
        };

        for (int[] nums : testCases) {
            System.out.println("\nTesting: " + Arrays.toString(nums));

            long startTime, endTime;

            // Only test brute force for small arrays
            if (nums.length <= 10) {
                startTime = System.nanoTime();
                boolean bruteForcResult = canJumpBruteForce(nums);
                endTime = System.nanoTime();
                System.out.println("Brute Force: " + bruteForcResult +
                        " (Time: " + (endTime - startTime) / 1000.0 + " μs)");
            }

            startTime = System.nanoTime();
            boolean dpResult = canJumpDP(nums);
            endTime = System.nanoTime();
            System.out.println("Dynamic Programming: " + dpResult +
                    " (Time: " + (endTime - startTime) / 1000.0 + " μs)");

            startTime = System.nanoTime();
            boolean greedyResult = canJumpGreedy(nums);
            endTime = System.nanoTime();
            System.out.println("Greedy Algorithm: " + greedyResult +
                    " (Time: " + (endTime - startTime) / 1000.0 + " μs)");

            startTime = System.nanoTime();
            boolean greedyBottomUpResult = canJumpGreedyBottomUp(nums);
            endTime = System.nanoTime();
            System.out.println("Greedy Bottom-Up: " + greedyBottomUpResult +
                    " (Time: " + (endTime - startTime) / 1000.0 + " μs)");
        }
    }

    /**
     * Explain why greedy works for this problem
     */
    public static void explainGreedyLogic() {
        System.out.println("\n=== Why Greedy Algorithm Works ===");
        System.out.println();
        System.out.println("1. PROBLEM NATURE:");
        System.out.println("   - We only need to know IF we can reach the end, not HOW");
        System.out.println("   - Multiple paths may exist, but we only need one valid path");
        System.out.println();

        System.out.println("2. GREEDY CHOICE PROPERTY:");
        System.out.println("   - At each position, extending maximum reach is always beneficial");
        System.out.println("   - If we can reach position X, we can also reach all positions < X");
        System.out.println("   - Local optimal choice: Track maximum reachable distance");
        System.out.println();

        System.out.println("3. WHY WE DON'T NEED TO EXPLORE ALL PATHS:");
        System.out.println("   - If farthest reach >= target, ANY valid path will work");
        System.out.println("   - We don't care about the specific sequence of jumps");
        System.out.println("   - Only the maximum achievable distance matters");
        System.out.println();

        System.out.println("4. ALGORITHM EFFICIENCY:");
        System.out.println("   - Single pass through array: O(n) time");
        System.out.println("   - No recursion or memoization needed: O(1) space");
        System.out.println("   - Optimal solution without exploring all possibilities");
    }

    public static void main(String[] args) {
        // Example 1: Can reach the end
        int[] nums1 = {2, 3, 1, 1, 4};
        demonstrateAlgorithm(nums1);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Example 2: Cannot reach the end
        int[] nums2 = {3, 2, 1, 0, 4};
        demonstrateAlgorithm(nums2);

        performanceComparison();
        explainGreedyLogic();

        System.out.println("\n=== KEY TAKEAWAYS ===");
        System.out.println("1. Greedy algorithms excel when local optimal choices lead to global optimum");
        System.out.println("2. Jump Game has greedy choice property: maximizing reach is always beneficial");
        System.out.println("3. Sometimes we can avoid exploring all possibilities with smart observations");
        System.out.println("4. Complexity reduction: O(k^n) → O(n²) → O(n) shows power of right approach");
    }
}