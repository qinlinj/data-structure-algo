package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._345_monotonic_stack_template;

/**
 * Monotonic Stack - Introduction and Base Implementation
 * <p>
 * A monotonic stack is a specialized stack data structure that maintains elements in either
 * strictly increasing (monotonic increasing) or strictly decreasing (monotonic decreasing) order.
 * <p>
 * Key characteristics:
 * 1. It's essentially a regular stack but with a special property - elements are always in sorted order
 * 2. When pushing new elements, we may need to pop existing elements to maintain the monotonic property
 * 3. Particularly useful for problems involving "next greater element" or "previous smaller element"
 * <p>
 * Common applications:
 * - Finding the next greater/smaller element
 * - Finding the previous greater/smaller element
 * - Solving problems related to temperatures, buildings, etc.
 * <p>
 * Time complexity:
 * Although the implementation appears to have nested loops (for + while), the overall time complexity
 * is actually O(n), because each element is pushed and popped at most once.
 * <p>
 * Space complexity: O(n) for the stack storage
 */

import java.util.*;

public class _345_a_MonotonicStackBase {

    /**
     * Main method to test the monotonic stack implementation
     */
    public static void main(String[] args) {
        _345_a_MonotonicStackBase solution = new _345_a_MonotonicStackBase();

        // Example 1: Basic test case
        int[] nums1 = {2, 1, 2, 4, 3};
        int[] result1 = solution.calculateNextGreaterElement(nums1);
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Next greater elements: " + Arrays.toString(result1));
        // Expected output: [4, 2, 4, -1, -1]

        // Example 2: All increasing elements
        int[] nums2 = {1, 2, 3, 4, 5};
        int[] result2 = solution.calculateNextGreaterElement(nums2);
        System.out.println("\nInput: " + Arrays.toString(nums2));
        System.out.println("Next greater elements: " + Arrays.toString(result2));
        // Expected output: [2, 3, 4, 5, -1]

        // Example 3: All decreasing elements
        int[] nums3 = {5, 4, 3, 2, 1};
        int[] result3 = solution.calculateNextGreaterElement(nums3);
        System.out.println("\nInput: " + Arrays.toString(nums3));
        System.out.println("Next greater elements: " + Arrays.toString(result3));
        // Expected output: [-1, -1, -1, -1, -1]

        // Visual explanation of the algorithm
        System.out.println("\n----- VISUAL EXPLANATION -----");
        solution.visualExplanation();
    }

    /**
     * Template function for finding the next greater element for each element in the array.
     * For each element, it finds the first element to its right that is greater than itself.
     * If no such element exists, it assigns -1.
     *
     * @param nums The input array
     * @return An array where res[i] is the next greater element for nums[i], or -1 if none exists
     */
    public int[] calculateNextGreaterElement(int[] nums) {
        int n = nums.length;
        // Array to store results
        int[] res = new int[n];
        // Stack to maintain potential "next greater elements"
        Stack<Integer> stack = new Stack<>();

        // Process the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements from stack that are smaller than or equal to current element
            // These elements cannot be "next greater element" for future elements
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // The top of stack is the next greater element (if exists)
            res[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element to stack as it might be the next greater element for future positions
            stack.push(nums[i]);
        }

        return res;
    }

    /**
     * Visual explanation of how the algorithm works
     */
    public void visualExplanation() {
        int[] example = {2, 1, 2, 4, 3};
        System.out.println("Example array: " + Arrays.toString(example));
        System.out.println("Finding next greater element for each position:");

        // Array to store stack state at each step
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[example.length];

        for (int i = example.length - 1; i >= 0; i--) {
            System.out.println("\nProcessing index " + i + " with value " + example[i]);
            System.out.println("Current stack: " + stack);

            // Pop elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= example[i]) {
                System.out.println("  Pop " + stack.peek() + " because it's <= " + example[i]);
                stack.pop();
            }

            // Check next greater element
            if (stack.isEmpty()) {
                result[i] = -1;
                System.out.println("  No greater element found, result[" + i + "] = -1");
            } else {
                result[i] = stack.peek();
                System.out.println("  Next greater element is " + stack.peek() + ", result[" + i + "] = " + stack.peek());
            }

            // Push current element
            stack.push(example[i]);
            System.out.println("  Push " + example[i] + " to stack");
            System.out.println("  Updated stack: " + stack);
        }

        System.out.println("\nFinal result: " + Arrays.toString(result));
    }
}
