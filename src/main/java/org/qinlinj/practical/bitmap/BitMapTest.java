package org.qinlinj.practical.bitmap;

import java.util.*;

public class BitMapTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int[] data = new int[10_000_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100_000_000);
        }

        int target = data[8888];
    }

}
