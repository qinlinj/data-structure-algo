package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_a_StringToInteger {
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
