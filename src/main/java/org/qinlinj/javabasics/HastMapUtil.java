package org.qinlinj.javabasics;

import java.util.*;

public class HastMapUtil {
    public static void main(String[] args) {
        // init a HashMap
        HashMap<Integer, String> map = new HashMap<>();

        // add data
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        System.out.println(map);

        // check if the hashmap is empty
        System.out.println("is empty: " + map.isEmpty());

        // get size
        System.out.println("size: " + map.size());

        // check if the hashmap has a specific key
        if (map.containsKey(2)) {
            System.out.println("Key 2 -> " + map.get(2));
        } else {
            System.out.println("Key 2 not found.");
        }

        // get the value of the specific key
        System.out.println(map.get(2));
        // null
        System.out.println(map.get(4));
    }
}
