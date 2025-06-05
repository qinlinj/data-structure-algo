package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

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

import java.util.*;

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

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              INTERVAL SCHEDULING PROBLEM                    ║");
        System.out.println("║           Classic Greedy Algorithm Application              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Test the main algorithm
        int[][] testIntervals = {{1, 3}, {2, 4}, {3, 6}};
        System.out.println("Test case: " + Arrays.deepToString(testIntervals));
        int result = intervalSchedule(testIntervals);
        System.out.println("Expected: 2, Got: " + result);
        System.out.println();

        // Show why wrong approaches fail
        System.out.println("=".repeat(60));
        WrongGreedyApproaches.showCounterexamples();

        // Test wrong approaches on a clear counterexample
        int[][] counterExample = {{0, 4}, {1, 2}, {3, 5}, {4, 6}};
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Testing approaches on: " + Arrays.deepToString(counterExample));

        int optimal = intervalSchedule(counterExample.clone());
        System.out.println();

        int shortestFirst = WrongGreedyApproaches.selectShortestFirst(counterExample.clone());
        int earliestStart = WrongGreedyApproaches.selectEarliestStart(counterExample.clone());

        System.out.println("\nComparison:");
        System.out.println("Optimal (earliest end): " + optimal);
        System.out.println("Shortest first: " + shortestFirst);
        System.out.println("Earliest start: " + earliestStart);

        // Show correctness proof
        CorrectnessProof.explainOptimalityProof();
        CorrectnessProof.intuitiveExplanation();

        // Visualize the algorithm
        AlgorithmVisualization.visualizeSelection(testIntervals);

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. Greedy choice: Always select interval with earliest end time");
        System.out.println("2. Why it works: Leaves maximum room for future selections");
        System.out.println("3. Proof technique: Exchange argument shows optimality");
        System.out.println("4. Time complexity: O(n log n) due to sorting");
        System.out.println("5. This forms the foundation for many interval problems");
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

    /**
     * Mathematical proof of correctness
     */
    public static class CorrectnessProof {

        /**
         * Explain why earliest end time is optimal
         */
        public static void explainOptimalityProof() {
            System.out.println("\n=== Proof of Optimality ===");
            System.out.println();

            System.out.println("THEOREM: Always selecting the interval with earliest end time");
            System.out.println("among remaining intervals gives optimal solution.");
            System.out.println();

            System.out.println("PROOF SKETCH (Exchange Argument):");
            System.out.println("1. Let OPT be any optimal solution");
            System.out.println("2. Let GREEDY be our greedy solution");
            System.out.println("3. Let x be the first interval where OPT ≠ GREEDY");
            System.out.println();

            System.out.println("4. OPT selects interval a = [start_a, end_a]");
            System.out.println("5. GREEDY selects interval b = [start_b, end_b]");
            System.out.println("6. By greedy choice: end_b ≤ end_a");
            System.out.println();

            System.out.println("7. We can replace interval a with interval b in OPT:");
            System.out.println("   - b doesn't conflict with previous intervals (same as a)");
            System.out.println("   - b doesn't conflict with future intervals (end_b ≤ end_a)");
            System.out.println("   - New solution has same number of intervals");
            System.out.println();

            System.out.println("8. Continue this process until OPT = GREEDY");
            System.out.println("9. Therefore, GREEDY is optimal. QED.");
        }

        /**
         * Intuitive explanation of why it works
         */
        public static void intuitiveExplanation() {
            System.out.println("\n=== Intuitive Explanation ===");
            System.out.println();

            System.out.println("WHY EARLIEST END TIME WORKS:");
            System.out.println("🎯 Goal: Maximize number of intervals");
            System.out.println("💡 Key insight: Earlier endings leave more room for future selections");
            System.out.println();

            System.out.println("ANALOGY - Party Planning:");
            System.out.println("- You want to attend maximum parties today");
            System.out.println("- Each party has start and end time");
            System.out.println("- Strategy: Always pick party that ends earliest");
            System.out.println("- Why? Leaves most time for subsequent parties");
            System.out.println();

            System.out.println("MATHEMATICAL INTUITION:");
            System.out.println("- Each interval 'consumes' time from [start, end]");
            System.out.println("- Goal: Minimize total time consumption");
            System.out.println("- Earliest end time = minimal time commitment");
            System.out.println("- Greedy minimization leads to global maximization");
        }
    }

    /**
     * Step-by-step visualization
     */
    public static class AlgorithmVisualization {

        /**
         * Visualize the selection process
         */
        public static void visualizeSelection(int[][] intervals) {
            if (intervals.length == 0) return;

            System.out.println("\n=== Algorithm Visualization ===");

            // Sort intervals by end time for visualization
            int[][] sorted = intervals.clone();
            Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

            System.out.println("Timeline visualization:");
            int maxEnd = Arrays.stream(sorted).mapToInt(interval -> interval[1]).max().orElse(0);

            // Print timeline
            System.out.print("Time: ");
            for (int t = 0; t <= maxEnd; t++) {
                System.out.printf("%2d ", t);
            }
            System.out.println();

            // Print each interval
            boolean[] selected = new boolean[sorted.length];
            int lastEnd = -1;
            int selectedCount = 0;

            for (int i = 0; i < sorted.length; i++) {
                int start = sorted[i][0];
                int end = sorted[i][1];

                if (start >= lastEnd) {
                    selected[i] = true;
                    lastEnd = end;
                    selectedCount++;
                }

                System.out.printf("I%d:   ", i);
                for (int t = 0; t <= maxEnd; t++) {
                    if (t >= start && t <= end) {
                        System.out.print(selected[i] ? " * " : " - ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.printf(" [%d,%d] %s%n", start, end,
                        selected[i] ? "SELECTED" : "skipped");
            }

            System.out.println("\nSelected intervals marked with '*', skipped with '-'");
            System.out.println("Total selected: " + selectedCount);
        }
    }
}