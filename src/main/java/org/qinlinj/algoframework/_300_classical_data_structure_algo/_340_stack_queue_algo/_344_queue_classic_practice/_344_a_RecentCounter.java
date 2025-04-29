package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Problem 1: Number of Recent Requests (LeetCode 933)
 * <p>
 * This problem demonstrates the queue's first-in-first-out property for time-based operations.
 * <p>
 * Problem Description:
 * Implement the RecentCounter class to track requests within a specific time window:
 * - RecentCounter() initializes the counter with 0 requests
 * - int ping(int t) adds a new request at time t and returns the number of requests
 * within the time window [t-3000, t] (including the new request)
 * <p>
 * Notes:
 * - Each successive call to ping uses a strictly increasing value of t
 * - The solution leverages a queue to maintain a sliding window of requests
 * - Older requests (outside the 3000ms window) are removed from the front of the queue
 * - The size of the queue after processing represents the answer
 * <p>
 * Time Complexity:
 * - O(1) amortized time for each ping operation, as each request is enqueued and dequeued at most once
 * <p>
 * Space Complexity:
 * - O(n) where n is the maximum number of requests that can occur in a 3000ms window
 */

import java.util.*;

class _344_a_RecentCounter {
    // Queue to store timestamps of requests
    private Queue<Integer> requestTimes;

    // Initialize the counter
    public _344_a_RecentCounter() {
        requestTimes = new LinkedList<>();
    }

    // Main method to demonstrate the implementation
    public static void main(String[] args) {
        _344_a_RecentCounter counter = new _344_a_RecentCounter();

        // Test cases
        System.out.println(counter.ping(1));     // Returns 1: only request [1]
        System.out.println(counter.ping(100));   // Returns 2: requests [1, 100]
        System.out.println(counter.ping(3001));  // Returns 3: requests [1, 100, 3001]
        System.out.println(counter.ping(3002));  // Returns 3: requests [1, 100, 3001, 3002], but 1 < 3002-3000, so remove 1
        // Now have [100, 3001, 3002]
    }

    /**
     * Add a new request at time t and return the count of requests within [t-3000, t]
     * @param t the timestamp of the new request
     * @return number of requests within the last 3000ms
     */
    public int ping(int t) {
        // Add the current request timestamp to the queue
        requestTimes.offer(t);

        // Remove all requests outside the 3000ms window
        while (!requestTimes.isEmpty() && requestTimes.peek() < t - 3000) {
            requestTimes.poll();
        }

        // Return the count of requests in the queue (within the window)
        return requestTimes.size();
    }
}