package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._913_minesweeper_map_generator;

/**
 * MONTE CARLO VERIFICATION FOR RANDOM ALGORITHM UNIFORMITY
 * <p>
 * This class implements Monte Carlo simulation methods to verify that random algorithms
 * for minesweeper generation produce truly uniform distributions. It provides statistical
 * analysis tools to validate the correctness of sampling algorithms.
 * <p>
 * Key Concepts:
 * 1. Monte Carlo Method: Statistical technique using random sampling to verify properties
 * - Run algorithm many times with same parameters
 * - Collect statistics on outcomes
 * - Compare against theoretical expectations
 * <p>
 * 2. Uniformity Testing:
 * - Each cell should be selected with probability p = mineCount / (width × height)
 * - Statistical tests verify if observed frequencies match expected frequencies
 * - Chi-square test can quantify deviation from uniform distribution
 * <p>
 * 3. Statistical Measures:
 * - Expected frequency: trials × probability
 * - Standard deviation: sqrt(n × p × (1-p))
 * - Confidence intervals for randomness assessment
 * - Chi-square goodness of fit test
 * <p>
 * 4. Verification Process:
 * - Run algorithm multiple times (e.g., 10,000 trials)
 * - Count how often each cell is selected as mine
 * - Calculate statistical measures of uniformity
 * - Visual representation of distribution patterns
 * <p>
 * Applications: Algorithm validation, quality assurance, educational demonstration
 */
public class _913_c_MonteCarloVerification {

    private java.util.Random random;

    public _913_c_MonteCarloVerification() {
        this.random = new java.util.Random();
    }

    public _913_c_MonteCarloVerification(long seed) {
        this.random = new java.util.Random(seed);
    }

