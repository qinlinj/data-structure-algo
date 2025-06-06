package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * XOR APPLICATIONS - FINDING SINGLE NUMBERS
 * <p>
 * This class demonstrates powerful applications of XOR operation for solving
 * problems involving finding unique elements in arrays.
 * <p>
 * Key XOR Properties:
 * 1. a ^ a = 0 (any number XOR with itself equals 0)
 * 2. a ^ 0 = a (any number XOR with 0 equals itself)
 * 3. XOR is commutative: a ^ b = b ^ a
 * 4. XOR is associative: (a ^ b) ^ c = a ^ (b ^ c)
 * 5. XOR is its own inverse: a ^ b ^ b = a
 * <p>
 * Applications:
 * 1. Find single number in array where all others appear twice
 * 2. Find missing number in sequence
 * 3. Detect pairs and eliminate duplicates
 * <p>
 * Why XOR works:
 * - All paired numbers cancel out (become 0)
 * - Single number remains (0 ^ single = single)
 * <p>
 * Time Complexity: O(n) for single pass through array
 * Space Complexity: O(1) - no extra space needed
 */

public class _912_d_XORApplications {
}
