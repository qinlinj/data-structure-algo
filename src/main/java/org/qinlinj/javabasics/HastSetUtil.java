package org.qinlinj.javabasics;

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

        // remove an exist element

        // traverse the HashSet

    }
}
