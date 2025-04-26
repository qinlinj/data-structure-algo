package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._333_rabin_karp_algorithm;

/**
 * Rabin-Karp String Matching Algorithm
 * <p>
 * This class demonstrates the classic Rabin-Karp string matching algorithm,
 * which uses a rolling hash function to efficiently find pattern matches in text.
 * <p>
 * Key concepts:
 * 1. Convert both pattern and text windows to numeric hash values
 * 2. Use a sliding window approach to efficiently recalculate hash values
 * 3. Only perform character-by-character comparison when hash values match
 * <p>
 * Time complexity:
 * - Average case: O(n + m) where n is text length and m is pattern length
 * - Worst case: O(n*m) when many hash collisions occur
 * <p>
 * Space complexity: O(1) as we only store hash values and constants
 */
public class IntroRabinKarpAlgorithm {
}
