package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

/**
 * 9. Palindrome Number - LeetCode Problem
 * <p>
 * SUMMARY:
 * This class determines if an integer is a palindrome without converting it to string.
 * A palindrome number reads the same forward and backward (e.g., 121, 1331).
 * The solution reverses the number by extracting digits and reconstructs it.
 * <p>
 * KEY CONCEPTS:
 * - Mathematical digit manipulation using modulo (%) and division (/)
 * - Number reversal technique by building from most significant digit
 * - Handling negative numbers (always return false)
 * - Integer comparison for palindrome validation
 * <p>
 * ALGORITHM APPROACH:
 * 1. Handle edge case: negative numbers are not palindromes
 * 2. Extract digits from right to left using num % 10
 * 3. Build reversed number using the formula: reversed = reversed * 10 + digit
 * 4. Compare original with reversed number
 * <p>
 * TIME COMPLEXITY: O(log n) where n is the input number (number of digits)
 * SPACE COMPLEXITY: O(1) constant extra space
 */
public class _925_b_PalindromeNumber {

    public static void main(String[] args) {
        _925_b_PalindromeNumber solution = new _925_b_PalindromeNumber();

        System.out.println("=== Palindrome Number Test Cases ===");

        // Test cases with expected results
        int[] testCases = {121, -121, 10, 0, 1221, 12321, 123, 7};

        for (int testCase : testCases) {
            boolean result1 = solution.isPalindrome(testCase);
            boolean result2 = solution.isPalindromeOptimized(testCase);

            System.out.printf("Number: %d\n", testCase);
            System.out.printf("  Basic approach: %s\n", result1);
            System.out.printf("  Optimized approach: %s\n", result2);
            System.out.printf("  Results match: %s\n\n", result1 == result2);
        }

        // Demonstrate the digit extraction process for educational purposes
        System.out.println("=== Digit Extraction Demo for 12321 ===");
        int demo = 12321;
        int reversed = 0;
        int step = 1;

        while (demo > 0) {
            int digit = demo % 10;
            demo = demo / 10;
            reversed = reversed * 10 + digit;
            System.out.printf("Step %d: Extract digit %d, remaining: %d, reversed so far: %d\n",
                    step++, digit, demo, reversed);
        }
        System.out.println("Final reversed number: " + reversed);
    }

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