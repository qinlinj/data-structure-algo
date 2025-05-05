package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_d_RandomFlipMatrix
 * <p>
 * LeetCode #519: Random Flip Matrix
 * <p>
 * Problem:
 * Design a data structure that randomly picks an index (i, j) with value 0
 * from a binary matrix and flips it to 1. All indices with value 0 should
 * be equally likely to be selected. Optimize for time and space complexity
 * and minimize calls to the random function.
 * <p>
 * Approach:
 * The key insight is to think of the 2D matrix as a flattened 1D array of size m*n.
 * Instead of storing the entire matrix, we:
 * 1. Track only the flipped cells using a HashMap
 * 2. Use the Fisher-Yates shuffle concept to efficiently pick random indices
 * 3. Maintain a virtual size that shrinks as we flip cells
 * <p>
 * When we need to flip a cell:
 * - Generate a random index from the current virtual size
 * - If this index has been flipped before, use its mapped value
 * - Swap this position with the last position in our virtual array
 * - Reduce the virtual size by 1
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(min(m*n, k)) where k is the number of flip operations
 */

import java.util.*;

public class _522_d_RandomFlipMatrix {
    private int m, n;
    // The virtual size of our flattened matrix
    private int remaining;
    // Maps flipped indices to their replacement values
    private HashMap<Integer, Integer> flippedMap;
    private Random rand;

    public _522_d_RandomFlipMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.remaining = m * n; // Start with all cells available
        this.flippedMap = new HashMap<>();
        this.rand = new Random();
    }

    public static void main(String[] args) {
        // Create a 3x1 matrix
        _522_d_RandomFlipMatrix solution = new _522_d_RandomFlipMatrix(3, 1);

        // Demonstrate the random flipping
        int[] flip1 = solution.flip();
        System.out.println("Flip 1: [" + flip1[0] + ", " + flip1[1] + "]");

        int[] flip2 = solution.flip();
        System.out.println("Flip 2: [" + flip2[0] + ", " + flip2[1] + "]");

        int[] flip3 = solution.flip();
        System.out.println("Flip 3: [" + flip3[0] + ", " + flip3[1] + "]");

        // Reset the matrix
        solution.reset();
        System.out.println("Matrix reset");

        // Flip again after reset
        int[] flip4 = solution.flip();
        System.out.println("Flip 4 (after reset): [" + flip4[0] + ", " + flip4[1] + "]");
    }

    public int[] flip() {
        // Generate a random index from the remaining indices
        int randomIdx = rand.nextInt(remaining);

        // Get the actual index value (accounting for previously flipped cells)
        int actualIdx = randomIdx;
        if (flippedMap.containsKey(randomIdx)) {
            actualIdx = flippedMap.get(randomIdx);
        }

        // The index to be moved into randomIdx's position
        // (either the last index or its mapped value)
        int lastIdx = remaining - 1;
        if (flippedMap.containsKey(lastIdx)) {
            lastIdx = flippedMap.get(lastIdx);
        }

        // Map randomIdx to the last available index
        flippedMap.put(randomIdx, lastIdx);

        // Decrease the size of available indices
        remaining--;

        // Convert 1D index back to 2D coordinates
        return new int[]{actualIdx / n, actualIdx % n};
    }

    public void reset() {
        // Reset to initial state
        flippedMap.clear();
        remaining = m * n;
    }
}