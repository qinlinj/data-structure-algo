package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue Application with Dynamic Programming - Constrained Subset Sum
 * LeetCode 1425: Constrained Subset Sum
 * <p>
 * Problem Description:
 * Given an array nums and an integer k, return the maximum sum of a non-empty
 * subsequence of nums such that every two consecutive integers in the subsequence
 * have indices at most k apart in the original array.
 * <p>
 * Approach:
 * This is a classic dynamic programming problem with an optimization using monotonic queue:
 * <p>
 * 1. Define dp[i] as the maximum sum of a subsequence ending at index i
 * 2. The recurrence relation is: dp[i] = nums[i] + max(0, dp[i-k], dp[i-k+1], ..., dp[i-1])
 * 3. Use a monotonic queue to efficiently find the maximum dp value in the sliding window
 * <p>
 * This problem is similar to Jump Game VI, but with a slight difference:
 * - In Jump Game VI, we must include each element we land on
 * - In this problem, we can choose to exclude some elements
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the dp array and monotonic queue
 */

import java.util.*;

public class _348_f_ConstrainedSubsetSum {

    /**
     * Find the maximum sum of a subsequence with indices at most k apart
     *
     * @param nums The input array
     * @param k The maximum distance between consecutive elements
     * @return The maximum subsequence sum
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;

        // dp[i] represents the maximum sum of a subsequence ending at index i
        int[] dp = new int[n];

        // Use monotonic queue to track maximum dp values in the sliding window
        MonotonicQueue<Integer> window = new MonotonicQueue<>();

        // Initialize the first element
        dp[0] = nums[0];
        window.push(dp[0]);

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            // Calculate dp[i]: either include previous subsequence or start a new one
            dp[i] = Math.max(nums[i], window.max() + nums[i]);

            // Maintain the window size (at most k elements)
            if (window.size() == k) {
                window.pop();
            }

            // Add the current dp value to the window
            window.push(dp[i]);
        }

        // Find the maximum value in the dp array
        int maxSum = Integer.MIN_VALUE;
        for (int value : dp) {
            maxSum = Math.max(maxSum, value);
        }

        return maxSum;
    }

    /**
     * Unoptimized DP solution (without monotonic queue) - for comparison
     */
    public static int constrainedSubsetSumUnoptimized(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            // Default: just take the current element
            dp[i] = nums[i];

            // Check previous k positions for maximum dp value
            for (int j = 1; j <= k && i - j >= 0; j++) {
                dp[i] = Math.max(dp[i], nums[i] + dp[i - j]);
            }
        }

        // Find maximum value in dp array
        int maxSum = Integer.MIN_VALUE;
        for (int value : dp) {
            maxSum = Math.max(maxSum, value);
        }

        return maxSum;
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

        MonotonicQueue<Integer> window = new MonotonicQueue<>();

        // Initialize first element
        dp[0] = nums[0];
        System.out.println("DP[0] = " + dp[0] + " (just the element at index 0)");
        window.push(dp[0]);

        for (int i = 1; i < n; i++) {
            System.out.println("\nCalculating DP[" + i + "]:");
            System.out.println("  Current element nums[" + i + "] = " + nums[i]);
            System.out.println("  Maximum DP value in previous " + Math.min(i, k) + " positions: " + window.max());

            int withPrevious = window.max() + nums[i];
            int justCurrent = nums[i];

            System.out.println("  Option 1 (with previous): " + window.max() + " + " + nums[i] + " = " + withPrevious);
            System.out.println("  Option 2 (just current): " + justCurrent);

            dp[i] = Math.max(withPrevious, justCurrent);
            System.out.println("  DP[" + i + "] = " + dp[i] + " (choose maximum)");

            if (window.size() == k) {
                int removed = window.pop();
                System.out.println("  Removing " + removed + " from window (size limit k = " + k + ")");
            }

            window.push(dp[i]);
            System.out.println("  Updated DP array: " + Arrays.toString(dp));
        }

        // Find maximum
        int maxSum = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxSum) {
                maxSum = dp[i];
                maxIndex = i;
            }
        }

        System.out.println("\nMaximum subsequence sum: " + maxSum + " (at index " + maxIndex + ")");

        // Try to reconstruct the subsequence
        System.out.println("\nPossible subsequence reconstruction:");
        reconstructSubsequence(nums, dp, k);
    }

    /**
     * Helper function to reconstruct a valid subsequence
     */
    private static void reconstructSubsequence(int[] nums, int[] dp, int k) {
        int n = nums.length;

        // Find the ending index with maximum sum
        int maxSum = Integer.MIN_VALUE;
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxSum) {
                maxSum = dp[i];
                endIndex = i;
            }
        }

        // Reconstruct the subsequence
        LinkedList<Integer> subsequence = new LinkedList<>();
        subsequence.addFirst(nums[endIndex]);

        int currentSum = dp[endIndex];
        int currentIndex = endIndex;

        while (currentIndex > 0) {
            boolean found = false;

            // Try to find the previous element in the subsequence
            for (int j = 1; j <= k && currentIndex - j >= 0; j++) {
                int prevIndex = currentIndex - j;

                // If removing this element gives us the correct previous state
                if (currentSum - nums[currentIndex] == dp[prevIndex]) {
                    subsequence.addFirst(nums[prevIndex]);
                    currentSum = dp[prevIndex];
                    currentIndex = prevIndex;
                    found = true;
                    break;
                }
            }

            // If no previous element was found, this element starts a new subsequence
            if (!found) {
                break;
            }
        }

        System.out.println("One possible subsequence: " + subsequence);
        System.out.println("Sum: " + maxSum);
    }

    /**
     * Main method to run examples
     */
    public static void main(String[] args) {
        System.out.println("===== CONSTRAINED SUBSET SUM =====\n");

        // Example 1
        int[] nums1 = {10, 2, -10, 5, 20};
        int k1 = 2;
        int result1 = constrainedSubsetSum(nums1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + result1);  // Expected: 37
        System.out.println("Explanation: Subsequence [10, 2, 5, 20]");

        // Example 2
        int[] nums2 = {-1, -2, -3};
        int k2 = 1;
        int result2 = constrainedSubsetSum(nums2, k2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + result2);  // Expected: -1
        System.out.println("Explanation: Subsequence [-1]");

        // Example 3
        int[] nums3 = {10, -2, -10, -5, 20};
        int k3 = 2;
        int result3 = constrainedSubsetSum(nums3, k3);
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + result3);  // Expected: 23
        System.out.println("Explanation: Subsequence [10, -2, -5, 20]");

        // Detailed demonstration
        System.out.println("\n===== DETAILED DEMONSTRATION =====");
        demonstrateSolution(nums1, k1);

        // Compare with unoptimized solution
        System.out.println("\n===== PERFORMANCE COMPARISON =====");
        System.out.println("Optimized solution (with monotonic queue): O(n) time complexity");
        System.out.println("Unoptimized solution (nested loops): O(nk) time complexity");

        System.out.println("\nResults should be identical:");
        System.out.println("Example 1 - Optimized: " + constrainedSubsetSum(nums1, k1));
        System.out.println("Example 1 - Unoptimized: " + constrainedSubsetSumUnoptimized(nums1, k1));
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
