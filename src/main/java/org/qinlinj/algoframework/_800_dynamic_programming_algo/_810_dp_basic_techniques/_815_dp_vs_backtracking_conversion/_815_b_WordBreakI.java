package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._815_dp_vs_backtracking_conversion;

import java.util.*;

/**
 * Word Break I - LeetCode 139
 * <p>
 * Summary:
 * - Problem: Given a string s and a dictionary wordDict, determine if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 * <p>
 * - This problem can be solved using two approaches:
 * 1. Backtracking (Traversal): Explore all possible word breaks recursively
 * 2. Dynamic Programming (Problem Decomposition): Break down the problem into smaller subproblems
 * <p>
 * - Backtracking approach:
 * - View the problem as a tree traversal where each node represents a partially processed string
 * - For each position, try all possible words from the dictionary that match the current prefix
 * - Time complexity: O(2^n * m*n) without memoization, where:
 * - n is the length of the string
 * - m is the size of wordDict
 * - 2^n represents all possible ways to segment the string
 * - m*n is the cost of checking each word against the string
 * <p>
 * - Dynamic Programming approach:
 * - Define dp(i) to determine if s[i..] can be segmented
 * - Use memoization to avoid redundant calculations
 * - Time complexity: O(n^3), where n is the length of the string
 * - n states for dp(i)
 * - O(n^2) work per state (iterating through all possible prefixes)
 */
public class _815_b_WordBreakI {

    public static void main(String[] args) {
        // Example 1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");

        // Example 2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");

        // Example 3
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");

        // Test backtracking solution
        BacktrackingSolution backSol = new BacktrackingSolution();
        System.out.println("Backtracking solution:");
        System.out.println("Example 1: " + backSol.wordBreak(s1, wordDict1)); // true
        System.out.println("Example 2: " + backSol.wordBreak(s2, wordDict2)); // true
        System.out.println("Example 3: " + backSol.wordBreak(s3, wordDict3)); // false

        // Test DP solution
        DPSolution dpSol = new DPSolution();
        System.out.println("\nDP solution:");
        System.out.println("Example 1: " + dpSol.wordBreak(s1, wordDict1)); // true
        System.out.println("Example 2: " + dpSol.wordBreak(s2, wordDict2)); // true
        System.out.println("Example 3: " + dpSol.wordBreak(s3, wordDict3)); // false

        // Test iterative DP solution
        IterativeDPSolution iterDpSol = new IterativeDPSolution();
        System.out.println("\nIterative DP solution:");
        System.out.println("Example 1: " + iterDpSol.wordBreak(s1, wordDict1)); // true
        System.out.println("Example 2: " + iterDpSol.wordBreak(s2, wordDict2)); // true
        System.out.println("Example 3: " + iterDpSol.wordBreak(s3, wordDict3)); // false
    }

    /**
     * Backtracking solution for Word Break problem
     * Time complexity: O(2^n * m*n) without optimization
     */
    public static class BacktrackingSolution {
        private List<String> wordDict;
        // Flag to indicate if a valid segmentation is found
        private boolean found = false;
        // Track the current path in the recursion
        private LinkedList<String> track = new LinkedList<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            // Start the backtracking process
            backtrack(s, 0);
            return found;
        }

        /**
         * Backtracking algorithm to explore all possible word breaks
         *
         * @param s the input string
         * @param i current position in the string
         */
        private void backtrack(String s, int i) {
            // If we already found a solution, no need to continue
            if (found) {
                return;
            }

            // Base case: reached the end of the string successfully
            if (i == s.length()) {
                found = true;
                return;
            }

            // Try each word in the dictionary
            for (String word : wordDict) {
                int len = word.length();
                // Check if the current word matches the prefix of remaining string
                if (i + len <= s.length() &&
                        s.substring(i, i + len).equals(word)) {

                    // Choose this word
                    track.addLast(word);
                    // Continue to the next position
                    backtrack(s, i + len);
                    // Unchoose (backtrack)
                    track.removeLast();
                }
            }
        }
    }

    /**
     * Optimized backtracking solution with memoization
     * Time complexity: Improved but still exponential in worst case
     */
    public static class MemoizedBacktrackingSolution {
        private List<String> wordDict;
        private boolean found = false;
        private LinkedList<String> track = new LinkedList<>();
        // Memoization to avoid redundant calculations
        private HashSet<String> memo = new HashSet<>();

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            backtrack(s, 0);
            return found;
        }

        private void backtrack(String s, int i) {
            if (found) {
                return;
            }

            if (i == s.length()) {
                found = true;
                return;
            }

            // Check if we've already determined this suffix cannot be segmented
            String suffix = s.substring(i);
            if (memo.contains(suffix)) {
                return;
            }

            // Try each word in the dictionary
            for (String word : wordDict) {
                int len = word.length();
                if (i + len <= s.length() &&
                        s.substring(i, i + len).equals(word)) {

                    track.addLast(word);
                    backtrack(s, i + len);
                    track.removeLast();
                }
            }

            // If we reach here without finding a solution,
            // this suffix cannot be segmented
            if (!found) {
                memo.add(suffix);
            }
        }
    }

    /**
     * Dynamic Programming solution for Word Break problem
     * Time complexity: O(n^3), where n is the length of the string
     */
    public static class DPSolution {
        // Use HashSet for O(1) lookups
        private HashSet<String> wordDict;
        // Memoization array: -1 = not calculated, 0 = false, 1 = true
        private int[] memo;

        public boolean wordBreak(String s, List<String> wordDict) {
            this.wordDict = new HashSet<>(wordDict);
            this.memo = new int[s.length()];
            Arrays.fill(memo, -1);
            return dp(s, 0);
        }

        /**
         * DP function: determines if s[i..] can be segmented into dictionary words
         *
         * @param s the input string
         * @param i current position in the string
         * @return true if s[i..] can be segmented, false otherwise
         */
        private boolean dp(String s, int i) {
            // Base case: empty string can always be segmented
            if (i == s.length()) {
                return true;
            }

            // Check memoization
            if (memo[i] != -1) {
                return memo[i] == 1;
            }

            // Try all possible prefixes
            for (int len = 1; i + len <= s.length(); len++) {
                String prefix = s.substring(i, i + len);
                if (wordDict.contains(prefix)) {
                    // If this prefix is a word and the rest can be segmented
                    if (dp(s, i + len)) {
                        memo[i] = 1;
                        return true;
                    }
                }
            }

            // If no valid segmentation found
            memo[i] = 0;
            return false;
        }
    }

    /**
     * Iterative Dynamic Programming solution
     * Time complexity: O(n^3), where n is the length of the string
     */
    public static class IterativeDPSolution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true; // Empty string can be segmented

            // For each position in the string
            for (int i = 1; i <= s.length(); i++) {
                // Try all possible prefixes ending at position i
                for (int j = 0; j < i; j++) {
                    // If we can segment up to j and s[j..i) is a word
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }
}