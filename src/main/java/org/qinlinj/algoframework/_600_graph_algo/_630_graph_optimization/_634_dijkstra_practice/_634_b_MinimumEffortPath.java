package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._634_dijkstra_practice; /**
 * Path with Minimum Effort - Dijkstra Algorithm Application
 * LeetCode 1631: https://leetcode.com/problems/path-with-minimum-effort/
 * <p>
 * Knowledge Points:
 * 1. Application of Dijkstra's algorithm to find a path with minimum effort in a grid
 * <p>
 * 2. Problem Translation:
 * - Each cell in the grid is a vertex in the graph
 * - Adjacent cells are connected by edges
 * - Edge weight is the absolute difference in height between cells
 * - The effort of a path is the maximum edge weight along the path
 * - The goal is to find the path from top-left to bottom-right with minimum effort
 * <p>
 * 3. Implementation Details:
 * - Treat each cell (i,j) as a node in the graph
 * - Define edges between adjacent cells (up, down, left, right)
 * - Edge weight is |heights[i][j] - heights[ni][nj]|
 * - Goal is to minimize the maximum edge weight along any path
 * - Modified Dijkstra to track the maximum edge weight (effort) instead of sum
 * <p>
 * 4. Key Insight:
 * - Unlike traditional Dijkstra, we're minimizing the maximum edge weight
 * - For each path, the "effort" is the maximum height difference encountered
 * <p>
 * 5. Time Complexity: O(mn log(mn)) where m,n are grid dimensions
 * Space Complexity: O(mn)
 */

import java.util.*;

public class _634_b_MinimumEffortPath {

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _634_b_MinimumEffortPath solution = new _634_b_MinimumEffortPath();

        // Example 1
        int[][] heights1 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println("Example 1: " + solution.minimumEffortPath(heights1));
        // Expected output: 2

        // Example 2
        int[][] heights2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        System.out.println("Example 2: " + solution.minimumEffortPath(heights2));
        // Expected output: 1

        // Example 3
        int[][] heights3 = {
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1}
        };
        System.out.println("Example 3: " + solution.minimumEffortPath(heights3));
        // Expected output: 0

        // Visualize the solution for Example 1
        explainSolution(heights1);
    }

    /**
     * Helper method to visualize the solution process
     */
    private static void explainSolution(int[][] heights) {
        System.out.println("\nDetailed explanation for Example 1:");
        System.out.println("Heights grid:");
        for (int[] row : heights) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nStep 1: Start from cell (0,0) with effort 0");
        System.out.println("Current grid: (X marks the current cell)");
        System.out.println("[X, 2, 2]");
        System.out.println("[3, 8, 2]");
        System.out.println("[5, 3, 5]");

        System.out.println("\nStep 2: Explore neighbors of (0,0)");
        System.out.println("Right neighbor (0,1): Height difference = |2-1| = 1, New effort = max(0,1) = 1");
        System.out.println("Down neighbor (1,0): Height difference = |3-1| = 2, New effort = max(0,2) = 2");
        System.out.println("Add both to priority queue: [(0,1,1), (1,0,2)]");

        System.out.println("\nStep 3: Process cell (0,1) with effort 1");
        System.out.println("Current grid:");
        System.out.println("[1, X, 2]");
        System.out.println("[3, 8, 2]");
        System.out.println("[5, 3, 5]");
        System.out.println("Explore neighbors: right (0,2) and down (1,1)");
        System.out.println("Right neighbor (0,2): Height difference = |2-2| = 0, New effort = max(1,0) = 1");
        System.out.println("Down neighbor (1,1): Height difference = |8-2| = 6, New effort = max(1,6) = 6");
        System.out.println("Priority queue: [(1,0,2), (0,2,1), (1,1,6)]");

        System.out.println("\nStep 4: Process cell (0,2) with effort 1");
        System.out.println("Current grid:");
        System.out.println("[1, 2, X]");
        System.out.println("[3, 8, 2]");
        System.out.println("[5, 3, 5]");
        System.out.println("Explore neighbors: down (1,2)");
        System.out.println("Down neighbor (1,2): Height difference = |2-2| = 0, New effort = max(1,0) = 1");
        System.out.println("Priority queue: [(1,0,2), (1,2,1), (1,1,6)]");

        System.out.println("\nStep 5: Process cell (1,2) with effort 1");
        System.out.println("Current grid:");
        System.out.println("[1, 2, 2]");
        System.out.println("[3, 8, X]");
        System.out.println("[5, 3, 5]");
        System.out.println("Explore neighbors: up (0,2), right (not valid), down (2,2), left (1,1)");
        System.out.println("Up neighbor (0,2): Already processed with effort 1");
        System.out.println("Down neighbor (2,2): Height difference = |5-2| = 3, New effort = max(1,3) = 3");
        System.out.println("Left neighbor (1,1): Height difference = |8-2| = 6, New effort = max(1,6) = 6");
        System.out.println("Priority queue: [(1,0,2), (2,2,3), (1,1,6)]");

        System.out.println("\nContinuing the process...");
        System.out.println("We eventually find the path with minimum effort: 2");
        System.out.println("One possible path: (0,0) -> (1,0) -> (2,0) -> (2,1) -> (2,2)");

        System.out.println("\nResult: The minimum effort required is 2.");
    }

    /**
     * Finds the minimum effort path from top-left to bottom-right of the grid
     *
     * @param heights Grid where heights[i][j] is the height of cell (i,j)
     * @return Minimum effort required to travel from top-left to bottom-right
     */
    public int minimumEffortPath(int[][] heights) {
        return dijkstra(heights);
    }

    /**
     * Dijkstra's algorithm implementation for minimum effort path
     *
     * @param heights Grid of heights
     * @return Minimum effort required
     */
    private int dijkstra(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Effort to reach each cell from the start
        // Initialize with infinity (or -1 to indicate unvisited)
        int[][] effortTo = new int[rows][cols];
        for (int[] row : effortTo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Priority queue to process cells in order of increasing effort
        PriorityQueue<State> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.effortFromStart, b.effortFromStart)
        );

        // Start from top-left cell (0,0)
        pq.offer(new State(0, 0, 0));

        // Direction arrays for moving up, right, down, left
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int row = state.row;
            int col = state.col;
            int effortSoFar = state.effortFromStart;

            // If we've reached the destination, return the effort
            if (row == rows - 1 && col == cols - 1) {
                return effortSoFar;
            }

            // Skip if we've found a better path to this cell
            if (effortTo[row][col] <= effortSoFar) {
                continue;
            }

            // Mark this cell as visited with current effort
            effortTo[row][col] = effortSoFar;

            // Explore the four adjacent cells
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new cell is within bounds
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                // Calculate the effort to reach the new cell
                // Effort is the maximum of current effort and the height difference
                int heightDiff = Math.abs(heights[newRow][newCol] - heights[row][col]);
                int newEffort = Math.max(effortSoFar, heightDiff);

                // If we've found a better path to the new cell, add it to the queue
                if (newEffort < effortTo[newRow][newCol]) {
                    pq.offer(new State(newRow, newCol, newEffort));
                }
            }
        }

        // This should not happen if the grid is connected
        return -1;
    }

    /**
     * State class to track a cell and its current effort from the start
     */
    static class State {
        // Current cell coordinates
        int row;
        int col;
        // Maximum height difference encountered so far
        int effortFromStart;

        public State(int row, int col, int effortFromStart) {
            this.row = row;
            this.col = col;
            this.effortFromStart = effortFromStart;
        }
    }
}