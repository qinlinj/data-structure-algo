package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation; /**
 * Complete Calculator with Parentheses Support
 * <p>
 * This class implements a fully-functional calculator that can handle:
 * - Addition, subtraction, multiplication, and division
 * - Proper order of operations (PEMDAS)
 * - Parentheses with arbitrary nesting
 * - Spaces in the expression
 * <p>
 * Key Algorithm Enhancements:
 * 1. We use recursion to handle parentheses - treating each parenthetical expression as a subproblem
 * 2. We first preprocess the expression to identify matching pairs of parentheses
 * 3. When we encounter a left parenthesis, we recursively evaluate the expression inside it
 * 4. The recursive structure naturally handles nested parentheses
 * <p>
 * Implementation Approach:
 * 1. Use a HashMap to store the indices of matching parentheses
 * 2. Use a recursive helper function to evaluate expressions within a specific range
 * 3. The base calculator logic remains the same, with added logic for parentheses
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) for the stack and recursion call stack
 */

import java.util.*;

public class _564_d_CalculatorWithParentheses {

    public static void main(String[] args) {
        // Test the calculator with expressions involving parentheses
        String[] expressions = {
                "3 * (2 - 6 / (3 - 7))",   // 9
                "1 + (2 * 3) - 4",         // 3
                "5 + 3 * (4 - 2)",         // 11
                "10 / (2 + 3)",            // 2
                "2 * (3 + (4 * 5))",       // 46
                "((1 + 2) * 3) + 4"        // 13
        };

        for (String expr : expressions) {
            Calculator calculator = new Calculator();
            int result = calculator.calculate(expr);
            System.out.println("Expression: " + expr + " = " + result);
        }

        // Detailed explanation of the first example
        System.out.println("\nDetailed calculation of '3 * (2 - 6 / (3 - 7))':");
        System.out.println("= 3 * (2 - 6 / (-4))");
        System.out.println("= 3 * (2 - (-1))");
        System.out.println("= 3 * 3");
        System.out.println("= 9");
    }

    static class Calculator {
        // Map to store matching parentheses: key=left parenthesis index, value=right parenthesis index
        private Map<Integer, Integer> parenthesesMap;

        /**
         * Main calculate method that handles the entire expression
         */
        public int calculate(String s) {
            // Build the parentheses map
            parenthesesMap = buildParenthesesMap(s);

            // Evaluate the entire expression
            return evaluateExpression(s, 0, s.length() - 1);
        }

        /**
         * Creates a map of matching parentheses indices
         */
        private Map<Integer, Integer> buildParenthesesMap(String s) {
            Map<Integer, Integer> map = new HashMap<>();
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (s.charAt(i) == ')') {
                    if (!stack.isEmpty()) {
                        map.put(stack.pop(), i);
                    }
                }
            }

            return map;
        }

        /**
         * Recursively evaluates the expression between start and end indices
         */
        private int evaluateExpression(String s, int start, int end) {
            Stack<Integer> stack = new Stack<>();
            int num = 0;
            char sign = '+';

            for (int i = start; i <= end; i++) {
                char c = s.charAt(i);

                // Skip spaces
                if (c == ' ') {
                    continue;
                }

                // Handle digits: build up the current number
                if (Character.isDigit(c)) {
                    num = 10 * num + (c - '0');
                }

                // Handle parentheses: recursively evaluate the inner expression
                if (c == '(') {
                    // Find the matching closing parenthesis
                    int j = parenthesesMap.get(i);

                    // Recursively evaluate the expression inside the parentheses
                    num = evaluateExpression(s, i + 1, j - 1);

                    // Skip to after the closing parenthesis
                    i = j;
                }

                // Process the operator (or end of expression)
                if (c == '+' || c == '-' || c == '*' || c == '/' || i == end) {
                    switch (sign) {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            stack.push(stack.pop() * num);
                            break;
                        case '/':
                            stack.push(stack.pop() / num);
                            break;
                    }

                    // Update the sign and reset the number
                    sign = c;
                    num = 0;
                }
            }

            // Sum all numbers in the stack to get the final result
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }
}