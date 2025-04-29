package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Summary
 * <p>
 * This class provides a comprehensive overview of classic queue-related problems
 * and their implementations. Queue problems typically leverage the First-In-First-Out (FIFO)
 * property to solve various algorithmic challenges.
 * <p>
 * The problems covered include:
 * <p>
 * 1. Number of Recent Requests (LeetCode 933)
 * - Tracking and counting requests within a sliding time window
 * - Demonstrates queue's application for maintaining time-based sequences
 * <p>
 * 2. Design Circular Queue (LeetCode 622)
 * - Implementation of a basic queue with fixed capacity
 * - Efficient space usage by connecting the end to the beginning (circular)
 * <p>
 * 3. Design Circular Deque (LeetCode 641)
 * - Extension of circular queue with operations at both ends
 * - Demonstrates double-ended queue implementation
 * <p>
 * 4. Design Front Middle Back Queue (LeetCode 1670)
 * - Advanced queue with operations at front, middle, and back
 * - Uses two linked lists to efficiently maintain and access the middle
 * <p>
 * 5. Time Needed to Buy Tickets (LeetCode 2073)
 * - Transforms a queue simulation problem into a mathematical solution
 * - Shows how understanding queue behavior leads to efficient algorithms
 * <p>
 * Common implementation techniques:
 * - Array-based circular buffers for fixed-size queues
 * - Linked lists for dynamic queues
 * - Maintaining pointers/indices to track front and back positions
 * - Balancing strategies for specialized queue operations
 */

public class _344_f_QueueProblemsOverview {

    public static void main(String[] args) {
        System.out.println("--- Queue Classic Problems Overview ---");

        // Demonstrate each queue implementation
        demonstrateRecentCounter();
        demonstrateCircularQueue();
        demonstrateCircularDeque();
        demonstrateFrontMiddleBackQueue();
        demonstrateTicketTime();
    }

    // Test Recent Counter implementation
    private static void demonstrateRecentCounter() {
        System.out.println("\n1. Recent Counter (LeetCode 933)");
        System.out.println("A time-based sliding window implementation using queue");

        _344_a_RecentCounter counter = new _344_a_RecentCounter();
        System.out.println("ping(1) = " + counter.ping(1));       // 1
        System.out.println("ping(100) = " + counter.ping(100));   // 2
        System.out.println("ping(3001) = " + counter.ping(3001)); // 3
        System.out.println("ping(3002) = " + counter.ping(3002)); // 3

        System.out.println("This problem demonstrates how queues can be used to track temporal events efficiently.");
    }

    // Test Circular Queue implementation
    private static void demonstrateCircularQueue() {
        System.out.println("\n2. Circular Queue (LeetCode 622)");
        System.out.println("Basic queue implementation with circular buffer for space efficiency");

        _344_b_CircularQueue circularQueue = new _344_b_CircularQueue(3);
        System.out.println("enQueue(1) = " + circularQueue.enQueue(1));  // true
        System.out.println("enQueue(2) = " + circularQueue.enQueue(2));  // true
        System.out.println("enQueue(3) = " + circularQueue.enQueue(3));  // true
        System.out.println("enQueue(4) = " + circularQueue.enQueue(4));  // false (full)
        System.out.println("Rear() = " + circularQueue.Rear());          // 3
        System.out.println("isFull() = " + circularQueue.isFull());      // true
        System.out.println("deQueue() = " + circularQueue.deQueue());    // true
        System.out.println("enQueue(4) = " + circularQueue.enQueue(4));  // true
        System.out.println("Rear() = " + circularQueue.Rear());          // 4

        System.out.println("This implementation shows how circular arrays efficiently reuse space.");
    }

    // Test Circular Deque implementation
    private static void demonstrateCircularDeque() {
        System.out.println("\n3. Circular Deque (LeetCode 641)");
        System.out.println("Double-ended queue with operations at both ends");

        _344_c_CircularDeque circularDeque = new _344_c_CircularDeque(3);
        System.out.println("insertLast(1) = " + circularDeque.insertLast(1));    // true
        System.out.println("insertLast(2) = " + circularDeque.insertLast(2));    // true
        System.out.println("insertFront(3) = " + circularDeque.insertFront(3));  // true
        System.out.println("insertFront(4) = " + circularDeque.insertFront(4));  // false (full)
        System.out.println("getRear() = " + circularDeque.getRear());            // 2
        System.out.println("isFull() = " + circularDeque.isFull());              // true
        System.out.println("deleteLast() = " + circularDeque.deleteLast());      // true
        System.out.println("insertFront(4) = " + circularDeque.insertFront(4));  // true
        System.out.println("getFront() = " + circularDeque.getFront());          // 4

        System.out.println("Deques combine the features of stacks and queues, allowing flexible operations.");
    }

    // Test Front Middle Back Queue implementation
    private static void demonstrateFrontMiddleBackQueue() {
        System.out.println("\n4. Front Middle Back Queue (LeetCode 1670)");
        System.out.println("Advanced queue with operations at front, middle, and back positions");

        _344_d_FrontMiddleBackQueue q = new _344_d_FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]

        System.out.println("After pushFront(1), pushBack(2), pushMiddle(3), pushMiddle(4)");
        System.out.println("popFront() = " + q.popFront());     // 1 -> [4, 3, 2]
        System.out.println("popMiddle() = " + q.popMiddle());   // 3 -> [4, 2]
        System.out.println("popMiddle() = " + q.popMiddle());   // 4 -> [2]
        System.out.println("popBack() = " + q.popBack());       // 2 -> []
        System.out.println("popFront() = " + q.popFront());     // -1 (empty)

        System.out.println("This implementation shows how to maintain the middle element efficiently.");
    }

    // Test Time Needed to Buy Tickets implementation
    private static void demonstrateTicketTime() {
        System.out.println("\n5. Time Needed to Buy Tickets (LeetCode 2073)");
        System.out.println("Queue simulation problem with mathematical optimization");

        _344_e_TimeRequiredToBuy solution = new _344_e_TimeRequiredToBuy();

        int[] tickets1 = {2, 3, 2};
        int k1 = 2;
        System.out.println("For tickets [2, 3, 2] and k=2: " + solution.timeRequiredToBuy(tickets1, k1));  // 6

        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        System.out.println("For tickets [5, 1, 1, 1] and k=0: " + solution.timeRequiredToBuy(tickets2, k2));  // 8

        System.out.println("This problem demonstrates how understanding queue behavior can lead to optimization.");
    }
}