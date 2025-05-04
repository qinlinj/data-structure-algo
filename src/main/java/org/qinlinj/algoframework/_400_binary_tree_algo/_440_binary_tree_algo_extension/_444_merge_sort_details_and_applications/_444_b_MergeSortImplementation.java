package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Merge Sort Implementation
 * ---------------------------------------------------------
 * <p>
 * This class provides a complete implementation of the merge sort algorithm
 * with detailed explanations of each component.
 * <p>
 * Key implementation details:
 * 1. Pre-allocates the temporary array to avoid repeated memory allocation during recursion
 * 2. Uses the standard divide-and-conquer approach with recursive calls
 * 3. Implements the critical merge function that combines two sorted subarrays
 * 4. Handles edge cases like single-element arrays
 * 5. Uses a defensive mid-point calculation to prevent integer overflow
 * <p>
 * Time Complexity: O(n log n) for all cases
 * Space Complexity: O(n) for the temporary array
 */
public class _444_b_MergeSortImplementation {

    // Temporary array used for merging - allocated once to improve performance
    private static int[] temp;

    /**
     * Public interface for the merge sort algorithm
     *
     * @param nums The array to be sorted (sorted in place)
     */
    public static void sort(int[] nums) {
        // Initialize the temporary array
        temp = new int[nums.length];

        // Call the recursive helper to sort the entire array
        sort(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper method that implements the divide-and-conquer strategy
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

        // Calculate midpoint (this way prevents integer overflow)
        int mid = lo + (hi - lo) / 2;

        // Sort left half recursively
        sort(nums, lo, mid);

        // Sort right half recursively
        sort(nums, mid + 1, hi);

        // Merge the two sorted halves (post-order position)
        merge(nums, lo, mid, hi);
    }

    /**
     * Merges two adjacent sorted subarrays: nums[lo..mid] and nums[mid+1..hi]
     *
     * @param nums The array containing both subarrays
     * @param lo   The start index of the first subarray
     * @param mid  The end index of the first subarray
     * @param hi   The end index of the second subarray
     */
    private static void merge(int[] nums, int lo, int mid, int hi) {
        // Copy the subarray nums[lo..hi] to the temporary array
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // Initialize pointers for the two subarrays
        int leftPtr = lo;         // Points to current element in left subarray
        int rightPtr = mid + 1;   // Points to current element in right subarray

        // Merge the subarrays back into the original array
        for (int currentPos = lo; currentPos <= hi; currentPos++) {
            if (leftPtr > mid) {
                // Left subarray is exhausted, take from right
                nums[currentPos] = temp[rightPtr++];
            } else if (rightPtr > hi) {
                // Right subarray is exhausted, take from left
                nums[currentPos] = temp[leftPtr++];
            } else if (temp[leftPtr] <= temp[rightPtr]) {
                // Current element in left subarray is smaller or equal
                nums[currentPos] = temp[leftPtr++];
            } else {
                // Current element in right subarray is smaller
                nums[currentPos] = temp[rightPtr++];
            }
        }
    }

    /**
     * A more memory-efficient version that reuses the same temporary array section
     * This is an optimization to avoid copying the entire array segment in each merge
     */
    private static void mergeOptimized(int[] nums, int[] temp, int lo, int mid, int hi) {
        // Copy only the relevant part of the array
        int leftSize = mid - lo + 1;
        int rightSize = hi - mid;

        // Copy left subarray to temp[0...leftSize-1]
        for (int i = 0; i < leftSize; i++) {
            temp[i] = nums[lo + i];
        }

        // Copy right subarray to temp[leftSize...leftSize+rightSize-1]
        for (int i = 0; i < rightSize; i++) {
            temp[leftSize + i] = nums[mid + 1 + i];
        }

        // Merge back to original array
        int leftIdx = 0, rightIdx = 0, mergeIdx = lo;

        // Merge until one of the subarrays is exhausted
        while (leftIdx < leftSize && rightIdx < rightSize) {
            if (temp[leftIdx] <= temp[leftSize + rightIdx]) {
                nums[mergeIdx++] = temp[leftIdx++];
            } else {
                nums[mergeIdx++] = temp[leftSize + rightIdx++];
            }
        }

        // Copy remaining elements from left subarray (if any)
        while (leftIdx < leftSize) {
            nums[mergeIdx++] = temp[leftIdx++];
        }

        // Copy remaining elements from right subarray (if any)
        while (rightIdx < rightSize) {
            nums[mergeIdx++] = temp[leftSize + rightIdx++];
        }
    }

    /**
     * An iterative implementation of merge sort (non-recursive)
     * This avoids potential stack overflow for very large arrays
     */
    public static void sortIterative(int[] nums) {
        int n = nums.length;
        int[] tempArray = new int[n];

        // Start with subarrays of size 1, then 2, 4, 8, ...
        for (int size = 1; size < n; size *= 2) {
            // Merge subarrays of size 'size'
            for (int leftStart = 0; leftStart < n - size; leftStart += 2 * size) {
                int mid = leftStart + size - 1;
                int rightEnd = Math.min(leftStart + 2 * size - 1, n - 1);

                // Merge two adjacent subarrays
                mergeOptimized(nums, tempArray, leftStart, mid, rightEnd);
            }
        }
    }

    /**
     * Main method to demonstrate the merge sort implementations
     */
    public static void main(String[] args) {
        // Test the recursive implementation
        int[] arr1 = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr1);

        sort(arr1);
        System.out.println("Sorted array (recursive):");
        printArray(arr1);

        // Test the iterative implementation
        int[] arr2 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nOriginal array:");
        printArray(arr2);

        sortIterative(arr2);
        System.out.println("Sorted array (iterative):");
        printArray(arr2);

        // Performance comparison for larger arrays
        System.out.println("\nPerformance comparison:");
        compareSortingPerformance(10000);
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
     * Compare the performance of recursive and iterative merge sort implementations
     */
    private static void compareSortingPerformance(int size) {
        // Generate a large random array
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];

        // Fill with the same random numbers
        for (int i = 0; i < size; i++) {
            int val = (int) (Math.random() * 1000000);
            arr1[i] = val;
            arr2[i] = val;
        }

        // Test recursive implementation
        long startTime = System.nanoTime();
        sort(arr1);
        long endTime = System.nanoTime();
        System.out.println("Recursive merge sort time: " + (endTime - startTime) / 1000000 + " ms");

        // Test iterative implementation
        startTime = System.nanoTime();
        sortIterative(arr2);
        endTime = System.nanoTime();
        System.out.println("Iterative merge sort time: " + (endTime - startTime) / 1000000 + " ms");

        // Verify that both arrays are sorted the same way
        boolean identical = true;
        for (int i = 0; i < size; i++) {
            if (arr1[i] != arr2[i]) {
                identical = false;
                break;
            }
        }
        System.out.println("Arrays sorted identically: " + identical);
    }
}