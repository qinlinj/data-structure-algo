package org.qinlinj.algoframework._500_data_structure_design._560_application_design._562_exam_seat_allocation; /**
 * Improved Exam Room Implementation
 * <p>
 * This class enhances the previous implementation to handle the requirement
 * of choosing the seat with the lowest index when multiple positions have
 * the same distance.
 * <p>
 * Key improvements:
 * <p>
 * 1. Modified TreeSet comparator:
 * - First compares segments by distance value
 * - For equal distances, compares by starting position (higher start position first)
 * - This ensures the tie-breaking favors lower indices when selecting the midpoint
 * <p>
 * 2. Updated distance calculation:
 * - Changed to calculate the distance from midpoint to nearest endpoint
 * - Special cases for segments at room boundaries
 * <p>
 * These changes ensure that when two segments have the same effective distance,
 * the one that would yield the lower index seat is selected.
 */

import java.util.*;

public class _562_e_ImprovedExamRoom {

    public static void main(String[] args) {
        // Demonstrate the improved ExamRoom implementation
        ExamRoom examRoom = new ExamRoom(10);

        System.out.println("First student sits at: " + examRoom.seat());   // 0
        System.out.println("Second student sits at: " + examRoom.seat());  // 9
        System.out.println("Third student sits at: " + examRoom.seat());   // 4
        System.out.println("Fourth student sits at: " + examRoom.seat());  // 2
        System.out.println("Fifth student sits at: " + examRoom.seat());   // 6

        System.out.println("\nRemoving student at position 4");
        examRoom.leave(4);

        System.out.println("Next student sits at: " + examRoom.seat());    // 4
    }

    static class ExamRoom {
        // The number of seats in the exam room
        private int N;

        // TreeSet to store segments ordered by their distance and position
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

            // Updated comparator to handle the lowest index requirement
            segments = new TreeSet<>((a, b) -> {
                int distA = distance(a);
                int distB = distance(b);

                // If distances are equal, sort by start position in descending order
                // This will make the segment with lower midpoint come later in the TreeSet
                if (distA == distB) {
                    return b[0] - a[0];
                }

                // Otherwise, sort by distance in ascending order
                return distA - distB;
            });

            // Add initial "virtual" segment covering the entire room
            addSegment(new int[]{-1, N});
        }

        /**
         * Calculate the effective distance of a segment
         * This is the distance from the midpoint to the nearest endpoint
         */
        private int distance(int[] segment) {
            int start = segment[0];
            int end = segment[1];

            // Case 1: Segment at the start of the room
            if (start == -1) {
                return end;
            }

            // Case 2: Segment at the end of the room
            if (end == N) {
                return N - 1 - start;
            }

            // Case 3: Segment in the middle
            // Return distance from midpoint to nearest endpoint
            return (end - start) / 2;
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
            // Get the segment with highest priority (longest or lowest index)
            int[] best = segments.last();
            int start = best[0];
            int end = best[1];

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
            removeSegment(best);

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