package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search Applications - Finding K Closest Elements
 * <p>
 * Key Concepts:
 * 1. Using binary search to find the position/insertion point of a target element
 * 2. Expanding a window around that position to find k closest elements
 * 3. Combining binary search with two-pointer technique
 * <p>
 * This class implements LeetCode 658: Find K Closest Elements
 * Problem: Given a sorted array, find k closest elements to a target value x.
 * <p>
 * Solution approach:
 * 1. Use binary search to find the position of x or where it would be inserted
 * 2. Use two pointers to expand outward from this position to find the k closest elements
 * 3. Return the k elements in ascending order
 */
public class _336_e_FindKClosestElements {

    public static void main(String[] args) {
        _336_e_FindKClosestElements solution = new _336_e_FindKClosestElements();

        // Example 1
        int[] arr1 = {1, 2, 3, 4, 5};
        int k1 = 4, x1 = 3;

        System.out.println("Example 1:");
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("k = " + k1 + ", x = " + x1);
        System.out.println("Result: " + solution.findClosestElements(arr1, k1, x1)); // Expected: [1, 2, 3, 4]

        // Example 2
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 4, x2 = -1;

        System.out.println("\nExample 2:");
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("k = " + k2 + ", x = " + x2);
        System.out.println("Result: " + solution.findClosestElements(arr2, k2, x2)); // Expected: [1, 2, 3, 4]

        // Example 3
        int[] arr3 = {1, 1, 1, 10, 10, 10};
        int k3 = 1, x3 = 9;

        System.out.println("\nExample 3:");
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("k = " + k3 + ", x = " + x3);
        System.out.println("Result: " + solution.findClosestElements(arr3, k3, x3)); // Expected: [10]
    }

    /**
     * Finds k closest elements to target x in a sorted array
     * Time Complexity: O(log n + k) where n is the length of arr
     * Space Complexity: O(k) for the result list
     *
     * @param arr Sorted array
     * @param k   Number of closest elements to find
     * @param x   Target value
     * @return List of k closest elements in ascending order
     */
    public java.util.List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Edge case: if k equals array length, return the entire array
        if (k >= arr.length) {
            java.util.List<Integer> result = new java.util.ArrayList<>();
            for (int num : arr) {
                result.add(num);
            }
            return result;
        }

        // Find the position of x using binary search
        int position = findPosition(arr, x);

        // Use two pointers to expand around the position
        int left = position - 1;  // Left pointer starts to the left of position
        int right = position;     // Right pointer starts at position

        // Collect k closest elements
        while (right - left - 1 < k) {
            // If left boundary is out of array or right boundary is within array
            // and the right element is closer than the left element
            if (left == -1) {
                // Can only expand to the right
                right++;
            } else if (right == arr.length) {
                // Can only expand to the left
                left--;
            } else if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                // Left element is closer or equidistant (prefer smaller values)
                left--;
            } else {
                // Right element is closer
                right++;
            }
        }

        // Collect the elements between left+1 and right-1
        java.util.List<Integer> result = new java.util.ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    /**
     * Finds the leftmost position where x should be inserted in arr
     * This is a standard binary search for left boundary
     *
     * @param arr Sorted array
     * @param x   Target value
     * @return Position where x should be inserted
     */
    private int findPosition(int[] arr, int x) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Alternative approach using a single binary search to find the left boundary
     * of the k-element window directly
     * Time Complexity: O(log(n-k)) where n is the length of arr
     *
     * @param arr Sorted array
     * @param k   Number of closest elements to find
     * @param x   Target value
     * @return List of k closest elements in ascending order
     */
    public java.util.List<Integer> findClosestElementsOptimized(int[] arr, int k, int x) {
        // Binary search to find the starting position of the k-length window
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare the distance of x from left and right boundaries of the window
            // If x is closer to right boundary, move left
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Collect the k elements starting from the found position
        java.util.List<Integer> result = new java.util.ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
