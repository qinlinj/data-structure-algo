package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._913_minesweeper_map_generator;

public class _913_c_MonteCarloVerification {
    private java.util.Random random;

    public _913_c_MonteCarloVerification() {
        this.random = new java.util.Random();
    }

    public _913_c_MonteCarloVerification(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Runs Monte Carlo simulation to test algorithm uniformity
     */
    public SimulationResults runSimulation(MineGenerator generator, int width, int height,
                                           int mineCount, int trials) {
        System.out.printf("Running Monte Carlo simulation: %d trials\n", trials);
        System.out.printf("Board: %dx%d, Mines: %d\n", width, height, mineCount);

        // Track frequency of each cell being selected
        int[][] frequencies = new int[height][width];

        // Run simulation
        for (int trial = 0; trial < trials; trial++) {
            if (trial % (trials / 10) == 0) {
                System.out.printf("Progress: %.0f%%\n", 100.0 * trial / trials);
            }

            java.util.List<Position> mines = generator.generateMines(width, height, mineCount);
            for (Position mine : mines) {
                frequencies[mine.y][mine.x]++;
            }
        }

        // Calculate statistics
        double expectedFreq = (double) trials * mineCount / (width * height);
        double stdDev = Math.sqrt(trials * expectedFreq / (width * height) *
                (1 - (double) mineCount / (width * height)));

        double chiSquare = calculateChiSquare(frequencies, expectedFreq);
        double uniformityScore = calculateUniformityScore(frequencies, expectedFreq);

        System.out.println("Simulation complete!\n");

        return new SimulationResults(trials, width, height, mineCount, frequencies,
                expectedFreq, stdDev, chiSquare, uniformityScore);
    }

    /**
     * Interface for mine generation algorithms to test
     */
    public interface MineGenerator {
        java.util.List<Position> generateMines(int width, int height, int mineCount);
    }

    /**
     * Position class for consistency
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
