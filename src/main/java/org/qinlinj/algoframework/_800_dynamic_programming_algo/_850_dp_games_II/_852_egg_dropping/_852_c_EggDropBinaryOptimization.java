package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

import java.util.*;

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
public class _852_c_EggDropBinaryOptimization {
    private int[][] memo;

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
}
