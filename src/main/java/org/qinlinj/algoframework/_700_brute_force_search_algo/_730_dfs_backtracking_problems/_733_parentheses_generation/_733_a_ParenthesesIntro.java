package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._733_parentheses_generation; /**
 * Parentheses Generation Problem - Introduction
 * <p>
 * This class covers the introduction to the Parentheses Generation problem (LeetCode 22).
 * Parentheses problems can be categorized into two types:
 * 1. Validity checking of parentheses expressions (typically solved using stacks)
 * 2. Generation of valid parentheses combinations (typically solved using backtracking)
 * <p>
 * Key insights about valid parentheses combinations:
 * 1. The number of left parentheses must equal the number of right parentheses.
 * 2. For any prefix substring, the number of left parentheses must be greater than or equal to
 * the number of right parentheses. This ensures proper nesting.
 * <p>
 * Problem statement (LeetCode 22):
 * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 * <p>
 * Example:
 * Input: n = 3
 * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 * <p>
 * Constraints:
 * 1 <= n <= 8
 */

import java.util.*;

public class _733_a_ParenthesesIntro {

    /**
     * Main class to demonstrate the parentheses generation problem
     */
    public static void main(String[] args) {
        System.out.println("=== Parentheses Generation Problem ===");
        System.out.println("Problem: Given n pairs of parentheses, generate all valid combinations.");

        System.out.println("\nProperties of valid parentheses combinations:");
        System.out.println("1. The number of left parentheses equals the number of right parentheses.");
        System.out.println("2. For any prefix substring, the number of left parentheses is >= the number of right parentheses.");

        System.out.println("\nExample for n = 2:");
        List<String> result = generateParenthesis(2);
        System.out.println(result);

        System.out.println("\nExample for n = 3:");
        result = generateParenthesis(3);
        System.out.println(result);
    }

    /**
     * Simple implementation to generate all valid parentheses combinations.
     * This serves as a basic introduction to the problem before diving into
     * the detailed backtracking approach in the next class.
     */
    private static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    /**
     * Backtracking approach to generate valid parentheses combinations.
     *
     * @param result List to store the valid combinations
     * @param current Current string being built
     * @param open Number of open parentheses used so far
     * @param close Number of close parentheses used so far
     * @param max Total number of pairs needed
     */
    private static void backtrack(List<String> result, StringBuilder current,
                                  int open, int close, int max) {
        // Base case: If we've used all parentheses, add the current string to results
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // We can add an open parenthesis if we haven't used all n
        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // We can add a close parenthesis if it doesn't create an invalid expression
        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    /**
     * Simple example to verify if a given parentheses string is valid.
     * This demonstrates the relationship between validity checking and generation.
     */
    public static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                // If at any point, we have more closing than opening, it's invalid
                if (count < 0) return false;
            }
        }
        // If we have the same number of opening and closing brackets, it's valid
        return count == 0;
    }
}