package org.qinlinj.practical.cache;

import java.util.*;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<>(3, 0.75F, true);

        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("--------------------------------------");

        map.put(2, 10);
        map.get(5);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
