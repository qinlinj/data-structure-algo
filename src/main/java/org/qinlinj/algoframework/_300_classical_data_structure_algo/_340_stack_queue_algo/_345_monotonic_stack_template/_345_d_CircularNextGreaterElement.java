package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._345_monotonic_stack_template;

/**
 * Monotonic Stack - Next Greater Element II (LeetCode 503)
 * <p>
 * Problem Description:
 * Given a circular array, find the next greater element for each element.
 * A circular array means the end of the array connects to the beginning.
 * If there is no greater element, output -1.
 * <p>
 * Key challenge:
 * In a circular array, elements at the end can find their next greater elements
 * at the beginning of the array, requiring special handling.
 * <p>
 * Approach:
 * 1. Simulate doubling the array by processing elements twice
 * 2. Use modulo operation (i % n) to handle the circular property
 * 3. Apply the monotonic stack template with adjustment for the circular property
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _345_d_CircularNextGreaterElement {

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        _345_d_CircularNextGreaterElement solution = new _345_d_CircularNextGreaterElement();

        // Example 1
        int[] nums1 = {2, 1, 2, 4, 3};
        int[] result1 = solution.nextGreaterElements(nums1);
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [4, 2, 4, -1, 4]");

        // Example 2
        int[] nums2 = {1, 2, 1};
        int[] result2 = solution.nextGreaterElements(nums2);
        System.out.println("\nExample 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [2, -1, 2]");

        // Alternative solution
        int[] altResult1 = solution.nextGreaterElementsAlternative(nums1);
        System.out.println("\nAlternative solution for Example 1:");
        System.out.println("Output: " + Arrays.toString(altResult1));

        // Detailed demonstration
        System.out.println("\n----- DETAILED DEMONSTRATION -----");
        solution.demonstrateSolution(nums1);
    }

    /**
     * Find the next greater element for each position in a circular array
     *
     * @param nums The input circular array
     * @return Array where result[i] is the next greater element for nums[i], or -1 if none exists
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process the array twice (simulating a circular array)
        // By starting from 2*n-1 and going down to 0
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Get actual index using modulo
            int idx = i % n;

            // Remove elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= nums[idx]) {
                stack.pop();
            }

            // Assign result only for the first n positions (prevent overwriting)
            if (i < n) {
                result[idx] = stack.isEmpty() ? -1 : stack.peek();
            }

            // Push current element
            stack.push(nums[idx]);
        }

        return result;
    }

    /**
     * Demonstrates the solution with a step-by-step explanation
     */
    public void demonstrateSolution(int[] nums) {
        System.out.println("Circular Array: " + Arrays.toString(nums));

        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nSimulating processing the array twice (from right to left):");

        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            System.out.println("\nVirtual position " + i + " (actual index " + idx +
                    " with value " + nums[idx] + ")");
            System.out.println("  Current stack: " + stack);

            // Pop smaller or equal elements
            while (!stack.isEmpty() && stack.peek() <= nums[idx]) {
                System.out.println("  Pop " + stack.peek() + " because it's <= " + nums[idx]);
                stack.pop();
            }

            // Only set result for the first pass
            if (i < n) {
                if (stack.isEmpty()) {
                    result[idx] = -1;
                    System.out.println("  No greater element found, result[" + idx + "] = -1");
                } else {
                    result[idx] = stack.peek();
                    System.out.println("  Next greater element is " + stack.peek() +
                            ", result[" + idx + "] = " + stack.peek());
                }
            } else {
                System.out.println("  Second pass - not updating result");
            }

            // Push current element
            stack.push(nums[idx]);
            System.out.println("  Push " + nums[idx] + " to stack");
            System.out.println("  Updated stack: " + stack);
        }

        System.out.println("\nFinal result: " + Arrays.toString(result));
    }

    /**
     * Alternative solution that actually duplicates the array for clarity
     * (Less efficient but easier to understand)
     */
    public int[] nextGreaterElementsAlternative(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize all to -1

        // Create a doubled array to simulate circular property
        int[] doubled = new int[2 * n];
        for (int i = 0; i < n; i++) {
            doubled[i] = nums[i];
            doubled[i + n] = nums[i];
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= doubled[i]) {
                stack.pop();
            }

            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(doubled[i]);
        }

        return result;
    }
}
