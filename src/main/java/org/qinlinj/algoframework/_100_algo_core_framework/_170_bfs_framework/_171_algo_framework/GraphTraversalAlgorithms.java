package org.qinlinj.algoframework._100_algo_core_framework._170_bfs_framework._171_algo_framework;

import java.util.*;

public class GraphTraversalAlgorithms {

    // Generic graph representation (adjacency list)
    private Map<Integer, List<Integer>> graph;

    public GraphTraversalAlgorithms() {
        graph = new HashMap<>();
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // Example: Create a simple graph
        GraphTraversalAlgorithms graph = new GraphTraversalAlgorithms();

        // Add some nodes and edges
        for (int i = 0; i < 6; i++) {
            graph.addEdge(i, (i + 1) % 6);  // Create a cycle 0->1->2->3->4->5->0
        }
        graph.addEdge(0, 3);  // Add a shortcut

        // Find shortest path from 0 to 3
        int shortestPath = graph.bfs(0, 3);
        System.out.println("Shortest path from 0 to 3: " + shortestPath + " steps");

        // Example maze solving
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 0};
        int[] exit = {4, 4};

        int mazeSteps = graph.solveMaze(maze, start, exit);
        System.out.println("Steps to solve maze: " + mazeSteps);
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

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // Try changing each character
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // Try all possible characters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;

                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // Found target word
                        if (newWord.equals(endWord)) {
                            return steps;
                        }

                        // Valid transformation
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // Mark as visited
                        }
                    }

                    // Restore original character
                    wordChars[j] = originalChar;
                }
            }
            steps++;
        }

        return -1;
    }

    // Helper methods

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

    private List<Integer> getNeighbors(int node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }

    private boolean isValidCell(int[][] maze, int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Example of adding edges to the graph
    public void addEdge(int from, int to) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        graph.computeIfAbsent(to, k -> new ArrayList<>());  // Ensure the node exists
    }
}
