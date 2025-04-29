package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue Application - Longest Subarray with Limited Absolute Difference
 * LeetCode 1438: Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * <p>
 * Problem Description:
 * Find the length of the longest subarray where the absolute difference between
 * any two elements is less than or equal to a given limit.
 * <p>
 * Approach:
 * 1. Use a sliding window technique to consider all possible subarrays
 * 2. Use a monotonic queue to efficiently track both the maximum and minimum
 * values in the current window
 * 3. When the absolute difference between max and min exceeds the limit,
 * shrink the window from the left
 * 4. Otherwise, expand the window to the right
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * - Each element is processed once (added to and removed from the window)
 * <p>
 * Space Complexity: O(n) for the monotonic queue
 * <p>
 * This problem demonstrates how monotonic queues can be combined with the sliding window
 * technique to efficiently solve problems requiring tracking of window extremes.
 */

import java.util.*;

public class _348_b_LongestSubarrayWithLimitedDiff {

    /**
     * Find the length of the longest subarray with absolute difference <= limit
     *
     * @param nums The input array
     * @param limit The maximum allowed absolute difference
     * @return Length of the longest valid subarray
     */
    public static int longestSubarray(int[] nums, int limit) {
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        int left = 0, right = 0;
        int windowSize = 0;
        int maxLength = 0;

        // Sliding window approach
        while (right < nums.length) {
            // Add element to the window
            window.push(nums[right]);
            right++;
            windowSize++;

            // Shrink window if it violates the limit condition
            while (window.max() - window.min() > limit) {
                window.pop();
                left++;
                windowSize--;
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, windowSize);
        }

        return maxLength;
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public static void demonstrateSolution(int[] nums, int limit) {
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("Limit: " + limit);
        System.out.println("\nSliding window demonstration:");

        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        int left = 0, right = 0;
        int windowSize = 0;
        int maxLength = 0;

        while (right < nums.length) {
            // Add new element
            window.push(nums[right]);
            windowSize++;

            // Print current window
            System.out.print("Adding nums[" + right + "] = " + nums[right] + ". Window: [");
            for (int i = left; i <= right; i++) {
                System.out.print(nums[i]);
                if (i < right) System.out.print(", ");
            }
            System.out.print("]");
            System.out.println(" (max: " + window.max() + ", min: " + window.min() + ")");

            right++;

            // Check if window needs to be shrunk
            while (!window.isEmpty() && window.max() - window.min() > limit) {
                System.out.print("  Diff " + (window.max() - window.min()) + " > " + limit +
                        ". Removing nums[" + left + "] = " + nums[left]);
                window.pop();
                left++;
                windowSize--;
                System.out.print(". New window: [");
                for (int i = left; i < right; i++) {
                    System.out.print(nums[i]);
                    if (i < right - 1) System.out.print(", ");
                }
                System.out.print("]");
                System.out.println(" (max: " + window.max() + ", min: " + window.min() + ")");
            }

            // Update max length
            maxLength = Math.max(maxLength, windowSize);
            System.out.println("  Current window length: " + windowSize + ", max length so far: " + maxLength);
        }

        System.out.println("\nFinal result: " + maxLength);
    }

    /**
     * Main method to run examples
     */
    public static void main(String[] args) {
        System.out.println("===== LONGEST SUBARRAY WITH LIMITED ABSOLUTE DIFFERENCE =====\n");

        // Example 1
        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        int result1 = longestSubarray(nums1, limit1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", limit = " + limit1);
        System.out.println("Output: " + result1);  // Expected: 2
        System.out.println("Explanation: Subarray [2,4] has absolute diff <= 4.");

        // Example 2
        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        int result2 = longestSubarray(nums2, limit2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", limit = " + limit2);
        System.out.println("Output: " + result2);  // Expected: 4
        System.out.println("Explanation: Subarray [2,4,7,2] has absolute diff <= 5.");

        // Detailed demonstration
        System.out.println("\n===== DETAILED DEMONSTRATION =====");
        demonstrateSolution(nums1, limit1);
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