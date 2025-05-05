package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_b_KSmallestPairs
 * <p>
 * LeetCode #373: Find K Pairs with Smallest Sums
 * <p>
 * This solution demonstrates how to find k pairs with the smallest sums from two sorted arrays.
 * The problem can be viewed as merging multiple sorted lists, similar to Problem #23.
 * <p>
 * Approach:
 * 1. Conceptualize the problem as merging k sorted lists:
 * - Each row represents pairs starting with a specific number from nums1
 * - These pairs form "virtual sorted lists" based on the sum of their elements
 * 2. Use a min-heap to efficiently find the next smallest pair
 * 3. For each removed pair, add the "next" pair in its sequence to the heap
 * <p>
 * Time Complexity: O(k log min(k, m)) where m is the length of nums1
 * Space Complexity: O(min(k, m)) for the heap
 */

import java.util.*;

public class _531_b_KSmallestPairs {

    public static void main(String[] args) {
        _531_b_KSmallestPairs solution = new _531_b_KSmallestPairs();

        // Test case 1
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        List<List<Integer>> result = solution.kSmallestPairs(nums1, nums2, k);

        System.out.println("K Smallest Pairs:");
        for (List<Integer> pair : result) {
            System.out.println(pair.get(0) + ", " + pair.get(1));
        }

        // Test case 2
        int[] nums3 = {1, 1, 2};
        int[] nums4 = {1, 2, 3};
        int k2 = 2;

        List<List<Integer>> result2 = solution.kSmallestPairs(nums3, nums4, k2);

        System.out.println("\nK Smallest Pairs (Test Case 2):");
        for (List<Integer> pair : result2) {
            System.out.println(pair.get(0) + ", " + pair.get(1));
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }

        // Priority queue to store pairs sorted by their sum
        // Each entry is [nums1[i], nums2[j], j] where j is the index in nums2
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        // Initialize with pairs formed by first element of nums2 and each element of nums1
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }

        // Extract k smallest pairs
        while (!minHeap.isEmpty() && result.size() < k) {
            int[] current = minHeap.poll();

            int val1 = current[0];
            int val2 = current[1];
            int index2 = current[2];

            // Add the pair to result
            List<Integer> pair = Arrays.asList(val1, val2);
            result.add(pair);

            // If there are more elements in nums2, add the next pair to the heap
            int nextIndex2 = index2 + 1;
            if (nextIndex2 < nums2.length) {
                minHeap.offer(new int[]{val1, nums2[nextIndex2], nextIndex2});
            }
        }

        return result;
    }
}