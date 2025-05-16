package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._812_longest_increasing_subsequence;

/**
 * Russian Doll Envelopes Problem - 2D Extension of LIS
 * ====================================================
 * <p>
 * This class demonstrates how to solve the "Russian Doll Envelopes" problem,
 * which is a two-dimensional extension of the Longest Increasing Subsequence (LIS) problem.
 * <p>
 * Problem Description:
 * - Given a set of envelopes where each envelope is represented by width and height [w, h]
 * - An envelope can be nested inside another only if both its width and height are strictly smaller
 * - Find the maximum number of envelopes that can be nested
 * <p>
 * Key Concepts:
 * <p>
 * 1. Dimension Reduction Strategy:
 * - Sort the envelopes by width (ascending) to handle the first dimension
 * - For envelopes with the same width, sort by height (descending) to prevent putting
 * multiple envelopes with the same width in the solution
 * - Apply the LIS algorithm on heights to find the solution for the second dimension
 * <p>
 * 2. Sorting Logic:
 * - Primary sort: Width (ascending)
 * - Secondary sort: Height (descending) when widths are equal
 * - The descending sort for height is crucial - it ensures we can't include multiple
 * envelopes with the same width in our solution
 * <p>
 * 3. Algorithm Flow:
 * - Sort the envelopes using the custom comparator
 * - Extract the heights into a separate array
 * - Find the LIS on the heights array
 * <p>
 * 4. Time Complexity: O(n log n)
 * - O(n log n) for sorting the envelopes
 * - O(n log n) for the binary search implementation of LIS
 * <p>
 * 5. Space Complexity: O(n) for storing the heights and the LIS data structures
 * <p>
 * This problem elegantly demonstrates how to reduce a multi-dimensional problem
 * to a simpler, well-understood algorithm through clever preprocessing.
 */

import java.util.*;

public class _812_c_RussianDollEnvelopes {

    public static void main(String[] args) {
        _812_c_RussianDollEnvelopes solution = new _812_c_RussianDollEnvelopes();

        // Example 1 from LeetCode: [[5,4],[6,4],[6,7],[2,3]]
        int[][] envelopes1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println("Example 1: " + solution.maxEnvelopes(envelopes1)); // Expected: 3

        // Example 2 from LeetCode: [[1,1],[1,1],[1,1]]
        int[][] envelopes2 = {{1, 1}, {1, 1}, {1, 1}};
        System.out.println("Example 2: " + solution.maxEnvelopes(envelopes2)); // Expected: 1

        // Custom example for demonstration
        int[][] demoEnvelopes = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}, {1, 5}, {6, 4}};
        System.out.println("\n--- Step-by-Step Demonstration ---");
        solution.demonstrateRussianDoll(demoEnvelopes);
    }

    /**
     * Find the maximum number of envelopes that can be nested.
     *
     * @param envelopes 2D array where each element is [width, height] of an envelope
     * @return maximum number of nested envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        // Sort by width (ascending) and then by height (descending when widths are equal)
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                // If widths are the same, sort by height in descending order
                // This prevents selecting multiple envelopes with the same width
                return b[1] - a[1];
            } else {
                // Sort by width in ascending order
                return a[0] - b[0];
            }
        });

        // Extract heights for LIS algorithm
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        // Apply the binary search approach for LIS on heights
        return lengthOfLIS(heights);
    }

    /**
     * Binary search implementation of Longest Increasing Subsequence algorithm.
     * This is the same algorithm from the previous class, just applied to the heights.
     */
    private int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;

        for (int num : nums) {
            // Binary search to find the pile to place the current number
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] >= num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // If no suitable pile found, create a new one
            if (left == piles) {
                piles++;
            }

            // Place the number on the chosen pile
            top[left] = num;
        }

        return piles;
    }

    /**
     * Demonstrates the Russian Doll Envelopes solution with detailed explanation
     */
    public void demonstrateRussianDoll(int[][] envelopes) {
        System.out.println("Russian Doll Envelopes Problem Demonstration");
        System.out.println("Input envelopes:");
        for (int[] envelope : envelopes) {
            System.out.println("Width: " + envelope[0] + ", Height: " + envelope[1]);
        }

        System.out.println("\nStep 1: Sort envelopes by width (ascending) and height (descending when widths are equal)");
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Descending height
            } else {
                return a[0] - b[0]; // Ascending width
            }
        });

        System.out.println("Sorted envelopes:");
        for (int[] envelope : envelopes) {
            System.out.println("Width: " + envelope[0] + ", Height: " + envelope[1]);
        }

        System.out.println("\nStep 2: Extract heights for LIS algorithm");
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }
        System.out.println("Heights array: " + Arrays.toString(heights));

        System.out.println("\nStep 3: Apply the binary search LIS algorithm to the heights");
        int[] top = new int[heights.length];
        int piles = 0;

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            System.out.println("\nProcessing height: " + height);

            // Binary search
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] >= height) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // Print current piles before placing
            System.out.print("Current piles before placing: ");
            for (int j = 0; j < piles; j++) {
                System.out.print(top[j] + " ");
            }
            System.out.println();

            // Place the height
            String action;
            if (left == piles) {
                action = "Creating new pile " + piles;
                piles++;
            } else {
                action = "Placing on existing pile " + left + " (replacing " + top[left] + ")";
            }
            top[left] = height;

            System.out.println(action);

            // Print current piles after placing
            System.out.print("Current piles after placing: ");
            for (int j = 0; j < piles; j++) {
                System.out.print(top[j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nFinal number of piles (maximum number of nested envelopes): " + piles);
    }
}