package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

import java.util.*;

/**
 * Bipartite Graph Detection using BFS
 * <p>
 * This class implements the Breadth-First Search (BFS) approach to determine
 * if a graph is bipartite. While the DFS approach works recursively by exploring
 * as far as possible along each branch, BFS explores the graph level by level.
 * <p>
 * Algorithm:
 * 1. Use a queue to process nodes in BFS order
 * 2. Color the starting node and enqueue it
 * 3. For each node dequeued, check all its neighbors:
 * - If a neighbor is uncolored, assign the opposite color and enqueue it
 * - If a neighbor is already colored, verify it has the opposite color
 * 4. Repeat for each component of the graph
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the queue and color/visited arrays
 * <p>
 * This implementation also solves LeetCode Problem 785: Is Graph Bipartite?
 */
public class _613_d_BipartiteGraphBFS {

    /**
     * Main method to demonstrate the bipartite checking with BFS
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("BIPARTITE GRAPH DETECTION USING BFS");
        System.out.println("===================================");

        // Example 1: A bipartite graph
        // Can be colored as [0, 1, 0, 1] where 0 and 1 represent different colors
        int[][] graph1 = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        System.out.println("\nExample 1: Bipartite Graph");
        System.out.println("Graph structure:");
        System.out.println("0 -- 1");
        System.out.println("|    |");
        System.out.println("3 -- 2");
        System.out.println();

        boolean result1 = solution.isBipartite(graph1);
        System.out.println("Is bipartite? " + result1);
        System.out.println("This graph can be colored with two colors where nodes [0,2] have one color");
        System.out.println("and nodes [1,3] have another color.");

        // Reset solution for next example
        solution = new Solution();

        // Example 2: A non-bipartite graph with a triangle (odd cycle)
        int[][] graph2 = {
                {1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2}
        };

        System.out.println("\nExample 2: Non-Bipartite Graph (Contains a Triangle)");
        System.out.println("Graph structure:");
        System.out.println("    0");
        System.out.println("   /|\\");
        System.out.println("  / | \\");
        System.out.println(" 1--2  3");
        System.out.println();

        boolean result2 = solution.isBipartite(graph2);
        System.out.println("Is bipartite? " + result2);
        System.out.println("This graph cannot be bipartite because it contains an odd cycle (triangle 0-1-2).");

        // Reset solution for next example
        solution = new Solution();

        // Example 3: A disconnected bipartite graph with multiple components
        int[][] graph3 = {
                {4, 7},
                {5, 6},
                {5, 6},
                {7},
                {0},
                {1, 2},
                {1, 2},
                {0, 3}
        };

        System.out.println("\nExample 3: Disconnected Bipartite Graph");
        System.out.println("Graph structure (3 components):");
        System.out.println("0 -- 4    1 -- 5 -- 2    3 -- 7");
        System.out.println("|              |              |");
        System.out.println("7              6              0");
        System.out.println();

        boolean result3 = solution.isBipartite(graph3);
        System.out.println("Is bipartite? " + result3);
        System.out.println("This is a bipartite graph because each component can be properly two-colored.");

        System.out.println("\nComparing BFS vs DFS for Bipartite Checking:");
        System.out.println("1. Both have the same time complexity: O(V + E)");
        System.out.println("2. Both have the same space complexity: O(V)");
        System.out.println("3. BFS explores level-by-level, which may be more intuitive for 'coloring'");
        System.out.println("4. DFS uses recursion which may be cleaner to implement in some cases");
        System.out.println("5. For detecting odd cycles (which make a graph non-bipartite), either approach works");
        System.out.println("6. BFS might detect conflicts earlier in some cases by exploring 'nearer' nodes first");
    }

    /**
     * Solution class implementing the BFS-based bipartite check
     */
    public static class Solution {
        // Flag to indicate whether the graph is bipartite
        private boolean isBipartite = true;

        // Color array: false and true represent two different colors
        private boolean[] color;

        // Visited array to track visited nodes
        private boolean[] visited;

        /**
         * Main function to check if a graph is bipartite
         *
         * @param graph Adjacency list representation of the graph
         * @return true if the graph is bipartite, false otherwise
         */
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            color = new boolean[n];
            visited = new boolean[n];

            // Check each connected component
            for (int vertex = 0; vertex < n; vertex++) {
                if (!visited[vertex]) {
                    bfs(graph, vertex);
                    // If we've already determined the graph isn't bipartite, no need to continue
                    if (!isBipartite) {
                        return false;
                    }
                }
            }

            return isBipartite;
        }

        /**
         * BFS traversal with coloring
         *
         * @param graph Adjacency list representation of the graph
         * @param start Starting vertex for BFS
         */
        private void bfs(int[][] graph, int start) {
            Queue<Integer> queue = new LinkedList<>();

            // Mark the starting vertex as visited and enqueue it
            visited[start] = true;
            queue.offer(start);

            while (!queue.isEmpty() && isBipartite) {
                int vertex = queue.poll();

                // Check all adjacent vertices
                for (int neighbor : graph[vertex]) {
                    if (!visited[neighbor]) {
                        // Assign the opposite color to the neighbor
                        color[neighbor] = !color[vertex];
                        // Mark as visited and enqueue
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    } else {
                        // If the neighbor is already colored, check for color conflicts
                        if (color[neighbor] == color[vertex]) {
                            // Found adjacent vertices with the same color - not bipartite
                            isBipartite = false;
                            return;
                        }
                    }
                }
            }
        }
    }
}