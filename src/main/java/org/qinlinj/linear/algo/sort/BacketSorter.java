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
 * Example:
 * For array [2, 3, 6, 1, 34, 11, 53, 6, 22, 12] with 10 buckets:
 * 1. Find max value: 53
 * 2. Calculate bucket range: 53/10 + 1 = 6 (each bucket covers 6 values)
 * 3. Distribute elements:
 * - Bucket 0 (values 0-5): [2, 3, 1]
 * - Bucket 1 (values 6-11): [6, 11, 6]
 * - Bucket 2 (values 12-17): [12]
 * - Bucket 3 (values 18-23): [22]
 * - Bucket 4 (values 24-29): [] (empty)
 * - Bucket 5 (values 30-35): [34]
 * - Bucket 6 (values 36-41): [] (empty)
 * - Bucket 7 (values 42-47): [] (empty)
 * - Bucket 8 (values 48-53): [53]
 * - Bucket 9 (values 54-59): [] (empty)
 * 4. Sort each bucket
 * 5. Merge buckets to get final sorted array
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
        // For our example array [2, 3, 6, 1, 34, 11, 53, 6, 22, 12]
        for (int datum : data) {
            // Calculate which bucket the current element belongs to
            // Examples:
            //   - For value 2: bucketIndex = 2/6 = 0
            //   - For value 34: bucketIndex = 34/6 = 5
            //   - For value 53: bucketIndex = 53/6 = 8
            int bucketIndex = datum / bucketGap;

            // Initialize the bucket if it doesn't exist yet
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<>();
            }

            // Add the element to its corresponding bucket
            buckets[bucketIndex].add(datum);
        }

        // At this point, the buckets contain:
        // Bucket 0: [2, 3, 1] (values 0-5)
        // Bucket 1: [6, 11, 6] (values 6-11)
        // Bucket 2: [12] (values 12-17)
        // Bucket 3: [22] (values 18-23)
        // Bucket 4: [] (empty)
        // Bucket 5: [34] (values 30-35)
        // Bucket 6: [] (empty)
        // Bucket 7: [] (empty)
        // Bucket 8: [53] (values 48-53)
        // Bucket 9: [] (empty)

        // Create a sorter for sorting individual buckets
        IntegerArrayQuickSorter sorter = new IntegerArrayQuickSorter();

        // Sort each bucket individually
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket != null) {
                sorter.sort(bucket);
            }
        }

        // After sorting, the buckets contain:
        // Bucket 0: [1, 2, 3]
        // Bucket 1: [6, 6, 11]
        // Bucket 2: [12]
        // Bucket 3: [22]
        // Bucket 4: [] (empty)
        // Bucket 5: [34]
        // Bucket 6: [] (empty)
        // Bucket 7: [] (empty)
        // Bucket 8: [53]
        // Bucket 9: [] (empty)

        // Index to track the position in the original array
        int curr = 0;

        // Merge all buckets back into the original array
        // This reconstructs the fully sorted array
        for (int i = 0; i < bucketNum; i++) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    // Copy elements from buckets back to original array in order
                    data[curr++] = buckets[i].get(j);
                }
            }
        }

        // Step-by-step merging process:
        // 1. Take all elements from bucket 0: data = [1, 2, 3, ...]
        // 2. Take all elements from bucket 1: data = [1, 2, 3, 6, 6, 11, ...]
        // 3. Take all elements from bucket 2: data = [1, 2, 3, 6, 6, 11, 12, ...]
        // 4. Take all elements from bucket 3: data = [1, 2, 3, 6, 6, 11, 12, 22, ...]
        // 5. Skip bucket 4 (empty)
        // 6. Take all elements from bucket 5: data = [1, 2, 3, 6, 6, 11, 12, 22, 34, ...]
        // 7. Skip buckets 6 and 7 (empty)
        // 8. Take all elements from bucket 8: data = [1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
        // 9. Skip bucket 9 (empty)

        // Final sorted array: [1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
    }
}