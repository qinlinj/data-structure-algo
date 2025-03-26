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

       /* // 1. HashMap
        Set<Integer> set = new HashSet<>(); // 10_000_000 * 4 / 0.75 = 51 MB
        for (int i = 0; i < data.length; i++) {
            set.add(data[i]);
        }
        if (set.contains(target)) { // O(1)
            System.out.println("" + target);
        }

        // 2. boolean Array
        boolean[] arr = new boolean[100_000_000]; // 100_000_000 byte = 95 MB
        for (int i = 0; i < data.length; i++) {
            arr[data[i]] = true;
        }
        if (arr[target]) { // O(1)
            System.out.println("" + target);
        }*/

        // 20 MB --> BitMap
        BitMap bitMap = new BitMap(100_000_000); // 100_000_000 bit = 12MB
        for (int i = 0; i < data.length; i++) {
            bitMap.set(data[i]);
        }
        if (bitMap.contains(target)) { // O(1)
            System.out.println("" + target);
        }
    }
}
