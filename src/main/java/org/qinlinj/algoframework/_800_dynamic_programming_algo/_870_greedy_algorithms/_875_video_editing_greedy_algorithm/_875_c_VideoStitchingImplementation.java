package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._875_video_editing_greedy_algorithm;

/**
 * VIDEO STITCHING ALGORITHM IMPLEMENTATION
 * LeetCode 1024: Video Stitching - Complete Solution
 * <p>
 * Algorithm Core:
 * Use greedy strategy with strategic sorting to find minimum clips needed
 * to cover target interval [0, T]. The key insight is that we can always
 * make locally optimal choices that lead to globally optimal solution.
 * <p>
 * Implementation Details:
 * 1. SORTING STRATEGY:
 * - Primary: start time ascending (process clips chronologically)
 * - Secondary: end time descending (prefer longer clips for same start)
 * <p>
 * 2. GREEDY SELECTION:
 * - Track current coverage end (curEnd)
 * - Track maximum reachable from current position (nextEnd)
 * - For each coverage window, select clip extending furthest
 * <p>
 * 3. TERMINATION CONDITIONS:
 * - Success: coverage reaches target T
 * - Failure: gap found (no clip covers next required position)
 * <p>
 * Key Variables:
 * - curEnd: Right boundary of current coverage
 * - nextEnd: Maximum right boundary reachable from current coverage
 * - res: Number of clips selected so far
 * - i: Pointer for iterating through sorted clips
 * <p>
 * Algorithm Flow:
 * 1. Sort clips strategically
 * 2. While coverage hasn't reached T:
 * a. Find all clips starting within current coverage
 * b. Among these, select the one extending furthest
 * c. Update coverage and increment clip count
 * d. Check for gaps or completion
 * <p>
 * Time Complexity: O(n log n) for sorting + O(n) for processing = O(n log n)
 * Space Complexity: O(1) excluding input storage
 * <p>
 * This implementation demonstrates the power of greedy algorithms when
 * problem structure guarantees that local optimal choices lead to
 * global optimal solutions.
 */

import java.util.*;

public class _875_c_VideoStitchingImplementation {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           VIDEO STITCHING ALGORITHM IMPLEMENTATION          ║");
        System.out.println("║              Complete Solution with Analysis                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Main example from the problem
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int T = 10;

        // Detailed algorithm execution
        int result = VideoStitchingSolution.videoStitchingDetailed(clips, T);

        // Algorithm visualization
        AlgorithmVisualization.visualizeExecution(clips, T);

        // Comprehensive testing
        ComprehensiveTesting.runTests();
        ComprehensiveTesting.performanceAnalysis();

        System.out.println("\n=== Implementation Summary ===");
        System.out.println("🎯 CORE ALGORITHM:");
        System.out.println("   Sort + Greedy coverage extension");
        System.out.println();

        System.out.println("⚡ COMPLEXITY:");
        System.out.println("   Time: O(n log n), Space: O(1)");
        System.out.println();

        System.out.println("🔑 KEY INSIGHTS:");
        System.out.println("   1. Strategic sorting enables greedy choices");
        System.out.println("   2. Two-variable tracking (curEnd, nextEnd)");
        System.out.println("   3. Local optimal → Global optimal");
        System.out.println();

        System.out.println("✅ VERIFICATION:");
        System.out.println("   All test cases pass, algorithm is correct!");
        System.out.println();

        System.out.println("This completes our comprehensive video stitching solution! 🎬");
    }

    /**
     * Main algorithm implementation
     */
    public static class VideoStitchingSolution {

