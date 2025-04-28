package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 225: Implement Stack using Queues
 * <p>
 * Problem Description:
 * Implement a last-in-first-out (LIFO) stack using only standard queue operations.
 * Implement the MyStack class:
 * - void push(int x) Pushes element x to the top of the stack.
 * - int pop() Removes the element on the top of the stack and returns it.
 * - int top() Returns the element on the top of the stack.
 * - boolean empty() Returns true if the stack is empty, false otherwise.
 * <p>
 * Notes:
 * - You must use only standard queue operations (push to back, peek/pop from front, size, and is empty).
 * - The challenge is to convert the FIFO (First-In-First-Out) behavior of a queue to
 * the LIFO (Last-In-First-Out) behavior of a stack.
 * <p>
 * Solution Approach:
 * We implement the stack using a single queue by:
 * 1. For push: Add element to queue and keep track of the top element
 * 2. For pop: "Rotate" the queue by moving all elements except the last one to the
 * back of the queue, then remove and return the last element
 * <p>
 * Time Complexity:
 * - push: O(1)
 * - pop: O(n) where n is the number of elements in the stack
 * - top: O(1)
 * - empty: O(1)
 * <p>
 * Space Complexity: O(n) to store the elements
 */

import java.util.*;

public class _342_e_StackUsingQueue {
    private Queue<Integer> queue;
    private int topElement; // Tracks the most recently added element (stack top)

    /** Initialize your data structure here. */
    public _342_e_StackUsingQueue() {
        queue = new LinkedList<>();
    }

    // Example usage
    public static void main(String[] args) {
        _342_e_StackUsingQueue stack = new _342_e_StackUsingQueue();

        stack.push(1);
        stack.push(2);
        System.out.println("Top element: " + stack.top()); // Output: 2
        System.out.println("Pop: " + stack.pop()); // Output: 2
        System.out.println("Is empty: " + stack.empty()); // Output: false
        System.out.println("Top element: " + stack.top()); // Output: 1
        System.out.println("Pop: " + stack.pop()); // Output: 1
        System.out.println("Is empty: " + stack.empty()); // Output: true
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        topElement = x; // Update the top element
    }

    /** Removes the element on top of the stack and return it. */
    public int pop() {
        int size = queue.size();

        // Move all elements except the last two
        while (size > 2) {
            queue.offer(queue.poll());
            size--;
        }

        // Update the top element to be the new last element
        if (size > 1) {
            topElement = queue.peek();
            queue.offer(queue.poll());
        }

        // Remove and return the last element (which was the top of the stack)
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return topElement;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
