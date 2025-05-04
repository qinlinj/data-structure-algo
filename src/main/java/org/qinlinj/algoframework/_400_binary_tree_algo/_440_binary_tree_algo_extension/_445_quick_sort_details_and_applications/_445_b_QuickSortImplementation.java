package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._445_quick_sort_details_and_applications;

import java.util.*;

/**
 * Quick Sort Implementation
 * ---------------------------------------------------------
 * <p>
 * This class provides a detailed implementation of the quicksort algorithm
 * with careful attention to boundary conditions and optimizations.
 * <p>
 * Key implementation details:
 * 1. Shuffling the array before sorting to prevent worst-case scenarios
 * 2. Careful handling of partition boundaries to ensure correctness
 * 3. A well-defined partition function that clearly maintains invariants
 * 4. Different partition schemes and their trade-offs
 * 5. Comparison with other sorting algorithms, including stability analysis
 * <p>
 * Time Complexity: O(n log n) average case, O(n²) worst case
 * Space Complexity: O(log n) for recursion stack in average case
 */
public class _445_b_QuickSortImplementation {

    /**
     * Main entry point for quicksort algorithm
     *
     * @param nums The array to be sorted (sorted in place)
     */
    public static void sort(int[] nums) {
        // Shuffle to prevent worst-case performance on already sorted arrays
        shuffle(nums);

        // Call the recursive helper to sort the entire array
        sort(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper method for sorting a subarray
     *
     * @param nums The array being sorted
     * @param lo   The lower bound of current subarray (inclusive)
     * @param hi   The upper bound of current subarray (inclusive)
     */
    private static void sort(int[] nums, int lo, int hi) {
        // Base case: subarray with 0 or 1 element is already sorted
        if (lo >= hi) {
            return;
        }

        // Partition the array and get the pivot's final position
        int p = partition(nums, lo, hi);

        // Sort elements to the left of the pivot
        sort(nums, lo, p - 1);

        // Sort elements to the right of the pivot
        sort(nums, p + 1, hi);
    }

    /**
     * The partition function - the core of quicksort
     * It rearranges elements such that:
     * - Elements less than or equal to pivot are on the left
     * - Elements greater than pivot are on the right
     * <p>
     * Invariants: [lo, i) <= pivot, (j, hi] > pivot
     *
     * @return The final position of the pivot element
     */
    private static int partition(int[] nums, int lo, int hi) {
        // Select the first element as pivot
        int pivot = nums[lo];

        // Initialize pointers for partitioning
        int i = lo + 1; // Right boundary of elements <= pivot
        int j = hi;     // Left boundary of elements > pivot

        // Continue until pointers cross
        while (i <= j) {
            // Find an element on the left that should be on the right
            while (i < hi && nums[i] <= pivot) {
                i++;
            }

            // Find an element on the right that should be on the left
            while (j > lo && nums[j] > pivot) {
                j--;
            }

            // If pointers have crossed, partitioning is complete
            if (i >= j) {
                break;
            }

            // Swap out-of-place elements to maintain invariants
            swap(nums, i, j);
        }

        // Place pivot in its final sorted position
        swap(nums, lo, j);

        // Return pivot's final position
        return j;
    }

    /**
     * Alternative partition scheme - Hoare's original partition
     * Often more efficient than Lomuto partition but slightly more complex
     */
    private static int hoarePartition(int[] nums, int lo, int hi) {
        // Choose pivot (middle element to avoid worst case on sorted arrays)
        int pivotIndex = lo + (hi - lo) / 2;
        int pivot = nums[pivotIndex];

        // Temporary swap pivot with first element
        swap(nums, lo, pivotIndex);

        // Initialize left and right pointers
        int i = lo;
        int j = hi + 1;

        while (true) {
            // Move i right until nums[i] >= pivot
            do {
                i++;
            } while (i <= hi && nums[i] < pivot);

            // Move j left until nums[j] <= pivot
            do {
                j--;
            } while (j >= lo && nums[j] > pivot);

            // If pointers crossed, partitioning is done
            if (i >= j) {
                break;
            }

            // Swap elements that are on the wrong sides
            swap(nums, i, j);
        }

        // Put pivot in its final place
        swap(nums, lo, j);

        return j;
    }

    /**
     * Three-way partitioning for arrays with many duplicates
     * This creates three regions: less than, equal to, and greater than pivot
     */
    private static int[] threeWayPartition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];

        int lt = lo;      // nums[lo..lt-1] < pivot
        int gt = hi;      // nums[gt+1..hi] > pivot
        int i = lo + 1;   // current element to examine

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt++, i++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }

