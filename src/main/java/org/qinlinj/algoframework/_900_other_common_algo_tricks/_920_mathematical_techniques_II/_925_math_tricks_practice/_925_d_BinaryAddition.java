package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

/**
 * 67. Add Binary - LeetCode Problem
 * <p>
 * SUMMARY:
 * This class implements binary string addition by simulating the manual addition process.
 * It processes binary strings digit by digit from right to left, handling carry propagation.
 * The algorithm reverses input strings for easier processing and reverses the result back.
 * <p>
 * KEY CONCEPTS:
 * - Binary arithmetic simulation (0+0=0, 0+1=1, 1+1=10 in binary)
 * - Carry propagation in addition algorithms
 * - String manipulation and character-to-digit conversion
 * - Mathematical addition simulation with different bases
 * - StringBuilder for efficient string building
 * <p>
 * ALGORITHM STEPS:
 * 1. Reverse both input strings to process from least significant bit
 * 2. Use carry variable to track overflow from previous position
 * 3. Add corresponding bits plus carry at each position
 * 4. Calculate result bit (sum % 2) and new carry (sum / 2)
 * 5. Continue until all digits processed and no remaining carry
 * 6. Reverse final result to get correct bit order
 * <p>
 * TIME COMPLEXITY: O(max(m, n)) where m, n are lengths of input strings
 * SPACE COMPLEXITY: O(max(m, n)) for the result string
 */
public class _925_d_BinaryAddition {

    public static void main(String[] args) {
        _925_d_BinaryAddition solution = new _925_d_BinaryAddition();

        System.out.println("=== Binary Addition Test Cases ===");

        // Test cases with step-by-step explanation
        String[][] testCases = {
                {"11", "1"},        // Expected: "100"
                {"1010", "1011"},   // Expected: "10101"
                {"0", "0"},         // Expected: "0"
                {"1111", "1111"},   // Expected: "11110"
                {"1", "111"}        // Expected: "1000"
        };

        for (String[] testCase : testCases) {
            String a = testCase[0];
            String b = testCase[1];
            String result1 = solution.addBinary(a, b);
            String result2 = solution.addBinaryAlternative(a, b);

            System.out.printf("Binary Addition: %s + %s\n", a, b);
            System.out.printf("  Method 1 result: %s\n", result1);
            System.out.printf("  Method 2 result: %s\n", result2);
            System.out.printf("  Results match: %s\n", result1.equals(result2));

            // Verify by converting to decimal
            int decimalA = Integer.parseInt(a, 2);
            int decimalB = Integer.parseInt(b, 2);
            int expectedSum = decimalA + decimalB;
            int actualSum = Integer.parseInt(result1, 2);
            System.out.printf("  Decimal verification: %d + %d = %d (expected: %d) ✓\n\n",
                    decimalA, decimalB, actualSum, expectedSum);
        }

        // Demonstrate manual addition process
        System.out.println("=== Manual Addition Process Demo ===");
        String demoA = "1011";
        String demoB = "111";
        System.out.printf("Adding %s + %s step by step:\n", demoA, demoB);
        System.out.println("  1011");
        System.out.println("+ 0111");
        System.out.println("------");

        // Simulate the addition process
        StringBuilder process = new StringBuilder();
        int carry = 0;
        for (int pos = Math.max(demoA.length(), demoB.length()) - 1; pos >= 0; pos--) {
            int bitA = pos < demoA.length() ? demoA.charAt(demoA.length() - 1 - pos) - '0' : 0;
            int bitB = pos < demoB.length() ? demoB.charAt(demoB.length() - 1 - pos) - '0' : 0;
            int sum = bitA + bitB + carry;

            System.out.printf("  Position %d: %d + %d + %d(carry) = %d -> bit=%d, carry=%d\n",
                    pos, bitA, bitB, carry, sum, sum % 2, sum / 2);

            process.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) {
            process.insert(0, carry);
        }

        System.out.println("  Final result: " + process.toString());
        System.out.println("  Algorithm result: " + solution.addBinary(demoA, demoB));
    }

    /**
     * Adds two binary strings and returns their sum as a binary string
     *
     * @param a first binary string
     * @param b second binary string
     * @return sum of a and b as binary string
     */
    public String addBinary(String a, String b) {
        // Reverse input strings to process from least significant bit (rightmost)
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        // StringBuilder to store result
        StringBuilder sb = new StringBuilder();

        int m = a.length(), n = b.length();
        // carry records the overflow from previous position
        int carry = 0;
        int i = 0;

        // Process similar to "Add Two Numbers" problem but for binary strings
        while (i < Math.max(m, n) || carry > 0) {
            int val = carry;
            // Add bit from string a if available, otherwise add 0
            val += i < m ? (a.charAt(i) - '0') : 0;
            // Add bit from string b if available, otherwise add 0
            val += i < n ? (b.charAt(i) - '0') : 0;

            // In binary: val can be 0, 1, 2, or 3
            // val % 2 gives the result bit, val / 2 gives the carry
            sb.append(val % 2);
            carry = val / 2;
            i++;
        }

        // Reverse result to get correct bit order (most significant bit first)
        return sb.reverse().toString();
    }

    /**
     * Alternative implementation without reversing strings
     */
    public String addBinaryAlternative(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }
}