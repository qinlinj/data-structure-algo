package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

import java.util.*;

/**
 * Merge Sort Advanced Applications and Problems
 * ---------------------------------------------------------
 * This class demonstrates how merge sort can be modified to solve complex problems
 * beyond basic sorting.
 * <p>
 * Key points:
 * 1. Merge sort's divide-and-conquer structure can be adapted to solve various problems
 * 2. The merge function can be modified to count specific patterns during merging
 * 3. Three LeetCode problems are solved by adding custom logic to the merge step:
 * - Count of Smaller Numbers After Self (LeetCode #315)
 * - Reverse Pairs (LeetCode #493)
 * - Count of Range Sum (LeetCode #327)
 * 4. These problems share a common pattern of counting pairs that satisfy a condition
 * 5. The time complexity remains O(n log n) for all solutions
 */
public class _444_b_MergeSortApplications {

    /**
     * Main method with examples
     */
    public static void main(String[] args) {
        // Example 1: Count of Smaller Numbers After Self
        int[] nums1 = {5, 2, 6, 1};
        CountSmallerAfterSelf counter = new CountSmallerAfterSelf();
        List<Integer> result1 = counter.countSmaller(nums1);

        System.out.println("Problem 1: Count of Smaller Numbers After Self");
        System.out.print("Input: ");
        printArray(nums1);
        System.out.println("Output: " + result1);
        System.out.println();

        // Example 2: Reverse Pairs
        int[] nums2 = {1, 3, 2, 3, 1};
        ReversePairs reversePairsCounter = new ReversePairs();
        int result2 = reversePairsCounter.reversePairs(nums2);

        System.out.println("Problem 2: Reverse Pairs");
        System.out.print("Input: ");
        printArray(nums2);
        System.out.println("Output: " + result2);
        System.out.println();

        // Example 3: Count of Range Sum
        int[] nums3 = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        CountRangeSum rangeCounter = new CountRangeSum();
        int result3 = rangeCounter.countRangeSum(nums3, lower, upper);

        System.out.println("Problem 3: Count of Range Sum");
        System.out.print("Input: ");
        printArray(nums3);
        System.out.println("Range: [" + lower + ", " + upper + "]");
        System.out.println("Output: " + result3);

        System.out.println("\n----------------------------------------\n");

        // Demonstration of the common pattern in these problems
        System.out.println("Common Pattern Analysis:");
        System.out.println("1. All problems use a modified merge sort algorithm");
        System.out.println("2. They all count pairs or subarrays that satisfy a condition");
        System.out.println("3. The counting is done efficiently in the merge phase");
        System.out.println("4. Time complexity is maintained at O(n log n)");
        System.out.println("5. Each solution demonstrates how a divide-and-conquer algorithm");
        System.out.println("   can be adapted to solve problems beyond its original purpose");
    }

    // Helper method to print arrays
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Problem 1: Count of Smaller Numbers After Self (LeetCode #315)
     * <p>
     * For each element, count how many elements to its right are smaller than it.
     * <p>
     * Example:
     * Input: [5,2,6,1]
     * Output: [2,1,1,0]
     */
    public static class CountSmallerAfterSelf {
        private Pair[] temp;
        private int[] count;

        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            count = new int[n];
            temp = new Pair[n];
            Pair[] arr = new Pair[n];

            // Store elements with their original indices
            for (int i = 0; i < n; i++) {
                arr[i] = new Pair(nums[i], i);
            }

            // Perform merge sort
            sort(arr, 0, n - 1);

            // Convert result to List
            List<Integer> result = new ArrayList<>();
            for (int c : count) {
                result.add(c);
            }
            return result;
        }

        private void sort(Pair[] arr, int lo, int hi) {
            if (lo == hi) return;

            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        private void merge(Pair[] arr, int lo, int mid, int hi) {
            // Copy to temp array
            for (int i = lo; i <= hi; i++) {
                temp[i] = arr[i];
            }

            // Merge process
            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    // Left half exhausted
                    arr[p] = temp[j++];
                } else if (j == hi + 1) {
                    // Right half exhausted
                    arr[p] = temp[i++];
                    // All elements in right half are larger, so add the count
                    count[arr[p].id] += j - (mid + 1);
                } else if (temp[i].val > temp[j].val) {
                    // Taking from right half
                    arr[p] = temp[j++];
                } else {
                    // Taking from left half
                    arr[p] = temp[i++];
                    // Count smaller elements in right half
                    count[arr[p].id] += j - (mid + 1);
                }
            }
        }

