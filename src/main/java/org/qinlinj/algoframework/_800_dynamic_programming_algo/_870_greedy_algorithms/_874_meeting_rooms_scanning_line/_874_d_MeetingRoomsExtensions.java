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

    /**
     * Extension 2: Actual Room Assignments
     * Assign specific meetings to specific rooms
     */
    public static class RoomAssignment {

        /**
         * Assign meetings to rooms using priority queue
         */
        public static Map<Integer, List<Meeting>> assignMeetingsToRooms(int[][] meetings) {
            System.out.println("\n=== Room Assignment ===");
            System.out.println("Input meetings: " + Arrays.deepToString(meetings));

            if (meetings.length == 0) {
                return new HashMap<>();
            }

            // Create meeting objects with IDs
            List<Meeting> meetingList = new ArrayList<>();
            for (int i = 0; i < meetings.length; i++) {
                meetingList.add(new Meeting(meetings[i][0], meetings[i][1], i));
            }

            // Sort meetings by start time
            meetingList.sort((a, b) -> Integer.compare(a.start, b.start));

            // Priority queue to track when each room becomes available
            // Each element is [end_time, room_id]
            PriorityQueue<int[]> roomQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

            Map<Integer, List<Meeting>> roomAssignments = new HashMap<>();
            int nextRoomId = 0;

            System.out.println("\nAssignment process:");

            for (Meeting meeting : meetingList) {
                // Check if any room is available
                if (!roomQueue.isEmpty() && roomQueue.peek()[0] <= meeting.start) {
                    // Reuse existing room
                    int[] availableRoom = roomQueue.poll();
                    int roomId = availableRoom[1];

                    roomAssignments.get(roomId).add(meeting);
                    roomQueue.offer(new int[]{meeting.end, roomId});

                    System.out.printf("%s assigned to Room %d (reused)%n", meeting, roomId);
                } else {
                    // Need new room
                    int roomId = nextRoomId++;
                    roomAssignments.put(roomId, new ArrayList<>());
                    roomAssignments.get(roomId).add(meeting);
                    roomQueue.offer(new int[]{meeting.end, roomId});

                    System.out.printf("%s assigned to Room %d (new)%n", meeting, roomId);
                }
            }

            System.out.println("\nFinal room assignments:");
            for (Map.Entry<Integer, List<Meeting>> entry : roomAssignments.entrySet()) {
                System.out.printf("Room %d: %s%n", entry.getKey(), entry.getValue());
            }

            System.out.println("Total rooms used: " + roomAssignments.size());
            return roomAssignments;
        }

        static class Meeting {
            int start, end, id;

            Meeting(int start, int end, int id) {
                this.start = start;
                this.end = end;
                this.id = id;
            }

            @Override
            public String toString() {
                return String.format("M%d[%d,%d]", id, start, end);
            }
        }
    }
}
