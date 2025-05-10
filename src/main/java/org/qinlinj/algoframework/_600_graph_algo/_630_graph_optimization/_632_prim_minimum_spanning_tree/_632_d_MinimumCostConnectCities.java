package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree; /**
 * Minimum Cost to Connect All Cities Using Prim's Algorithm
 * (LeetCode 1135: Connecting Cities With Minimum Cost)
 * 
 * Knowledge Points:
 * 1. This problem is a direct application of the Minimum Spanning Tree (MST):
 *    - Cities are vertices in the graph
 *    - Connections between cities are edges with costs as weights
 *    - Finding the minimum cost to connect all cities is equivalent to finding an MST
 * 
 * 2. Implementation approach:
 *    - Convert the input connections into an adjacency list representation
 *    - Apply Prim's algorithm to find the MST
 *    - Check if all cities are connected in the resulting MST
 *    - Return the total weight of the MST if all cities are connected, otherwise return -1
 * 
 * 3. Edge case handling:
 *    - The problem may use 1-indexed cities (nodes numbered from 1 to n)
 *    - The graph may be disconnected, in which case it's impossible to connect all cities
 * 
 * 4. Time Complexity: O(E log E) where E is the number of connections
 *    Space Complexity: O(V + E) where V is the number of cities
 */
import java.util.*;

public class _632_d_MinimumCostConnectCities {
    
    /**
     * Build an adjacency list representation of the graph from connections
     * 
     * @param n Number of cities (labeled from 1 to n)
     * @param connections Array of connections [city1, city2, cost]
     * @return Adjacency list representation of the graph
     */
    private List<int[]>[] buildGraph(int n, int[][] connections) {
        // Initialize the adjacency list
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        
        // Add each connection to the graph
        for (int[] conn : connections) {
            // Cities are 1-indexed, but our graph is 0-indexed
            int u = conn[0] - 1;
            int v = conn[1] - 1;
            int weight = conn[2];
            
            // Add edges in both directions (undirected graph)
            graph[u].add(new int[]{u, v, weight});
            graph[v].add(new int[]{v, u, weight});
        }
        
        return graph;
    }
    
    /**
     * Prim's algorithm implementation
     */
    private class Prim {
        // Priority queue for edges
        private PriorityQueue<int[]> pq;
        
        // Track which cities are in the MST
        private boolean[] inMST;
        
        // Total cost of the MST
        private int weightSum = 0;
        
        // Graph representation
        private List<int[]>[] graph;
        
        /**
         * Constructor that executes Prim's algorithm
         * 
         * @param graph Adjacency list representation of the graph
         */
        public Prim(List<int[]>[] graph) {
            this.graph = graph;
            
            // Priority queue ordered by edge weight
            this.pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            
            // Number of cities
            int n = graph.length;
            this.inMST = new boolean[n];
            
            // Start from city 0 (arbitrary starting point)
            inMST[0] = true;
            cut(0);
            
            // Main algorithm loop
            while (!pq.isEmpty()) {
                int[] edge = pq.poll();
                int to = edge[1];
                int weight = edge[2];
                
                // Skip if the destination city is already in the MST
                if (inMST[to]) {
                    continue;
                }
                
                // Add the edge to the MST
                weightSum += weight;
                inMST[to] = true;
                
                // Add new crossing edges
                cut(to);
            }
        }
        
        /**
         * Add all crossing edges from city s to the priority queue
         * 
         * @param s The city whose edges are to be considered
         */
        private void cut(int s) {
            // Examine each edge from city s
            for (int[] edge : graph[s]) {
                int to = edge[1];
                
                // Skip if the destination is already in the MST
                if (inMST[to]) {
                    continue;
                }
                
                // Add this crossing edge to the priority queue
                pq.offer(edge);
            }
        }
        
        /**
         * Get the total cost of the MST
         * 
         * @return Sum of costs of all edges in the MST
         */
        public int weightSum() {
            return weightSum;
        }
        
        /**
         * Check if the MST includes all cities
         * 
         * @return true if all cities are in the MST, false otherwise
         */
        public boolean allConnected() {
            for (boolean cityInMST : inMST) {
                if (!cityInMST) {
                    return false;
                }
            }
            return true;
        }
    }
    
    /**
     * Example usage of the solution
     */
    public static void main(String[] args) {
        _632_d_MinimumCostConnectCities solution = new _632_d_MinimumCostConnectCities();
        
        // Example 1: Connected graph
        int n1 = 3;
        int[][] connections1 = {
            {1, 2, 5},  // Connection from city 1 to city 2 with cost 5
            {1, 3, 6},  // Connection from city 1 to city 3 with cost 6
            {2, 3, 1}   // Connection from city 2 to city 3 with cost 1
        };
        
        int minCost1 = solution.minimumCost(n1, connections1);
        System.out.println("Example 1 - Minimum cost to connect all cities: " + minCost1);
        System.out.println("Expected output: 6 (using connections [2,3,1] and [1,2,5])");
        
        // Example 2: Disconnected graph
        int n2 = 4;
        int[][] connections2 = {
            {1, 2, 3},
            {3, 4, 4}
            // Missing connection between cities {1,2} and {3,4}
        };
        
        int minCost2 = solution.minimumCost(n2, connections2);
        System.out.println("\nExample 2 - Minimum cost to connect all cities: " + minCost2);
        System.out.println("Expected output: -1 (impossible to connect all cities)");
        
        // Example 3: Larger connected graph
        int n3 = 5;
        int[][] connections3 = {
            {1, 2, 3},
            {1, 3, 4},
            {1, 4, 6},
            {2, 3, 5},
            {2, 5, 7},
            {3, 4, 2},
            {3, 5, 8},
            {4, 5, 9}
        };
        
        int minCost3 = solution.minimumCost(n3, connections3);
        System.out.println("\nExample 3 - Minimum cost to connect all cities: " + minCost3);
        System.out.println("Expected output: 16 (optimal connections form an MST)");
    }
 Find the minimum cost to connect all cities
     * 
     * @param n Number of cities (labeled from 1 to n)
     * @param connections Array where connections[i] = [city1, city2, cost]
     * @return Minimum cost to connect all cities, or -1 if not possible
     */
    public int minimumCost(int n, int[][] connections) {
        // Build the graph from the given connections
        List<int[]>[] graph = buildGraph(n, connections);
        
        // Apply Prim's algorithm
        Prim prim = new Prim(graph);
        
        // Check if all cities are connected in the MST
        if (!prim.allConnected()) {
            return -1;  // Not all cities can be connected
        }
        
        // Return the total cost (weight) of the MST
        return prim.weightSum();
    }
    
    /**
     *