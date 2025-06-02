package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._862_knapsack_classic_practice;

import java.util.*;

// ==================== Problem 3: Maximum Total Reward ====================
class _862_c_MaximumTotalReward {
    public static void main(String[] args) {
        _862_c_MaximumTotalReward solution = new _862_c_MaximumTotalReward();

        // Test case 1: [1,1,3,3] -> Expected: 4
        // Process: take 1 (x=1), then take 3 (x=4), can't take more 3s since 3 <= 4
        int[] rewards1 = {1, 1, 3, 3};
        System.out.println("Test 1 - Expected: 4, Got: " + solution.maxTotalReward(rewards1));
        System.out.println("Test 1 Optimized - Expected: 4, Got: " + solution.maxTotalRewardOptimized(rewards1));

        // Test case 2: [1,6,4,3,2] -> Expected: 11
        // After sorting: [1,2,3,4,6]
        // Process: 1(x=1) -> 2(x=3) -> 4(x=7) -> 6 can't be taken since 6 <= 7
        // Wait, let me recalculate: 1(x=1) -> 2(x=3) -> 4(x=7), but 6 > 7? No, 6 < 7
        // Actually: 1(x=1) -> 6(x=7), then 4 can be taken since 4 < 7, so x=11
        int[] rewards2 = {1, 6, 4, 3, 2};
        System.out.println("Test 2 - Expected: 11, Got: " + solution.maxTotalReward(rewards2));
        System.out.println("Test 2 Optimized - Expected: 11, Got: " + solution.maxTotalRewardOptimized(rewards2));

        // Test case 3: Edge case [5] -> Expected: 5
        int[] rewards3 = {5};
        System.out.println("Test 3 - Expected: 5, Got: " + solution.maxTotalReward(rewards3));

        // Test case 4: [1,2] -> Expected: 3
        int[] rewards4 = {1, 2};
        System.out.println("Test 4 - Expected: 3, Got: " + solution.maxTotalReward(rewards4));
    }

    /**
     * LeetCode 3180: Maximum Total Reward Using Operations I
     * <p>
     * Problem: Given rewardValues array, start with x=0. Can select unmarked index i
     * if rewardValues[i] > x, then x += rewardValues[i] and mark i.
     * Find maximum possible total reward.
     * <p>
     * Key Insights:
     * 1. This is a variant of 0-1 knapsack with dynamic capacity constraint
     * 2. Unlike standard knapsack with fixed capacity, here capacity = current total value
     * 3. Must sort rewardValues first (greedy: take smaller values first to enable larger ones)
     * 4. Maximum possible x ≤ 2 * max(rewardValues) - 1
     * <p>
     * Proof of max bound:
     * - Largest value must be included (otherwise can always improve by swapping)
     * - Sum of other values < largest value (otherwise largest couldn't be added)
     * - Therefore: total < 2 * max_value
     * <p>
     * DP Definition: dp[i][x] = true if using first i items, can achieve total value x
     * <p>
     * State Transition:
     * - Can include rewardValues[i] if: x >= rewardValues[i] && rewardValues[i] > (x - rewardValues[i])
     * - This simplifies to: rewardValues[i] > x/2
     */

    public int maxTotalReward(int[] rewardValues) {
        int n = rewardValues.length;

        // Critical: Sort array to process smaller values first
        Arrays.sort(rewardValues);
        int maxVal = rewardValues[n - 1];

        // dp[i][x] = true if using first i items, can achieve total value x
        boolean[][] dp = new boolean[n + 1][maxVal * 2];

        // Base case: with 0 items, can only achieve value 0
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int currentVal = rewardValues[i - 1];

            for (int x = 0; x < maxVal * 2; x++) {
                // Option 1: Don't include current item
                dp[i][x] = dp[i - 1][x];

                // Option 2: Include current item (if possible)
                if (x >= currentVal && currentVal > (x - currentVal)) {
                    // Can include: currentVal > previous_total
                    // Since x = previous_total + currentVal, we have currentVal > x - currentVal
                    dp[i][x] = dp[i][x] || dp[i - 1][x - currentVal];
                }
            }
        }

        // Find maximum achievable value
        for (int x = maxVal * 2 - 1; x >= 0; x--) {
            if (dp[n][x]) {
                return x;
            }
        }

        return 0;
    }

    // Alternative space-optimized version
    public int maxTotalRewardOptimized(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int maxVal = rewardValues[rewardValues.length - 1];

        // Use 1D array with rolling update
        boolean[] dp = new boolean[maxVal * 2];
        dp[0] = true;

        for (int val : rewardValues) {
            // Process backwards to avoid using updated values in same iteration
            for (int x = maxVal * 2 - 1; x >= val; x--) {
                if (val > (x - val) && dp[x - val]) {
                    dp[x] = true;
                }
            }
        }

        for (int x = maxVal * 2 - 1; x >= 0; x--) {
            if (dp[x]) return x;
        }

        return 0;
    }
}