package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack - Comprehensive Summary
 * <p>
 * This class provides a comprehensive overview of monotonic stack variations and applications.
 * <p>
 * Overview of Monotonic Stack Variations:
 * <p>
 * 1. Direction of Processing:
 * - Right to Left: For finding next (right) elements
 * - Left to Right: For finding previous (left) elements
 * <p>
 * 2. Stack Order:
 * - Decreasing Stack: For finding greater elements
 * - Increasing Stack: For finding smaller elements
 * <p>
 * 3. Comparison Types:
 * - Strict Comparison (>, <): For finding strictly greater/smaller elements
 * - Non-strict Comparison (>=, <=): For finding greater-or-equal/smaller-or-equal elements
 * <p>
 * 4. Common Patterns:
 * - Next Greater Element
 * - Next Greater or Equal Element
 * - Next Smaller Element
 * - Next Smaller or Equal Element
 * - Previous Greater Element
 * - Previous Greater or Equal Element
 * - Previous Smaller Element
 * - Previous Smaller or Equal Element
 * <p>
 * 5. Practical Applications:
 * - Finding spans (stock price spans)
 * - Calculating discounts (final prices)
 * - Finding visible elements (visible people in queue)
 * - Processing linked lists (next greater nodes)
 * - Removing digits to form smallest number
 * - Finding unsorted subarrays
 * - Analyzing car fleets
 * <p>
 * Time Complexity: O(n) for all variations - each element is pushed and popped at most once
 * Space Complexity: O(n) for all variations in the worst case
 */

import java.util.*;

public class _346_g_MonotonicStackSummary {

    /**
     * Main method to provide a comprehensive summary
     */
    public static void main(String[] args) {
        System.out.println("===== MONOTONIC STACK VARIATIONS COMPREHENSIVE SUMMARY =====\n");

        // Variation patterns
        displayVariationPatterns();

        // Example comparisons
        displayExampleComparisons();

        // Application summary
        displayApplications();

        // Implementation tips
        displayImplementationTips();
    }

    /**
     * Display a summary of all variation patterns
     */
    private static void displayVariationPatterns() {
        System.out.println("VARIATION PATTERNS:");
        System.out.println("+-----------------------+---------------+------------------+---------------+");
        System.out.println("| Variation             | Direction     | Comparison       | Stack Order   |");
        System.out.println("+-----------------------+---------------+------------------+---------------+");
        System.out.println("| Next Greater          | Right to Left | Remove if <= x   | Decreasing    |");
        System.out.println("| Next Greater/Equal    | Right to Left | Remove if < x    | Decreasing    |");
        System.out.println("| Next Smaller          | Right to Left | Remove if >= x   | Increasing    |");
        System.out.println("| Next Smaller/Equal    | Right to Left | Remove if > x    | Increasing    |");
        System.out.println("| Previous Greater      | Left to Right | Remove if <= x   | Decreasing    |");
        System.out.println("| Previous Greater/Equal| Left to Right | Remove if < x    | Decreasing    |");
        System.out.println("| Previous Smaller      | Left to Right | Remove if >= x   | Increasing    |");
        System.out.println("| Previous Smaller/Equal| Left to Right | Remove if > x    | Increasing    |");
        System.out.println("+-----------------------+---------------+------------------+---------------+\n");

        System.out.println("KEY PATTERN INSIGHTS:");
        System.out.println("1. Direction determines whether we find next or previous elements");
        System.out.println("   - Right to Left: For 'next' elements (to the right)");
        System.out.println("   - Left to Right: For 'previous' elements (to the left)");
        System.out.println("2. Comparison determines the type of element we're looking for");
        System.out.println("   - For 'greater' elements: Remove smaller/equal elements (<=)");
        System.out.println("   - For 'smaller' elements: Remove greater/equal elements (>=)");
        System.out.println("3. For 'or equal' variations, adjust the comparison operator:");
        System.out.println("   - Change <= to < when looking for greater/equal elements");
        System.out.println("   - Change >= to > when looking for smaller/equal elements\n");
    }

