package org.qinlinj.practical.bitmap;

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