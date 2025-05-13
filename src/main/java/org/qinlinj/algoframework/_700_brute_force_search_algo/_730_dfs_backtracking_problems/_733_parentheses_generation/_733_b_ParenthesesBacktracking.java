package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._733_parentheses_generation; /**
 * Detailed Backtracking Solution for Parentheses Generation
 * <p>
 * This class implements a comprehensive backtracking solution for generating all
 * valid parentheses combinations (LeetCode 22).
 * <p>
 * The backtracking approach works by building the solution incrementally, making choices
 * at each step, and undoing those choices if they lead to invalid combinations.
 * <p>
 * Key concepts in the backtracking implementation:
 * 1. State tracking: We track the number of remaining left and right parentheses
 * 2. Pruning: We apply constraints to avoid generating invalid combinations
 * 3. Base case: When all parentheses are used, we add the current combination to our result
 * <p>
 * Time Complexity: O(4^n / sqrt(n)) - Related to Catalan numbers
 * Space Complexity: O(n) - For the recursion stack and current combination storage
 */

import java.util.*;

public class _733_b_ParenthesesBacktracking {

    /**
     * Main method for demonstration and testing
     */
    public static void main(String[] args) {
        _733_b_ParenthesesBacktracking solution = new _733_b_ParenthesesBacktracking();

        // Generate and display combinations for different values of n
        for (int n = 1; n <= 4; n++) {
            List<String> combinations = solution.generateParenthesis(n);
            System.out.println("n = " + n + ", Total combinations: " + combinations.size());
            System.out.println(combinations);
            System.out.println();
        }

        // Visualize the backtracking process for n = 2
        solution.visualizeBacktracking(2);

        // Compare with alternative implementation
        System.out.println("\nAlternative implementation for n = 3:");
        List<String> combinations = solution.generateParenthesisAlternative(3);
        System.out.println(combinations);

        // Time complexity discussion
        System.out.println("\nTime Complexity Analysis:");
        System.out.println("- The time complexity is related to Catalan numbers: O(4^n / sqrt(n))");
        System.out.println("- For each position, we have at most 2 choices (left or right parenthesis)");
        System.out.println("- With pruning, we only generate valid combinations");
        System.out.println("- The number of valid combinations is the nth Catalan number");
    }

    /**
     * Main function to generate all valid parentheses combinations
     *
     * @param n Number of pairs of parentheses
     * @return List of all valid combinations
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;

        // Start backtracking with n left and n right parentheses available
        backtrack(result, new StringBuilder(), n, n);
        return result;
    }

    /**
     * Backtracking algorithm to generate valid parentheses combinations
     *
     * @param result List to store valid combinations
     * @param track Current combination being built
     * @param left Number of remaining left parentheses
     * @param right Number of remaining right parentheses
     */
    private void backtrack(List<String> result, StringBuilder track, int left, int right) {
        // Pruning: if remaining right parentheses are fewer than left, invalid state
        if (right < left) return;

        // Pruning: if either count goes negative, invalid state
        if (left < 0 || right < 0) return;

        // Base case: all parentheses are used
        if (left == 0 && right == 0) {
            result.add(track.toString());
            return;
        }

        // Option 1: Try adding a left parenthesis
        track.append('(');
        backtrack(result, track, left - 1, right);
        // Backtrack: remove the last character we added
        track.deleteCharAt(track.length() - 1);

        // Option 2: Try adding a right parenthesis
        track.append(')');
        backtrack(result, track, left, right - 1);
        // Backtrack: remove the last character we added
        track.deleteCharAt(track.length() - 1);
    }

    /**
     * Alternative implementation with clearer pruning conditions
     */
    public List<String> generateParenthesisAlternative(int n) {
        List<String> result = new ArrayList<>();
        backtrackAlternative(result, "", 0, 0, n);
        return result;
    }

    /**
     * Alternative backtracking approach with different parameter tracking
     *
     * @param result List to store valid combinations
     * @param current Current string being built
     * @param open Count of open parentheses used so far
     * @param close Count of close parentheses used so far
     * @param max Total number of pairs needed
     */
    private void backtrackAlternative(List<String> result, String current,
                                      int open, int close, int max) {
        // Base case: We have a complete valid combination
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // Option 1: Add an open parenthesis if we haven't used all n
        if (open < max) {
            backtrackAlternative(result, current + "(", open + 1, close, max);
        }

        // Option 2: Add a close parenthesis if it won't create an invalid expression
        if (close < open) {
            backtrackAlternative(result, current + ")", open, close + 1, max);
        }
    }

    /**
     * Visualize the backtracking process for a small example
     */
    public void visualizeBacktracking(int n) {
        System.out.println("Visualizing backtracking process for n = " + n + ":");
        visualize(new StringBuilder(), n, n, 0);
    }

    /**
     * Helper method to visualize the backtracking process
     */
    private void visualize(StringBuilder sb, int left, int right, int depth) {
        String indent = "  ".repeat(depth);

        // Print current state
        System.out.println(indent + "State: " + (sb.length() > 0 ? sb.toString() : "empty") +
                " (left=" + left + ", right=" + right + ")");

        // Base cases and pruning
        if (right < left) {
            System.out.println(indent + "Prune: right < left");
            return;
        }
        if (left < 0 || right < 0) {
            System.out.println(indent + "Prune: negative count");
            return;
        }
        if (left == 0 && right == 0) {
            System.out.println(indent + "Found valid combination: " + sb.toString());
            return;
        }

        // Try adding left parenthesis
        System.out.println(indent + "Try adding '('");
        sb.append('(');
        visualize(sb, left - 1, right, depth + 1);
        sb.deleteCharAt(sb.length() - 1);

        // Try adding right parenthesis
        System.out.println(indent + "Try adding ')'");
        sb.append(')');
        visualize(sb, left, right - 1, depth + 1);
        sb.deleteCharAt(sb.length() - 1);
    }
}