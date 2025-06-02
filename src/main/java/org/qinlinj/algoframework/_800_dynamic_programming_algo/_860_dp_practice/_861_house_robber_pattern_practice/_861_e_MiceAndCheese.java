package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

// ==================== Problem 5: Mice and Cheese ====================
class _861_e_MiceAndCheese {
    public static void main(String[] args) {
        _861_e_MiceAndCheese solution = new _861_e_MiceAndCheese();

        // Test case 1
        int[] reward1_1 = {1, 1, 3, 4};
        int[] reward2_1 = {4, 4, 1, 1};
        System.out.println("Test 1 - Expected: 15, Got: " + solution.miceAndCheese(reward1_1, reward2_1, 2));

        // Test case 2
        int[] reward1_2 = {1, 1};
        int[] reward2_2 = {1, 1};
        System.out.println("Test 2 - Expected: 2, Got: " + solution.miceAndCheese(reward1_2, reward2_2, 2));
    }

    /**
     * LeetCode 2611: Mice and Cheese
     * <p>
     * Problem: Two mice, n cheeses. First mouse gets reward1[i], second gets reward2[i].
     * First mouse must eat exactly k cheeses. Maximize total score.
     * <p>
     * Insight: DP approach works but times out. Use greedy approach instead.
     * Greedy: Choose k cheeses where reward1[i] - reward2[i] is largest.
     * <p>
     * Pattern: When DP times out, consider greedy alternatives
     */

    // Greedy solution (efficient)
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;

        // Create difference array with indices
        int[][] diff = new int[n][2];
        for (int i = 0; i < n; i++) {
            diff[i][0] = reward1[i] - reward2[i]; // difference
            diff[i][1] = i; // original index
        }

        // Sort by difference in descending order
        java.util.Arrays.sort(diff, (a, b) -> b[0] - a[0]);

        int sum = 0;

        // First mouse takes k cheeses with highest differences
        for (int i = 0; i < k; i++) {
            sum += reward1[diff[i][1]];
        }

        // Second mouse takes remaining cheeses
        for (int i = k; i < n; i++) {
            sum += reward2[diff[i][1]];
        }

        return sum;
    }
}