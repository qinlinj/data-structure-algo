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
}
