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

    public static void main(String[] args) {
        _913_a_FisherYatesShuffle generator = new _913_a_FisherYatesShuffle(42); // Fixed seed for reproducibility

        // Test with small board for demonstration
        System.out.println("=== Small Board Example ===");
        int width = 4, height = 3, mineCount = 3;

        generator.demonstrateShuffle(width, height, mineCount);

        java.util.List<Position> mines = generator.generateMinePositions(width, height, mineCount);
        char[][] board = generator.createBoard(width, height, mines);
        generator.printBoard(board);

        // Analysis
        generator.analyzeComplexity(width, height, mineCount);

        // Test with different board sizes
        System.out.println("=== Different Board Sizes ===");
        int[][] testCases = {
                {5, 5, 5},      // Small board
                {10, 10, 15},   // Medium board
                {20, 15, 50},   // Larger board
        };

        for (int[] testCase : testCases) {
            width = testCase[0];
            height = testCase[1];
            mineCount = testCase[2];

            System.out.printf("Board %dx%d with %d mines:\n", width, height, mineCount);

            long startTime = System.nanoTime();
            mines = generator.generateMinePositions(width, height, mineCount);
            long endTime = System.nanoTime();

            System.out.printf("Generated in %.2f ms\n", (endTime - startTime) / 1_000_000.0);
            System.out.printf("Memory used: %d positions\n", width * height);
            System.out.println("First 5 mines: " + mines.subList(0, Math.min(5, mines.size())));
            System.out.println();
        }

        // Edge cases
        System.out.println("=== Edge Cases ===");

        // Minimum mines
        mines = generator.generateMinePositions(3, 3, 0);
        System.out.println("No mines: " + mines);

        // Maximum mines
        mines = generator.generateMinePositions(3, 3, 9);
        System.out.println("All mines: " + mines);

        // Single mine
        mines = generator.generateMinePositions(3, 3, 1);
        System.out.println("Single mine: " + mines);

        System.out.println("\n=== Key Takeaways ===");
        System.out.println("1. Fisher-Yates provides perfect uniform distribution");
        System.out.println("2. Simple to implement and understand");
        System.out.println("3. Suitable for small to medium boards");
        System.out.println("4. Memory usage grows with board size, not mine count");
        System.out.println("5. Foundation for more advanced sampling algorithms");
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
     * Demonstrates the shuffle process step by step
     */
    public void demonstrateShuffle(int width, int height, int mineCount) {
        System.out.println("=== Fisher-Yates Shuffle Demonstration ===");
        System.out.printf("Board: %dx%d, Mines: %d\n", width, height, mineCount);

        // Create all positions
        java.util.List<Position> positions = new java.util.ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                positions.add(new Position(x, y));
            }
        }

        System.out.println("All positions: " + positions);

        // Show shuffle process for small example
        if (positions.size() <= 12) {
            System.out.println("\nShuffle steps:");
            for (int i = positions.size() - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                System.out.printf("Step %d: Swap position %d with position %d (%s <-> %s)\n",
                        positions.size() - i, i, j, positions.get(i), positions.get(j));

                // Perform swap
                Position temp = positions.get(i);
                positions.set(i, positions.get(j));
                positions.set(j, temp);
            }
        } else {
            fisherYatesShuffle(positions);
        }

        System.out.println("After shuffle: " + positions);
        System.out.println("Selected mines: " + positions.subList(0, mineCount));
        System.out.println();
    }

    /**
     * Analyzes the complexity and properties of the algorithm
     */
    public void analyzeComplexity(int width, int height, int mineCount) {
        System.out.println("=== Algorithm Analysis ===");
        System.out.printf("Board size: %d × %d = %d cells\n", width, height, width * height);
        System.out.printf("Mine count: %d\n", mineCount);
        System.out.printf("Mine probability per cell: %.4f\n", (double) mineCount / (width * height));

        System.out.println("\nComplexity Analysis:");
        System.out.println("Time Complexity: O(width × height)");
        System.out.println("- Must create and shuffle all positions");
        System.out.println("- Fisher-Yates shuffle takes O(n) time");

        System.out.println("Space Complexity: O(width × height)");
        System.out.println("- Must store all possible positions");
        System.out.println("- Not suitable for very large boards");

        System.out.println("\nAdvantages:");
        System.out.println("- Simple and intuitive implementation");
        System.out.println("- Guaranteed uniform random distribution");
        System.out.println("- Well-established algorithm with proven properties");

        System.out.println("Disadvantages:");
        System.out.println("- High memory usage for large boards");
        System.out.println("- Must process all positions even if few mines needed");
        System.out.println();
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