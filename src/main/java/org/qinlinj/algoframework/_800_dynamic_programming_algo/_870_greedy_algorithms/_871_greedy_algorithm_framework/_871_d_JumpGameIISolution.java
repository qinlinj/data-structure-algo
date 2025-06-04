package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

/**
 * JUMP GAME II - LeetCode 45 (Minimum Steps with Greedy Algorithm)
 * <p>
 * Problem: Given array nums where nums[i] represents maximum jump length from index i,
 * find the minimum number of jumps to reach the last index.
 * (Guaranteed that you can reach the last index)
 * <p>
 * Key Concepts:
 * 1. Brute Force: Recursively try all possible jumps from each position
 * 2. Dynamic Programming: Use memoization to avoid redundant calculations
 * 3. Greedy Insight: At each "level", choose the jump that reaches farthest
 * 4. BFS-like Approach: Process positions level by level (jump by jump)
 * <p>
 * Algorithm Evolution:
 * - Brute Force: O(k^n) exponential time complexity
 * - Dynamic Programming: O(n²) with memoization
 * - Greedy Algorithm: O(n) time, O(1) space
 * <p>
 * Greedy Strategy:
 * - Think of it as BFS: each "level" represents positions reachable in k jumps
 * - For current level, find the position that can reach farthest in next level
 * - Jump count increases only when we must move to next level
 * - Key variables: currentEnd (end of current level), farthest (max reach), jumps
 * <p>
 * Why Greedy Works:
 * - We want minimum jumps, so we should maximize distance per jump
 * - At each level, the position that reaches farthest is always optimal choice
 * - Local optimal (reach farthest) leads to global optimal (minimum jumps)
 */

import java.util.*;

public class _871_d_JumpGameIISolution {

    /**
     * Brute Force Solution - Explores all possible jump sequences
     * Time Complexity: O(k^n) where k is average jump distance
     * Space Complexity: O(n) for recursion stack
     */
    public static int jumpBruteForce(int[] nums) {
        return jumpHelper(nums, 0);
    }

    private static int jumpHelper(int[] nums, int position) {
        // Base case: reached or passed the last index
        if (position >= nums.length - 1) {
            return 0;
        }

        int minJumps = Integer.MAX_VALUE;
        int maxJump = nums[position];

        // Try all possible jumps from current position
        for (int jump = 1; jump <= maxJump; jump++) {
            int nextPosition = position + jump;
            if (nextPosition < nums.length) {
                int result = jumpHelper(nums, nextPosition);
                if (result != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, result + 1);
                }
            }
        }

