package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Bucket Sort Implementation
 * <p>
 * Bucket sort is a distribution sort algorithm that works by distributing elements
 * into a number of buckets, then sorting these buckets individually and finally
 * merging them to produce the sorted array.
 * <p>
 * Time Complexity:
 * - Average case: O(n + k) where n is the number of elements and k is the number of buckets
 * - Worst case: O(n²) when all elements are placed in a single bucket
 * <p>
 * Space Complexity: O(n + k) for storing elements in buckets
 */
public class BacketSorter extends Sorter {

    /**
     * Main method to demonstrate the bucket sort algorithm
     * <p>
     * Example:
     * Input array: [2, 3, 6, 1, 34, 11, 53, 6, 22, 12]
     * After sorting: [1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
     */
    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 6, 1, 34, 11, 53, 6, 22, 12};
        BacketSorter sorter = new BacketSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sorts an array using the bucket sort algorithm
     *
     * @param data The array to be sorted
     */
    public void sort(int[] data) {
        // Edge case: If array is null or has less than 2 elements, it's already sorted
        if (data == null || data.length < 2) {
            return;
        }

        // Initialize the number of buckets
        // This is a parameter that can be adjusted based on the input data characteristics
        int bucketNum = 10;

        // Find the maximum value in the array to determine the bucket range
        // Example: If max = 53, we know all elements are between 0 and 53
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(data[i], max);
        }

        // Calculate the range of values each bucket will hold
        // Example: With max = 53 and bucketNum = 10, bucketGap = 53/10 + 1 = 6
        // This means bucket 0 holds values 0-5, bucket 1 holds 6-11, etc.
        int bucketGap = max / bucketNum + 1;

        // Create an array of buckets where each bucket is an ArrayList
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        // Distribute all elements into their respective buckets
        for (int datum : data) {
            // Calculate which bucket the current element belongs to
            // Example: For value 34, bucketIndex = 34/6 = 5
            int bucketIndex = datum / bucketGap;

            // Initialize the bucket if it doesn't exist yet
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<>();
            }

            // Add the element to its corresponding bucket
            // Example: 34 goes to bucket 5
            buckets[bucketIndex].add(datum);
        }

        // Create a sorter for sorting individual buckets
        IntegerArrayQuickSorter sorter = new IntegerArrayQuickSorter();

        // Sort each bucket individually
        // Example: Bucket 1 might contain [6, 11, 6], which gets sorted to [6, 6, 11]
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket != null) {
                sorter.sort(bucket);
            }
        }

        // Index to track the position in the original array
        int curr = 0;

        // Merge all buckets back into the original array
        // This reconstructs the fully sorted array
        for (int i = 0; i < bucketNum; i++) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    // Copy elements from buckets back to original array in order
                    // Example: data[0] = first element from first non-empty bucket
                    data[curr++] = buckets[i].get(j);
                }
            }
        }
    }
}