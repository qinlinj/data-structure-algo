package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_m_FindDisappearedNumbers
 * <p>
 * LeetCode #448: Find All Numbers Disappeared in an Array
 * <p>
 * Problem:
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 * The solution should run in O(n) time and use O(1) extra space.
 * <p>
 * Approach 1 (Using array elements as indices):
 * Similar to problem #442, we can use the array itself to mark which numbers we've seen:
 * <p>
 * 1. Iterate through the array, and for each value nums[i], mark the element at index
 * nums[i]-1 as negative to indicate we've seen the number nums[i]
 * 2. After marking, iterate again and check which indices have positive values
 * 3. For any index i with a positive value, i+1 is a missing number
 * <p>
 * Approach 2 (Using additional storage):
 * A simpler approach using a boolean array to track seen numbers, but uses O(n) extra space.
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(1) for Approach 1, O(n) for Approach 2
 */

import java.util.*;

public class _522_m_FindDisappearedNumbers {

    public static void main(String[] args) {
        _522_m_FindDisappearedNumbers solution = new _522_m_FindDisappearedNumbers();

        // Test case 1
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result1 = solution.findDisappearedNumbers(nums1);
        System.out.println("Example 1: " + result1);
        // Expected output: [5, 6]

        // Test case 2
        int[] nums2 = {1, 1};
        List<Integer> result2 = solution.findDisappearedNumbers(nums2);
        System.out.println("Example 2: " + result2);
        // Expected output: [2]

        // Test case 3
        int[] nums3 = {1, 2, 3, 4};
        List<Integer> result3 = solution.findDisappearedNumbers(nums3);
        System.out.println("Example 3: " + result3);
        // Expected output: []
    }

    /**
     * Solution using the array itself for marking (O(1) space)
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // Mark seen numbers by making their corresponding index negative
        for (int i = 0; i < nums.length; i++) {
            // Use absolute value since the number may have been marked already
            int index = Math.abs(nums[i]) - 1;

            // Mark as seen by making value at this index negative
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        // Find indices with positive values (those are the missing numbers)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                // i+1 is missing from the array
                result.add(i + 1);
            }
        }

        // Optional: Restore the array (if needed)
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = Math.abs(nums[i]);
        // }

        return result;
    }

    /**
     * Solution using additional array for tracking (O(n) space)
     */
    public List<Integer> findDisappearedNumbersWithExtraSpace(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        // Array to track which numbers we've seen
        boolean[] seen = new boolean[n + 1]; // +1 because numbers are 1-indexed

        // Mark seen numbers
        for (int num : nums) {
            seen[num] = true;
        }

        // Collect missing numbers
        for (int i = 1; i <= n; i++) {
            if (!seen[i]) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Solution using counting array (O(n) space)
     */
    public List<Integer> findDisappearedNumbersWithCounting(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        // Array to count occurrences of each number
        int[] count = new int[n + 1]; // +1 because numbers are 1-indexed

        // Count occurrences
        for (int num : nums) {
            count[num]++;
        }

        // Find numbers that didn't appear
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }
}