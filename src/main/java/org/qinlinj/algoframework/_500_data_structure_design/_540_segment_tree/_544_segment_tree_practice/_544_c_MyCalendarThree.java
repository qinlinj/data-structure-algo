package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._544_segment_tree_practice; /**
 * LeetCode 732: My Calendar III
 * <p>
 * Problem Description:
 * Implement a calendar system that keeps track of the maximum number of overlapping bookings.
 * For each booking, return the maximum k-booking that exists, where a k-booking means
 * k events have some non-empty intersection.
 * <p>
 * Key Concepts:
 * 1. Counting Overlapping Intervals - Determining the maximum number of concurrent events
 * 2. Line Sweep Algorithm - A technique for processing intervals by sorting their endpoints
 * 3. Segment Tree with Lazy Propagation - Efficiently tracking the maximum number of
 * overlapping bookings across all intervals
 * <p>
 * Solution Approaches:
 * 1. Segment Tree: O(log N) time for both updates and queries
 * - Create a segment tree that tracks the maximum number of bookings at any time
 * - For each new booking, increment the count for that interval
 * - Return the maximum count across the entire range
 * <p>
 * 2. Boundary Count Map: O(N log N) time complexity
 * - Use a TreeMap to track the "change in count" at each time boundary
 * - +1 for a start time, -1 for an end time
 * - Iterate through the sorted map to find the maximum "running count"
 * <p>
 * The segment tree approach is more efficient for large numbers of queries on the same
 * calendar instance, while the boundary count approach is more space-efficient.
 */

import java.util.*;
import org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._543_lazy_propagation_segment_tree._543_c_AllInOneSegmentTree;

public class _544_c_MyCalendarThree {
    // Using Segment Tree approach
    private final _543_c_AllInOneSegmentTree segmentTree;

    public _544_c_MyCalendarThree() {
        // Create a max segment tree with a range of 0 to 10^9
        segmentTree = new _543_c_AllInOneSegmentTree(0, 1000000000, 0, "max");
    }

    public static void main(String[] args) {
        _544_c_MyCalendarThree calendar = new _544_c_MyCalendarThree();

        System.out.println("Booking [10, 20): " + calendar.book(10, 20));  // 1
        System.out.println("Booking [50, 60): " + calendar.book(50, 60));  // 1
        System.out.println("Booking [10, 40): " + calendar.book(10, 40));  // 2 (overlaps with [10, 20))
        System.out.println("Booking [5, 15): " + calendar.book(5, 15));    // 3 (overlaps with [10, 20) and [10, 40))
        System.out.println("Booking [5, 10): " + calendar.book(5, 10));    // 3
        System.out.println("Booking [25, 55): " + calendar.book(25, 55));  // 3 (overlaps with [10, 40) and [50, 60))

        System.out.println("\nUsing Boundary Count Implementation:");
        BoundaryCountImplementation boundaryCalendar = new BoundaryCountImplementation();

        System.out.println("Booking [10, 20): " + boundaryCalendar.book(10, 20));  // 1
        System.out.println("Booking [50, 60): " + boundaryCalendar.book(50, 60));  // 1
        System.out.println("Booking [10, 40): " + boundaryCalendar.book(10, 40));  // 2
        System.out.println("Booking [5, 15): " + boundaryCalendar.book(5, 15));    // 3
        System.out.println("Booking [5, 10): " + boundaryCalendar.book(5, 10));    // 3
        System.out.println("Booking [25, 55): " + boundaryCalendar.book(25, 55));  // 3
    }

    /**
     * Books a new interval and returns the maximum k-booking
     *
     * @param start Start time (inclusive)
     * @param end   End time (exclusive)
     * @return The maximum number of overlapping bookings (k)
     */
    public int book(int start, int end) {
        // Increment the count for the booking interval [start, end)
        segmentTree.rangeAdd(start, end - 1, 1);

        // Return the maximum number of overlaps across the entire range
        return segmentTree.query(0, 1000000000);
    }

    /**
     * Alternative implementation using boundary count approach
     */
    public static class BoundaryCountImplementation {
        // TreeMap to track count changes at each boundary
        // Key: time point, Value: change in count (+1 for start, -1 for end)
        private TreeMap<Integer, Integer> timeline;

        public BoundaryCountImplementation() {
            timeline = new TreeMap<>();
        }

        public int book(int start, int end) {
            // Increment count at start time
            timeline.put(start, timeline.getOrDefault(start, 0) + 1);

            // Decrement count at end time
            timeline.put(end, timeline.getOrDefault(end, 0) - 1);

            // Calculate the maximum overlap by sweeping through the timeline
            int activeBookings = 0;
            int maxBookings = 0;

            for (int delta : timeline.values()) {
                activeBookings += delta;
                maxBookings = Math.max(maxBookings, activeBookings);
            }

            return maxBookings;
        }
    }
}