    /**
     * Display example comparisons for all variations
     */
    private static void displayExampleComparisons() {
        System.out.println("EXAMPLE COMPARISONS:");

        int[] example1 = {1, 3, 2, 4, 4};
        System.out.println("Array: " + Arrays.toString(example1));
        System.out.println("Next Greater:       [3, 4, 4, -1, -1]");
        System.out.println("Next Greater/Equal: [3, 4, 4, 4, -1]");

        int[] example2 = {8, 4, 6, 6, 3};
        System.out.println("\nArray: " + Arrays.toString(example2));
        System.out.println("Next Smaller:       [4, 3, 3, 3, -1]");
        System.out.println("Next Smaller/Equal: [4, 3, 6, 3, -1]");

        int[] example3 = {8, 7, 6, 7};
        System.out.println("\nArray: " + Arrays.toString(example3));
        System.out.println("Previous Greater:       [-1, 8, 7, 8]");
        System.out.println("Previous Greater/Equal: [-1, 8, 7, 8]");

        int[] example4 = {3, 6, 6, 5};
        System.out.println("\nArray: " + Arrays.toString(example4));
        System.out.println("Previous Smaller:       [-1, 3, 3, 3]");
        System.out.println("Previous Smaller/Equal: [-1, 3, 6, 3]");
        System.out.println();
    }

    /**
     * Display a summary of practical applications
     */
    private static void displayApplications() {
        System.out.println("PRACTICAL APPLICATIONS:");
        System.out.println("+-----------------------+--------------------------------------------------+");
        System.out.println("| Problem               | Approach                                         |");
        System.out.println("+-----------------------+--------------------------------------------------+");
        System.out.println("| Stock Span (901)      | Previous Greater Element with span accumulation  |");
        System.out.println("| NextLargerNodes (1019)| Next Greater Element with linked list conversion |");
        System.out.println("| VisiblePeople (1944)  | Next Greater Element with counting               |");
        System.out.println("| FinalPrices (1475)    | Next Smaller or Equal Element for discounts      |");
        System.out.println("| RemoveKDigits (402)   | Maintain increasing digits using monotonic stack |");
        System.out.println("| CarFleet (853)        | Sort by position and track arrival times         |");
        System.out.println("| UnsortedSubarray (581)| Find elements out of order using two mono stacks |");
        System.out.println("+-----------------------+--------------------------------------------------+\n");

        System.out.println("COMMON APPLICATION PATTERNS:");
        System.out.println("1. Element Relationship Problems:");
        System.out.println("   - Finding next/previous greater/smaller elements");
        System.out.println("   - Finding spans or sequences with specific properties");
        System.out.println("2. Optimization Problems:");
        System.out.println("   - Finding the minimum/maximum in a sliding window");
        System.out.println("   - Finding optimal strategies based on monotonic properties");
        System.out.println("3. Geometric Problems:");
        System.out.println("   - Skyline/building problems");
        System.out.println("   - Histogram area calculations");
        System.out.println("4. Time Series Analysis:");
        System.out.println("   - Stock price analysis");
        System.out.println("   - Temperature trends\n");
    }

    /**
     * Display implementation tips
     */
    private static void displayImplementationTips() {
        System.out.println("IMPLEMENTATION TIPS:");
        System.out.println("1. Pattern Selection:");
        System.out.println("   - Identify the relationship you're looking for (next/prev, greater/smaller)");
        System.out.println("   - Choose the appropriate monotonic stack pattern");
        System.out.println("2. Stack Contents:");
        System.out.println("   - Sometimes store values directly");
        System.out.println("   - Sometimes store indices (when position matters)");
        System.out.println("   - Sometimes store pairs (like [value, count] or [value, index])");
        System.out.println("3. Edge Cases:");
        System.out.println("   - Handle empty stack scenarios with default values");
        System.out.println("   - For circular arrays, process the array twice or use modulo");
        System.out.println("4. Performance Optimization:");
        System.out.println("   - Combine steps when appropriate for better efficiency");
        System.out.println("   - Leverage the O(n) time complexity of monotonic stacks");
        System.out.println("5. Problem Recognition:");
        System.out.println("   - Look for keywords like 'next', 'previous', 'greater', 'smaller'");
        System.out.println("   - Look for problems involving finding elements with relative properties");
        System.out.println("   - Consider monotonic stack when brute force would be O(n²)\n");

        System.out.println("CONCLUSION:");
        System.out.println("Monotonic stack is a powerful pattern for solving a specific class of problems");
        System.out.println("efficiently. By understanding the variations and when to apply them, you can");
        System.out.println("tackle a wide range of challenging problems with elegant O(n) solutions.");
    }
}
