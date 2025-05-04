package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Merge Sort Introduction
 * ---------------------------------------------------------
 * <p>
 * This class introduces merge sort from a conceptual perspective, highlighting
 * its connection to binary tree operations.
 * <p>
 * Key insights:
 * 1. Merge sort can be visualized as a binary tree's post-order traversal
 * 2. The recursive algorithm follows the divide-and-conquer paradigm
 * 3. Each node in the conceptual tree represents a subarray to be sorted
 * 4. The "merge" operation happens in the post-order position (after both subtrees are processed)
 * 5. Time complexity is O(n log n) - derived from tree height (log n) and work at each level (n)
 * <p>
 * This recursive framework is similar to many binary tree algorithms,
 * making merge sort an elegant application of tree-based thinking.
 */
public class _444_a_MergeSortIntroduction {

    /**
     * Main method to demonstrate the conceptual framework of merge sort
     */
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        printArray(arr);

        // Visualize the conceptual recursive tree of merge sort
        System.out.println("\nConceptual merge sort tree visualization:");
        visualizeMergeSortTree(arr, 0, arr.length - 1, 0);

        // Perform actual merge sort
        sort(arr);

        System.out.println("\nSorted array:");
        printArray(arr);
    }

    /**
     * The standard merge sort algorithm implementation
     */
    public static void sort(int[] nums) {
        // Create temporary array once to avoid repeated memory allocation
        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
    }

    /**
     * Recursive helper method for merge sort
     * Compare this to a binary tree traversal:
     * - sort left subtree
     * - sort right subtree
     * - merge results (post-order position)
     */
    private static void sort(int[] nums, int lo, int hi, int[] temp) {
        if (lo >= hi) {
            return;
        }

        // Calculate middle point (avoiding overflow)
        int mid = lo + (hi - lo) / 2;

        // Sort left half - like traversing left subtree
        sort(nums, lo, mid, temp);

        // Sort right half - like traversing right subtree
        sort(nums, mid + 1, hi, temp);

        // POST-ORDER POSITION: Merge the sorted halves
        merge(nums, lo, mid, hi, temp);
    }

    /**
     * Merge two sorted subarrays into one
     */
    private static void merge(int[] nums, int lo, int mid, int hi, int[] temp) {
        // Copy elements to temporary array
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // Merge process using two pointers
        int i = lo;     // pointer for left subarray
        int j = mid + 1; // pointer for right subarray

        // Fill the original array with sorted elements
        for (int p = lo; p <= hi; p++) {
            if (i > mid) {
                // Left subarray exhausted
                nums[p] = temp[j++];
            } else if (j > hi) {
                // Right subarray exhausted
                nums[p] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                // Element in left subarray is smaller
                nums[p] = temp[i++];
            } else {
                // Element in right subarray is smaller
                nums[p] = temp[j++];
            }
        }
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
     * Helper method to visualize the conceptual recursive tree of merge sort
     * This is for educational purposes to show how merge sort relates to binary trees
     */
    private static void visualizeMergeSortTree(int[] nums, int lo, int hi, int depth) {
        // Print indentation based on recursion depth
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        // Print current subarray
        System.out.print("Subarray [");
        for (int i = lo; i <= hi; i++) {
            System.out.print(nums[i]);
            if (i < hi) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Base case
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        // Recursively visualize left subtree
        visualizeMergeSortTree(nums, lo, mid, depth + 1);

        // Recursively visualize right subtree
        visualizeMergeSortTree(nums, mid + 1, hi, depth + 1);

        // Post-order position (after both subtrees)
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println("Merge the above two subarrays");
    }
}