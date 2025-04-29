package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack - Practice Problem 4
 * Online Stock Span (LeetCode 901)
 * <p>
 * Problem Description:
 * Design an algorithm that collects daily stock prices and returns the span of
 * the stock's price for the current day. The span of the stock's price today is
 * defined as the maximum number of consecutive days (starting from today and
 * going backward) for which the stock price was less than or equal to today's price.
 * <p>
 * Approach:
 * 1. Use a monotonic stack to maintain prices in decreasing order
 * 2. When a new price comes in, pop all prices that are less than or equal to it
 * 3. Sum up the spans of all popped prices (plus 1 for the current day)
 * 4. Store each price along with its span to handle future queries efficiently
 * <p>
 * Key insight:
 * This problem is related to the "previous greater element" pattern, but with a twist:
 * we need to keep track of the spans as we go, and maintain state between calls.
 * <p>
 * Time Complexity: O(n) amortized over n calls to next()
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _346_f_StockSpan {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        _346_f_StockSpan solution = new _346_f_StockSpan();

        System.out.println("===== STOCK PRICE SPAN =====");

        // Example 1
        StockSpanner spanner = new StockSpanner();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = new int[prices.length];

        System.out.println("Input prices: " + Arrays.toString(prices));

        for (int i = 0; i < prices.length; i++) {
            spans[i] = spanner.next(prices[i]);
        }

        System.out.println("Output spans: " + Arrays.toString(spans));
        System.out.println("Expected: [1, 1, 1, 2, 1, 4, 6]");

        // Detailed explanation
        System.out.println("\n===== DETAILED EXPLANATION =====");
        solution.demonstrateSolution();

        // Explanation of the pattern
        System.out.println("\n===== KEY INSIGHTS =====");
        System.out.println("1. This problem involves finding consecutive days with prices <= today's price");
        System.out.println("2. We use a stack to store [price, span] pairs in decreasing order");
        System.out.println("3. When a new price arrives:");
        System.out.println("   - Pop all prices <= current price");
        System.out.println("   - Add up spans of popped prices (+ 1 for today)");
        System.out.println("   - Store the new [price, span] pair");
        System.out.println("4. By storing spans with prices, we avoid recounting days we've already processed");
        System.out.println("5. This gives us O(1) amortized time per query");
    }

    /**
     * Demonstrate the solution with a detailed explanation
     */
    public void demonstrateSolution() {
        StockSpanner spanner = new StockSpanner();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        System.out.println("Processing stock prices: " + Arrays.toString(prices));
        System.out.println("\nDay-by-day calculations:");

        Stack<int[]> visualStack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            System.out.println("\nDay " + (i + 1) + " - Price: " + price);
            System.out.println("  Current stack: " + stackToString(visualStack));

            int span = 1;

            // Pop all prices less than or equal to current price
            while (!visualStack.isEmpty() && price >= visualStack.peek()[0]) {
                int[] popped = visualStack.pop();
                span += popped[1];
                System.out.println("  Pop [" + popped[0] + ", " + popped[1] + "] (price <= " + price + ")");
                System.out.println("  Add span " + popped[1] + " to current span, new span = " + span);
            }

            // Push current price and its span
            visualStack.push(new int[]{price, span});
            System.out.println("  Push [" + price + ", " + span + "] to stack");
            System.out.println("  Updated stack: " + stackToString(visualStack));
            System.out.println("  Span for day " + (i + 1) + ": " + span);
        }
    }

    /**
     * Helper method to format stack contents as a string
     */
    private String stackToString(Stack<int[]> stack) {
        if (stack.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] pair : stack) {
            sb.append("[").append(pair[0]).append(", ").append(pair[1]).append("], ");
        }
        sb.setLength(sb.length() - 2); // Remove trailing comma and space
        sb.append("]");
        return sb.toString();
    }

    /**
     * Implementation of the StockSpanner class
     */
    static class StockSpanner {
        // Stack to store pairs of [price, span]
        private Stack<int[]> stack;

        /**
         * Initialize the StockSpanner
         */
        public StockSpanner() {
            stack = new Stack<>();
        }

        /**
         * Calculate the span of the stock price for the current day
         *
         * @param price The stock price for the current day
         * @return The span of the stock price
         */
        public int next(int price) {
            // Start with span of 1 (count the current day)
            int span = 1;

            // Pop all prices less than or equal to current price
            while (!stack.isEmpty() && price >= stack.peek()[0]) {
                int[] popped = stack.pop();
                // Add the span of the popped price to our current span
                span += popped[1];
            }

            // Push current price and its span to the stack
            stack.push(new int[]{price, span});

            return span;
        }
    }
}
