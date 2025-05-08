package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation; /**
 * Calculator with Addition, Subtraction, Multiplication and Division
 * <p>
 * This class extends our calculator to handle all four basic arithmetic operations
 * while respecting the standard order of operations (multiplication and division
 * take precedence over addition and subtraction).
 * <p>
 * Algorithm Enhancement:
 * 1. We continue using a stack to store numbers
 * 2. For addition and subtraction, we push the number to the stack (with appropriate sign)
 * 3. For multiplication and division, we pop the top number from the stack,
 * perform the operation with the current number, and push the result back
 * 4. This approach automatically handles the precedence of operations:
 * - Multiplication and division are calculated immediately with the previous number
 * - Addition and subtraction are deferred until all numbers are processed
 * 5. We also handle spaces by simply ignoring them during processing
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in the worst case for the stack
 */

import java.util.*;

public class _564_c_CalculatorWithMultiplyDivide {

    public static void main(String[] args) {
        // Test the calculator with expressions involving all four operations
        String[] expressions = {
                "2-3*4+5",           // -5
                "10/2+3*4",          // 17
                "8-4/2*3+1",         // 3
                "20 / 4 * 3 - 2",    // 13 (with spaces)
                "1 + 2 * 3 / 6 + 4"  // 6 (with spaces)
        };

        for (String expr : expressions) {
            int result = calculate(expr);
            System.out.println("Expression: " + expr + " = " + result);
        }

        // Visualize the calculation process for a specific example
        System.out.println("\nDetailed calculation process for '2-3*4+5':");
        visualizeCalculation("2-3*4+5");
    }

    /**
     * Calculates the result of an expression with all four basic operations
     */
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // Record the current number being processed
        int num = 0;
        // Record the sign before the current number, initialize as '+'
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If it's a digit, continue building the current number
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }

            // Skip spaces
            if (c == ' ') {
                continue;
            }

            // If it's an operator or the end of the string, process the current number
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
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

    /**
     * Visualizes the calculation process for a given expression
     */
    private static void visualizeCalculation(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';

        System.out.println("Starting with empty stack and sign = '+'");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
                System.out.println("Read digit '" + c + "', current number = " + num);
            }

            // Skip spaces in visualization too
            if (c == ' ') {
                System.out.println("Skipping space character");
                continue;
            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                System.out.print("Processing with sign = '" + sign + "', number = " + num + ": ");

                switch (sign) {
                    case '+':
                        stack.push(num);
                        System.out.println("Push +" + num + " to stack");
                        break;
                    case '-':
                        stack.push(-num);
                        System.out.println("Push -" + num + " to stack");
                        break;
                    case '*':
                        int prev1 = stack.pop();
                        int result1 = prev1 * num;
                        stack.push(result1);
                        System.out.println("Pop " + prev1 + ", calculate " + prev1 + " * " + num + " = " + result1 + ", push result to stack");
                        break;
                    case '/':
                        int prev2 = stack.pop();
                        int result2 = prev2 / num;
                        stack.push(result2);
                        System.out.println("Pop " + prev2 + ", calculate " + prev2 + " / " + num + " = " + result2 + ", push result to stack");
                        break;
                }

                System.out.println("Current stack: " + stack);

                sign = c;
                System.out.println("Update sign to '" + sign + "'");

                num = 0;
                System.out.println("Reset number to 0");
            }
        }

        System.out.println("\nFinal stack: " + stack);

        int result = 0;
        System.out.print("Sum of stack elements: ");

        // Create a temporary stack to print elements in original order
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        while (!tempStack.isEmpty()) {
            int value = tempStack.pop();
            result += value;
            System.out.print(value + (tempStack.isEmpty() ? " = " : " + "));
        }
        System.out.println(result);
    }
}