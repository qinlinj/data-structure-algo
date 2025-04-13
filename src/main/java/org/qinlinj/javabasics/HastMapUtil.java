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
        

    }
}