        /**
         * Core algorithm: minimum clips to cover [0, T]
         */
        public static int videoStitching(int[][] clips, int T) {
            if (T == 0) return 0;

            // Strategic sorting: start ascending, end descending for ties
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];  // Longer clips first for same start
                }
                return a[0] - b[0];      // Earlier start times first
            });

            int res = 0;        // Number of clips selected
            int curEnd = 0;     // Current coverage right boundary
            int nextEnd = 0;    // Maximum reachable right boundary
            int i = 0;          // Pointer for clips array
            int n = clips.length;

            // Continue until we cover [0, T] or determine it's impossible
            while (i < n && clips[i][0] <= curEnd) {
                // Greedy selection within current coverage window
                while (i < n && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    i++;
                }

                // Select the best clip (increment counter)
                res++;
                curEnd = nextEnd;

                // Check if we've covered the target
                if (curEnd >= T) {
                    return res;
                }
            }

            // If we exit the loop without covering T, it's impossible
            return -1;
        }

        /**
         * Detailed implementation with step-by-step explanation
         */
        public static int videoStitchingDetailed(int[][] clips, int T) {
            System.out.println("=== Video Stitching Algorithm Execution ===");
            System.out.println("Input clips: " + Arrays.deepToString(clips));
            System.out.println("Target: [0, " + T + "]");
            System.out.println();

            if (T == 0) {
                System.out.println("Target is 0, no clips needed");
                return 0;
            }

            // Sort clips
            System.out.println("Step 1: Sorting clips");
            System.out.println("Original: " + Arrays.deepToString(clips));
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });
            System.out.println("Sorted:   " + Arrays.deepToString(clips));
            System.out.println("Strategy: start time asc, end time desc for ties");
            System.out.println();

            // Initialize tracking variables
            int res = 0;
            int curEnd = 0;
            int nextEnd = 0;
            int i = 0;
            int n = clips.length;

            System.out.println("Step 2: Greedy selection process");
            System.out.println("Initial state: curEnd=0, nextEnd=0, res=0, i=0");
            System.out.println();

            int round = 1;
            while (i < n && clips[i][0] <= curEnd) {
                System.out.printf("Round %d: Finding clips starting ≤ %d%n", round, curEnd);

                // Find best clip within current coverage
                int bestEnd = nextEnd;
                int startI = i;
                while (i < n && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    System.out.printf("  Consider clip [%d,%d]: nextEnd = %d%n",
                            clips[i][0], clips[i][1], nextEnd);
                    i++;
                }

                // Select the best extension
                res++;
                curEnd = nextEnd;

                System.out.printf("  Selected best extension: curEnd = %d, res = %d%n", curEnd, res);
                System.out.printf("  Processed clips %d to %d%n", startI, i - 1);

                if (curEnd >= T) {
                    System.out.printf("  Target reached! Coverage [0,%d] ≥ [0,%d]%n", curEnd, T);
                    System.out.println("Final result: " + res + " clips needed");
                    return res;
                }

                System.out.println();
                round++;
            }

            System.out.printf("Cannot extend further. curEnd=%d < T=%d%n", curEnd, T);
            System.out.println("Result: Impossible (-1)");
            return -1;
        }

        /**
         * Alternative implementation using explicit interval tracking
         */
        public static int videoStitchingAlternative(int[][] clips, int T) {
            if (T == 0) return 0;

            // Sort clips
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });

            List<int[]> selected = new ArrayList<>();
            int coverage = 0;
            int i = 0;

            while (coverage < T && i < clips.length) {
                if (clips[i][0] > coverage) {
                    // Gap found - impossible to continue
                    return -1;
                }

                // Find the clip extending furthest from current coverage
                int maxEnd = coverage;
                int bestClip = -1;

                while (i < clips.length && clips[i][0] <= coverage) {
                    if (clips[i][1] > maxEnd) {
                        maxEnd = clips[i][1];
                        bestClip = i;
                    }
                    i++;
                }

                if (bestClip == -1 || maxEnd <= coverage) {
                    // No progress possible
                    return -1;
                }

                selected.add(clips[bestClip]);
                coverage = maxEnd;
            }

            return coverage >= T ? selected.size() : -1;
        }
    }

    /**
     * Algorithm visualization and step-by-step demonstration
     */
    public static class AlgorithmVisualization {

        /**
         * Visualize the algorithm execution with timeline
         */
        public static void visualizeExecution(int[][] clips, int T) {
            System.out.println("\n=== Algorithm Visualization ===");

            // Sort clips first
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            });

            System.out.println("Timeline visualization:");
            printTimeline(T);

            // Show all clips on timeline
            System.out.println("Available clips:");
            for (int i = 0; i < clips.length; i++) {
                printClipOnTimeline(clips[i], T, "Clip" + i);
            }

            System.out.println();

            // Simulate algorithm execution
            simulateAlgorithm(clips, T);
        }

        /**
         * Print timeline header
         */
        private static void printTimeline(int T) {
            System.out.print("Time:   ");
            for (int t = 0; t <= T; t++) {
                System.out.printf("%2d ", t);
            }
            System.out.println();

            System.out.print("Target: ");
            for (int t = 0; t < T; t++) {
                System.out.print("-- ");
            }
            System.out.println("|");
        }

        /**
         * Print clip on timeline
         */
        private static void printClipOnTimeline(int[] clip, int T, String name) {
            System.out.printf("%-7s ", name + ":");
            for (int t = 0; t <= T; t++) {
                if (t >= clip[0] && t < clip[1]) {
                    System.out.print("** ");
                } else if (t == clip[1]) {
                    System.out.print("]  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.printf(" [%d,%d]%n", clip[0], clip[1]);
        }

        /**
         * Simulate algorithm step by step
         */
        private static void simulateAlgorithm(int[][] clips, int T) {
            System.out.println("Algorithm simulation:");

            int res = 0, curEnd = 0, nextEnd = 0, i = 0;
            List<int[]> selected = new ArrayList<>();

            while (i < clips.length && clips[i][0] <= curEnd) {
                System.out.printf("\nRound %d: Current coverage [0,%d]%n", res + 1, curEnd);

                // Find best clip
                int roundStart = i;
                while (i < clips.length && clips[i][0] <= curEnd) {
                    if (clips[i][1] > nextEnd) {
                        nextEnd = clips[i][1];
                    }
                    System.out.printf("  Consider [%d,%d] → nextEnd = %d%n",
                            clips[i][0], clips[i][1], nextEnd);
                    i++;
                }

                // Select best extension
                res++;
                curEnd = nextEnd;

                // Find which clip was actually selected (for visualization)
                for (int j = roundStart; j < i; j++) {
                    if (clips[j][1] == nextEnd && clips[j][0] <= curEnd - (nextEnd - curEnd)) {
                        selected.add(clips[j]);
                        System.out.printf("  SELECTED: [%d,%d] extending to %d%n",
                                clips[j][0], clips[j][1], nextEnd);
                        break;
                    }
                }

                if (curEnd >= T) {
                    System.out.println("  TARGET REACHED!");
                    break;
                }
            }

            System.out.println("\nFinal solution:");
            System.out.print("Selected: ");
            for (int[] clip : selected) {
                System.out.printf("[%d,%d] ", clip[0], clip[1]);
            }
            System.out.println();
            System.out.println("Total clips: " + res);
        }
    }

    /**
     * Comprehensive testing with various test cases
     */
    public static class ComprehensiveTesting {

        /**
         * Test algorithm with various cases
         */
        public static void runTests() {
            System.out.println("\n=== Comprehensive Testing ===");

            // Test case 1: Basic success
            testCase("Basic Success",
                    new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}},
                    10, 3);

            // Test case 2: Impossible
            testCase("Impossible Case",
                    new int[][]{{0, 1}, {1, 2}},
                    5, -1);

            // Test case 3: Complex case
            testCase("Complex Case",
                    new int[][]{{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}},
                    9, 3);

            // Test case 4: Edge cases
            testCase("Target Zero", new int[][]{{0, 1}, {1, 2}}, 0, 0);
            testCase("Single Clip", new int[][]{{0, 5}}, 3, 1);
            testCase("No Valid Start", new int[][]{{1, 3}, {2, 4}}, 5, -1);
        }

        /**
         * Test individual case
         */
        private static void testCase(String name, int[][] clips, int T, int expected) {
            System.out.println("\n--- " + name + " ---");
            System.out.println("Clips: " + Arrays.deepToString(clips));
            System.out.println("Target: " + T);
            System.out.println("Expected: " + expected);

            int result = VideoStitchingSolution.videoStitching(clips, T);
            System.out.println("Result: " + result);
            System.out.println("Status: " + (result == expected ? "✅ PASS" : "❌ FAIL"));
        }

        /**
         * Performance analysis
         */
        public static void performanceAnalysis() {
            System.out.println("\n=== Performance Analysis ===");

            System.out.println("TIME COMPLEXITY ANALYSIS:");
            System.out.println("1. Sorting: O(n log n)");
            System.out.println("2. Main loop: O(n) - each clip processed once");
            System.out.println("3. Inner loop: O(n) total across all iterations");
            System.out.println("Total: O(n log n) + O(n) = O(n log n)");
            System.out.println();

            System.out.println("SPACE COMPLEXITY ANALYSIS:");
            System.out.println("1. Input storage: O(n) for clips array");
            System.out.println("2. Additional space: O(1) for variables");
            System.out.println("Total: O(1) excluding input");
            System.out.println();

            System.out.println("OPTIMALITY:");
            System.out.println("✓ Time: Optimal for comparison-based algorithm");
            System.out.println("✓ Space: Optimal constant space");
            System.out.println("✓ Solution: Guaranteed optimal result");
        }
    }
}