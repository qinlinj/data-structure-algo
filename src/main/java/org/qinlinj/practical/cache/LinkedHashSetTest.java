package org.qinlinj.practical.cache;

import java.util.*;

public class LinkedHashSetTest {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3);
        set.add(1);
        set.add(10);
        set.add(1);
        set.add(3);
        System.out.println(set);
    }
}
