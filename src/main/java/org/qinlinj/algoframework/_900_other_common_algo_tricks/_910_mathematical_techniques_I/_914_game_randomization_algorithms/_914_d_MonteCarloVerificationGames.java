package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._914_game_randomization_algorithms;

/**
 * MONTE CARLO VERIFICATION FOR RANDOM GAME ALGORITHMS
 * <p>
 * This class implements Monte Carlo simulation methods to verify the correctness
 * and uniformity of random algorithms used in game development. It provides
 * statistical validation tools to ensure random algorithms work as expected.
 * <p>
 * Key Concepts:
 * 1. Monte Carlo Method: Statistical technique using repeated random sampling
 * - Run algorithm many times with same parameters
 * - Collect frequency statistics of outcomes
 * - Compare against theoretical expectations
 * <p>
 * 2. Uniformity Testing: Verify each outcome has equal probability
 * - Chi-square goodness of fit test
 * - Standard deviation analysis
 * - Visual distribution analysis
 * <p>
 * 3. Applications:
 * - Validate shuffle algorithms (Fisher-Yates)
 * - Verify sampling algorithms (Reservoir Sampling)
 * - Test random number generators
 * - Quality assurance for game randomness
 * <p>
 * 4. Statistical Measures:
 * - Expected frequency: trials / possible_outcomes
 * - Standard deviation: sqrt(expected * (1 - probability))
 * - Chi-square statistic: Σ((observed - expected)² / expected)
 * - Confidence intervals and p-values
 * <p>
 * 5. Practical Examples:
 * - Calculate π using random points in circle
 * - Verify card shuffle fairness
 * - Test mine placement uniformity
 * - Validate loot drop probabilities
 * <p>
 * 6. LeetCode Judge System: How platforms detect randomness bugs
 * - Run algorithm thousands of times
 * - Statistical analysis of results
 * - Detect non-uniform distributions
 * <p>
 * Time Complexity: O(trials × algorithm_complexity)
 * Space Complexity: O(outcomes) for frequency tracking
 */

public class _914_d_MonteCarloVerificationGames {
    private java.util.Random random;

    public _914_d_MonteCarloVerificationGames() {
        this.random = new java.util.Random();
    }

    public _914_d_MonteCarloVerificationGames(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Classic Monte Carlo example: Estimate π using random points
     */
    public double estimatePi(int trials) {
        System.out.printf("Estimating π using %,d random points\n", trials);

        int pointsInCircle = 0;

        for (int i = 0; i < trials; i++) {
            double x = random.nextDouble() * 2 - 1; // Range [-1, 1]
            double y = random.nextDouble() * 2 - 1; // Range [-1, 1]

            // Check if point is inside unit circle
            if (x * x + y * y <= 1.0) {
                pointsInCircle++;
            }
        }

        // π = 4 * (points in circle / total points)
        double piEstimate = 4.0 * pointsInCircle / trials;
        double actualPi = Math.PI;
        double error = Math.abs(piEstimate - actualPi);

        System.out.printf("Points in circle: %,d / %,d (%.4f%%)\n",
                pointsInCircle, trials, 100.0 * pointsInCircle / trials);
        System.out.printf("Estimated π: %.6f\n", piEstimate);
        System.out.printf("Actual π: %.6f\n", actualPi);
        System.out.printf("Error: %.6f (%.4f%%)\n", error, 100.0 * error / actualPi);

        return piEstimate;
    }

    /**
     * Verifies Fisher-Yates shuffle uniformity
     */
    public void verifyShuffleUniformity(int arraySize, int trials) {
        System.out.printf("=== Verifying Fisher-Yates Shuffle ===\n");
        System.out.printf("Array size: %d, Trials: %,d\n", arraySize, trials);

        // Track frequency of each element at each position
        int[][] positionFrequency = new int[arraySize][arraySize];

        for (int trial = 0; trial < trials; trial++) {
            // Create and shuffle array
            int[] array = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                array[i] = i;
            }
            fisherYatesShuffle(array);

            // Record where each element ended up
            for (int pos = 0; pos < arraySize; pos++) {
                int element = array[pos];
                positionFrequency[element][pos]++;
            }
        }

        // Analyze results
        double expectedFrequency = (double) trials / arraySize;
        double chiSquare = 0.0;
        int maxDeviation = 0;

        System.out.printf("Expected frequency per position: %.1f\n", expectedFrequency);
        System.out.println("\nFrequency matrix (Element -> Position):");
        System.out.print("Elem\\Pos ");
        for (int j = 0; j < arraySize; j++) {
            System.out.printf("%8d", j);
        }
        System.out.println();

        for (int i = 0; i < arraySize; i++) {
            System.out.printf("%8d ", i);
            for (int j = 0; j < arraySize; j++) {
                int observed = positionFrequency[i][j];
                System.out.printf("%8d", observed);

                // Calculate chi-square contribution
                double deviation = observed - expectedFrequency;
                chiSquare += (deviation * deviation) / expectedFrequency;
                maxDeviation = Math.max(maxDeviation, (int) Math.abs(deviation));
            }
            System.out.println();
        }

        System.out.printf("\nChi-square statistic: %.2f\n", chiSquare);
        System.out.printf("Maximum deviation: %d (%.2f%%)\n",
                maxDeviation, 100.0 * maxDeviation / expectedFrequency);

        // Assess uniformity
        if (maxDeviation < expectedFrequency * 0.1) {
            System.out.println("✓ Excellent uniformity - algorithm appears correct!");
        } else if (maxDeviation < expectedFrequency * 0.2) {
            System.out.println("✓ Good uniformity - minor statistical variations");
        } else {
            System.out.println("⚠ Poor uniformity - check algorithm implementation");
        }
    }