    public static void main(String[] args) {
        _913_c_MonteCarloVerification verifier = new _913_c_MonteCarloVerification(42);

        // Create test generators
        MineGenerator fisherYates = (w, h, m) -> {
            // Simplified Fisher-Yates for testing
            java.util.List<Position> positions = new java.util.ArrayList<>();
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    positions.add(new Position(x, y));
                }
            }
            java.util.Collections.shuffle(positions);
            return positions.subList(0, m);
        };

        MineGenerator reservoir1 = (w, h, m) -> {
            // Simplified Reservoir Sampling for testing
            java.util.Random rand = new java.util.Random();
            Position[] reservoir = new Position[m];
            int totalCells = w * h;

            for (int i = 0; i < totalCells; i++) {
                int x = i % w;
                int y = i / w;
                Position pos = new Position(x, y);

                if (i < m) {
                    reservoir[i] = pos;
                } else {
                    int randomIndex = rand.nextInt(i + 1);
                    if (randomIndex < m) {
                        reservoir[randomIndex] = pos;
                    }
                }
            }
            return java.util.Arrays.asList(reservoir);
        };

        // Single algorithm test
        System.out.println("=== Testing Fisher-Yates Algorithm ===");
        SimulationResults results = verifier.runSimulation(fisherYates, 5, 4, 4, 1000);
        verifier.analyzeResults(results);
        verifier.printFrequencyHeatmap(results);

        // Algorithm comparison
        java.util.Map<String, MineGenerator> algorithms = new java.util.HashMap<>();
        algorithms.put("Fisher-Yates", fisherYates);
        algorithms.put("Reservoir Sampling", reservoir1);

        verifier.compareAlgorithms(algorithms, 6, 6, 6, 5000);

        // Large scale test
        System.out.println("\n=== Large Scale Test ===");
        results = verifier.runSimulation(reservoir1, 10, 10, 15, 10000);
        verifier.analyzeResults(results);

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. Monte Carlo method validates algorithm uniformity");
        System.out.println("2. Chi-square test quantifies deviation from uniform distribution");
        System.out.println("3. More trials = more reliable statistical analysis");
        System.out.println("4. Visual heatmaps reveal distribution patterns");
        System.out.println("5. Both Fisher-Yates and Reservoir Sampling should show uniformity");

        System.out.println("\n=== Statistical Interpretation ===");
        System.out.println("• Expected frequency = trials × (mines/cells)");
        System.out.println("• Standard deviation shows expected variation");
        System.out.println("• 95% of cells should be within 2σ of expected");
        System.out.println("• Uniformity score < 0.1 indicates excellent randomness");
        System.out.println("• Chi-square test confirms goodness of fit");
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
     * Calculates chi-square statistic for goodness of fit test
     */
    private double calculateChiSquare(int[][] frequencies, double expected) {
        double chiSquare = 0.0;
        for (int y = 0; y < frequencies.length; y++) {
            for (int x = 0; x < frequencies[0].length; x++) {
                double deviation = frequencies[y][x] - expected;
                chiSquare += (deviation * deviation) / expected;
            }
        }
        return chiSquare;
    }

    /**
     * Calculates uniformity score (0 = perfect uniform, higher = less uniform)
     */
    private double calculateUniformityScore(int[][] frequencies, double expected) {
        double sumSquaredDeviations = 0.0;
        int totalCells = frequencies.length * frequencies[0].length;

        for (int y = 0; y < frequencies.length; y++) {
            for (int x = 0; x < frequencies[0].length; x++) {
                double deviation = frequencies[y][x] - expected;
                sumSquaredDeviations += deviation * deviation;
            }
        }

        return Math.sqrt(sumSquaredDeviations / totalCells) / expected;
    }

    /**
     * Prints detailed analysis of simulation results
     */
    public void analyzeResults(SimulationResults results) {
        System.out.println("=== Monte Carlo Analysis Results ===");
        System.out.printf("Trials: %,d\n", results.trials);
        System.out.printf("Board: %dx%d (%d cells)\n", results.width, results.height,
                results.width * results.height);
        System.out.printf("Mines per trial: %d\n", results.mineCount);

        System.out.println("\n=== Statistical Analysis ===");
        System.out.printf("Expected frequency per cell: %.2f\n", results.expectedFrequency);
        System.out.printf("Theoretical standard deviation: %.2f\n", results.standardDeviation);
        System.out.printf("Chi-square statistic: %.2f\n", results.chiSquareStatistic);
        System.out.printf("Uniformity score: %.4f (lower is better)\n", results.uniformityScore);

        // Calculate actual statistics from frequencies
        int[] flatFreqs = flattenFrequencies(results.frequencies);
        double actualMean = calculateMean(flatFreqs);
        double actualStdDev = calculateStandardDeviation(flatFreqs, actualMean);
        int minFreq = java.util.Arrays.stream(flatFreqs).min().orElse(0);
        int maxFreq = java.util.Arrays.stream(flatFreqs).max().orElse(0);

        System.out.println("\n=== Observed Statistics ===");
        System.out.printf("Actual mean frequency: %.2f\n", actualMean);
        System.out.printf("Actual standard deviation: %.2f\n", actualStdDev);
        System.out.printf("Frequency range: [%d, %d]\n", minFreq, maxFreq);
        System.out.printf("Range span: %d (%.1f%% of mean)\n",
                maxFreq - minFreq, 100.0 * (maxFreq - minFreq) / actualMean);

        // Uniformity assessment
        System.out.println("\n=== Uniformity Assessment ===");
        double tolerance = 2.0 * results.standardDeviation;
        int cellsInRange = 0;
        int totalCells = results.width * results.height;

        for (int freq : flatFreqs) {
            if (Math.abs(freq - results.expectedFrequency) <= tolerance) {
                cellsInRange++;
            }
        }

        double percentageInRange = 100.0 * cellsInRange / totalCells;
        System.out.printf("Cells within 2σ of expected: %d/%d (%.1f%%)\n",
                cellsInRange, totalCells, percentageInRange);
        System.out.printf("Expected percentage within 2σ: ~95%%\n");

        // Verdict
        String verdict;
        if (percentageInRange >= 90 && results.uniformityScore < 0.1) {
            verdict = "EXCELLENT - Very uniform distribution";
        } else if (percentageInRange >= 85 && results.uniformityScore < 0.15) {
            verdict = "GOOD - Reasonably uniform distribution";
        } else if (percentageInRange >= 75 && results.uniformityScore < 0.25) {
            verdict = "ACCEPTABLE - Somewhat uniform distribution";
        } else {
            verdict = "POOR - Non-uniform distribution detected";
        }

        System.out.printf("Verdict: %s\n", verdict);
    }

    /**
     * Creates a visual heatmap of the frequency distribution
     */
    public void printFrequencyHeatmap(SimulationResults results) {
        System.out.println("\n=== Frequency Heatmap ===");

        if (results.width > 20 || results.height > 20) {
            System.out.println("Board too large for heatmap display");
            return;
        }

        int[][] freqs = results.frequencies;
        double expected = results.expectedFrequency;

        // Print header
        System.out.print("    ");
        for (int x = 0; x < results.width; x++) {
            System.out.printf("%4d", x);
        }
        System.out.println();

        // Print rows
        for (int y = 0; y < results.height; y++) {
            System.out.printf("%2d: ", y);
            for (int x = 0; x < results.width; x++) {
                int freq = freqs[y][x];
                double deviation = Math.abs(freq - expected);

                // Color coding based on deviation
                String symbol;
                if (deviation <= expected * 0.1) {
                    symbol = "█"; // Very close to expected
                } else if (deviation <= expected * 0.2) {
                    symbol = "▓"; // Close to expected
                } else if (deviation <= expected * 0.3) {
                    symbol = "▒"; // Moderate deviation
                } else {
                    symbol = "░"; // High deviation
                }

                System.out.printf("%3s ", symbol);
            }
            System.out.println();
        }

        System.out.println("\nLegend:");
        System.out.println("█ = Within 10% of expected");
        System.out.println("▓ = Within 20% of expected");
        System.out.println("▒ = Within 30% of expected");
        System.out.println("░ = More than 30% deviation");
        System.out.printf("Expected frequency: %.1f\n", expected);
    }

    /**
     * Compares multiple algorithms side by side
     */
    public void compareAlgorithms(java.util.Map<String, MineGenerator> algorithms,
                                  int width, int height, int mineCount, int trials) {
        System.out.println("=== Algorithm Comparison ===");

        java.util.Map<String, SimulationResults> results = new java.util.HashMap<>();

        for (java.util.Map.Entry<String, MineGenerator> entry : algorithms.entrySet()) {
            String name = entry.getKey();
            MineGenerator generator = entry.getValue();

            System.out.printf("\nTesting %s...\n", name);
            results.put(name, runSimulation(generator, width, height, mineCount, trials));
        }

        // Comparison table
        System.out.println("\n=== Comparison Summary ===");
        System.out.printf("%-20s %12s %12s %12s\n",
                "Algorithm", "Chi-Square", "Uniformity", "Verdict");
        System.out.println("-".repeat(60));

        for (java.util.Map.Entry<String, SimulationResults> entry : results.entrySet()) {
            String name = entry.getKey();
            SimulationResults result = entry.getValue();

            String verdict = result.uniformityScore < 0.1 ? "Excellent" :
                    result.uniformityScore < 0.15 ? "Good" :
                            result.uniformityScore < 0.25 ? "Acceptable" : "Poor";

            System.out.printf("%-20s %12.2f %12.4f %12s\n",
                    name, result.chiSquareStatistic, result.uniformityScore, verdict);
        }
    }

    // Helper methods
    private int[] flattenFrequencies(int[][] frequencies) {
        int[] flattened = new int[frequencies.length * frequencies[0].length];
        int index = 0;
        for (int y = 0; y < frequencies.length; y++) {
            for (int x = 0; x < frequencies[0].length; x++) {
                flattened[index++] = frequencies[y][x];
            }
        }
        return flattened;
    }

    private double calculateMean(int[] values) {
        return java.util.Arrays.stream(values).average().orElse(0.0);
    }

    private double calculateStandardDeviation(int[] values, double mean) {
        double sumSquaredDiffs = 0.0;
        for (int value : values) {
            double diff = value - mean;
            sumSquaredDiffs += diff * diff;
        }
        return Math.sqrt(sumSquaredDiffs / values.length);
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

    /**
     * Results of Monte Carlo simulation
     */
    public static class SimulationResults {
        public final int trials;
        public final int width, height, mineCount;
        public final int[][] frequencies;
        public final double expectedFrequency;
        public final double standardDeviation;
        public final double chiSquareStatistic;
        public final double uniformityScore;

        public SimulationResults(int trials, int width, int height, int mineCount,
                                 int[][] frequencies, double expectedFreq, double stdDev,
                                 double chiSquare, double uniformity) {
            this.trials = trials;
            this.width = width;
            this.height = height;
            this.mineCount = mineCount;
            this.frequencies = frequencies;
            this.expectedFrequency = expectedFreq;
            this.standardDeviation = stdDev;
            this.chiSquareStatistic = chiSquare;
            this.uniformityScore = uniformity;
        }
    }
}