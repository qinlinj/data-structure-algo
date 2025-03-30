package org.qinlinj.practical.bitmap;

// @formatter:off
/**
 * BitMap Data Structure Implementation
 *
 * Advantages of BitMap:
 * 1. Memory Efficiency: Uses just 1 bit per element instead of 32 or 64 bits in traditional data structures
 * 2. Fast Operations: O(1) time complexity for set, get, and contains operations
 * 3. Space-Time Optimization: Excellent for tasks requiring compact representation of large sets of integers
 * 4. Fast Set Operations: Efficient for union, intersection, and difference operations
 * 5. Cache Friendly: Sequential memory layout improves cache performance
 *
 * Concept and Principle:
 * A BitMap represents a set of non-negative integers using bits. Each bit in the bitmap
 * corresponds to a specific integer. If a bit is set to 1, it means the corresponding
 * integer exists in the set; if it's 0, the integer is not in the set.
 *
 * The BitMap uses a byte array for storage where:
 * - byte[i] represents 8 consecutive integers starting from i*8
 * - The j-th bit in byte[i] represents the integer (i*8 + j)
 *
 * Visual representation of a BitMap with 16 bits:
 *
 * Bits:        7 6 5 4 3 2 1 0 | 15 14 13 12 11 10 9 8
 * Values:      7 6 5 4 3 2 1 0 | 15 14 13 12 11 10 9 8
 * Byte array: [    byte[0]     |      byte[1]         ]
 *
 * If we set values 2, 3, 7, and 10:
 *
 * Bits:        7 6 5 4 3 2 1 0 | 15 14 13 12 11 10 9 8
 * Values:      7 6 5 4 3 2 1 0 | 15 14 13 12 11 10 9 8
 * Set values:  1 0 0 0 1 1 0 0 | 0  0  0  0  0  1  0 0
 * Byte array: [    0x84       |      0x04           ]
 */
public class BitMap {
    private byte[] bytes;  // Storage array where each bit represents an integer
    private int nBits;     // Maximum number of integers that can be represented

    /**
     * Constructor initializes a BitMap capable of representing integers from 0 to nBits-1
     *
     * Time Complexity: O(1)
     * Space Complexity: O(nBits/8) - uses approximately nBits/8 bytes of memory
     *
     * @param nBits The number of bits (integers) to represent
     */
    public BitMap(int nBits) {
        this.nBits = nBits;
        // Calculate the number of bytes needed: ceiling of nBits/8
        this.bytes = new byte[nBits / 8 + 1];
    }

    /**
     * Demonstration of BitMap functionality
     */
    public static void main(String[] args) {
        int[] data = new int[]{2, 7, 2, 5, 3};
        int target = 2;

        // Create a bitmap that can represent integers 0-7
        BitMap bitMap1 = new BitMap(8);

        // Set bits corresponding to values in the data array
        for (int i = 0; i < data.length; i++) {
            bitMap1.set(data[i]);
        }

        // Print the bitmap (toString method would need to be implemented)
        System.out.println(bitMap1);

        // Check if the target value exists in the bitmap
        if (bitMap1.contains(target)) {
            System.out.println("There is a target value: " + target);
        }

        /*
         * Visual representation of what happens:
         *
         * 1. Initialize BitMap with 8 bits: [00000000]
         * 2. Add value 2:                   [00000100]
         * 3. Add value 7:                   [10000100]
         * 4. Add value 2 (already set):     [10000100]
         * 5. Add value 5:                   [10100100]
         * 6. Add value 3:                   [10101100]
         *
         * Final bitmap: [10101100] which represents values {2, 3, 5, 7}
         * Checking if 2 is in the set: Yes (bit 2 is set to 1)
         */
    }

    /**
     * Sets the bit corresponding to the specified number
     *
     * Visual example: Setting bit 3 in an 8-bit bitmap
     * Before: [00000000]
     * Operation: Set bit at index 3 (num=3)
     *   byteIndex = 3/8 = 0
     *   bitIndex = 3%8 = 3
     *   1 << bitIndex = 00001000
     *   bytes[0] |= 00001000 = 00001000
     * After: [00001000]
     *
     * Time Complexity: O(1)
     *
     * @param num The number to set in the bitmap
     */
    public void set(int num) {
        // Check if the number is within range
        if (num > nBits) return;

        // Calculate the byte index: num / 8 gives the index in the byte array
        int byteIndex = num / 8;

        // Calculate the bit index within the byte: num % 8 gives the bit position
        int bitIndex = num % 8;

        // Set the bit using bitwise OR with a shifted 1
        // 1 << bitIndex creates a byte with only the target bit set to 1
        bytes[byteIndex] |= (1 << bitIndex);
    }

    /**
     * Checks if the specified number exists in the bitmap
     *
     * Visual example: Checking bit 3 in bitmap [00001000]
     * Operation: Check if bit at index 3 (target=3) is set
     *   byteIndex = 3/8 = 0
     *   bitIndex = 3%8 = 3
     *   1 << bitIndex = 00001000
     *   bytes[0] & 00001000 = 00001000 (non-zero result means the bit is set)
     * Result: true (bit 3 is set)
     *
     * Time Complexity: O(1)
     *
     * @param target The number to check in the bitmap
     * @return true if the number exists, false otherwise
     */
    public boolean contains(int target) {
        // Check if the target is within range
        if (target > nBits) return false;

        // Calculate the byte index
        int byteIndex = target / 8;

        // Calculate the bit index within the byte
        int bitIndex = target % 8;

        // Check if the bit is set using bitwise AND with a shifted 1
        // (1 << bitIndex) creates a byte with only the target bit set to 1
        // The result is non-zero only if the target bit is also 1
        return ((1 << bitIndex) & bytes[byteIndex]) != 0;
    }

    /**
     * Optional extension methods could include:
     * - clear(int num): Clear a specific bit
     * - toggle(int num): Toggle a specific bit
     * - isEmpty(): Check if all bits are 0
     * - count(): Count the number of set bits
     * - toString(): Provide a string representation
     */
}