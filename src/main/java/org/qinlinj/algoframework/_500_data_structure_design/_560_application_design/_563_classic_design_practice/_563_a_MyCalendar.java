package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 729: My Calendar I
 * <p>
 * This problem involves designing a calendar system that allows scheduling events
 * without overlapping time slots.
 * <p>
 * Problem Description:
 * - Implement a calendar class that can add events with start and end times
 * - Events are represented as half-open intervals [start, end) where start <= x < end
 * - A new event can only be added if it doesn't overlap with existing events
 * - Return true if an event can be added without overlap, false otherwise
 * <p>
 * Key Design Considerations:
 * 1. We need an efficient way to check if a new interval overlaps with existing intervals
 * 2. We need to store the scheduled events in an organized manner
 * <p>
 * Solution Approach:
 * - Use a TreeMap to store events (start time -> end time)
 * - For a new event [start, end), we need to check if there's any overlap with existing events
 * - Two intervals [s1, e1) and [s2, e2) overlap if and only if: s1 < e2 && s2 < e1
 * - We can use floorKey and ceilingKey methods to efficiently find potential overlaps
 * <p>
 * Time Complexity:
 * - book(): O(log n) for TreeMap operations
 * <p>
 * Space Complexity:
 * - O(n) where n is the number of scheduled events
 */

import java.util.*;

public class _563_a_MyCalendar {

    public static void main(String[] args) {
        // Example usage
        MyCalendar calendar = new MyCalendar();

        System.out.println(calendar.book(10, 20)); // true
        System.out.println(calendar.book(15, 25)); // false - overlaps with [10,20)
        System.out.println(calendar.book(20, 30)); // true - starts exactly when first event ends

        // Additional test cases
        System.out.println(calendar.book(5, 10));  // true - ends exactly when first event starts
        System.out.println(calendar.book(5, 15));  // false - overlaps with [5,10) and [10,20)
    }

    static class MyCalendar {
        // TreeMap to store events: key is start time, value is end time
        private TreeMap<Integer, Integer> calendar;

        public MyCalendar() {
            calendar = new TreeMap<>();
        }

        /**
         * Attempts to book a new event
         * @param start Start time of the event (inclusive)
         * @param end End time of the event (exclusive)
         * @return true if the event can be booked without overlap, false otherwise
         */
        public boolean book(int start, int end) {
            // Find the event with the greatest start time <= start
            Integer earlier = calendar.floorKey(start);

            // Find the event with the smallest start time >= start
            Integer later = calendar.ceilingKey(start);

            // Check if the new event overlaps with the event starting after it
            if (later != null && end > later) {
                // The new event ends after the next event starts
                return false;
            }

            // Check if the new event overlaps with the event starting before it
            if (earlier != null && start < calendar.get(earlier)) {
                // The new event starts before the previous event ends
                return false;
            }

            // No overlap found, book the event
            calendar.put(start, end);
            return true;
        }
    }
}