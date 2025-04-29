package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Problem 4: Design Front Middle Back Queue (LeetCode 1670)
 * <p>
 * This implementation demonstrates a more complex queue that supports operations
 * at the front, middle, and back positions. The key challenge is efficiently
 * maintaining and accessing the middle element.
 * <p>
 * Problem approach:
 * - Split the queue into two parts (left and right) using LinkedLists
 * - Maintain the balance between the two parts such that right part has at most one more element
 * - For odd-length queues, the middle is first element of right part
 * - For even-length queues, the middle is between left's last and right's first
 * <p>
 * Operations supported:
 * - pushFront(val): Add element at the front
 * - pushMiddle(val): Add element at the middle
 * - pushBack(val): Add element at the back
 * - popFront(): Remove and return the front element
 * - popMiddle(): Remove and return the middle element
 * - popBack(): Remove and return the back element
 * <p>
 * Time Complexity:
 * - All operations are O(1) amortized time
 * <p>
 * Space Complexity:
 * - O(n) where n is the number of elements in the queue
 */

import java.util.*;

class _344_d_FrontMiddleBackQueue {
    // Two linked lists to represent left and right parts of the queue
    private LinkedList<Integer> left = new LinkedList<>();
    private LinkedList<Integer> right = new LinkedList<>();

    // Constructor
    public _344_d_FrontMiddleBackQueue() {
        // No initialization needed beyond field initialization
    }

    // Main method to demonstrate the implementation
    public static void main(String[] args) {
        _344_d_FrontMiddleBackQueue q = new _344_d_FrontMiddleBackQueue();

        // Test operations
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]

        System.out.println("Pop front: " + q.popFront());     // Returns 1 -> [4, 3, 2]
        System.out.println("Pop middle: " + q.popMiddle());   // Returns 3 -> [4, 2]
        System.out.println("Pop middle: " + q.popMiddle());   // Returns 4 -> [2]
        System.out.println("Pop back: " + q.popBack());       // Returns 2 -> []
        System.out.println("Pop front: " + q.popFront());     // Returns -1 (queue is empty)
    }

    /**
     * Balances the two lists to maintain our invariant:
     * - If total size is even: left.size() == right.size()
     * - If total size is odd: right.size() == left.size() + 1
     *
     * This ensures that:
     * 1. The middle element is always the first element in right
     * 2. The right side never has more than one extra element
     */
    private void balance() {
        // If right has too many elements, move one to left
        if (right.size() > left.size() + 1) {
            left.addLast(right.removeFirst());
        }

        // If left has too many elements, move one to right
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }
    }

    /**
     * Add an element to the front of the queue
     */
    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    /**
     * Add an element to the middle of the queue
     * For even lengths: insert before middle position
     * For odd lengths: insert at middle position
     */
    public void pushMiddle(int val) {
        if (size() % 2 == 0) {
            // For even number of elements, add to beginning of right
            right.addFirst(val);
        } else {
            // For odd number of elements, add to end of left
            left.addLast(val);
        }
        balance();
    }

    /**
     * Add an element to the back of the queue
     */
    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    /**
     * Remove and return the front element
     * Returns -1 if the queue is empty
     */
    public int popFront() {
        if (size() == 0) {
            return -1;
        }

        int result;
        if (size() == 1) {
            // Special case: only one element (in right)
            result = right.removeFirst();
        } else {
            // Normal case: remove from left
            result = left.removeFirst();
        }

        balance();
        return result;
    }

    /**
     * Remove and return the middle element
     * Returns -1 if the queue is empty
     */
    public int popMiddle() {
        if (size() == 0) {
            return -1;
        }

        int result;
        if (size() % 2 == 0) {
            // Even number of elements, remove from end of left
            result = left.removeLast();
        } else {
            // Odd number of elements, remove from front of right
            result = right.removeFirst();
        }

        balance();
        return result;
    }

    /**
     * Remove and return the back element
     * Returns -1 if the queue is empty
     */
    public int popBack() {
        if (size() == 0) {
            return -1;
        }

        int result = right.removeLast();
        balance();
        return result;
    }

    /**
     * Returns the total size of the queue
     */
    public int size() {
        return left.size() + right.size();
    }
}
