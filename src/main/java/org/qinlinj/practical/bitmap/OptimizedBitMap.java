package org.qinlinj.practical.bitmap;

/**
 * Optimized BitMap Implementation
 * <p>
 * This implementation improves upon the original BitMap by:
 * 1. Using a long[] array to support a much larger range of integers
 * 2. Adding methods for clearing bits and checking if bits are set
 * 3. Providing efficient bit counting and range operations
 * 4. Implementing proper toString() for better visualization
 * 5. Adding methods for bitwise operations (AND, OR, XOR) between bitmaps
 */
public class OptimizedBitMap {
    private static final int BITS_PER_WORD = 64; // Using long (64 bits) for better efficiency
    private static final int ADDRESS_BITS = 6;   // 2^6 = 64 bits in a long
    private static final int MASK = 0x3F;        // Mask for bit index (63 in binary: 111111)
    private long[] bits;
    private int capacity;

    /**
     * Constructor initializes a BitMap capable of representing integers from 0 to capacity-1
     *
     * @param capacity The maximum number plus one that can be represented
     */
    public OptimizedBitMap(int capacity) {
        this.capacity = capacity;
        // Calculate number of longs needed (each long can store 64 integers)
        this.bits = new long[(capacity + BITS_PER_WORD - 1) / BITS_PER_WORD];
    }

}
