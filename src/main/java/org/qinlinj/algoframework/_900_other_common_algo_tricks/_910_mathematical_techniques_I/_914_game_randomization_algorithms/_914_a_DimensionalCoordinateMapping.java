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
