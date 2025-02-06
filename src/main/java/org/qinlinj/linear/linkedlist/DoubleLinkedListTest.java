package org.qinlinj.linear.linkedlist;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

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

        linkedList.set(1, 2);
        System.out.println(linkedList);

        System.out.println(linkedList.find(34));

        System.out.println(linkedList.contains(34));

        linkedList.removeElement(34);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
    }
}