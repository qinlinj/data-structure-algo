package org.qinlinj.algoframework._500_data_structure_design._560_application_design._562_exam_seat_allocation; /**
 * Data Structure Setup for Exam Room
 * <p>
 * This class focuses on setting up the core data structures needed to implement
 * the Exam Room problem efficiently.
 * <p>
 * Key Data Structures:
 * <p>
 * 1. TreeSet<int[]>:
 * - Stores segments (represented as int arrays [start, end])
 * - Ordered by segment "value" (distance between midpoint and endpoint)
 * - Allows quick access to the "best" segment (maximum distance)
 * - Provides O(log N) insertion, deletion, and retrieval
 * <p>
 * 2. HashMap<Integer, int[]> for start positions:
 * - Maps a seat index to the segment that starts at that index
 * - Enables O(1) lookup when we need to find a segment by its start position
 * <p>
 * 3. HashMap<Integer, int[]> for end positions:
 * - Maps a seat index to the segment that ends at that index
 * - Enables O(1) lookup when we need to find a segment by its end position
 * <p>
 * These data structures work together to efficiently track the best seat and handle
 * student arrivals and departures.
 */

import java.util.*;

public class _562_c_DataStructureSetup {

    public static void main(String[] args) {
        // Demonstrate the ExamRoom class with its data structures
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println("Created an exam room with 10 seats");

        System.out.println("Adding first student...");
        int seat1 = examRoom.seat();
        System.out.println("First student assigned to seat: " + seat1);

        System.out.println("Adding second student...");
        int seat2 = examRoom.seat();
        System.out.println("Second student assigned to seat: " + seat2);

        System.out.println("Current data structure state:");
        examRoom.printState();
    }

    static class ExamRoom {
        // The number of seats in the exam room
        private int N;

        // TreeSet to store segments ordered by their "value"
        private TreeSet<int[]> segments;

        // Map from position to segment starting at that position
        private Map<Integer, int[]> startMap;

        // Map from position to segment ending at that position
        private Map<Integer, int[]> endMap;

        /**
         * Constructor: Initialize the exam room with N seats
         */
        public ExamRoom(int N) {
            this.N = N;

            // Initialize the maps to track segments
            startMap = new HashMap<>();
            endMap = new HashMap<>();

            // Initialize TreeSet with custom comparator
            // This will be updated in a later implementation
            segments = new TreeSet<>((a, b) -> {
                int distA = distance(a);
                int distB = distance(b);
                return distA - distB;
            });

            // Add initial "virtual" segment covering the entire room
            addSegment(new int[]{-1, N});
        }

        /**
         * Calculate the "value" of a segment
         * (Simplified version for now, will be updated later)
         */
        private int distance(int[] segment) {
            return segment[1] - segment[0] - 1;
        }

        /**
         * Add a segment to all data structures
         */
        private void addSegment(int[] segment) {
            segments.add(segment);
            startMap.put(segment[0], segment);
            endMap.put(segment[1], segment);
        }

        /**
         * Remove a segment from all data structures
         */
        private void removeSegment(int[] segment) {
            segments.remove(segment);
            startMap.remove(segment[0]);
            endMap.remove(segment[1]);
        }

        /**
         * Placeholder for seat() method
         */
        public int seat() {
            // This will be implemented in a later class
            return 0;
        }

        /**
         * Placeholder for leave() method
         */
        public void leave(int p) {
            // This will be implemented in a later class
        }

        /**
         * Helper method to print the current state
         * (For demonstration purposes only)
         */
        public void printState() {
            System.out.println("Current segments:");
            for (int[] segment : segments) {
                System.out.println("  Segment: [" + segment[0] + ", " + segment[1] +
                        "], Distance: " + distance(segment));
            }
        }
    }
}