package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

/**
 * MEETING ROOMS PROBLEM - DIFFERENCE ARRAY APPROACH
 * <p>
 * Conceptual Foundation:
 * The difference array technique is based on the idea of efficiently performing
 * range updates on an array. For the meeting rooms problem, we can think of:
 * - Time as an array where each index represents a time unit
 * - Each meeting [start, end] as a range update: increment all elements in [start, end)
 * - The maximum value in the final array represents peak concurrent meetings
 * <p>
 * Algorithm Concept:
 * 1. Create an array representing the timeline (initially all zeros)
 * 2. For each meeting [start, end]:
 * - Increment the counter at start time (+1 meeting begins)
 * - Decrement the counter at end time (-1 meeting ends)
 * 3. Compute prefix sum to get actual meeting count at each time
 * 4. Return the maximum value (peak concurrent meetings)
 * <p>
 * Difference Array Mechanics:
 * - diff[i] represents the change in meeting count at time i
 * - Prefix sum: actual[i] = actual[i-1] + diff[i]
 * - Range update [start, end): diff[start]++, diff[end]--
 * - O(1) time per update, O(n) time for final computation
 * <p>
 * Advantages:
 * - Intuitive: Directly models the problem as array operations
 * - Educational: Shows connection between range updates and prefix sums
 * - Simple implementation for small time ranges
 * <p>
 * Limitations:
 * - Space complexity O(max_time): problematic for large time ranges
 * - Time complexity includes O(max_time) for array initialization
 * - Inefficient when time range >> number of meetings
 * <p>
 * Example: meetings = [[0,30], [5,10], [15,20]]
 * - Need array of size 30
 * - diff[0]++, diff[30]-- (meeting 1)
 * - diff[5]++, diff[10]-- (meeting 2)
 * - diff[15]++, diff[20]-- (meeting 3)
 * - Compute prefix sum and find maximum
 * <p>
 * This approach sets the foundation for understanding the more efficient
 * sweep line algorithm that follows.
 */

import java.util.*;

public class _874_b_DifferenceArrayApproach {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              MEETING ROOMS - DIFFERENCE ARRAY               ║");
        System.out.println("║          Foundation for Sweep Line Algorithm                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Test basic difference array approach
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
        BasicDifferenceArray.minMeetingRoomsDifferenceArray(meetings);
        BasicDifferenceArray.demonstrateStepByStep(meetings);

        // Test optimized approach
        OptimizedDifferenceArray.minMeetingRoomsCompressed(meetings);
        OptimizedDifferenceArray.compareApproaches(meetings);

        // Show limitations and motivate sweep line
        LimitationsAndTransition.demonstrateLimitations();
        LimitationsAndTransition.motivateSweepLine();
        LimitationsAndTransition.showEvolution();

        // Performance analysis
        PerformanceAnalysis.analyzeComplexity();
        PerformanceAnalysis.practicalComparison();

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. DIFFERENCE ARRAY CONCEPT:");
        System.out.println("   - Range updates in O(1), query in O(n)");
        System.out.println("   - Perfect for understanding the problem structure");
        System.out.println("   - Shows why we only care about start/end events");
        System.out.println();

        System.out.println("2. LIMITATIONS:");
        System.out.println("   - Space/time complexity depends on time range");
        System.out.println("   - Inefficient for sparse time usage");
        System.out.println("   - Coordinate compression helps but adds complexity");
        System.out.println();

        System.out.println("3. EVOLUTION TO SWEEP LINE:");
        System.out.println("   - Key insight: Only event times matter");
        System.out.println("   - Process events chronologically with counter");
        System.out.println("   - Achieves optimal O(n log n) time, O(n) space");
        System.out.println("   - Independent of time range values");
        System.out.println();

        System.out.println("The difference array approach teaches us the core insight:");
        System.out.println("Meeting rooms = maximum overlapping intervals = event processing!");
        System.out.println();
        System.out.println("Next: We'll implement the elegant sweep line algorithm! 🚀");
    }

    /**
     * Basic difference array implementation
     * Works well for small time ranges, demonstrates the core concept
     */
    public static class BasicDifferenceArray {

