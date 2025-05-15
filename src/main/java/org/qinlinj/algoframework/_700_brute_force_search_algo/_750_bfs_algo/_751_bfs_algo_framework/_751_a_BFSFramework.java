package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._751_bfs_algo_framework; /**
 * BFS Algorithm Framework
 * ----------------------
 * <p>
 * Summary:
 * BFS (Breadth-First Search) is an algorithm for traversing or searching tree or graph data structures.
 * Unlike DFS which explores as far as possible before backtracking, BFS explores all neighbor nodes
 * at the present depth before moving to nodes at the next depth level.
 * <p>
 * Key Concepts:
 * 1. BFS is essentially a tree/graph traversal algorithm
 * 2. It's particularly useful for finding the shortest path in unweighted graphs
 * 3. It uses a queue data structure to track nodes to be explored
 * 4. A visited set is needed to prevent cycles
 * <p>
 * Common Applications:
 * - Finding shortest paths in graphs
 * - Solving puzzles and games
 * - Web crawling
 * - Social networking friend suggestions
 * <p>
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the queue and visited set
 */

import java.util.*;

public class _751_a_BFSFramework {

    // A generic example of a graph representation
    private List<List<Integer>> graph;

    public _751_a_BFSFramework(int vertices) {
        graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
    }

    // Example usage
    public static void main(String[] args) {
        _751_a_BFSFramework g = new _751_a_BFSFramework(6);

        // Create a simple graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);

        // Find shortest path from 0 to 4
        int steps = g.bfs(0, 4);
        System.out.println("Shortest path from 0 to 4: " + steps + " steps");
    }

    // Add an edge to the graph
    public void addEdge(int from, int to) {
        graph.get(from).add(to);
    }

    // Get neighbors of a node
    private List<Integer> neighborsOf(int node) {
        return graph.get(node);
    }

    /**
     * Standard BFS framework to find the shortest path from start to target
     *
     * @param start  The starting node
     * @param target The target node we're searching for
     * @return The shortest distance (number of steps) or -1 if not found
     */
    public int bfs(int start, int target) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from the starting node
        queue.offer(start);
        visited[start] = true;

        // Track the steps/distance from start
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                System.out.println("visit " + current + " at step " + step);

                // Check if we've reached the target
                if (current == target) {
                    return step;
                }

                // Add unvisited neighbors to the queue
                for (int neighbor : neighborsOf(current)) {
                    if (!visited[neighbor]) {
                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }

            // Move to the next level
            step++;
        }

        // Target not found
        return -1;
    }
}