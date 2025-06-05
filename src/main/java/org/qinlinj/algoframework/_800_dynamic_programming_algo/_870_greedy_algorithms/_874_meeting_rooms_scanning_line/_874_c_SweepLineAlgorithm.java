package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

import java.util.*;

public class _874_c_SweepLineAlgorithm {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               SWEEP LINE ALGORITHM                          ║");
        System.out.println("║          Elegant Solution for Meeting Rooms                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Main example from the tutorial
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};

        // Basic algorithm demonstration
        int result = SweepLineCore.minMeetingRoomsDetailed(meetings);

        // Alternative implementation
        SweepLineCore.minMeetingRoomsWithEvents(meetings);

        // Visualization
        SweepLineVisualization.visualizeSweepLine(meetings);
        SweepLineVisualization.stepThroughAlgorithm(meetings);

        // Edge cases and verification
        EdgeCasesAndVerification.testEdgeCases();
        EdgeCasesAndVerification.verifyCorrectness(meetings);

        System.out.println("\n=== Algorithm Summary ===");
        System.out.println("✅ SWEEP LINE ALGORITHM ADVANTAGES:");
        System.out.println("1. Optimal time complexity: O(n log n)");
        System.out.println("2. Optimal space complexity: O(n)");
        System.out.println("3. Independent of time range values");
        System.out.println("4. Elegant and easy to understand");
        System.out.println("5. Generalizable to many interval problems");
        System.out.println();

        System.out.println("🔧 KEY TECHNIQUES:");
        System.out.println("1. Event abstraction: start/end as discrete events");
        System.out.println("2. Two-pointer technique: efficient event processing");
        System.out.println("3. Counter tracking: monitor concurrent resource usage");
        System.out.println("4. Maximum tracking: find peak resource demand");
        System.out.println();

        System.out.println("🎯 PROBLEM-SOLVING PATTERN:");
        System.out.println("1. Identify that we need to track overlapping intervals");
        System.out.println("2. Abstract intervals into start/end events");
        System.out.println("3. Sort events chronologically");
        System.out.println("4. Process events with running counter");
        System.out.println("5. Track maximum counter value");
        System.out.println();

