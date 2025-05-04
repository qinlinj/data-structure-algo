package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._445_quick_sort_details_and_applications;

import java.util.*;

/**
 * Quick Select Algorithm
 * ---------------------------------------------------------
 * <p>
 * This class implements the Quick Select algorithm, a variant of quicksort
 * that efficiently finds the kth smallest element in an unordered array.
 * <p>
 * Key insights:
 * 1. Quick Select uses the partition scheme from quicksort but recursively
 * processes only one side of the partition
 * 2. After partitioning, we know the exact rank of the pivot element
 * 3. If pivot position equals k, we've found our answer
 * 4. If pivot position > k, search in the left subarray
 * 5. If pivot position < k, search in the right subarray
 * <p>
 * Applications:
 * - Finding the kth smallest/largest element (LeetCode #215)
 * - Computing medians without sorting the entire array
 * - Finding order statistics in linear time
 * <p>
 * Time Complexity: O(n) average case, O(n²) worst case
 * Space Complexity: O(log n) average case for recursion stack
 */
public class _445_d_QuickSelect {

    /**
     * Find the kth largest element in an unsorted array
     * Note: k is 1-based, so k=1 means the largest element
     *
     * @param nums Array to search
     * @param k    Position (1-based) of the element to find
     * @return The kth largest element
     */
    public static int findKthLargest(int[] nums, int k) {
        // Shuffle the array to avoid worst-case performance
        shuffle(nums);

        int n = nums.length;
        // Convert to 0-based index and find n-k smallest (which is kth largest)
        k = n - k;

        int lo = 0;
        int hi = n - 1;

        // Quick Select algorithm
        while (lo <= hi) {
            // Partition the array and get the pivot position
            int pivotPos = partition(nums, lo, hi);

            if (pivotPos < k) {
                // If pivot is less than k, search in right half
                lo = pivotPos + 1;
            } else if (pivotPos > k) {
                // If pivot is greater than k, search in left half
                hi = pivotPos - 1;
            } else {
                // Found the kth element
                return nums[pivotPos];
            }
        }

        // Should never reach here if k is valid
        return -1;
    }

    /**
     * Find the kth smallest element in an unsorted array
     * Note: k is 1-based, so k=1 means the smallest element
     *
     * @param nums Array to search
     * @param k    Position (1-based) of the element to find
     * @return The kth smallest element
     */
    public static int findKthSmallest(int[] nums, int k) {
        // Shuffle the array to avoid worst-case performance
        shuffle(nums);

        // Convert to 0-based index
        k = k - 1;

        int lo = 0;
        int hi = nums.length - 1;

        // Quick Select algorithm
        while (lo <= hi) {
            // Partition the array and get the pivot position
            int pivotPos = partition(nums, lo, hi);

            if (pivotPos < k) {
                // If pivot is less than k, search in right half
                lo = pivotPos + 1;
            } else if (pivotPos > k) {
                // If pivot is greater than k, search in left half
                hi = pivotPos - 1;
            } else {
                // Found the kth element
                return nums[pivotPos];
            }
        }

        // Should never reach here if k is valid
        return -1;
    }

    /**
     * Partition function used in quicksort
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
     * Another implementation of Quick Select using a median-of-medians approach
     * to guarantee O(n) worst-case time complexity
     */
    public static int findKthLargestDeterministic(int[] nums, int k) {
        int n = nums.length;
        // Convert to 0-based index and find n-k smallest (which is kth largest)
        k = n - k;

        return quickSelectDeterministic(nums, 0, n - 1, k);
    }

    /**
     * Deterministic quick select using median-of-medians for pivot selection
     * This guarantees O(n) worst-case time complexity
     */
    private static int quickSelectDeterministic(int[] nums, int lo, int hi, int k) {
        // Base case: only one element
        if (lo == hi) {
            return nums[lo];
        }

        // Choose a good pivot using median-of-medians method
        int pivotIndex = findMedianOfMedians(nums, lo, hi);
        swap(nums, pivotIndex, lo); // Move pivot to first position

        // Partition around pivot
        int pivotPos = partition(nums, lo, hi);

        // Recursive case
        if (pivotPos == k) {
            return nums[pivotPos];
        } else if (pivotPos > k) {
            return quickSelectDeterministic(nums, lo, pivotPos - 1, k);
        } else {
            return quickSelectDeterministic(nums, pivotPos + 1, hi, k);
        }
    }

