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

    /**
     * Helper method to demonstrate the digit counting pattern
     */
    public void demonstratePattern(int maxDigits) {
        System.out.println("=== Digit Distribution Pattern ===");
        long totalDigits = 0;

        for (int digitCount = 1; digitCount <= maxDigits; digitCount++) {
            long base = (long) Math.pow(10, digitCount - 1);
            long numbersInGroup = 9 * base;
            long digitsInGroup = numbersInGroup * digitCount;
            totalDigits += digitsInGroup;

            long startNum = digitCount == 1 ? 1 : base;
            long endNum = base * 10 - 1;

            System.out.printf("%d-digit numbers: %d to %d\n", digitCount, startNum, endNum);
            System.out.printf("  Count: %d numbers\n", numbersInGroup);
            System.out.printf("  Total digits: %d\n", digitsInGroup);
            System.out.printf("  Cumulative digits: %d\n\n", totalDigits);
        }
    }

    /**
     * Alternative implementation with more detailed step tracking
     */
    public int findNthDigitDetailed(int n) {
        System.out.printf("Finding the %d-th digit:\n", n);

        int digit = 1;
        long base = 1;
        int originalN = n;

        while (n > 9 * base * digit) {
            long digitsUsed = 9 * base * digit;
            System.out.printf("  %d-digit numbers use %d digits, remaining: %d\n",
                    digit, digitsUsed, n - digitsUsed);
            n -= digitsUsed;
            base *= 10;
            digit++;
        }

        long val = base + (n - 1) / digit;
        int index = (n - 1) % digit;

        System.out.printf("  Found in %d-digit numbers starting from %d\n", digit, base);
        System.out.printf("  Target number: %d, digit position: %d\n", val, index);

        int result = ("" + val).charAt(index) - '0';
        System.out.printf("  The %d-th digit is: %d\n\n", originalN, result);

        return result;
    }
}