        // Return boundaries of the middle segment containing equal elements
        return new int[]{lt, gt};
    }

    /**
     * Shuffle the array to prevent worst-case scenarios
     * This is critical for ensuring good average-case performance
     */
    private static void shuffle(int[] nums) {
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            // Pick a random index between i and end of array
            int r = i + rand.nextInt(nums.length - i);
            swap(nums, i, r);
        }
    }

    /**
     * Utility method to swap two elements in an array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Main method to demonstrate and compare different quicksort implementations
     */
    public static void main(String[] args) {
        // Test standard implementation
        int[] arr1 = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr1);

        sort(arr1);
        System.out.println("Sorted array (standard quicksort):");
        printArray(arr1);

        // Test with array containing duplicates
        int[] arr2 = {4, 2, 4, 1, 4, 2, 4, 3};
        System.out.println("\nArray with duplicates:");
        printArray(arr2);

        // Use three-way partitioning for duplicates
        threeWayQuickSort(arr2, 0, arr2.length - 1);
        System.out.println("Sorted array (three-way quicksort):");
        printArray(arr2);

        // Test with already sorted array (without shuffle would be worst case)
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("\nAlready sorted array:");
        printArray(arr3);

        sort(arr3);
        System.out.println("After quicksort (with shuffle):");
        printArray(arr3);

        // Performance comparison for larger arrays
        System.out.println("\nPerformance comparison:");
        compareSortingPerformance(10000);
    }

    /**
     * Three-way quicksort for arrays with many duplicates
     */
    private static void threeWayQuickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // Three-way partition
        int[] p = threeWayPartition(nums, lo, hi);

        // Sort the less than segment
        threeWayQuickSort(nums, lo, p[0] - 1);

        // Sort the greater than segment
        threeWayQuickSort(nums, p[1] + 1, hi);
    }

    /**
     * Helper method to print an array
     */
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Compare performance of different quicksort implementations
     */
    private static void compareSortingPerformance(int size) {
        // Generate a large random array
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        int[] sortedArr = new int[size];

        // Fill with the same random numbers
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int val = rand.nextInt(1000000);
            arr1[i] = val;
            arr2[i] = val;
            sortedArr[i] = i; // Already sorted array
        }

        // Test standard quicksort
        long startTime = System.nanoTime();
        sort(arr1);
        long endTime = System.nanoTime();
        System.out.println("Standard quicksort time: " + (endTime - startTime) / 1000000 + " ms");

        // Test quicksort with Hoare partition
        startTime = System.nanoTime();
        // Create a version that uses Hoare partition
        hoareQuickSort(arr2, 0, arr2.length - 1);
        endTime = System.nanoTime();
        System.out.println("Hoare partition quicksort time: " + (endTime - startTime) / 1000000 + " ms");

        // Test on already sorted array (with shuffle)
        int[] sortedCopy = Arrays.copyOf(sortedArr, sortedArr.length);
        startTime = System.nanoTime();
        sort(sortedCopy);
        endTime = System.nanoTime();
        System.out.println("Quicksort on sorted array (with shuffle): " + (endTime - startTime) / 1000000 + " ms");

        // Verify that both arrays are sorted correctly
        boolean isSorted1 = isSorted(arr1);
        boolean isSorted2 = isSorted(arr2);
        System.out.println("First array is sorted: " + isSorted1);
        System.out.println("Second array is sorted: " + isSorted2);
    }

    /**
     * Quicksort using Hoare's partition scheme
     */
    private static void hoareQuickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // Partition and get pivot position
        int p = hoarePartition(nums, lo, hi);

        // Sort subarrays
        hoareQuickSort(nums, lo, p - 1);
        hoareQuickSort(nums, p + 1, hi);
    }

    /**
     * Check if an array is sorted
     */
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}