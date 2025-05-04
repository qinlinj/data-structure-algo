package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

import java.util.*;

/**
 * Count Smaller Numbers After Self Using Merge Sort
 * ---------------------------------------------------------
 * <p>
 * This class demonstrates how merge sort can be adapted to solve LeetCode #315:
 * "Count of Smaller Numbers After Self"
 * <p>
 * Key insights:
 * 1. The merge step in merge sort can be modified to count inversions
 * 2. We need to track the original indices of elements to record results correctly
 * 3. When merging two sorted arrays, we can count smaller elements to the right
 * 4. The merge process provides the perfect opportunity to count these elements
 * 5. We need a pair structure to preserve original indices during sorting
 * <p>
 * Problem: Given an array nums, return an array counts where counts[i] is the number
 * of elements to the right of nums[i] that are smaller than nums[i].
 * <p>
 * Time Complexity: O(n log n) - same as merge sort
 * Space Complexity: O(n) - for the temporary arrays and result array
 */
public class _444_c_CountSmallerNumbers {

    // Array to store the result counts
    private static int[] counts;
    // Temporary array for merge sort
    private static Pair[] temp;

    /**
     * Main function to count smaller numbers after self
     *
     * @param nums Input array
     * @return Array where counts[i] is the number of elements to the right of nums[i] that are smaller than nums[i]
     */
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        counts = new int[n];

        // Convert the input array to Pair objects to track original indices
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        // Initialize temporary array for merge sort
        temp = new Pair[n];

        // Perform merge sort on the pairs array
        mergeSort(pairs, 0, n - 1);

        // Convert the result to a List<Integer>
        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }

        return result;
    }

    /**
     * Recursive merge sort implementation
     */
    private static void mergeSort(Pair[] pairs, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        // Sort left and right halves
        mergeSort(pairs, lo, mid);
        mergeSort(pairs, mid + 1, hi);

        // Merge the sorted halves and count smaller elements
        merge(pairs, lo, mid, hi);
    }

    /**
     * Merge two sorted subarrays and count smaller elements on the right
     */
    private static void merge(Pair[] pairs, int lo, int mid, int hi) {
        // Copy the subarray to temporary array
        for (int i = lo; i <= hi; i++) {
            temp[i] = pairs[i];
        }

        int i = lo;      // Pointer for left subarray
        int j = mid + 1; // Pointer for right subarray

        // Merge process
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                // Left subarray is exhausted
                pairs[k] = temp[j++];
            } else if (j > hi) {
                // Right subarray is exhausted
                pairs[k] = temp[i++];

                // Critical: Count smaller elements to the right
                // Since right subarray is exhausted, no elements to count
            } else if (temp[i].val <= temp[j].val) {
                // Current element in left subarray is smaller or equal
                pairs[k] = temp[i];

                // Critical: Before moving the pointer, update the count
                // j - (mid + 1) represents the number of elements in right subarray
                // that are smaller than the current element (they've already been moved)
                counts[temp[i].index] += (j - (mid + 1));
                i++;
            } else {
                // Current element in right subarray is smaller
                pairs[k] = temp[j++];

                // No count update needed here - we only care about smaller elements to the right
            }
        }
    }

    /**
     * Main method to demonstrate the counting algorithm
     */
    public static void main(String[] args) {
        // Example 1: [5,2,6,1]
        int[] nums1 = {5, 2, 6, 1};
        List<Integer> result1 = countSmaller(nums1);

        System.out.println("Input array: [5, 2, 6, 1]");
        System.out.println("Count of smaller numbers after self: " + result1);
        System.out.println("Expected: [2, 1, 1, 0]");

        // Example 2: [1, 5, 4, 2, 7, 3]
        int[] nums2 = {1, 5, 4, 2, 7, 3};
        List<Integer> result2 = countSmaller(nums2);

        System.out.println("\nInput array: [1, 5, 4, 2, 7, 3]");
        System.out.println("Count of smaller numbers after self: " + result2);
        System.out.println("Expected: [0, 3, 2, 0, 1, 0]");

        // Explanation of the algorithm on a small example
        System.out.println("\nDetailed explanation of the algorithm on [5, 2, 6, 1]:");
        explainAlgorithm();
    }

    /**
     * Helper method to explain the algorithm with a step-by-step breakdown
     */
    private static void explainAlgorithm() {
        System.out.println("1. Initial array: [5, 2, 6, 1] with indices [0, 1, 2, 3]");
        System.out.println("2. Divide into single elements: [5], [2], [6], [1]");
        System.out.println("3. Merge [5] and [2] -> [2, 5]:");
        System.out.println("   - When 5 is placed, we've seen 1 smaller element (2) to its right");
        System.out.println("   - counts[0] = 1");

        System.out.println("4. Merge [6] and [1] -> [1, 6]:");
        System.out.println("   - When 6 is placed, we've seen 1 smaller element (1) to its right");
        System.out.println("   - counts[2] = 1");

        System.out.println("5. Merge [2, 5] and [1, 6] -> [1, 2, 5, 6]:");
        System.out.println("   - When 2 is placed, we've seen 1 smaller element (1) to its right");
        System.out.println("   - counts[1] = 1");
        System.out.println("   - When 5 is placed, we've already counted 1 element earlier,");
        System.out.println("     plus 1 more since 2 moved. So counts[0] = 1 + 1 = 2");

        System.out.println("6. Final counts array: [2, 1, 1, 0]");
    }

    /**
     * Pair class to keep track of the original index of each element
     * This allows us to update the count array correctly even as elements move during sorting
     */
    private static class Pair {
        int val;    // The value of the element
        int index;  // The original index in the input array

        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}