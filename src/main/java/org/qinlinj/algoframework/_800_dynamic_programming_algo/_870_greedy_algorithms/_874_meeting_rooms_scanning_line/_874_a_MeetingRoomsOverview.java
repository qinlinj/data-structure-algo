package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

/**
 * MEETING ROOMS PROBLEM OVERVIEW AND INTERVAL PROBLEM CLASSIFICATIONS
 * LeetCode 253: Meeting Rooms II (Premium Problem)
 * <p>
 * Problem Statement:
 * Given an array of meeting time intervals [start, end], find the minimum number
 * of meeting rooms required to accommodate all meetings.
 * <p>
 * Core Problem Essence:
 * Calculate the maximum number of overlapping intervals at any given time.
 * This represents the peak concurrent meetings, which determines minimum rooms needed.
 * <p>
 * Key Insight:
 * Transform the problem from "scheduling meetings" to "counting maximum overlaps"
 * - Each meeting occupies a room from start to end time
 * - Multiple meetings can run simultaneously if they don't overlap
 * - The bottleneck is the moment when most meetings are running concurrently
 * <p>
 * COMPREHENSIVE INTERVAL PROBLEM CLASSIFICATION:
 * <p>
 * 1. Maximum Non-overlapping Selection (Greedy):
 * - Sort by end time, select greedily
 * - Example: Activity selection, single meeting room scheduling
 * <p>
 * 2. Video Segment Merging (Greedy):
 * - Sort by start time, minimize segments to cover target
 * - Example: Video editing, coverage problems
 * <p>
 * 3. Remove Covered Intervals (Sorting):
 * - Sort by start time, identify completely covered intervals
 * - Example: Data compression, redundancy elimination
 * <p>
 * 4. Merge Overlapping Intervals (Sorting):
 * - Sort by start time, merge adjacent overlapping intervals
 * - Example: Calendar consolidation, range merging
 * <p>
 * 5. Interval Intersection (Two Pointers):
 * - Find common time slots between two interval sets
 * - Example: Meeting conflict detection, resource overlap
 * <p>
 * 6. Maximum Room Utilization (Dynamic Programming):
 * - 0-1 Knapsack variant with time constraints
 * - Example: Optimize meeting room usage, job scheduling
 * <p>
 * 7. Minimum Meeting Rooms (Sweep Line):
 * - Current problem - count maximum concurrent intervals
 * - Example: Resource allocation, capacity planning
 * <p>
 * Algorithm Categories:
 * - Greedy: When local optimal choices lead to global optimum
 * - Sorting: When order matters for problem structure
 * - Two Pointers: When comparing or merging sorted sequences
 * - Sweep Line: When tracking events across timeline
 * - Dynamic Programming: When optimal substructure with overlapping subproblems
 */

import java.util.*;

public class _874_a_MeetingRoomsOverview {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                MEETING ROOMS PROBLEM OVERVIEW               ║");
        System.out.println("║            Comprehensive Interval Problem Guide             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Demonstrate different interval problem scenarios
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};

        // Scenario 1: Maximum meetings in one room
        IntervalProblemClassification.maxMeetingsInOneRoom(meetings.clone());

        // Scenario 2: Video segments
        int[][] segments = {{0, 5}, {3, 8}, {7, 12}, {10, 15}};
        int[] target = {0, 15};
        IntervalProblemClassification.minVideoSegments(segments, target);

        // Scenario 3: Remove covered intervals
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
        IntervalProblemClassification.removeCoveredIntervals(intervals);

        // Scenario 4: Merge intervals
        int[][] toMerge = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        IntervalProblemClassification.mergeIntervals(toMerge);

