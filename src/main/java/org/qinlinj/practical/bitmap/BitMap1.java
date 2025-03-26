package org.qinlinj.practical.bitmap;

public class BitMap1 {
    private byte b;

    public BitMap1() {
        b = 0;
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
