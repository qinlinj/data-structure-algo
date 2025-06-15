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

    /**
     * Alternative optimized approach - only reverse half the number
     * This avoids potential integer overflow issues
     */
    public boolean isPalindromeOptimized(int x) {
        // Negative numbers and numbers ending with 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even number of digits: x == reversedHalf
        // For odd number of digits: x == reversedHalf / 10 (middle digit doesn't matter)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
