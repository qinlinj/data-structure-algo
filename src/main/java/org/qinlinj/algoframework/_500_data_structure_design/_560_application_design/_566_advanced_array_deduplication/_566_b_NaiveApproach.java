package org.qinlinj.algoframework._500_data_structure_design._560_application_design._566_advanced_array_deduplication; /**
 * BASIC STACK APPROACH FOR DEDUPLICATION
 * <p>
 * This class demonstrates the first step in solving the "Remove Duplicate Letters" problem:
 * using a stack to keep track of characters while maintaining their relative order.
 * <p>
 * This approach satisfies:
 * 1. Requirement 1: Remove duplicates (using the inStack boolean array)
 * 2. Requirement 2: Maintain relative order (using a stack's LIFO property)
 * <p>
 * However, it does NOT satisfy:
 * 3. Requirement 3: Ensure lexicographically smallest result
 * <p>
 * Key Insight:
 * - Using a stack allows us to process characters in order
 * - A boolean array tracks which characters are already in our result
 * - This approach maintains the relative order of characters in the original string
 * <p>
 * Limitations:
 * - This approach simply takes the first occurrence of each character
 * - It doesn't consider if a better (lexicographically smaller) order is possible
 */

import java.util.*;

public class _566_b_NaiveApproach {

    /**
     * A basic implementation that removes duplicates while maintaining relative order,
     * but doesn't ensure lexicographically smallest result.
     */
    public static String removeDuplicateLetters(String s) {
        // Stack to store unique characters in order
        Stack<Character> stack = new Stack<>();

        // Boolean array to track if a character is in the stack
        // Using 256 to handle ASCII characters
        boolean[] inStack = new boolean[256];

        // Process each character
        for (char c : s.toCharArray()) {
            // Skip if this character is already in our stack
            if (inStack[c]) continue;

            // Add new character to result and mark it as seen
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
        String[] testCases = {"bcabc", "cbacdcbc"};

        for (String test : testCases) {
            String result = removeDuplicateLetters(test);
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Output using naive approach: \"" + result + "\"");

            if (test.equals("bcabc")) {
                System.out.println("Expected output: \"abc\"");
                System.out.println("Explanation: For 'bcabc', the naive approach gives 'bca' instead of 'abc'");
                System.out.println("This is because we're not considering lexicographical order yet.\n");
            } else if (test.equals("cbacdcbc")) {
                System.out.println("Expected output: \"acdb\"");
                System.out.println("Explanation: For 'cbacdcbc', the naive approach gives 'cbad' instead of 'acdb'");
                System.out.println("Again, we're not optimizing for lexicographical order.\n");
            }
        }
    }
}