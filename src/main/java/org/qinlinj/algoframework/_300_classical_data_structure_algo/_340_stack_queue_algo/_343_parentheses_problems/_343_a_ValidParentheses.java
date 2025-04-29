package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._343_parentheses_problems;

/**
 * Bracket Problems Summary
 * <p>
 * This class summarizes three classic bracket matching problems and their solutions.
 * All problems require understanding of bracket validation rules and stack-based approaches.
 * <p>
 * The three problems covered are:
 * 1. Valid Parentheses (LeetCode 20) - Determine if a string of brackets is valid
 * 2. Minimum Add to Make Parentheses Valid (LeetCode 921) - Find minimum insertions to make valid
 * 3. Minimum Insertions to Balance Parentheses (LeetCode 1541) - Special case where 1 left bracket needs 2 right brackets
 * <p>
 * Key techniques:
 * - Using stack to track unclosed brackets
 * - Tracking bracket demand/need with counters
 * - Special handling for different matching rules
 */

/**
 * Problem 1: Valid Parentheses (LeetCode 20)
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * A string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */
class _343_a_ValidParentheses {

    public static void main(String[] args) {
        // Test valid parentheses
        _343_a_ValidParentheses validChecker = new _343_a_ValidParentheses();
        System.out.println("'()' is valid: " + validChecker.isValid("()"));
        System.out.println("'()[]{}' is valid: " + validChecker.isValid("()[]{}"));
        System.out.println("'(]' is valid: " + validChecker.isValid("(]"));

        // Test minimum add to make valid
        _343_b_MinAddToMakeValid minAddChecker = new _343_b_MinAddToMakeValid();
        System.out.println("Minimum insertions for '())': " + minAddChecker.minAddToMakeValid("())"));
        System.out.println("Minimum insertions for '(((': " + minAddChecker.minAddToMakeValid("((("));

        // Test minimum insertions to balance (1:2 matching rule)
        _343_c_MinInsertionsToBalance minInsertChecker = new _343_c_MinInsertionsToBalance();
        System.out.println("Minimum insertions for '(()))': " + minInsertChecker.minInsertions("(()))"));
        System.out.println("Minimum insertions for '())))(': " + minInsertChecker.minInsertions("())))("));
        System.out.println("Minimum insertions for '((((((': " + minInsertChecker.minInsertions("(((((("));
    }

    /**
     * Simple approach for just one type of brackets
     */
    public boolean isValidSimple(String str) {
        // Counter for unmatched left brackets
        int left = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                left++;
            } else {
                // Found a right bracket
                left--;
            }

            // If left becomes negative, we have a right bracket with no matching left
            if (left < 0) {
                return false;
            }
        }

        // All brackets should be matched for a valid string
        return left == 0;
    }

    /**
     * Solution for multiple types of brackets using a stack
     */
    public boolean isValid(String str) {
        // Stack to track unclosed left brackets
        java.util.Stack<Character> left = new java.util.Stack<>();

        for (char c : str.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                left.push(c);
            } else {
                // It's a closing bracket
                // Check if stack is empty or if the top doesn't match
                if (left.isEmpty() || leftOf(c) != left.peek()) {
                    return false;
                }
                // Pop the matching opening bracket
                left.pop();
            }
        }

        // If stack is empty, all brackets were matched
        return left.isEmpty();
    }

    /**
     * Helper function to get the corresponding left bracket for a right bracket
     */
    private char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '['; // For ']'
    }
}