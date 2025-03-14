package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

/**
 * ConnectedComponentsAnalyzer - A class to identify and analyze connected components in an undirected graph using DFS.
 * <p>
 * What are Connected Components (CC)?
 * A connected component is a subgraph in which any two vertices are connected to each other by paths,
 * and which is connected to no additional vertices in the supergraph.
 * <p>
 * Advantages and Applications:
 * 1. Network Analysis: Identify isolated networks or communities within a larger graph
 * 2. Image Processing: Connected components are used to identify distinct objects in images
 * 3. Social Network Analysis: Finding communities of connected individuals
 * 4. Circuit Design: Identifying connected elements in electronic circuits
 * <p>
 * Principles and Concepts:
 * - Uses DFS to identify all vertices in the same connected component
 * - Each component is assigned a unique ID number (starting from 1)
 * - The algorithm tracks not just whether a vertex is visited, but also which component it belongs to
 * - This allows for more advanced queries like checking if two vertices are connected
 * <p>
 * Differences from basic CC implementation:
 * 1. Uses integer array instead of boolean array to track components IDs, not just visited status
 * 2. Provides additional utility methods to:
 * - Retrieve all vertices in each component
 * - Check if two vertices are in the same component (connected)
 * 3. Includes validation for vertex parameters
 * <p>
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array, recursion stack, and component lists
 */
public class ConnectedComponentsAnalyzer {
    // The graph to be processed
    private Graph g;

    // Array to track which component each vertex belongs to (-1 means unvisited)
    private int[] visited;

    // Count of connected components
    private int ccCount = 0;

    /**
     * Constructor that initializes the connected components algorithm and computes the result.
     * <p>
     * Visual Example:
     * Consider a graph with 7 vertices:
     * 0 -- 1    3 -- 4
     * |         |
     * 2         5 -- 6
     * <p>
     * After processing:
     * - visited array = [1, 1, 1, 2, 2, 2, 2]
     * - ccCount = 2
     * - Component 1: {0, 1, 2}
     * - Component 2: {3, 4, 5, 6}
     *
     * @param g The input graph
     *          <p>
     *          Time Complexity: O(V + E) - We visit each vertex once and process all edges
     *          Space Complexity: O(V) - For the visited array and recursion stack
     */
    public ConnectedComponentsAnalyzer(Graph g) {
        this.g = g;

        if (g == null) return;

        // Initialize visited array with -1 (unvisited)
        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);

        // Iterate through all vertices
        for (int v = 0; v < g.getV(); v++) {
            // If vertex is not visited (component ID is -1), start a new DFS
            if (visited[v] == -1) {
                ccCount++;  // Increment connected component count
                dfs(v, ccCount);  // Perform DFS to mark all vertices in this component
            }
        }
    }

    /**
     * Main method to demonstrate the ConnectedComponentsAnalyzer usage.
     *
     * @param args Command line arguments (not used)
     *             <p>
     *             Time Complexity: Depends on the size of the input graph
     *             Space Complexity: Depends on the size of the input graph
     */
    public static void main(String[] args) {
        // Create a graph from file
        Graph g = new AdjSet("data/graph-dfs.txt");

        // Find and analyze connected components
        ConnectedComponentsAnalyzer graphDFS = new ConnectedComponentsAnalyzer(g);

        // Print all components
        System.out.println(Arrays.toString(graphDFS.components()));

        // Check if two vertices are connected
        System.out.println(graphDFS.isConnected(0, 6));
    }

    /**
     * Depth-First Search to mark all vertices in the same connected component.
     * Unlike the basic implementation, this method assigns a component ID to each visited vertex.
     * <p>
     * Visual Example for dfs(0, 1):
     * Graph:
     * 0 -- 1
     * |
     * 2
     * <p>
     * Steps:
     * 1. Mark vertex 0 as component 1: visited[0] = 1
     * 2. For each neighbor of 0 (1 and 2):
     * a. If not visited, call dfs on the neighbor
     * b. dfs(1, 1) marks vertex 1 as component 1
     * c. dfs(2, 1) marks vertex 2 as component 1
     * 3. Now all vertices {0,1,2} are marked as belonging to component 1
     *
     * @param v    The vertex to start DFS from
     * @param ccId The component ID to assign to all vertices in this component
     *             <p>
     *             Time Complexity: O(V + E) - Each vertex and edge is processed once
     *             Space Complexity: O(V) - Due to recursion stack in worst case
     */
    private void dfs(int v, int ccId) {
        visited[v] = ccId;  // Mark current vertex as part of component ccId

        // Explore all adjacent vertices
        for (int w : g.adj(v)) {
            // If adjacent vertex is not visited, perform DFS on it
            if (visited[w] == -1) {
                dfs(w, ccId);
            }
        }
    }

    /**
     * Returns the count of connected components in the graph.
     *
     * @return The number of connected components
     * <p>
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - No additional space used
     */
    public int getCcCount() {
        return ccCount;
    }

    /**
     * Returns all vertices grouped by their connected components.
     * <p>
     * Visual Example:
     * For a graph with following structure:
     * 0 -- 1    3 -- 4
     * |         |
     * 2         5 -- 6
     * <p>
     * The result would be:
     * [[0, 1, 2], [3, 4, 5, 6]]
     *
     * @return An array of lists where each list contains vertices in one connected component
     * <p>
     * Time Complexity: O(V) - We iterate through all vertices once
     * Space Complexity: O(V) - To store all vertices in component lists
     */
    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];

        // Initialize each component's list
        // Note: Cannot use Arrays.fill(res, new ArrayList<>()) as it would
        // use the same ArrayList object for all elements
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }

        // Add each vertex to its respective component's list
        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);  // Adjust index because component IDs start from 1
        }

        return res;
    }

    /**
     * Checks if two vertices are connected (belong to the same component).
     *
     * @param v First vertex
     * @param w Second vertex
     * @return true if vertices are in the same connected component, false otherwise
     * <p>
     * Time Complexity: O(1) - Just array lookups
     * Space Complexity: O(1) - No additional space used
     */
    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];
    }

    /**
     * Validates if a vertex is within the valid range for the graph.
     *
     * @param v The vertex to validate
     * @throws IllegalArgumentException if vertex is outside valid range
     *                                  <p>
     *                                  Time Complexity: O(1) - Constant time operation
     *                                  Space Complexity: O(1) - No additional space used
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {  // Fixed logical condition from '&&' to '||'
            throw new IllegalArgumentException("Vertex is invalid, out of range");
        }
    }
}