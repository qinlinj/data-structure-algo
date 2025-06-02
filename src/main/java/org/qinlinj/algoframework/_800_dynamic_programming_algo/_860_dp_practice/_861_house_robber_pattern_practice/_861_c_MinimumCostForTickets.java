package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

// ==================== Problem 3: Minimum Cost For Tickets ====================
class _861_c_MinimumCostForTickets {
    /**
     * LeetCode 983: Minimum Cost For Tickets
     * <p>
     * Problem: Given travel days and ticket costs [1-day, 7-day, 30-day],
     * find minimum cost to cover all travel days.
     * <p>
     * Pattern: Multiple choices with different coverage periods
     * DP Definition: dp(i) = minimum cost from days[i] onwards
     * Transition: dp(i) = min of three ticket options, each covering different periods
     */

    private int[] memo;

    public static void main(String[] args) {
        _861_c_MinimumCostForTickets solution = new _861_c_MinimumCostForTickets();

        // Test case 1
        int[] days1 = {1, 4, 6, 7, 8, 20};
        int[] costs1 = {2, 7, 15};
        System.out.println("Test 1 - Expected: 11, Got: " + solution.mincostTickets(days1, costs1));

        // Test case 2
        int[] days2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = {2, 7, 15};
        System.out.println("Test 2 - Expected: 17, Got: " + solution.mincostTickets(days2, costs2));
    }

    public int mincostTickets(int[] days, int[] costs) {
        memo = new int[days.length];
        java.util.Arrays.fill(memo, -1);
        return dp(days, costs, 0);
    }

    private int dp(int[] days, int[] costs, int start) {
        if (start >= days.length) {
            return 0;
        }

        if (memo[start] != -1) {
            return memo[start];
        }

        int currentDay = days[start];
        int result = Integer.MAX_VALUE;

        // Option 1: Buy 1-day ticket
        int nextDay1 = findNextDay(days, start, currentDay + 1);
        result = Math.min(result, costs[0] + dp(days, costs, nextDay1));

        // Option 2: Buy 7-day ticket
        int nextDay7 = findNextDay(days, start, currentDay + 7);
        result = Math.min(result, costs[1] + dp(days, costs, nextDay7));

        // Option 3: Buy 30-day ticket
        int nextDay30 = findNextDay(days, start, currentDay + 30);
        result = Math.min(result, costs[2] + dp(days, costs, nextDay30));

        memo[start] = result;
        return result;
    }

    private int findNextDay(int[] days, int start, int targetDay) {
        int index = start;
        while (index < days.length && days[index] < targetDay) {
            index++;
        }
        return index;
    }
}