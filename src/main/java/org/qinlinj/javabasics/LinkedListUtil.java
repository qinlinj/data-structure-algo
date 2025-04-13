package org.qinlinj.javabasics;

import java.util.*;

public class LinkedListUtil {
    public static void main(String[] args) {
        // init LinkedList with specific element
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(list);

        System.out.println(list.isEmpty());
        System.out.println("List size: " + list.size());

        // add an element to the end of the LinkedList
        list.addLast(4);
        // add an element to the top of the LinkedList
        list.addLast(0);


        // get the element at the end of the LinkedList
        System.out.println("the element at the end of the LinkedList: " + list.get(list.size() - 1));
        // remove the element at the end of the LinkedList
        list.remove(list.size() - 1);

        System.out.println(list);
    }
}
