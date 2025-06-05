package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

import java.util.*;

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

public class _874_a_MeetingRoomsOverview {
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

    }
}
