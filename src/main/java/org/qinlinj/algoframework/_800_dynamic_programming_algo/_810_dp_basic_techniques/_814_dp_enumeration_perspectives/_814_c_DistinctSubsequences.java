package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._814_dp_enumeration_perspectives;

/**
 * Distinct Subsequences Example Problem (LeetCode 115)
 * <p>
 * Summary:
 * - Problem: Count the number of times string t appears as a subsequence in string s
 * - This is a classic DP problem that can be approached from different enumeration perspectives
 * - Two ways to define the state transition:
 * 1. T-perspective (Box perspective): For each character in t, find all matching positions in s
 * 2. S-perspective (Ball perspective): For each character in s, decide whether to match with the current character in t
 * - Both solutions use the same DP function definition but have different state transitions
 * - The time complexity differs significantly between these approaches:
 * - T-perspective: O(N*M²) where M = length of s, N = length of t
 * - S-perspective: O(M*N)
 * - This demonstrates how the choice of enumeration perspective can significantly impact algorithm efficiency
 */
public class _814_c_DistinctSubsequences {

    public static void main(String[] args) {
        _814_c_DistinctSubsequences solution = new _814_c_DistinctSubsequences();

        String s1 = "rabbbit";
        String t1 = "rabbit";
        System.out.println("Example 1:");
        System.out.println("T-perspective: " + solution.numDistinctTPerspective(s1, t1));  // 3
        System.out.println("S-perspective: " + solution.numDistinctSPerspective(s1, t1));  // 3
        System.out.println("Tabulation: " + solution.numDistinctTabulation(s1, t1));       // 3

        String s2 = "babgbag";
        String t2 = "bag";
        System.out.println("\nExample 2:");
        System.out.println("T-perspective: " + solution.numDistinctTPerspective(s2, t2));  // 5
        System.out.println("S-perspective: " + solution.numDistinctSPerspective(s2, t2));  // 5
        System.out.println("Tabulation: " + solution.numDistinctTabulation(s2, t2));       // 5
    }

    /**
     * Solution using t's perspective (Box perspective)
     * Time Complexity: O(N*M²) where M = length of s, N = length of t
     *
     * @param s Source string
     * @param t Target string (subsequence to find)
     * @return Number of times t appears as a subsequence in s
     */
    public int numDistinctTPerspective(String s, String t) {
        // Initialize memoization array
        Integer[][] memo = new Integer[s.length()][t.length()];
        return dpT(s, 0, t, 0, memo);
    }

    /**
     * DP function from T's perspective (Box perspective)
     * Definition: dp(s, i, t, j) = number of times t[j..] appears in s[i..]
     */
    private int dpT(String s, int i, String t, int j, Integer[][] memo) {
        // Base case 1: if we've matched all characters in t
        if (j == t.length()) {
            return 1;
        }

        // Base case 2: if remaining s is shorter than remaining t
        if (s.length() - i < t.length() - j) {
            return 0;
        }

        // Check memoization array
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int result = 0;

        // For t[j], find all matching positions in s[i..]
        for (int k = i; k < s.length(); k++) {
            if (s.charAt(k) == t.charAt(j)) {
                // If match found, move to next character in t
                result += dpT(s, k + 1, t, j + 1, memo);
            }
        }

        // Store result in memo
        memo[i][j] = result;
        return result;
    }

    /**
     * Solution using s's perspective (Ball perspective)
     * Time Complexity: O(M*N) where M = length of s, N = length of t
     *
     * @param s Source string
     * @param t Target string (subsequence to find)
     * @return Number of times t appears as a subsequence in s
     */
    public int numDistinctSPerspective(String s, String t) {
        // Initialize memoization array
        Integer[][] memo = new Integer[s.length()][t.length()];
        return dpS(s, 0, t, 0, memo);
    }

    /**
     * DP function from S's perspective (Ball perspective)
     * Definition: dp(s, i, t, j) = number of times t[j..] appears in s[i..]
     */
    private int dpS(String s, int i, String t, int j, Integer[][] memo) {
        // Base case 1: if we've matched all characters in t
        if (j == t.length()) {
            return 1;
        }

        // Base case 2: if remaining s is shorter than remaining t
        if (s.length() - i < t.length() - j) {
            return 0;
        }

        // Check memoization array
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int result = 0;

        // For s[i], decide whether to match with t[j]
        if (s.charAt(i) == t.charAt(j)) {
            // Two options when characters match:
            // 1. Use s[i] to match t[j] and move both pointers
            // 2. Skip s[i] and look for t[j] in the rest of s
            result = dpS(s, i + 1, t, j + 1, memo) + dpS(s, i + 1, t, j, memo);
        } else {
            // If characters don't match, skip s[i]
            result = dpS(s, i + 1, t, j, memo);
        }

        // Store result in memo
        memo[i][j] = result;
        return result;
    }

    /**
     * Bottom-up tabulation solution using s perspective (more efficient)
     */
    public int numDistinctTabulation(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[i][j] represents the number of ways t[0..j-1] is a subsequence of s[0..i-1]
        int[][] dp = new int[m + 1][n + 1];

        // Empty t is a subsequence of any s exactly once
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Either use s[i-1] to match t[j-1], or don't use it
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Can't match, so just skip s[i-1]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}