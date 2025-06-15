package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_e_NthDigit {
    /**
     * Finds the nth digit in the sequence 1,2,3,4,5,6,7,8,9,10,11,12,...
     *
     * @param n the position of digit to find (1-indexed)
     * @return the nth digit as an integer
     */
    public int findNthDigit(int n) {
        // digit represents number of digits (1 for single-digit, 2 for double-digit, etc.)
        int digit = 1;
        // base represents the starting number for each digit group (1, 10, 100, 1000, ...)
        long base = 1;

        // Find which digit group contains the nth digit
        while (n > 9 * base * digit) {
            n -= 9 * base * digit;  // Subtract digits used by current group
            base *= 10;             // Move to next group (1->10, 10->100, etc.)
            digit++;                // Increase digit count
        }

        // Now we know the nth digit is in the 'digit'-digit numbers starting from 'base'
        // For example, if base=100 and digit=3, we're looking in 3-digit numbers 100-999

        // Find which specific number contains our digit
        long val = base + (n - 1) / digit;

        // Find which position within that number
        int index = (n - 1) % digit;

        // Extract the digit at the specified position by converting to string
        return ("" + val).charAt(index) - '0';
    }
}
