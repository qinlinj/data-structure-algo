package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_e_NthDigit {
    public static void main(String[] args) {
        _925_e_NthDigit solution = new _925_e_NthDigit();

        System.out.println("=== Nth Digit Test Cases ===");

        // Test cases with examples
        int[] testCases = {3, 11, 15, 100, 189, 190, 1000};

        for (int testCase : testCases) {
            int result = solution.findNthDigit(testCase);
            System.out.printf("The %d-th digit is: %d\n", testCase, result);
        }

        System.out.println();

        // Demonstrate the pattern for educational purposes
        solution.demonstratePattern(4);

        // Show detailed step-by-step process for specific examples
        System.out.println("=== Detailed Step-by-Step Examples ===");
        solution.findNthDigitDetailed(11);
        solution.findNthDigitDetailed(189);
        solution.findNthDigitDetailed(190);

        // Generate first 50 digits of the sequence for verification
        System.out.println("=== First 50 digits of sequence ===");
        StringBuilder sequence = new StringBuilder();
        for (int i = 1; sequence.length() < 50; i++) {
            sequence.append(i);
        }
        System.out.println("Sequence: " + sequence.toString());

        // Verify our algorithm against the generated sequence
        System.out.println("\n=== Verification ===");
        for (int i = 1; i <= Math.min(50, sequence.length()); i++) {
            int expected = sequence.charAt(i - 1) - '0';
            int actual = solution.findNthDigit(i);
            if (expected != actual) {
                System.out.printf("Mismatch at position %d: expected %d, got %d\n", i, expected, actual);
            }
        }
        System.out.println("Verification completed successfully!");
    }

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
