package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._345_monotonic_stack_template;

/**
 * Monotonic Stack - Next Greater Element I (LeetCode 496)
 * <p>
 * Problem Description:
 * Given two arrays nums1 and nums2 without duplicates, where nums1's elements are a subset of nums2.
 * For each element in nums1, find the next greater element in nums2.
 * The next greater element is the first element to the right that is greater than the current element.
 * If there is no greater element, return -1.
 * <p>
 * Approach:
 * 1. First use monotonic stack to calculate the next greater element for each element in nums2
 * 2. Build a hash map to store the mapping from element to its next greater element
 * 3. For each element in nums1, look up its next greater element from the map
 * <p>
 * Time Complexity: O(n), where n is the length of nums2
 * Space Complexity: O(n) for the stack and the hash map
 */

import java.util.*;

public class _345_b_NextGreaterElement1 {

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        _345_b_NextGreaterElement1 solution = new _345_b_NextGreaterElement1();

        // Example 1
        int[] nums1_1 = {4, 1, 2};
        int[] nums2_1 = {1, 3, 4, 2};
        int[] result1 = solution.nextGreaterElement(nums1_1, nums2_1);
        System.out.println("Example 1:");
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_1) + ", nums2 = " + Arrays.toString(nums2_1));
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [-1, 3, -1]");

        // Example 2
        int[] nums1_2 = {2, 4};
        int[] nums2_2 = {1, 2, 3, 4};
        int[] result2 = solution.nextGreaterElement(nums1_2, nums2_2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_2) + ", nums2 = " + Arrays.toString(nums2_2));
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [3, -1]");

        // Detailed demonstration
        System.out.println("\n----- DETAILED DEMONSTRATION -----");
        solution.demonstrateSolution(nums1_1, nums2_1);
    }

    /**
     * Finds the next greater element for elements in nums1 based on their position in nums2
     *
     * @param nums1 Array where each element is guaranteed to exist in nums2
     * @param nums2 Array containing all elements of nums1 and possibly more
     * @return Array where result[i] is the next greater element for nums1[i] in nums2
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Calculate next greater elements for nums2
        int[] greater = calculateNextGreaterElement(nums2);

        // Create a map from element value to its next greater element
        HashMap<Integer, Integer> greaterMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            greaterMap.put(nums2[i], greater[i]);
        }

        // Look up the next greater element for each element in nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = greaterMap.get(nums1[i]);
        }

        return result;
    }

    /**
     * Helper method to calculate next greater element for each position
     * (Same as the template function from the base class)
     */
    private int[] calculateNextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return res;
    }

    /**
     * Demonstrates the solution with a step-by-step explanation
     */
    public void demonstrateSolution(int[] nums1, int[] nums2) {
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));

        // Step 1: Calculate next greater elements for nums2
        int[] greater = calculateNextGreaterElement(nums2);
        System.out.println("Next greater elements for nums2: " + Arrays.toString(greater));

        // Step 2: Create the mapping
        HashMap<Integer, Integer> greaterMap = new HashMap<>();
        System.out.println("\nBuilding the mapping from element to its next greater element:");
        for (int i = 0; i < nums2.length; i++) {
            greaterMap.put(nums2[i], greater[i]);
            System.out.println("  " + nums2[i] + " -> " + greater[i]);
        }

        // Step 3: Look up results for nums1
        int[] result = new int[nums1.length];
        System.out.println("\nLooking up next greater elements for nums1:");
        for (int i = 0; i < nums1.length; i++) {
            result[i] = greaterMap.get(nums1[i]);
            System.out.println("  For nums1[" + i + "] = " + nums1[i] + ", the next greater element is " + result[i]);
        }

        System.out.println("\nFinal result: " + Arrays.toString(result));
    }
}
