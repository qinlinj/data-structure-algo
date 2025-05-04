package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Merge Sort Algorithm and Binary Tree Perspective
 * ---------------------------------------------------------
 * This class implements the merge sort algorithm and explains its relationship to binary trees.
 * <p>
 * Key points:
 * 1. Merge sort is fundamentally a divide-and-conquer algorithm that can be visualized as a binary tree
 * 2. The algorithm corresponds to post-order traversal of this conceptual binary tree
 * 3. Each node in the tree represents sorting a subarray nums[lo..hi]
 * 4. The merge operation happens at the post-order position of traversal
 * 5. Time complexity is O(n log n) since the tree has log n levels with n elements at each level
 * <p>
 * The implementation includes a visualization-friendly version and an optimized version.
 */
public class _444_a_MergeSortAlgorithm {

    /**
     * Main merge sort function that sorts an array in-place.
     *
     * @param nums The array to be sorted
     * @return The sorted array (same reference as input)
     */
    public static int[] sort(int[] nums) {
        // Create temporary array for merging
        int[] temp = new int[nums.length];
        // Sort the entire array
        sort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    /**
     * Recursive helper method for merge sort.
     * Corresponds to traversing a node in the conceptual binary tree.
     *
     * @param nums The array to be sorted
     * @param lo   The start index of the subarray
     * @param hi   The end index of the subarray
     * @param temp Temporary array for merging
     */
    private static void sort(int[] nums, int lo, int hi, int[] temp) {
        // Base case: a single element is already sorted
        if (lo == hi) {
            return;
        }

        // Calculate mid point safely to avoid overflow
        int mid = lo + (hi - lo) / 2;

        // Recursively sort left subarray - nums[lo..mid]
        // This corresponds to traversing the left child in a binary tree
        sort(nums, lo, mid, temp);

        // Recursively sort right subarray - nums[mid+1..hi]
        // This corresponds to traversing the right child in a binary tree
        sort(nums, mid + 1, hi, temp);

        // POST-ORDER POSITION
        // Merge the two sorted subarrays
        // This happens after both left and right subtrees are processed
        merge(nums, lo, mid, hi, temp);
    }

    /**
     * Merges two sorted subarrays into one sorted array.
     *
     * @param nums The array containing the two sorted subarrays
     * @param lo   The start index of the first subarray
     * @param mid  The end index of the first subarray
     * @param hi   The end index of the second subarray
     * @param temp Temporary array for merging
     */
    private static void merge(int[] nums, int lo, int mid, int hi, int[] temp) {
        // Copy the target range to the temporary array
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // Merge the two sorted subarrays back into nums
        int i = lo;      // Pointer for the left subarray
        int j = mid + 1; // Pointer for the right subarray

        // For each position in the output range
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                // Left subarray is exhausted, take from right
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                // Right subarray is exhausted, take from left
                nums[p] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                // Left element is smaller or equal, take it
                nums[p] = temp[i++];
            } else {
                // Right element is smaller, take it
                nums[p] = temp[j++];
            }
        }
    }

    /**
     * Demonstrates how merge sort can be viewed as post-order traversal
     * of a binary tree. This version prints the execution flow.
     */
    public static void demonstrateMergeSortAsBinaryTree(int[] nums) {
        System.out.println("Demonstrating merge sort as binary tree traversal:");
        int[] temp = new int[nums.length];
        sortWithVisualization(nums, 0, nums.length - 1, temp, 0);
    }

    private static void sortWithVisualization(int[] nums, int lo, int hi, int[] temp, int depth) {
        String indent = "  ".repeat(depth);

        System.out.println(indent + "Sorting subarray[" + lo + ".." + hi + "]");

        if (lo == hi) {
            System.out.println(indent + "Single element, already sorted: " + nums[lo]);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        System.out.println(indent + "Recursively sort left half[" + lo + ".." + mid + "]");
        sortWithVisualization(nums, lo, mid, temp, depth + 1);

        System.out.println(indent + "Recursively sort right half[" + (mid + 1) + ".." + hi + "]");
        sortWithVisualization(nums, mid + 1, hi, temp, depth + 1);

        System.out.println(indent + "POST-ORDER: Merge halves[" + lo + ".." + mid + "] and [" + (mid + 1) + ".." + hi + "]");
        merge(nums, lo, mid, hi, temp);

        System.out.print(indent + "After merging: ");
        for (int i = lo; i <= hi; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    /**
     * Main method with examples
     */
    public static void main(String[] args) {
        // Example 1: Basic sorting
        int[] arr1 = {8, 4, 2, 1, 3, 6, 7, 5};
        System.out.println("Original array:");
        printArray(arr1);

        sort(arr1);

        System.out.println("Sorted array:");
        printArray(arr1);

        System.out.println("\n----------------------------------------\n");

        // Example 2: Visualization as binary tree
        int[] arr2 = {5, 3, 1, 4, 2};
        System.out.println("Original array for visualization:");
        printArray(arr2);

        demonstrateMergeSortAsBinaryTree(arr2);

        System.out.println("\nFinal sorted array:");
        printArray(arr2);

        System.out.println("\n----------------------------------------\n");

        // Example 3: Time complexity demonstration
        System.out.println("Time complexity demonstration:");
        for (int n = 1000; n <= 1000000; n *= 10) {
            int[] largeArray = generateRandomArray(n);
            long startTime = System.nanoTime();
            sort(largeArray);
            long endTime = System.nanoTime();
            double timeInMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("Sorting %,d elements took %.2f ms%n", n, timeInMs);
        }
    }

    // Helper methods

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        return arr;
    }
}