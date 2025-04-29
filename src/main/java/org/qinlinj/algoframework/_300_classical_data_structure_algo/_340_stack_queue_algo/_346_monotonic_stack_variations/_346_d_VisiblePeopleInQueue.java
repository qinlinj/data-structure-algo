package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack - Practice Problem 2
 * Number of Visible People in a Queue (LeetCode 1944)
 * <p>
 * Problem Description:
 * There are n people standing in a queue, with heights given in an array.
 * A person can see another person to their right if everyone in between is shorter than both of them.
 * For each person, return how many people they can see to their right.
 * <p>
 * Approach:
 * 1. Use a monotonic stack to process heights from right to left
 * 2. For each person, count how many people they can see:
 * - First, count all shorter people to their right
 * - If there's a taller person after those shorter people, add 1 more to the count
 * <p>
 * Key insight:
 * This is a "next greater element" problem with a twist - we need to count elements
 * between the current element and its next greater element.
 * <p>
 * Time Complexity: O(n) where n is the number of people
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _346_d_VisiblePeopleInQueue {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        _346_d_VisiblePeopleInQueue solution = new _346_d_VisiblePeopleInQueue();

        System.out.println("===== NUMBER OF VISIBLE PEOPLE IN QUEUE =====");

        // Example 1
        int[] heights1 = {10, 6, 8, 5, 11, 9};
        int[] result1 = solution.canSeePersonsCount(heights1);
        System.out.println("Heights: " + Arrays.toString(heights1));
        System.out.println("Visible People: " + Arrays.toString(result1));
        System.out.println("Expected: [3, 1, 2, 1, 1, 0]");

        // Example 2
        int[] heights2 = {5, 1, 2, 3, 10};
        int[] result2 = solution.canSeePersonsCount(heights2);
        System.out.println("\nHeights: " + Arrays.toString(heights2));
        System.out.println("Visible People: " + Arrays.toString(result2));
        System.out.println("Expected: [4, 1, 1, 1, 0]");

        // Detailed explanation for the first example
        System.out.println("\n===== DETAILED EXPLANATION =====");
        solution.demonstrateSolution(heights1);

        // Explanation of the pattern
        System.out.println("\n===== KEY INSIGHTS =====");
        System.out.println("1. This problem uses a monotonic stack to efficiently count visible people");
        System.out.println("2. For each person, we count:");
        System.out.println("   - All shorter people to their right until a taller person");
        System.out.println("   - The next taller person (if exists)");
        System.out.println("3. We use the stack to track heights in non-increasing order from right to left");
        System.out.println("4. The time complexity is O(n) because each person is pushed and popped at most once");
    }

    /**
     * Calculate how many people each person can see to their right
     *
     * @param heights Array of heights of people in the queue
     * @return Array where result[i] is the number of people the ith person can see
     */
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Count of shorter people that person i can see
            int count = 0;

            // Remove heights that are shorter than or equal to the current height
            // These are the people that person i can see
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                count++;
            }

            // If there's still someone in the stack, they must be taller
            // So the current person can also see them
            result[i] = stack.isEmpty() ? count : count + 1;

            // Push current height to the stack
            stack.push(heights[i]);
        }

        return result;
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public void demonstrateSolution(int[] heights) {
        System.out.println("Heights: " + Arrays.toString(heights));

        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nStep-by-step calculation:");

        for (int i = n - 1; i >= 0; i--) {
            System.out.println("\nPerson " + i + " (height " + heights[i] + "):");
            System.out.println("  Current stack: " + stack);

            int count = 0;

            // Pop shorter people
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                int popped = stack.pop();
                count++;
                System.out.println("  Pop " + popped + " (shorter), count = " + count);
            }

            // Check if there's a taller person
            if (!stack.isEmpty()) {
                count++;
                System.out.println("  Can also see the taller person " + stack.peek() + ", count = " + count);
            } else {
                System.out.println("  No taller people remaining");
            }

            result[i] = count;
            System.out.println("  Result[" + i + "] = " + count);

            // Push current height
            stack.push(heights[i]);
            System.out.println("  Push " + heights[i] + " to stack");
            System.out.println("  Updated stack: " + stack);
        }

        System.out.println("\nFinal result: " + Arrays.toString(result));
    }
}
