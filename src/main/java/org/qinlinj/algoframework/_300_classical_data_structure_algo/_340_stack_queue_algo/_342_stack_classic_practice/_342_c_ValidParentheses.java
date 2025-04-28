package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 20: Valid Parentheses
 * <p>
 * Problem Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Solution Approach:
 * The stack data structure is ideal for this problem because of its Last-In-First-Out property.
 * <p>
 * 1. Iterate through each character in the string
 * 2. If the character is an opening bracket ('(', '{', '['), push it onto the stack
 * 3. If the character is a closing bracket (')', '}', ']'):
 * - If the stack is empty, return false (no matching opening bracket)
 * - If the top of the stack isn't the corresponding opening bracket, return false
 * - Otherwise, pop the top of the stack (matching pair found)
 * 4. After processing all characters, the stack should be empty for a valid string
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in the worst case when all characters are opening brackets
 */

import java.util.*;

public class _342_c_ValidParentheses {
    // Example usage
    public static void main(String[] args) {
        _342_c_ValidParentheses solution = new _342_c_ValidParentheses();

        System.out.println(solution.isValid("()")); // true
        System.out.println(solution.isValid("()[]{}")); // true
        System.out.println(solution.isValid("(]")); // false
        System.out.println(solution.isValid("([)]")); // false
        System.out.println(solution.isValid("{[]}")); // true
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                // If the character is an opening bracket, push it onto the stack
                stack.push(c);
            } else {
                // Character is a closing bracket
                if (stack.isEmpty() || !isMatchingPair(stack.peek(), c)) {
                    // No matching opening bracket or wrong type
                    return false;
                }
                // Matching pair found, remove the opening bracket
                stack.pop();
            }
        }

        // Valid if all brackets have been matched (stack is empty)
        return stack.isEmpty();
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    // Alternatively, use a helper method to get the corresponding opening bracket
    private char getOpeningBracket(char c) {
        if (c == ')') return '(';
        if (c == '}') return '{';
        return '['; // For ']'
    }
}
