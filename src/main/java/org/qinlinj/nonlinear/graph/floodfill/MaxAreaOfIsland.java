package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

// @formatter:off
/**
 * Max Area of Island - Flood Fill Algorithm Implementation
 *
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - In this problem, we're finding islands (connected land cells) in a grid where:
 *   - 1 represents land
 *   - 0 represents water
 * - The "area" of an island is the number of connected land cells.
 * - We convert the 2D grid into a graph representation and use DFS to find connected components.
 *
 * Advantages:
 * - Graph Representation: Simplifies traversal by converting a 2D problem into a graph problem
 * - DFS Approach: Efficiently explores all connected cells in each island
 * - Modularity: Clear separation between graph construction and traversal
 * - Flexibility: Can be easily adapted for similar flood fill problems
 * - Intuitive: Models the real-world concept of islands as connected landmasses
 *
 * Visualization Example:
 * Consider a small 3x3 grid:
 *
 *   1 1 0
 *   1 0 0
 *   0 0 1
 *
 * This would be represented as a graph where:
 * - Vertex 0 (row=0, col=0) connects to vertices 1 and 3
 * - Vertex 1 (row=0, col=1) connects to vertex 0
 * - Vertex 3 (row=1, col=0) connects to vertex 0
 * - Vertex 8 (row=2, col=2) has no connections
 *
 * DFS traversal from vertex 0 visits vertices 0, 1, and 3, finding an island of area 3.
 * DFS traversal from vertex 8 finds an island of area 1.
 *
 * The maximum island area is therefore 3.
 */
public class MaxAreaOfIsland {
    // Number of rows in the grid
    private int rows;

    // Number of columns in the grid
    private int cols;

    // The original grid
    private int[][] grid;

    // Graph representation: adjacency list where each vertex is a cell in the grid
    // The index is (row * cols + col) and the value is the set of adjacent land cells
    private Set<Integer>[] graph;

    // Four possible directions: up, down, left, right
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Array to track visited cells during DFS
    private boolean[] visited;

    /**
     * Main method to demonstrate the Max Area of Island solution.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(R*C) for the graph representation and visited array
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example grid with multiple islands
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    /**
     * Find the maximum area of an island in the given grid.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(R*C) for the graph representation and visited array
     *
     * @param grid 2D grid where 1 represents land and 0 represents water
     * @return the maximum area of an island (maximum number of connected land cells)
     */
    public int maxAreaOfIsland(int[][] grid) {
        // Handle edge cases
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        // Convert the 2D grid to a graph representation
        graph = constructGraph();

        // Initialize visited array
        this.visited = new boolean[graph.length];
        int res = 0;

        // Iterate through all cells
        for (int v = 0; v < graph.length; v++) {
            // Convert vertex index back to row and column
            int row = v / cols;
            int col = v % cols;

            // If the cell is unvisited land, perform DFS to find the area of this island
            if (!visited[v] && grid[row][col] == 1) {
                res = Math.max(dfs(v), res);
            }
        }
        return res;
    }

    /**
     * Perform DFS traversal from a vertex to find the area of an island.
     *
     * Time Complexity: O(V+E) where V is the number of vertices and E is the number of edges
     * In the context of this problem, it's bounded by O(R*C) where R is rows and C is columns
     * Space Complexity: O(R*C) for the recursion stack in worst case
     *
     * @param v The current vertex (cell) being visited
     * @return The area of the island (number of connected land cells)
     */
    private int dfs(int v) {
        // Mark the current cell as visited
        visited[v] = true;

        // Start with area 1 (counting the current cell)
        int res = 1;

        // Explore all adjacent land cells
        for (int w : graph[v]) {
            if (!visited[w]) {
                // Add the area of the connected subisland
                res += dfs(w);
            }
        }
        return res;
    }

    /**
     * Convert the 2D grid into a graph representation.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(R*C) for the graph adjacency list
     *
     * @return An array of sets representing the adjacency list of the graph
     */
    private Set<Integer>[] constructGraph() {
        // Initialize the adjacency list
        Set<Integer>[] g = new HashSet[rows * cols];
        for (int v = 0; v < g.length; v++) {
            g[v] = new HashSet<>();
        }

        // For each cell in the grid
        for (int v = 0; v < g.length; v++) {
            // Convert vertex index to row and column
            int row = v / cols;
            int col = v % cols;

            // If the cell is land, check its neighbors
            if (grid[row][col] == 1) {
                // Check all four directions
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    // If the neighboring cell is within bounds and is land
                    if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == 1) {
                        // Convert row and column to vertex index
                        int w = nextRow * cols + nextCol;

                        // Add edges in both directions (undirected graph)
                        g[v].add(w);
                        g[w].add(v);
                    }
                }
            }
        }

        return g;
    }

    /**
     * Check if a given row and column are within the grid boundaries.
     *
     * Time Complexity: O(1) - constant time checks
     * Space Complexity: O(1) - no additional space used
     *
     * @param row The row index
     * @param col The column index
     * @return true if the cell is within the grid, false otherwise
     */
    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
