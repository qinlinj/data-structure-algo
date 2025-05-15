package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 93. Restore IP Addresses
 * <p>
 * Problem Summary:
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * A valid IP address consists of exactly four integers separated by periods, with each integer between 0 and 255.
 * Integers cannot have leading zeros (except 0 itself).
 * <p>
 * Key Insights:
 * - This problem is similar to the palindrome partitioning problem (131)
 * - Instead of palindromes, we need to ensure each part is a valid IP segment
 * - We need to partition the string into exactly 4 segments
 * - Additional constraints:
 * 1. Each segment must be a valid integer between 0 and 255
 * 2. No leading zeros allowed (except for 0 itself)
 * <p>
 * Time Complexity: O(3^4) = O(1) since we make at most 3 choices for each of the 4 segments
 * Space Complexity: O(1) for recursion stack and tracking current partition
 */
class _741_f_RestoreIPAddresses {

    List<String> res = new LinkedList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0);
        return res;
    }

    // Backtracking framework
    void backtrack(String s, int start) {
        if (start == s.length() && track.size() == 4) {
            // Base case: reached leaf node
            // The entire string has been successfully partitioned into 4 valid IP segments
            res.add(String.join(".", track));
        }

        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) {
                // s[start..i] is not a valid IP segment
                continue;
            }

            if (track.size() >= 4) {
                // Already have 4 segments, cannot add more
                break;
            }

            // s[start..i] is a valid IP segment
            // Add this segment to our IP address
            track.addLast(s.substring(start, i + 1));

            // Recursively process the rest of the string
            backtrack(s, i + 1);

            // Backtrack: remove the last segment to try other partitions
            track.removeLast();
        }
    }

    // Helper function to check if s[start..end] is a valid IP segment
    boolean isValid(String s, int start, int end) {
        int length = end - start + 1;

        if (length == 0 || length > 3) {
            return false;
        }

        if (length == 1) {
            // Single digit is always valid
            return true;
        }

        if (s.charAt(start) == '0') {
            // Multiple digits starting with 0 is invalid
            return false;
        }

        if (length <= 2) {
            // Two-digit number is always valid (after excluding leading zeros)
            return true;
        }

        // Now checking three-digit numbers
        if (Integer.parseInt(s.substring(start, start + length)) > 255) {
            // Cannot be greater than 255
            return false;
        } else {
            return true;
        }
    }
}