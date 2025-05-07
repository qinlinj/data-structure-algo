package org.qinlinj.algoframework._500_data_structure_design._560_application_design._562_exam_seat_allocation; /**
 * Simple Exam Room Implementation
 * <p>
 * This class provides a simplified implementation of the Exam Room problem,
 * without handling the "lowest index" requirement yet.
 * <p>
 * This implementation:
 * 1. Treats the exam room as a collection of segments between students
 * 2. Uses a TreeSet to find the longest segment quickly
 * 3. Uses HashMaps to track segment relationships
 * <p>
 * The simplified algorithm:
 * - seat(): Find the longest segment, place student at midpoint, split into two segments
 * - leave(p): Find segments to left and right of p, remove them, add a new merged segment
 * <p>
 * This solution handles the basic functionality but doesn't yet address the requirement
 * to choose the seat with the lowest index when multiple positions have the same distance.
 */

import java.util.*;

public class _562_d_SimpleExamRoom {

    public static void main(String[] args) {
        // Demonstrate the simple ExamRoom implementation
        ExamRoom examRoom = new ExamRoom(10);

        System.out.println("First student sits at: " + examRoom.seat());   // 0
        System.out.println("Second student sits at: " + examRoom.seat());  // 9
        System.out.println("Third student sits at: " + examRoom.seat());   // 4
        System.out.println("Fourth student sits at: " + examRoom.seat());  // 2

        System.out.println("\nRemoving student at position 4");
        examRoom.leave(4);

        System.out.println("Next student sits at: " + examRoom.seat());    // 4
    }

    static class ExamRoom {
        // The number of seats in the exam room
        private int N;

        // TreeSet to store segments ordered by their length
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
            startMap = new HashMap<>();
            endMap = new HashMap<>();

            // Order segments by their length (longest at the end)
            segments = new TreeSet<>((a, b) -> {
                int distA = distance(a);
                int distB = distance(b);
                // Sort by distance in ascending order
                return distA - distB;
            });

            // Add initial "virtual" segment covering the entire room
            addSegment(new int[]{-1, N});
        }

        /**
         * Calculate the distance (length) of a segment
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
         * Seat a new student in the best available position
         */
        public int seat() {
            // Get the longest segment (highest priority)
            int[] longest = segments.last();
            int start = longest[0];
            int end = longest[1];

            int seatPosition;

            // Case 1: Start of the room
            if (start == -1) {
                seatPosition = 0;
            }
            // Case 2: End of the room
            else if (end == N) {
                seatPosition = N - 1;
            }
            // Case 3: Middle of a segment
            else {
                // Place at the midpoint of the segment
                seatPosition = start + (end - start) / 2;
            }

            // Remove the original segment
            removeSegment(longest);

            // Add two new segments representing the split
            addSegment(new int[]{start, seatPosition});
            addSegment(new int[]{seatPosition, end});

            return seatPosition;
        }

        /**
         * Remove a student from the given position
         */
        public void leave(int position) {
            // Find the segments to the left and right of the position
            int[] rightSegment = startMap.get(position);
            int[] leftSegment = endMap.get(position);

            // Create a new merged segment
            int[] mergedSegment = new int[]{leftSegment[0], rightSegment[1]};

            // Remove the old segments
            removeSegment(leftSegment);
            removeSegment(rightSegment);

            // Add the new merged segment
            addSegment(mergedSegment);
        }
    }
}