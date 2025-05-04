package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Count Range Sum Using Merge Sort
 * ---------------------------------------------------------
 * <p>
 * This class demonstrates using merge sort to solve LeetCode #327: "Count of Range Sum"
 * <p>
 * Key insights:
 * 1. We use prefix sums to efficiently calculate the sum of any subarray
 * 2. For a subarray from i to j, its sum equals: preSum[j+1] - preSum[i]
 * 3. For each position i, we want to count j's where:
 * lower <= preSum[j] - preSum[i] <= upper
 * 4. This becomes a two-pointer sliding window approach during the merge step
 * 5. We need to use long to avoid integer overflow when computing large sums
 * <p>
 * Problem: Given an array nums and two integers lower and upper, count the number
 * of subarrays where the sum falls in the range [lower, upper].
 * <p>
 * Time Complexity: O(n log n) - same as merge sort
 * Space Complexity: O(n) - for the prefix sum and temporary arrays
 */
public class _444_e_CountRangeSum {

    // Bounds for the range
    private static int lower;
    private static int upper;
    // Temporary array for merge sort
    private static long[] temp;

    /**
     * Count the number of range sums within [lower, upper]
     *
     * @param nums  Input array
     * @param lower Lower bound of range
     * @param upper Upper bound of range
     * @return Number of subarrays with sum in [lower, upper]
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        _444_e_CountRangeSum.lower = lower;
        _444_e_CountRangeSum.upper = upper;

        int n = nums.length;

        // Construct prefix sum array
        // preSum[i] represents the sum of elements 0 to i-1
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // Initialize temporary array for merge sort
        temp = new long[n + 1];

        // Perform merge sort and count range sums
        return mergeAndCount(preSum, 0, n);
    }

    /**
     * Recursive merge sort with counting
     */
    private static int mergeAndCount(long[] preSum, int start, int end) {
        // Base case: single element
        if (start >= end) {
            return 0;
        }

        // Divide array in half
        int mid = start + (end - start) / 2;

        // Count range sums in left and right subarrays
        int count = mergeAndCount(preSum, start, mid) + mergeAndCount(preSum, mid + 1, end);

        // Count range sums that span the two subarrays
        count += countCrossingRangeSums(preSum, start, mid, end);

        // Merge the sorted subarrays
        merge(preSum, start, mid, end);

        return count;
    }

    /**
     * Count range sums that span across the two subarrays
     * This is the key function that counts subarrays with sum in [lower, upper]
     */
    private static int countCrossingRangeSums(long[] preSum, int start, int mid, int end) {
        int count = 0;

        // For each preSum[i] in the left subarray, find all preSum[j] in the right subarray
        // such that: lower <= preSum[j] - preSum[i] <= upper

        // Use two pointers to maintain a window of valid sums
        int lowerIndex = mid + 1; // Points to the first j where preSum[j] - preSum[i] >= lower
        int upperIndex = mid + 1; // Points to the first j where preSum[j] - preSum[i] > upper

        for (int i = start; i <= mid; i++) {
            // Find the first j where preSum[j] - preSum[i] >= lower
            while (lowerIndex <= end && preSum[lowerIndex] - preSum[i] < lower) {
                lowerIndex++;
            }

            // Find the first j where preSum[j] - preSum[i] > upper
            while (upperIndex <= end && preSum[upperIndex] - preSum[i] <= upper) {
                upperIndex++;
            }

            // The number of valid j's is (upperIndex - lowerIndex)
            count += (upperIndex - lowerIndex);
        }

        return count;
    }

    /**
     * Standard merge function to merge two sorted subarrays
     */
    private static void merge(long[] preSum, int start, int mid, int end) {
        // Copy to temporary array
        for (int i = start; i <= end; i++) {
            temp[i] = preSum[i];
        }

        // Merge process
        int i = start;      // Pointer for left subarray
        int j = mid + 1;    // Pointer for right subarray

        for (int k = start; k <= end; k++) {
            if (i > mid) {
                preSum[k] = temp[j++];
            } else if (j > end) {
                preSum[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                preSum[k] = temp[i++];
            } else {
                preSum[k] = temp[j++];
            }
        }
    }

    /**
     * Main method to demonstrate the algorithm
     */
    public static void main(String[] args) {
        // Example 1: [-2, 5, -1] with range [-2, 2]
        int[] nums1 = {-2, 5, -1};
        int lower1 = -2;
        int upper1 = 2;
        int result1 = countRangeSum(nums1, lower1, upper1);

        System.out.println("Input array: [-2, 5, -1]");
        System.out.println("Range: [" + lower1 + ", " + upper1 + "]");
        System.out.println("Number of subarrays with sum in range: " + result1);
        System.out.println("Expected: 3");
        System.out.println("Explanation: Subarrays: [-2], [-1], [-2, 5, -1]");

        // Example 2: [0] with range [0, 0]
        int[] nums2 = {0};
        int lower2 = 0;
        int upper2 = 0;
        int result2 = countRangeSum(nums2, lower2, upper2);

        System.out.println("\nInput array: [0]");
        System.out.println("Range: [" + lower2 + ", " + upper2 + "]");
        System.out.println("Number of subarrays with sum in range: " + result2);
        System.out.println("Expected: 1");
        System.out.println("Explanation: Subarray: [0]");

        // Detailed explanation
        System.out.println("\nDetailed explanation of the algorithm on [-2, 5, -1]:");
        explainAlgorithm();
    }

    /**
     * Helper method to explain the algorithm with a step-by-step breakdown
     */
    private static void explainAlgorithm() {
        System.out.println("1. Create prefix sum array for [-2, 5, -1]:");
        System.out.println("   - preSum = [0, -2, 3, 2] (sum of elements up to each index)");

        System.out.println("2. Apply merge sort to preSum while counting range sums:");
        System.out.println("   a. Divide [0, -2, 3, 2] into [0, -2] and [3, 2]");
        System.out.println("   b. Further divide [0, -2] into [0] and [-2]");
        System.out.println("   c. Merge [0] and [-2] -> [0, -2] (sorted) and count:");
        System.out.println("      For preSum[0]=0, find j where -2 ≤ preSum[j]-0 ≤ 2");
        System.out.println("      preSum[1]=-2, difference=-2, count++");

        System.out.println("   d. Divide [3, 2] into [3] and [2]");
        System.out.println("   e. Merge [3] and [2] -> [2, 3] (sorted) and count:");
        System.out.println("      For preSum[2]=3, find j where -2 ≤ preSum[j]-3 ≤ 2");
        System.out.println("      preSum[3]=2, difference=-1, count++");

        System.out.println("   f. Merge [0, -2] and [2, 3] -> [-2, 0, 2, 3] and count:");
        System.out.println("      For preSum[0]=0, find j where -2 ≤ preSum[j]-0 ≤ 2");
        System.out.println("      preSum[2]=2, difference=2, count++");
        System.out.println("      preSum[3]=3, difference=3, outside range");

        System.out.println("      For preSum[1]=-2, find j where -2 ≤ preSum[j]-(-2) ≤ 2");
        System.out.println("      preSum[2]=2, difference=4, outside range");
        System.out.println("      preSum[3]=3, difference=5, outside range");

        System.out.println("3. Total count: 3 subarrays with sum in range [-2, 2]");

        System.out.println("\nKey insight:");
        System.out.println("- For a range sum from index i to j, we need: preSum[j+1] - preSum[i]");
        System.out.println("- The merge sort approach lets us efficiently find valid j values");
        System.out.println("- The sliding window technique avoids redundant calculations");
    }
}