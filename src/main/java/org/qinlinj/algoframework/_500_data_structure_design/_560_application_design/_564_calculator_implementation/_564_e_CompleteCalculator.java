package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation; /**
 * Final Complete Calculator Implementation
 * <p>
 * This class provides a clean, complete implementation of our calculator that can handle:
 * - Addition, subtraction, multiplication, and division
 * - Proper order of operations (PEMDAS)
 * - Parentheses with arbitrary nesting
 * - Spaces in the expression
 * - Integer division that follows the rule of truncating toward zero
 * <p>
 * Key Features:
 * 1. Comprehensive input support for all standard calculator operations
 * 2. Recursive evaluation to handle nested parentheses
 * 3. Stack-based approach to handle operator precedence
 * 4. Proper handling of integer division (truncation toward zero)
 * 5. Clean, organized code structure for maintainability
 * <p>
 * This implementation demonstrates how complex problems can be broken down into
 * smaller, manageable components and solved incrementally.
 * <p>
 * Time Complexity: O(n) where n is the length of the expression string
 * Space Complexity: O(n) for the stack and recursion call stack
 */

import java.util.*;

public class _564_e_CompleteCalculator {

    public static void main(String[] args) {
        // Test cases for the calculator
        String[] testExpressions = {
                "3 * (2 - 6 / (3 - 7))",    // 9
                "1 + 2 * 3 - 4 / 2",        // 5
                "42",                        // 42
                "(1 + 2) * (3 + 4)",        // 21
                "5 / 2",                     // 2 (integer division)
                "-5 / 2",                    // -2 (truncating toward zero)
                "10 * (2 + (3 - 1) * 3)"    // 50
        };

        Calculator calculator = new Calculator();

        System.out.println("Calculator Test Results:");
        System.out.println("=======================");

        for (String expression : testExpressions) {
            int result = calculator.calculate(expression);
            System.out.println(expression + " = " + result);
        }

        // Run comprehensive tests
        System.out.println("\nRunning comprehensive tests:");
        CalculatorTester.runTests();

        // Interactive calculator demo
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInteractive Calculator (type 'exit' to quit)");
        System.out.println("Supported operations: +, -, *, /, (, )");
        System.out.println("----------------------------------------------");

        while (true) {
            System.out.print("\nEnter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int result = calculator.calculate(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: Invalid expression");
            }
        }
    }

    /**
     * Complete calculator implementation with all features
     */
    static class Calculator {
        /**
         * Main method to calculate the result of an expression
         *
         * @param s The expression to evaluate
         * @return The result of the expression
         */
        public int calculate(String s) {
            // Map to store matching parentheses indices
            Map<Integer, Integer> parenthesesMap = buildParenthesesMap(s);

            // Evaluate the expression
            return evaluateExpression(s, 0, s.length() - 1, parenthesesMap);
        }

        /**
         * Builds a map of matching parentheses: key=left parenthesis index, value=right parenthesis index
         *
         * @param s The expression string
         * @return Map mapping left parenthesis indices to matching right parenthesis indices
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
         *
         * @param s The expression string
         * @param start The start index (inclusive)
         * @param end The end index (inclusive)
         * @param parenthesesMap Map of matching parentheses
         * @return Result of evaluating the expression
         */
        private int evaluateExpression(String s, int start, int end, Map<Integer, Integer> parenthesesMap) {
            Stack<Integer> stack = new Stack<>();
            int num = 0;
            char sign = '+';
            boolean hasDigit = false; // To track if we've seen a digit for handling negative numbers

            for (int i = start; i <= end; i++) {
                char c = s.charAt(i);

                // Skip spaces
                if (c == ' ') {
                    continue;
                }

                // Handle digits: build up the current number
                if (Character.isDigit(c)) {
                    num = 10 * num + (c - '0');
                    hasDigit = true;
                }

                // Handle parentheses: recursively evaluate the inner expression
                if (c == '(') {
                    // Find the matching closing parenthesis
                    int j = findClosingParenthesis(i, parenthesesMap);

                    // Recursively evaluate the expression inside the parentheses
                    num = evaluateExpression(s, i + 1, j - 1, parenthesesMap);
                    hasDigit = true;

                    // Skip to after the closing parenthesis
                    i = j;
                }

                // Handle unary plus/minus at the beginning of an expression or after an operator
                if (!hasDigit && (c == '+' || c == '-')) {
                    if (c == '-') {
                        sign = '-';
                    }
                    continue;
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
                            int divisor = num;
                            int dividend = stack.pop();

                            // Integer division truncating toward zero
                            int result = dividend / divisor;
                            stack.push(result);
                            break;
                    }

                    // Update the sign and reset the number
                    sign = c;
                    num = 0;
                    hasDigit = false;
                }
            }

            // Sum all numbers in the stack to get the final result
            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }

        /**
         * Helper method to find the matching closing parenthesis
         *
         * @param openIndex The index of the opening parenthesis
         * @param parenthesesMap Map of matching parentheses
         * @return The index of the matching closing parenthesis
         */
        private int findClosingParenthesis(int openIndex, Map<Integer, Integer> parenthesesMap) {
            return parenthesesMap.get(openIndex);
        }
    }

    /**
     * A class to demonstrate testing the calculator with various expressions
     */
    static class CalculatorTester {
        /**
         * Run calculator tests with expected results to verify correctness
         */
        public static void runTests() {
            Calculator calculator = new Calculator();
            Map<String, Integer> testCases = new HashMap<>();

            // Basic arithmetic
            testCases.put("1+1", 2);
            testCases.put("2-1", 1);
            testCases.put("2*3", 6);
            testCases.put("6/2", 3);

            // Order of operations
            testCases.put("1+2*3", 7);
            testCases.put("2*3+4", 10);
            testCases.put("2+2*3+4", 12);
            testCases.put("10-2*3", 4);

            // Parentheses
            testCases.put("(1+2)*3", 9);
            testCases.put("2*(3+4)", 14);
            testCases.put("(2+3)*(4+5)", 45);

            // Nested parentheses
            testCases.put("2*((3+4)+5)", 24);
            testCases.put("((1+2)*3)+4", 13);
            testCases.put("3*(2-(6/(3-7)))", 9);

            // Spaces
            testCases.put(" 1 + 2 ", 3);
            testCases.put("2 * 3", 6);
            testCases.put("  (  1  +  2  )  *  3  ", 9);

            // Integer division
            testCases.put("5/2", 2);
            testCases.put("-5/2", -2);
            testCases.put("7/3", 2);

            // Negative numbers
            testCases.put("-1+2", 1);
            testCases.put("2*-3", -6);
            testCases.put("-2*-3", 6);
            testCases.put("-(2+3)", -5);

            // Complex expressions
            testCases.put("1+2+3+4+5", 15);
            testCases.put("10-5-3", 2);
            testCases.put("2*3*4", 24);
            testCases.put("10/2/5", 1);
            testCases.put("1+2*3-4/2", 6);
            testCases.put("2*(3+4)*(5-1)", 56);
            testCases.put("((2+3)*4)+((6-1)*3)", 35);

            // Run the tests
            int passed = 0;
            int total = testCases.size();

            for (Map.Entry<String, Integer> testCase : testCases.entrySet()) {
                String expression = testCase.getKey();
                int expected = testCase.getValue();

                try {
                    int result = calculator.calculate(expression);
                    if (result == expected) {
                        passed++;
                        System.out.println("✓ " + expression + " = " + result);
                    } else {
                        System.out.println("✗ " + expression + " = " + result + " (Expected: " + expected + ")");
                    }
                } catch (Exception e) {
                    System.out.println("✗ " + expression + " - Error: " + e.getMessage());
                }
            }

            System.out.println("\nTest Summary: " + passed + "/" + total + " tests passed");
        }
    }
}