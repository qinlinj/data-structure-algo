package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._171_algo_framework;

import java.util.*;

/**
 * Tree and Graph Traversal Algorithms Summary
 * <p>
 * Key Concepts:
 * <p>
 * 1. Algorithm Foundations:
 * - DFS/Backtracking: Essentially recursive traversal of an enumeration tree
 * - BFS: Fundamentally traversal of a graph
 * - Both derive from binary tree traversal algorithms
 * <p>
 * 2. Relationships between algorithms:
 * - DFS/Backtracking → Multi-way tree recursive traversal → Binary tree recursive traversal
 * - BFS → Graph traversal → Multi-way tree traversal with visited array → Binary tree level-order traversal
 * <p>
 * 3. BFS for Shortest Path:
 * - BFS is often used for finding shortest paths because it explores nodes level by level
 * - Similar to finding the minimum depth in a binary tree
 * - Recursive traversal must visit all nodes, while level-order traversal can find the target earlier
 * <p>
 * 4. Problem Abstraction:
 * - Real-world problems require abstracting specific scenarios into standard graph/tree structures
 * - Examples: maze paths, word transformations, connecting-blocks games
 * <p>
 * 5. BFS Framework:
 * - Multiple implementation approaches with increasing complexity and flexibility
 * - Most common approach uses a queue with step counting
 */
public class GraphTraversalAlgorithms {
    // Generic graph representation (adjacency list)
    private Map<Integer, List<Integer>> graph;

    public GraphTraversalAlgorithms() {
        graph = new HashMap<>();
    }

    /**
     * BFS algorithm framework for finding shortest path.
     * <p>
     * This is the standard implementation for BFS that:
     * 1. Uses a queue to track nodes to visit
     * 2. Maintains a visited array to prevent cycles
     * 3. Counts steps to reach the target
     *
     * @param start  Starting node
     * @param target Target node to find
     * @return Minimum steps to reach target, or -1 if unreachable
     */
    public int bfs(int start, int target) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        // Initialize with start node
        queue.offer(start);
        visited[start] = true;

        // Track steps from start to current position
        int step = 0;

        while (!queue.isEmpty()) {
            // Process nodes level by level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                System.out.println("Visiting node " + current + " at step " + step);

                // Check if target found
                if (current == target) {
                    return step;
                }

                // Explore neighbors (expand search in all directions)
                for (int neighbor : getNeighbors(current)) {
                    if (!visited[neighbor]) {
                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
            // Move to next level
            step++;
        }

        // Target not found
        return -1;
    }

    /**
     * Example application: Solving a maze
     * <p>
     * We can abstract a maze as a grid where:
     * - Each cell is a node
     * - Adjacent walkable cells are connected nodes
     * - BFS finds shortest path from start to exit
     */
    public int solveMaze(int[][] maze, int[] start, int[] exit) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Mark visited cells
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Add start position
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                // Check if reached exit
                if (current[0] == exit[0] && current[1] == exit[1]) {
                    return steps;
                }

                // Try all four directions
                for (int[] dir : directions) {
                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];

                    // Check if valid move (in bounds, not a wall, not visited)
                    if (isValidCell(maze, newRow, newCol, rows, cols) &&
                            maze[newRow][newCol] == 0 &&
                            !visited[newRow][newCol]) {

                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
            steps++;
        }

        // No path found
        return -1;
    }

    /**
     * Example application: Word transformation
     * <p>
     * We can abstract word transformation as:
     * - Words are nodes
     * - Words with one character difference are connected
     * - BFS finds shortest transformation path
     */
    public int wordTransformation(String beginWord, String endWord, List<String> wordList) {
        // Create a set for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordList);

        // Check if end word exists
        if (!wordSet.contains(endWord)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord); // Mark as visited

        int steps = 1;  // Start at 1 since we count transformations

        return -1;
    }

    private boolean isValidCell(int[][] maze, int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * DFS recursive traversal example
     * Used for exhaustive search, backtracking
     */
    public void dfs(int node, boolean[] visited) {
        // Mark current node as visited
        visited[node] = true;
        System.out.println("Visiting node " + node);

        // Recursively visit all unvisited neighbors
        for (int neighbor : getNeighbors(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // Helper methods
    private List<Integer> getNeighbors(int node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }
}
