package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_k_SeatManager
 * <p>
 * LeetCode #1845: Seat Reservation Manager
 * <p>
 * This class manages seat reservations with n seats numbered from 1 to n.
 * It efficiently supports reserving the smallest available seat number and
 * unreserving specific seats.
 * <p>
 * Approach:
 * 1. Use a min-heap (priority queue) to keep track of available seat numbers
 * 2. When initializing, add all seat numbers from 1 to n to the heap
 * 3. For reservation, poll the smallest seat number from the heap
 * 4. For unreservation, add the seat number back to the heap
 * <p>
 * The priority queue automatically maintains the order of available seats,
 * ensuring we always get the smallest available seat number in O(log n) time.
 * <p>
 * Time Complexity:
 * - Constructor: O(n log n) to initialize n seats
 * - reserve(): O(log n)
 * - unreserve(): O(log n)
 * <p>
 * Space Complexity: O(n) for storing available seat numbers
 */

import java.util.*;

public class _531_k_SeatManager {
    private final PriorityQueue<Integer> availableSeats;

    public _531_k_SeatManager(int n) {
        availableSeats = new PriorityQueue<>();

        // Initialize with all seats available
        for (int i = 1; i <= n; i++) {
            availableSeats.offer(i);
        }
    }

    public static void main(String[] args) {
        // Test case from the problem
        _531_k_SeatManager seatManager = new _531_k_SeatManager(5);

        System.out.println("Initialized with 5 seats");

        System.out.println("Reserve: " + seatManager.reserve());  // Output: 1
        System.out.println("Reserve: " + seatManager.reserve());  // Output: 2

        System.out.println("Unreserve seat 2");
        seatManager.unreserve(2);

        System.out.println("Reserve: " + seatManager.reserve());  // Output: 2
        System.out.println("Reserve: " + seatManager.reserve());  // Output: 3
        System.out.println("Reserve: " + seatManager.reserve());  // Output: 4
        System.out.println("Reserve: " + seatManager.reserve());  // Output: 5

        System.out.println("Unreserve seat 5");
        seatManager.unreserve(5);

        System.out.println("Reserve: " + seatManager.reserve());  // Output: 5
    }

    /**
     * Reserves the smallest-numbered unreserved seat
     * @return the smallest available seat number
     */
    public int reserve() {
        // Get and remove the smallest seat number
        return availableSeats.poll();
    }

    // Optimization: We could avoid initializing all seats in the constructor
    // by tracking the next unused seat and only adding unreserved seats to the queue

    /**
     * Unreserves a seat so it becomes available for future reservations
     * @param seatNumber the seat number to unreserve
     */
    public void unreserve(int seatNumber) {
        // Add the seat back to available seats
        availableSeats.offer(seatNumber);
    }
}