package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_n_SingleThreadedCPU
 * <p>
 * LeetCode #1834: Single-Threaded CPU
 * <p>
 * This solution simulates a single-threaded CPU processing tasks according to specific rules:
 * - If the CPU is idle and no tasks are available, it remains idle
 * - If the CPU is idle and tasks are available, it processes the one with the shortest processing time
 * - If multiple tasks have the same processing time, it chooses the one with the smallest index
 * - The CPU processes each task to completion before starting another
 * <p>
 * Approach:
 * 1. Sort tasks by enqueue time to know when each task becomes available
 * 2. Use a min-heap to select the task with the shortest processing time from available tasks
 * 3. Simulate time passing, adding tasks to the available pool when their enqueue time is reached
 * 4. Process tasks one by one according to the priority rules
 * <p>
 * Time Complexity: O(n log n) for sorting and heap operations
 * Space Complexity: O(n) for the arrays and heap
 */

import java.util.*;

public class _531_n_SingleThreadedCPU {

    public static void main(String[] args) {
        _531_n_SingleThreadedCPU solution = new _531_n_SingleThreadedCPU();

        // Test case 1
        int[][] tasks1 = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        int[] result1 = solution.getOrder(tasks1);

        System.out.println("Tasks: ");
        printTasks(tasks1);
        System.out.println("Processing order: " + Arrays.toString(result1));
        // Expected output: [0, 2, 3, 1]

        // Test case 2
        int[][] tasks2 = {{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}};
        int[] result2 = solution.getOrder(tasks2);

        System.out.println("\nTasks: ");
        printTasks(tasks2);
        System.out.println("Processing order: " + Arrays.toString(result2));
        // Expected output: [4, 3, 2, 0, 1]
    }

    // Helper method to print tasks
    private static void printTasks(int[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println("Task " + i + ": enqueue time = " + tasks[i][0] +
                    ", processing time = " + tasks[i][1]);
        }
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        // Create array of task info with original indices
        int[][] sortedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortedTasks[i][0] = tasks[i][0];  // Enqueue time
            sortedTasks[i][1] = tasks[i][1];  // Processing time
            sortedTasks[i][2] = i;            // Original index
        }

        // Sort tasks by enqueue time
        Arrays.sort(sortedTasks, Comparator.comparingInt(a -> a[0]));

        // Min heap to store available tasks
        // Ordered by: 1. Processing time, 2. Original index
        PriorityQueue<int[]> availableTasks = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];  // Sort by processing time
            }
            return a[2] - b[2];      // If processing times are equal, sort by index
        });

        int[] result = new int[n];
        int resultIndex = 0;
        int taskIndex = 0;
        long currentTime = 0;  // Use long to avoid integer overflow

        // Process tasks until all are complete
        while (resultIndex < n) {
            // Add all tasks that have arrived by the current time
            while (taskIndex < n && sortedTasks[taskIndex][0] <= currentTime) {
                availableTasks.offer(sortedTasks[taskIndex]);
                taskIndex++;
            }

            if (availableTasks.isEmpty()) {
                // If no tasks are available, jump time to the next task's enqueue time
                currentTime = sortedTasks[taskIndex][0];
            } else {
                // Process the next task (shortest processing time)
                int[] currentTask = availableTasks.poll();
                result[resultIndex++] = currentTask[2];  // Add original index to result

                // Update current time
                currentTime += currentTask[1];
            }
        }

        return result;
    }
}