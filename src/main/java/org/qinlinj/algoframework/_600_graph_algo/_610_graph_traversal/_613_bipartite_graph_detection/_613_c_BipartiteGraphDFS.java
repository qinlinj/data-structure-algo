package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

/**
 * Bipartite Graph Detection using DFS
 * <p>
 * This class implements the Depth-First Search (DFS) approach to determine
 * if a graph is bipartite. A graph is bipartite if its vertices can be divided
 * into two disjoint sets such that no vertices within the same set are adjacent.
 * <p>
 * Algorithm:
 * 1. Use two arrays: one to track visited nodes and one to track colors
 * 2. Perform DFS from each unvisited node (to handle disconnected components)
 * 3. During traversal, color each node the opposite color of its parent
 * 4. If we encounter an already colored node that conflicts with the expected color,
 * the graph is not bipartite
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the recursion stack and color/visited arrays
 * <p>
 * This implementation solves LeetCode Problem 785: Is Graph Bipartite?
 */
public class _613_c_BipartiteGraphDFS {

    /**
     * Main method to demonstrate the bipartite checking with DFS
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("BIPARTITE GRAPH DETECTION USING DFS");
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
        System.out.println("This is correct because we can color nodes [0,2] with one color and [1,3] with another.");

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
        System.out.println("This is correct because nodes 0-1-2 form a triangle (odd cycle),");
        System.out.println("making it impossible to color with just two colors.");

        // Reset solution for next example
        solution = new Solution();

        // Example 3: A disconnected bipartite graph
        int[][] graph3 = {
                {1},
                {0},
                {3},
                {2},
                {5},
                {4}
        };

        System.out.println("\nExample 3: Disconnected Bipartite Graph");
        System.out.println("Graph structure:");
        System.out.println("0 -- 1    2 -- 3    4 -- 5");
        System.out.println();

        boolean result3 = solution.isBipartite(graph3);
        System.out.println("Is bipartite? " + result3);
        System.out.println("This is correct because each component can be properly two-colored.");

        System.out.println("\nKey Implementation Points:");
        System.out.println("1. We use boolean arrays for both visited status and coloring");
        System.out.println("2. We check each component separately to handle disconnected graphs");
        System.out.println("3. We terminate early if we detect any conflict");
        System.out.println("4. Adjacent nodes must have different colors for the graph to be bipartite");
    }

    /**
     * Solution class implementing the DFS-based bipartite check
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
                    dfs(graph, vertex);
                }
            }

            return isBipartite;
        }

        /**
         * DFS traversal with coloring
         *
         * @param graph  Adjacency list representation of the graph
         * @param vertex Current vertex being processed
         */
        private void dfs(int[][] graph, int vertex) {
            // If we've already determined the graph isn't bipartite, return early
            if (!isBipartite) return;

            // Mark the current vertex as visited
            visited[vertex] = true;

            // Check all adjacent vertices
            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) {
                    // Assign the opposite color to the neighbor
                    color[neighbor] = !color[vertex];
                    // Continue DFS from this neighbor
                    dfs(graph, neighbor);
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