package org.qinlinj.nonlinear.highlevel.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        // Java built-in priority queue, which is implemented by default using small top heap
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);

        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }
}
