package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_a_TwoSum
 * <p>
 * LeetCode #1: Two Sum
 * <p>
 * Problem:
 * Given an array of integers nums and a target integer value target, find two
 * numbers in the array that add up to the target and return their indices.
 * Each input has exactly one valid answer, and you cannot use the same element twice.
 * You can return the answer in any order.
 * <p>
 * Approach:
 * The solution uses a HashMap to track values we've seen before.
 * For each element nums[i], we:
 * 1. Calculate the complementary value needed (target - nums[i])
 * 2. Check if this complement already exists in our HashMap
 * 3. If yes, we've found our pair and return both indices
 * 4. If no, add the current element to the HashMap for future lookups
 * <p>
 * Time Complexity: O(n) - We make a single pass through the array
 * Space Complexity: O(n) - We potentially store all elements in the HashMap
 */

import java.util.*;

public class _522_a_TwoSum {

    public static void main(String[] args) {
        _522_a_TwoSum solution = new _522_a_TwoSum();

        // Test case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Example 1: [" + result1[0] + ", " + result1[1] + "]");
        // Expected output: [0, 1]

        // Test case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Example 2: [" + result2[0] + ", " + result2[1] + "]");
        // Expected output: [1, 2]

        // Test case 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Example 3: [" + result3[0] + ", " + result3[1] + "]");
        // Expected output: [0, 1]
    }

    public int[] twoSum(int[] nums, int target) {
        // Map to store values we've seen and their indices
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement we need
            int complement = target - nums[i];

            // Check if we've seen this complement before
            if (valToIndex.containsKey(complement)) {
                // Return both indices as an array
                return new int[]{valToIndex.get(complement), i};
            }

            // Store current value and index for future lookups
            valToIndex.put(nums[i], i);
        }

        // In case no solution is found (should not happen per problem constraints)
        return null;
    }
}