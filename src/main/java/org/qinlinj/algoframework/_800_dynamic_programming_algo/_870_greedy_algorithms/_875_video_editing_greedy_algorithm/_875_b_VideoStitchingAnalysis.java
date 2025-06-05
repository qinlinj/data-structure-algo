package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._875_video_editing_greedy_algorithm;

/**
 * VIDEO STITCHING PROBLEM ANALYSIS AND STRATEGY
 * LeetCode 1024: Video Stitching
 * <p>
 * Problem Statement:
 * Given video clips represented as intervals and a target duration T,
 * find the minimum number of clips needed to cover the entire duration [0, T].
 * Clips can be re-edited (cut) to fit the coverage needs.
 * <p>
 * Core Analysis:
 * 1. COVERAGE REQUIREMENT:
 * - Must cover continuous range [0, T] without gaps
 * - Clips can overlap, but gaps are not allowed
 * - Goal: Minimize number of clips used
 * <p>
 * 2. GREEDY INSIGHT:
 * - Must start with clip beginning at 0 (no other choice)
 * - For clips with same start time, always choose longest (greedy choice)
 * - At each step, choose clip that extends coverage furthest
 * <p>
 * 3. SORTING STRATEGY:
 * - Primary: Sort by start time (ascending)
 * - Secondary: For same start time, sort by end time (descending)
 * - This ensures optimal clips appear first for each position
 * <p>
 * 4. ALGORITHM PATTERN:
 * - Similar to Jump Game: greedy coverage extension
 * - Track current coverage end and next reachable end
 * - Advance coverage in optimal jumps
 * <p>
 * Key Observations:
 * 1. If no clip starts at 0, impossible to cover [0, T]
 * 2. For same start time, longer clip is always better (greedy choice)
 * 3. Must ensure continuous coverage (no gaps allowed)
 * 4. Can terminate early when coverage reaches T
 * <p>
 * Algorithm Framework:
 * 1. Sort clips by start time, then by end time (desc) for ties
 * 2. Initialize coverage tracking variables
 * 3. For each position, greedily select best extending clip
 * 4. Return minimum clips needed or -1 if impossible
 * <p>
 * Time Complexity: O(n log n) for sorting + O(n) for processing
 * Space Complexity: O(1) excluding input
 * <p>
 * This problem elegantly demonstrates how real-world constraints
 * (video editing) lead to fundamental algorithmic insights (greedy coverage).
 */

import java.util.*;

public class _875_b_VideoStitchingAnalysis {

    /**
     * Problem understanding through examples
     */
    public static class ProblemExamples {

        /**
         * Example 1: Basic successful case
         */
        public static void example1() {
            System.out.println("=== Example 1: Basic Success Case ===");
            int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
            int T = 10;

            System.out.println("Clips: " + Arrays.deepToString(clips));
            System.out.println("Target: [0, " + T + "]");
            System.out.println();

            System.out.println("Manual analysis:");
            System.out.println("- Must start with clip beginning at 0: [0,2]");
            System.out.println("- From position 2, can use [1,9] (starts ≤ 2, extends to 9)");
            System.out.println("- From position 9, can use [8,10] (starts ≤ 9, reaches target)");
            System.out.println("- Solution: [0,2] + [1,9] + [8,10] = 3 clips");
            System.out.println();

            visualizeExample(clips, T, "Example 1");
        }

        /**
         * Example 2: Impossible case
         */
        public static void example2() {
            System.out.println("=== Example 2: Impossible Case ===");
            int[][] clips = {{0, 1}, {1, 2}};
            int T = 5;

            System.out.println("Clips: " + Arrays.deepToString(clips));
            System.out.println("Target: [0, " + T + "]");
            System.out.println();

            System.out.println("Analysis:");
            System.out.println("- [0,1] covers [0,1]");
            System.out.println("- [1,2] covers [1,2]");
            System.out.println("- Combined coverage: [0,2]");
            System.out.println("- Gap: [2,5] cannot be covered");
            System.out.println("- Result: Impossible (-1)");
            System.out.println();

            visualizeExample(clips, T, "Example 2 (Impossible)");
        }

