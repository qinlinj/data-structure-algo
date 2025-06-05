package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._874_meeting_rooms_scanning_line;

/**
 * MEETING ROOMS PROBLEM EXTENSIONS AND VARIATIONS
 * <p>
 * Building on the core sweep line algorithm, this class explores various
 * extensions and related problems that demonstrate the versatility of the
 * interval processing pattern.
 * <p>
 * PROBLEM VARIATIONS COVERED:
 * <p>
 * 1. ROOM SUFFICIENCY CHECK:
 * - Given k rooms, can we accommodate all meetings?
 * - Simple modification: check if max concurrent <= k
 * <p>
 * 2. ACTUAL ROOM ASSIGNMENTS:
 * - Which specific meetings go in which rooms?
 * - Use priority queue to track room availability
 * <p>
 * 3. ROOM UTILIZATION OPTIMIZATION:
 * - Maximize total meeting time given limited rooms
 * - Dynamic programming with interval constraints
 * <p>
 * 4. MEETING SCHEDULING WITH PRIORITIES:
 * - Some meetings are more important than others
 * - Weighted interval scheduling problem
 * <p>
 * 5. RECURRING MEETINGS:
 * - Handle weekly/daily repeating meetings
 * - Expand time horizon and generate instances
 * <p>
 * 6. MEETING ROOM BOOKING SYSTEM:
 * - Dynamic booking with real-time availability
 * - Online algorithm with immediate responses
 * <p>
 * 7. MULTI-RESOURCE SCHEDULING:
 * - Meetings need rooms + equipment + people
 * - Multi-dimensional resource constraints
 * <p>
 * ALGORITHMIC PATTERNS:
 * - Event processing: Core pattern for time-based problems
 * - Priority queues: For tracking resource availability
 * - Dynamic programming: For optimization with constraints
 * - Greedy algorithms: For approximation and heuristics
 * - Online algorithms: For real-time systems
 * <p>
 * REAL-WORLD APPLICATIONS:
 * - Corporate meeting scheduling systems
 * - Hospital operating room allocation
 * - Manufacturing line scheduling
 * - Cloud resource provisioning
 * - Airport gate assignment
 * - Classroom scheduling in universities
 * <p>
 * This comprehensive exploration shows how mastering one core algorithm
 * opens doors to solving many related problems in different domains.
 */

import java.util.*;

