package org.qinlinj.algoframework._500_data_structure_design._560_application_design._566_advanced_array_deduplication; /**
 * OPTIMAL SOLUTION TO REMOVE DUPLICATE LETTERS
 * <p>
 * This class provides the complete solution to the "Remove Duplicate Letters" problem
 * (LeetCode 316) using a monotonic stack approach combined with character counting.
 * <p>
 * This approach satisfies all three requirements:
 * 1. Remove duplicates (using the inStack boolean array)
 * 2. Maintain relative order (using a stack's LIFO property)
 * 3. Ensure lexicographically smallest result (using monotonic stack with character frequency)
 * <p>
 * Key Insights:
 * 1. We use a monotonic stack to maintain increasing lexicographical order
 * 2. Before removing a character from the stack, we check if it occurs later in the string
 * 3. Only remove a character if it occurs again later, otherwise we must keep it
 * <p>
 * Algorithm Steps:
 * 1. Count frequency of each character to know if it appears later
 * 2. Process each character:
 * - Decrease its count (as we've processed one occurrence)
 * - Skip if already in the result stack
 * - Try to pop stack elements that are lexicographically larger
 * (but only if they occur again later in the string)
 * - Add current character to the result
 * <p>
 * Time Complexity: O(n) where n is the length of the input string
 * Space Complexity: O(k) where k is the number of unique characters (26 for lowercase letters)
 */

import java.util.*;

public class _566_d_FinalSolution {

    /**
     * Removes duplicate letters from a string to achieve the lexicographically
     * smallest result while maintaining the original relative order.
     */
    public static String removeDuplicateLetters(String s) {
        // Stack to store unique characters in order
        Stack<Character> stack = new Stack<>();

        // Boolean array to track if a character is in the stack
        boolean[] inStack = new boolean[256];

        // Count array to track remaining occurrences of each character
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        // Process each character
        for (char c : s.toCharArray()) {
            // Decrease count as we've seen one occurrence
            count[c]--;

            // Skip if this character is already in our stack
            if (inStack[c]) continue;

            // Try to pop characters that are lexicographically larger
            while (!stack.isEmpty() && stack.peek() > c) {
                // Critical check: only pop if this character appears again later
                if (count[stack.peek()] == 0) {
                    break; // Can't remove - this is the last occurrence
                }

                // Safe to remove - it occurs again later
                char top = stack.pop();
                inStack[top] = false;
            }

            // Add current character
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
            System.out.println("Output: \"" + result + "\"");

            if (test.equals("bcabc")) {
                System.out.println("Expected: \"abc\"");
                System.out.println("Explanation: The characters are processed in order.");
                System.out.println("When 'a' is encountered, 'b' and 'c' are popped because");
                System.out.println("they are larger and occur again later in the string.\n");
            } else if (test.equals("cbacdcbc")) {
                System.out.println("Expected: \"acdb\"");
                System.out.println("Explanation: Complex example demonstrating the full algorithm.\n");
            } else if (test.equals("bcac")) {
                System.out.println("Expected: \"bac\"");
                System.out.println("Explanation: When 'a' is encountered, we can't pop 'b'");
                System.out.println("because it never occurs again in the string.\n");
            }
        }
    }
}