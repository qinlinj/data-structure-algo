package org.qinlinj.nonlinear.highlevel.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Test class for demonstrating Java's PriorityQueue functionality.
 */
public class PriorityQueueTest {
    /**
     * Main method to run the demonstration.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Java built-in priority queue, which is implemented by default using min heap
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Create a custom priority queue with a max heap implementation
        // by using a comparator that reverses the natural ordering
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // Reverse order for max heap behavior
            }
        });

        // Add elements to the priority queue
        pq.add(13);
        pq.add(10);
        pq.add(56);

        // Remove and print the highest priority elements
        // Since we're using a max heap, this will print the largest values first
        System.out.println(pq.remove()); // Should print 56
        System.out.println(pq.remove()); // Should print 13
    }
}
