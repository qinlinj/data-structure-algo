package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._754_hua_rong_road_game; /**
 * Huarong Road BFS Optimization Techniques
 * ---------------------------------------
 * <p>
 * Summary:
 * This class explores optimization techniques for the Huarong Road puzzle solver,
 * focusing on reducing the search space and improving the efficiency of the BFS algorithm.
 * It demonstrates advanced techniques like piece equivalence, pruning, and heuristic functions
 * that can be applied to complex sliding block puzzles.
 * <p>
 * Key Optimization Techniques:
 * <p>
 * 1. Piece Equivalence:
 * - Treating pieces with the same shape as equivalent drastically reduces the search space
 * - This is implemented through categorization in the board hashing function
 * - Pieces of the same shape (e.g., all 1×2 generals) are assigned the same category
 * <p>
 * 2. Efficient State Representation:
 * - Compact board representation for faster hashing and comparison
 * - Using character encoding instead of storing full board states
 * <p>
 * 3. Early Termination:
 * - Check for solution state immediately after each move
 * - Return as soon as a solution is found
 * <p>
 * 4. Move Pruning:
 * - Skip moves that would go out of bounds
 * - Skip moves into occupied spaces
 * - Skip moves that would result in already visited states
 * <p>
 * These optimizations can reduce the search space from millions to thousands of states,
 * making the BFS algorithm practical for solving the Huarong Road puzzle in a reasonable time.
 */

import java.util.*;

public class _754_b_HuarongRoadOptimizations {

    // Board dimensions
    private static final int ROWS = 5, COLS = 4;

    /**
     * Demonstrates different hashing strategies and their impact on search space
     */
    public static void compareHashingStrategies() {
        // Example board
        int[][] board = {
                {3, 1, 1, 4},
                {3, 1, 1, 4},
                {5, 2, 2, 6},
                {5, 7, 8, 6},
                {9, 0, 0, 10}
        };

        // Strategy 1: No optimization - each piece is unique
        String hash1 = naiveHash(board);

        // Strategy 2: Treating pieces of the same shape as equivalent
        String hash2 = optimizedHash(board);

        System.out.println("Board state:");
        printBoard(board);
        System.out.println("\nNaive hashing (no optimization): " + hash1);
        System.out.println("Length: " + hash1.length() + " characters");
        System.out.println("\nOptimized hashing (piece equivalence): " + hash2);
        System.out.println("Length: " + hash2.length() + " characters");

        System.out.println("\nSearch space impact analysis:");
        System.out.println("1. Without optimization:");
        System.out.println("   - Each board state is considered unique when any piece moves");
        System.out.println("   - Even functionally identical states (e.g., swapping two soldiers) are treated as different");
        System.out.println("   - Estimated search space size: Millions of states");

        System.out.println("\n2. With piece equivalence optimization:");
        System.out.println("   - Pieces of the same shape are treated as equivalent");
        System.out.println("   - Functionally identical states are treated as the same state");
        System.out.println("   - Estimated search space reduction: ~10-100x smaller");
        System.out.println("   - Makes BFS solution feasible in reasonable time");
    }

