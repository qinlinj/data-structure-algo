package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

import java.util.*;

public class _874_d_MeetingRoomsExtensions {
    /**
     * Extension 1: Room Sufficiency Check
     * Given k rooms, determine if all meetings can be accommodated
     */
    public static class RoomSufficiencyCheck {

        /**
         * Check if k rooms are sufficient for all meetings
         */
        public static boolean canAccommodateWithKRooms(int[][] meetings, int k) {
            System.out.println("=== Room Sufficiency Check ===");
            System.out.println("Meetings: " + Arrays.deepToString(meetings));
            System.out.println("Available rooms: " + k);

            if (meetings.length == 0) {
                System.out.println("No meetings - sufficient rooms: true");
                return true;
            }

            // Use sweep line to find maximum concurrent meetings
            int n = meetings.length;
            int[] start = new int[n];
            int[] end = new int[n];

            for (int i = 0; i < n; i++) {
                start[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }

            Arrays.sort(start);
            Arrays.sort(end);

            int maxConcurrent = 0;
            int currentMeetings = 0;
            int i = 0, j = 0;

            while (i < n && j < n) {
                if (start[i] < end[j]) {
                    currentMeetings++;
                    i++;
                } else {
                    currentMeetings--;
                    j++;
                }
                maxConcurrent = Math.max(maxConcurrent, currentMeetings);
            }

            boolean sufficient = maxConcurrent <= k;
            System.out.println("Maximum concurrent meetings: " + maxConcurrent);
            System.out.println("Sufficient rooms: " + sufficient);

            return sufficient;
        }

        /**
         * Find minimum additional rooms needed
         */
        public static int additionalRoomsNeeded(int[][] meetings, int currentRooms) {
            int n = meetings.length;
            if (n == 0) return 0;

            int[] start = new int[n];
            int[] end = new int[n];

            for (int i = 0; i < n; i++) {
                start[i] = meetings[i][0];
                end[i] = meetings[i][1];
            }

            Arrays.sort(start);
            Arrays.sort(end);

            int maxConcurrent = 0;
            int currentMeetings = 0;
            int i = 0, j = 0;

            while (i < n && j < n) {
                if (start[i] < end[j]) {
                    currentMeetings++;
                    i++;
                } else {
                    currentMeetings--;
                    j++;
                }
                maxConcurrent = Math.max(maxConcurrent, currentMeetings);
            }

            int additional = Math.max(0, maxConcurrent - currentRooms);
            System.out.printf("Current rooms: %d, Needed: %d, Additional: %d%n",
                    currentRooms, maxConcurrent, additional);

            return additional;
        }
    }
}
