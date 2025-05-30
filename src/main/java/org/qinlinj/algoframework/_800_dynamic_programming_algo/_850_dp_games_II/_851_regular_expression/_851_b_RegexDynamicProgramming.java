package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._851_regular_expression;

import java.util.*;

/**
 * REGULAR EXPRESSION MATCHING - DYNAMIC PROGRAMMING SOLUTION
 * <p>
 * CORE CONCEPTS:
 * 1. State Definition: dp(s, i, p, j) = true if s[i..] can match p[j..]
 * 2. State Transitions: Based on current characters and presence of '*'
 * 3. Memoization: Cache results to avoid recomputing overlapping subproblems
 * 4. Base Cases: Handle end-of-string scenarios properly
 * <p>
 * STATE TRANSITIONS:
 * Case 1: s[i] matches p[j] (either same char or p[j] is '.')
 * - If p[j+1] is '*': try 0 matches OR 1+ matches
 * - Otherwise: simple 1-to-1 match
 * Case 2: s[i] doesn't match p[j]
 * - If p[j+1] is '*': must match 0 times
 * - Otherwise: matching fails
 * <p>
 * BASE CASES:
 * 1. Pattern exhausted (j == p.length): text must also be exhausted
 * 2. Text exhausted (i == s.length): pattern remainder must match empty string
 * <p>
 * TIME COMPLEXITY: O(M*N) where M = text length, N = pattern length
 * SPACE COMPLEXITY: O(M*N) for memoization table
 */

public class _851_b_RegexDynamicProgramming {
    // Memoization table: -1 = not computed, 0 = false, 1 = true
    private int[][] memo;

    /**
     * Main function: determines if pattern p matches text s
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        memo = new int[m + 1][n + 1];  // +1 to handle end positions

        // Initialize memo with -1 (not computed)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start matching from beginning of both strings
        return dp(s, 0, p, 0);
    }

    /**
     * Dynamic programming function
     * Returns true if s[i..] can match p[j..]
     */
    private boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();

        // BASE CASE 1: Pattern exhausted
        if (j == n) {
            return i == m;  // Text must also be exhausted
        }

        // BASE CASE 2: Text exhausted but pattern remains
        if (i == m) {
            return canMatchEmptyString(p, j);
        }

        // Check memoization table
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        boolean result = false;

        // Check if current characters match
        boolean firstMatch = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // Check if next character in pattern is '*'
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            // Two choices with '*' wildcard:
            // 1. Match 0 times: skip current pattern char and '*'
            // 2. Match 1+ times: if chars match, consume text char and keep pattern
            result = dp(s, i, p, j + 2) ||  // 0 matches
                    (firstMatch && dp(s, i + 1, p, j));  // 1+ matches
        } else {
            // No '*' wildcard: simple character matching
            result = firstMatch && dp(s, i + 1, p, j + 1);
        }

        // Store result in memo table
        memo[i][j] = result ? 1 : 0;
        return result;
    }
}
