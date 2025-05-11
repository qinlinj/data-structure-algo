package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._713_sudoku_solver_game;

import java.util.*;

/**
 * SUDOKU CHEATER PERFORMANCE ANALYSIS
 * ==================================
 * <p>
 * This class compares the performance of different Sudoku solving approaches:
 * 1. Standard backtracking with linear validation
 * 2. Optimized backtracking with hash set validation
 * <p>
 * It analyzes:
 * - Execution time
 * - Number of backtracking steps
 * - Effect of puzzle difficulty on performance
 * - Memory usage implications
 * <p>
 * Key Insights:
 * - How optimization affects different parts of the algorithm
 * - When to use each approach based on performance characteristics
 * - The relationship between puzzle difficulty and solution time
 * <p>
 * This analysis helps understand the real-world implications of algorithm design choices
 * and demonstrates practical optimization techniques for backtracking algorithms.
 */
public class _713_d_SudokuCheaterPerformance {

    /**
     * Generate a Sudoku puzzle with the specified number of filled cells
     *
     * @param filledCells Number of cells to fill with initial values (higher = easier)
     * @return A valid Sudoku puzzle
     */
    public static Integer[][] generatePuzzle(int filledCells) {
        Integer[][] board = new Integer[9][9];

        // Start with a solved board
        OptimizedSudokuSolver solver = new OptimizedSudokuSolver();
        solver.solve(board);  // This will fill the board with a valid solution

        // Make a copy of the solved board
        Integer[][] solution = new Integer[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = board[i][j];
            }
        }

