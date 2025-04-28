package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._341_queue_stack_implementation;

/**
 * IMPLEMENTING A STACK USING A QUEUE
 * <p>
 * Key Concepts:
 * 1. A stack is a LIFO (Last-In-First-Out) data structure.
 * 2. A queue is a FIFO (First-In-First-Out) data structure.
 * 3. We can implement a stack using a single queue by:
 * - Adding elements directly to the queue
 * - Rearranging the queue after each push to maintain LIFO order
 * - Keeping track of the top element for O(1) top operations
 * <p>
 * Time Complexity Analysis:
 * - push: O(1) - Simply add to queue
 * - pop: O(n) - Need to rotate the queue to get the newest element
 * - top: O(1) - We keep track of the top element
 * - empty: O(1) - Simple check of queue emptiness
 * <p>
 * The core technique is to "rotate" the queue after each pop operation
 * by moving elements from the front to the back until reaching the last element.
 */

import java.util.*;

public class MyStack {
    private Queue<Integer> q;
    private int topElement; // Tracks the most recently added element (stack top)

    public MyStack() {
        q = new LinkedList<>();
    }

    /**
     * Push element onto the stack
     * Add to queue and update topElement
     */
    public void push(int x) {
        q.offer(x);
        topElement = x;
    }

    /**
     * Remove the element on top of the stack and return it
     * Rearrange queue to move all but the last element to the back
     */
    public int pop() {
        int size = q.size();

        // Move all elements except the last two
        while (size > 2) {
            q.offer(q.poll());
            size--;
        }

        // Update the topElement to be the new last element
        if (size > 1) {
            topElement = q.peek();
            q.offer(q.poll());
        }

        // Remove and return the last element (which was the top of the stack)
        return q.poll();
    }

    /**
     * Get the top element without removing it
     */
    public int top() {
        return topElement;
    }

    /**
     * Check if the stack is empty
     */
    public boolean empty() {
        return q.isEmpty();
    }
}
