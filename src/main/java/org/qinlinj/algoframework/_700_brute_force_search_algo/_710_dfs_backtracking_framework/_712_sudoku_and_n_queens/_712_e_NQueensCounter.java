package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._712_sudoku_and_n_queens;

import java.util.*;

/**
 * N-QUEENS SOLUTION COUNTER USING BACKTRACKING
 * ===========================================
 * <p>
 * Problem: Count the total number of valid ways to place N queens on an NxN chessboard.
 * LeetCode Problem 52: https://leetcode.com/problems/n-queens-ii/
 * <p>
 * Key Insights:
 * 1. This is a variation of the N-Queens problem where we only need to count solutions
 * 2. We can use the same backtracking approach but avoid storing the actual solutions
 * 3. Simply increment a counter when a valid solution is found
 * <p>
 * Optimization:
 * - No need to create and store board configurations
 * - Just maintain a counter instead of a list of solutions
 * - Can reduce memory usage significantly for large N
 * <p>
 * The core algorithm remains the same:
 * - Place queens row by row
 * - Check if a placement is valid considering previously placed queens
 * - If all N queens are placed, increment the count
 * <p>
 * Time Complexity: Still O(N!), but with less overhead
 * Space Complexity: O(N) for the recursion stack, compared to O(N²) when storing solutions
 */
public class _712_e_NQueensCounter {

    // Counter for valid solutions
    private int count = 0;

    /**
     * Main method to demonstrate counting N-Queens solutions
     */
    public static void main(String[] args) {
        _712_e_NQueensCounter counter = new _712_e_NQueensCounter();

        // Test with different board sizes
        for (int n = 1; n <= 9; n++) {
            int solutions = counter.totalNQueens(n);
            System.out.println(n + "-Queens problem has " + solutions + " solutions");
        }

        // Compare with optimized method
        System.out.println("\nUsing optimized method:");
        int n = 8;
        long startTime = System.nanoTime();
        int solutions = counter.totalNQueensOptimized(n);
        long endTime = System.nanoTime();
        System.out.println(n + "-Queens problem has " + solutions + " solutions");
        System.out.println("Optimized method took " + (endTime - startTime) / 1000000 + " ms");
    }

    /**
     * Count the total number of solutions to the N-Queens problem
     *
     * @param n Size of the board and number of queens
     * @return Number of valid solutions
     */
    public int totalNQueens(int n) {
        // Initialize empty board
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append('.');
            }
            board.add(row.toString());
        }

        // Start backtracking from the first row
        backtrack(board, 0);

        return count;
    }

    /**
     * Backtracking function to count N-Queens solutions
     *
     * @param board Current state of the board
     * @param row   Current row being considered
     */
    private void backtrack(List<String> board, int row) {
        // If all rows have a queen, we've found a valid solution
        if (row == board.size()) {
            count++; // Just increment the counter instead of storing the solution
            return;
        }

        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // Skip invalid placements
            if (!isValid(board, row, col)) {
                continue;
            }

            // Make a choice - place a queen
            char[] rowChars = board.get(row).toCharArray();
            rowChars[col] = 'Q';
            board.set(row, new String(rowChars));

            // Explore next row
            backtrack(board, row + 1);

            // Undo the choice (backtrack)
            rowChars[col] = '.';
            board.set(row, new String(rowChars));
        }
    }

    /**
     * Check if placing a queen at (row, col) is valid
     *
     * @param board Current state of the board
     * @param row   Row to place the queen
     * @param col   Column to place the queen
     * @return true if placement is valid, false otherwise
     */
    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();

        // Check column (vertical)
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * Further optimized version that doesn't even need to store the board
     * (Alternative implementation using arrays instead of string manipulation)
     */
    public int totalNQueensOptimized(int n) {
        boolean[] cols = new boolean[n];     // columns under attack
        boolean[] diag1 = new boolean[2 * n - 1]; // diagonals under attack (top-left to bottom-right)
        boolean[] diag2 = new boolean[2 * n - 1]; // diagonals under attack (top-right to bottom-left)

        return countQueens(0, n, cols, diag1, diag2);
    }

    private int countQueens(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            int d1 = row + col;           // diagonal 1 index
            int d2 = row - col + n - 1;   // diagonal 2 index

            // If the column or diagonals are under attack, skip this position
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen and mark attacks
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Explore next row
            count += countQueens(row + 1, n, cols, diag1, diag2);

            // Remove queen and clear attacks
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }

        return count;
    }
}