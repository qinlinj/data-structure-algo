package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Nearest Exit from Entrance in Maze (LeetCode 1926)
 * ------------------------------------------------
 * <p>
 * Summary:
 * This problem gives us a maze represented as a 2D grid. We start from an entrance position
 * and need to find the shortest path to any exit (a cell at the boundary of the maze).
 * The entrance itself is not considered an exit.
 * <p>
 * Key Concepts:
 * 1. BFS to find the shortest path in an unweighted graph
 * 2. Grid traversal with four possible moves (up, down, left, right)
 * 3. Tracking the number of steps taken
 * 4. Checking boundary conditions for exits
 * <p>
 * Approach:
 * - Use BFS starting from the entrance
 * - Explore in four directions: up, down, left, right
 * - Track visited cells to avoid revisiting
 * - Count the number of steps
 * - Return when we find an exit at the boundary
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the maze
 * Space Complexity: O(m*n) for the visited array and the queue
 */

import java.util.*;

public class _752_i_NearestExit {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_i_NearestExit solution = new _752_i_NearestExit();

        // Example 1: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
        char[][] maze1 = {
                {'+', '+', '.', '+'},
                {'.', '.', '.', '+'},
                {'+', '+', '+', '.'}
        };
        int[] entrance1 = {1, 2};

        int result1 = solution.nearestExit(maze1, entrance1);
        System.out.println("Example 1: " + result1); // Should output 1

        // Example 2: maze = [["+","+","+"],[".",".","."],["+"],["+","+"]], entrance = [1,0]
        char[][] maze2 = {
                {'+', '+', '+'},
                {'.', '.', '.'},
                {'+', '+', '+'}
        };
        int[] entrance2 = {1, 0};

        int result2 = solution.nearestExit(maze2, entrance2);
        System.out.println("Example 2: " + result2); // Should output 2

        // Example 3: maze = [[".","+"]], entrance = [0,0]
        char[][] maze3 = {
                {'.', '+'}
        };
        int[] entrance3 = {0, 0};

        int result3 = solution.nearestExit(maze3, entrance3);
        System.out.println("Example 3: " + result3); // Should output -1

        // Example 4: A more complex maze
        char[][] maze4 = {
                {'+', '+', '+', '+', '+'},
                {'.', '.', '.', '.', '+'},
                {'+', '+', '+', '.', '+'},
                {'+', '.', '.', '.', '.'},
                {'+', '+', '+', '+', '+'}
        };
        int[] entrance4 = {1, 0};

        int result4 = solution.nearestExit(maze4, entrance4);
        System.out.println("Example 4: " + result4); // Should output 3
    }

    /**
     * Find the nearest exit from the entrance in a maze
     * @param maze 2D grid representing the maze ('.' is empty, '+' is wall)
     * @param entrance Starting position [row, col]
     * @return Number of steps to the nearest exit or -1 if not possible
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Four possible directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS implementation
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // Start from the entrance
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                // If we've reached an exit (not the entrance)
                if ((row == 0 || row == rows - 1 || col == 0 || col == cols - 1) &&
                        !(row == entrance[0] && col == entrance[1])) {
                    return steps;
                }

                // Explore all four directions
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check bounds and if the cell is valid and unvisited
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                            maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {

                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }

            // Increment steps after processing each level
            steps++;
        }

        // If we exit the loop, no exit was found
        return -1;
    }
}