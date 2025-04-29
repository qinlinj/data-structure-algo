package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue Application with Dynamic Programming - Jump Game VI
 * LeetCode 1696: Jump Game VI
 * <p>
 * Problem Description:
 * You are given an array nums and an integer k. You start at index 0 and can jump up to k
 * steps forward to any index. Your score is the sum of all elements you land on.
 * Return the maximum score you can get.
 * <p>
 * Approach:
 * This problem combines dynamic programming with monotonic queue optimization:
 * <p>
 * 1. Define dp[i] as the maximum score ending at position i
 * 2. The recurrence relation is: dp[i] = nums[i] + max(dp[i-k], dp[i-k+1], ..., dp[i-1])
 * 3. Use a monotonic queue to efficiently find the maximum dp value in the sliding window
 * <p>
 * Without a monotonic queue, the time complexity would be O(n*k) due to the nested loops
 * needed to find the maximum dp value in the previous k positions. With the monotonic
 * queue optimization, we reduce this to O(n) time.
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the dp array and monotonic queue
 */

import java.util.*;

public class _348_e_JumpGameWithDP {

    /**
     * Find the maximum score by jumping up to k steps at a time
     *
     * @param nums The input array of scores
     * @param k The maximum number of steps allowed
     * @return The maximum possible score
     */
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;

        // dp[i] represents the maximum score ending at position i
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);

        // Base case: start at index 0
        dp[0] = nums[0];

        // Use monotonic queue to track maximum dp values in the sliding window
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        window.push(dp[0]);

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            // The maximum score at position i is the current value plus
            // the maximum score from the previous k positions
            dp[i] = nums[i] + window.max();

            // Maintain the window size (at most k elements)
            if (window.size() == k) {
                window.pop();
            }

            // Add the current dp value to the window
            window.push(dp[i]);
        }

        return dp[n - 1];
    }

    /**
     * Demonstrate the solution with step-by-step progression
     */
    public static void demonstrateSolution(int[] nums, int k) {
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("k: " + k);
        System.out.println("\nStep-by-step DP calculation with monotonic queue:");

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];

        System.out.println("Initial DP[0] = " + dp[0]);

        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        window.push(dp[0]);

        for (int i = 1; i < n; i++) {
            System.out.println("\nCalculating DP[" + i + "]:");
            System.out.println("  Current value nums[" + i + "] = " + nums[i]);
            System.out.println("  Maximum DP value in previous " + Math.min(i, k) + " positions: " + window.max());

            dp[i] = nums[i] + window.max();
            System.out.println("  DP[" + i + "] = " + nums[i] + " + " + window.max() + " = " + dp[i]);

            if (window.size() == k) {
                int removed = window.pop();
                System.out.println("  Removing " + removed + " from window (size limit k = " + k + ")");
            }

            window.push(dp[i]);
            System.out.println("  Updated DP array: " + Arrays.toString(dp));
        }

        System.out.println("\nFinal result (DP[" + (n - 1) + "]): " + dp[n - 1]);
    }

    /**
     * Alternative implementation showing the progression from brute force to optimized solution
     */
    public static void showProgressiveOptimization() {
        System.out.println("\n===== PROGRESSIVE OPTIMIZATION =====");
        System.out.println("1. Recursive solution (would time out on large inputs):");
        System.out.println("   - Calculate dp(i) by checking all dp(i-1), dp(i-2), ..., dp(i-k)");
        System.out.println("   - Time complexity: O(n^k) - exponential!");

        System.out.println("\n2. Memoized recursive solution:");
        System.out.println("   - Add memoization to avoid recalculating subproblems");
        System.out.println("   - Time complexity: O(nk) - still too slow for large k");

        System.out.println("\n3. Bottom-up DP solution:");
        System.out.println("   - Fill dp array iteratively from start to end");
        System.out.println("   - For each position, check previous k positions for maximum");
        System.out.println("   - Time complexity: O(nk) - still too slow for large k");

        System.out.println("\n4. DP with monotonic queue optimization:");
        System.out.println("   - Use monotonic queue to track maximum dp value in sliding window");
        System.out.println("   - Eliminates the inner loop for finding maximum");
        System.out.println("   - Time complexity: O(n) - linear time!");
    }

    /**
     * Main method to run examples
     */
    public static void main(String[] args) {
        System.out.println("===== JUMP GAME VI - DP WITH MONOTONIC QUEUE =====\n");

        // Example 1
        int[] nums1 = {1, -1, -2, 4, -7, 3};
        int k1 = 2;
        int result1 = maxResult(nums1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + result1);  // Expected: 7
        System.out.println("Explanation: 1 + (-1) + 4 + 3 = 7");

        // Example 2
        int[] nums2 = {10, -5, -2, 4, 0, 3};
        int k2 = 3;
        int result2 = maxResult(nums2, k2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + result2);  // Expected: 17
        System.out.println("Explanation: 10 + 4 + 3 = 17");

        // Example 3
        int[] nums3 = {1, -5, -20, 4, -1, 3, -6, -3};
        int k3 = 2;
        int result3 = maxResult(nums3, k3);
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + result3);  // Expected: 0

        // Detailed demonstration
        System.out.println("\n===== DETAILED DEMONSTRATION =====");
        demonstrateSolution(nums1, k1);

        // Show progressive optimization
        showProgressiveOptimization();
    }

    /**
     * Generic Monotonic Queue implementation
     */
    static class MonotonicQueue<E extends Comparable<E>> {
        // Standard queue to store all elements
        private LinkedList<E> q = new LinkedList<>();
        // Max queue (decreasing order)
        private LinkedList<E> maxq = new LinkedList<>();
        // Min queue (increasing order)
        private LinkedList<E> minq = new LinkedList<>();

        public void push(E elem) {
            q.addLast(elem);

            // Update max queue
            while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
                maxq.pollLast();
            }
            maxq.addLast(elem);

            // Update min queue
            while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
                minq.pollLast();
            }
            minq.addLast(elem);
        }

        public E max() {
            return maxq.getFirst();
        }

        public E min() {
            return minq.getFirst();
        }

        public E pop() {
            E deleteVal = q.pollFirst();

            if (deleteVal.equals(maxq.getFirst())) {
                maxq.pollFirst();
            }

            if (deleteVal.equals(minq.getFirst())) {
                minq.pollFirst();
            }

            return deleteVal;
        }

        public int size() {
            return q.size();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
}
