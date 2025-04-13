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
        System.out.println(list);
        // add an element to the top of the LinkedList
        list.addFirst(0);
        System.out.println(list);

        // get the element at the top and end of the LinkedList
        System.out.println("the element at the top of the LinkedList: " + list.getFirst() + "\nthe element at the end of the LinkedList: " + list.getLast());

        // remove the element at the top of the LinkedList
        list.removeFirst();
        // remove the element at the end of the LinkedList
        list.removeLast();

        System.out.println(list);
    }
}
