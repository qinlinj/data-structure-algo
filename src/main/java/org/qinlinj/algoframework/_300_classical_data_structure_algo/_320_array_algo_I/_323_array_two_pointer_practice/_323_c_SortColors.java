package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Sort Colors (Dutch National Flag Problem) - LeetCode 75
 * =====================================================
 * <p>
 * This class implements a solution to the "Sort Colors" problem, also known as the
 * Dutch National Flag problem (originally posed by Edsger Dijkstra).
 * <p>
 * Problem:
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red,
 * white, and blue. We use the integers 0, 1, and 2 to represent red, white, and blue.
 * <p>
 * You must solve this problem without using the library's sort function and with
 * a one-pass algorithm using only O(1) extra space.
 * <p>
 * Examples:
 * - [2,0,2,1,1,0] -> [0,0,1,1,2,2]
 * - [2,0,1] -> [0,1,2]
 * <p>
 * Approach:
 * We use a three-pointer technique (also known as the Dutch National Flag algorithm):
 * 1. p0: Boundary for the section containing 0s [0, p0)
 * 2. p: Current element being examined
 * 3. p2: Boundary for the section containing 2s (p2, n-1]
 * <p>
 * As we traverse the array, we:
 * - If nums[p] is 0, swap it to the p0 boundary and increment both p0 and p
 * - If nums[p] is 2, swap it to the p2 boundary and decrement p2 (don't increment p)
 * - If nums[p] is 1, just increment p
 * <p>
 * Time Complexity: O(n) - we process each element once
 * Space Complexity: O(1) - we only use a constant amount of extra space
 */
public class _323_c_SortColors {
    /**
     * Sorts an array containing only 0s, 1s, and 2s in a single pass.
     *
     * @param nums Array to sort, containing only values 0, 1, and 2
     */
    public void sortColors(int[] nums) {
        // Initialize pointers
        // [0, p0) contains all 0s
        // [p0, p) contains all 1s
        // (p2, nums.length-1] contains all 2s
        // [p, p2] is the region to be explored

        int p0 = 0;         // Boundary of 0s
        int p = 0;          // Current element
        int p2 = nums.length - 1;  // Boundary of 2s

        while (p <= p2) {
            if (nums[p] == 0) {
                // If the current element is 0, swap it to the 0s boundary
                swap(nums, p0, p);
                p0++;
                // Since we know the element that was at p0 was either 0 or 1,
                // we can safely increment p
                p++;
            } else if (nums[p] == 2) {
                // If the current element is 2, swap it to the 2s boundary
                swap(nums, p, p2);
                p2--;
                // Don't increment p here, as the element we swapped might be 0,
                // which requires further processing
            } else {
                // If the current element is 1, just move forward
                p++;
            }
        }
    }

    /**
     * Swaps elements at indices i and j in the array.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * A traditional two-pass approach using the counting sort algorithm.
     * This is more straightforward but doesn't meet the one-pass requirement.
     */
    public void sortColorsTwoPass(int[] nums) {
        // Count the number of 0s, 1s, and 2s
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        // Overwrite the array with the correct number of each color
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < count[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    /**
     * Prints an array for visualization.
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
