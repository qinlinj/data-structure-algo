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
}
