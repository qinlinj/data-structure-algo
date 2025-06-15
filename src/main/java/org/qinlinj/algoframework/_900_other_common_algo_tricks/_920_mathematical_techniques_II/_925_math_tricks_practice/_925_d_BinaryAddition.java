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
