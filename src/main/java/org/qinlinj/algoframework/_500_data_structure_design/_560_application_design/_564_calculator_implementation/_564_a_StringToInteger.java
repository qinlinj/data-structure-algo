package org.qinlinj.algoframework._500_data_structure_design._560_application_design._564_calculator_implementation;

/**
 * String to Integer Conversion
 * <p>
 * This class demonstrates the fundamental operation of converting a string representation
 * of a number into an integer value, which is the first building block of our calculator.
 * <p>
 * Key Points:
 * 1. We iterate through each character in the string
 * 2. For each digit, we multiply the current result by 10 and add the digit value
 * 3. To get the digit value, we subtract the ASCII value of '0' from the character
 * 4. Parentheses around (c - '0') are important to prevent potential integer overflow
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use a single variable for the result
 */
public class _564_a_StringToInteger {

    public static void main(String[] args) {
        // Test the string to integer conversion
        String[] testStrings = {"458", "123", "9999", "0"};

        for (String s : testStrings) {
            int result = stringToInt(s);
            System.out.println("String: \"" + s + "\" → Integer: " + result);
        }

        // Demonstrate why parentheses are important in (c - '0')
        demonstrateParenthesesImportance();
    }

    /**
     * Converts a string representation of a positive integer to an int value
     */
    public static int stringToInt(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            n = 10 * n + (c - '0');  // Parentheses are important here
        }
        return n;
    }

    /**
     * Demonstrates why parentheses around (c - '0') are important
     */
    private static void demonstrateParenthesesImportance() {
        System.out.println("\nDemonstration of parentheses importance:");

        // ASCII value of '0' is 48
        System.out.println("ASCII value of '0': " + (int) '0');

        char c = '5';  // ASCII value is 53
        System.out.println("ASCII value of '" + c + "': " + (int) c);

        // With parentheses: correct calculation
        int withParentheses = 10 * 0 + (c - '0');
        System.out.println("10 * 0 + (c - '0') = " + withParentheses);

        // Without parentheses: incorrect due to operator precedence
        // This would calculate: 10 * 0 + c - '0' = 0 + 53 - 48 = 5
        // But for larger numbers, this approach could cause unexpected results or overflow
        int withoutParentheses = 10 * 0 + c - '0';
        System.out.println("10 * 0 + c - '0' = " + withoutParentheses);

        // For larger values, the difference becomes more apparent
        int largeNum = 100000000;
        char d = '9';

        // With parentheses ensures proper calculation order
        // 10 * largeNum + (d - '0') = 1000000009
        withParentheses = 10 * largeNum + (d - '0');

        // Without parentheses, the addition happens before subtraction
        // 10 * largeNum + d - '0' = 1000000000 + 57 - 48 = 1000000009
        // This looks okay for this example, but with numbers closer to MAX_INT,
        // the intermediate result could overflow
        withoutParentheses = 10 * largeNum + d - '0';

        System.out.println("For larger numbers:");
        System.out.println("10 * " + largeNum + " + (d - '0') = " + withParentheses);
        System.out.println("10 * " + largeNum + " + d - '0' = " + withoutParentheses);
    }
}