        // Remove some numbers to create a puzzle
        int cellsToRemove = 81 - filledCells;
        Random random = new Random();

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != null) {
                board[row][col] = null;
                cellsToRemove--;
            }
        }

        return board;
    }

    /**
     * Run performance tests on the given solvers with puzzles of various difficulties
     */
    public static void runPerformanceTests() {
        SudokuSolver[] solvers = {
                new BasicSudokuSolver(),
                new OptimizedSudokuSolver()
        };

        int[] filledCellCounts = {30, 35, 40, 45, 50};
        int testsPerLevel = 5;

        System.out.println("SUDOKU SOLVER PERFORMANCE COMPARISON");
        System.out.println("====================================");
        System.out.println();

        for (int filledCells : filledCellCounts) {
            System.out.println("Testing puzzles with " + filledCells + " filled cells (higher = easier):");
            System.out.println("----------------------------------------------------------");

            for (SudokuSolver solver : solvers) {
                long totalTime = 0;
                int totalBacktracks = 0;

                for (int test = 0; test < testsPerLevel; test++) {
                    Integer[][] puzzle = generatePuzzle(filledCells);
                    Integer[][] puzzleCopy = new Integer[9][9];

                    // Make a copy of the puzzle for the solver
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            puzzleCopy[i][j] = puzzle[i][j];
                        }
                    }

                    long startTime = System.nanoTime();
                    solver.solve(puzzleCopy);
                    long endTime = System.nanoTime();

                    long executionTime = (endTime - startTime) / 1_000_000; // Convert to ms
                    totalTime += executionTime;
                    totalBacktracks += solver.getBacktrackCount();
                }

                double avgTime = (double) totalTime / testsPerLevel;
                double avgBacktracks = (double) totalBacktracks / testsPerLevel;

                System.out.println(solver.getName() + ":");
                System.out.println("  - Average execution time: " + String.format("%.2f", avgTime) + " ms");
                System.out.println("  - Average backtracks: " + String.format("%.2f", avgBacktracks));
            }

            System.out.println();
        }
    }

    /**
     * Main method to run the performance analysis
     */
    public static void main(String[] args) {
        runPerformanceTests();
    }

    // Interface for Sudoku solvers
    interface SudokuSolver {
        boolean solve(Integer[][] board);

        String getName();

        int getBacktrackCount();
    }

    /**
     * Basic implementation using standard backtracking
     */
    static class BasicSudokuSolver implements SudokuSolver {
        private boolean found = false;
        private int backtrackCount = 0;

        @Override
        public boolean solve(Integer[][] board) {
            found = false;
            backtrackCount = 0;
            backtrack(board, 0);
            return found;
        }

        private void backtrack(Integer[][] board, int index) {
            if (found) return;

            int m = 9, n = 9;
            int i = index / n, j = index % n;

            if (index == m * n) {
                found = true;
                return;
            }

            if (board[i][j] != null) {
                backtrack(board, index + 1);
                return;
            }

            for (int digit = 1; digit <= 9; digit++) {
                if (!isValid(board, i, j, digit)) continue;

                board[i][j] = digit;
                backtrack(board, index + 1);

                if (found) return;

                board[i][j] = null;
                backtrackCount++;
            }
        }

        private boolean isValid(Integer[][] board, int row, int col, int num) {
            // Check row
            for (int j = 0; j < 9; j++) {
                if (board[row][j] != null && board[row][j] == num) return false;
            }

            // Check column
            for (int i = 0; i < 9; i++) {
                if (board[i][col] != null && board[i][col] == num) return false;
            }

            // Check box
            int boxRow = (row / 3) * 3;
            int boxCol = (col / 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[boxRow + i][boxCol + j] != null &&
                            board[boxRow + i][boxCol + j] == num) {
                        return false;
                    }
                }
            }

            return true;
        }

        @Override
        public String getName() {
            return "Basic Solver";
        }

        @Override
        public int getBacktrackCount() {
            return backtrackCount;
        }
    }

    /**
     * Optimized implementation using hash sets for validation
     */
    static class OptimizedSudokuSolver implements SudokuSolver {
        private boolean found = false;
        private int backtrackCount = 0;
        private List<Set<Integer>> rows = new ArrayList<>(9);
        private List<Set<Integer>> cols = new ArrayList<>(9);
        private List<Set<Integer>> boxes = new ArrayList<>(9);

        public OptimizedSudokuSolver() {
            for (int i = 0; i < 9; i++) {
                rows.add(new HashSet<>());
                cols.add(new HashSet<>());
                boxes.add(new HashSet<>());
            }
        }

        @Override
        public boolean solve(Integer[][] board) {
            found = false;
            backtrackCount = 0;

            // Clear all sets
            for (int i = 0; i < 9; i++) {
                rows.get(i).clear();
                cols.get(i).clear();
                boxes.get(i).clear();
            }

            // Initialize sets with existing numbers
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != null) {
                        rows.get(i).add(board[i][j]);
                        cols.get(j).add(board[i][j]);
                        boxes.get((i / 3) * 3 + j / 3).add(board[i][j]);
                    }
                }
            }

            backtrack(board, 0);
            return found;
        }

        private void backtrack(Integer[][] board, int index) {
            if (found) return;

            int m = 9, n = 9;
            int i = index / n, j = index % n;

            if (index == m * n) {
                found = true;
                return;
            }

            if (board[i][j] != null) {
                backtrack(board, index + 1);
                return;
            }

            for (int digit = 1; digit <= 9; digit++) {
                if (!isValid(i, j, digit)) continue;

                // Make choice
                board[i][j] = digit;
                rows.get(i).add(digit);
                cols.get(j).add(digit);
                boxes.get((i / 3) * 3 + j / 3).add(digit);

                backtrack(board, index + 1);

                if (found) return;

                // Undo choice
                board[i][j] = null;
                rows.get(i).remove(digit);
                cols.get(j).remove(digit);
                boxes.get((i / 3) * 3 + j / 3).remove(digit);
                backtrackCount++;
            }
        }

        private boolean isValid(int row, int col, int digit) {
            return !rows.get(row).contains(digit) &&
                    !cols.get(col).contains(digit) &&
                    !boxes.get((row / 3) * 3 + col / 3).contains(digit);
        }

        @Override
        public String getName() {
            return "Optimized Solver";
        }

        @Override
        public int getBacktrackCount() {
            return backtrackCount;
        }
    }
}