        /**
         * Difference array solution for meeting rooms
         */
        public static int minMeetingRoomsDifferenceArray(int[][] meetings) {
            if (meetings.length == 0) return 0;

            System.out.println("=== Difference Array Approach ===");
            System.out.println("Meetings: " + Arrays.deepToString(meetings));

            // Find the maximum time to determine array size
            int maxTime = 0;
            for (int[] meeting : meetings) {
                maxTime = Math.max(maxTime, meeting[1]);
            }

            System.out.println("Timeline length needed: " + maxTime);

            // Create difference array
            int[] diff = new int[maxTime + 1];

            // Apply range updates using difference array
            System.out.println("\nApplying difference array updates:");
            for (int i = 0; i < meetings.length; i++) {
                int start = meetings[i][0];
                int end = meetings[i][1];

                diff[start]++;     // Meeting starts: +1
                if (end < diff.length) {
                    diff[end]--;   // Meeting ends: -1
                }

                System.out.printf("Meeting %d [%d,%d]: diff[%d]++, diff[%d]--%n",
                        i, start, end, start, end);
            }

            // Show difference array state
            System.out.println("\nDifference array: " + Arrays.toString(diff));

            // Compute prefix sum to get actual meeting counts
            int maxRooms = 0;
            int currentMeetings = 0;

            System.out.println("\nComputing prefix sum (actual meeting counts):");
            for (int i = 0; i < diff.length; i++) {
                currentMeetings += diff[i];
                maxRooms = Math.max(maxRooms, currentMeetings);

                if (diff[i] != 0 || i % 5 == 0) { // Print key time points
                    System.out.printf("Time %d: %d meetings active%n", i, currentMeetings);
                }
            }

            System.out.println("Maximum concurrent meetings: " + maxRooms);
            return maxRooms;
        }

        /**
         * Demonstrate the concept with step-by-step visualization
         */
        public static void demonstrateStepByStep(int[][] meetings) {
            System.out.println("\n=== Step-by-Step Demonstration ===");

            int maxTime = Arrays.stream(meetings)
                    .flatMapToInt(meeting -> Arrays.stream(meeting))
                    .max().orElse(0);

            // Create timeline visualization
            System.out.println("Timeline visualization:");
            System.out.print("Time:     ");
            for (int t = 0; t <= maxTime; t++) {
                System.out.printf("%2d ", t);
            }
            System.out.println();

            // Show each meeting on timeline
            for (int i = 0; i < meetings.length; i++) {
                System.out.printf("Meeting %d: ", i);
                for (int t = 0; t <= maxTime; t++) {
                    if (t >= meetings[i][0] && t < meetings[i][1]) {
                        System.out.print(" * ");
                    } else {
                        System.out.print("   ");
                    }
                }
                System.out.printf(" [%d,%d)%n", meetings[i][0], meetings[i][1]);
            }

            // Show concurrent count at each time
            int[] count = new int[maxTime + 1];
            for (int[] meeting : meetings) {
                for (int t = meeting[0]; t < meeting[1]; t++) {
                    count[t]++;
                }
            }

            System.out.print("Count:    ");
            for (int t = 0; t <= maxTime; t++) {
                System.out.printf("%2d ", count[t]);
            }
            System.out.println();

            int maxCount = Arrays.stream(count).max().orElse(0);
            System.out.println("Maximum concurrent meetings: " + maxCount);
        }
    }

    /**
     * Optimized difference array with coordinate compression
     * Handles larger time ranges by only considering relevant time points
     */
    public static class OptimizedDifferenceArray {

        /**
         * Use coordinate compression to handle large time ranges
         */
        public static int minMeetingRoomsCompressed(int[][] meetings) {
            if (meetings.length == 0) return 0;

            System.out.println("\n=== Optimized Difference Array (Coordinate Compression) ===");
            System.out.println("Meetings: " + Arrays.deepToString(meetings));

            // Collect all unique time points
            Set<Integer> timePoints = new TreeSet<>();
            for (int[] meeting : meetings) {
                timePoints.add(meeting[0]); // Start time
                timePoints.add(meeting[1]); // End time
            }

            List<Integer> times = new ArrayList<>(timePoints);
            System.out.println("Unique time points: " + times);

            // Create compressed difference array
            Map<Integer, Integer> timeToIndex = new HashMap<>();
            for (int i = 0; i < times.size(); i++) {
                timeToIndex.put(times.get(i), i);
            }

            int[] diff = new int[times.size()];

            // Apply difference array updates
            System.out.println("\nApplying compressed updates:");
            for (int[] meeting : meetings) {
                int startIdx = timeToIndex.get(meeting[0]);
                int endIdx = timeToIndex.get(meeting[1]);

                diff[startIdx]++;
                if (endIdx < diff.length) {
                    diff[endIdx]--;
                }

                System.out.printf("Meeting [%d,%d] -> indices [%d,%d]%n",
                        meeting[0], meeting[1], startIdx, endIdx);
            }

            // Compute maximum concurrent meetings
            int maxRooms = 0;
            int currentMeetings = 0;

            System.out.println("\nComputing concurrent meetings:");
            for (int i = 0; i < diff.length; i++) {
                currentMeetings += diff[i];
                maxRooms = Math.max(maxRooms, currentMeetings);

                System.out.printf("Time %d (compressed %d): %d meetings%n",
                        times.get(i), i, currentMeetings);
            }

            System.out.println("Maximum concurrent meetings: " + maxRooms);
            return maxRooms;
        }

