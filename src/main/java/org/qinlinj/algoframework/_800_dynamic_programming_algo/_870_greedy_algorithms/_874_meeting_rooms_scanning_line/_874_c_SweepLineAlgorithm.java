package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

import java.util.*;

/**
 * MEETING ROOMS PROBLEM - SWEEP LINE ALGORITHM IMPLEMENTATION
 * LeetCode 253: Meeting Rooms II
 * <p>
 * Sweep Line Algorithm Concept:
 * Imagine a vertical line sweeping across the timeline from left to right.
 * The line carries a counter that tracks concurrent meetings:
 * - When sweep line hits a meeting START: counter += 1
 * - When sweep line hits a meeting END: counter -= 1
 * - Maximum counter value = minimum meeting rooms needed
 * <p>
 * Key Innovation - Two Pointer Technique:
 * Instead of creating explicit events, we:
 * 1. Extract start times and end times into separate arrays
 * 2. Sort both arrays independently
 * 3. Use two pointers to simulate sweep line movement
 * 4. Compare current start vs current end to determine next event
 * <p>
 * Algorithm Steps:
 * 1. Separate start times and end times from meetings
 * 2. Sort start[] and end[] arrays independently
 * 3. Initialize two pointers i=0, j=0 and counter=0
 * 4. While both pointers are valid:
 * - If start[i] < end[j]: meeting starts, counter++, i++
 * - Else: meeting ends, counter--, j++
 * - Track maximum counter value
 * 5. Return maximum counter value
 * <p>
 * Why This Works:
 * - Sorting preserves chronological order of events
 * - Two pointers efficiently merge sorted sequences
 * - Counter accurately tracks concurrent meetings at each moment
 * - No need to store actual time values or create event objects
 * <p>
 * Edge Case Handling:
 * - Empty input: return 0
 * - Single meeting: return 1
 * - Simultaneous start/end: end events processed first (start[i] >= end[j])
 * <p>
 * Time Complexity: O(n log n) for sorting + O(n) for processing = O(n log n)
 * Space Complexity: O(n) for start/end arrays
 * <p>
 * This elegant algorithm transforms a complex scheduling problem into
 * a simple event-processing problem using fundamental CS techniques.
 */

public class _874_c_SweepLineAlgorithm {
    /**
     * Core sweep line algorithm implementation
     */
    public static class SweepLineCore {

        /**
         * Main algorithm: Two-pointer sweep line approach
         */
        public static int minMeetingRooms(int[][] meetings) {
            if (meetings.length == 0) return 0;

            int n = meetings.length;
            int[] start = new int[n];
            int[] end = new int[n];

            // Extract start and end times
            for (int i = 0; i < n; i++) {
                start[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }

            // Sort both arrays
            Arrays.sort(start);
            Arrays.sort(end);

            // Two-pointer sweep line simulation
            int maxRooms = 0;
            int currentRooms = 0;
            int i = 0, j = 0;

            while (i < n && j < n) {
                if (start[i] < end[j]) {
                    // Meeting starts: need another room
                    currentRooms++;
                    i++;
                } else {
                    // Meeting ends: free up a room
                    currentRooms--;
                    j++;
                }

                // Track maximum concurrent meetings
                maxRooms = Math.max(maxRooms, currentRooms);
            }

            return maxRooms;
        }

        /**
         * Detailed implementation with step-by-step explanation
         */
        public static int minMeetingRoomsDetailed(int[][] meetings) {
            if (meetings.length == 0) return 0;

            System.out.println("=== Sweep Line Algorithm - Detailed Execution ===");
            System.out.println("Input meetings: " + Arrays.deepToString(meetings));

            int n = meetings.length;
            int[] start = new int[n];
            int[] end = new int[n];

            // Extract and display start/end times
            for (int i = 0; i < n; i++) {
                start[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }

            System.out.println("Start times: " + Arrays.toString(start));
            System.out.println("End times: " + Arrays.toString(end));

            // Sort arrays
            Arrays.sort(start);
            Arrays.sort(end);

            System.out.println("Sorted start times: " + Arrays.toString(start));
            System.out.println("Sorted end times: " + Arrays.toString(end));
            System.out.println();

            // Sweep line simulation
            int maxRooms = 0;
            int currentRooms = 0;
            int i = 0, j = 0;

            System.out.println("Sweep line simulation:");
            System.out.println("Step | Next Start | Next End | Event | Rooms | Max");
            System.out.println("-----|------------|----------|-------|-------|----");

            int step = 1;
            while (i < n && j < n) {
                String event, nextStart = (i < n) ? String.valueOf(start[i]) : "N/A";
                String nextEnd = (j < n) ? String.valueOf(end[j]) : "N/A";

                if (start[i] < end[j]) {
                    // Meeting starts
                    currentRooms++;
                    event = "START";
                    i++;
                } else {
                    // Meeting ends (or starts at same time as end)
                    currentRooms--;
                    event = "END";
                    j++;
                }

                maxRooms = Math.max(maxRooms, currentRooms);

                System.out.printf("%4d |     %4s   |    %4s  | %5s | %5d | %3d%n",
                        step++, nextStart, nextEnd, event, currentRooms, maxRooms);
            }

            System.out.println();
            System.out.println("Minimum meeting rooms needed: " + maxRooms);
            return maxRooms;
        }

        /**
         * Alternative implementation using explicit events
         */
        public static int minMeetingRoomsWithEvents(int[][] meetings) {
            if (meetings.length == 0) return 0;

            System.out.println("\n=== Alternative: Explicit Event Processing ===");

            // Create events
            List<int[]> events = new ArrayList<>();
            for (int[] meeting : meetings) {
                events.add(new int[]{meeting[0], 1});   // start: +1
                events.add(new int[]{meeting[1], -1});  // end: -1
            }

            // Sort events (end events before start events at same time)
            events.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            });

            System.out.println("Events (time, change):");
            for (int[] event : events) {
                String type = event[1] == 1 ? "START" : "END";
                System.out.printf("Time %d: %s (%+d)%n", event[0], type, event[1]);
            }

            // Process events
            int maxRooms = 0;
            int currentRooms = 0;

            System.out.println("\nProcessing events:");
            for (int[] event : events) {
                currentRooms += event[1];
                maxRooms = Math.max(maxRooms, currentRooms);

                String type = event[1] == 1 ? "START" : "END";
                System.out.printf("Time %d (%s): %d rooms needed%n",
                        event[0], type, currentRooms);
            }

            System.out.println("Maximum rooms: " + maxRooms);
            return maxRooms;
        }
    }
}
