package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._913_minesweeper_map_generator;

/**
 * RESERVOIR SAMPLING ALGORITHM FOR MINESWEEPER GENERATION
 * <p>
 * This class implements the Reservoir Sampling algorithm to generate mine positions
 * for minesweeper with optimal space complexity, especially suitable for large boards.
 * <p>
 * Key Concepts:
 * 1. Reservoir Sampling: Algorithm for selecting k random samples from a stream of n items
 * where n is very large or unknown, using only O(k) space complexity
 * <p>
 * 2. Algorithm Steps:
 * - Fill reservoir with first k items
 * - For each subsequent item i (i > k):
 * * Generate random number r in range [0, i]
 * * If r < k, replace reservoir[r] with current item
 * <p>
 * 3. Mathematical Proof of Uniformity:
 * - Each item has probability k/n of being selected
 * - Probability that item i is in final reservoir = k/n
 * - Maintains uniform distribution throughout the process
 * <p>
 * 4. Advantages:
 * - Space Complexity: O(mineCount) instead of O(width × height)
 * - Works with arbitrarily large boards
 * - Still maintains uniform random distribution
 * - Processes positions in streaming fashion
 * <p>
 * 5. Trade-offs:
 * - Time Complexity: O(width × height) - must examine all positions
 * - More complex than Fisher-Yates shuffle
 * - Requires understanding of probabilistic algorithms
 * <p>
 * Applications: Large minesweeper boards where memory efficiency is critical
 */

public class _913_b_ReservoirSampling {
    private java.util.Random random;

    public _913_b_ReservoirSampling() {
        this.random = new java.util.Random();
    }

    public _913_b_ReservoirSampling(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Generates mine positions using Reservoir Sampling algorithm
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
        if (mineCount == 0) {
            return new java.util.ArrayList<>();
        }

        // Reservoir to store selected mine positions
        Position[] reservoir = new Position[mineCount];
        int totalCells = width * height;

        // Process each position as a stream
        for (int i = 0; i < totalCells; i++) {
            // Convert 1D index to 2D coordinates
            int x = i % width;
            int y = i / width;
            Position currentPos = new Position(x, y);

            if (i < mineCount) {
                // Fill reservoir with first mineCount positions
                reservoir[i] = currentPos;
            } else {
                // For subsequent positions, replace with probability mineCount/(i+1)
                int randomIndex = random.nextInt(i + 1);
                if (randomIndex < mineCount) {
                    reservoir[randomIndex] = currentPos;
                }
            }
        }

        return java.util.Arrays.asList(reservoir);
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