        // Scenario 5: Interval intersection
        int[][] list1 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] list2 = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        IntervalProblemClassification.intervalIntersection(list1, list2);

        // Problem analysis
        ProblemAnalysis.analyzeMeetingRoomsProblem();
        ProblemAnalysis.compareApproaches();
        ProblemAnalysis.demonstrateSweepLineAdvantage();

        System.out.println("\n=== Key Takeaways ===");
        System.out.println("1. Interval problems have recurring patterns and solutions");
        System.out.println("2. Sorting strategy depends on problem requirements");
        System.out.println("3. Meeting rooms problem = maximum overlap counting");
        System.out.println("4. Sweep line algorithm is optimal for time-based problems");
        System.out.println("5. Understanding patterns helps solve related problems quickly");

        System.out.println("\nNext: We'll dive deep into the sweep line algorithm implementation!");
    }

    /**
     * Problem classification and solution approaches
     */
    public static class IntervalProblemClassification {

        /**
         * Scenario 1: Maximum meetings in one room (Activity Selection)
         * Greedy approach: Sort by end time, select non-overlapping
         */
        public static int maxMeetingsInOneRoom(int[][] meetings) {
            if (meetings.length == 0) return 0;

            System.out.println("=== Scenario 1: Maximum Meetings in One Room ===");
            System.out.println("Strategy: Greedy selection by earliest end time");
            System.out.println("Input: " + Arrays.deepToString(meetings));

            // Sort by end time
            Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));

            int count = 1;
            int lastEnd = meetings[0][1];

            System.out.println("Sorted by end time: " + Arrays.deepToString(meetings));
            System.out.printf("Selected: [%d, %d]%n", meetings[0][0], meetings[0][1]);

            for (int i = 1; i < meetings.length; i++) {
                if (meetings[i][0] >= lastEnd) {
                    count++;
                    lastEnd = meetings[i][1];
                    System.out.printf("Selected: [%d, %d]%n", meetings[i][0], meetings[i][1]);
                } else {
                    System.out.printf("Skipped: [%d, %d] (overlaps)%n", meetings[i][0], meetings[i][1]);
                }
            }

            System.out.println("Maximum meetings in one room: " + count);
            return count;
        }

        /**
         * Scenario 2: Video segment coverage (Minimum segments to cover target)
         * Greedy approach: Sort by start time, extend coverage greedily
         */
        public static int minVideoSegments(int[][] segments, int[] target) {
            System.out.println("\n=== Scenario 2: Minimum Video Segments ===");
            System.out.println("Strategy: Greedy coverage extension");
            System.out.printf("Target: [%d, %d]%n", target[0], target[1]);
            System.out.println("Segments: " + Arrays.deepToString(segments));

            // Sort by start time
            Arrays.sort(segments, (a, b) -> Integer.compare(a[0], b[0]));

            int count = 0;
            int currentEnd = target[0];
            int i = 0;

            while (currentEnd < target[1] && i < segments.length) {
                if (segments[i][0] > currentEnd) {
                    System.out.println("Gap found - no solution");
                    return -1;
                }

                int maxEnd = currentEnd;
                // Find segment that extends furthest from current position
                while (i < segments.length && segments[i][0] <= currentEnd) {
                    maxEnd = Math.max(maxEnd, segments[i][1]);
                    i++;
                }

                if (maxEnd == currentEnd) {
                    System.out.println("Cannot extend further - no solution");
                    return -1;
                }

                count++;
                currentEnd = maxEnd;
                System.out.printf("Selected segment extending to: %d%n", currentEnd);
            }

            System.out.println("Minimum segments needed: " + count);
            return currentEnd >= target[1] ? count : -1;
        }

        /**
         * Scenario 3: Remove covered intervals
         * Sort by start time, then by end time (descending)
         */
        public static int removeCoveredIntervals(int[][] intervals) {
            System.out.println("\n=== Scenario 3: Remove Covered Intervals ===");
            System.out.println("Strategy: Sort and identify covered intervals");
            System.out.println("Input: " + Arrays.deepToString(intervals));

            // Sort by start time, then by end time (descending for same start)
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(b[1], a[1]); // Longer intervals first
                }
                return Integer.compare(a[0], b[0]);
            });

            System.out.println("Sorted: " + Arrays.deepToString(intervals));

            int count = 0;
            int prevEnd = 0;

            for (int[] interval : intervals) {
                if (interval[1] > prevEnd) {
                    count++;
                    prevEnd = interval[1];
                    System.out.printf("Keep: [%d, %d]%n", interval[0], interval[1]);
                } else {
                    System.out.printf("Remove: [%d, %d] (covered)%n", interval[0], interval[1]);
                }
            }

            System.out.println("Remaining intervals: " + count);
            return count;
        }

        /**
         * Scenario 4: Merge overlapping intervals
         * Sort by start time, merge adjacent overlapping intervals
         */
        public static int[][] mergeIntervals(int[][] intervals) {
            if (intervals.length <= 1) return intervals;

            System.out.println("\n=== Scenario 4: Merge Overlapping Intervals ===");
            System.out.println("Strategy: Sort by start time and merge");
            System.out.println("Input: " + Arrays.deepToString(intervals));

            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            List<int[]> merged = new ArrayList<>();
            merged.add(intervals[0]);

            System.out.printf("Start with: [%d, %d]%n", intervals[0][0], intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                int[] current = intervals[i];
                int[] last = merged.get(merged.size() - 1);

                if (current[0] <= last[1]) {
                    // Overlapping, merge them
                    last[1] = Math.max(last[1], current[1]);
                    System.out.printf("Merge [%d, %d] with previous -> [%d, %d]%n",
                            current[0], current[1], last[0], last[1]);
                } else {
                    // Non-overlapping, add as new interval
                    merged.add(current);
                    System.out.printf("Add new: [%d, %d]%n", current[0], current[1]);
                }
            }

            int[][] result = merged.toArray(new int[merged.size()][]);
            System.out.println("Merged result: " + Arrays.deepToString(result));
            return result;
        }

        /**
         * Scenario 5: Interval intersection (find common time slots)
         * Two pointer approach on sorted intervals
         */
        public static int[][] intervalIntersection(int[][] list1, int[][] list2) {
            System.out.println("\n=== Scenario 5: Interval Intersection ===");
            System.out.println("Strategy: Two pointers on sorted lists");
            System.out.println("List 1: " + Arrays.deepToString(list1));
            System.out.println("List 2: " + Arrays.deepToString(list2));

            List<int[]> result = new ArrayList<>();
            int i = 0, j = 0;

            while (i < list1.length && j < list2.length) {
                int start = Math.max(list1[i][0], list2[j][0]);
                int end = Math.min(list1[i][1], list2[j][1]);

                if (start <= end) {
                    result.add(new int[]{start, end});
                    System.out.printf("Intersection: [%d, %d]%n", start, end);
                }

                // Move pointer of interval that ends earlier
                if (list1[i][1] < list2[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }

            int[][] intersections = result.toArray(new int[result.size()][]);
            System.out.println("All intersections: " + Arrays.deepToString(intersections));
            return intersections;
        }
    }

    /**
     * Problem analysis and approach comparison
     */
    public static class ProblemAnalysis {

        /**
         * Analyze the core meeting rooms problem
         */
        public static void analyzeMeetingRoomsProblem() {
            System.out.println("\n=== Meeting Rooms Problem Analysis ===");
            System.out.println();

            System.out.println("PROBLEM ESSENCE:");
            System.out.println("- Input: Meeting intervals [start, end]");
            System.out.println("- Output: Minimum meeting rooms needed");
            System.out.println("- Core question: Maximum concurrent meetings at any time?");
            System.out.println();

            System.out.println("KEY INSIGHTS:");
            System.out.println("1. Transform 'scheduling' to 'counting overlaps'");
            System.out.println("2. Peak concurrent meetings = minimum rooms needed");
            System.out.println("3. Track room occupancy changes over time");
            System.out.println("4. Maximum occupancy gives the answer");
            System.out.println();

            System.out.println("APPROACH OPTIONS:");
            System.out.println("1. Difference Array: Simulate time array, mark occupancy");
            System.out.println("2. Sweep Line: Track events (start/end) with counter");
            System.out.println("3. Priority Queue: Simulate room allocation directly");
            System.out.println("4. Event Processing: Sort all events by time");
        }

        /**
         * Compare different algorithmic approaches
         */
        public static void compareApproaches() {
            System.out.println("\n=== Approach Comparison ===");

            System.out.println("1. DIFFERENCE ARRAY:");
            System.out.println("   - Time: O(max_time) for array creation + O(n) for processing");
            System.out.println("   - Space: O(max_time) for the array");
            System.out.println("   - Problem: Large time ranges create huge arrays");
            System.out.println("   - Best for: Small, dense time ranges");
            System.out.println();

            System.out.println("2. SWEEP LINE (Two Pointers):");
            System.out.println("   - Time: O(n log n) for sorting + O(n) for scanning");
            System.out.println("   - Space: O(n) for start/end arrays");
            System.out.println("   - Advantage: Efficient for any time range");
            System.out.println("   - Best for: General case, large time ranges");
            System.out.println();

            System.out.println("3. PRIORITY QUEUE:");
            System.out.println("   - Time: O(n log n) for sorting + O(n log n) for heap operations");
            System.out.println("   - Space: O(n) for the heap");
            System.out.println("   - Advantage: Natural simulation of room allocation");
            System.out.println("   - Best for: When you need actual room assignments");
            System.out.println();

            System.out.println("4. EVENT PROCESSING:");
            System.out.println("   - Time: O(n log n) for sorting events");
            System.out.println("   - Space: O(n) for event list");
            System.out.println("   - Advantage: Handles complex event types");
            System.out.println("   - Best for: Multiple event types, extensible design");
        }

        /**
         * Demonstrate why sweep line is optimal
         */
        public static void demonstrateSweepLineAdvantage() {
            System.out.println("\n=== Why Sweep Line is Optimal ===");
            System.out.println();

            System.out.println("DIFFERENCE ARRAY LIMITATIONS:");
            System.out.println("Example: meetings = [[0,30], [5,10], [10^8, 10^9]]");
            System.out.println("- Need array of size 10^9 (1 billion elements)");
            System.out.println("- Memory: ~4GB just for the array");
            System.out.println("- Time: O(10^9) to initialize and scan");
            System.out.println();

            System.out.println("SWEEP LINE ADVANTAGES:");
            System.out.println("- Only needs O(n) space regardless of time range");
            System.out.println("- Time complexity O(n log n) independent of time values");
            System.out.println("- Works efficiently for any time range");
            System.out.println("- Elegant and mathematically sound");
            System.out.println();

            System.out.println("SWEEP LINE INTUITION:");
            System.out.println("1. Imagine a vertical line moving left to right on timeline");
            System.out.println("2. At each meeting start: increment room counter");
            System.out.println("3. At each meeting end: decrement room counter");
            System.out.println("4. Maximum counter value = minimum rooms needed");
            System.out.println("5. Two pointers simulate this sweeping process efficiently");
        }
    }
}