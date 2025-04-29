package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack Variations - Part 1
 * <p>
 * This class implements different variations of the monotonic stack pattern for finding
 * next greater or smaller elements in an array.
 * <p>
 * Key variations covered:
 * 1. Next Greater Element: Find the first element to the right that is greater
 * 2. Next Greater or Equal Element: Find the first element to the right that is greater or equal
 * 3. Next Smaller Element: Find the first element to the right that is smaller
 * 4. Next Smaller or Equal Element: Find the first element to the right that is smaller or equal
 * <p>
 * All these variations follow the same core pattern:
 * - Process the array from right to left
 * - Maintain a monotonic stack
 * - Adjust the comparison operator based on the specific requirement
 * <p>
 * Time Complexity: O(n) for all variations
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _346_a_NextElementVariations {

    /**
     * Main method to demonstrate the implementations
     */
    public static void main(String[] args) {
        _346_a_NextElementVariations solution = new _346_a_NextElementVariations();

        System.out.println("===== NEXT ELEMENT VARIATIONS =====");
        solution.demonstrateNextVariations();

        System.out.println("\n===== COMPARISON OF VARIATIONS =====");
        System.out.println("1. Next Greater Element (>):");
        System.out.println("   - Process from right to left");
        System.out.println("   - Remove elements <= current");
        System.out.println("   - Example: [1,3,2,4,4] -> [3,4,4,-1,-1]");

        System.out.println("\n2. Next Greater or Equal Element (>=):");
        System.out.println("   - Process from right to left");
        System.out.println("   - Remove elements < current");
        System.out.println("   - Example: [1,3,2,4,4] -> [3,4,4,4,-1]");

        System.out.println("\n3. Next Smaller Element (<):");
        System.out.println("   - Process from right to left");
        System.out.println("   - Remove elements >= current");
        System.out.println("   - Example: [8,4,6,6,3] -> [4,3,3,3,-1]");

        System.out.println("\n4. Next Smaller or Equal Element (<=):");
        System.out.println("   - Process from right to left");
        System.out.println("   - Remove elements > current");
        System.out.println("   - Example: [8,4,6,6,3] -> [4,3,6,3,-1]");
    }

    /**
     * 1. Next Greater Element
     * For each element, find the first element to its right that is greater than it
     */
    public int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 2. Next Greater or Equal Element
     * For each element, find the first element to its right that is greater than or equal to it
     */
    public int[] nextGreaterOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements smaller than current
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 3. Next Smaller Element
     * For each element, find the first element to its right that is smaller than it
     */
    public int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements greater than or equal to current
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 4. Next Smaller or Equal Element
     * For each element, find the first element to its right that is smaller than or equal to it
     */
    public int[] nextSmallerOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements greater than current
            while (!stack.isEmpty() && stack.peek() > nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * Demonstrate all next element variations with examples
     */
    public void demonstrateNextVariations() {
        // Example array
        int[] example1 = {1, 3, 2, 4, 4};
        System.out.println("Example array: " + Arrays.toString(example1));

        System.out.println("Next Greater Elements: " +
                Arrays.toString(nextGreaterElement(example1)));
        // Expected: [3, 4, 4, -1, -1]

        System.out.println("Next Greater or Equal Elements: " +
                Arrays.toString(nextGreaterOrEqualElement(example1)));
        // Expected: [3, 4, 4, 4, -1]

        // Example array for smaller element variations
        int[] example2 = {8, 4, 6, 6, 3};
        System.out.println("\nExample array: " + Arrays.toString(example2));

        System.out.println("Next Smaller Elements: " +
                Arrays.toString(nextSmallerElement(example2)));
        // Expected: [4, 3, 3, 3, -1]

        System.out.println("Next Smaller or Equal Elements: " +
                Arrays.toString(nextSmallerOrEqualElement(example2)));
        // Expected: [4, 3, 6, 3, -1]
    }
}
