package org.qinlinj.practical.bitmap;

// @formatter:off
/**
 * Single Byte BitMap Implementation
 *
 * Advantages of BitMap:
 * 1. Memory Efficiency: Each bit represents one number, using 8x less memory than int arrays
 * 2. Fast Operations: O(1) time complexity for setting and checking values
 * 3. Simple Implementation: This version uses just a single byte to store 8 distinct values
 * 4. Constant Space: Fixed memory usage regardless of how many unique numbers are stored
 * 5. Efficient for Small Sets: Ideal for representing a small set of integers (0-7)
 *
 * Concept and Principle:
 * This simplified BitMap uses a single byte (8 bits) to represent the presence or absence
 * of integers from 0 to 7. Each bit position corresponds to an integer value:
 *
 * Bit positions:  7 6 5 4 3 2 1 0
 * Values:         7 6 5 4 3 2 1 0
 *
 * When a bit is set to 1, it indicates the presence of the corresponding number.
 * When a bit is 0, it indicates the absence of that number.
 *
 * Visual representation:
 * Empty bitmap:   [0 0 0 0 0 0 0 0] (byte value: 0)
 * After set(2):   [0 0 0 0 0 1 0 0] (byte value: 4)
 * After set(5):   [0 0 1 0 0 1 0 0] (byte value: 36)
 */
public class BitMap1 {
    private byte b;

    public BitMap1() {
        b = 0;
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
        /*
            set 2
            00000001 << 2
            00000100
          | 00000000
          = 00000100

            set 2
            00000001 << 2
            00000100
          | 00000100
          = 00000100
         */
        b |= (1 << num);
    }

    public boolean contains(int target) {
        /*
            contains 4
            00000001 << 4
            00010000
          & 10101100
          = 00000000
         */
        // 10101100
        return ((1 << target) & b) != 0;
    }

    @Override
    public String toString() {
        return "BitMap{" +
                "b=" + Integer.toBinaryString(b).substring(24) +
                '}';
    }
}
