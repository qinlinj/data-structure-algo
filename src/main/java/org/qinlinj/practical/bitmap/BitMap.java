package org.qinlinj.practical.bitmap;

public class BitMap {
    private byte[] bytes;
    private int nBits;

    public BitMap(int nBits) {
        this.nBits = nBits;
        this.bytes = new byte[nBits / 8 + 1];
    }

    public void set(int num) {
        if (num > nBits) return;
        int byteIndex = num / 8;
        int bitIndex = num % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean contains(int target) {

    }
}