package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * CIRCULAR ARRAY OPTIMIZATION WITH BIT MANIPULATION
 * <p>
 * This class demonstrates how to optimize circular array operations using bit manipulation
 * instead of expensive modulo operations for better performance.
 * <p>
 * Key Concepts:
 * 1. Modulo Replacement: For arrays with length = power of 2, use (index & (length-1))
 * instead of (index % length) for better performance
 * <p>
 * 2. Why It Works: When length is power of 2, (length-1) has all lower bits set to 1
 * Example: length=8 (1000), length-1=7 (0111)
 * AND operation with (length-1) effectively masks to get remainder
 * <p>
 * 3. Bidirectional Support: Works for both forward (index++) and backward (index--) traversal
 * Unlike modulo which can produce negative results, bitwise AND always gives positive result
 * <p>
 * 4. Requirements: Only works when array length is exactly a power of 2 (2, 4, 8, 16, 32, ...)
 * <p>
 * Performance: Bitwise AND is significantly faster than modulo division
 * Time Complexity: O(1) for index calculation vs O(log n) for modulo
 * Space Complexity: O(1)
 */

public class _912_b_CircularArrayOptimization {
}
