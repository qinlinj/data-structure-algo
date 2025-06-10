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
}
