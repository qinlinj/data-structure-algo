package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._743_backtracking_classic_practice_III;

import java.util.*;

/**
 * 2305. Fair Distribution of Cookies
 * <p>
 * Problem Summary:
 * You need to distribute cookie bags among k children. Each bag (cookies[i]) contains a certain
 * number of cookies and must be given to exactly one child. The "unfairness" is defined as the
 * maximum number of cookies any single child receives. Return the minimum possible unfairness.
 * <p>
 * Key Insights:
 * - This problem is nearly identical to "1723. Find Minimum Time to Finish All Jobs"
 * - We need to distribute resources (cookie bags) to minimize the maximum allocation
 * - The backtracking approach involves trying all possible assignments of bags to children
 * - Same optimizations apply:
 * 1. Skip assignments that would exceed our current best solution
 * 2. Skip trying multiple children with the same current cookie count
 * - The problem size is even smaller (≤8 bags), making backtracking very efficient
 * <p>
 * Time Complexity: O(k^n) worst case without optimizations, where n is the number of cookie bags
 * Space Complexity: O(k) for tracking allocations plus recursion stack
 */
class _743_d_FairDistributionOfCookies {

    // Track minimum unfairness across all distributions
    int res = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        // Array to track each child's current cookie count
        int[] allocations = new int[k];

        // Start backtracking from the first cookie bag
        backtrack(cookies, 0, allocations);

        return res;
    }

    void backtrack(int[] cookies, int bagIdx, int[] allocations) {
        // Base case: all cookie bags distributed
        if (bagIdx == cookies.length) {
            // Calculate maximum cookies any child received
            int maxAllocation = 0;
            for (int allocation : allocations) {
                maxAllocation = Math.max(maxAllocation, allocation);
            }

            // Update minimum unfairness
            res = Math.min(res, maxAllocation);
            return;
        }

        // Optimization: track which allocation values we've already tried
        HashSet<Integer> tried = new HashSet<>();

        // Try giving current bag to each child
        for (int childIdx = 0; childIdx < allocations.length; childIdx++) {
            // Optimization 1: Skip if this assignment would exceed our current best solution
            if (allocations[childIdx] + cookies[bagIdx] >= res) {
                continue;
            }

            // Optimization 2: Skip if we've already tried a child with this cookie count
            if (tried.contains(allocations[childIdx])) {
                continue;
            }

            // Mark this allocation as tried
            tried.add(allocations[childIdx]);

            // Make choice: give bag to child
            allocations[childIdx] += cookies[bagIdx];

            // Explore next bag distribution
            backtrack(cookies, bagIdx + 1, allocations);

            // Undo choice for backtracking
            allocations[childIdx] -= cookies[bagIdx];
        }
    }

    /**
     * Alternative approach: Sort cookies first
     *
     * One optimization not implemented here would be to sort the cookie bags
     * in descending order first. This allows us to assign the largest bags early
     * in the backtracking, which tends to lead to earlier pruning.
     *
     * int[] sortedCookies = cookies.clone();
     * Arrays.sort(sortedCookies);
     * reverse(sortedCookies);  // Reverse to get descending order
     */
}