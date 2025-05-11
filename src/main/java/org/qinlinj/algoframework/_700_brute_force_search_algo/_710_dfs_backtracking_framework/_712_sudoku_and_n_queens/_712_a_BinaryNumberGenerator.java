package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._712_sudoku_and_n_queens;

import java.util.*;

/**
 * BINARY NUMBER GENERATOR USING BACKTRACKING
 * ==========================================
 * <p>
 * Problem: Generate all binary numbers of length n.
 * This serves as a simpler introduction to the backtracking approach
 * that will be used to solve Sudoku and N-Queens problems.
 * <p>
 * Key Insights:
 * 1. This problem involves traversing a binary decision tree with a height of n+1
 * 2. Each node in the tree represents a partial binary number
 * 3. At each position, we have two choices: 0 or 1
 * 4. The leaf nodes (at depth n) represent all possible binary numbers of length n
 * <p>
 * Visualization of the Tree (for n=3):
 * - Root node represents an empty string
 * - Each level represents adding one more binary digit
 * - Each path from root to leaf represents one binary number
 * - For n=3, we get 2^3 = 8 different binary numbers at the leaf nodes
 * <p>
 * Recursive Structure:
 * - Each layer represents a position in the binary number
 * - Each branch represents a choice (0 or 1) for that position
 * - The tree has a depth of n+1 (including the root)
 * <p>
 * Time Complexity: O(2^n) - we generate all 2^n binary numbers
 * Space Complexity: O(n) - maximum recursion depth and path length
 */
public class _712_a_BinaryNumberGenerator {

    // Store the results
    private List<String> results = new ArrayList<>();

    // Track the current path
    private StringBuilder track = new StringBuilder();

    /**
     * Main method to demonstrate the binary number generation
     */
    public static void main(String[] args) {
        _712_a_BinaryNumberGenerator generator = new _712_a_BinaryNumberGenerator();
        int n = 3;
        List<String> binaryNumbers = generator.generateBinaryNumber(n);

        System.out.println("All binary numbers of length " + n + ":");
        for (String binary : binaryNumbers) {
            System.out.println(binary);
        }

        // Expected output:
        // 000
        // 001
        // 010
        // 011
        // 100
        // 101
        // 110
        // 111
    }

    /**
     * Generate all binary numbers of length n
     *
     * @param n The length of the binary number
     * @return List of all binary numbers of length n
     */
    public List<String> generateBinaryNumber(int n) {
        backtrack(n, 0);
        return results;
    }

    /**
     * Backtracking function to build binary numbers
     *
     * @param n Total length of binary number
     * @param i Current position (depth in the recursion tree)
     */
    private void backtrack(int n, int i) {
        // Base case: If we've filled all n positions
        if (i == n) {
            results.add(track.toString());
            return;
        }

        // For each position, we have two choices: 0 or 1
        for (int val : new int[]{0, 1}) {
            // Make a choice
            track.append(val);

            // Explore further
            backtrack(n, i + 1);

            // Undo the choice (backtrack)
            track.deleteCharAt(track.length() - 1);
        }
    }
}