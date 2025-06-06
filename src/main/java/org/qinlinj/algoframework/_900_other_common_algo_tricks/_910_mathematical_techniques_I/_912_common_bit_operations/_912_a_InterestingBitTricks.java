package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * INTERESTING BIT MANIPULATION TRICKS
 * <p>
 * This class demonstrates several clever but mostly impractical bit manipulation tricks
 * that work due to ASCII encoding properties and bitwise operation characteristics.
 * <p>
 * Key Concepts:
 * 1. ASCII Case Conversion: Use bitwise operations for upper/lowercase conversion
 * - OR with space (' ') converts to lowercase
 * - AND with underscore ('_') converts to uppercase
 * - XOR with space (' ') toggles case
 * <p>
 * 2. Variable Swapping: Use XOR to swap without temporary variables
 * - Based on property: a ^ a = 0, a ^ 0 = a
 * <p>
 * 3. Arithmetic Tricks: Use bitwise operations for increment/decrement
 * - Add one: -~n (bitwise NOT then negate)
 * - Subtract one: ~-n (negate then bitwise NOT)
 * <p>
 * 4. Sign Detection: Use XOR to detect if two numbers have different signs
 * - Different signs: (x ^ y) < 0 returns true
 * - Same signs: (x ^ y) < 0 returns false
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(1) for all operations
 */
public class _912_a_InterestingBitTricks {

    public static void main(String[] args) {
        _912_a_InterestingBitTricks tricks = new _912_a_InterestingBitTricks();

        System.out.println("=== Case Conversion Tricks ===");

        // Test case conversion
        char[] testChars = {'A', 'a', 'B', 'b', 'Z', 'z'};
        for (char c : testChars) {
            System.out.printf("'%c' -> lower: '%c', upper: '%c', toggle: '%c'\n",
                    c, tricks.toLowerCase(c), tricks.toUpperCase(c), tricks.toggleCase(c));
        }

        tricks.demonstrateASCII();

        System.out.println("\n=== Variable Swapping ===");
        tricks.swapWithoutTemp(1, 2);
        tricks.swapWithoutTemp(100, 200);

        System.out.println("\n=== Arithmetic Tricks ===");
        int[] testNums = {1, 5, 10, -3};
        for (int n : testNums) {
            System.out.printf("%d + 1 = %d, %d - 1 = %d\n",
                    n, tricks.addOne(n), n, tricks.subtractOne(n));
        }

        System.out.println("\n=== Sign Detection ===");
        int[][] pairs = {{-1, 2}, {3, 2}, {-5, -10}, {0, 5}, {-7, 0}};
        for (int[] pair : pairs) {
            boolean different = tricks.hasDifferentSigns(pair[0], pair[1]);
            System.out.printf("%d and %d have different signs: %b\n",
                    pair[0], pair[1], different);
        }

        System.out.println("\n=== Why These Work ===");
        System.out.println("1. ASCII 'A'=65, 'a'=97. Difference is 32 (space character)");
        System.out.println("2. Space ' ' = 100000 (binary), affects bit 5");
        System.out.println("3. Underscore '_' = 1011111 (binary), clears bit 5");
        System.out.println("4. XOR swap: uses a^a=0, a^0=a properties");
        System.out.println("5. Sign detection: uses sign bit in two's complement");
    }

    /**
     * Converts English character to lowercase using OR operation with space
     */
    public char toLowerCase(char c) {
        return (char) (c | ' ');
    }

    /**
     * Converts English character to uppercase using AND operation with underscore
     */
    public char toUpperCase(char c) {
        return (char) (c & '_');
    }

    /**
     * Toggles case of English character using XOR operation with space
     */
    public char toggleCase(char c) {
        return (char) (c ^ ' ');
    }

    /**
     * Swaps two integers without using temporary variable
     */
    public int[] swapWithoutTemp(int a, int b) {
        System.out.println("Before swap: a=" + a + ", b=" + b);
        a ^= b;  // a = a ^ b
        b ^= a;  // b = b ^ (a ^ b) = b ^ a ^ b = a
        a ^= b;  // a = (a ^ b) ^ a = b
        System.out.println("After swap: a=" + a + ", b=" + b);
        return new int[]{a, b};
    }

    /**
     * Adds one to a number using bitwise operations
     */
    public int addOne(int n) {
        return -~n;  // NOT then negate
    }

    /**
     * Subtracts one from a number using bitwise operations
     */
    public int subtractOne(int n) {
        return ~-n;  // negate then NOT
    }

    /**
     * Checks if two numbers have different signs
     */
    public boolean hasDifferentSigns(int x, int y) {
        return (x ^ y) < 0;
    }

    /**
     * Demonstrates ASCII values for understanding case conversion tricks
     */
    public void demonstrateASCII() {
        System.out.println("=== ASCII Analysis ===");
        char[] chars = {'A', 'a', ' ', '_'};
        for (char c : chars) {
            System.out.printf("'%c' = %d = %s\n", c, (int) c, Integer.toBinaryString(c));
        }

        System.out.println("\nSpace (' ') = 32 = " + Integer.toBinaryString(' '));
        System.out.println("Underscore ('_') = 95 = " + Integer.toBinaryString('_'));
        System.out.println("Notice: space has bit 5 set, underscore has bit 5 clear");
    }
}
