package org.qinlinj.util;

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

        // get the value of the specific key, or returns null if it does not exist
        System.out.println(map.get(2));
        System.out.println(map.get(4));

        // get the value of the specific key, or returns default value if it does not exist
        System.out.println(map.getOrDefault(2, "Default Value"));
        System.out.println(map.getOrDefault(4, "Default Value"));

        // insert a new key-value pair
        map.put(10, "ten");

        // remove an exist key-value pair by a specific key
        map.remove(2);

        // check if the hashmap has a specific key
        if (map.containsKey(2)) {
            System.out.println("Key 2 -> " + map.get(2));
        } else {
            System.out.println("Key 2 not found.");
        }

        // traverse the hashmap
        for (Map.Entry<Integer, String> integerStringEntry : map.entrySet()) {
            System.out.println(integerStringEntry.getKey() + " -> " + integerStringEntry.getValue());
        }
    }
}
