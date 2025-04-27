package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._338_algorithmic_decision_making;

import java.util.*;

/**
 * Advantage Shuffle - Tian Ji Horse Racing Strategy
 * <p>
 * Key Concepts:
 * 1. Applying the classic Tian Ji horse racing strategy to maximize advantage
 * 2. Using sorting and priority queue for efficient matching
 * 3. Using a two-pointer technique for optimal assignment
 * <p>
 * This class implements LeetCode 870: Advantage Shuffle
 * Problem: Given two arrays nums1 and nums2, rearrange nums1 to maximize the number
 * of positions where nums1[i] > nums2[i].
 * <p>
 * Historical Background:
 * The problem is inspired by the classical Chinese story of Tian Ji's horse racing,
 * where through clever matching of horses, Tian Ji was able to defeat the King of Qi
 * despite having individually weaker horses.
 * <p>
 * Strategy:
 * - For each of the opponent's strongest horses, use our strongest horse if it can win
 * - If our strongest horse cannot win, use our weakest horse to minimize losses
 */
public class AdvantageShuffle {

    public static void main(String[] args) {
        AdvantageShuffle solution = new AdvantageShuffle();

        // Example 1
        int[] nums1_1 = {12, 24, 8, 32};
        int[] nums2_1 = {13, 25, 32, 11};
        int[] result1 = solution.advantageCount(nums1_1, nums2_1);

        System.out.println("Example 1:");
        System.out.println("nums1 = " + Arrays.toString(nums1_1));
        System.out.println("nums2 = " + Arrays.toString(nums2_1));
        System.out.println("Result = " + Arrays.toString(result1));
        System.out.println("Advantages: " + countAdvantages(result1, nums2_1));

        // Example 2
        int[] nums1_2 = {2, 7, 11, 15};
        int[] nums2_2 = {1, 10, 4, 11};
        int[] result2 = solution.advantageCount(nums1_2, nums2_2);

        System.out.println("\nExample 2:");
        System.out.println("nums1 = " + Arrays.toString(nums1_2));
        System.out.println("nums2 = " + Arrays.toString(nums2_2));
        System.out.println("Result = " + Arrays.toString(result2));
        System.out.println("Advantages: " + countAdvantages(result2, nums2_2));

        // Try the alternative implementation
        int[] result3 = solution.advantageCountAlternative(nums1_1, nums2_1);
        System.out.println("\nAlternative Implementation (Example 1):");
        System.out.println("Result = " + Arrays.toString(result3));
        System.out.println("Advantages: " + countAdvantages(result3, nums2_1));
    }

    /**
     * Helper method to count the number of positions where array1[i] > array2[i]
     */
    private static int countAdvantages(int[] array1, int[] array2) {
        int count = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] > array2[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Rearranges nums1 to maximize the number of positions where nums1[i] > nums2[i]
     * Time Complexity: O(n log n) due to sorting and priority queue operations
     * Space Complexity: O(n) for the priority queue and result array
     *
     * @param nums1 Array to be rearranged
     * @param nums2 Target array for comparison
     * @return Rearranged nums1 with maximum advantage
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Sort nums1 in ascending order
        Arrays.sort(nums1);

        // Use priority queue to process nums2 in descending order while keeping original indices
        // Pair structure: [index, value]
        java.util.PriorityQueue<int[]> maxPQ = new java.util.PriorityQueue<>(
                (pair1, pair2) -> pair2[1] - pair1[1]
        );

        // Add all elements from nums2 with their indices to the priority queue
        for (int i = 0; i < n; i++) {
            maxPQ.offer(new int[]{i, nums2[i]});
        }

        // Initialize pointers for nums1
        int left = 0;         // Points to the smallest element in nums1
        int right = n - 1;    // Points to the largest element in nums1

        // Result array
        int[] result = new int[n];

        // Process nums2 from highest to lowest values
        while (!maxPQ.isEmpty()) {
            int[] pair = maxPQ.poll();
            int idx = pair[0];     // Original index in nums2
            int value = pair[1];   // Value at this index

            // If our strongest remaining horse can beat their current horse
            if (nums1[right] > value) {
                // Use our strongest horse
                result[idx] = nums1[right];
                right--;
            } else {
                // Otherwise, sacrifice our weakest horse
                result[idx] = nums1[left];
                left++;
            }
        }

        return result;
    }

    /**
     * Alternative implementation using deque instead of pointers
     */
    public int[] advantageCountAlternative(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Sort nums1
        Arrays.sort(nums1);

        // Store nums1 in a deque for easy removal from both ends
        java.util.Deque<Integer> deque = new java.util.ArrayDeque<>();
        for (int num : nums1) {
            deque.offer(num);
        }

        // Create pairs [value, index] for nums2 and sort by value
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums2[i], i};
        }
        Arrays.sort(pairs, (a, b) -> b[0] - a[0]);  // Sort in descending order

        // Result array
        int[] result = new int[n];

        // Process nums2 from highest to lowest values
        for (int[] pair : pairs) {
            int value = pair[0];
            int idx = pair[1];

            // If our largest element can beat their current element
            if (deque.peekLast() > value) {
                result[idx] = deque.pollLast();  // Use our largest
            } else {
                result[idx] = deque.pollFirst();  // Use our smallest
            }
        }

        return result;
    }
}