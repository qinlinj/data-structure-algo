package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

/**
 * GREEDY ALGORITHM APPLICATIONS - INTERVAL PROBLEMS
 * <p>
 * This class demonstrates three related interval problems that showcase
 * how the core interval scheduling algorithm can be adapted for different scenarios:
 * <p>
 * 1. NON-OVERLAPPING INTERVALS (LeetCode 435):
 * - Problem: Remove minimum intervals to make all remaining non-overlapping
 * - Insight: Total intervals - Maximum non-overlapping intervals
 * - Greedy choice: Same as interval scheduling (earliest end time)
 * <p>
 * 2. MINIMUM ARROWS TO BURST BALLOONS (LeetCode 452):
 * - Problem: Minimum arrows needed to burst all balloon intervals
 * - Insight: Each arrow can burst overlapping balloons at intersection
 * - Greedy choice: Shoot at earliest end point of overlapping group
 * - Key difference: Touching boundaries count as overlapping
 * <p>
 * 3. MEETING ROOMS VARIATIONS:
 * - Problem: Various meeting room scheduling scenarios
 * - Applications: Resource allocation, task scheduling, time management
 * <p>
 * Algorithm Adaptations:
 * - Core strategy remains: sort by end time, make greedy choices
 * - Modifications: Adjust overlap definition, counting mechanism
 * - Complexity: All maintain O(n log n) time due to sorting
 * <p>
 * Key Learning Points:
 * 1. Same underlying greedy principle applies to multiple problems
 * 2. Small modifications adapt algorithm to different requirements
 * 3. Understanding core algorithm enables solving related problems
 * 4. Greedy choice property transfers across similar problem structures
 * <p>
 * Real-world Applications:
 * - Project management: Task scheduling with dependencies
 * - Resource allocation: Equipment, rooms, personnel scheduling
 * - Entertainment: TV program scheduling, event planning
 * - Manufacturing: Production line optimization, machine scheduling
 */

import java.util.*;

public class _873_c_GreedyApplicationsIntervals {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           GREEDY ALGORITHM APPLICATIONS                     ║");
        System.out.println("║              Interval-Based Problems                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Test Problem 1: Non-overlapping Intervals
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        NonOverlappingIntervals.eraseOverlapIntervalsDetailed(intervals1);

        // Test Problem 2: Minimum Arrow Shots
        int[][] balloons = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        MinimumArrowShots.findMinArrowShots(balloons);
        MinimumArrowShots.demonstrateDifference();

        // Test Problem 3: Meeting Rooms
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
        MeetingRoomProblems.canAttendMeetings(meetings);
        MeetingRoomProblems.minMeetingRooms(meetings);
        MeetingRoomProblems.maxMeetingsInOneRoom(meetings);

        // Show real-world applications
        RealWorldApplications.demonstrateApplications();
        RealWorldApplications.algorithmExtensions();

        System.out.println("\n=== Summary of Key Insights ===");
        System.out.println("1. CORE ALGORITHM: Sort by end time, greedily select non-overlapping");
        System.out.println("2. ADAPTATIONS: Modify overlap definition and counting mechanism");
        System.out.println("3. APPLICATIONS: Wide variety of scheduling and resource allocation problems");
        System.out.println("4. COMPLEXITY: All variants maintain O(n log n) time complexity");
        System.out.println("5. EXTENSIBILITY: Basic algorithm serves as foundation for advanced problems");

        System.out.println("\n🎯 MASTERY CHECKLIST:");
        System.out.println("□ Understand core interval scheduling algorithm");
        System.out.println("□ Can adapt algorithm for different overlap definitions");
        System.out.println("□ Recognize interval problems in real-world scenarios");
        System.out.println("□ Can modify algorithm for counting vs selection problems");
        System.out.println("□ Understand when greedy choice property applies to intervals");
    }

    /**
     * Problem 1: Non-overlapping Intervals (LeetCode 435)
     * Remove minimum number of intervals to make remaining intervals non-overlapping
     */
    public static class NonOverlappingIntervals {

        /**
         * Core interval scheduling algorithm (from previous class)
         */
        private static int intervalSchedule(int[][] intervals) {
            if (intervals.length == 0) return 0;

            // Sort by end time
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

            int count = 1;
            int lastEnd = intervals[0][1];

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= lastEnd) {
                    count++;
                    lastEnd = intervals[i][1];
                }
            }

