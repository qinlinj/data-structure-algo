package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._544_segment_tree_practice; /**
 * LeetCode 731: My Calendar II
 * <p>
 * Problem Description:
 * Implement a calendar system that allows booking time intervals with at most double booking.
 * That is, a new booking is allowed if it would create at most a double-booking,
 * but must be rejected if it would create a triple booking.
 * <p>
 * Key Concepts:
 * 1. Overlapping Interval Counting - Tracking the number of overlapping bookings at each time point
 * 2. Segment Tree with Range Updates - Using a max segment tree to track the maximum number of
 * bookings in any given interval
 * 3. Double Booking vs. Triple Booking - Allowing up to 2 overlapping bookings, but rejecting if it
 * would create 3 or more
 * <p>
 * Solution Approaches:
 * 1. Segment Tree: O(log N) time for both queries and updates
 * - Create a max segment tree that tracks the maximum number of bookings in any interval
 * - For each new booking, check if adding it would cause any interval to have more than 2 bookings
 * - If not, update the segment tree for that interval
 * <p>
 * 2. Two Lists Approach: O(N²) time complexity where N is the number of bookings
 * - Maintain two lists: one for all bookings and one for overlapping intervals
 * - For each new booking, check if it overlaps with any interval in the "overlap" list
 * - If yes, reject it as it would create a triple booking
 * - If not, check for overlaps with the "bookings" list and add any overlaps to the "overlap" list
 * <p>
 * The segment tree approach is more efficient and scalable for larger inputs.
 */

import java.util.*;
import org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree._543_c_AllInOneSegmentTree;

public class _544_b_MyCalendarTwo {
    // Using the Segment Tree approach for efficient range queries and updates
    private final _543_c_AllInOneSegmentTree segmentTree;

    public _544_b_MyCalendarTwo() {
        // Create a max segment tree with a range of 0 to 10^9
        // Initial value 0 means no bookings
        segmentTree = new _543_c_AllInOneSegmentTree(0, 1000000000, 0, "max");
    }

    public static void main(String[] args) {
        _544_b_MyCalendarTwo calendar = new _544_b_MyCalendarTwo();

        System.out.println("Booking [10, 20): " + calendar.book(10, 20));  // true
        System.out.println("Booking [50, 60): " + calendar.book(50, 60));  // true
        System.out.println("Booking [10, 40): " + calendar.book(10, 40));  // true (creates double booking [10, 20))
        System.out.println("Booking [5, 15): " + calendar.book(5, 15));    // false (would create triple booking)
        System.out.println("Booking [5, 10): " + calendar.book(5, 10));    // true (doesn't overlap with double booking)
        System.out.println("Booking [25, 55): " + calendar.book(25, 55));  // true

        System.out.println("\nUsing Two Lists Implementation:");
        TwoListsImplementation twoListsCalendar = new TwoListsImplementation();

        System.out.println("Booking [10, 20): " + twoListsCalendar.book(10, 20));  // true
        System.out.println("Booking [50, 60): " + twoListsCalendar.book(50, 60));  // true
        System.out.println("Booking [10, 40): " + twoListsCalendar.book(10, 40));  // true (creates double booking [10, 20))
        System.out.println("Booking [5, 15): " + twoListsCalendar.book(5, 15));    // false (would create triple booking)
        System.out.println("Booking [5, 10): " + twoListsCalendar.book(5, 10));    // true (doesn't overlap with double booking)
        System.out.println("Booking [25, 55): " + twoListsCalendar.book(25, 55));  // true
    }

    /**
     * Attempts to book a new interval
     *
     * @param start Start time (inclusive)
     * @param end   End time (exclusive)
     * @return true if booking is successful, false if it would create a triple booking
     */
    public boolean book(int start, int end) {
        // Check if adding this booking would cause a triple booking
        // Current max booking count must be less than 2
        if (segmentTree.query(start, end - 1) >= 2) {
            return false;
        }

        // No triple booking would be created, add the booking
        segmentTree.rangeAdd(start, end - 1, 1);
        return true;
    }

    /**
     * Alternative implementation using two lists approach
     */
    public static class TwoListsImplementation {
        // List of all single bookings
        private List<int[]> bookings;
        // List of overlapping intervals (double bookings)
        private List<int[]> overlaps;

        public TwoListsImplementation() {
            bookings = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            // Check if new booking overlaps with any existing double booking
            for (int[] overlap : overlaps) {
                if (start < overlap[1] && end > overlap[0]) {
                    // Would create triple booking
                    return false;
                }
            }

            // Check for overlaps with existing single bookings
            for (int[] booking : bookings) {
                if (start < booking[1] && end > booking[0]) {
                    // Create a new overlap interval
                    overlaps.add(new int[]{
                            Math.max(start, booking[0]),
                            Math.min(end, booking[1])
                    });
                }
            }

            // Add the new booking
            bookings.add(new int[]{start, end});
            return true;
        }
    }
}