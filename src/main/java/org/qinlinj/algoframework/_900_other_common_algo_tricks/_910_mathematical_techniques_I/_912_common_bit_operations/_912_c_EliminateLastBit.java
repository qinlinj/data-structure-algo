package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * n & (n-1) - ELIMINATE LAST SET BIT TECHNIQUE
 * <p>
 * This class demonstrates the powerful bit manipulation technique n & (n-1) which
 * eliminates the rightmost set bit (last 1) in the binary representation of n.
 * <p>
 * Key Concepts:
 * 1. Core Operation: n & (n-1) removes the rightmost 1 bit from n
 * - Works because (n-1) flips the rightmost 1 and all bits to its right
 * - AND operation then clears only the original rightmost 1
 * <p>
 * 2. Applications:
 * - Count set bits (Hamming Weight): Keep removing 1s until n becomes 0
 * - Check power of 2: A power of 2 has exactly one 1 bit
 * - Various bit manipulation algorithms
 * <p>
 * 3. How it works:
 * Example: n = 12 (1100), n-1 = 11 (1011)
 * n & (n-1) = 1100 & 1011 = 1000 (removed rightmost 1)
 * <p>
 * Time Complexity: O(k) where k is number of set bits, O(log n) worst case
 * Space Complexity: O(1)
 */

public class _912_c_EliminateLastBit {
    /**
     * Counts the number of 1s in binary representation (Hamming Weight)
     * LeetCode 191: Number of 1 Bits
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);  // Remove rightmost 1
            count++;
        }
        return count;
    }

    /**
     * Alternative implementation treating n as unsigned value
     */
    public int hammingWeightUnsigned(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);  // Remove rightmost 1
            count++;
        }
        return count;
    }
}