        private class Pair {
            int val, id;

            Pair(int val, int id) {
                // Value of the element
                this.val = val;
                // Original index in the array
                this.id = id;
            }
        }
    }

    /**
     * Problem 2: Reverse Pairs (LeetCode #493)
     * <p>
     * Count pairs (i,j) where i < j and nums[i] > 2*nums[j]
     * <p>
     * Example:
     * Input: [1,3,2,3,1]
     * Output: 2
     */
    public static class ReversePairs {
        private int[] temp;
        private int count = 0;

        public int reversePairs(int[] nums) {
            temp = new int[nums.length];
            sort(nums, 0, nums.length - 1);
            return count;
        }

        private void sort(int[] nums, int lo, int hi) {
            if (lo == hi) return;

            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);

            // Count reverse pairs before merging
            countPairs(nums, lo, mid, hi);

            // Merge the two sorted subarrays
            merge(nums, lo, mid, hi);
        }

        private void countPairs(int[] nums, int lo, int mid, int hi) {
            // Two-pointer approach to count pairs efficiently
            int end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // For each element in left half, find elements in right half
                // that form reverse pairs
                while (end <= hi && (long) nums[i] > (long) nums[end] * 2) {
                    end++;
                }
                count += end - (mid + 1);
            }
        }

        private void merge(int[] nums, int lo, int mid, int hi) {
            // Standard merge process
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }

            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    nums[p] = temp[j++];
                } else if (j == hi + 1) {
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    nums[p] = temp[j++];
                } else {
                    nums[p] = temp[i++];
                }
            }
        }
    }

    /**
     * Problem 3: Count of Range Sum (LeetCode #327)
     * <p>
     * Count subarrays with sum in range [lower, upper]
     * <p>
     * Example:
     * Input: nums = [-2,5,-1], lower = -2, upper = 2
     * Output: 3
     */
    public static class CountRangeSum {
        private long[] temp;
        private int count = 0;
        private int lower, upper;

        public int countRangeSum(int[] nums, int lower, int upper) {
            this.lower = lower;
            this.upper = upper;

            // Compute prefix sums
            long[] prefixSums = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSums[i + 1] = prefixSums[i] + nums[i];
            }

            temp = new long[prefixSums.length];
            sort(prefixSums, 0, prefixSums.length - 1);
            return count;
        }

        private void sort(long[] sums, int lo, int hi) {
            if (lo == hi) return;

            int mid = lo + (hi - lo) / 2;
            sort(sums, lo, mid);
            sort(sums, mid + 1, hi);

            // Count ranges before merging
            countRanges(sums, lo, mid, hi);

            // Merge the sorted subarrays
            merge(sums, lo, mid, hi);
        }

        private void countRanges(long[] sums, int lo, int mid, int hi) {
            // For each prefix sum in left half
            int start = mid + 1, end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // Find range in right half where diff is in [lower, upper]
                while (start <= hi && sums[start] - sums[i] < lower) {
                    start++;
                }
                while (end <= hi && sums[end] - sums[i] <= upper) {
                    end++;
                }
                // Count elements in range
                count += end - start;
            }
        }

        private void merge(long[] sums, int lo, int mid, int hi) {
            // Standard merge process
            for (int i = lo; i <= hi; i++) {
                temp[i] = sums[i];
            }

            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    sums[p] = temp[j++];
                } else if (j == hi + 1) {
                    sums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    sums[p] = temp[j++];
                } else {
                    sums[p] = temp[i++];
                }
            }
        }
    }
}