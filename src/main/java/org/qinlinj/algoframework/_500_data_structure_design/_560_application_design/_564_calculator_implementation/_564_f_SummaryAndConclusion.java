package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation; /**
 * Calculator Implementation Summary and Conclusion
 * <p>
 * This class summarizes the incremental approach we took to build a comprehensive calculator.
 * It demonstrates how complex problems can be broken down into manageable steps,
 * each building upon the previous one.
 * <p>
 * Key Development Steps:
 * <p>
 * 1. String to Integer Conversion
 * - The fundamental building block
 * - Demonstrated character-by-character processing
 * - Highlighted important implementation details (parentheses for overflow prevention)
 * <p>
 * 2. Basic Calculator with Addition/Subtraction
 * - Introduced stack-based approach to handle operations
 * - Used sign tracking to handle both operations
 * - Unified approach of pushing positive/negative numbers then summing
 * <p>
 * 3. Enhanced Calculator with Multiplication/Division
 * - Maintained the stack approach
 * - Implemented operator precedence (MDAS)
 * - Added immediate calculation for higher precedence operators
 * - Added space handling
 * <p>
 * 4. Complete Calculator with Parentheses
 * - Introduced recursion to handle nested expressions
 * - Used HashMap to track matching parentheses
 * - Combined all previous components
 * <p>
 * 5. Final Calculator Implementation
 * - Clean, complete implementation with all features
 * - Proper handling of edge cases
 * - Added support for negative numbers
 * <p>
 * This incremental approach demonstrates effective problem-solving:
 * 1. Break down complex problems into smaller parts
 * 2. Solve each part independently
 * 3. Combine solutions while maintaining core functionality
 * 4. Refine the implementation to handle edge cases
 * <p>
 * The resulting calculator is robust, efficient, and maintainable.
 */

import java.util.*;

public class _564_f_SummaryAndConclusion {

    public static void main(String[] args) {
        System.out.println("Building a Calculator: Step-by-Step Approach");
        System.out.println("===========================================");

        // Demonstrate the evolution of our calculator
        demonstrateEvolution();

        // Discuss algorithm complexity and optimization
        discussComplexity();

        // Provide real-world applications
        discussApplications();
    }

    /**
     * Demonstrates the evolution of our calculator implementation
     */
    private static void demonstrateEvolution() {
        String testExpression = "3 * (2 - 6 / (3 - 7))";  // Should evaluate to 9

        System.out.println("\nEVOLUTION OF CALCULATOR IMPLEMENTATION");
        System.out.println("-------------------------------------");
        System.out.println("Test Expression: " + testExpression);
        System.out.println();

        System.out.println("Step 1: String to Integer Conversion");
        System.out.println("- Cannot directly evaluate the full expression");
        System.out.println("- But can parse individual numbers: '3', '2', '6', etc.");
        System.out.println();

        System.out.println("Step 2: Basic Calculator (Addition/Subtraction)");
        System.out.println("- Cannot handle multiplication, division, or parentheses");
        System.out.println("- Could evaluate simplified expressions like '3 + 2 - 6'");
        System.out.println();

        System.out.println("Step 3: Enhanced Calculator (All Operations)");
        System.out.println("- Handles operator precedence");
        System.out.println("- Still cannot handle parentheses");
        System.out.println("- Could evaluate '3 * 2 - 6 / 3'");
        System.out.println();

        System.out.println("Step 4: Complete Calculator (With Parentheses)");
        System.out.println("- Handles all aspects of the expression");
        System.out.println("- Evaluates: 3 * (2 - 6 / (3 - 7))");
        System.out.println("  → 3 * (2 - 6 / (-4))");
        System.out.println("  → 3 * (2 - (-1))");
        System.out.println("  → 3 * 3");
        System.out.println("  → 9");
        System.out.println();

        // Create and use the complete calculator
        Calculator calculator = new Calculator();
        int result = calculator.calculate(testExpression);
        System.out.println("Final Result: " + result);
    }

    /**
     * Discusses the time and space complexity of our calculator
     */
    private static void discussComplexity() {
        System.out.println("\nALGORITHM COMPLEXITY");
        System.out.println("-------------------");
        System.out.println("Time Complexity: O(n)");
        System.out.println("- We process each character of the input string exactly once");
        System.out.println("- Building the parentheses map: O(n)");
        System.out.println("- Evaluating expressions: O(n)");
        System.out.println("- Stack operations are O(1)");
        System.out.println();

        System.out.println("Space Complexity: O(n)");
        System.out.println("- Stack size proportional to the number of operations: O(n)");
        System.out.println("- Parentheses map: O(n) in the worst case");
        System.out.println("- Recursive call stack: O(d) where d is the nesting depth");
        System.out.println("  (In the worst case, d could be n/2)");
    }

    /**
     * Discusses real-world applications of expression evaluation
     */
    private static void discussApplications() {
        System.out.println("\nREAL-WORLD APPLICATIONS");
        System.out.println("----------------------");
        System.out.println("1. Calculator Applications");
        System.out.println("   - Scientific calculators");
        System.out.println("   - Spreadsheet formula evaluation (Excel, Google Sheets)");
        System.out.println();

        System.out.println("2. Programming Language Interpreters");
        System.out.println("   - Expression evaluation in programming languages");
        System.out.println("   - REPL (Read-Eval-Print Loop) environments");
        System.out.println();

        System.out.println("3. Mathematical Software");
        System.out.println("   - Computer algebra systems");
        System.out.println("   - Numerical computing tools");
        System.out.println();

        System.out.println("4. Query Languages");
        System.out.println("   - SQL expression evaluation");
        System.out.println("   - Search query processing");
        System.out.println();

        System.out.println("The techniques demonstrated here (stack-based processing, recursion,");
        System.out.println("and incremental problem solving) are applicable to many other");
        System.out.println("complex parsing and evaluation problems.");
    }

    /**
     * Simplified calculator implementation for demonstration
     */
    static class Calculator {
        public int calculate(String s) {
            // Map to store matching parentheses indices
            Map<Integer, Integer> parenthesesMap = new HashMap<>();
            Stack<Integer> stack = new Stack<>();

            // Build parentheses map
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else if (s.charAt(i) == ')') {
                    if (!stack.isEmpty()) {
                        parenthesesMap.put(stack.pop(), i);
                    }
                }
            }

            // Evaluate expression
            return evaluate(s, 0, s.length() - 1, parenthesesMap);
        }

        private int evaluate(String s, int start, int end, Map<Integer, Integer> parenthesesMap) {
            Stack<Integer> stack = new Stack<>();
            int num = 0;
            char sign = '+';

            for (int i = start; i <= end; i++) {
                char c = s.charAt(i);

                if (c == ' ') continue;

                if (Character.isDigit(c)) {
                    num = 10 * num + (c - '0');
                } else if (c == '(') {
                    // Find matching closing parenthesis
                    int j = parenthesesMap.get(i);
                    // Recursively evaluate the expression inside parentheses
                    num = evaluate(s, i + 1, j - 1, parenthesesMap);
                    i = j;
                }

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
                    sign = c;
                    num = 0;
                }
            }

            int result = 0;
            while (!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }
}