package org.qinlinj.algoframework._500_data_structure_design._560_application_design._562_exam_seat_allocation; /**
 * Complete Exam Room Solution
 * <p>
 * This class provides the complete solution to the LeetCode problem 855: Exam Room.
 * It implements all the required functionality with proper handling of edge cases
 * and the lowest index requirement.
 * <p>
 * Key components:
 * <p>
 * 1. Data structures:
 * - TreeSet for ordered storage of segments
 * - HashMaps for efficient segment lookup
 * <p>
 * 2. Core operations:
 * - seat(): Find best seat based on maximum distance and lowest index
 * - leave(p): Merge segments when a student leaves
 * <p>
 * 3. Helper methods:
 * - distance(): Calculate effective distance of a segment
 * - addSegment()/removeSegment(): Manage data structures
 * <p>
 * Time complexity:
 * - Construction: O(1)
 * - seat(): O(log N) where N is the number of students
 * - leave(p): O(log N)
 * <p>
 * Space complexity: O(N) for storing segments and mappings
 */

import java.util.*;

public class _562_f_CompleteExamRoom {

    public static void main(String[] args) {
        // Demonstrate the complete solution with all test cases
        ExamRoom examRoom = new ExamRoom(10);

        // Test case 1: Basic seating
        System.out.println("Test Case 1: Basic Seating");
        System.out.println("Seat 1: " + examRoom.seat());  // 0
        System.out.println("Seat 2: " + examRoom.seat());  // 9
        System.out.println("Seat 3: " + examRoom.seat());  // 4
        System.out.println("Seat 4: " + examRoom.seat());  // 2
        System.out.println("Seat 5: " + examRoom.seat());  // 6
        System.out.println();

        // Test case 2: Leaving and reseating
        System.out.println("Test Case 2: Leaving and Reseating");
        System.out.println("Student at position 4 leaves");
        examRoom.leave(4);
        System.out.println("Next seat: " + examRoom.seat());  // 4
        System.out.println();

        // Test case 3: Multiple students leaving
        System.out.println("Test Case 3: Multiple Students Leaving");
        System.out.println("Student at position 2 leaves");
        examRoom.leave(2);
        System.out.println("Student at position 6 leaves");
        examRoom.leave(6);
        System.out.println("Next seat: " + examRoom.seat());  // 2
        System.out.println("Next seat: " + examRoom.seat());  // 6
        System.out.println();
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

            // Comparator for TreeSet to handle ordering by distance and index
            segments = new TreeSet<>((a, b) -> {
                int distA = distance(a);
                int distB = distance(b);

                // If distances are equal, prioritize the segment that gives the lower index
                if (distA == distB) {
                    return b[0] - a[0];
                }

                // Otherwise, order by distance (smaller to larger)
                return distA - distB;
            });

            // Add initial "virtual" segment covering the entire room
            addSegment(new int[]{-1, N});
        }

        /**
         * Calculate the effective distance of a segment
         * This determines the priority for seating
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
         * Assign a seat to a new student based on maximum distance and lowest index
         */
        public int seat() {
            // Get the best segment (highest priority by distance and index)
            int[] best = segments.last();
            int start = best[0];
            int end = best[1];

            int seatPosition;

            // Case 1: First student - seat at position 0
            if (start == -1) {
                seatPosition = 0;
            }
            // Case 2: Last position in the room
            else if (end == N) {
                seatPosition = N - 1;
            }
            // Case 3: Middle of a segment - take the midpoint
            else {
                // Calculate midpoint (this gives the lower index when equal distances)
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
         * Handle a student leaving from position p
         * This merges the segments to the left and right of p
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