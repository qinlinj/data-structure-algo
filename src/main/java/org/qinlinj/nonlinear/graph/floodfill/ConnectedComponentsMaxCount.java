package org.qinlinj.nonlinear.graph.floodfill;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;
// @formatter:off
/**
 * Connected Components Algorithm - Finding Maximum Component Size
 *
 * Concept and Principles:
 * A connected component in an undirected graph is a subgraph in which any two vertices
 * are connected to each other by paths. In other words, it's a group of vertices that
 * are all reachable from each other.
 *
 * Advantages of Connected Components Analysis:
 * 1. Helps identify isolated groups within a network
 * 2. Useful for clustering and community detection in social networks
 * 3. Important for image segmentation in computer vision
 * 4. Enables efficient processing of disconnected parts of a graph in parallel
 * 5. Forms the basis for many graph algorithms including minimum spanning trees
 *
 * This implementation specifically finds the size of the largest connected component
 * in an undirected graph using Depth-First Search (DFS).
 *
 * Visual Example:
 * Consider this graph with 7 vertices (0-6) having two connected components:
 *    0 -- 1    4 -- 5
 *    |    |    |
 *    2 -- 3    6
 *
 * Connected Component 1: [0,1,2,3] (size 4)
 * Connected Component 2: [4,5,6] (size 3)
 *
 * The maximum component size is 4.
 */
public class ConnectedComponentsMaxCount {
    private Graph g;  // The input graph
    private boolean[] visited;  // Tracks visited vertices
    private int maxVertexNum = 0;  // Stores the size of the largest component

    /**
     * Constructor that initializes the algorithm and computes the maximum component size.
     *
     * Algorithm:
     * 1. Create a visited array to keep track of processed vertices
     * 2. For each unvisited vertex, start a DFS traversal to explore its component
     * 3. Keep track of the maximum component size found
     *
     * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
     * - We visit each vertex once and process each edge once during the DFS traversals
     *
     * Space Complexity: O(V) for the visited array and recursion stack in worst case
     *
     * @param g The graph to analyze
     */
    public ConnectedComponentsMaxCount(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                // For each unvisited vertex, start a new DFS traversal
                // This means we've found a new connected component
                // Calculate its size and update the maximum if needed
                maxVertexNum = Math.max(dfs(v), maxVertexNum);
            }
        }
    }

    /**
     * Main method to demonstrate the connected components algorithm.
     *
     * Visual demonstration of execution with graph-dfs.txt:
     * Assuming the file contains the example graph shown in the class comment:
     *
     * 1. Create graph from file
     * 2. Run the connected components algorithm
     * 3. Print the maximum component size (which should be 4)
     *
     * Time Complexity: Depends on the graph construction and algorithm execution, O(V + E) overall
     * Space Complexity: O(V + E) for graph representation and algorithm data structures
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        ConnectedComponentsMaxCount graphDFS = new ConnectedComponentsMaxCount(g);
        System.out.println(graphDFS.getMaxVertexNum());
    }

    /**
     * Performs depth-first search from vertex v and returns the size of its connected component.
     *
     * The DFS algorithm:
     * 1. Mark the current vertex as visited
     * 2. Initialize component size to 1 (counting the current vertex)
     * 3. Recursively visit all unvisited adjacent vertices
     * 4. Add the sizes of connected subgraphs to the result
     * 5. Return the total component size
     *
     * Visual example:
     * For the DFS starting at vertex 0 in our example graph:
     *    0 -- 1    4 -- 5
     *    |    |    |
     *    2 -- 3    6
     *
     * DFS(0):
     *   - Mark 0 as visited, res = 1
     *   - Visit adjacent vertices 1 and 2
     *   DFS(1):
     *     - Mark 1 as visited, res = 1
     *     - Visit adjacent vertex 3
     *     DFS(3):
     *       - Mark 3 as visited, res = 1
     *       - Visit adjacent vertex 2
     *       DFS(2):
     *         - Mark 2 as visited, res = 1
     *         - No unvisited adjacent vertices
     *         - Return 1
     *       - res += 1 = 2
     *       - Return 2
     *     - res += 2 = 3
     *     - Return 3
     *   - res += 3 = 4
     *   - Return 4
     *
     * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
     * - Each vertex and edge is processed exactly once in its component
     *
     * Space Complexity: O(V) for the recursion stack in worst case (e.g., a line graph)
     *
     * @param v The starting vertex for DFS
     * @return The size of the connected component containing vertex v
     */
    private int dfs(int v) {
        visited[v] = true;
        int res = 1;  // Count the current vertex
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                res += dfs(w);  // Add sizes of all connected subgraphs
            }
        }
        return res;
    }

    /**
     * Returns the size of the largest connected component found in the graph.
     *
     * Time Complexity: O(1) - constant time access to the class variable
     * Space Complexity: O(1) - no additional space used
     *
     * @return The maximum size of any connected component in the graph
     */
    public int getMaxVertexNum() {
        return maxVertexNum;
    }
}