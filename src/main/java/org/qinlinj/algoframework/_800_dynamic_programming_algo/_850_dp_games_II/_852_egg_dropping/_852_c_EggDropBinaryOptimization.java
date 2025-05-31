package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

/**
 * EGG DROPPING PROBLEM - BINARY SEARCH OPTIMIZATION
 * <p>
 * OPTIMIZATION INSIGHT:
 * In the basic DP solution, we linearly search through all floors i in [1, N].
 * However, the two functions in our recurrence have opposite monotonicity:
 * - dp(K-1, i-1): increases as i increases (more floors to search below)
 * - dp(K, N-i): decreases as i increases (fewer floors to search above)
 * <p>
 * MONOTONICITY ANALYSIS:
 * When we plot these two functions against i, they form intersecting curves.
 * The optimal choice is at their intersection point (valley of max function).
 * This allows us to use binary search instead of linear search.
 * <p>
 * BINARY SEARCH STRATEGY:
 * - If dp(K-1, mid-1) > dp(K, N-mid): search left half (decrease mid)
 * - If dp(K-1, mid-1) < dp(K, N-mid): search right half (increase mid)
 * - Continue until we find the optimal floor choice
 * <p>
 * COMPLEXITY IMPROVEMENT:
 * - Original: O(K * N^2) - linear search over N floors for each state
 * - Optimized: O(K * N * log N) - binary search over floors
 * <p>
 * KEY CONCEPTS:
 * 1. Function monotonicity enables binary search
 * 2. Optimal point is where two curves intersect
 * 3. Binary search finds valley in max(f1(i), f2(i))
 */

import java.util.*;

public class _852_c_EggDropBinaryOptimization {

    private int[][] memo;

    public static void main(String[] args) {
        _852_c_EggDropBinaryOptimization solver = new _852_c_EggDropBinaryOptimization();

        // Test basic functionality
        System.out.println("=== Test Results ===");
        System.out.println("2 eggs, 10 floors: " + solver.superEggDrop(2, 10));
        System.out.println("3 eggs, 14 floors: " + solver.superEggDrop(3, 14));
        System.out.println("4 eggs, 20 floors: " + solver.superEggDrop(4, 20));

        // Demonstrate monotonicity
        solver.demonstrateMonotonicity(2, 8);

        // Visualize binary search
        solver.visualizeBinarySearch(2, 8);

        // Compare performance
        solver.comparePerformance();

        // Explain intuition
        solver.explainOptimizationIntuition();
    }

