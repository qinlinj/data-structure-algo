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
    /**
     * Demonstrates circular array traversal using modulo operation
     */
    public void traverseWithModulo(int[] arr, int steps, boolean forward) {
        System.out.println("=== Traversal with Modulo (%) ===");
        System.out.println("Array: " + java.util.Arrays.toString(arr));

        int index = 0;
        for (int i = 0; i < steps; i++) {
            System.out.print(arr[index % arr.length] + " ");
            index = forward ? index + 1 : index - 1;

            // Handle negative index for modulo
            if (!forward && index < 0) {
                index = arr.length - 1;
            }
        }
        System.out.println();
    }

    /**
     * Demonstrates circular array traversal using bitwise AND optimization
     * ONLY works when array length is power of 2!
     */
    public void traverseWithBitwise(int[] arr, int steps, boolean forward) {
        if (!isPowerOfTwo(arr.length)) {
            System.out.println("Warning: Bitwise optimization only works for power-of-2 lengths!");
            return;
        }

        System.out.println("=== Traversal with Bitwise (&) ===");
        System.out.println("Array: " + java.util.Arrays.toString(arr));

        int index = 0;
        for (int i = 0; i < steps; i++) {
            System.out.print(arr[index & (arr.length - 1)] + " ");
            index = forward ? index + 1 : index - 1;
        }
        System.out.println();
    }

    /**
     * Checks if a number is power of 2
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Demonstrates why bitwise AND works as modulo replacement
     */
    public void explainBitwiseModulo(int arrayLength) {
        if (!isPowerOfTwo(arrayLength)) {
            System.out.println(arrayLength + " is not a power of 2, bitwise trick won't work");
            return;
        }

        System.out.println("=== Why (index & (length-1)) == (index % length) ===");
        System.out.printf("Array length: %d (binary: %s)\n",
                arrayLength, Integer.toBinaryString(arrayLength));
        System.out.printf("Length - 1: %d (binary: %s)\n",
                arrayLength - 1, Integer.toBinaryString(arrayLength - 1));

        System.out.println("\nTesting various indices:");
        for (int i = 0; i < arrayLength * 2; i++) {
            int modResult = i % arrayLength;
            int bitResult = i & (arrayLength - 1);
            System.out.printf("index=%d: %% gives %d, & gives %d (binary: %s & %s = %s)\n",
                    i, modResult, bitResult,
                    String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0'),
                    Integer.toBinaryString(arrayLength - 1),
                    String.format("%4s", Integer.toBinaryString(bitResult)).replace(' ', '0'));
        }
    }
}
