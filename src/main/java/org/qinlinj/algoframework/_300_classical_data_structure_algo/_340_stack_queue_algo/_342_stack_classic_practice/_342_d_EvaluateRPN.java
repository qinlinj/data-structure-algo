package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 150: Evaluate Reverse Polish Notation
 * <p>
 * Problem Description:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation (RPN).
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * Division between two integers should truncate toward zero.
 * <p>
 * Reverse Polish Notation (RPN) is a mathematical notation where every operator
 * follows all of its operands. This eliminates the need for parentheses.
 * <p>
 * Solution Approach:
 * The stack is perfectly suited for evaluating RPN expressions:
 * <p>
 * 1. Process each token in the array:
 * - If the token is a number, push it onto the stack
 * - If the token is an operator, pop the top two numbers from the stack,
 * apply the operator, and push the result back onto the stack
 * 2. After processing all tokens, the stack should contain only one element: the final result
 * <p>
 * Note: For division and subtraction, the order matters. The second popped value is
 * the left operand, and the first popped value is the right operand.
 * <p>
 * Time Complexity: O(n) where n is the number of tokens
 * Space Complexity: O(n) for the stack in the worst case
 */

import java.util.*;

public class _342_d_EvaluateRPN {
    // Example usage
    public static void main(String[] args) {
        _342_d_EvaluateRPN solution = new _342_d_EvaluateRPN();

        // Example 1: ((2 + 1) * 3) = 9
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println(solution.evalRPN(tokens1)); // 9

        // Example 2: (4 + (13 / 5)) = 6
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println(solution.evalRPN(tokens2)); // 6

        // Example 3: More complex expression
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(solution.evalRPN(tokens3)); // 22
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            // Check if the token is an operator
            if (isOperator(token)) {
                // Pop the top two numbers (order matters!)
                int b = stack.pop(); // Right operand (popped first)
                int a = stack.pop(); // Left operand (popped second)

                // Apply the operator and push the result
                int result = applyOperator(a, b, token);
                stack.push(result);
            } else {
                // Token is a number, push it onto the stack
                stack.push(Integer.parseInt(token));
            }
        }

        // The final result should be the only item left in the stack
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/");
    }

    private int applyOperator(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}