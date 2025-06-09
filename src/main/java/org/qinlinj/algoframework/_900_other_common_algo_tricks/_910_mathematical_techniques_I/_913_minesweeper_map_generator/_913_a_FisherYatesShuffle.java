package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._913_minesweeper_map_generator;

/**
 * FISHER-YATES SHUFFLE ALGORITHM FOR MINESWEEPER GENERATION
 * <p>
 * This class implements the Fisher-Yates shuffle algorithm to generate a uniform random
 * distribution of mines in a minesweeper game board. The algorithm ensures that every
 * cell has an equal probability of containing a mine.
 * <p>
 * Key Concepts:
 * 1. Fisher-Yates Shuffle: A classic algorithm for generating random permutations
 * - Creates all possible positions (width × height)
 * - Shuffles them randomly using Fisher-Yates algorithm
 * - Takes first mineCount positions as mine locations
 * <p>
 * 2. Uniform Random Distribution: Every cell has probability P(x) = mineCount/(width×height)
 * <p>
 * 3. Algorithm Steps:
 * - Generate all possible coordinates
 * - Apply Fisher-Yates shuffle to randomize order
 * - Select first mineCount positions as mines
 * <p>
 * 4. Trade-offs:
 * - Time Complexity: O(width × height) - must process all positions
 * - Space Complexity: O(width × height) - stores all positions
 * - Advantage: Simple and guaranteed uniform distribution
 * - Disadvantage: High memory usage for large boards
 * <p>
 * Applications: Small to medium sized minesweeper boards where memory is not a constraint
 */

public class _913_a_FisherYatesShuffle {
    private java.util.Random random;

    public _913_a_FisherYatesShuffle() {
        this.random = new java.util.Random();
    }

    public _913_a_FisherYatesShuffle(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Generates mine positions using Fisher-Yates shuffle algorithm
     *
     * @param width     board width
     * @param height    board height
     * @param mineCount number of mines to place
     * @return list of mine positions
     */
    public java.util.List<Position> generateMinePositions(int width, int height, int mineCount) {
        if (mineCount > width * height) {
            throw new IllegalArgumentException("Mine count cannot exceed total cells");
        }
        if (mineCount < 0) {
            throw new IllegalArgumentException("Mine count cannot be negative");
        }

        // Step 1: Create all possible positions
        java.util.List<Position> allPositions = new java.util.ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                allPositions.add(new Position(x, y));
            }
        }

        // Step 2: Apply Fisher-Yates shuffle
        fisherYatesShuffle(allPositions);

        // Step 3: Take first mineCount positions
        return allPositions.subList(0, mineCount);
    }

    /**
     * Fisher-Yates shuffle implementation
     * Randomly permutes the given list in-place
     */
    private void fisherYatesShuffle(java.util.List<Position> list) {
        // Start from the last element and work backwards
        for (int i = list.size() - 1; i > 0; i--) {
            // Generate random index j where 0 <= j <= i
            int j = random.nextInt(i + 1);

            // Swap elements at positions i and j
            Position temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    /**
     * Creates a visual representation of the minesweeper board
     */
    public char[][] createBoard(int width, int height, java.util.List<Position> minePositions) {
        char[][] board = new char[height][width];

        // Initialize with empty cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                board[y][x] = '.';
            }
        }

        // Place mines
        for (Position mine : minePositions) {
            board[mine.y][mine.x] = '*';
        }

        return board;
    }


    /**
     * Prints the board in a readable format
     */
    public void printBoard(char[][] board) {
        System.out.println("Minesweeper Board:");
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Represents a position on the minesweeper board
     */
    public static class Position {
        public final int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(x, y);
        }
    }

}
