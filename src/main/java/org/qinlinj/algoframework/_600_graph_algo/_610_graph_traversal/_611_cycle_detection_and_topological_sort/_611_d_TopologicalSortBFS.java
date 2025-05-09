package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._611_cycle_detection_and_topological_sort;

import java.util.*;

/**
 * Topological Sort Using BFS (Kahn's Algorithm)
 * <p>
 * Key Concepts:
 * 1. In-degree Based Approach - Using the in-degree of nodes to determine processing order
 * 2. Kahn's Algorithm - A BFS-based algorithm for topological sorting
 * 3. Level-by-level Processing - Nodes are processed in "waves" based on their dependency depth
 * <p>
 * Algorithm Steps:
 * 1. Calculate in-degree for each node
 * 2. Start with nodes having zero in-degree (no dependencies)
 * 3. Process these nodes and decrease in-degree of their neighbors
 * 4. Add new nodes with zero in-degree to the queue
 * 5. Repeat until queue is empty
 * 6. If all nodes processed, return the ordering; otherwise, a cycle exists
 * <p>
 * Advantages Over DFS:
 * - More intuitive representation of dependency levels
 * - Easier to parallelize (nodes at the same "level" can be processed simultaneously)
 * - Can produce a more balanced ordering when multiple valid orderings exist
 * <p>
 * This implementation solves LeetCode 210 (Course Schedule II) using BFS.
 */
public class _611_d_TopologicalSortBFS {

    // Example usage
    public static void main(String[] args) {
        _611_d_TopologicalSortBFS solution = new _611_d_TopologicalSortBFS();

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

        // Array to store the topological order
        int[] result = new int[numCourses];
        int index = 0;

        // Process nodes in BFS order
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // Add current node to the result
            result[index++] = current;

            // Decrease in-degree of all adjacent nodes
            for (int neighbor : graph[current]) {
                inDegree[neighbor]--;

                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If not all nodes were processed, a cycle exists
        if (index != numCourses) {
            return new int[0]; // Return empty array to indicate impossibility
        }

        return result;
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