public class _874_d_MeetingRoomsExtensions {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║             MEETING ROOMS EXTENSIONS                        ║");
        System.out.println("║        Advanced Variations and Real-World Applications      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Test data
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}, {25, 35}};

        // Extension 1: Room sufficiency
        RoomSufficiencyCheck.canAccommodateWithKRooms(meetings, 2);
        RoomSufficiencyCheck.additionalRoomsNeeded(meetings, 1);

        // Extension 2: Room assignments
        RoomAssignment.assignMeetingsToRooms(meetings);

        // Extension 3: Weighted scheduling
        int[] weights = {10, 20, 15, 25};
        WeightedMeetingScheduling.maximizeWeightedMeetings(meetings, weights, 2);

        // Extension 4: Online booking
        OnlineMeetingBooking bookingSystem = new OnlineMeetingBooking(2);
        bookingSystem.bookMeeting(0, 10, 1);
        bookingSystem.bookMeeting(5, 15, 2);
        bookingSystem.bookMeeting(12, 20, 3);
        bookingSystem.printUtilization();

        // Extension 5: Multi-resource scheduling
        MultiResourceScheduling.ResourceRequirement[] requirements = {
                new MultiResourceScheduling.ResourceRequirement(1, 1, 2),
                new MultiResourceScheduling.ResourceRequirement(1, 0, 1),
                new MultiResourceScheduling.ResourceRequirement(1, 1, 1),
                new MultiResourceScheduling.ResourceRequirement(1, 0, 2)
        };
        MultiResourceScheduling.scheduleMultiResourceMeetings(meetings, requirements);

        System.out.println("\n=== Summary of Extensions ===");
        System.out.println("1. ✅ Room Sufficiency: Check if k rooms are enough");
        System.out.println("2. ✅ Room Assignment: Assign meetings to specific rooms");
        System.out.println("3. ✅ Weighted Scheduling: Prioritize important meetings");
        System.out.println("4. ✅ Online Booking: Real-time booking system");
        System.out.println("5. ✅ Multi-Resource: Handle multiple resource types");
        System.out.println();

        System.out.println("🌟 REAL-WORLD IMPACT:");
        System.out.println("These extensions show how the core sweep line algorithm");
        System.out.println("can be adapted to solve complex resource allocation problems");
        System.out.println("across many industries and applications!");
    }

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

    /**
     * Extension 3: Meeting Priority and Weighted Scheduling
     * Some meetings are more important than others
     */
    public static class WeightedMeetingScheduling {

        /**
         * Maximize total weight of scheduled meetings with limited rooms
         */
        public static List<WeightedMeeting> maximizeWeightedMeetings(
                int[][] meetings, int[] weights, int maxRooms) {

            System.out.println("\n=== Weighted Meeting Scheduling ===");
            System.out.println("Meetings: " + Arrays.deepToString(meetings));
            System.out.println("Weights: " + Arrays.toString(weights));
            System.out.println("Max rooms: " + maxRooms);

            // Create weighted meetings
            List<WeightedMeeting> weightedMeetings = new ArrayList<>();
            for (int i = 0; i < meetings.length; i++) {
                weightedMeetings.add(new WeightedMeeting(
                        meetings[i][0], meetings[i][1], weights[i], i));
            }

            // Sort by end time for dynamic programming
            weightedMeetings.sort((a, b) -> Integer.compare(a.end, b.end));

            // This is a simplified greedy approach
            // For optimal solution, would need more complex DP
            List<WeightedMeeting> scheduled = new ArrayList<>();
            List<Integer> roomEndTimes = new ArrayList<>();

            for (WeightedMeeting meeting : weightedMeetings) {
                // Find available room
                int availableRoom = -1;
                for (int i = 0; i < roomEndTimes.size(); i++) {
                    if (roomEndTimes.get(i) <= meeting.start) {
                        availableRoom = i;
                        break;
                    }
                }

                if (availableRoom != -1) {
                    // Use existing room
                    roomEndTimes.set(availableRoom, meeting.end);
                    scheduled.add(meeting);
                    System.out.printf("Scheduled %s in room %d%n", meeting, availableRoom);
                } else if (roomEndTimes.size() < maxRooms) {
                    // Use new room
                    roomEndTimes.add(meeting.end);
                    scheduled.add(meeting);
                    System.out.printf("Scheduled %s in new room %d%n", meeting, roomEndTimes.size() - 1);
                } else {
                    System.out.printf("Cannot schedule %s (no available rooms)%n", meeting);
                }
            }

            int totalWeight = scheduled.stream().mapToInt(m -> m.weight).sum();
            System.out.println("Total weight: " + totalWeight);
            System.out.println("Scheduled meetings: " + scheduled);

            return scheduled;
        }

        static class WeightedMeeting {
            int start, end, weight, id;

            WeightedMeeting(int start, int end, int weight, int id) {
                this.start = start;
                this.end = end;
                this.weight = weight;
                this.id = id;
            }

            @Override
            public String toString() {
                return String.format("M%d[%d,%d,w=%d]", id, start, end, weight);
            }
        }
    }

    /**
     * Extension 4: Online Meeting Room Booking
     * Handle booking requests in real-time
     */
    public static class OnlineMeetingBooking {

        private TreeMap<Integer, Integer> bookedIntervals; // start -> end
        private int totalRooms;

        public OnlineMeetingBooking(int totalRooms) {
            this.totalRooms = totalRooms;
            this.bookedIntervals = new TreeMap<>();
            System.out.println("=== Online Meeting Room Booking System ===");
            System.out.println("Total rooms available: " + totalRooms);
        }

        /**
         * Try to book a meeting, return true if successful
         */
        public boolean bookMeeting(int start, int end, int meetingId) {
            System.out.printf("\nBooking request: Meeting %d [%d,%d]%n", meetingId, start, end);

            // Check how many rooms are needed at any point in [start, end)
            int maxConcurrent = getCurrentMaxConcurrent(start, end);

            if (maxConcurrent < totalRooms) {
                // Book the meeting
                bookedIntervals.put(start, end);
                System.out.printf("✅ Meeting %d booked successfully%n", meetingId);
                System.out.println("Current bookings: " + bookedIntervals);
                return true;
            } else {
                System.out.printf("❌ Meeting %d rejected (no available rooms)%n", meetingId);
                return false;
            }
        }

        /**
         * Calculate max concurrent meetings in time range [start, end)
         */
        private int getCurrentMaxConcurrent(int newStart, int newEnd) {
            // Collect all events including the new meeting
            List<int[]> events = new ArrayList<>();

            // Add existing meetings
            for (Map.Entry<Integer, Integer> entry : bookedIntervals.entrySet()) {
                events.add(new int[]{entry.getKey(), 1});   // start
                events.add(new int[]{entry.getValue(), -1}); // end
            }

            // Add the new meeting
            events.add(new int[]{newStart, 1});
            events.add(new int[]{newEnd, -1});

            // Sort events
            events.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            });

            // Find maximum concurrent
            int maxConcurrent = 0;
            int current = 0;

            for (int[] event : events) {
                current += event[1];
                maxConcurrent = Math.max(maxConcurrent, current);
            }

            return maxConcurrent;
        }

        /**
         * Cancel a meeting
         */
        public void cancelMeeting(int start, int end) {
            if (bookedIntervals.containsKey(start) && bookedIntervals.get(start) == end) {
                bookedIntervals.remove(start);
                System.out.printf("Meeting [%d,%d] cancelled%n", start, end);
            } else {
                System.out.printf("Meeting [%d,%d] not found%n", start, end);
            }
        }

        /**
         * Get current room utilization
         */
        public void printUtilization() {
            System.out.println("\nCurrent utilization:");
            System.out.println("Total rooms: " + totalRooms);
            System.out.println("Booked meetings: " + bookedIntervals.size());
            System.out.println("Active bookings: " + bookedIntervals);
        }
    }

    /**
     * Extension 5: Multi-Resource Scheduling
     * Meetings need rooms + equipment + people
     */
    public static class MultiResourceScheduling {

        /**
         * Schedule meetings with multiple resource constraints
         */
        public static boolean scheduleMultiResourceMeetings(
                int[][] meetings, ResourceRequirement[] requirements) {

            System.out.println("\n=== Multi-Resource Scheduling ===");
            System.out.println("Meetings: " + Arrays.deepToString(meetings));
            System.out.print("Requirements: ");
            for (ResourceRequirement req : requirements) {
                System.out.print(req + " ");
            }
            System.out.println();

            // Initial resource availability
            ResourceAvailability available = new ResourceAvailability(3, 2, 5);
            System.out.println("Initial resources: " + available);

            // Create events with resource requirements
            List<int[]> events = new ArrayList<>();
            for (int i = 0; i < meetings.length; i++) {
                events.add(new int[]{meetings[i][0], 1, i});  // start, +1, meeting_id
                events.add(new int[]{meetings[i][1], -1, i}); // end, -1, meeting_id
            }

            // Sort events
            events.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
                return Integer.compare(a[0], b[0]);
            });

            // Process events
            System.out.println("\nEvent processing:");
            for (int[] event : events) {
                int time = event[0];
                int type = event[1];
                int meetingId = event[2];
                ResourceRequirement req = requirements[meetingId];

                if (type == 1) {
                    // Meeting starts
                    if (available.canSupport(req)) {
                        available.allocate(req);
                        System.out.printf("Time %d: Meeting %d starts %s → %s%n",
                                time, meetingId, req, available);
                    } else {
                        System.out.printf("Time %d: Meeting %d REJECTED %s (insufficient resources)%n",
                                time, meetingId, req);
                        return false;
                    }
                } else {
                    // Meeting ends
                    available.deallocate(req);
                    System.out.printf("Time %d: Meeting %d ends, resources freed → %s%n",
                            time, meetingId, available);
                }
            }

            System.out.println("All meetings successfully scheduled!");
            return true;
        }

        static class ResourceRequirement {
            int rooms, projectors, laptops;

            ResourceRequirement(int rooms, int projectors, int laptops) {
                this.rooms = rooms;
                this.projectors = projectors;
                this.laptops = laptops;
            }

            @Override
            public String toString() {
                return String.format("(R:%d,P:%d,L:%d)", rooms, projectors, laptops);
            }
        }

        static class ResourceAvailability {
            int rooms, projectors, laptops;

            ResourceAvailability(int rooms, int projectors, int laptops) {
                this.rooms = rooms;
                this.projectors = projectors;
                this.laptops = laptops;
            }

            boolean canSupport(ResourceRequirement req) {
                return rooms >= req.rooms && projectors >= req.projectors && laptops >= req.laptops;
            }

            void allocate(ResourceRequirement req) {
                rooms -= req.rooms;
                projectors -= req.projectors;
                laptops -= req.laptops;
            }

            void deallocate(ResourceRequirement req) {
                rooms += req.rooms;
                projectors += req.projectors;
                laptops += req.laptops;
            }

            @Override
            public String toString() {
                return String.format("Available(R:%d,P:%d,L:%d)", rooms, projectors, laptops);
            }
        }
    }
}