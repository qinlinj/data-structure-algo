package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._914_game_randomization_algorithms;

/**
 * 2D TO 1D COORDINATE MAPPING FOR GAME BOARDS
 * <p>
 * This class demonstrates the fundamental technique of converting 2D game board coordinates
 * to 1D array indices and vice versa. This "dimensional reduction" approach simplifies
 * random element selection in 2D games like Minesweeper, Bomberman, etc.
 * <p>
 * Key Concepts:
 * 1. Dimensional Reduction: Convert 2D matrix (m×n) to 1D array (m*n length)
 * - Simplifies random selection: pick random index in [0, m*n)
 * - Avoids complex 2D coordinate generation
 * <p>
 * 2. Coordinate Transformation Formulas:
 * - 2D to 1D: index = x * n + y (where n is number of columns)
 * - 1D to 2D: x = index / n, y = index % n
 * <p>
 * 3. Applications in Game Development:
 * - Minesweeper: Random mine placement
 * - Bomberman: Random obstacle generation
 * - Puzzle games: Random tile arrangement
 * - RPG games: Random item/enemy placement
 * <p>
 * 4. Extension to Higher Dimensions:
 * - 3D to 1D: index = x * (n*p) + y * p + z
 * - General formula for n-dimensional space
 * <p>
 * 5. Memory and Performance Benefits:
 * - Single array vs 2D array allocation
 * - Better cache locality
 * - Simplified index calculations
 * <p>
 * Time Complexity: O(1) for coordinate transformations
 * Space Complexity: O(m*n) for board storage
 */

