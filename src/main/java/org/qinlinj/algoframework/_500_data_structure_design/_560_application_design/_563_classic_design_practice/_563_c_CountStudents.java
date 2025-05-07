package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 1700: Number of Students Unable to Eat Lunch
 * <p>
 * This problem involves a cafeteria scenario where students and sandwiches need to be matched.
 * <p>
 * Problem Description:
 * - There are n students and n sandwiches in a cafeteria
 * - Students are in a queue (FIFO) and sandwiches are in a stack (LIFO)
 * - Each student either wants a square (1) or circular (0) sandwich
 * - When a student gets to the front of the queue:
 * - If they prefer the sandwich on top of the stack, they take it and leave
 * - Otherwise, they leave the sandwich and go to the end of the queue
 * - This continues until no student can take the sandwich at the top of the stack
 * - Return the number of students unable to eat
 * <p>
 * Key Insight:
 * - Since students can cycle in the queue, any sandwich can potentially be taken
 * by any student who wants it
 * - The process only stops when none of the remaining students want the top sandwich
 * - So we just need to track how many students want each type of sandwich (0/1)
 * - Then process the sandwiches in order, reducing the count of students who want that type
 * - When we find a sandwich that no student wants, all remaining students can't eat
 * <p>
 * Time Complexity: O(n) where n is the number of students/sandwiches
 * Space Complexity: O(1) as we only use a fixed-size array
 */

import java.util.*;

public class _563_c_CountStudents {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] students1 = {1, 1, 0, 0};
        int[] sandwiches1 = {0, 1, 0, 1};
        System.out.println("Example 1:");
        System.out.println("Students: " + Arrays.toString(students1));
        System.out.println("Sandwiches: " + Arrays.toString(sandwiches1));
        System.out.println("Students unable to eat: " + solution.countStudents(students1, sandwiches1));
        System.out.println();

        // Example 2
        int[] students2 = {1, 1, 1, 0, 0, 1};
        int[] sandwiches2 = {1, 0, 0, 0, 1, 1};
        System.out.println("Example 2:");
        System.out.println("Students: " + Arrays.toString(students2));
        System.out.println("Sandwiches: " + Arrays.toString(sandwiches2));
        System.out.println("Students unable to eat: " + solution.countStudents(students2, sandwiches2));

        // Visual demonstration
        System.out.println("\nDetailed simulation of Example 2:");
        simulateProcess(students2, sandwiches2);
    }

    /**
     * Simulates the cafeteria process step by step for visualization
     */
    private static void simulateProcess(int[] students, int[] sandwiches) {
        // Count initial preferences
        int[] studentCount = new int[2];
        for (int type : students) {
            studentCount[type]++;
        }

        System.out.println("Initial state:");
        System.out.println("Students who want circular sandwich (0): " + studentCount[0]);
        System.out.println("Students who want square sandwich (1): " + studentCount[1]);

        for (int i = 0; i < sandwiches.length; i++) {
            int currentSandwich = sandwiches[i];
            if (studentCount[currentSandwich] > 0) {
                System.out.println("\nSandwich " + i + " is type " + currentSandwich);
                System.out.println("A student who wants type " + currentSandwich + " takes it");
                studentCount[currentSandwich]--;
            } else {
                System.out.println("\nSandwich " + i + " is type " + currentSandwich);
                System.out.println("No students want this type. Process stops here.");
                System.out.println("Remaining students unable to eat: " +
                        (studentCount[0] + studentCount[1]));
                break;
            }
        }
    }

    static class Solution {
        public int countStudents(int[] students, int[] sandwiches) {
            // Count how many students want each type of sandwich
            int[] studentCount = new int[2]; // Index 0 for circular, 1 for square

            for (int type : students) {
                studentCount[type]++;
            }

            // Process sandwiches in order (top to bottom of stack)
            for (int sandwichType : sandwiches) {
                if (studentCount[sandwichType] == 0) {
                    // No student wants this sandwich, so all remaining students can't eat
                    return studentCount[0] + studentCount[1];
                }
                // A student takes this sandwich
                studentCount[sandwichType]--;
            }

            // All sandwiches were taken
            return 0;
        }
    }
}