        /**
         * Compare basic vs compressed approach
         */
        public static void compareApproaches(int[][] meetings) {
            System.out.println("\n=== Approach Comparison ===");

            int maxTime = Arrays.stream(meetings)
                    .flatMapToInt(meeting -> Arrays.stream(meeting))
                    .max().orElse(0);

            int uniqueTimePoints = (int) Arrays.stream(meetings)
                    .flatMapToInt(meeting -> Arrays.stream(meeting))
                    .distinct().count();

            System.out.println("Basic Difference Array:");
            System.out.println("  Array size needed: " + maxTime);
            System.out.println("  Space complexity: O(" + maxTime + ")");
            System.out.println("  Time complexity: O(" + maxTime + ")");

            System.out.println("\nCompressed Difference Array:");
            System.out.println("  Array size needed: " + uniqueTimePoints);
            System.out.println("  Space complexity: O(" + uniqueTimePoints + ")");
            System.out.println("  Time complexity: O(n log n) for sorting + O(n)");

            System.out.println("\nSpace saving: " +
                    (maxTime > 0 ? String.format("%.1fx", (double) maxTime / uniqueTimePoints) : "N/A"));
        }
    }

    /**
     * Demonstrate limitations and transition to sweep line
     */
    public static class LimitationsAndTransition {

        /**
         * Show why difference array can be problematic
         */
        public static void demonstrateLimitations() {
            System.out.println("\n=== Difference Array Limitations ===");

            // Example with large time range
            int[][] largeMeetings = {
                    {0, 30},
                    {5, 10},
                    {100000000, 100000010} // Very large time values
            };

            System.out.println("Problematic input: " + Arrays.deepToString(largeMeetings));

            int maxTime = Arrays.stream(largeMeetings)
                    .flatMapToInt(meeting -> Arrays.stream(meeting))
                    .max().orElse(0);

            System.out.println("Maximum time: " + maxTime);
            System.out.println("Array size needed: " + maxTime + " elements");
            System.out.printf("Memory required: ~%.1f GB%n", maxTime * 4.0 / (1024 * 1024 * 1024));
            System.out.println("This is clearly impractical!");

            System.out.println("\nWhy this happens:");
            System.out.println("- Difference array size depends on time range, not number of meetings");
            System.out.println("- Sparse time usage (few meetings across large time range)");
            System.out.println("- Wasted space for unused time slots");
            System.out.println("- Time complexity becomes dominated by array size, not input size");
        }

        /**
         * Motivate the need for sweep line algorithm
         */
        public static void motivateSweepLine() {
            System.out.println("\n=== Motivation for Sweep Line Algorithm ===");
            System.out.println();

            System.out.println("DIFFERENCE ARRAY INSIGHTS:");
            System.out.println("1. Only start/end times matter for counting meetings");
            System.out.println("2. Meeting count changes only at start/end events");
            System.out.println("3. We don't need to track every time unit explicitly");
            System.out.println("4. Can process events in chronological order");
            System.out.println();

            System.out.println("SWEEP LINE OPTIMIZATION:");
            System.out.println("1. Extract all start/end events from meetings");
            System.out.println("2. Sort events by time (start events before end events)");
            System.out.println("3. Process events in order: +1 for start, -1 for end");
            System.out.println("4. Track maximum concurrent count during processing");
            System.out.println();

            System.out.println("BENEFITS:");
            System.out.println("- Time complexity: O(n log n) regardless of time range");
            System.out.println("- Space complexity: O(n) regardless of time range");
            System.out.println("- Elegant and mathematically sound");
            System.out.println("- Scales well with input size, not time range");
        }

