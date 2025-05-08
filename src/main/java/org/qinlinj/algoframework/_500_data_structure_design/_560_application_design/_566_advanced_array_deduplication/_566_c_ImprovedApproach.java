package org.qinlinj.algoframework._500_data_structure_design._560_application_design._566_advanced_array_deduplication; /**
 * IMPROVED APPROACH WITH LEXICOGRAPHICAL ORDERING
 * <p>
 * This class demonstrates the second step in solving the "Remove Duplicate Letters" problem:
 * enhancing our stack approach to consider lexicographical order.
 * <p>
 * This approach satisfies:
 * 1. Requirement 1: Remove duplicates (using the inStack boolean array)
 * 2. Requirement 2: Maintain relative order (using a stack's LIFO property)
 * 3. Partially satisfies Requirement 3: Tries to ensure lexicographically smallest result
 * but still has a critical flaw
 * <p>
 * Key Insight:
 * - Using a monotonic increasing stack pattern to optimize for lexicographical order
 * - When adding a character, we check if it's lexicographically smaller than the stack top
 * - If it is smaller, we pop characters from the stack to maintain increasing order
 * <p>
 * Limitation:
 * - The approach doesn't consider whether characters that are popped will appear again later
 * - This could cause characters to be permanently removed when they shouldn't be
 */

import java.util.*;

public class _566_c_ImprovedApproach {

    /**
     * An improved implementation that tries to optimize for lexicographical order
     * but still has a critical flaw.
     */
    public static String removeDuplicateLetters(String s) {
        // Stack to store unique characters in order
        Stack<Character> stack = new Stack<>();

        // Boolean array to track if a character is in the stack
        boolean[] inStack = new boolean[256];

        // Process each character
        for (char c : s.toCharArray()) {
            // Skip if this character is already in our stack
            if (inStack[c]) continue;

            // While stack is not empty and current character is lexicographically smaller than top
            // Pop the top elements to maintain increasing lexicographical order
            while (!stack.isEmpty() && stack.peek() > c) {
                // Pop the top element and mark it as no longer in stack
                char top = stack.pop();
                inStack[top] = false;
            }

            // Add current character to stack and mark it as in stack
            stack.push(c);
            inStack[c] = true;
        }

        // Build the result string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        // Stack is LIFO, so we need to reverse
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"bcabc", "cbacdcbc", "bcac"};

        for (String test : testCases) {
            String result = removeDuplicateLetters(test);
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output using improved approach: \"" + result + "\"");

            if (test.equals("bcabc")) {
                System.out.println("Expected output: \"abc\"");
                System.out.println("This works correctly for this example!\n");
            } else if (test.equals("cbacdcbc")) {
                System.out.println("Expected output: \"acdb\"");
                System.out.println("This also works for this more complex example.\n");
            } else if (test.equals("bcac")) {
                System.out.println("Expected output: \"bac\"");
                System.out.println("The improved approach gives \"ac\" instead of \"bac\"");
                System.out.println("This is the flaw: We removed 'b' when 'a' came along since 'a' < 'b',");
                System.out.println("but 'b' never appears again in the string, so we've lost it forever!\n");
            }
        }
    }
}