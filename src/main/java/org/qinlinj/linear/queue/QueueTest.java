package org.qinlinj.linear.queue;

/**
 * A demonstration class for LinkedListQueue operations.
 * This class showcases various methods of the LinkedListQueue implementation
 * and provides a simple way to verify its functionality.
 */
public class QueueTest {
    /**
     * Main method to demonstrate LinkedListQueue functionality.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new LinkedListQueue of Integers
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // Enqueue first element
        queue.enqueue(10);
        System.out.println("After enqueuing 10: " + queue);

        // Enqueue second element
        queue.enqueue(20);
        System.out.println("After enqueuing 20: " + queue);

        // Enqueue third element
        queue.enqueue(30);
        System.out.println("After enqueuing 30: " + queue);

        // Dequeue first element
        queue.dequeue();
        System.out.println("After first dequeue: " + queue);

        // Dequeue second element
        queue.dequeue();
        System.out.println("After second dequeue: " + queue);

        // Dequeue third element
        queue.dequeue();
        System.out.println("After third dequeue: " + queue);

        // Attempt to dequeue from an empty queue (will throw an exception)
        try {
            queue.dequeue();
            System.out.println("This line should not be reached");
        } catch (RuntimeException e) {
            System.out.println("Exception caught when dequeuing from empty queue: " + e.getMessage());
        }
    }
}