        /**
         * Show the evolution from difference array to sweep line
         */
        public static void showEvolution() {
            System.out.println("\n=== Algorithm Evolution ===");

            int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
            System.out.println("Example: " + Arrays.deepToString(meetings));
            System.out.println();

            System.out.println("STEP 1: Difference Array Perspective");
            System.out.println("- Create array of size 30");
            System.out.println("- diff[0]++, diff[30]--");
            System.out.println("- diff[5]++, diff[10]--");
            System.out.println("- diff[15]++, diff[20]--");
            System.out.println("- Compute prefix sum, find maximum");
            System.out.println();

            System.out.println("STEP 2: Identify Key Events");
            System.out.println("- Start events: 0(+1), 5(+1), 15(+1)");
            System.out.println("- End events: 10(-1), 20(-1), 30(-1)");
            System.out.println("- Only these time points matter!");
            System.out.println();

            System.out.println("STEP 3: Sweep Line Algorithm");
            System.out.println("- Sort all events: 0(+1), 5(+1), 10(-1), 15(+1), 20(-1), 30(-1)");
            System.out.println("- Process in order, maintaining running count");
            System.out.println("- Maximum count = answer");
            System.out.println();

            // Demonstrate the sweep line process
            List<int[]> events = new ArrayList<>();
            for (int[] meeting : meetings) {
                events.add(new int[]{meeting[0], 1});  // start: +1
                events.add(new int[]{meeting[1], -1}); // end: -1
            }

            events.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(a[1], b[1]); // end before start
                return Integer.compare(a[0], b[0]);
            });

            System.out.println("STEP 4: Execution");
            int count = 0, maxCount = 0;
            for (int[] event : events) {
                count += event[1];
                maxCount = Math.max(maxCount, count);
                String type = event[1] == 1 ? "start" : "end";
                System.out.printf("Time %d (%s): count = %d%n", event[0], type, count);
            }
            System.out.println("Maximum concurrent meetings: " + maxCount);
        }
    }

    /**
     * Performance analysis and comparison
     */
    public static class PerformanceAnalysis {

        /**
         * Analyze time and space complexity
         */
        public static void analyzeComplexity() {
            System.out.println("\n=== Complexity Analysis ===");
            System.out.println();

            System.out.println("BASIC DIFFERENCE ARRAY:");
            System.out.println("- Time: O(max_time) array init + O(n) updates + O(max_time) scan");
            System.out.println("- Total time: O(max_time + n)");
            System.out.println("- Space: O(max_time)");
            System.out.println("- Problem: Depends on time range, not input size");
            System.out.println();

            System.out.println("COMPRESSED DIFFERENCE ARRAY:");
            System.out.println("- Time: O(n log n) for coordinate compression + O(n) processing");
            System.out.println("- Total time: O(n log n)");
            System.out.println("- Space: O(n) for compressed coordinates");
            System.out.println("- Better, but still more complex than needed");
            System.out.println();

            System.out.println("SWEEP LINE (NEXT SECTION):");
            System.out.println("- Time: O(n log n) for sorting + O(n) for processing");
            System.out.println("- Total time: O(n log n)");
            System.out.println("- Space: O(n) for events");
            System.out.println("- Optimal: Only depends on input size");
        }

        /**
         * Practical performance comparison
         */
        public static void practicalComparison() {
            System.out.println("\n=== Practical Performance Comparison ===");

            System.out.println("SCENARIO 1: Small time range, many meetings");
            System.out.println("- Input: 1000 meetings in time range [0, 100]");
            System.out.println("- Difference array: Fast (small array)");
            System.out.println("- Sweep line: Also fast, similar performance");
            System.out.println();

            System.out.println("SCENARIO 2: Large time range, few meetings");
            System.out.println("- Input: 10 meetings in time range [0, 10^9]");
            System.out.println("- Difference array: Very slow (huge array)");
            System.out.println("- Sweep line: Fast (only processes 20 events)");
            System.out.println();

            System.out.println("SCENARIO 3: Real-world typical");
            System.out.println("- Input: 100-1000 meetings over business hours/days");
            System.out.println("- Difference array: Moderate performance");
            System.out.println("- Sweep line: Consistently fast and predictable");
            System.out.println();

            System.out.println("CONCLUSION: Sweep line wins in general case!");
        }
    }
}