            return count;
        }

        /**
         * Solution: Total intervals - Maximum non-overlapping intervals
         */
        public static int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length <= 1) return 0;

            System.out.println("=== Non-overlapping Intervals Problem ===");
            System.out.println("Input intervals: " + Arrays.deepToString(intervals));

            int maxNonOverlapping = intervalSchedule(intervals);
            int toRemove = intervals.length - maxNonOverlapping;

            System.out.println("Maximum non-overlapping intervals: " + maxNonOverlapping);
            System.out.println("Intervals to remove: " + toRemove);

            return toRemove;
        }

        /**
         * Detailed explanation with step-by-step process
         */
        public static int eraseOverlapIntervalsDetailed(int[][] intervals) {
            if (intervals.length <= 1) return 0;

            System.out.println("\n=== Detailed Non-overlapping Solution ===");
            System.out.println("Strategy: Keep maximum non-overlapping, remove the rest");

            // Sort by end time
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

            System.out.println("Sorted by end time: " + Arrays.deepToString(intervals));

            int kept = 1;
            int removed = 0;
            int lastEnd = intervals[0][1];

            System.out.printf("Keep interval 0: [%d, %d]%n", intervals[0][0], intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                int start = intervals[i][0];
                int end = intervals[i][1];

                if (start >= lastEnd) {
                    // Non-overlapping, keep it
                    kept++;
                    lastEnd = end;
                    System.out.printf("Keep interval %d: [%d, %d] (non-overlapping)%n", i, start, end);
                } else {
                    // Overlapping, remove it
                    removed++;
                    System.out.printf("Remove interval %d: [%d, %d] (overlaps)%n", i, start, end);
                }
            }

            System.out.println("Total kept: " + kept + ", Total removed: " + removed);
            return removed;
        }
    }

    /**
     * Problem 2: Minimum Number of Arrows to Burst Balloons (LeetCode 452)
     * Find minimum arrows needed to burst all balloon intervals
     */
    public static class MinimumArrowShots {

        /**
         * Key insight: Each arrow bursts all overlapping balloons
         * Touching boundaries count as overlapping for arrows
         */
        public static int findMinArrowShots(int[][] points) {
            if (points.length == 0) return 0;

            System.out.println("\n=== Minimum Arrow Shots Problem ===");
            System.out.println("Balloon intervals: " + Arrays.deepToString(points));

            // Sort by end position
            Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
            System.out.println("Sorted by end position: " + Arrays.deepToString(points));

            int arrows = 1;
            int arrowPosition = points[0][1]; // Shoot at end of first balloon

            System.out.printf("Arrow 1 at position %d, bursts balloon [%d, %d]%n",
                    arrowPosition, points[0][0], points[0][1]);

            for (int i = 1; i < points.length; i++) {
                int start = points[i][0];
                int end = points[i][1];

                // Key difference: use > instead of >= (touching boundaries overlap)
                if (start > arrowPosition) {
                    // Need new arrow
                    arrows++;
                    arrowPosition = end;
                    System.out.printf("Arrow %d at position %d, bursts balloon [%d, %d]%n",
                            arrows, arrowPosition, start, end);
                } else {
                    // Current arrow can burst this balloon too
                    System.out.printf("Arrow %d also bursts balloon [%d, %d]%n",
                            arrows, start, end);
                }
            }

            System.out.println("Total arrows needed: " + arrows);
            return arrows;
        }

        /**
         * Demonstrate the difference between interval scheduling and arrow shooting
         */
        public static void demonstrateDifference() {
            System.out.println("\n=== Key Difference: Boundary Handling ===");

            int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
            System.out.println("Test intervals: " + Arrays.deepToString(intervals));

            // For interval scheduling: touching boundaries don't overlap
            System.out.println("\nInterval Scheduling (boundaries don't overlap):");
            System.out.println("Condition: start >= lastEnd");
            System.out.println("Result: All 4 intervals can be selected");

            // For arrow shooting: touching boundaries do overlap
            System.out.println("\nArrow Shooting (boundaries do overlap):");
            System.out.println("Condition: start > arrowPosition");
            System.out.println("Result: Need 4 arrows (each boundary touch requires new arrow)");

            int arrows = findMinArrowShots(intervals);
            System.out.println("Arrows needed: " + arrows);
        }
    }

    /**
     * Problem 3: Meeting Rooms Variations
     * Various meeting room scheduling problems
     */
    public static class MeetingRoomProblems {

        /**
         * Meeting Rooms I: Can attend all meetings?
         */
        public static boolean canAttendMeetings(int[][] intervals) {
            if (intervals.length <= 1) return true;

            System.out.println("\n=== Meeting Rooms I: Can Attend All? ===");
            System.out.println("Meetings: " + Arrays.deepToString(intervals));

            // Sort by start time
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < intervals[i - 1][1]) {
                    System.out.printf("Conflict: Meeting [%d, %d] overlaps with [%d, %d]%n",
                            intervals[i][0], intervals[i][1],
                            intervals[i - 1][0], intervals[i - 1][1]);
                    return false;
                }
            }

            System.out.println("No conflicts - can attend all meetings");
            return true;
        }

        /**
         * Meeting Rooms II: Minimum meeting rooms needed
         */
        public static int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) return 0;

            System.out.println("\n=== Meeting Rooms II: Minimum Rooms Needed ===");
            System.out.println("Meetings: " + Arrays.deepToString(intervals));

            // Create events for start and end times
            List<int[]> events = new ArrayList<>();
            for (int[] interval : intervals) {
                events.add(new int[]{interval[0], 1});  // start: +1 room
                events.add(new int[]{interval[1], -1}); // end: -1 room
            }

            // Sort events by time, end events before start events at same time
            events.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            });

            int currentRooms = 0;
            int maxRooms = 0;

            System.out.println("Event processing:");
            for (int[] event : events) {
                currentRooms += event[1];
                maxRooms = Math.max(maxRooms, currentRooms);
                String eventType = event[1] == 1 ? "start" : "end";
                System.out.printf("Time %d: %s event, current rooms: %d%n",
                        event[0], eventType, currentRooms);
            }

            System.out.println("Maximum rooms needed: " + maxRooms);
            return maxRooms;
        }

        /**
         * Maximum meetings in one room (back to interval scheduling)
         */
        public static int maxMeetingsInOneRoom(int[][] intervals) {
            System.out.println("\n=== Maximum Meetings in One Room ===");
            System.out.println("This is exactly the interval scheduling problem!");

            if (intervals.length == 0) return 0;

            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

            int count = 1;
            int lastEnd = intervals[0][1];

            System.out.printf("Selected meeting: [%d, %d]%n", intervals[0][0], intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= lastEnd) {
                    count++;
                    lastEnd = intervals[i][1];
                    System.out.printf("Selected meeting: [%d, %d]%n", intervals[i][0], intervals[i][1]);
                }
            }

            System.out.println("Maximum meetings in one room: " + count);
            return count;
        }
    }

    /**
     * Real-world applications and extensions
     */
    public static class RealWorldApplications {

        /**
         * Demonstrate various real-world scenarios
         */
        public static void demonstrateApplications() {
            System.out.println("\n=== Real-World Applications ===");

            System.out.println("1. PROJECT MANAGEMENT:");
            System.out.println("   - Task scheduling with resource constraints");
            System.out.println("   - Critical path optimization");
            System.out.println("   - Milestone planning");

            System.out.println("\n2. ENTERTAINMENT INDUSTRY:");
            System.out.println("   - TV program scheduling");
            System.out.println("   - Concert venue booking");
            System.out.println("   - Movie theater scheduling");

            System.out.println("\n3. TRANSPORTATION:");
            System.out.println("   - Flight gate assignment");
            System.out.println("   - Train platform scheduling");
            System.out.println("   - Delivery route optimization");

            System.out.println("\n4. MANUFACTURING:");
            System.out.println("   - Machine scheduling");
            System.out.println("   - Production line optimization");
            System.out.println("   - Quality control checkpoint scheduling");

            System.out.println("\n5. HEALTHCARE:");
            System.out.println("   - Operating room scheduling");
            System.out.println("   - Equipment allocation");
            System.out.println("   - Staff shift optimization");
        }

        /**
         * Show how to extend the basic algorithm
         */
        public static void algorithmExtensions() {
            System.out.println("\n=== Algorithm Extensions ===");

            System.out.println("BASIC INTERVAL SCHEDULING VARIATIONS:");
            System.out.println("1. Weighted intervals: Each interval has a profit/weight");
            System.out.println("2. Multiple resources: Schedule across multiple rooms/machines");
            System.out.println("3. Preemption allowed: Can interrupt and resume activities");
            System.out.println("4. Setup times: Time needed between different activities");

            System.out.println("\nADVANCED EXTENSIONS:");
            System.out.println("1. Online scheduling: Activities arrive dynamically");
            System.out.println("2. Stochastic scheduling: Uncertain start/end times");
            System.out.println("3. Multi-objective: Optimize multiple criteria simultaneously");
            System.out.println("4. Distributed scheduling: Coordinate across multiple locations");
        }
    }
}