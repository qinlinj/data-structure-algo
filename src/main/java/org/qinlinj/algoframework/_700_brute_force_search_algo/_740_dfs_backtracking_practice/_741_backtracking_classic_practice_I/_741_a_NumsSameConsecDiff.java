package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 967. Numbers With Same Consecutive Differences
 * <p>
 * Problem Summary:
 * Return all non-negative integers of length 'n' such that the absolute difference between
 * consecutive digits is 'k'. Numbers cannot have leading zeros (except 0 itself).
 * <p>
 * Key Insights:
 * - This is a backtracking problem where we need to generate all valid numbers
 * - We can conceptualize this as placing n elements (digits) into n positions
 * - Two pruning conditions:
 * 1. First digit cannot be 0 (to avoid leading zeros)
 * 2. The absolute difference between consecutive digits must be k
 * - We can use a single integer variable to track our current number rather than a list
 * <p>
 * Time Complexity: O(2^n) in worst case, as each digit can potentially branch to 2 paths
 * Space Complexity: O(n) for recursion stack
 */
class _741_a_NumsSameConsecDiff {

    List<Integer> res = new LinkedList<>();
    // Track the current number being built
    int track = 0;
    // Track the number of digits so far
    int digit = 0;

    public int[] numsSameConsecDiff(int n, int k) {
        backtrack(n, k);
        // Convert List<Integer> to int[]
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    // Backtracking core function
    void backtrack(int n, int k) {
        // Base case: reached a leaf node
        if (digit == n) {
            // Found a valid n-digit number
            res.add(track);
            return;
        }

        // Standard backtracking framework
        for (int i = 0; i <= 9; i++) {
            // Pruning logic 1: first digit cannot be 0
            if (digit == 0 && i == 0) continue;
            // Pruning logic 2: absolute difference between consecutive digits must be k
            if (digit > 0 && Math.abs(i - track % 10) != k) continue;

            // Make choice: append digit i to track
            digit++;
            track = 10 * track + i;
            // Move to next level of backtracking tree
            backtrack(n, k);
            // Undo choice: remove last digit from track
            track = track / 10;
            digit--;
        }
    }
}