    /**
     * Find the median of medians to use as a good pivot
     * This is a linear-time approximate median-finding algorithm
     */
    private static int findMedianOfMedians(int[] nums, int lo, int hi) {
        // If the array is small, find median directly
        if (hi - lo < 5) {
            return findMedian(nums, lo, hi);
        }

        // Divide the array into groups of 5 and find median of each group
        int numGroups = (hi - lo) / 5 + 1;
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupStart = lo + i * 5;
            int groupEnd = Math.min(groupStart + 4, hi);
            medians[i] = findMedian(nums, groupStart, groupEnd);
        }

        // Recursively find the median of medians
        return quickSelectDeterministic(medians, 0, numGroups - 1, numGroups / 2);
    }

    /**
     * Find the median of a small array by sorting
     * Works well for small arrays (e.g., size 5)
     */
    private static int findMedian(int[] nums, int lo, int hi) {
        // Simple insertion sort for small arrays
        for (int i = lo + 1; i <= hi; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= lo && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }

        // Return the median position
        return lo + (hi - lo) / 2;
    }

    /**
     * Main method to demonstrate Quick Select
     */
    public static void main(String[] args) {
        // Example 1: Find 3rd largest element
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 3;
        int result1 = findKthLargest(nums1, k1);
        System.out.println("The " + k1 + "rd largest element in [3,2,1,5,6,4] is: " + result1);

        // Example 2: Find 2nd largest element
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 2;
        int result2 = findKthLargest(nums2, k2);
        System.out.println("The " + k2 + "nd largest element in [3,2,3,1,2,4,5,5,6] is: " + result2);

        // Example 3: Find median
        int[] nums3 = {7, 10, 4, 3, 20, 15};
        int k3 = nums3.length / 2 + 1; // Median for odd-length array
        int result3 = findKthSmallest(nums3, k3);
        System.out.println("The median of [7,10,4,3,20,15] is: " + result3);

        // Comparison with sorting approach
        System.out.println("\nComparison with sorting approach:");
        compareWithSorting();

        // Demonstration of partition visualization
        System.out.println("\nStep-by-step visualization of Quick Select:");
        demonstrateQuickSelect();
    }

    /**
     * Compare Quick Select with sorting approach for finding kth element
     */
    private static void compareWithSorting() {
        int size = 10000000; // 10 million elements
        int[] largeArray = new int[size];
        Random rand = new Random();

        // Fill with random integers
        for (int i = 0; i < size; i++) {
            largeArray[i] = rand.nextInt(size);
        }

        // Copy array for both approaches
        int[] arrayCopy1 = Arrays.copyOf(largeArray, size);
        int[] arrayCopy2 = Arrays.copyOf(largeArray, size);

        // Find 1000th largest element using Quick Select
        int k = 1000;

        System.out.println("Finding the " + k + "th largest element in an array of " + size + " elements");

        // Measure time for Quick Select
        long startTime = System.nanoTime();
        int resultQuickSelect = findKthLargest(arrayCopy1, k);
        long endTime = System.nanoTime();
        long quickSelectTime = (endTime - startTime) / 1000000; // Convert to milliseconds

        // Measure time for sorting approach
        startTime = System.nanoTime();
        Arrays.sort(arrayCopy2);
        int resultSorting = arrayCopy2[size - k]; // kth largest after sorting
        endTime = System.nanoTime();
        long sortingTime = (endTime - startTime) / 1000000; // Convert to milliseconds

        // Print results
        System.out.println("Quick Select result: " + resultQuickSelect + ", Time: " + quickSelectTime + " ms");
        System.out.println("Sorting approach result: " + resultSorting + ", Time: " + sortingTime + " ms");
        System.out.println("Speed improvement: " + (sortingTime / (double) quickSelectTime) + "x");
    }

    /**
     * Demonstrate Quick Select algorithm step by step
     */
    private static void demonstrateQuickSelect() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 3; // Find 3rd largest element

        System.out.println("Finding the " + k + "th largest element in array: " + Arrays.toString(nums));

        // Create a copy to visualize the process
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        int targetIndex = numsCopy.length - k; // Convert to 0-based index

        // Visualization
        int lo = 0;
        int hi = numsCopy.length - 1;
        int step = 1;

        while (lo <= hi) {
            System.out.println("\nStep " + step + ": Searching in subarray " + Arrays.toString(Arrays.copyOfRange(numsCopy, lo, hi + 1)));
            System.out.println("Target index: " + targetIndex + " (0-based)");

            int pivotValue = numsCopy[lo];
            System.out.println("Pivot value: " + pivotValue);

            // Make a copy before partitioning for visualization
            int[] beforePartition = Arrays.copyOf(numsCopy, numsCopy.length);
            int pivotPos = partition(numsCopy, lo, hi);

            System.out.println("After partition:");
            System.out.print("[");
            for (int i = 0; i < numsCopy.length; i++) {
                if (i == pivotPos) {
                    System.out.print("*" + numsCopy[i] + "*");
                } else {
                    System.out.print(numsCopy[i]);
                }
                if (i < numsCopy.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println("Pivot position: " + pivotPos);

            // Decision based on pivot position
            if (pivotPos < targetIndex) {
                System.out.println("Pivot position (" + pivotPos + ") < target index (" + targetIndex + ")");
                System.out.println("Search in right half");
                lo = pivotPos + 1;
            } else if (pivotPos > targetIndex) {
                System.out.println("Pivot position (" + pivotPos + ") > target index (" + targetIndex + ")");
                System.out.println("Search in left half");
                hi = pivotPos - 1;
            } else {
                System.out.println("Pivot position (" + pivotPos + ") = target index (" + targetIndex + ")");
                System.out.println("Found the " + k + "th largest element: " + numsCopy[pivotPos]);
                break;
            }

            step++;
        }
    }

    /**
     * Compare the performance of different implementations
     */
    public static void compareImplementations() {
        // Generate large random array
        int size = 10000000;
        int[] largeArray = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            largeArray[i] = rand.nextInt(size);
        }

        int k = 1000; // Find 1000th largest element

        // Copy array for different implementations
        int[] array1 = Arrays.copyOf(largeArray, size);
        int[] array2 = Arrays.copyOf(largeArray, size);
        int[] array3 = Arrays.copyOf(largeArray, size);

        // Measure time for randomized Quick Select
        long startTime = System.nanoTime();
        int result1 = findKthLargest(array1, k);
        long quickSelectTime = (System.nanoTime() - startTime) / 1000000;

        // Measure time for deterministic Quick Select
        startTime = System.nanoTime();
        int result2 = findKthLargestDeterministic(array2, k);
        long deterministicTime = (System.nanoTime() - startTime) / 1000000;

        // Measure time for Priority Queue solution
        startTime = System.nanoTime();
        int result3 = new PriorityQueueSolution().findKthLargest(array3, k);
        long priorityQueueTime = (System.nanoTime() - startTime) / 1000000;

        // Output results
        System.out.println("Randomized Quick Select: " + result1 + ", Time: " + quickSelectTime + " ms");
        System.out.println("Deterministic Quick Select: " + result2 + ", Time: " + deterministicTime + " ms");
        System.out.println("Priority Queue: " + result3 + ", Time: " + priorityQueueTime + " ms");
    }

    /**
     * Solve LeetCode #215: Kth Largest Element in an Array
     * Example of how to use Quick Select in competitive programming
     */
    public static class LeetCode215Solution {
        public int findKthLargest(int[] nums, int k) {
            // Shuffle to avoid worst case
            shuffle(nums);

            int n = nums.length;
            k = n - k; // Convert to 0-based index of kth smallest

            int lo = 0, hi = n - 1;
            while (lo < hi) {
                int j = partition(nums, lo, hi);

                if (j < k) {
                    lo = j + 1;
                } else if (j > k) {
                    hi = j - 1;
                } else {
                    break;
                }
            }

            return nums[k];
        }

        private void shuffle(int[] nums) {
            Random rand = new Random();
            for (int i = 0; i < nums.length; i++) {
                int r = i + rand.nextInt(nums.length - i);
                swap(nums, i, r);
            }
        }

        private int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            int i = lo + 1, j = hi;

            while (true) {
                while (i <= hi && nums[i] < pivot) i++;
                while (j >= lo + 1 && nums[j] > pivot) j--;

                if (i >= j) break;
                swap(nums, i, j);
                i++;
                j--;
            }

            swap(nums, lo, j);
            return j;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * An implementation using the standard library's PriorityQueue (Binary Heap)
     * This approach is O(n log k) which is worse than Quick Select but simpler
     */
    public static class PriorityQueueSolution {
        public int findKthLargest(int[] nums, int k) {
            // Create a min heap of size k
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // Add first k elements to the heap
            for (int i = 0; i < k; i++) {
                pq.offer(nums[i]);
            }

            // For each remaining element, if larger than the smallest in heap,
            // remove smallest and add this element
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }

            // The root of the heap is the kth largest element
            return pq.peek();
        }
    }
}