        System.out.println("This pattern applies to many resource allocation problems!");
        System.out.println("Next: We'll explore extensions and variations! 🚀");
    }

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

    /**
     * Visualization and intuition building
     */
    public static class SweepLineVisualization {

        /**
         * Visualize the sweep line process
         */
        public static void visualizeSweepLine(int[][] meetings) {
            System.out.println("\n=== Sweep Line Visualization ===");

            if (meetings.length == 0) return;

            // Find time range for visualization
            int minTime = Arrays.stream(meetings).mapToInt(m -> m[0]).min().orElse(0);
            int maxTime = Arrays.stream(meetings).mapToInt(m -> m[1]).max().orElse(0);

            System.out.println("Meetings on timeline:");

            // Print timeline header
            System.out.print("Time:    ");
            for (int t = minTime; t <= maxTime; t++) {
                System.out.printf("%2d ", t);
            }
            System.out.println();

            // Print each meeting
            for (int i = 0; i < meetings.length; i++) {
                System.out.printf("Meet %d:  ", i);
                for (int t = minTime; t <= maxTime; t++) {
                    if (t >= meetings[i][0] && t < meetings[i][1]) {
                        System.out.print(" * ");
                    } else if (t == meetings[i][0]) {
                        System.out.print(" S ");
                    } else if (t == meetings[i][1]) {
                        System.out.print(" E ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.printf(" [%d,%d)%n", meetings[i][0], meetings[i][1]);
            }

            // Show concurrent count
            System.out.print("Count:   ");
            for (int t = minTime; t <= maxTime; t++) {
                int count = 0;
                for (int[] meeting : meetings) {
                    if (t >= meeting[0] && t < meeting[1]) {
                        count++;
                    }
                }
                System.out.printf("%2d ", count);
            }
            System.out.println();

            // Show sweep line movement
            int[] start = Arrays.stream(meetings).mapToInt(m -> m[0]).sorted().toArray();
            int[] end = Arrays.stream(meetings).mapToInt(m -> m[1]).sorted().toArray();

            System.out.println("\nSweep line events:");
            System.out.println("Start events: " + Arrays.toString(start));
            System.out.println("End events: " + Arrays.toString(end));
        }

        /**
         * Interactive step-through of algorithm
         */
        public static void stepThroughAlgorithm(int[][] meetings) {
            System.out.println("\n=== Interactive Algorithm Walkthrough ===");

            if (meetings.length == 0) {
                System.out.println("No meetings - 0 rooms needed");
                return;
            }

            int n = meetings.length;
            int[] start = new int[n];
            int[] end = new int[n];

            for (int i = 0; i < n; i++) {
                start[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }

            Arrays.sort(start);
            Arrays.sort(end);

            System.out.println("Initial state:");
            System.out.println("Start array: " + Arrays.toString(start));
            System.out.println("End array: " + Arrays.toString(end));
            System.out.println("Pointers: i=0, j=0");
            System.out.println("Rooms: 0, Max: 0");
            System.out.println();

            int maxRooms = 0;
            int currentRooms = 0;
            int i = 0, j = 0;

            while (i < n && j < n) {
                System.out.printf("Step %d:%n", i + j + 1);
                System.out.printf("  Compare: start[%d]=%d vs end[%d]=%d%n",
                        i, start[i], j, end[j]);

                if (start[i] < end[j]) {
                    currentRooms++;
                    System.out.printf("  start[%d] < end[%d] → Meeting starts%n", i, j);
                    System.out.printf("  Rooms: %d → %d, i: %d → %d%n",
                            currentRooms - 1, currentRooms, i, i + 1);
                    i++;
                } else {
                    currentRooms--;
                    System.out.printf("  start[%d] >= end[%d] → Meeting ends%n", i, j);
                    System.out.printf("  Rooms: %d → %d, j: %d → %d%n",
                            currentRooms + 1, currentRooms, j, j + 1);
                    j++;
                }

                maxRooms = Math.max(maxRooms, currentRooms);
                System.out.printf("  Current rooms: %d, Max so far: %d%n",
                        currentRooms, maxRooms);
                System.out.println();
            }

            System.out.println("Final result: " + maxRooms + " rooms needed");
        }
    }

    /**
     * Edge cases and correctness verification
     */
    public static class EdgeCasesAndVerification {

        /**
         * Test various edge cases
         */
        public static void testEdgeCases() {
            System.out.println("\n=== Edge Cases Testing ===");

            // Case 1: Empty input
            System.out.println("Case 1: Empty meetings");
            int result1 = SweepLineCore.minMeetingRooms(new int[][]{});
            System.out.println("Result: " + result1 + " (expected: 0)");
            System.out.println();

            // Case 2: Single meeting
            System.out.println("Case 2: Single meeting");
            int[][] single = {{0, 1}};
            int result2 = SweepLineCore.minMeetingRooms(single);
            System.out.println("Meetings: " + Arrays.deepToString(single));
            System.out.println("Result: " + result2 + " (expected: 1)");
            System.out.println();

            // Case 3: No overlaps
            System.out.println("Case 3: No overlapping meetings");
            int[][] noOverlap = {{1, 2}, {3, 4}, {5, 6}};
            int result3 = SweepLineCore.minMeetingRooms(noOverlap);
            System.out.println("Meetings: " + Arrays.deepToString(noOverlap));
            System.out.println("Result: " + result3 + " (expected: 1)");
            System.out.println();

            // Case 4: All overlapping
            System.out.println("Case 4: All meetings overlap");
            int[][] allOverlap = {{1, 5}, {2, 6}, {3, 7}};
            int result4 = SweepLineCore.minMeetingRooms(allOverlap);
            System.out.println("Meetings: " + Arrays.deepToString(allOverlap));
            System.out.println("Result: " + result4 + " (expected: 3)");
            System.out.println();

            // Case 5: Touching boundaries
            System.out.println("Case 5: Meetings with touching boundaries");
            int[][] touching = {{1, 3}, {3, 5}, {5, 7}};
            int result5 = SweepLineCore.minMeetingRooms(touching);
            System.out.println("Meetings: " + Arrays.deepToString(touching));
            System.out.println("Result: " + result5 + " (expected: 1)");
            System.out.println();

            // Case 6: Same start/end times
            System.out.println("Case 6: Identical meeting times");
            int[][] identical = {{1, 3}, {1, 3}, {1, 3}};
            int result6 = SweepLineCore.minMeetingRooms(identical);
            System.out.println("Meetings: " + Arrays.deepToString(identical));
            System.out.println("Result: " + result6 + " (expected: 3)");
        }

        /**
         * Verify correctness against brute force
         */
        public static void verifyCorrectness(int[][] meetings) {
            System.out.println("\n=== Correctness Verification ===");

            // Brute force: check every time point
            int bruteForceResult = bruteForceMaxOverlap(meetings);

            // Sweep line result
            int sweepLineResult = SweepLineCore.minMeetingRooms(meetings);

            System.out.println("Meetings: " + Arrays.deepToString(meetings));
            System.out.println("Brute force result: " + bruteForceResult);
            System.out.println("Sweep line result: " + sweepLineResult);
            System.out.println("Results match: " + (bruteForceResult == sweepLineResult));
        }

        /**
         * Brute force solution for verification
         */
        private static int bruteForceMaxOverlap(int[][] meetings) {
            if (meetings.length == 0) return 0;

            // Find all unique time points
            Set<Integer> timePoints = new TreeSet<>();
            for (int[] meeting : meetings) {
                timePoints.add(meeting[0]);
                timePoints.add(meeting[1]);
            }

            int maxOverlap = 0;

            // Check overlap at each time point
            for (int time : timePoints) {
                int overlap = 0;
                for (int[] meeting : meetings) {
                    if (time >= meeting[0] && time < meeting[1]) {
                        overlap++;
                    }
                }
                maxOverlap = Math.max(maxOverlap, overlap);
            }

            return maxOverlap;
        }
    }
}