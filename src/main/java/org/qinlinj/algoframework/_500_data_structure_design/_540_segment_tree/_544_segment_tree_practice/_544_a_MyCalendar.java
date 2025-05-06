package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._544_segment_tree_practice; /**
 * LeetCode 729: My Calendar I
 * <p>
 * Problem Description:
 * Implement a calendar system that allows booking time intervals.
 * A booking [start, end) is considered valid if there's no overlap with existing bookings.
 * Time intervals are half-open: [start, end) where start <= x < end.
 * <p>
 * Key Concepts:
 * 1. Interval Overlap Detection - Two intervals [a, b) and [c, d) overlap if and only if
 * max(a, c) < min(b, d). Or more intuitively, they overlap if one interval doesn't
 * completely come before or after the other.
 * 2. Segment Tree Approach - Using a max segment tree to check if any existing booking
 * conflicts with the new booking.
 * 3. TreeMap Approach - Using ordered map to efficiently find potential conflicts.
 * <p>
 * Solution Approaches:
 * 1. Segment Tree: O(log N) time for both queries and updates
 * - Create a segment tree that tracks the maximum number of bookings in any interval
 * - For each new booking, check if the interval already has any bookings
 * - If not, update the segment tree for that interval
 * <p>
 * 2. TreeMap: O(log N) time for both queries and updates
 * - Store intervals in a sorted map using start time as the key
 * - Use floorKey and ceilingKey to efficiently find potential conflicts
 * - Check if the new interval conflicts with adjacent intervals
 */

import java.util.*;
import org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree._543_c_AllInOneSegmentTree;

// Implementation using the TreeMap approach
public class _544_a_MyCalendar {
    // TreeMap to store bookings: key = start time, value = end time
    private TreeMap<Integer, Integer> calendar;

    public _544_a_MyCalendar() {
        calendar = new TreeMap<>();
    }

    public static void main(String[] args) {
        _544_a_MyCalendar myCalendar = new _544_a_MyCalendar();

        System.out.println("Booking [10, 20): " + myCalendar.book(10, 20));  // true
        System.out.println("Booking [15, 25): " + myCalendar.book(15, 25));  // false (conflicts with [10, 20))
        System.out.println("Booking [20, 30): " + myCalendar.book(20, 30));  // true (just touches [10, 20) at 20)

        System.out.println("\nUsing Segment Tree Implementation:");
        SegmentTreeImplementation stCalendar = new SegmentTreeImplementation();

        System.out.println("Booking [10, 20): " + stCalendar.book(10, 20));  // true
        System.out.println("Booking [15, 25): " + stCalendar.book(15, 25));  // false
        System.out.println("Booking [20, 30): " + stCalendar.book(20, 30));  // true
    }

    /**
     * Attempts to book a new interval
     *
     * @param start Start time (inclusive)
     * @param end   End time (exclusive)
     * @return true if booking is successful, false if there's a conflict
     */
    public boolean book(int start, int end) {
        // Find the booking with the largest start time ≤ start
        Integer earlier = calendar.floorKey(start);

        // Find the booking with the smallest start time ≥ start
        Integer later = calendar.ceilingKey(start);

        // Case 1: Check if current booking would overlap with the later booking
        // Overlap occurs if end > later's start time
        if (later != null && end > later) {
            return false;
        }

        // Case 2: Check if current booking would overlap with the earlier booking
        // Overlap occurs if start < earlier's end time
        if (earlier != null && start < calendar.get(earlier)) {
            return false;
        }

        // No conflicts found, add the booking
        calendar.put(start, end);
        return true;
    }

    /**
     * Alternative implementation using segment tree approach
     */
    public static class SegmentTreeImplementation {
        private final _543_c_AllInOneSegmentTree segmentTree;

        public SegmentTreeImplementation() {
            // Create a max segment tree with a range of 0 to 10^9
            // Initial value 0 means no bookings
            segmentTree = new _543_c_AllInOneSegmentTree(0, 1000000000, 0, "max");
        }

        public boolean book(int start, int end) {
            // Check if there are any existing bookings in the interval
            if (segmentTree.query(start, end - 1) > 0) {
                // Segment has a booking, can't add another
                return false;
            }

            // No conflict, add a booking by setting interval to 1
            segmentTree.rangeAdd(start, end - 1, 1);
            return true;
        }
    }
}