    /**
     * Optimized solution using binary search
     */
    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(K, N);
    }

    /**
     * DP function with binary search optimization
     */
    private int dp(int K, int N) {
        // Base cases
        if (N == 0) return 0;
        if (K == 1) return N;

        // Check memo
        if (memo[K][N] != -1) {
            return memo[K][N];
        }

        // Binary search for optimal floor choice
        int result = Integer.MAX_VALUE;
        int left = 1, right = N;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate both scenarios
            int eggBreaks = dp(K - 1, mid - 1);    // Search floors below
            int eggSurvives = dp(K, N - mid);      // Search floors above

            // Update result with current choice
            result = Math.min(result, Math.max(eggBreaks, eggSurvives) + 1);

            // Binary search logic based on monotonicity
            if (eggBreaks > eggSurvives) {
                // The breaking case dominates, try lower floors
                right = mid - 1;
            } else {
                // The surviving case dominates, try higher floors
                left = mid + 1;
            }
        }

        memo[K][N] = result;
        return result;
    }

    /**
     * Demonstrates the monotonicity that enables binary search
     */
    public void demonstrateMonotonicity(int K, int N) {
        System.out.println("=== Monotonicity Demonstration ===");
        System.out.println("K=" + K + ", N=" + N);
        System.out.println();

        // Reset memo for clean calculation
        memo = new int[K + 1][N + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        System.out.println("Floor\tBreaks\tSurvives\tMax\tOptimal?");
        System.out.println("-----\t------\t--------\t---\t--------");

        int optimalFloor = -1;
        int minMaxTrials = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int breaks = dp(K - 1, i - 1);
            int survives = dp(K, N - i);
            int maxTrials = Math.max(breaks, survives);

            boolean isOptimal = false;
            if (maxTrials < minMaxTrials) {
                minMaxTrials = maxTrials;
                optimalFloor = i;
                isOptimal = true;
            }

            System.out.printf("%d\t%d\t%d\t\t%d\t%s%n",
                    i, breaks, survives, maxTrials,
                    isOptimal ? "***" : "");
        }

        System.out.println();
        System.out.println("Optimal floor: " + optimalFloor + " with " + (minMaxTrials + 1) + " total trials");
        System.out.println("Notice: 'Breaks' increases, 'Survives' decreases as floor increases");
    }

    /**
     * Compares performance of linear vs binary search approaches
     */
    public void comparePerformance() {
        System.out.println("\n=== Performance Comparison ===");

        int[] testK = {2, 3, 4};
        int[] testN = {10, 50, 100};

        System.out.println("Test Case\t\tLinear Search\tBinary Search");
        System.out.println("---------\t\t-------------\t-------------");

        for (int k : testK) {
            for (int n : testN) {
                // Both should give same result
                int result = superEggDrop(k, n);

                // Estimate operation counts (simplified)
                long linearOps = (long) k * n * n;
                long binaryOps = (long) k * n * (int) (Math.log(n) / Math.log(2));

                System.out.printf("K=%d, N=%d (ans=%d)\t%d ops\t\t%d ops%n",
                        k, n, result, linearOps, binaryOps);
            }
        }
    }

    /**
     * Visualizes the binary search process
     */
    public void visualizeBinarySearch(int K, int N) {
        System.out.println("\n=== Binary Search Visualization ===");
        System.out.println("Finding optimal floor for K=" + K + ", N=" + N);
        System.out.println();

        // Reset memo
        memo = new int[K + 1][N + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int left = 1, right = N;
        int iteration = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int eggBreaks = dp(K - 1, mid - 1);
            int eggSurvives = dp(K, N - mid);

            System.out.printf("Iteration %d: left=%d, right=%d, mid=%d%n",
                    iteration, left, right, mid);
            System.out.printf("  Breaks: %d, Survives: %d%n", eggBreaks, eggSurvives);

            if (eggBreaks > eggSurvives) {
                System.out.println("  Breaks > Survives, search left half");
                right = mid - 1;
            } else {
                System.out.println("  Breaks <= Survives, search right half");
                left = mid + 1;
            }

            iteration++;
            System.out.println();
        }
    }

    /**
     * Explains the mathematical intuition behind the optimization
     */
    public void explainOptimizationIntuition() {
        System.out.println("\n=== Optimization Intuition ===");
        System.out.println("1. FUNCTION BEHAVIOR:");
        System.out.println("   f1(i) = dp(K-1, i-1) [increasing in i]");
        System.out.println("   f2(i) = dp(K, N-i)   [decreasing in i]");
        System.out.println();
        System.out.println("2. GRAPHICAL INTERPRETATION:");
        System.out.println("   When plotted, f1 and f2 intersect");
        System.out.println("   max(f1(i), f2(i)) forms a 'V' shape");
        System.out.println("   Minimum of this V is our optimal point");
        System.out.println();
        System.out.println("3. BINARY SEARCH APPLICATION:");
        System.out.println("   If f1(mid) > f2(mid): optimum is to the left");
        System.out.println("   If f1(mid) < f2(mid): optimum is to the right");
        System.out.println("   Continue until convergence");
        System.out.println();
        System.out.println("4. COMPLEXITY BENEFIT:");
        System.out.println("   Linear search: O(N) per subproblem");
        System.out.println("   Binary search: O(log N) per subproblem");
        System.out.println("   Overall improvement: O(KN²) → O(KN log N)");
    }
}