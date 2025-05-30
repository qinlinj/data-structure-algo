package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

/**
 * EGG DROPPING PROBLEM - STATE REDEFINITION APPROACH
 * <p>
 * REVOLUTIONARY PERSPECTIVE:
 * Instead of asking "Given K eggs and N floors, what's the minimum trials?"
 * We ask "Given K eggs and M trials, what's the maximum floors we can handle?"
 * <p>
 * STATE REDEFINITION:
 * dp[k][m] = maximum floors we can handle with k eggs and m trials
 * <p>
 * INTUITIVE REASONING:
 * When we drop an egg from floor F:
 * - If it breaks: we have (k-1) eggs and (m-1) trials for floors below F
 * - If it survives: we have k eggs and (m-1) trials for floors above F
 * - Total floors we can handle = floors_below + floors_above + current_floor
 * <p>
 * STATE TRANSITION:
 * dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1
 * Where:
 * - dp[k-1][m-1]: floors below (egg breaks scenario)
 * - dp[k][m-1]: floors above (egg survives scenario)
 * - +1: current floor being tested
 * <p>
 * SOLUTION APPROACH:
 * Find minimum m such that dp[K][m] >= N
 * <p>
 * COMPLEXITY IMPROVEMENT:
 * Time: O(K * N) instead of O(K * N * log N)
 * Space: O(K * N)
 * <p>
 * KEY INSIGHTS:
 * 1. State redefinition can dramatically simplify problems
 * 2. "Reverse" thinking often leads to elegant solutions
 * 3. Optimal substructure becomes more apparent
 */

public class _852_d_EggDropStateRedefinition {
    /**
     * Main solution using redefined state approach
     */
    public int superEggDrop(int K, int N) {
        // dp[k][m] = max floors handleable with k eggs and m trials
        int[][] dp = new int[K + 1][N + 1];

        // Base cases are automatically handled (dp[0][m] = 0, dp[k][0] = 0)

        int m = 0; // trials counter

        // Increment trials until we can handle N floors with K eggs
        while (dp[K][m] < N) {
            m++;
            // Fill dp table for current trial count m
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1;
            }
        }

