package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue Application - Shortest Subarray with Sum at Least K
 * LeetCode 862: Shortest Subarray with Sum at Least K
 * <p>
 * Problem Description:
 * Find the length of the shortest non-empty subarray with a sum at least K.
 * If there is no such subarray, return -1.
 * <p>
 * Approach:
 * 1. Calculate prefix sum array to efficiently compute subarray sums
 * 2. Use a monotonic queue to track potential starting points of subarrays
 * 3. For each ending position, find the shortest valid subarray by checking
 * against the monotonic queue
 * 4. Maintain the monotonic queue in increasing order of prefix sums
 * <p>
 * This problem combines three techniques:
 * - Prefix sum for efficient subarray sum calculation
 * - Sliding window for tracking subarray boundaries
 * - Monotonic queue for efficiently finding optimal starting points
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the prefix sum array and monotonic queue
 */

import java.util.*;

public class _348_c_ShortestSubarrayWithSumK {

    /**
     * Find the length of the shortest subarray with sum at least K
     *
     * @param nums The input array
     * @param k The minimum required sum
     * @return The length of the shortest valid subarray, or -1 if none exists
     */
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        // Use long to prevent integer overflow with large sums
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;

        // Calculate prefix sums
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // Initialize result to a value larger than any possible subarray length
        int minLength = Integer.MAX_VALUE;

        // Use a monotonic queue to store indices of the prefix sums
        // (we'll use a custom implementation to track indices)
        LinkedList<Integer> queue = new LinkedList<>();

        for (int right = 0; right <= n; right++) {
            // Before adding a new prefix sum, remove prefix sums from the queue
            // that are greater than or equal to the current one
            // This ensures the queue maintains increasing order of prefix sums
            while (!queue.isEmpty() && prefixSum[queue.getLast()] >= prefixSum[right]) {
                queue.removeLast();
            }

            // Try to find a valid subarray ending at the current position
            while (!queue.isEmpty() && prefixSum[right] - prefixSum[queue.getFirst()] >= k) {
                // We found a valid subarray, update the minimum length
                minLength = Math.min(minLength, right - queue.getFirst());
                // Remove this starting point as we've found a valid subarray with it
                queue.removeFirst();
            }

            // Add the current index to the queue
            queue.addLast(right);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public static void demonstrateSolution(int[] nums, int k) {
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("K: " + k);
        System.out.println("\nStep-by-step demonstration:");

        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;

        // Calculate prefix sums
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        System.out.println("Prefix sums: " + Arrays.toString(prefixSum));

        int minLength = Integer.MAX_VALUE;
        LinkedList<Integer> queue = new LinkedList<>();

        for (int right = 0; right <= n; right++) {
            System.out.println("\nProcessing index " + right + " (prefix sum = " + prefixSum[right] + ")");
            System.out.println("  Current queue: " + queue);

            // Remove larger prefix sums from the back
            while (!queue.isEmpty() && prefixSum[queue.getLast()] >= prefixSum[right]) {
                int removed = queue.removeLast();
                System.out.println("  Remove " + removed + " from back (prefix sum " +
                        prefixSum[removed] + " >= " + prefixSum[right] + ")");
            }

            // Check for valid subarrays
            while (!queue.isEmpty() && prefixSum[right] - prefixSum[queue.getFirst()] >= k) {
                int left = queue.getFirst();
                int length = right - left;
                System.out.println("  Found valid subarray: [" + left + ", " + right + ") with length " +
                        length + " and sum " + (prefixSum[right] - prefixSum[left]));
                minLength = Math.min(minLength, length);
                queue.removeFirst();
                System.out.println("  Updated min length: " + minLength);
            }

            // Add current index
            queue.addLast(right);
            System.out.println("  Added " + right + " to queue. Updated queue: " + queue);
        }

        int result = minLength == Integer.MAX_VALUE ? -1 : minLength;
        System.out.println("\nFinal result: " + result);
    }

    /**
     * Main method to run examples
     */
    public static void main(String[] args) {
        System.out.println("===== SHORTEST SUBARRAY WITH SUM AT LEAST K =====\n");

        // Example 1
        int[] nums1 = {1};
        int k1 = 1;
        int result1 = shortestSubarray(nums1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + result1);  // Expected: 1

        // Example 2
        int[] nums2 = {1, 2};
        int k2 = 4;
        int result2 = shortestSubarray(nums2, k2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + result2);  // Expected: -1

        // Example 3
        int[] nums3 = {2, -1, 2};
        int k3 = 3;
        int result3 = shortestSubarray(nums3, k3);
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + result3);  // Expected: 3

        // Detailed demonstration
        System.out.println("\n===== DETAILED DEMONSTRATION =====");
        demonstrateSolution(nums3, k3);
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
