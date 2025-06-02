package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

// ==================== Problem 2: Count House Placements ====================
class _861_b_CountHousePlacements {
    /**
     * LeetCode 2320: Count House Placements
     * <p>
     * Problem: A street has n*2 plots (n on each side). Houses cannot be adjacent on same side.
     * Count total ways to place houses.
     * <p>
     * Key Insight: Two sides are independent, so total ways = ways_one_side²
     * <p>
     * Pattern: Count variations instead of max value
     * DP Definition: dp(i) = number of ways to place houses from position i to n
     * Transition: dp(i) = dp(i+1) + dp(i+2) (skip or place house)
     */

    private static final int MOD = 1000000007;
    private int[] memo;

    public static void main(String[] args) {
        _861_b_CountHousePlacements solution = new _861_b_CountHousePlacements();

        System.out.println("n=1 - Expected: 4, Got: " + solution.countHousePlacements(1));
        System.out.println("n=2 - Expected: 9, Got: " + solution.countHousePlacements(2));
        System.out.println("n=3 - Expected: 20, Got: " + solution.countHousePlacements(3));
    }

    public int countHousePlacements(int n) {
        memo = new int[n + 1];
        java.util.Arrays.fill(memo, -1);
        long oneSideWays = dp(0, n);
        return (int) ((oneSideWays * oneSideWays) % MOD);
    }

    private int dp(int i, int n) {
        // Base case: reached end or beyond
        if (i >= n) {
            return 1; // One way: place nothing
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        // Two choices: place house at i (then skip i+1) or don't place
        long placeHouse = dp(i + 2, n);
        long dontPlace = dp(i + 1, n);

        memo[i] = (int) ((placeHouse + dontPlace) % MOD);
        return memo[i];
    }
}