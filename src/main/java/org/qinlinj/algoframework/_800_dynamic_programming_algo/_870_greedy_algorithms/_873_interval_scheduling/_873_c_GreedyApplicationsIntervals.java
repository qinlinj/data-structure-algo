package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

import java.util.*;

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

public class _873_c_GreedyApplicationsIntervals {
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
}