        return m;
    }

    /**
     * Alternative implementation that shows the logic more clearly
     */
    public int superEggDropDetailed(int K, int N) {
        // dp[k][m] = max floors we can determine with k eggs and m trials
        int[][] dp = new int[K + 1][N + 1];

        // For each number of trials m
        for (int m = 1; m <= N; m++) {
            // For each number of eggs k
            for (int k = 1; k <= K; k++) {
                // State transition: floors_below + floors_above + current_floor
                dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1;

                // If we can handle N floors with K eggs in m trials, return m
                if (dp[K][m] >= N) {
                    return m;
                }
            }
        }

        return N; // Fallback (shouldn't reach here for valid inputs)
    }

    /**
     * Demonstrates the state transition logic step by step
     */
    public void demonstrateStateTransition(int K, int N) {
        System.out.println("=== State Transition Demonstration ===");
        System.out.println("Question: With " + K + " eggs and m trials, max floors we can handle?");
        System.out.println();

        int[][] dp = new int[K + 1][N + 1];

        // Show step-by-step building of dp table
        for (int m = 1; m <= Math.min(N, 8); m++) {
            System.out.println("Trials = " + m + ":");

            for (int k = 1; k <= K; k++) {
                int floorsBelow = (k > 1) ? dp[k - 1][m - 1] : 0;
                int floorsAbove = (m > 1) ? dp[k][m - 1] : 0;
                dp[k][m] = floorsBelow + floorsAbove + 1;

                System.out.printf("  dp[%d][%d] = dp[%d][%d] + dp[%d][%d] + 1 = %d + %d + 1 = %d%n",
                        k, m, k - 1, m - 1, k, m - 1, floorsBelow, floorsAbove, dp[k][m]);
            }
            System.out.println();

            if (dp[K][m] >= N) {
                System.out.println("*** Found answer: " + m + " trials can handle " + dp[K][m] + " floors ***");
                break;
            }
        }
    }

    /**
     * Visualizes the complete dp table for small inputs
     */
    public void visualizeDPTable(int maxK, int maxM) {
        System.out.println("\n=== DP Table Visualization ===");
        System.out.println("dp[k][m] = max floors with k eggs and m trials");
        System.out.println();

        int[][] dp = new int[maxK + 1][maxM + 1];

        // Fill the table
        for (int m = 1; m <= maxM; m++) {
            for (int k = 1; k <= maxK; k++) {
                dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1;
            }
        }

        // Print header
        System.out.print("k\\m\t");
        for (int m = 0; m <= maxM; m++) {
            System.out.print(m + "\t");
        }
        System.out.println();

        // Print table
        for (int k = 0; k <= maxK; k++) {
            System.out.print(k + "\t");
            for (int m = 0; m <= maxM; m++) {
                System.out.print(dp[k][m] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Explains why this redefinition works
     */
    public void explainRedefinitionIntuition() {
        System.out.println("\n=== Why State Redefinition Works ===");
        System.out.println("ORIGINAL QUESTION:");
        System.out.println("  'Given K eggs and N floors, minimum trials needed?'");
        System.out.println("  → Complex because we don't know the optimal strategy");
        System.out.println();
        System.out.println("REDEFINED QUESTION:");
        System.out.println("  'Given K eggs and M trials, maximum floors we can handle?'");
        System.out.println("  → Simpler because the strategy is constructive");
        System.out.println();
        System.out.println("KEY INSIGHT:");
        System.out.println("  If we can construct a strategy to handle F floors with M trials,");
        System.out.println("  then this strategy is automatically optimal for those parameters!");
        System.out.println();
        System.out.println("CONSTRUCTION LOGIC:");
        System.out.println("  1. Choose any floor F to test first");
        System.out.println("  2. If egg breaks: handle floors [1, F-1] with (K-1, M-1)");
        System.out.println("  3. If egg survives: handle floors [F+1, ?] with (K, M-1)");
        System.out.println("  4. Total capacity = capacity_below + capacity_above + 1");
    }

    /**
     * Compares this approach with previous methods
     */
    public void compareWithPreviousApproaches() {
        System.out.println("\n=== Comparison with Previous Approaches ===");

        int[] testK = {2, 3, 4};
        int[] testN = {10, 20, 50};

        System.out.println("Test Case\t\tComplexity Comparison");
        System.out.println("---------\t\t--------------------");
        System.out.println("Method\t\t\tTime\t\tSpace");
        System.out.println("Basic DP\t\tO(KN²)\t\tO(KN)");
        System.out.println("Binary Opt\t\tO(KN log N)\tO(KN)");
        System.out.println("Redefinition\t\tO(KN)\t\tO(KN)");
        System.out.println();

        System.out.println("Results verification:");
        for (int k : testK) {
            for (int n : testN) {
                int result = superEggDrop(k, n);
                System.out.printf("K=%d, N=%d: %d trials%n", k, n, result);
            }
        }
    }

    /**
     * Shows the mathematical elegance of the recurrence
     */
    public void showMathematicalElegance() {
        System.out.println("\n=== Mathematical Elegance ===");
        System.out.println("RECURRENCE RELATION:");
        System.out.println("  dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1");
        System.out.println();
        System.out.println("INTERPRETATION:");
        System.out.println("  Total floors = Floors below + Floors above + Current floor");
        System.out.println();
        System.out.println("BOUNDARY CONDITIONS:");
        System.out.println("  dp[0][m] = 0  (no eggs, can't test any floors)");
        System.out.println("  dp[k][0] = 0  (no trials, can't test any floors)");
        System.out.println("  dp[1][m] = m  (one egg, must test linearly)");
        System.out.println();
        System.out.println("GROWTH PATTERN:");
        System.out.println("  Notice how dp[k][m] grows much faster than m");
        System.out.println("  This is why we can handle large N with small m");
    }
}
