package org.qinlinj.algoframework._500_data_structure_design._560_application_design._566_advanced_array_deduplication; /**
 * MONOTONIC STACK PATTERN EXPLAINED
 * <p>
 * This class explains the monotonic stack pattern that is central to solving
 * the "Remove Duplicate Letters" problem and many other algorithmic challenges.
 * <p>
 * What is a Monotonic Stack?
 * A monotonic stack is a stack whose elements are always in sorted order (either increasing or decreasing).
 * As new elements are processed, we remove elements from the stack to maintain this property.
 * <p>
 * Types of Monotonic Stacks:
 * 1. Monotonic Increasing Stack: Elements in the stack are in increasing order from bottom to top
 * 2. Monotonic Decreasing Stack: Elements in the stack are in decreasing order from bottom to top
 * <p>
 * In the context of the "Remove Duplicate Letters" problem:
 * - We use a monotonic increasing stack to ensure lexicographically smallest result
 * - When we encounter a smaller character, we try to pop larger characters before it
 * - The additional constraint is that we only pop if the character appears again later
 * <p>
 * Common Use Cases for Monotonic Stacks:
 * 1. Finding next greater/smaller element
 * 2. Finding previous greater/smaller element
 * 3. Problems involving optimal selection with ordering constraints
 * 4. Problems where we need to maintain a specific order (like lexicographical order)
 */

import java.util.*;

public class _566_e_MonotonicStackPattern {

    /**
     * Example 1: Using a monotonic increasing stack to find the next greater element
     * For each element, find the first element to its right that is greater than it
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default if no greater element exists

        // Monotonic decreasing stack (stores indices)
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While current element is greater than stack top element
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                // We found the next greater element for the stack top
                int idx = stack.pop();
                result[idx] = nums[i];
            }

            // Push current index
            stack.push(i);
        }

        return result;
    }

    /**
     * Example 2: Using a monotonic stack to implement the "Remove Duplicate Letters" logic
     * This is a simplified version to highlight just the monotonic stack aspect
     */
    public static String demonstrateMonotonicStackForLexOrder(String s) {
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26]; // for lowercase letters

        for (char c : s.toCharArray()) {
            // Skip duplicates
            if (inStack[c - 'a']) continue;

            // Maintain monotonic increasing stack
            while (!stack.isEmpty() && stack.peek() > c) {
                // In the real solution, we'd check if we can safely pop here
                // For simplicity, we'll just pop
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
                System.out.println("Popped '" + removed + "' to maintain monotonic increasing order");
            }

            stack.push(c);
            inStack[c - 'a'] = true;
            System.out.println("Pushed '" + c + "'");

            // Print current stack state
            System.out.println("Current stack (bottom->top): " + stackToString(stack));
        }

        // Build result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    // Helper method to visualize stack contents
    private static String stackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example 1: Next Greater Element
        int[] nums = {4, 3, 2, 5, 1, 6};
        int[] nextGreater = nextGreaterElement(nums);

        System.out.println("Next Greater Element Example:");
        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Next Greater Elements: " + Arrays.toString(nextGreater));
        System.out.println("For each position, this shows the next greater element to its right (-1 if none exists)");

        // Example 2: Monotonic Stack for Lexicographical Order
        System.out.println("\nMonotonic Stack for Lexicographical Order Example:");
        String example = "bcabc";
        System.out.println("Input: \"" + example + "\"");
        System.out.println("Tracing the monotonic stack operations:");
        String result = demonstrateMonotonicStackForLexOrder(example);
        System.out.println("Final result: \"" + result + "\"");

        System.out.println("\nKey insight: The monotonic stack pattern allows us to");
        System.out.println("efficiently maintain ordered elements by popping elements");
        System.out.println("that don't satisfy our ordering criteria.");
    }
}