package org.qinlinj.practical.bitmap;

public class BitMap1 {
    private byte b;

    public BitMap1() {
        b = 0;
    }

    public void set(int num) {
        b |= (1 << num);
    }
}
