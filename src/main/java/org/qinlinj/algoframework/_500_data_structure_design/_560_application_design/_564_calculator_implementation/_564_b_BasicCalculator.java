package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation; /**
 * Basic Calculator with Addition and Subtraction
 * <p>
 * This class implements a calculator that can handle expressions with addition and subtraction.
 * It's the second step in building our complete calculator.
 * <p>
 * Algorithm Overview:
 * 1. Use a stack to store the numbers (with their signs)
 * 2. Process the input string character by character:
 * - If it's a digit, continue building the current number
 * - If it's an operator (+/-) or the end of the string, push the current number to the stack
 * with the appropriate sign (determined by the previous operator)
 * 3. Sum all numbers in the stack to get the final result
 * <p>
 * Key Insights:
 * - We can treat the first number as having an implicit '+' sign in front
 * - By tracking the previous operation sign, we can properly push positive or negative numbers
 * - Addition and subtraction can be unified by pushing positive/negative numbers and then summing
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in the worst case for the stack
 */

import java.util.*;

public class _564_b_BasicCalculator {

    public static void main(String[] args) {
        // Test the basic calculator with different expressions
        String[] expressions = {
                "1+2",
                "1-12+3",
                "42-15+7-9",
                "100+200-50"
        };

        for (String expr : expressions) {
            int result = calculate(expr);
            System.out.println("Expression: " + expr + " = " + result);
        }

        // Visualize the calculation process for a specific example
        System.out.println("\nDetailed calculation process for '1-12+3':");
        visualizeCalculation("1-12+3");
    }

    /**
     * Calculates the result of an expression with addition and subtraction
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

            // If it's an operator or the end of the string, process the current number
            if (c == '+' || c == '-' || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
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

            if (c == '+' || c == '-' || i == s.length() - 1) {
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
        while (!stack.isEmpty()) {
            int value = stack.pop();
            result += value;
            System.out.print(value + (stack.isEmpty() ? " = " : " + "));
        }
        System.out.println(result);
    }
}