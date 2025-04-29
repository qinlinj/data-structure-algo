package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._347_monotonic_queue_sliding_window;

/**
 * Monotonic Queue - Sliding Window Maximum (LeetCode 239)
 * <p>
 * Problem Description:
 * Given an array of integers and a window size k, find the maximum value in each
 * sliding window as it moves from left to right through the array.
 * <p>
 * For example:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * <p>
 * Approach:
 * 1. Use a monotonic queue to efficiently track the maximum value in each window
 * 2. The queue maintains elements in decreasing order (largest at the front)
 * 3. As the window slides, add new elements to the queue and remove elements that fall outside
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * - Each element is pushed and popped at most once
 * <p>
 * Space Complexity: O(k) where k is the window size
 * - The monotonic queue will contain at most k elements
 */

import java.util.*;

public class _347_b_SlidingWindowMaximum {

    /**
     * Find the maximum value in each sliding window
     *
     * @param nums The input array
     * @param k The window size
     * @return Array of maximum values for each window position
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // Fill the first window (k-1 elements)
                window.push(nums[i]);
            } else {
                // Window is now sliding

                // Add current element to the window
                window.push(nums[i]);

                // Get maximum for current window
                result.add(window.max());

                // Remove the leftmost element as window slides
                window.pop(nums[i - k + 1]);
            }
        }

        // Convert list to array
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    /**
     * Demonstrates the sliding window algorithm with visualization
     */
    public static void demonstrateSlidingWindow(int[] nums, int k) {
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("Window size: " + k);
        System.out.println("\nSliding window visualization:");

        MonotonicQueue window = new MonotonicQueue();

        for (int i = 0; i < nums.length; i++) {
            StringBuilder windowView = new StringBuilder();
            int start = Math.max(0, i - k + 1);

            // Visualize the current window
            for (int j = 0; j < nums.length; j++) {
                if (j < start || j > i) {
                    windowView.append("   ");
                } else {
                    windowView.append("[").append(nums[j]).append("]");
                }
            }

            if (i < k - 1) {
                // Still filling the first window
                window.push(nums[i]);
                System.out.println(windowView + " -> Building initial window");
            } else {
                // Window is now sliding
                window.push(nums[i]);
                int max = window.max();
                System.out.println(windowView + " -> Maximum: " + max);
                window.pop(nums[i - k + 1]);
            }
        }

        // Show the result
        int[] result = maxSlidingWindow(nums, k);
        System.out.println("\nResult: " + Arrays.toString(result));
    }

    /**
     * Main method to run the example
     */
    public static void main(String[] args) {
        System.out.println("===== SLIDING WINDOW MAXIMUM =====\n");

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        demonstrateSlidingWindow(nums, k);

        // Additional test cases
        System.out.println("\n===== ADDITIONAL EXAMPLES =====");

        int[] nums2 = {9, 10, 9, -7, -4, -8, 2, -6};
        int k2 = 5;
        int[] result2 = maxSlidingWindow(nums2, k2);
        System.out.println("\nInput: " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(result2));

        int[] nums3 = {1, -1};
        int k3 = 1;
        int[] result3 = maxSlidingWindow(nums3, k3);
        System.out.println("\nInput: " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(result3));
    }

    /**
     * Implementation of a monotonic queue for finding maximum values
     */
    static class MonotonicQueue {
        private LinkedList<Integer> maxq = new LinkedList<>();

        /**
         * Add an element to the queue while maintaining monotonic decreasing order
         */
        public void push(int n) {
            // Remove all elements smaller than n from the back
            while (!maxq.isEmpty() && maxq.getLast() < n) {
                maxq.pollLast();
            }
            // Add the new element to the back
            maxq.addLast(n);
        }

        /**
         * Get the maximum element in the queue
         */
        public int max() {
            return maxq.getFirst();
        }

        /**
         * Remove an element from the front of the queue if it matches the given value
         */
        public void pop(int n) {
            if (n == maxq.getFirst()) {
                maxq.pollFirst();
            }
        }
    }
}
