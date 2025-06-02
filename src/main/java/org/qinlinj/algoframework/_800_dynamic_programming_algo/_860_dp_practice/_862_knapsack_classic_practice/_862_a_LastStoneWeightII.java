package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._862_knapsack_classic_practice; /**
 * DYNAMIC PROGRAMMING - KNAPSACK PROBLEM VARIATIONS
 * <p>
 * This collection demonstrates various knapsack problem variants, which are classic DP problems
 * focused on selecting optimal element combinations within certain constraints.
 * <p>
 * Key Characteristics of Knapsack Problems:
 * 1. DP Array Definition: First dimension is "using only first i items"
 * 2. Additional dimensions define constraint conditions (weight, capacity, etc.)
 * 3. State Transition: For each item, decide whether to include it or not
 * 4. Optimization Goal: Maximize value, minimize cost, or count possibilities
 * <p>
 * Standard 0-1 Knapsack DP Definition:
 * dp[i][j] = maximum value using first i items with capacity j
 * <p>
 * Common Variations:
 * - Multiple constraints (2D/3D knapsack)
 * - Subset sum problems
 * - Partition problems
 * - Dynamic constraint problems
 */

// ==================== Problem 1: Last Stone Weight II ====================
class _862_a_LastStoneWeightII {
    public static void main(String[] args) {
        _862_a_LastStoneWeightII solution = new _862_a_LastStoneWeightII();

        // Test case 1: [2,7,4,1,8,1] -> Expected: 1
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println("Test 1 - Expected: 1, Got: " + solution.lastStoneWeightII(stones1));

        // Test case 2: [31,26,33,21,40] -> Expected: 5
        int[] stones2 = {31, 26, 33, 21, 40};
        System.out.println("Test 2 - Expected: 5, Got: " + solution.lastStoneWeightII(stones2));

        // Test case 3: Edge case [1] -> Expected: 1
        int[] stones3 = {1};
        System.out.println("Test 3 - Expected: 1, Got: " + solution.lastStoneWeightII(stones3));

        // Test case 4: [1,1] -> Expected: 0
        int[] stones4 = {1, 1};
        System.out.println("Test 4 - Expected: 0, Got: " + solution.lastStoneWeightII(stones4));
    }

    /**
     * LeetCode 1049: Last Stone Weight II
     *
     * Problem: Given stones array, two stones with weights x,y collide.
     * If x==y, both destroyed. If x!=y, stone with weight |x-y| remains.
     * Find minimum possible weight of last remaining stone.
     *
     * Key Insight: Transform to Subset Sum Problem
     * - Divide stones into two groups to minimize their weight difference
     * - Find subset closest to sum/2, the other subset will be sum - subset1
     * - Minimum difference = |subset1 - subset2| = |subset1 - (sum - subset1)| = |2*subset1 - sum|
     *
     * DP Definition: dp[i][j] = maximum weight using first i stones with capacity j
     * This is classic 0-1 knapsack where each stone can be used at most once.
     */

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }

        int target = sum / 2;
        // dp[i][j] = maximum weight using first i stones with capacity j
        int[][] dp = new int[stones.length + 1][target + 1];

        // Base case: dp[0][j] = 0 (no stones, no weight)

        for (int i = 1; i <= stones.length; i++) {
            int currentStone = stones[i - 1]; // Convert to 0-based index

            for (int j = 0; j <= target; j++) {
                if (j >= currentStone) {
                    // Can include current stone: choose max of include vs exclude
                    dp[i][j] = Math.max(
                            dp[i - 1][j],                              // Don't take current stone
                            dp[i - 1][j - currentStone] + currentStone // Take current stone
                    );
                } else {
                    // Cannot include current stone due to capacity
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Maximum weight we can achieve with capacity sum/2
        int maxWeight = dp[stones.length][target];

        // Two subsets: maxWeight and (sum - maxWeight)
        // Minimum difference = |(sum - maxWeight) - maxWeight| = |sum - 2*maxWeight|
        return sum - 2 * maxWeight;
    }
}