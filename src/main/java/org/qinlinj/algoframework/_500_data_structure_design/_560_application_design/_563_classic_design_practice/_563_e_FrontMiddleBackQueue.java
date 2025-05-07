package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 1670: Design Front Middle Back Queue
 * <p>
 * This problem involves designing a queue that supports operations at the front, middle, and back positions.
 * <p>
 * Problem Description:
 * - Implement a queue that supports the following operations:
 * - pushFront(val): Add an element to the front of the queue
 * - pushMiddle(val): Add an element to the middle of the queue
 * - pushBack(val): Add an element to the back of the queue
 * - popFront(): Remove and return the front element
 * - popMiddle(): Remove and return the middle element
 * - popBack(): Remove and return the back element
 * <p>
 * - Special rules for middle operations:
 * - When there are two middle positions, the operation applies to the front-middle position
 * - For example: in [1,2,3,4,5,6], the middle position for insertion is after 3
 * - For popping: from [1,2,3,4,5,6], popMiddle() returns 3
 * <p>
 * Key Insight:
 * - Use two linked lists to represent the left and right halves of the queue
 * - Maintain a balance so the right half has at most one more element than the left half
 * - This arrangement makes middle operations efficient
 * - After each operation, rebalance the lists to maintain this property
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) for storing all elements
 */

import java.util.*;

public class _563_e_FrontMiddleBackQueue {

    public static void main(String[] args) {
        // Example from problem description
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();

        System.out.println("Operations and results:");
        System.out.println("------------------------");

        System.out.println("pushFront(1)");
        q.pushFront(1);
        printState(q);

        System.out.println("pushBack(2)");
        q.pushBack(2);
        printState(q);

        System.out.println("pushMiddle(3)");
        q.pushMiddle(3);
        printState(q);

        System.out.println("pushMiddle(4)");
        q.pushMiddle(4);
        printState(q);

        System.out.println("popFront() -> " + q.popFront());
        printState(q);

        System.out.println("popMiddle() -> " + q.popMiddle());
        printState(q);

        System.out.println("popMiddle() -> " + q.popMiddle());
        printState(q);

        System.out.println("popBack() -> " + q.popBack());
        printState(q);

        System.out.println("popFront() -> " + q.popFront());
        printState(q);
    }

    /**
     * Helper method to print the current state of the queue
     */
    private static void printState(FrontMiddleBackQueue q) {
        System.out.println("Current state: " + q);
        System.out.println();
    }

    static class FrontMiddleBackQueue {
        // Left half of the queue (front elements)
        private LinkedList<Integer> left;

        // Right half of the queue (back elements)
        private LinkedList<Integer> right;

        public FrontMiddleBackQueue() {
            left = new LinkedList<>();
            right = new LinkedList<>();
        }

        /**
         * Add an element to the front of the queue
         */
        public void pushFront(int val) {
            // Add to the front of the left list
            left.addFirst(val);

            // Rebalance if needed
            balance();
        }

        /**
         * Add an element to the middle of the queue
         */
        public void pushMiddle(int val) {
            if (size() % 2 == 0) {
                // Even total elements: add to the beginning of right list
                right.addFirst(val);
            } else {
                // Odd total elements: add to the end of left list
                left.addLast(val);
            }

            // Rebalance if needed
            balance();
        }

        /**
         * Add an element to the back of the queue
         */
        public void pushBack(int val) {
            // Add to the end of the right list
            right.addLast(val);

            // Rebalance if needed
            balance();
        }

        /**
         * Remove and return the front element
         */
        public int popFront() {
            if (size() == 0) {
                return -1;
            }

            // Special case: only one element in the queue (in right list)
            if (left.isEmpty()) {
                return right.removeFirst();
            }

            // Normal case: remove from front of left list
            int val = left.removeFirst();

            // Rebalance if needed
            balance();

            return val;
        }

        /**
         * Remove and return the middle element
         */
        public int popMiddle() {
            if (size() == 0) {
                return -1;
            }

            int val;
            if (size() % 2 == 0) {
                // Even number of elements: get from end of left list
                val = left.removeLast();
            } else {
                // Odd number of elements: get from front of right list
                val = right.removeFirst();
            }

            // Rebalance if needed
            balance();

            return val;
        }

        /**
         * Remove and return the back element
         */
        public int popBack() {
            if (size() == 0) {
                return -1;
            }

            // Remove from end of right list
            int val = right.removeLast();

            // Rebalance if needed
            balance();

            return val;
        }

        /**
         * Return the total number of elements in the queue
         */
        public int size() {
            return left.size() + right.size();
        }

        /**
         * Rebalance the two halves to maintain the property:
         * - right side has at most one more element than left side
         * - left size cannot exceed right size
         */
        private void balance() {
            // If right has too many elements, move one to left
            if (right.size() > left.size() + 1) {
                left.addLast(right.removeFirst());
            }

            // If left has more elements than right, move one to right
            if (left.size() > right.size()) {
                right.addFirst(left.removeLast());
            }
        }

        /**
         * String representation of the queue for debugging
         */
        @Override
        public String toString() {
            if (size() == 0) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");

            // Add left elements
            for (Integer val : left) {
                sb.append(val).append(", ");
            }

            // Add right elements
            for (Integer val : right) {
                sb.append(val).append(", ");
            }

            // Remove trailing comma and space
            sb.setLength(sb.length() - 2);
            sb.append("]");

            return sb.toString();
        }
    }
}