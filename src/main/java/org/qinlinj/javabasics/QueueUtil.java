package org.qinlinj.javabasics;

import java.util.*;

public class QueueUtil {
    public static void main(String[] args) {
        // common implementation classes are LinkedList and PriorityQueue
        Queue<Integer> queue = new LinkedList<>();

        // add element at the end of the queue
        queue.offer(1);
        queue.offer(2);
        queue.offer(10);

        // check if the queue is empty
        System.out.println("is empty: " + queue.isEmpty());

        // get size
        System.out.println("size: " + queue.size());
    }
}
