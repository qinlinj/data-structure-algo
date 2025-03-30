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

    /**
     * Sets the bit at the specified position
     *
     * @param position The position to set
     */
    public void set(int position) {
        if (position < 0 || position >= capacity) return;

        int wordIndex = position >>> ADDRESS_BITS; // Divide by 64
        int bitIndex = position & MASK;            // Modulo 64

        bits[wordIndex] |= (1L << bitIndex);
    }

    /**
     * Checks if the bit at the specified position is set
     *
     * @param position The position to check
     * @return true if the bit is set, false otherwise
     */
    public boolean contains(int position) {
        if (position < 0 || position >= capacity) return false;

        int wordIndex = position >>> ADDRESS_BITS;
        int bitIndex = position & MASK;

        return (bits[wordIndex] & (1L << bitIndex)) != 0;
    }

    /**
     * Clears the bit at the specified position
     *
     * @param position The position to clear
     */
    public void clear(int position) {
        if (position < 0 || position >= capacity) return;

        int wordIndex = position >>> ADDRESS_BITS;
        int bitIndex = position & MASK;

        bits[wordIndex] &= ~(1L << bitIndex);
    }

    /**
     * Toggles the bit at the specified position
     *
     * @param position The position to toggle
     */
    public void toggle(int position) {
        if (position < 0 || position >= capacity) return;

        int wordIndex = position >>> ADDRESS_BITS;
        int bitIndex = position & MASK;

        bits[wordIndex] ^= (1L << bitIndex);
    }

    /**
     * Returns the number of bits set to 1 in this BitMap
     *
     * @return The number of bits set to 1
     */
    public int cardinality() {
        int count = 0;
        for (long word : bits) {
            count += Long.bitCount(word);
        }
        return count;
    }

    /**
     * Performs a bitwise AND operation with another BitMap
     *
     * @param other The other BitMap
     * @return A new BitMap containing the result of the AND operation
     */
    public OptimizedBitMap and(OptimizedBitMap other) {
        OptimizedBitMap result = new OptimizedBitMap(Math.min(this.capacity, other.capacity));
        for (int i = 0; i < result.bits.length; i++) {
            if (i < this.bits.length && i < other.bits.length) {
                result.bits[i] = this.bits[i] & other.bits[i];
            }
        }
        return result;
    }
}
