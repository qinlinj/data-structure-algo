package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II;
/**
 * Pacific Atlantic Water Flow (LeetCode 417)
 * ----------------------------------------
 * <p>
 * Summary:
 * This problem involves a matrix representing heights of land with the Pacific Ocean on the top and left
 * edges, and the Atlantic Ocean on the bottom and right edges. Water can flow from a cell to adjacent
 * cells with equal or lower height. The task is to find all cells from which water can flow to both oceans.
 * <p>
 * Key Concepts:
 * 1. Reverse thinking: Start from ocean boundaries and work inward
 * 2. Multiple-source BFS from both oceans simultaneously
 * 3. Working backward to find cells that can reach each ocean
 * 4. Finding the intersection of reachable cells
 * <p>
 * Approach:
 * - Instead of tracking water flow from each cell (which would be inefficient),
 * we work backward from the ocean boundaries
 * - Perform BFS from all Pacific Ocean boundary cells to find all cells that can flow to Pacific
 * - Perform BFS from all Atlantic Ocean boundary cells to find all cells that can flow to Atlantic
 * - The intersection of these two sets is our answer
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the matrix
 * Space Complexity: O(m*n) for the visited arrays and queue
 */

import java.util.*;

public class _753_e_PacificAtlanticWaterFlow {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_e_PacificAtlanticWaterFlow solution = new _753_e_PacificAtlanticWaterFlow();

        // Example 1: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
        int[][] heights1 = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        List<List<Integer>> result1 = solution.pacificAtlantic(heights1);
        System.out.println("Example 1 (BFS): " + result1);

        // Example 2: heights = [[2,1],[1,2]]
        int[][] heights2 = {
                {2, 1},
                {1, 2}
        };

        List<List<Integer>> result2 = solution.pacificAtlantic(heights2);
        System.out.println("Example 2 (BFS): " + result2);

        // Comparing with DFS implementation
        List<List<Integer>> result1DFS = solution.pacificAtlanticDFS(heights1);
        System.out.println("Example 1 (DFS): " + result1DFS);

        List<List<Integer>> result2DFS = solution.pacificAtlanticDFS(heights2);
        System.out.println("Example 2 (DFS): " + result2DFS);
    }

    /**
     * Find all cells from which water can flow to both oceans
     * @param heights Matrix representing heights of land
     * @return List of coordinates [row, col] that can flow to both oceans
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Track cells that can reach each ocean
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // Add all edge cells to the respective queues
        // Pacific Ocean borders (top and left edges)
        for (int i = 0; i < rows; i++) {
            pacificQueue.offer(new int[]{i, 0});
            pacificReachable[i][0] = true;

            atlanticQueue.offer(new int[]{i, cols - 1});
            atlanticReachable[i][cols - 1] = true;
        }

        for (int j = 0; j < cols; j++) {
            pacificQueue.offer(new int[]{0, j});
            pacificReachable[0][j] = true;

            atlanticQueue.offer(new int[]{rows - 1, j});
            atlanticReachable[rows - 1][j] = true;
        }

        // BFS from Pacific Ocean boundaries
        bfs(heights, pacificQueue, pacificReachable);

        // BFS from Atlantic Ocean boundaries
        bfs(heights, atlanticQueue, atlanticReachable);

        // Find cells that can reach both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    /**
     * BFS helper method to find all cells that can reach a particular ocean
     * @param heights Height matrix
     * @param queue Queue initialized with ocean boundary cells
     * @param reachable Boolean matrix to track cells that can reach this ocean
     */
    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // Try all four directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new cell is valid, not visited, and water can flow from it
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && !reachable[newRow][newCol]
                        && heights[newRow][newCol] >= heights[row][col]) {

                    // Mark as reachable and add to queue
                    reachable[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    /**
     * Alternative DFS implementation
     */
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];

        // DFS from every cell on the Pacific borders
        for (int i = 0; i < rows; i++) {
            dfs(heights, i, 0, pacificReachable, Integer.MIN_VALUE);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, 0, j, pacificReachable, Integer.MIN_VALUE);
        }

        // DFS from every cell on the Atlantic borders
        for (int i = 0; i < rows; i++) {
            dfs(heights, i, cols - 1, atlanticReachable, Integer.MIN_VALUE);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, rows - 1, j, atlanticReachable, Integer.MIN_VALUE);
        }

        // Find cells that can reach both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    /**
     * DFS helper method
     */
    private void dfs(int[][] heights, int row, int col, boolean[][] reachable, int prevHeight) {
        int rows = heights.length;
        int cols = heights[0].length;

        // Check if out of bounds, already visited, or water can't flow from here
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || reachable[row][col] || heights[row][col] < prevHeight) {
            return;
        }

        // Mark as reachable
        reachable[row][col] = true;

        // Try all four directions
        dfs(heights, row + 1, col, reachable, heights[row][col]);
        dfs(heights, row - 1, col, reachable, heights[row][col]);
        dfs(heights, row, col + 1, reachable, heights[row][col]);
        dfs(heights, row, col - 1, reachable, heights[row][col]);
    }
}