        return minJumps;
    }

    /**
     * Dynamic Programming Solution with Memoization
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static int jumpDP(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return jumpDPHelper(nums, 0, memo);
    }

    private static int jumpDPHelper(int[] nums, int position, int[] memo) {
        if (position >= nums.length - 1) {
            return 0;
        }

        if (memo[position] != -1) {
            return memo[position];
        }

        int minJumps = Integer.MAX_VALUE;
        int maxJump = nums[position];

        for (int jump = 1; jump <= maxJump; jump++) {
            int nextPosition = position + jump;
            if (nextPosition < nums.length) {
                int result = jumpDPHelper(nums, nextPosition, memo);
                if (result != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, result + 1);
                }
            }
        }

        memo[position] = minJumps;
        return minJumps;
    }

    /**
     * Greedy Solution - BFS-like approach (Optimal)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * <p>
     * Think of this as BFS where each level represents positions reachable in k jumps
     */
    public static int jumpGreedy(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int jumps = 0;          // Number of jumps taken
        int currentEnd = 0;     // End of current level (positions reachable in 'jumps' steps)
        int farthest = 0;       // Farthest position reachable in next level

        // Process all positions except the last one
        for (int i = 0; i < n - 1; i++) {
            // Update farthest reachable position from current level
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of current level, we must jump
            if (i == currentEnd) {
                jumps++;                // Move to next level
                currentEnd = farthest;  // Update end of new level
            }
        }

        return jumps;
    }

    /**
     * Alternative Greedy Implementation - More explicit BFS style
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int jumpGreedyBFS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int jumps = 0;
        int left = 0, right = 0;  // Current level boundaries

        while (right < n - 1) {
            int farthest = 0;

            // Explore all positions in current level
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            // Move to next level
            left = right + 1;
            right = farthest;
            jumps++;
        }

        return jumps;
    }

    /**
     * Demonstrate the algorithm with step-by-step visualization
     */
    public static void demonstrateAlgorithm(int[] nums) {
        System.out.println("=== Jump Game II Algorithm Demonstration ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Goal: Find minimum jumps to reach index " + (nums.length - 1));
        System.out.println();

        // Show greedy algorithm step by step
        int n = nums.length;
        if (n <= 1) {
            System.out.println("Array has 0 or 1 element, no jumps needed!");
            return;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        System.out.println("Step-by-step Greedy Algorithm (BFS-like approach):");
        System.out.println("i | nums[i] | farthest | currentEnd | jumps | Action");
        System.out.println("--|--------|----------|------------|-------|--------");

        for (int i = 0; i < n - 1; i++) {
            int newFarthest = Math.max(farthest, i + nums[i]);
            String action = "explore";

            if (i == currentEnd) {
                jumps++;
                currentEnd = newFarthest;
                action = "JUMP! (next level)";
            }

            System.out.printf("%d |   %2d   |    %2d    |     %2d     |   %d   | %s%n",
                    i, nums[i], newFarthest, currentEnd, jumps, action);

            farthest = newFarthest;
        }

        System.out.println();
        System.out.println("Final Result: " + jumps + " jumps needed");
        System.out.println("Algorithm mimics BFS: each 'level' represents positions reachable in k jumps");
    }

    /**
     * Visualize the BFS-like levels
     */
    public static void visualizeLevels(int[] nums) {
        System.out.println("\n=== BFS Level Visualization ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println();

        int n = nums.length;
        if (n <= 1) return;

        int jumps = 0;
        int left = 0, right = 0;

        System.out.println("Level | Positions | Max Reach | Next Level Range");
        System.out.println("------|-----------|-----------|------------------");

        while (right < n - 1) {
            int farthest = 0;

            // Show current level
            System.out.printf("  %d   | [%d", jumps, left);
            if (left != right) {
                System.out.printf("-%d", right);
            }
            System.out.print("]     |");

            // Find farthest reachable from current level
            for (int i = left; i <= right; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            System.out.printf("    %2d     | [%d-%d]", farthest, right + 1, farthest);
            System.out.println();

            // Move to next level
            left = right + 1;
            right = farthest;
            jumps++;
        }

        System.out.println();
        System.out.println("Total levels (jumps): " + jumps);
    }

    /**
     * Performance comparison of all approaches
     */
    public static void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");

        // Test cases
        int[][] testCases = {
                {2, 3, 1, 1, 4},           // Expected: 2
                {2, 3, 0, 1, 4},           // Expected: 2
                {1, 1, 1, 1, 1},           // Expected: 4
                {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3},  // Expected: 2
                {1, 2, 3}                  // Expected: 2
        };

        for (int[] nums : testCases) {
            System.out.println("\nTesting: " + Arrays.toString(nums));

            long startTime, endTime;

            // Only test brute force for very small arrays
            if (nums.length <= 8) {
                startTime = System.nanoTime();
                int bruteForceResult = jumpBruteForce(nums);
                endTime = System.nanoTime();
                System.out.println("Brute Force: " + bruteForceResult +
                        " jumps (Time: " + (endTime - startTime) / 1000.0 + " μs)");
            }

            startTime = System.nanoTime();
            int dpResult = jumpDP(nums);
            endTime = System.nanoTime();
            System.out.println("Dynamic Programming: " + dpResult +
                    " jumps (Time: " + (endTime - startTime) / 1000.0 + " μs)");

            startTime = System.nanoTime();
            int greedyResult = jumpGreedy(nums);
            endTime = System.nanoTime();
            System.out.println("Greedy Algorithm: " + greedyResult +
                    " jumps (Time: " + (endTime - startTime) / 1000.0 + " μs)");

            startTime = System.nanoTime();
            int greedyBFSResult = jumpGreedyBFS(nums);
            endTime = System.nanoTime();
            System.out.println("Greedy BFS-style: " + greedyBFSResult +
                    " jumps (Time: " + (endTime - startTime) / 1000.0 + " μs)");
        }
    }

    /**
     * Explain the greedy insight
     */
    public static void explainGreedyInsight() {
        System.out.println("\n=== Greedy Algorithm Insight ===");
        System.out.println();

        System.out.println("1. BFS ANALOGY:");
        System.out.println("   - Think of positions as nodes in a graph");
        System.out.println("   - Each jump creates edges to reachable positions");
        System.out.println("   - BFS finds shortest path (minimum jumps)");
        System.out.println("   - But we don't need to store the queue explicitly!");
        System.out.println();

        System.out.println("2. LEVEL-BY-LEVEL PROCESSING:");
        System.out.println("   - Level 0: Starting position (index 0)");
        System.out.println("   - Level 1: All positions reachable in 1 jump");
        System.out.println("   - Level 2: All positions reachable in 2 jumps");
        System.out.println("   - Continue until we can reach the last index");
        System.out.println();

        System.out.println("3. GREEDY CHOICE:");
        System.out.println("   - Within each level, find the position that reaches farthest");
        System.out.println("   - This defines the boundary of the next level");
        System.out.println("   - We don't need to know exact positions, just the boundary!");
        System.out.println();

        System.out.println("4. WHY IT'S OPTIMAL:");
        System.out.println("   - BFS guarantees minimum number of levels (jumps)");
        System.out.println("   - Greedy choice ensures maximum progress per level");
        System.out.println("   - Local optimal (max reach per level) = Global optimal (min jumps)");
        System.out.println();

        System.out.println("5. KEY VARIABLES:");
        System.out.println("   - jumps: Current number of jumps (BFS level)");
        System.out.println("   - currentEnd: Rightmost position in current level");
        System.out.println("   - farthest: Rightmost position reachable in next level");
    }

    public static void main(String[] args) {
        // Example 1: Standard case
        int[] nums1 = {2, 3, 1, 1, 4};
        demonstrateAlgorithm(nums1);
        visualizeLevels(nums1);

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Example 2: Different case
        int[] nums2 = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        demonstrateAlgorithm(nums2);

        performanceComparison();
        explainGreedyInsight();

        System.out.println("\n=== KEY TAKEAWAYS ===");
        System.out.println("1. Jump Game II is essentially BFS for shortest path");
        System.out.println("2. Greedy optimization: track level boundaries instead of explicit BFS");
        System.out.println("3. Time complexity reduced from O(n²) to O(n) with greedy insight");
        System.out.println("4. Space complexity reduced from O(n) to O(1)");
        System.out.println("5. Local optimal choice (maximize reach) leads to global optimal solution");
    }
}