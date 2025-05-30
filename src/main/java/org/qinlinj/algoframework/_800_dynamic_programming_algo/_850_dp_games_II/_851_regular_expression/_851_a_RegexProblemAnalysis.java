package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._851_regular_expression;

/**
 * REGULAR EXPRESSION MATCHING - PROBLEM ANALYSIS
 * <p>
 * OVERVIEW:
 * This class demonstrates the fundamental concepts of regular expression matching,
 * specifically focusing on two key wildcards: '.' and '*'.
 * <p>
 * KEY CONCEPTS:
 * 1. '.' wildcard: Matches any single character
 * 2. '*' wildcard: Allows the preceding character to repeat 0 or more times
 * 3. Pattern matching requires exhaustive exploration of all possibilities
 * 4. Dynamic programming is needed to handle overlapping subproblems
 * <p>
 * EXAMPLES:
 * - Pattern ".a*b" matches "zaaab" (. matches z, a* matches aa, b matches b)
 * - Pattern ".a*b" matches "cb" (. matches c, a* matches 0 times, b matches b)
 * - Pattern "a..b" matches "amnb" (a matches a, .. matches mn, b matches b)
 * - Pattern ".*" matches any text (universal matcher)
 * <p>
 * CHALLENGES:
 * - '*' wildcard creates multiple matching possibilities
 * - Need to explore all paths: 0 matches, 1 match, multiple matches
 * - Requires systematic approach to avoid missing valid matches
 */
public class _851_a_RegexProblemAnalysis {
    /**
     * Basic pattern matching without '*' wildcard
     * This is the foundation before adding complexity
     */
    public boolean basicMatch(String s, String p) {
        int i = 0, j = 0;

        while (i < s.length() && j < p.length()) {
            // '.' wildcard is universal matcher
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                // Characters match, continue with next positions
                i++;
                j++;
            } else {
                // No match found
                return false;
            }
        }

        // Both strings should be fully consumed for complete match
        return i == s.length() && j == p.length();
    }

    /**
     * Demonstrates the complexity introduced by '*' wildcard
     * Shows different scenarios that need to be handled
     */
    public void demonstrateStarWildcardScenarios() {
        System.out.println("=== Star Wildcard Scenarios ===");

        // Scenario 1: Multiple character matches
        System.out.println("Pattern 'a*' with text 'aaa':");
        System.out.println("- 'a' can match 3 times through '*'");

        // Scenario 2: Zero matches
        System.out.println("Pattern 'a*aa' with text 'aa':");
        System.out.println("- 'a*' matches 0 times, remaining 'aa' matches text");

        // Scenario 3: Forced zero matches due to mismatch
        System.out.println("Pattern 'b*aa' with text 'aa':");
        System.out.println("- 'b*' must match 0 times since 'b' doesn't match 'a'");
    }

    /**
     * Illustrates the decision tree for '*' wildcard handling
     */
    public void illustrateDecisionProcess() {
        System.out.println("\n=== Decision Process for '*' Wildcard ===");
        System.out.println("When encountering pattern[j] followed by '*':");
        System.out.println("1. If text[i] matches pattern[j]:");
        System.out.println("   - Try matching 0 times (skip pattern[j]*) ");
        System.out.println("   - Try matching 1+ times (consume text[i], keep pattern[j]*)");
        System.out.println("2. If text[i] doesn't match pattern[j]:");
        System.out.println("   - Must match 0 times (skip pattern[j]*)");
    }

}
