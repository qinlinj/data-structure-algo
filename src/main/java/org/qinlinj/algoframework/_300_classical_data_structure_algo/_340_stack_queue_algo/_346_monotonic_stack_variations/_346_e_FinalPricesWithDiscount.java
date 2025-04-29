package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack - Practice Problem 3
 * Final Prices With a Special Discount in a Shop (LeetCode 1475)
 * <p>
 * Problem Description:
 * Given an array of prices, where prices[i] is the price of the ith item in a shop.
 * If you buy the ith item, you'll receive a discount equal to prices[j],
 * where j is the smallest index such that j > i and prices[j] <= prices[i].
 * If there is no such j, you don't get any discount.
 * Return the final prices after applying the discount.
 * <p>
 * Approach:
 * 1. For each item, find the first smaller or equal price to its right (next smaller or equal element)
 * 2. Apply the discount by subtracting this price from the original price
 * 3. If no smaller or equal price exists, no discount is applied
 * <p>
 * Key insight:
 * This is a direct application of the "next smaller or equal element" monotonic stack pattern.
 * <p>
 * Time Complexity: O(n) where n is the number of prices
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _346_e_FinalPricesWithDiscount {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        _346_e_FinalPricesWithDiscount solution = new _346_e_FinalPricesWithDiscount();

        System.out.println("===== FINAL PRICES WITH SPECIAL DISCOUNT =====");

        // Example 1
        int[] prices1 = {8, 4, 6, 2, 3};
        int[] result1 = solution.finalPrices(prices1);
        System.out.println("Original prices: " + Arrays.toString(prices1));
        System.out.println("Final prices: " + Arrays.toString(result1));
        System.out.println("Expected: [4, 2, 4, 2, 3]");

        // Example 2
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] result2 = solution.finalPrices(prices2);
        System.out.println("\nOriginal prices: " + Arrays.toString(prices2));
        System.out.println("Final prices: " + Arrays.toString(result2));
        System.out.println("Expected: [1, 2, 3, 4, 5]");

        // Example 3
        int[] prices3 = {10, 1, 1, 6};
        int[] result3 = solution.finalPrices(prices3);
        System.out.println("\nOriginal prices: " + Arrays.toString(prices3));
        System.out.println("Final prices: " + Arrays.toString(result3));
        System.out.println("Expected: [9, 0, 1, 6]");

        // Detailed explanation for the first example
        System.out.println("\n===== DETAILED EXPLANATION =====");
        solution.demonstrateSolution(prices1);

        // Compare the two implementations
        System.out.println("\n===== COMPARING IMPLEMENTATIONS =====");
        System.out.println("Original prices: " + Arrays.toString(prices1));
        System.out.println("Two-step method: " + Arrays.toString(solution.finalPrices(prices1)));
        System.out.println("Direct method: " + Arrays.toString(solution.finalPricesDirect(prices1)));
    }

    /**
     * Calculate the final prices after applying the discount
     *
     * @param prices Array of original prices
     * @return Array of final prices after applying the discount
     */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];

        // Get next smaller or equal elements
        int[] nextSmallerOrEqual = nextSmallerOrEqualElement(prices);

        // Calculate final prices after discount
        for (int i = 0; i < n; i++) {
            if (nextSmallerOrEqual[i] != -1) {
                // Apply discount
                result[i] = prices[i] - nextSmallerOrEqual[i];
            } else {
                // No discount
                result[i] = prices[i];
            }
        }

        return result;
    }

    /**
     * Helper method to find the next smaller or equal element for each price
     *
     * @param nums Array of prices
     * @return Array where result[i] is the next smaller or equal element to nums[i], or -1 if none exists
     */
    private int[] nextSmallerOrEqualElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process from right to left
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
     * Direct implementation that combines the two steps above
     * (More efficient without the separate helper method)
     */
    public int[] finalPricesDirect(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements greater than current
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }

            // Calculate final price with discount (if applicable)
            result[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();

            // Push current element
            stack.push(prices[i]);
        }

        return result;
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public void demonstrateSolution(int[] prices) {
        System.out.println("Original prices: " + Arrays.toString(prices));

        int n = prices.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        System.out.println("\nStep-by-step calculation:");

        for (int i = n - 1; i >= 0; i--) {
            System.out.println("\nItem " + i + " (price " + prices[i] + "):");
            System.out.println("  Current stack: " + stack);

            // Pop prices that cannot provide discount
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                System.out.println("  Pop " + stack.peek() + " (> " + prices[i] + ", cannot provide discount)");
                stack.pop();
            }

            // Calculate discount
            if (stack.isEmpty()) {
                result[i] = prices[i];
                System.out.println("  No discount available, final price = " + result[i]);
            } else {
                result[i] = prices[i] - stack.peek();
                System.out.println("  Discount = " + stack.peek() + ", final price = " + result[i]);
            }

            // Push current price
            stack.push(prices[i]);
            System.out.println("  Push " + prices[i] + " to stack");
            System.out.println("  Updated stack: " + stack);
        }

        System.out.println("\nFinal prices: " + Arrays.toString(result));
    }
}
