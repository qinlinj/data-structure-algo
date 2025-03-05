package org.qinlinj.nonlinear.highlevel.map;

import java.util.HashSet;
import java.util.Set;

/**
 * Class demonstrating basic Java Set and Map implementations
 */
public class JavaSetMap {
    public static void main(String[] args) {
        // TreeSet: implemented using Red-Black tree, elements are ordered
        // HashSet: implemented using hash function, uses array + linked list for collision resolution
        Set<Integer> set = new HashSet<>();

        // TreeMap: implemented using Red-Black tree, keys are ordered
        // HashMap: implemented using hash function, uses array + linked list for collision resolution
        Map<Integer, Integer> map = new HashMap<>();
    }
}
