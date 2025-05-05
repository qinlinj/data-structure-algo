package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_l_FindDuplicates
 * <p>
 * LeetCode #442: Find All Duplicates in an Array
 * <p>
 * Problem:
 * Given an array of integers nums where each integer appears once or twice,
 * return an array of all the integers that appear twice. All integers are in
 * the range [1, n] where n is the array length. The solution must use O(n) time
 * and O(1) extra space.
 * <p>
 * Approach 1 (Using array elements as indices):
 * The key insight is that array values are in range [1, n], making them perfect
 * candidates to serve as indices into the array itself.
 * <p>
 * For each element nums[i]:
 * 1. Use its absolute value as an index: idx = Math.abs(nums[i]) - 1
 * 2. Negate the value at nums[idx] to mark that we've seen the number idx+1
 * 3. If nums[idx] is already negative, we've seen this number before (it's a duplicate)
 * <p>
 * Approach 2 (Using additional storage):
 * A simpler approach using a HashSet to track seen numbers, but it uses O(n) extra space.
 * <p>
 * Time Complexity: O(n)
 * Space Complexity: O(1) for Approach 1, O(n) for Approach 2
 */

import java.util.*;

public class _522_l_FindDuplicates {

    public static void main(String[] args) {
        _522_l_FindDuplicates solution = new _522_l_FindDuplicates();

        // Test case 1
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result1 = solution.findDuplicates(nums1);
        System.out.println("Example 1: " + result1);
        // Expected output: [2, 3]

        // Test case 2
        int[] nums2 = {1, 1, 2};
        List<Integer> result2 = solution.findDuplicates(nums2);
        System.out.println("Example 2: " + result2);
        // Expected output: [1]

        // Test case 3
        int[] nums3 = {1};
        List<Integer> result3 = solution.findDuplicates(nums3);
        System.out.println("Example 3: " + result3);
        // Expected output: []
    }

    /**
     * Solution using the array itself for marking (O(1) space)
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // Get the index corresponding to the current value
            int index = Math.abs(nums[i]) - 1;

            // If the value at this index is already negative,
            // we've seen this number before
            if (nums[index] < 0) {
                result.add(index + 1); // Add the duplicate number
            } else {
                // Mark as visited by negating the value
                nums[index] = -nums[index];
            }
        }

        // Optional: Restore the array (if needed)
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = Math.abs(nums[i]);
        // }

        return result;
    }

    /**
     * Solution using a HashSet (O(n) space)
     */
    public List<Integer> findDuplicatesWithSet(int[] nums) {
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {
            // If we've seen this number before, it's a duplicate
            if (seen.contains(num)) {
                result.add(num);
            } else {
                seen.add(num);
            }
        }

        return result;
    }

    /**
     * Alternative solution using counting array (O(n) space)
     */
    public List<Integer> findDuplicatesWithCounting(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        // Array to count occurrences of each number
        int[] count = new int[n + 1];  // +1 because numbers are 1-indexed

        // Count occurrences
        for (int num : nums) {
            count[num]++;

            // If we've seen this number twice, add it to result
            if (count[num] == 2) {
                result.add(num);
            }
        }

        return result;
    }
}