package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._851_regular_expression;

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

}
