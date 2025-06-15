package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_b_PalindromeNumber {
    /**
     * Determines if an integer is a palindrome
     *
     * @param x the integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        // y is the reversed version of x
        int y = 0;
        while (temp > 0) {
            int lastDigit = temp % 10;
            temp = temp / 10;
            // Technique to build number from most significant digit
            y = y * 10 + lastDigit;
        }
        return y == x;
    }
}
