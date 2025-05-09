package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._611_cycle_detection_and_topological_sort;

import java.util.*;

/**
 * Graph Cycle Detection Using BFS
 * <p>
 * Key Concepts:
 * 1. In-degree Based Approach - Using the number of incoming edges (in-degrees) to detect cycles
 * 2. Kahn's Algorithm - A BFS-based algorithm for topological sorting that can also detect cycles
 * 3. Topological Properties - If a directed graph has a cycle, not all nodes will be processed in a topological sort
 * <p>
 * How It Works:
 * - Start with nodes that have zero in-degree (no dependencies)
 * - Remove these nodes and their outgoing edges from the graph
 * - This decreases the in-degree of adjacent nodes
 * - Continue until either all nodes are processed (no cycle) or some nodes remain with non-zero in-degree (cycle exists)
 * <p>
 * Applications:
 * - Deadlock detection in operating systems
 * - Build dependency verification in software
 * - Constraint satisfaction problems
 * <p>
 * This implementation solves LeetCode 207 (Course Schedule) using BFS instead of DFS.
 */
public class _611_c_CycleDetectionBFS {

    // Example usage
    public static void main(String[] args) {
        _611_c_CycleDetectionBFS solution = new _611_c_CycleDetectionBFS();

        // Example 1: No cycle
        int[][] prerequisites1 = {{1, 0}};
        boolean result1 = solution.canFinish(2, prerequisites1);
        System.out.println("Can finish all courses (no cycle): " + result1); // Expected: true

        // Example 2: Has cycle
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = solution.canFinish(2, prerequisites2);
        System.out.println("Can finish all courses (has cycle): " + result2); // Expected: false

        // Example 3: Complex graph without cycle
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        boolean result3 = solution.canFinish(4, prerequisites3);
        System.out.println("Can finish all courses (complex graph, no cycle): " + result3); // Expected: true
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list representation of the graph
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        // Calculate in-degree for each node
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1]; // prerequisite course
            int to = edge[0];   // dependent course
            // Increase in-degree of the dependent course
            inDegree[to]++;
        }

        // Initialize queue with all nodes that have no dependencies (in-degree = 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Counter for visited nodes
        int visited = 0;

        // Process nodes in BFS order
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited++;

            // Decrease in-degree of all adjacent nodes
            for (int neighbor : graph[current]) {
                inDegree[neighbor]--;

                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all nodes were visited, no cycle exists
        return visited == numCourses;
    }

    /**
     * Builds an adjacency list representation of the directed graph
     */
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1]; // prerequisite course
            int to = edge[0];   // dependent course
            // Add a directed edge from -> to
            graph[from].add(to);
        }

        return graph;
    }
}