package org.qinlinj.algoframework._500_data_structure_design._560_application_design._562_exam_seat_allocation;

/**
 * LeetCode Problem 855: Exam Room
 * <p>
 * This problem involves implementing a seating arrangement system for an examination room.
 * The goal is to place students in seats that maximize the distance between them.
 * <p>
 * Problem Description:
 * - We have a row of N seats, indexed from 0 to N-1
 * - Students enter one by one and need to be allocated seats
 * - When a student enters, they should be seated to maximize the distance from the closest person
 * - If multiple positions offer the same maximum distance, choose the seat with the lowest index
 * - Students can leave at any time, and their seat becomes available again
 * <p>
 * Required Operations:
 * 1. ExamRoom(int N): Initialize the system with N seats
 * 2. seat(): Find the best seat for a new student and return its index
 * 3. leave(int p): Mark the seat at position p as vacant
 * <p>
 * The problem tests our understanding of data structures and requires a clever approach
 * to efficiently track the best available seat without checking every possibility.
 */
public class _562_a_ExamRoomIntroduction {

    public static void main(String[] args) {
        // Example usage
        ExamRoom examRoom = new ExamRoom(5);

        // First student sits at position 0 (lowest index when empty)
        System.out.println("First student seat: " + examRoom.seat());  // Returns 0

        // Second student sits at position 4 (farthest from first student)
        System.out.println("Second student seat: " + examRoom.seat()); // Returns 4

        // Third student sits at position 2 (middle point between 0 and 4)
        System.out.println("Third student seat: " + examRoom.seat());  // Returns 2

        // Fourth student sits at position 1 (middle point between 0 and 2)
        System.out.println("Fourth student seat: " + examRoom.seat()); // Returns 1

        // Student at position 4 leaves
        examRoom.leave(4);

        // Next student sits at position 4 (farthest from others)
        System.out.println("After leave(4), next seat: " + examRoom.seat()); // Returns 4
    }

    static class ExamRoom {
        // Placeholder implementation for demonstration
        public ExamRoom(int N) {
        }

        public int seat() {
            return 0;
        }

        public void leave(int p) {
        }
    }
}