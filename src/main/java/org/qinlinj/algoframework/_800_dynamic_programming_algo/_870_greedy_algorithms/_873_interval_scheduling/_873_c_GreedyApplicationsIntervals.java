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
}