public class _914_a_DimensionalCoordinateMapping {
    /**
     * Demonstrates coordinate mapping with practical examples
     */
    public static void demonstrateCoordinateMapping() {
        System.out.println("=== 2D Coordinate Mapping Demonstration ===");

        GameBoard board = new GameBoard(4, 3); // 4x3 board
        System.out.printf("Created %dx%d board with %d total cells\n",
                board.getWidth(), board.getHeight(), board.getTotalCells());

        // Show coordinate to index mapping
        System.out.println("\n2D Coordinates to 1D Index mapping:");
        System.out.println("Position -> Index");
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                int index = board.encode(x, y);
                System.out.printf("(%d,%d) -> %d\n", x, y, index);
            }
        }

        // Show index to coordinate mapping
        System.out.println("\n1D Index to 2D Coordinates mapping:");
        System.out.println("Index -> Position");
        for (int i = 0; i < board.getTotalCells(); i++) {
            Position pos = board.decode(i);
            System.out.printf("%d -> %s\n", i, pos);
        }

        // Demonstrate setting values
        System.out.println("\nSetting some cells using both methods:");
        board.setCell(1, 2, true);  // Using 2D coordinates
        board.setCell(7, true);     // Using 1D index (should be position (2,1))

        Position pos7 = board.decode(7);
        System.out.printf("Set cell (1,2) = true using 2D coordinates\n");
        System.out.printf("Set cell at index 7 = true, which corresponds to position %s\n", pos7);

        board.printBoard();
    }

    /**
     * Demonstrates 3D coordinate mapping
     */
    public static void demonstrate3DMapping() {
        System.out.println("\n=== 3D Coordinate Mapping Demonstration ===");

        GameBoard3D board3D = new GameBoard3D(2, 3, 4); // 2x3x4 board
        System.out.printf("Created 2x3x4 board with %d total cells\n", 2 * 3 * 4);

        // Show some 3D mappings
        System.out.println("\n3D Coordinates to 1D Index mapping (sample):");
        int[][] testCoords = {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {1, 0, 0}, {1, 2, 3}};

        for (int[] coord : testCoords) {
            int index = board3D.encode(coord[0], coord[1], coord[2]);
            Position3D decoded = board3D.decode(index);
            System.out.printf("(%d,%d,%d) -> %d -> %s\n",
                    coord[0], coord[1], coord[2], index, decoded);
        }
    }

    /**
     * Performance comparison between 2D array and 1D array access
     */
    public static void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");

        int width = 1000, height = 1000;
        int iterations = 1000000;

        // 2D array approach
        boolean[][] board2D = new boolean[width][height];
        GameBoard board1D = new GameBoard(width, height);

        java.util.Random random = new java.util.Random(42);

        // Test 2D array access
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            board2D[x][y] = !board2D[x][y];
        }
        long time2D = System.nanoTime() - startTime;

        // Test 1D array access
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int index = board1D.encode(x, y);
            board1D.setCell(index, !board1D.getCell(index));
        }
        long time1D = System.nanoTime() - startTime;

        System.out.printf("2D Array access: %.2f ms\n", time2D / 1_000_000.0);
        System.out.printf("1D Array access: %.2f ms\n", time1D / 1_000_000.0);
        System.out.printf("Performance ratio: %.2fx\n", (double) time2D / time1D);
    }

    /**
     * Practical game examples using coordinate mapping
     */
    public static void gameExamples() {
        System.out.println("\n=== Practical Game Examples ===");

        // Minesweeper example
        System.out.println("Minesweeper Mine Placement:");
        GameBoard minesweeper = new GameBoard(8, 8);
        java.util.Random random = new java.util.Random(42);

        // Place 10 random mines
        int mineCount = 10;
        java.util.Set<Integer> mineIndices = new java.util.HashSet<>();

        while (mineIndices.size() < mineCount) {
            int randomIndex = random.nextInt(minesweeper.getTotalCells());
            mineIndices.add(randomIndex);
        }

        for (int index : mineIndices) {
            minesweeper.setCell(index, true);
            Position pos = minesweeper.decode(index);
            System.out.printf("Mine placed at index %d -> position %s\n", index, pos);
        }

        System.out.println("\nMinesweeper board:");
        minesweeper.printBoard();

        // Bomberman example
        System.out.println("\nBomberman Obstacle Placement:");
        GameBoard bomberman = new GameBoard(6, 6);

        // Place obstacles randomly but avoid corners and center
        for (int i = 0; i < bomberman.getTotalCells(); i++) {
            Position pos = bomberman.decode(i);
            // Skip corners and center, place obstacles randomly elsewhere
            if (!isCornerOrCenter(pos, 6, 6) && random.nextDouble() < 0.3) {
                bomberman.setCell(i, true);
            }
        }

        bomberman.printBoard();
    }

    /**
     * Represents a 2D position
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

    /**
     * Represents a 3D position for extension examples
     */
    public static class Position3D {
        public final int x, y, z;

        public Position3D(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + z + ")";
        }
    }

    /**
     * Game board using 1D array representation
     */
    public static class GameBoard {
        private final int width, height;
        private final boolean[] board; // true = mine/obstacle, false = empty

        public GameBoard(int width, int height) {
            this.width = width;
            this.height = height;
            this.board = new boolean[width * height];
        }

        /**
         * Converts 2D coordinates to 1D array index
         */
        public int encode(int x, int y) {
            if (!isValidPosition(x, y)) {
                throw new IllegalArgumentException("Invalid position: (" + x + "," + y + ")");
            }
            return x * height + y;
        }

        /**
         * Converts 1D array index to 2D coordinates
         */
        public Position decode(int index) {
            if (index < 0 || index >= board.length) {
                throw new IllegalArgumentException("Invalid index: " + index);
            }
            int x = index / height;
            int y = index % height;
            return new Position(x, y);
        }

        /**
         * Sets a cell value using 2D coordinates
         */
        public void setCell(int x, int y, boolean value) {
            int index = encode(x, y);
            board[index] = value;
        }

        /**
         * Gets a cell value using 2D coordinates
         */
        public boolean getCell(int x, int y) {
            int index = encode(x, y);
            return board[index];
        }

        /**
         * Sets a cell value using 1D index
         */
        public void setCell(int index, boolean value) {
            if (index >= 0 && index < board.length) {
                board[index] = value;
            }
        }

        /**
         * Gets a cell value using 1D index
         */
        public boolean getCell(int index) {
            return index >= 0 && index < board.length && board[index];
        }

        /**
         * Checks if 2D position is valid
         */
        public boolean isValidPosition(int x, int y) {
            return x >= 0 && x < width && y >= 0 && y < height;
        }

        /**
         * Gets total number of cells
         */
        public int getTotalCells() {
            return width * height;
        }

        /**
         * Gets board dimensions
         */
        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        /**
         * Clears the entire board
         */
        public void clear() {
            java.util.Arrays.fill(board, false);
        }

        /**
         * Prints the board in 2D format
         */
        public void printBoard() {
            System.out.printf("Board %dx%d:\n", width, height);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    System.out.print(getCell(x, y) ? "* " : ". ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Extended 3D coordinate mapping for advanced games
     */
    public static class GameBoard3D {
        private final int width, height, depth;
        private final boolean[] board;

        public GameBoard3D(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            this.board = new boolean[width * height * depth];
        }

        /**
         * Converts 3D coordinates to 1D index
         */
        public int encode(int x, int y, int z) {
            return x * (height * depth) + y * depth + z;
        }

        /**
         * Converts 1D index to 3D coordinates
         */
        public Position3D decode(int index) {
            int x = index / (height * depth);
            int y = (index % (height * depth)) / depth;
            int z = index % depth;
            return new Position3D(x, y, z);
        }

        public void setCell(int x, int y, int z, boolean value) {
            int index = encode(x, y, z);
            if (index >= 0 && index < board.length) {
                board[index] = value;
            }
        }

        public boolean getCell(int x, int y, int z) {
            int index = encode(x, y, z);
            return index >= 0 && index < board.length && board[index];
        }
    }
}
