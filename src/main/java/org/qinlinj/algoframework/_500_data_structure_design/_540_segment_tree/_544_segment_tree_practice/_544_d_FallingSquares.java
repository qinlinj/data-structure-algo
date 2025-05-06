package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._544_segment_tree_practice; /**
 * LeetCode 699: Falling Squares
 * <p>
 * Problem Description:
 * Given positions of falling squares on a 2D plane, where positions[i] = [left_i, sideLength_i]
 * represents the position and size of the ith square. Each square falls from an infinite height
 * until it lands on either another square or the x-axis. After each square falls, return the
 * height of the tallest stack of squares.
 * <p>
 * Key Concepts:
 * 1. Interval Height Tracking - Maintaining the height at each position on the x-axis
 * 2. Segment Tree with Range Updates - Using a segment tree to efficiently update and query heights
 * 3. Range Maximum Query - Finding the maximum height in a range to determine landing position
 * <p>
 * Solution Approach:
 * Segment Tree: O(N log MAX_COORD) time complexity
 * - Create a segment tree that tracks the maximum height at each position
 * - For each falling square:
 * 1. Query the current maximum height in the interval where the square will land
 * 2. Update the interval with the new height (current max + sideLength)
 * 3. Track the overall maximum height across all intervals
 * <p>
 * The key insight is that we can use a segment tree with lazy propagation to efficiently
 * handle range updates and range maximum queries, which are the core operations for this problem.
 */

import java.util.*;
import org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree._543_c_AllInOneSegmentTree;

public class _544_d_FallingSquares {

    public static void main(String[] args) {
        _544_d_FallingSquares solution = new _544_d_FallingSquares();

        // Example 1
        int[][] positions1 = {{1, 2}, {2, 3}, {6, 1}};
        List<Integer> result1 = solution.fallingSquares(positions1);
        System.out.println("Example 1 output: " + result1); // [2, 5, 5]

        // Example 2
        int[][] positions2 = {{100, 100}, {200, 100}};
        List<Integer> result2 = solution.fallingSquares(positions2);
        System.out.println("Example 2 output: " + result2); // [100, 100]

        // Using brute force implementation for comparison
        BruteForceImplementation bruteSolution = new BruteForceImplementation();

        System.out.println("\nBrute Force Implementation:");
        System.out.println("Example 1 output: " + bruteSolution.fallingSquares(positions1)); // [2, 5, 5]
        System.out.println("Example 2 output: " + bruteSolution.fallingSquares(positions2)); // [100, 100]
    }

    /**
     * Calculates the height of the tallest stack after each square falls
     *
     * @param positions Array of [left_i, sideLength_i] pairs representing positions and sizes of squares
     * @return List of heights after each square falls
     */
    public List<Integer> fallingSquares(int[][] positions) {
        // Maximum coordinate limit to create the segment tree
        int boundary = 2 * 100000000; // 2 * 10^8

        // Create a segment tree for maximum height tracking
        _543_c_AllInOneSegmentTree maxTree = new _543_c_AllInOneSegmentTree(0, boundary, 0, "max");

        List<Integer> results = new ArrayList<>();
        int maxHeight = 0;

        for (int[] position : positions) {
            // Extract square position and size
            int left = position[0];
            int sideLength = position[1];
            int right = left + sideLength - 1; // Right boundary (inclusive)

            // Query the current maximum height in the landing interval
            int currentMaxHeight = maxTree.query(left, right);

            // Calculate the new height after this square lands
            int newHeight = currentMaxHeight + sideLength;

            // Update the segment tree with the new height
            maxTree.rangeUpdate(left, right, newHeight);

            // Track the overall maximum height
            maxHeight = Math.max(maxHeight, newHeight);

            // Add the current maximum height to the results
            results.add(maxHeight);
        }

        return results;
    }

    /**
     * Alternative implementation using a brute force approach
     * This is less efficient but easier to understand
     */
    public static class BruteForceImplementation {
        public List<Integer> fallingSquares(int[][] positions) {
            List<Integer> results = new ArrayList<>();
            // Store [left, right, height] for each landed square
            List<int[]> landedSquares = new ArrayList<>();
            int currentMaxHeight = 0;

            for (int[] position : positions) {
                int left = position[0];
                int size = position[1];
                int right = left + size - 1;

                // Calculate the landing height based on previously landed squares
                int height = size; // Initial height is the square's own size

                for (int[] square : landedSquares) {
                    int squareLeft = square[0];
                    int squareRight = square[1];
                    int squareHeight = square[2];

                    // Check if this square is under the falling square
                    if (right >= squareLeft && left <= squareRight) {
                        // Overlapping squares, add the previous square's height
                        height = Math.max(height, squareHeight + size);
                    }
                }

                // Add the new square to the landed squares list
                landedSquares.add(new int[]{left, right, height});

                // Update the maximum height
                currentMaxHeight = Math.max(currentMaxHeight, height);
                results.add(currentMaxHeight);
            }

            return results;
        }
    }
}