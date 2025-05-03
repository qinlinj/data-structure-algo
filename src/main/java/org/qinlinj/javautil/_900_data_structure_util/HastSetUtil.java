package org.qinlinj.javautil._900_data_structure_util;

import java.util.*;

public class HastSetUtil {
    public static void main(String[] args) {
        // init a HashSet
        HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(set);

        // check if the HashSet is empty
        System.out.println(set.isEmpty());

        // get size
        System.out.println(set.size());

        // check if the HashSet has a specific key
        if (set.contains(3)) {
            System.out.println("Element 3 found.");
        } else {
            System.out.println("Element 3 not found.");
        }

        // add a new element
        set.add(10);
        System.out.println(set);

        // remove an exist element
        set.remove(3);
        System.out.println(set);

        // traverse the HashSet
        for (int element : set) {
            System.out.println(element);
        }
    }
}
