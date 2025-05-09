package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

/**
 * Bipartite Graph Detection using DFS
 * 
 * This class implements the Depth-First Search (DFS) approach to determine
 * if a graph is bipartite. A graph is bipartite if its vertices can be divided
 * into two disjoint sets such that no vertices within the same set are adjacent.
 * 
 * Algorithm:
 * 1. Use two arrays: one to track visited nodes and one to track colors
 * 2. Perform DFS from each unvisited node (to handle disconnected components)
 * 3. During traversal, color each node the opposite color of its parent
 * 4. If we encounter an already colored node that conflicts with the expected color,
 *    the graph is not bipartite
 * 
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the recursion stack and color/visited arrays
 * 
 * This implementation solves LeetCode Problem 785: Is Graph Bipartite?
 */
public class _613_c_BipartiteGraphDFS {
    
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
        

        
}