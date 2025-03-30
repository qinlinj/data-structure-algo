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
    private byte[] bytes;
    private int nBits;

    public BitMap(int nBits) {
        this.nBits = nBits;
        this.bytes = new byte[nBits / 8 + 1];
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 7, 2, 5, 3};
        int target = 2;

        BitMap bitMap1 = new BitMap(8);
        for (int i = 0; i < data.length; i++) {
            bitMap1.set(data[i]);
        }

        System.out.println(bitMap1);

        if (bitMap1.contains(target)) {
            System.out.println("There is a target value: " + target);
        }
    }

    public void set(int num) {
        if (num > nBits) return;
        int byteIndex = num / 8;
        int bitIndex = num % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean contains(int target) {
        if (target > nBits) return false;
        int byteIndex = target / 8;
        int bitIndex = target % 8;
        return ((1 << bitIndex) & bytes[byteIndex]) != 0;
    }
}