        /**
         * Example 3: Complex case with multiple options
         */
        public static void example3() {
            System.out.println("=== Example 3: Complex Case ===");
            int[][] clips = {{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}};
            int T = 9;

            System.out.println("Clips: " + Arrays.deepToString(clips));
            System.out.println("Target: [0, " + T + "]");
            System.out.println();

            System.out.println("Analysis (after sorting):");
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });
            System.out.println("Sorted: " + Arrays.deepToString(clips));
            System.out.println();

            System.out.println("Greedy selection:");
            System.out.println("1. Start at 0: Choose [0,4] (longest from 0)");
            System.out.println("2. From 4: Choose [4,7] (longest starting ≤ 4)");
            System.out.println("3. From 7: Choose [6,9] (longest starting ≤ 7, reaches target)");
            System.out.println("Solution: [0,4] + [4,7] + [6,9] = 3 clips");
        }

        /**
         * Visualize example with timeline
         */
        private static void visualizeExample(int[][] clips, int T, String title) {
            System.out.println("Timeline visualization for " + title + ":");
            System.out.print("Time: ");
            for (int t = 0; t <= T; t++) {
                System.out.printf("%2d ", t);
            }
            System.out.println();

            System.out.print("Target: ");
            for (int t = 0; t <= T; t++) {
                System.out.print("-- ");
            }
            System.out.println();

            for (int i = 0; i < clips.length; i++) {
                System.out.printf("Clip%d: ", i);
                for (int t = 0; t <= T; t++) {
                    if (t >= clips[i][0] && t < clips[i][1]) {
                        System.out.print("** ");
                    } else if (t == clips[i][1]) {
                        System.out.print("*] ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.printf(" [%d,%d]%n", clips[i][0], clips[i][1]);
            }
            System.out.println();
        }
    }

    /**
     * Sorting strategy analysis
     */
    public static class SortingStrategy {

        /**
         * Demonstrate why specific sorting is needed
         */
        public static void demonstrateSortingNeed() {
            System.out.println("=== Sorting Strategy Analysis ===");
            System.out.println();

            int[][] clips = {{2, 6}, {0, 4}, {0, 2}, {1, 3}};
            System.out.println("Original clips: " + Arrays.deepToString(clips));

            // Sort by start time only
            int[][] sortedByStart = clips.clone();
            Arrays.sort(sortedByStart, (a, b) -> Integer.compare(a[0], b[0]));
            System.out.println("Sorted by start only: " + Arrays.deepToString(sortedByStart));

            // Sort by start, then end descending
            int[][] optimallySorted = clips.clone();
            Arrays.sort(optimallySorted, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });
            System.out.println("Optimally sorted: " + Arrays.deepToString(optimallySorted));
            System.out.println();

            System.out.println("WHY THIS SORTING?");
            System.out.println("1. PRIMARY KEY (start time ascending):");
            System.out.println("   - Process clips in timeline order");
            System.out.println("   - Ensure we can build continuous coverage");
            System.out.println();

            System.out.println("2. SECONDARY KEY (end time descending):");
            System.out.println("   - For same start time, longer clip is always better");
            System.out.println("   - Greedy choice: why use short clip when long one available?");
            System.out.println("   - Example: [0,2] vs [0,4] starting at 0 → choose [0,4]");
            System.out.println();

            demonstrateSortingBenefit(optimallySorted);
        }

        /**
         * Show how sorting enables greedy algorithm
         */
        private static void demonstrateSortingBenefit(int[][] clips) {
            System.out.println("GREEDY ALGORITHM WITH SORTED CLIPS:");
            System.out.println("After sorting: " + Arrays.deepToString(clips));

            int curEnd = 0, nextEnd = 0, count = 0;
            int i = 0;
            int T = 6;

            System.out.println("Target: [0, " + T + "]");
            System.out.println();

            while (i < clips.length && clips[i][0] <= curEnd) {
                System.out.printf("Round %d: curEnd = %d%n", count + 1, curEnd);

                // Find best clip starting within current coverage
                while (i < clips.length && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    System.out.printf("  Consider clip [%d,%d]: nextEnd = %d%n",
                            clips[i][0], clips[i][1], nextEnd);
                    i++;
                }

                count++;
                curEnd = nextEnd;
                System.out.printf("  Selected best extension: curEnd = %d, count = %d%n", curEnd, count);

                if (curEnd >= T) {
                    System.out.println("  Target reached!");
                    break;
                }
                System.out.println();
            }

            System.out.println("Final result: " + count + " clips needed");
        }
    }
}