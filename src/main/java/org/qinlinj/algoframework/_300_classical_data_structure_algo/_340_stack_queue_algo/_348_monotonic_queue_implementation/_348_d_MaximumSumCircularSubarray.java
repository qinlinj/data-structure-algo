package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue Application - Maximum Sum Circular Subarray
 * LeetCode 918: Maximum Sum Circular Subarray
 * <p>
 * Problem Description:
 * Find the maximum possible sum of a non-empty subarray of a circular array.
 * A circular array means the end connects to the beginning, forming a circle.
 * <p>
 * Approach:
 * 1. Simulate a circular array by doubling the original array
 * 2. Calculate prefix sums for the doubled array
 * 3. Use a monotonic queue to track minimum prefix sums within a window of size n
 * 4. For each position, calculate the maximum subarray sum by finding the difference
 * between the current prefix sum and the minimum prefix sum in the window
 * <p>
 * Key insight:
 * - To find the maximum subarray sum, we need to find the minimum prefix sum
 * within a valid window (size <= n) ending at the current position
 * - The constraint that the subarray can't wrap around more than once is handled
 * by limiting the queue size to n
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the prefix sums and monotonic queue
 */

import java.util.*;

public class _348_d_MaximumSumCircularSubarray {

    /**
     * Find the maximum sum of a subarray in a circular array
     *
     * @param nums The input circular array
     * @return The maximum sum of any non-empty subarray
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // Calculate prefix sums for a doubled array to simulate circularity
        // We need 2*n+1 elements (including the initial 0)
        int[] prefixSum = new int[2 * n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[(i - 1) % n];
        }

        // Initialize the maximum sum
        int maxSum = Integer.MIN_VALUE;

        // Use monotonic queue to track minimum prefix sums
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        window.push(0);  // Start with prefixSum[0] = 0

        // Process all positions in the doubled array
        for (int i = 1; i < prefixSum.length; i++) {
            // Calculate max subarray sum ending at position i
            maxSum = Math.max(maxSum, prefixSum[i] - window.min());

            // Maintain window size at most n to ensure we don't wrap more than once
            if (window.size() == n) {
                window.pop();
            }

            // Add current prefix sum
            window.push(prefixSum[i]);
        }

        return maxSum;
    }

    /**
     * Alternative implementation using Kadane's algorithm for non-circular case
     * combined with circular case handling
     */
    public static int maxSubarraySumCircularAlternative(int[] nums) {
        int n = nums.length;

        // Case 1: Maximum subarray sum (standard Kadane's algorithm)
        int maxSum = nums[0];
        int currentMax = nums[0];

        // Case 2: Maximum circular subarray sum
        // To find this, we find the minimum subarray sum and subtract it from the total
        int minSum = nums[0];
        int currentMin = nums[0];

        // Total sum of the array
        int totalSum = nums[0];

        for (int i = 1; i < n; i++) {
            // Update for max sum
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSum = Math.max(maxSum, currentMax);

            // Update for min sum
            currentMin = Math.min(nums[i], currentMin + nums[i]);
            minSum = Math.min(minSum, currentMin);

            // Update total sum
            totalSum += nums[i];
        }

        // Special case: if all elements are negative, the max circular sum is just the max element
        if (totalSum == minSum) {
            return maxSum;
        }

        // Return the maximum of standard max sum and circular max sum
        return Math.max(maxSum, totalSum - minSum);
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public static void demonstrateSolution(int[] nums) {
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("\nStep-by-step demonstration:");

        int n = nums.length;
        int[] prefixSum = new int[2 * n + 1];
        prefixSum[0] = 0;

        // Calculate prefix sums
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[(i - 1) % n];
        }

        System.out.println("Simulated circular array: " +
                Arrays.toString(Arrays.copyOfRange(nums, 0, n)) + " + " +
                Arrays.toString(Arrays.copyOfRange(nums, 0, n)));
        System.out.println("Prefix sums: " + Arrays.toString(prefixSum));

        int maxSum = Integer.MIN_VALUE;
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        window.push(0);

        System.out.println("\nTracking window of minimum prefix sums (max size " + n + "):");

        for (int i = 1; i < prefixSum.length; i++) {
            // Calculate max sum
            int currentSum = prefixSum[i] - window.min();
            String maxUpdate = maxSum < currentSum ? " (new max)" : "";
            maxSum = Math.max(maxSum, currentSum);

            System.out.println("Position " + i + " (value = " + prefixSum[i] +
                    "): Current min = " + window.min() +
                    ", Current sum = " + currentSum + maxUpdate);

            // Maintain window size
            if (window.size() == n) {
                int removed = window.pop();
                System.out.println("  Removing " + removed + " to maintain window size");
            }

            // Add current prefix sum
            window.push(prefixSum[i]);
            System.out.println("  Added " + prefixSum[i] + " to window");
        }

        System.out.println("\nFinal result: " + maxSum);

        // Also demonstrate the alternative solution
        int alternativeResult = maxSubarraySumCircularAlternative(nums);
        System.out.println("\nAlternative solution (Kadane's algorithm) result: " + alternativeResult);
        System.out.println("(Both approaches should yield the same result)");
    }

    /**
     * Main method to run examples
     */
    public static void main(String[] args) {
        System.out.println("===== MAXIMUM SUM CIRCULAR SUBARRAY =====\n");

        // Example 1
        int[] nums1 = {1, -2, 3, -2};
        int result1 = maxSubarraySumCircular(nums1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + result1);  // Expected: 3
        System.out.println("Explanation: Subarray [3] has maximum sum 3");

        // Example 2
        int[] nums2 = {5, -3, 5};
        int result2 = maxSubarraySumCircular(nums2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + result2);  // Expected: 10
        System.out.println("Explanation: Subarray [5,5] (elements from end and beginning) has maximum sum 10");

        // Example 3
        int[] nums3 = {3, -2, 2, -3};
        int result3 = maxSubarraySumCircular(nums3);
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + result3);  // Expected: 3
        System.out.println("Explanation: Subarray [3] has maximum sum 3");

        // Example 4
        int[] nums4 = {-2, -3, -1};
        int result4 = maxSubarraySumCircular(nums4);
        System.out.println("\nExample 4:");
        System.out.println("Input: nums = " + Arrays.toString(nums4));
        System.out.println("Output: " + result4);  // Expected: -1
        System.out.println("Explanation: Subarray [-1] has maximum sum -1 (all elements are negative)");

        // Detailed demonstration
        System.out.println("\n===== DETAILED DEMONSTRATION =====");
        demonstrateSolution(nums2);

        // Compare monotonic queue approach with Kadane's algorithm
        System.out.println("\n===== APPROACH COMPARISON =====");
        System.out.println("1. Monotonic Queue approach:");
        System.out.println("   - Simulates circular array by doubling it");
        System.out.println("   - Uses prefix sums and monotonic queue");
        System.out.println("   - Window size constraint prevents double wrapping");

        System.out.println("\n2. Kadane's Algorithm approach:");
        System.out.println("   - Considers both standard case and circular case");
        System.out.println("   - For circular case, finds minimum subarray and subtracts from total");
        System.out.println("   - Both approaches have O(n) time complexity");
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