    /**
     * Naive hashing that treats each piece as unique
     */
    private static String naiveHash(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                sb.append(board[r][c]);
                // Add separator for clarity in this example
                if (c < COLS - 1) sb.append(",");
            }
            if (r < ROWS - 1) sb.append("|");
        }
        return sb.toString();
    }

    /**
     * Optimized hashing that treats pieces of the same shape as equivalent
     */
    private static String optimizedHash(int[][] board) {
        // Create mapping from piece ID to category
        Map<Integer, Character> pidToCategory = new HashMap<>();
        // Empty spaces (0x0) - category 0
        pidToCategory.put(0, '0');
        // Four soldiers (1x1) - category 1
        pidToCategory.put(7, '1');
        pidToCategory.put(8, '1');
        pidToCategory.put(9, '1');
        pidToCategory.put(10, '1');
        // Four generals (1x2) - category 2
        pidToCategory.put(3, '2');
        pidToCategory.put(4, '2');
        pidToCategory.put(5, '2');
        pidToCategory.put(6, '2');
        // Guan Yu (2x1) - category 3
        pidToCategory.put(2, '3');
        // Cao Cao (2x2) - category 4
        pidToCategory.put(1, '4');

        // Convert board to category string
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                sb.append(pidToCategory.get(board[r][c]));
            }
        }
        return sb.toString();
    }

    /**
     * Print the board for visualization
     */
    private static void printBoard(int[][] board) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Calculate and display search space statistics for a given board
     */
    public static void analyzeSearchSpace(int[][] board) {
        // Retrieve all pieces
        Map<Integer, List<int[]>> positions = getPositions(board);

        // Count pieces by type
        int countCaoCao = 0;     // 2x2
        int countGuanYu = 0;     // 2x1
        int countGenerals = 0;   // 1x2
        int countSoldiers = 0;   // 1x1
        int countEmptyCells = 0; // Empty spaces

        for (Map.Entry<Integer, List<int[]>> entry : positions.entrySet()) {
            int pid = entry.getKey();
            List<int[]> cells = entry.getValue();

            if (pid == 0) {
                countEmptyCells = cells.size();
            } else if (pid == 1) {
                countCaoCao++;
            } else if (pid == 2) {
                countGuanYu++;
            } else if (pid >= 3 && pid <= 6) {
                countGenerals++;
            } else if (pid >= 7 && pid <= 10) {
                countSoldiers++;
            }
        }

        System.out.println("Board Analysis:");
        System.out.println("- Cao Cao (2x2): " + countCaoCao);
        System.out.println("- Guan Yu (2x1): " + countGuanYu);
        System.out.println("- Generals (1x2): " + countGenerals);
        System.out.println("- Soldiers (1x1): " + countSoldiers);
        System.out.println("- Empty cells: " + countEmptyCells);

        System.out.println("\nSearch Space Analysis:");

        // Theoretical max states without optimization
        // This is a very rough estimate since not all pieces can occupy all positions
        long theoreticalMax = factorial(ROWS * COLS) /
                (factorial(4) * factorial(2) * factorial(2) *
                        factorial(4) * factorial(countEmptyCells));

        System.out.println("- Theoretical maximum states (without optimization): ~" + theoreticalMax);

        // Reduced states with piece equivalence
        long reducedStates = theoreticalMax /
                (factorial(countGenerals) * factorial(countSoldiers));

        System.out.println("- Estimated states with piece equivalence: ~" + reducedStates);
        System.out.println("- Reduction factor: ~" + (theoreticalMax / reducedStates) + "x");
    }

    /**
     * Simple factorial calculation (for small numbers only)
     */
    private static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * Collect positions of all pieces
     */
    private static Map<Integer, List<int[]>> getPositions(int[][] board) {
        Map<Integer, List<int[]>> positions = new HashMap<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int pid = board[r][c];
                positions.computeIfAbsent(pid, k -> new ArrayList<>()).add(new int[]{r, c});
            }
        }
        return positions;
    }

    /**
     * Example usage demonstrating optimization techniques
     */
    public static void main(String[] args) {
        // Example board
        int[][] exampleBoard = {
                {3, 1, 1, 4},
                {3, 1, 1, 4},
                {5, 2, 2, 6},
                {5, 7, 8, 6},
                {9, 0, 0, 10}
        };

        System.out.println("Huarong Road Puzzle Optimizations\n");

        // Compare different hashing strategies
        compareHashingStrategies();

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Analyze search space
        analyzeSearchSpace(exampleBoard);

        System.out.println("\n" + "-".repeat(50) + "\n");

        System.out.println("Additional Optimization Techniques:");
        System.out.println("1. Use bidirectional BFS for even faster solving");
        System.out.println("2. Implement A* search with heuristics to prioritize promising moves");
        System.out.println("3. Use pattern databases to pre-compute partial solutions");
        System.out.println("4. Implement move ordering to try more promising moves first");
        System.out.println("5. Parallel processing to explore multiple branches simultaneously");
    }
}