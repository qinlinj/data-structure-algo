package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._345_monotonic_stack_template;

/**
 * Monotonic Stack - Daily Temperatures (LeetCode 739)
 * <p>
 * Problem Description:
 * Given an array of daily temperatures, calculate for each day how many days you would need to
 * wait until a warmer temperature. If there is no future day with a warmer temperature, output 0.
 * <p>
 * This problem is a variation of the "next greater element" problem, but instead of returning
 * the value of the next greater element, we need to return the distance (number of days) to it.
 * <p>
 * Approach:
 * 1. Use a monotonic stack to track indices of temperatures (not the temperatures themselves)
 * 2. When we find the next greater temperature, calculate the distance between indices
 * 3. For days that don't have a warmer future temperature, set the result to 0
 * <p>
 * Time Complexity: O(n) where n is the number of days
 * Space Complexity: O(n) for the stack in the worst case
 */

import java.util.*;

public class _345_c_DailyTemperatures {

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        _345_c_DailyTemperatures solution = new _345_c_DailyTemperatures();

        // Example 1
        int[] temperatures1 = {73, 74, 75, 71, 69, 76};
        int[] result1 = solution.dailyTemperatures(temperatures1);
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(temperatures1));
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println("Expected: [1, 1, 3, 2, 1, 0]");

        // Example 2
        int[] temperatures2 = {30, 40, 50, 60};
        int[] result2 = solution.dailyTemperatures(temperatures2);
        System.out.println("\nExample 2:");
        System.out.println("Input: " + Arrays.toString(temperatures2));
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println("Expected: [1, 1, 1, 0]");

        // Example 3
        int[] temperatures3 = {30, 60, 90};
        int[] result3 = solution.dailyTemperatures(temperatures3);
        System.out.println("\nExample 3:");
        System.out.println("Input: " + Arrays.toString(temperatures3));
        System.out.println("Output: " + Arrays.toString(result3));
        System.out.println("Expected: [1, 1, 0]");

        // Detailed demonstration
        System.out.println("\n----- DETAILED DEMONSTRATION -----");
        solution.demonstrateSolution(temperatures1);
    }

    /**
     * For each day, calculate how many days until a warmer temperature
     *
     * @param temperatures Array of daily temperatures
     * @return Array where result[i] is the number of days to wait for a warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        // Stack to store indices of temperatures (not the temperatures themselves)
        Stack<Integer> stack = new Stack<>();

        // Process the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove indices of temperatures cooler than or equal to the current temperature
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }

            // Calculate the distance to the next warmer day (if exists)
            result[i] = stack.isEmpty() ? 0 : (stack.peek() - i);

            // Push current index onto the stack
            stack.push(i);
        }

        return result;
    }

    /**
     * Demonstrates the solution with a step-by-step explanation
     */
    public void demonstrateSolution(int[] temperatures) {
        System.out.println("Temperatures: " + Arrays.toString(temperatures));

        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nProcessing each day from right to left:");

        for (int i = n - 1; i >= 0; i--) {
            System.out.println("\nDay " + i + " with temperature " + temperatures[i]);
            System.out.println("  Current stack (indices): " + stack);

            // Pop cooler or equal temperatures
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                int popped = stack.pop();
                System.out.println("  Pop " + popped + " (temp: " + temperatures[popped] +
                        ") because it's <= current temp " + temperatures[i]);
            }

            // Calculate wait time
            if (stack.isEmpty()) {
                result[i] = 0;
                System.out.println("  No warmer day found, result[" + i + "] = 0");
            } else {
                result[i] = stack.peek() - i;
                System.out.println("  Next warmer day is at index " + stack.peek() +
                        " (temp: " + temperatures[stack.peek()] + ")");
                System.out.println("  Need to wait " + result[i] + " days, result[" + i + "] = " + result[i]);
            }

            // Push current index
            stack.push(i);
            System.out.println("  Push current index " + i + " to stack");
            System.out.println("  Updated stack: " + stack);
        }

        System.out.println("\nFinal result: " + Arrays.toString(result));
    }
}