    /**
     * Verifies reservoir sampling uniformity
     */
    public void verifyReservoirSamplingUniformity(int totalElements, int sampleSize, int trials) {
        System.out.printf("=== Verifying Reservoir Sampling ===\n");
        System.out.printf("Total elements: %d, Sample size: %d, Trials: %,d\n",
                totalElements, sampleSize, trials);

        int[] selectionCount = new int[totalElements];

        for (int trial = 0; trial < trials; trial++) {
            int[] sample = reservoirSample(totalElements, sampleSize);
            for (int element : sample) {
                selectionCount[element]++;
            }
        }

        // Analyze results
        double expectedFrequency = (double) trials * sampleSize / totalElements;
        double standardDeviation = Math.sqrt(expectedFrequency * (1.0 - (double) sampleSize / totalElements));

        System.out.printf("Expected frequency per element: %.2f\n", expectedFrequency);
        System.out.printf("Theoretical standard deviation: %.2f\n", standardDeviation);

        // Calculate actual statistics
        double sumSquaredDeviations = 0.0;
        int minCount = Integer.MAX_VALUE;
        int maxCount = Integer.MIN_VALUE;

        for (int count : selectionCount) {
            double deviation = count - expectedFrequency;
            sumSquaredDeviations += deviation * deviation;
            minCount = Math.min(minCount, count);
            maxCount = Math.max(maxCount, count);
        }

        double actualStdDev = Math.sqrt(sumSquaredDeviations / totalElements);

        System.out.printf("Actual standard deviation: %.2f\n", actualStdDev);
        System.out.printf("Selection count range: [%d, %d]\n", minCount, maxCount);
        System.out.printf("Range span: %d (%.1f%% of expected)\n",
                maxCount - minCount, 100.0 * (maxCount - minCount) / expectedFrequency);

        // Show frequency distribution for small examples
        if (totalElements <= 20) {
            System.out.println("\nElement selection frequencies:");
            for (int i = 0; i < totalElements; i++) {
                double percentage = 100.0 * selectionCount[i] / (trials * sampleSize);
                System.out.printf("Element %2d: %4d times (%.2f%%)\n",
                        i, selectionCount[i], percentage);
            }
        }

        // Uniformity assessment
        double tolerance = 2.0 * standardDeviation;
        int elementsInRange = 0;
        for (int count : selectionCount) {
            if (Math.abs(count - expectedFrequency) <= tolerance) {
                elementsInRange++;
            }
        }

        double percentageInRange = 100.0 * elementsInRange / totalElements;
        System.out.printf("\nElements within 2σ of expected: %d/%d (%.1f%%)\n",
                elementsInRange, totalElements, percentageInRange);

        if (percentageInRange >= 95) {
            System.out.println("✓ Excellent uniformity - algorithm is working correctly!");
        } else if (percentageInRange >= 85) {
            System.out.println("✓ Good uniformity - acceptable statistical variation");
        } else {
            System.out.println("⚠ Poor uniformity - algorithm may have issues");
        }
    }

