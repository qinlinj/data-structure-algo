package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Reverse Pairs Using Merge Sort
 * ---------------------------------------------------------
 * <p>
 * This class demonstrates using merge sort to solve LeetCode #493: "Reverse Pairs"
 * <p>
 * Key insights:
 * 1. Similar to the "Count Smaller" problem, but with a different condition
 * 2. A reverse pair is defined as: i < j and nums[i] > 2 * nums[j]
 * 3. We count these pairs before the merge step, when both subarrays are sorted
 * 4. We use a two-pointer approach to efficiently count pairs without nested loops
 * 5. Long data type is used to prevent overflow when multiplying by 2
 * <p>
 * Problem: Given an array nums, return the number of reverse pairs (i,j) where:
 * - i < j
 * - nums[i] > 2 * nums[j]
 * <p>
 * Time Complexity: O(n log n) - same as merge sort
 * Space Complexity: O(n) - for the temporary array
 */
public class _444_d_ReversePairs {

    // Temporary array for merge sort
    private static int[] temp;
    // Counter for reverse pairs
    private static int count;

    /**
     * Main function to count reverse pairs in an array
     *
     * @param nums Input array
     * @return Number of reverse pairs
     */
    public static int reversePairs(int[] nums) {
        count = 0;
        temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    /**
     * Recursive merge sort implementation
     */
    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        // Sort left and right halves
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        // Count reverse pairs before merging
        countPairs(nums, left, mid, right);

        // Merge the sorted halves
        merge(nums, left, mid, right);
    }

    /**
     * Count reverse pairs between the two sorted subarrays
     * This is the key function that adds the special logic to solve the problem
     */
    private static void countPairs(int[] nums, int left, int mid, int right) {
        // Two pointers approach
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            // Find the position where nums[j] * 2 >= nums[i]
            // All elements after j will also satisfy this condition
            while (j <= right && (long) nums[i] > (long) nums[j] * 2) {
                j++;
            }

            // Add the count of valid pairs
            count += (j - (mid + 1));
        }
    }

    /**
     * Standard merge function to merge two sorted subarrays
     */
    private static void merge(int[] nums, int left, int mid, int right) {
        // Copy to temporary array
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        // Merge process
        int i = left;      // Pointer for left subarray
        int j = mid + 1;   // Pointer for right subarray

        for (int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = temp[j++];
            } else if (j > right) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
            }
        }
    }

    /**
     * Optimized implementation with improved readability
     */
    public static int reversePairsOptimized(int[] nums) {
        return mergeAndCount(nums, 0, nums.length - 1);
    }

    private static int mergeAndCount(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int count = mergeAndCount(nums, start, mid) + mergeAndCount(nums, mid + 1, end);

        // Count reverse pairs
        int j = mid + 1;
        for (int i = start; i <= mid; i++) {
            while (j <= end && (long) nums[i] > (long) nums[j] * 2) {
                j++;
            }
            count += j - (mid + 1);
        }

        // Merge the arrays
        merge(nums, start, mid, end);
        return count;
    }

    /**
     * Main method to demonstrate the algorithm
     */
    public static void main(String[] args) {
        // Example 1: [1,3,2,3,1]
        int[] nums1 = {1, 3, 2, 3, 1};
        int result1 = reversePairs(nums1);
        System.out.println("Input array: [1, 3, 2, 3, 1]");
        System.out.println("Number of reverse pairs: " + result1);
        System.out.println("Expected: 2");
        System.out.println("Explanation: The reverse pairs are (3,1) and (3,1)");

        // Example 2: [2,4,3,5,1]
        int[] nums2 = {2, 4, 3, 5, 1};
        int result2 = reversePairsOptimized(nums2);
        System.out.println("\nInput array: [2, 4, 3, 5, 1]");
        System.out.println("Number of reverse pairs: " + result2);
        System.out.println("Expected: 3");
        System.out.println("Explanation: The reverse pairs are (4,1), (3,1), and (5,1)");

        // Detailed step-by-step explanation
        System.out.println("\nDetailed step-by-step explanation for [1, 3, 2, 3, 1]:");
        explainAlgorithm();
    }

    /**
     * Helper method to explain the algorithm with a step-by-step breakdown
     */
    private static void explainAlgorithm() {
        System.out.println("1. Divide the array [1, 3, 2, 3, 1] into single elements");
        System.out.println("2. Merge [1] and [3] -> [1, 3] (no reverse pairs)");
        System.out.println("3. Merge [2] and [3] -> [2, 3] (no reverse pairs)");
        System.out.println("4. Merge [1, 3] and [2, 3] -> [1, 2, 3, 3]:");
        System.out.println("   - When checking 1 from left subarray: No pairs");
        System.out.println("   - When checking 3 from left subarray: No pairs");
        System.out.println("5. Merge [1, 2, 3, 3] and [1] -> [1, 1, 2, 3, 3]:");
        System.out.println("   - When checking 1 from left subarray: No pairs");
        System.out.println("   - When checking 2 from left subarray: No pairs");
        System.out.println("   - When checking 3 from left subarray: 3 > 2*1, count++");
        System.out.println("   - When checking 3 from left subarray: 3 > 2*1, count++");
        System.out.println("6. Final count: 2 reverse pairs");

        System.out.println("\nKey optimization:");
        System.out.println("- We use a sliding window approach to avoid nested loops");
        System.out.println("- For each element in the left subarray, we don't reset the j pointer");
        System.out.println("- This works because both subarrays are already sorted");
        System.out.println("- If nums[i] > 2*nums[j], then nums[i+1] (which is bigger) also satisfies this");
    }
}