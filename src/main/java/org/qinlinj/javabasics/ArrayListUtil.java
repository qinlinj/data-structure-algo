package org.qinlinj.javabasics;

import java.util.*;

public class ArrayListImp {
    public static void main(String[] args) {
        int n = 10;

        // init a ArrayList
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(n, 0));
        // print
        System.out.println(list);

        // check if the arraylist is empty
        System.out.println("check if the arraylist is empty: " + list.isEmpty());

        // print arraylist size
        System.out.println("size of the arraylist is: " + list.size());

        // insert an element at the end of arraylist
        list.add(10);

        // get the end of the element of arraylist
        System.out.println("the end of the element: " + list.get(list.size() - 1));

        // remove the end of the element of arraylist
        list.remove(list.size() - 1);

        // change or get element of arraylist by index
        System.out.println("the element of arraylist at index 3 is: " + list.get(3));
        list.set(3, 10);
        System.out.println("the changed element of arraylist at index 3 is: " + list.get(3));
        list.set(4, 6);
        list.set(5, 8);

        // insert an element 5 at index 3
        list.add(3, 5);
        System.out.println("added arraylist is: " + list);

        // remove the element at index 4
        list.remove(3);
        System.out.println("the arraylist with element removed at index 3 is : " + list);

        // swap the element at 3 and 5
        Collections.swap(list, 3, 5);

        // traverse the arraylist
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }
}
