package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

import java.util.*;

/**
 * Graph Traversal Algorithms for Bipartite Graph Detection
 * <p>
 * This class demonstrates the basic graph traversal frameworks (DFS and BFS)
 * that can be adapted to determine whether a graph is bipartite.
 * <p>
 * Key Concepts:
 * 1. Graph traversal with cycle detection
 * 2. Using visited array to prevent revisiting nodes
 * 3. Different traversal styles (DFS vs BFS)
 * 4. Adapting traversal frameworks for specific problems
 */
public class _613_b_BipartiteGraphTraversal {

    /**
     * Basic DFS graph traversal template
     */
    public static class BasicDFSTraversal {
        private boolean[] visited;

        public void traverse(List<Integer>[] graph, int v) {
            // Prevent cycles by checking if already visited
            if (visited[v]) return;

            // Mark node as visited (pre-order position)
            visited[v] = true;
            System.out.println("Visiting node: " + v);

            // Recursively visit all neighbors
            for (int neighbor : graph[v]) {
                traverse(graph, neighbor);
            }
        }

        public void traverseGraph(List<Integer>[] graph) {
            int n = graph.length;
            visited = new boolean[n];

            // Handle disconnected components by trying all possible starting nodes
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    traverse(graph, i);
                }
            }
        }
    }

    /**
     * Alternative DFS traversal style that checks visited status before recursion
     */
    public static class AlternativeDFSTraversal {
        private boolean[] visited;

        public void traverse(List<Integer>[] graph, int v) {
            // Mark node as visited immediately
            visited[v] = true;
            System.out.println("Visiting node: " + v);

            // Only visit unvisited neighbors
            for (int neighbor : graph[v]) {
                if (!visited[neighbor]) {
                    traverse(graph, neighbor);
                }
            }
        }

        public void traverseGraph(List<Integer>[] graph) {
            int n = graph.length;
            visited = new boolean[n];

            // Handle disconnected components by trying all possible starting nodes
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    traverse(graph, i);
                }
            }
        }
    }

    /**
     * BFS graph traversal template
     */
    public static class BFSTraversal {
        private boolean[] visited;

        public void traverse(List<Integer>[] graph, int start) {
            Queue<Integer> queue = new LinkedList<>();

            // Add starting node to queue and mark as visited
            visited[start] = true;
            queue.offer(start);

            while (!queue.isEmpty()) {
                int v = queue.poll();
                System.out.println("Visiting node: " + v);

                // Process all neighbors
                for (int neighbor : graph[v]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }

        public void traverseGraph(List<Integer>[] graph) {
            int n = graph.length;
            visited = new boolean[n];

            // Handle disconnected components by trying all possible starting nodes
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    traverse(graph, i);
                }
            }
        }
    }

    /**
     * Example of how to adapt graph traversal for bipartite checking
     */
    public static class BipartiteCheckingTemplate {
        private boolean[] visited;
        private boolean[] color;
        private boolean isBipartite = true;

        public void traverse(List<Integer>[] graph, int v) {
            // If we've already determined this isn't a bipartite graph, stop
            if (!isBipartite) return;

            visited[v] = true;

            // Process all neighbors
            for (int neighbor : graph[v]) {
                if (!visited[neighbor]) {
                    // Assign opposite color to neighbor
                    color[neighbor] = !color[v];
                    traverse(graph, neighbor);
                } else {
                    // If neighbor is already visited, check if colors conflict
                    if (color[neighbor] == color[v]) {
                        isBipartite = false;
                        return;
                    }
                }
            }
        }
    }
}