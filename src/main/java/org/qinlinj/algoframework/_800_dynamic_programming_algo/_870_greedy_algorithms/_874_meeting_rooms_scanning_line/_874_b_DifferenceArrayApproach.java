package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

import java.util.*;

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

public class _874_b_DifferenceArrayApproach {
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
    }
}
