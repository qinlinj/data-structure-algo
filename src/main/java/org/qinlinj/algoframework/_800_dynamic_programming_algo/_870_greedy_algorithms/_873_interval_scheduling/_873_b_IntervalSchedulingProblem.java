package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

import java.util.*;

/**
 * INTERVAL SCHEDULING PROBLEM - CLASSIC GREEDY ALGORITHM
 * LeetCode 435: Non-overlapping Intervals (variant)
 * <p>
 * Problem Statement:
 * Given multiple intervals [start, end], find the maximum number of non-overlapping intervals.
 * Two intervals are non-overlapping if they don't share any common points (touching boundaries allowed).
 * <p>
 * Real-world Applications:
 * - Activity scheduling: What's the maximum number of activities you can attend?
 * - Meeting room allocation: How many meetings can be scheduled in one room?
 * - Resource allocation: Optimal assignment of time-based resources
 * - Task scheduling: Maximize number of non-conflicting tasks
 * <p>
 * Key Insights:
 * 1. Greedy Choice Property: Always select interval with earliest end time
 * 2. Why this works: Early ending intervals leave more room for future selections
 * 3. Mathematical proof: Any optimal solution can be modified to include greedy choice
 * <p>
 * Algorithm Strategy:
 * 1. Sort intervals by end time (ascending order)
 * 2. Greedily select non-overlapping intervals
 * 3. Each selection eliminates all overlapping intervals
 * <p>
 * Common Wrong Approaches:
 * - Select shortest intervals first → Counterexample: [1,100], [2,3], [4,5]
 * - Select earliest start time → Counterexample: [1,100], [2,3], [4,5]
 * - Select intervals with fewest conflicts → Complex to compute and still wrong
 * <p>
 * Time Complexity: O(n log n) for sorting + O(n) for selection = O(n log n)
 * Space Complexity: O(1) excluding input storage
 * <p>
 * This problem perfectly demonstrates greedy choice property and serves as
 * a foundation for many other interval-based optimization problems.
 */

public class _873_b_IntervalSchedulingProblem {
    /**
     * Core interval scheduling algorithm
     * Returns maximum number of non-overlapping intervals
     */
    public static int intervalSchedule(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time (key insight: earliest end time first)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1; // At least one interval can always be selected
        int lastEnd = intervals[0][1]; // End time of last selected interval

        System.out.println("=== Interval Scheduling Algorithm ===");
        System.out.println("Sorted intervals by end time:");
        for (int i = 0; i < intervals.length; i++) {
            System.out.printf("Interval %d: [%d, %d]%n", i, intervals[i][0], intervals[i][1]);
        }
        System.out.println();

        System.out.println("Selection process:");
        System.out.printf("Selected interval 0: [%d, %d]%n", intervals[0][0], intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start >= lastEnd) {
                // Non-overlapping interval found
                count++;
                lastEnd = end;
                System.out.printf("Selected interval %d: [%d, %d] (start %d >= last_end %d)%n",
                        i, start, end, start, intervals[i - 1][1]);
            } else {
                // Overlapping interval, skip it
                System.out.printf("Skipped interval %d: [%d, %d] (overlaps with selected)%n",
                        i, start, end);
            }
        }

        System.out.println("Total selected intervals: " + count);
        return count;
    }

    /**
     * Demonstrate why wrong greedy choices fail
     */
    public static class WrongGreedyApproaches {

        /**
         * Wrong approach 1: Select shortest intervals first
         */
        public static int selectShortestFirst(int[][] intervals) {
            if (intervals.length == 0) return 0;

            // Sort by interval length
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1] - a[0], b[1] - b[0]));

            System.out.println("=== Wrong Approach: Shortest First ===");
            System.out.println("Sorted by length:");
            for (int i = 0; i < intervals.length; i++) {
                int length = intervals[i][1] - intervals[i][0];
                System.out.printf("Interval %d: [%d, %d] length=%d%n",
                        i, intervals[i][0], intervals[i][1], length);
            }

            int count = 1;
            int lastEnd = intervals[0][1];
            System.out.printf("Selected: [%d, %d]%n", intervals[0][0], intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= lastEnd) {
                    count++;
                    lastEnd = intervals[i][1];
                    System.out.printf("Selected: [%d, %d]%n", intervals[i][0], intervals[i][1]);
                } else {
                    System.out.printf("Skipped: [%d, %d] (overlaps)%n", intervals[i][0], intervals[i][1]);
                }
            }

            System.out.println("Result with shortest-first: " + count);
            return count;
        }

        /**
         * Wrong approach 2: Select earliest start time first
         */
        public static int selectEarliestStart(int[][] intervals) {
            if (intervals.length == 0) return 0;

            // Sort by start time
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            System.out.println("\n=== Wrong Approach: Earliest Start ===");
            System.out.println("Sorted by start time:");
            for (int i = 0; i < intervals.length; i++) {
                System.out.printf("Interval %d: [%d, %d]%n", i, intervals[i][0], intervals[i][1]);
            }

            int count = 1;
            int lastEnd = intervals[0][1];
            System.out.printf("Selected: [%d, %d]%n", intervals[0][0], intervals[0][1]);

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= lastEnd) {
                    count++;
                    lastEnd = intervals[i][1];
                    System.out.printf("Selected: [%d, %d]%n", intervals[i][0], intervals[i][1]);
                } else {
                    System.out.printf("Skipped: [%d, %d] (overlaps)%n", intervals[i][0], intervals[i][1]);
                }
            }

            System.out.println("Result with earliest-start: " + count);
            return count;
        }

        /**
         * Demonstrate counterexamples for wrong approaches
         */
        public static void showCounterexamples() {
            System.out.println("\n=== Counterexamples for Wrong Approaches ===");

            // Counterexample for shortest-first approach
            int[][] example1 = {{1, 100}, {2, 3}, {4, 5}};
            System.out.println("Example 1: " + Arrays.deepToString(example1));
            System.out.println("Optimal (earliest end): Select [2,3], [4,5] → count = 2");
            System.out.println("Shortest first: Select [2,3], [4,5], skip [1,100] → count = 2");
            System.out.println("Actually, this example doesn't break shortest-first. Let's try another:");

            int[][] example2 = {{1, 10}, {2, 3}, {4, 5}, {6, 7}, {8, 9}};
            System.out.println("\nExample 2: " + Arrays.deepToString(example2));
            System.out.println("Optimal: Select [2,3], [4,5], [6,7], [8,9] → count = 4");
            System.out.println("If we had [1,15] instead of [1,10]:");

            int[][] example3 = {{1, 15}, {2, 3}, {4, 5}, {6, 7}, {8, 9}};
            System.out.println("Example 3: " + Arrays.deepToString(example3));
            System.out.println("Shortest first might select [2,3], then skip others due to [1,15]");
            System.out.println("Optimal: Select [2,3], [4,5], [6,7], [8,9] → count = 4");
        }
    }
}
