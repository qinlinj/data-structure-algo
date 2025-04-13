package org.qinlinj.javabasics;

import java.util.*;

public class LinkedListUtil {
    public static void main(String[] args) {
        // init LinkedList with specific element
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(list);

        System.out.println(list.isEmpty());
        System.out.println(list.size());

        // add an element to the end of the LinkedList
        list.add(4);
        
    }
}
