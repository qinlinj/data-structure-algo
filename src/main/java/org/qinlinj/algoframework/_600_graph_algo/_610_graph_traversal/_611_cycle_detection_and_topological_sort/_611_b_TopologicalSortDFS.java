package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._611_cycle_detection_and_topological_sort;

import java.util.*;

/**
 * Topological Sort Using DFS
 * <p>
 * Key Concepts:
 * 1. Topological Sort - A linear ordering of vertices in a directed acyclic graph (DAG) where for each directed
 * edge (u, v), vertex u comes before v in the ordering.
 * 2. Post-order Traversal - The reverse of a graph's post-order traversal gives a valid topological sort.
 * 3. Cycle Detection - Topological sort is only possible if the graph has no cycles.
 * <p>
 * Applications:
 * - Course scheduling
 * - Build systems (determining compilation order)
 * - Task scheduling with dependencies
 * - Data processing pipelines
 * <p>
 * This implementation solves LeetCode 210 (Course Schedule II) which asks for a valid course ordering
 * that satisfies all prerequisites (equivalent to finding a topological sort of the prerequisite graph).
 */
public class _611_b_TopologicalSortDFS {
    // Stores the post-order traversal result
    private List<Integer> postorder = new ArrayList<>();
    // Tracks if a cycle has been detected
    private boolean hasCycle = false;
    // Tracking arrays for cycle detection
    private boolean[] visited;
    private boolean[] onPath;

    // Example usage
    public static void main(String[] args) {
        _611_b_TopologicalSortDFS solution = new _611_b_TopologicalSortDFS();

        // Example 1: Simple prerequisite chain
        int[][] prerequisites1 = {{1, 0}};
        int[] order1 = solution.findOrder(2, prerequisites1);
        System.out.print("Course order: ");
        printArray(order1); // Expected: [0, 1]

        // Example 2: More complex dependency graph
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order2 = solution.findOrder(4, prerequisites2);
        System.out.print("Course order: ");
        printArray(order2); // Expected something like: [0, 1, 2, 3] or [0, 2, 1, 3]

        // Example 3: Cyclic dependencies (impossible)
        int[][] prerequisites3 = {{1, 0}, {0, 1}};
        int[] order3 = solution.findOrder(2, prerequisites3);
        System.out.print("Course order (with cycle): ");
        printArray(order3); // Expected: [] (empty array)
    }

    private static void printArray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build the graph from prerequisites
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        // Initialize tracking arrays
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        // Perform DFS on each node
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        // If a cycle exists, topological sort is impossible
        if (hasCycle) {
            return new int[]{};
        }

        // Reverse the post-order traversal to get topological sort
        Collections.reverse(postorder);

        // Convert list to array
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = postorder.get(i);
        }

        return result;
    }

    /**
     * DFS traversal for topological sort and cycle detection
     */
    private void traverse(List<Integer>[] graph, int s) {
        // Check for cycle
        if (onPath[s]) {
            hasCycle = true;
            return;
        }

        // Skip if already visited or cycle detected
        if (visited[s] || hasCycle) {
            return;
        }

        // Mark node as visited and on current path
        visited[s] = true;
        onPath[s] = true;

        // Visit all neighbors
        for (int neighbor : graph[s]) {
            traverse(graph, neighbor);
        }

        // Post-order: add to result after processing all neighbors
        postorder.add(s);

        // Remove from current path
        onPath[s] = false;
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