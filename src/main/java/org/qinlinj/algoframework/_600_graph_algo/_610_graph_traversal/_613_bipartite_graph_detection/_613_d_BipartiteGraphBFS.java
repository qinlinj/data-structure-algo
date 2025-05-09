package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

import java.util.*;

/**
 * Bipartite Graph Detection using BFS
 * 
 * This class implements the Breadth-First Search (BFS) approach to determine
 * if a graph is bipartite. While the DFS approach works recursively by exploring
 * as far as possible along each branch, BFS explores the graph level by level.
 * 
 * Algorithm:
 * 1. Use a queue to process nodes in BFS order
 * 2. Color the starting node and enqueue it
 * 3. For each node dequeued, check all its neighbors:
 *    - If a neighbor is uncolored, assign the opposite color and enqueue it
 *    - If a neighbor is already colored, verify it has the opposite color
 * 4. Repeat for each component of the graph
 * 
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the queue and color/visited arrays
 * 
 * This implementation also solves LeetCode Problem 785: Is Graph Bipartite?
 */
public class _613_d_BipartiteGraphBFS {
    
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
        

        
}