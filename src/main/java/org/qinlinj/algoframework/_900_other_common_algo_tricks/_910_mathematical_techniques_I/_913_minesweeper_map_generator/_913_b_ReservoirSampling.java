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

    public void demonstrateReservoirSampling(int width, int height, int mineCount) {
        System.out.println("=== Reservoir Sampling Demonstration ===");
        System.out.printf("Board: %dx%d, Mines: %d\n", width, height, mineCount);

        if (width * height > 20) {
            System.out.println("Board too large for step-by-step demonstration");
            return;
        }

        Position[] reservoir = new Position[mineCount];
        int totalCells = width * height;

        System.out.println("\nReservoir sampling steps:");

        for (int i = 0; i < totalCells; i++) {
            int x = i % width;
            int y = i / width;
            Position currentPos = new Position(x, y);

            if (i < mineCount) {
                reservoir[i] = currentPos;
                System.out.printf("Step %d: Fill reservoir[%d] = %s\n", i + 1, i, currentPos);
            } else {
                int randomIndex = random.nextInt(i + 1);
                System.out.printf("Step %d: Process %s, random=%d/%d", i + 1, currentPos, randomIndex, i + 1);

                if (randomIndex < mineCount) {
                    Position replaced = reservoir[randomIndex];
                    reservoir[randomIndex] = currentPos;
                    System.out.printf(" -> Replace reservoir[%d]: %s -> %s", randomIndex, replaced, currentPos);
                } else {
                    System.out.print(" -> Keep current reservoir");
                }
                System.out.println();
            }

            System.out.printf("   Reservoir: %s\n", java.util.Arrays.toString(reservoir));
        }

        System.out.printf("Final mines: %s\n\n", java.util.Arrays.asList(reservoir));
    }

    /**
     * Explains the mathematical foundation of reservoir sampling
     */
    public void explainMathematicalProof(int totalItems, int sampleSize) {
        System.out.println("=== Mathematical Proof of Uniformity ===");
        System.out.printf("Total items: %d, Sample size: %d\n", totalItems, sampleSize);

        System.out.println("\nWhy every item has equal probability k/n:");
        System.out.println("1. For first k items: probability = 1 (always selected)");
        System.out.println("2. For item i (i > k):");
        System.out.println("   - Probability of being selected = k/(i+1)");
        System.out.println("   - Probability of staying selected = product of not being replaced");

        // Demonstrate probability calculation for specific positions
        if (totalItems <= 10 && sampleSize <= 5) {
            System.out.println("\nProbability calculation for each item:");
            for (int item = 0; item < totalItems; item++) {
                double probability = calculateSelectionProbability(item, totalItems, sampleSize);
                System.out.printf("Item %d: P = %.6f (expected: %.6f)\n",
                        item, probability, (double) sampleSize / totalItems);
            }
        }

        System.out.println("\nKey insight: Each replacement maintains the invariant that");
        System.out.println("every seen item has equal probability of being in the reservoir.");
        System.out.println();
    }

    /**
     * Calculates the exact probability of an item being selected
     */
    private double calculateSelectionProbability(int itemIndex, int totalItems, int sampleSize) {
        if (itemIndex >= totalItems || sampleSize > totalItems) return 0.0;

        if (itemIndex < sampleSize) {
            // Item is initially in reservoir, calculate survival probability
            double survivalProb = 1.0;
            for (int i = sampleSize; i < totalItems; i++) {
                // Probability of not being replaced at step i
                survivalProb *= (1.0 - (1.0 / (i + 1)));
            }
            return survivalProb;
        } else {
            // Item enters reservoir at position itemIndex, then must survive
            double entryProb = (double) sampleSize / (itemIndex + 1);
            double survivalProb = 1.0;
            for (int i = itemIndex + 1; i < totalItems; i++) {
                survivalProb *= (1.0 - (1.0 / (i + 1)));
            }
            return entryProb * survivalProb;
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
