package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack Variations - Part 2
 * <p>
 * This class implements different variations of the monotonic stack pattern for finding
 * previous greater or smaller elements in an array.
 * <p>
 * Key variations covered:
 * 1. Previous Greater Element: Find the first element to the left that is greater
 * 2. Previous Greater or Equal Element: Find the first element to the left that is greater or equal
 * 3. Previous Smaller Element: Find the first element to the left that is smaller
 * 4. Previous Smaller or Equal Element: Find the first element to the left that is smaller or equal
 * <p>
 * All these variations follow the same core pattern:
 * - Process the array from left to right
 * - Maintain a monotonic stack
 * - Adjust the comparison operator based on the specific requirement
 * <p>
 * Time Complexity: O(n) for all variations
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _346_b_PreviousElementVariations {

    /**
     * Main method to demonstrate the implementations
     */
    public static void main(String[] args) {
        _346_b_PreviousElementVariations solution = new _346_b_PreviousElementVariations();

        System.out.println("===== PREVIOUS ELEMENT VARIATIONS =====");
        solution.demonstratePreviousVariations();

        System.out.println("\n===== COMPARISON OF VARIATIONS =====");
        System.out.println("1. Previous Greater Element (>):");
        System.out.println("   - Process from left to right");
        System.out.println("   - Remove elements <= current");
        System.out.println("   - Example: [8,7,6,7] -> [-1,8,7,8]");

        System.out.println("\n2. Previous Greater or Equal Element (>=):");
        System.out.println("   - Process from left to right");
        System.out.println("   - Remove elements < current");
        System.out.println("   - Example: [8,7,6,7] -> [-1,8,7,8]");

        System.out.println("\n3. Previous Smaller Element (<):");
        System.out.println("   - Process from left to right");
        System.out.println("   - Remove elements >= current");
        System.out.println("   - Example: [3,6,6,5] -> [-1,3,3,3]");

        System.out.println("\n4. Previous Smaller or Equal Element (<=):");
        System.out.println("   - Process from left to right");
        System.out.println("   - Remove elements > current");
        System.out.println("   - Example: [3,6,6,5] -> [-1,3,6,3]");
    }

    /**
     * 1. Previous Greater Element
     * For each element, find the first element to its left that is greater than it
     */
    public int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
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
     * 2. Previous Greater or Equal Element
     * For each element, find the first element to its left that is greater than or equal to it
     */
    public int[] previousGreaterOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
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
     * 3. Previous Smaller Element
     * For each element, find the first element to its left that is smaller than it
     */
    public int[] previousSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
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
     * 4. Previous Smaller or Equal Element
     * For each element, find the first element to its left that is smaller than or equal to it
     */
    public int[] previousSmallerOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
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
     * Demonstrate all previous element variations with examples
     */
    public void demonstratePreviousVariations() {
        // Example array for greater element variations
        int[] example1 = {8, 7, 6, 7};
        System.out.println("Example array: " + Arrays.toString(example1));

        System.out.println("Previous Greater Elements: " +
                Arrays.toString(previousGreaterElement(example1)));
        // Expected: [-1, 8, 7, 8]

        System.out.println("Previous Greater or Equal Elements: " +
                Arrays.toString(previousGreaterOrEqualElement(example1)));
        // Expected: [-1, 8, 7, 8]

        // Example array for smaller element variations
        int[] example2 = {3, 6, 6, 5};
        System.out.println("\nExample array: " + Arrays.toString(example2));

        System.out.println("Previous Smaller Elements: " +
                Arrays.toString(previousSmallerElement(example2)));
        // Expected: [-1, 3, 3, 3]

        System.out.println("Previous Smaller or Equal Elements: " +
                Arrays.toString(previousSmallerOrEqualElement(example2)));
        // Expected: [-1, 3, 6, 3]
    }
}
