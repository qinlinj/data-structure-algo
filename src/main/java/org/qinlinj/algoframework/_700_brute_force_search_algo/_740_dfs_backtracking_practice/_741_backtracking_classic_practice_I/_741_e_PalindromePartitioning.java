package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 131. Palindrome Partitioning
 * <p>
 * Problem Summary:
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * Key Insights:
 * - This is a classic backtracking problem
 * - We can start from the beginning of the string and try all possible cuts
 * - If the substring s[0...i] is a palindrome, we can cut it and recursively process s[i+1...]
 * - The backtracking tree branches represent different cut positions
 * - We need a helper function to check if a substring is a palindrome
 * <p>
 * Time Complexity: O(n * 2^n) where n is the length of the string
 * Space Complexity: O(n) for recursion stack and storing current partition
 */
class _741_e_PalindromePartitioning {

    List<List<String>> res = new LinkedList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    // Backtracking framework
    void backtrack(String s, int start) {
        if (start == s.length()) {
            // Base case: reached leaf node
            // The entire string has been successfully partitioned into palindromes
            res.add(new ArrayList<String>(track));
        }

        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                // s[start..i] is not a palindrome, cannot partition here
                continue;
            }

            // s[start..i] is a palindrome, can make a cut
            // Add this substring to our partition
            track.addLast(s.substring(start, i + 1));

            // Recursively process the rest of the string
            backtrack(s, i + 1);

            // Backtrack: remove the last substring to try other partitions
            track.removeLast();
        }
    }

    // Helper function to check if s[lo..hi] is a palindrome using two-pointer technique
    boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }
}