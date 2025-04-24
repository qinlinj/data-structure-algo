package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Squares of a Sorted Array (LeetCode 977)
 * ======================================
 * <p>
 * This class implements a solution to the "Squares of a Sorted Array" problem.
 * <p>
 * Problem:
 * Given an integer array sorted in non-decreasing order, return an array of the
 * squares of each number sorted in non-decreasing order.
 * <p>
 * Examples:
 * - [-4,-1,0,3,10] -> [0,1,9,16,100]
 * - [-7,-3,2,3,11] -> [4,9,9,49,121]
 * <p>
 * Approach:
 * The key insight is that squaring can change the order of elements because
 * negative numbers become positive. The largest squares will come from either the
 * most negative or most positive elements of the original array.
 * <p>
 * 1. Use two pointers starting at opposite ends of the array
 * 2. Compare the absolute values of elements at both pointers
 * 3. Square the larger absolute value and place it at the end of the result array
 * 4. Move the appropriate pointer and continue
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the result array
 */
public class _323_e_SortedSquares {
    /**
     * Demonstration of the algorithm.
     */
    public static void main(String[] args) {
        _323_e_SortedSquares solution = new _323_e_SortedSquares();

        // Example 1
        int[] nums1 = {-4, -1, 0, 3, 10};
        System.out.println("Example 1:");
        System.out.print("Input array: ");
        solution.printArray(nums1);

        int[] result1 = solution.sortedSquares(nums1);
        System.out.print("Squared and sorted: ");
        solution.printArray(result1);
        System.out.println();

        // Example 2
        int[] nums2 = {-7, -3, 2, 3, 11};
        System.out.println("Example 2:");
        System.out.print("Input array: ");
        solution.printArray(nums2);

        int[] result2 = solution.sortedSquares(nums2);
        System.out.print("Squared and sorted: ");
        solution.printArray(result2);
        System.out.println();

        // Example with all negative numbers
        int[] nums3 = {-10, -5, -3, -2, -1};
        System.out.println("Example with all negative numbers:");
        System.out.print("Input array: ");
        solution.printArray(nums3);

        int[] result3 = solution.sortedSquares(nums3);
        System.out.print("Squared and sorted: ");
        solution.printArray(result3);
        System.out.println();

        // Example with all positive numbers
        int[] nums4 = {1, 2, 3, 5, 10};
        System.out.println("Example with all positive numbers:");
        System.out.print("Input array: ");
        solution.printArray(nums4);

        int[] result4 = solution.sortedSquares(nums4);
        System.out.print("Squared and sorted: ");
        solution.printArray(result4);
    }

    /**
     * Returns a new array containing the squares of the input array elements,
     * sorted in non-decreasing order.
     *
     * @param nums The input array sorted in non-decreasing order
     * @return A new array of squares sorted in non-decreasing order
     */
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Two pointers approaching from both ends
        int left = 0;
        int right = n - 1;

        // We fill the result array from back to front (largest to smallest)
        for (int i = n - 1; i >= 0; i--) {
            // Compare absolute values to determine which element's square is larger
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[i] = nums[left] * nums[left];
                left++;
            } else {
                result[i] = nums[right] * nums[right];
                right--;
            }
        }

        return result;
    }

    /**
     * Alternative implementation that matches the provided solution exactly.
     */
    public int[] sortedSquaresAlt(int[] nums) {
        int n = nums.length;
        // Two pointers for the elements with potentially largest absolute values
        int i = 0, j = n - 1;
        // Position to place the next largest square (from back to front)
        int p = n - 1;
        int[] res = new int[n];

        // Execute the logic of merging two sorted arrays
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                res[p] = nums[i] * nums[i];
                i++;
            } else {
                res[p] = nums[j] * nums[j];
                j--;
            }
            p--;
        }

        return res;
    }

    /**
     * A naive implementation that squares each element and then sorts.
     * This is O(n log n) due to the sorting step.
     */
    public int[] sortedSquaresNaive(int[] nums) {
        int[] result = new int[nums.length];

        // Square each number
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }

        // Sort the resulting array
        java.util.Arrays.sort(result);

        return result;
    }

    /**
     * Utility method to print an array.
     */
    public void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