    /**
     * Game-specific verification: Minesweeper mine placement
     */
    public void verifyMinesweeperUniformity(int width, int height, int mineCount, int trials) {
        System.out.printf("=== Verifying Minesweeper Mine Placement ===\n");
        System.out.printf("Board: %dx%d, Mines: %d, Trials: %,d\n", width, height, mineCount, trials);

        int totalCells = width * height;
        int[] cellMineCount = new int[totalCells];

        for (int trial = 0; trial < trials; trial++) {
            // Generate mines using reservoir sampling
            int[] minePositions = reservoirSample(totalCells, mineCount);
            for (int position : minePositions) {
                cellMineCount[position]++;
            }
        }

        // Analyze uniformity
        double expectedFrequency = (double) trials * mineCount / totalCells;

        System.out.printf("Expected mine frequency per cell: %.2f\n", expectedFrequency);

        if (width <= 8 && height <= 8) {
            // Show heatmap for small boards
            System.out.println("\nMine frequency heatmap:");
            System.out.print("     ");
            for (int y = 0; y < height; y++) {
                System.out.printf("%5d", y);
            }
            System.out.println();

            for (int x = 0; x < width; x++) {
                System.out.printf("%2d:  ", x);
                for (int y = 0; y < height; y++) {
                    int position = x * height + y;
                    int frequency = cellMineCount[position];
                    double ratio = frequency / expectedFrequency;

                    // Color coding based on deviation
                    String symbol;
                    if (ratio >= 0.9 && ratio <= 1.1) {
                        symbol = "██"; // Very close to expected
                    } else if (ratio >= 0.8 && ratio <= 1.2) {
                        symbol = "▓▓"; // Close to expected
                    } else if (ratio >= 0.7 && ratio <= 1.3) {
                        symbol = "▒▒"; // Moderate deviation
                    } else {
                        symbol = "░░"; // High deviation
                    }

                    System.out.printf("%s", symbol);
                }
                System.out.println();
            }

            System.out.println("\nLegend:");
            System.out.println("██ = Within 10% of expected");
            System.out.println("▓▓ = Within 20% of expected");
            System.out.println("▒▒ = Within 30% of expected");
            System.out.println("░░ = More than 30% deviation");
        }

        // Statistical analysis
        double chiSquare = 0.0;
        for (int frequency : cellMineCount) {
            double deviation = frequency - expectedFrequency;
            chiSquare += (deviation * deviation) / expectedFrequency;
        }

        System.out.printf("Chi-square statistic: %.2f\n", chiSquare);
        System.out.printf("Degrees of freedom: %d\n", totalCells - 1);

        // Critical value for 95% confidence (approximation)
        double criticalValue = totalCells * 1.2; // Rough approximation
        if (chiSquare < criticalValue) {
            System.out.println("✓ Mine placement appears uniform and fair!");
        } else {
            System.out.println("⚠ Mine placement may not be uniform - investigate algorithm");
        }
    }

    /**
     * Card game verification: Deck shuffling fairness
     */
    public void verifyCardShuffling(int trials) {
        System.out.printf("=== Verifying Card Deck Shuffling ===\n");
        System.out.printf("Trials: %,d\n", trials);

        final int DECK_SIZE = 52;
        // Track how often each card appears at each position
        int[][] cardPositionCount = new int[DECK_SIZE][DECK_SIZE];

        for (int trial = 0; trial < trials; trial++) {
            // Create and shuffle deck
            int[] deck = new int[DECK_SIZE];
            for (int i = 0; i < DECK_SIZE; i++) {
                deck[i] = i;
            }
            fisherYatesShuffle(deck);

            // Record positions
            for (int pos = 0; pos < DECK_SIZE; pos++) {
                int card = deck[pos];
                cardPositionCount[card][pos]++;
            }
        }

        // Analyze specific cards (Aces and Kings)
        int[] aces = {0, 13, 26, 39}; // Ace of each suit
        int[] kings = {12, 25, 38, 51}; // King of each suit

        double expectedFrequency = (double) trials / DECK_SIZE;

        System.out.printf("Expected frequency per position: %.1f\n", expectedFrequency);
        System.out.println("\nAce position distribution (first 13 positions):");

        for (int ace : aces) {
            System.out.printf("Ace %2d: ", ace);
            for (int pos = 0; pos < 13; pos++) {
                System.out.printf("%4d", cardPositionCount[ace][pos]);
            }
            System.out.println();
        }

        // Calculate overall uniformity
        double maxDeviation = 0.0;
        for (int card = 0; card < DECK_SIZE; card++) {
            for (int pos = 0; pos < DECK_SIZE; pos++) {
                double deviation = Math.abs(cardPositionCount[card][pos] - expectedFrequency);
                maxDeviation = Math.max(maxDeviation, deviation);
            }
        }

        System.out.printf("Maximum deviation from expected: %.1f (%.2f%%)\n",
                maxDeviation, 100.0 * maxDeviation / expectedFrequency);

        if (maxDeviation < expectedFrequency * 0.15) {
            System.out.println("✓ Card shuffling is fair and uniform!");
        } else {
            System.out.println("⚠ Card shuffling may have bias - check algorithm");
        }
    }
}
