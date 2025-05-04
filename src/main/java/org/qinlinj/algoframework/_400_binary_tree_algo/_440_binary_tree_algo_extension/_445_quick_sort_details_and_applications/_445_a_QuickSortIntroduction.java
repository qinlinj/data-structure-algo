package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._445_quick_sort_details_and_applications;

import java.util.*;

/**
 * Quick Sort Introduction
 * ---------------------------------------------------------
 * <p>
 * This class introduces quicksort from a conceptual perspective, highlighting
 * its connection to binary tree operations.
 * <p>
 * Key insights:
 * 1. Quicksort can be visualized as a binary tree's pre-order traversal
 * 2. The recursive algorithm follows a different paradigm than merge sort:
 * - Merge sort: Sort left half, sort right half, then merge (post-order)
 * - Quicksort: Partition array, then sort left half and right half (pre-order)
 * 3. Each node in the conceptual tree represents a subarray to be partitioned
 * 4. The "partition" operation happens in the pre-order position (before recursion)
 * 5. The partitioning process effectively constructs a binary search tree
 * <p>
 * Time Complexity: O(n log n) average case, O(n²) worst case
 * Space Complexity: O(log n) for recursion stack
 */
public class _445_a_QuickSortIntroduction {

    /**
     * Main method to demonstrate the conceptual framework of quicksort
     */
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        printArray(arr);

        // Visualize the conceptual recursive tree of quicksort
        System.out.println("\nConceptual quicksort tree visualization:");
        visualizeQuickSortTree(arr, 0, arr.length - 1, 0);

        // Perform actual quicksort
        sort(arr);

        System.out.println("\nSorted array:");
        printArray(arr);
    }

    /**
     * The standard quicksort algorithm implementation
     */
    public static void sort(int[] nums) {
        // Shuffle the array to avoid worst-case scenarios
        shuffle(nums);
        // Sort the entire array
        sort(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper method for quicksort
     * Compare this to a binary tree traversal:
     * - partition the array (pre-order position)
     * - sort left subtree
     * - sort right subtree
     */
    private static void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        // PRE-ORDER POSITION: Partition the array and get pivot position
        int pivot = partition(nums, lo, hi);

        // Sort left half - like traversing left subtree
        sort(nums, lo, pivot - 1);

        // Sort right half - like traversing right subtree
        sort(nums, pivot + 1, hi);
    }

    /**
     * Partitions array around a pivot element
     * After this function, elements to the left of pivot are less than pivot,
     * and elements to the right of pivot are greater than pivot
     */
    private static int partition(int[] nums, int lo, int hi) {
        // Choose the first element as pivot
        int pivot = nums[lo];

        // Define regions: [lo, i) <= pivot, (j, hi] > pivot
        int i = lo + 1;
        int j = hi;

        while (i <= j) {
            // Find element on left that should be on right
            while (i < hi && nums[i] <= pivot) {
                i++;
            }

            // Find element on right that should be on left
            while (j > lo && nums[j] > pivot) {
                j--;
            }

            // If pointers crossed, we're done
            if (i >= j) {
                break;
            }

            // Swap out-of-place elements
            swap(nums, i, j);
        }

        // Place pivot in its final position
        swap(nums, lo, j);

        // Return the pivot's final position
        return j;
    }

    /**
     * Shuffle the array to prevent worst-case scenarios
     */
    private static void shuffle(int[] nums) {
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            // Swap with a random position between i and end
            int r = i + rand.nextInt(nums.length - i);
            swap(nums, i, r);
        }
    }

    /**
     * Swap two elements in an array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Helper method to print an array
     */
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
     * Helper method to visualize the conceptual recursive tree of quicksort
     * This is for educational purposes to show how quicksort relates to binary trees
     */
    private static void visualizeQuickSortTree(int[] nums, int lo, int hi, int depth) {
        // Base case
        if (lo >= hi) {
            return;
        }

        // Print indentation based on recursion depth
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        // Print current subarray
        System.out.print("Partition: [");
        for (int i = lo; i <= hi; i++) {
            System.out.print(nums[i]);
            if (i < hi) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Create a copy of the array to visualize partitioning
        int[] copy = Arrays.copyOf(nums, nums.length);
        int pivot = partition(copy, lo, hi);

        // Print the result of partition
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("Pivot: " + copy[pivot] + ", Final position: " + pivot);

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.print("Result: [");
        for (int i = lo; i <= hi; i++) {
            if (i == pivot) {
                System.out.print("*" + copy[i] + "*");
            } else {
                System.out.print(copy[i]);
            }
            if (i < hi) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Recursively visualize left subtree (pre-order traversal)
        visualizeQuickSortTree(copy, lo, pivot - 1, depth + 1);

        // Recursively visualize right subtree
        visualizeQuickSortTree(copy, pivot + 1, hi, depth + 1);
    }
}