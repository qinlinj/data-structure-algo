package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 526. Beautiful Arrangement
 * <p>
 * Problem Summary:
 * Suppose you have n integers from 1 to n. A permutation of those numbers is considered
 * a beautiful arrangement if either:
 * 1. perm[i] is divisible by i
 * 2. i is divisible by perm[i]
 * Return the number of beautiful arrangements.
 * <p>
 * Key Insights:
 * - This is a standard backtracking problem
 * - We can use the "index perspective" approach (as discussed in the original text)
 * - For each index position, we try all possible elements
 * - We only proceed if the divisibility condition is met
 * - We need to track used elements to avoid duplicates
 * <p>
 * Time Complexity: O(n!), as we're generating permutations
 * Space Complexity: O(n) for recursion stack and tracking used elements
 */
class _741_c_BeautifulArrangement {

    // Count of all beautiful arrangements
    int res = 0;
    // Track the backtracking path (elements chosen for each index)
    LinkedList<Integer> track = new LinkedList<>();
    // Elements in track are marked true to avoid duplicate selection
    boolean[] used;

    public int countArrangement(int n) {
        used = new boolean[n + 1];
        backtrack(n, 1);
        return res;
    }

    // Standard backtracking framework from index perspective
    void backtrack(int n, int index) {
        // Base case: reached leaf node
        if (index > n) {
            // Found a valid result
            res += 1;
            return;
        }

        // Index 'index' selects elements
        for (int elem = 1; elem <= n; elem++) {
            // Skip elements already selected by other indices
            if (used[elem]) {
                continue;
            }
            // Skip if divisibility condition not met
            if (!(index % elem == 0 || elem % index == 0)) {
                continue;
            }
            // Make choice: index selects element 'elem'
            used[elem] = true;
            track.addLast(elem);
            // Move to next level in backtracking tree
            backtrack(n, index + 1);
            // Undo choice
            track.removeLast();
            used[elem] = false;
        }
    }
}