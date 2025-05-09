package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._611_cycle_detection_and_topological_sort;

import java.util.*;

/**
 * Graph Cycle Detection Using DFS
 * <p>
 * Key Concepts:
 * 1. Cycle Detection - Identifying if a directed graph contains any cycles (crucial for dependency management)
 * 2. DFS Traversal - Using depth-first search to explore all paths in a graph
 * 3. Path Tracking - Using onPath array to track nodes in the current recursive stack
 * 4. Optimization - Using visited array to avoid redundant traversals
 * <p>
 * Real-world Applications:
 * - Course prerequisite verification
 * - Dependency resolution in build systems
 * - Deadlock detection in resource allocation
 * <p>
 * This implementation solves LeetCode 207 (Course Schedule) which asks whether all courses can be completed
 * given their prerequisites, equivalent to checking if the prerequisite graph has no cycles.
 */
public class _611_a_CycleDetectionDFS {
    // Tracks nodes in the current recursive path
    private boolean[] onPath;
    // Tracks nodes that have been fully processed
    private boolean[] visited;
    // Flag to indicate if a cycle has been found
    private boolean hasCycle = false;

    // Example usage
    public static void main(String[] args) {
        _611_a_CycleDetectionDFS solution = new _611_a_CycleDetectionDFS();

        // Example 1: No cycle
        int[][] prerequisites1 = {{1, 0}};
        boolean result1 = solution.canFinish(2, prerequisites1);
        System.out.println("Can finish all courses (no cycle): " + result1); // Expected: true

        // Example 2: Has cycle
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = solution.canFinish(2, prerequisites2);
        System.out.println("Can finish all courses (has cycle): " + result2); // Expected: false
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build the directed graph from the prerequisites
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        // Initialize tracking arrays
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];

        // Check for cycles starting from each node
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        // Return true if no cycle is found
        return !hasCycle;
    }

    /**
     * DFS traversal to detect cycles
     *
     * @param graph The adjacency list representation of the graph
     * @param s     The current node being visited
     */
    private void traverse(List<Integer>[] graph, int s) {
        if (hasCycle) {
            // If a cycle is already found, no need to continue
            return;
        }

        if (onPath[s]) {
            // If the current node is already on our path, we found a cycle
            hasCycle = true;
            return;
        }

        if (visited[s]) {
            // If we've already processed this node, no need to revisit
            return;
        }

        // Mark the current node as visited and on the current path
        visited[s] = true;
        onPath[s] = true;

        // Visit all neighbors
        for (int neighbor : graph[s]) {
            traverse(graph, neighbor);
        }

        // After exploring all paths from this node, remove it from the current path
        onPath[s] = false;
    }

    /**
     * Builds an adjacency list representation of the directed graph
     *
     * @param numCourses    Number of nodes in the graph
     * @param prerequisites Edges in the graph, where [a,b] means "b is a prerequisite for a"
     * @return Adjacency list representation of the graph
     */
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // Initialize the adjacency list
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        // Add edges to the graph
        for (int[] edge : prerequisites) {
            int from = edge[1]; // prerequisite course
            int to = edge[0];   // dependent course
            // Add a directed edge from -> to
            graph[from].add(to);
        }

        return graph;
    }
}