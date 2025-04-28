package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._341_queue_stack_implementation;

/**
 * IMPLEMENTING A QUEUE USING TWO STACKS
 * <p>
 * Key Concepts:
 * 1. A queue is a FIFO (First-In-First-Out) data structure.
 * 2. A stack is a LIFO (Last-In-First-Out) data structure.
 * 3. We can implement a queue using two stacks by:
 * - Using stack s1 for pushing elements (enqueue operation)
 * - Using stack s2 as a buffer to reverse the order when dequeuing
 * <p>
 * Time Complexity Analysis:
 * - push: O(1) - Simply add to stack s1
 * - peek/pop:
 * - Worst-case: O(n) when s2 is empty and elements need to be transferred from s1
 * - Amortized: O(1) since each element is transferred at most once
 * - empty: O(1) - Simple check of both stacks
 * <p>
 * The core insight is that transferring elements from one stack to another
 * reverses their order. This "double negative" effect allows us to convert
 * LIFO to FIFO behavior.
 */

import java.util.*;

public class MyQueue {
    private Stack<Integer> s1; // for pushing elements
    private Stack<Integer> s2; // for popping elements

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Add element to the queue
     * Simply push to stack s1
     */
    public void push(int x) {
        s1.push(x);
    }

    /**
     * Remove the element from the front of the queue and return it
     * First ensure s2 has elements by calling peek()
     */
    public int pop() {
        // This call ensures s2 is not empty
        peek();
        return s2.pop();
    }

    /**
     * Return the element at the front of the queue without removing it
     * If s2 is empty, transfer all elements from s1 to s2 to reverse their order
     */
    public int peek() {
        if (s2.isEmpty()) {
            // Transfer all elements from s1 to s2
            // This reverses the order, making the oldest element on top
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        return s2.peek();
    }

    /**
     * Check if the queue is empty
     * The queue is empty only if both stacks are empty
     */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
