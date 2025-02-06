package org.qinlinj.linear.linkedlist;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addLast(5);
        System.out.println(linkedList);

        linkedList.addFirst(10);
        System.out.println(linkedList);

        linkedList.add(2, 34);
        System.out.println(linkedList);

        System.out.println(linkedList.get(1));

        linkedList.addFirst(50);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
    }
}