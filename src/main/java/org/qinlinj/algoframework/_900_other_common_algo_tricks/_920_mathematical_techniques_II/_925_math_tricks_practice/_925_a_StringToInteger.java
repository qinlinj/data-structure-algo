package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_a_StringToInteger {
    public static void main(String[] args) {
        _925_a_StringToInteger solution = new _925_a_StringToInteger();

        // Test cases demonstrating different scenarios
        System.out.println("=== String to Integer (atoi) Test Cases ===");

        // Example 1: Simple positive number
        String test1 = "42";
        System.out.println("Input: \"" + test1 + "\" -> Output: " + solution.myAtoi(test1));

        // Example 2: Negative number with leading spaces and zeros
        String test2 = "   -042";
        System.out.println("Input: \"" + test2 + "\" -> Output: " + solution.myAtoi(test2));

        // Example 3: Number followed by non-digit characters
        String test3 = "1337c0d3";
        System.out.println("Input: \"" + test3 + "\" -> Output: " + solution.myAtoi(test3));

        // Example 4: Zero followed by non-digit
        String test4 = "0-1";
        System.out.println("Input: \"" + test4 + "\" -> Output: " + solution.myAtoi(test4));

        // Example 5: Non-digit at beginning
        String test5 = "words and 987";
        System.out.println("Input: \"" + test5 + "\" -> Output: " + solution.myAtoi(test5));

        // Example 6: Overflow case
        String test6 = "91283472332";
        System.out.println("Input: \"" + test6 + "\" -> Output: " + solution.myAtoi(test6));

        // Example 7: Underflow case
        String test7 = "-91283472332";
        System.out.println("Input: \"" + test7 + "\" -> Output: " + solution.myAtoi(test7));
    }

    /**
     * Converts a string to a 32-bit signed integer following atoi rules
     *
     * @param str input string to convert
     * @return converted integer value
     */
    public int myAtoi(String str) {
        int n = str.length();
        int i = 0;
        // Record positive or negative sign
        int sign = 1;
        // Use long to avoid int overflow
        long res = 0;

        // Skip leading whitespaces
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        if (i == n) {
            return 0;
        }

        // Handle sign characters
        if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (str.charAt(i) == '+') {
            i++;
        }
        if (i == n) {
            return 0;
        }

        // Process digit characters
        while (i < n && '0' <= str.charAt(i) && str.charAt(i) <= '9') {
            res = res * 10 + str.charAt(i) - '0';
            if (res > Integer.MAX_VALUE) {
                break;
            }
            i++;
        }

        // Handle overflow by checking if casting changes the value
        if ((int) res != res) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }
}
