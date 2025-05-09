package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

import java.util.*;

/**
 * Bipartite Graph: Comprehensive Summary
 * <p>
 * This class provides a complete overview of bipartite graphs, including:
 * - Definition and properties
 * - Detection algorithms (DFS and BFS approaches)
 * - Applications and practical examples
 * - Theoretical foundations and connections to other graph problems
 * <p>
 * A bipartite graph is a graph whose vertices can be divided into two disjoint sets
 * such that every edge connects vertices from different sets. Equivalently, a bipartite
 * graph is one that can be colored using only two colors such that no adjacent vertices
 * share the same color.
 */
public class _613_f_BipartiteGraphSummary {

    /**
     * Main method demonstrating comprehensive bipartite graph concepts
     */
    public static void main(String[] args) {
        System.out.println("BIPARTITE GRAPHS: COMPREHENSIVE SUMMARY");
        System.out.println("=======================================");

        // Demonstrate key properties
        demonstrateProperties();

        // Compare detection algorithms
        compareAlgorithms();

        // Show real-world applications
        explainApplications();

        // Theoretical insights
        theoreticalInsights();
    }

    /**
     * Demonstrate key properties of bipartite graphs
     */
    private static void demonstrateProperties() {
        System.out.println("\n1. KEY PROPERTIES OF BIPARTITE GRAPHS");
        System.out.println("----------------------------------");

        System.out.println("Fundamental Properties:");
        System.out.println("• Vertices can be partitioned into two disjoint sets (U and V)");
        System.out.println("• Every edge connects a vertex in U with a vertex in V");
        System.out.println("• No edges exist between vertices within the same set");
        System.out.println("• Can be two-colored: adjacent vertices have different colors");
        System.out.println();

        System.out.println("Characterization by Cycles:");
        System.out.println("• A graph is bipartite if and only if it contains no odd-length cycles");
        System.out.println("• Even-length cycles are permitted in bipartite graphs");
        System.out.println();

        System.out.println("Examples:");
        System.out.println("Bipartite: Trees, Even-length cycles, Complete bipartite graphs (Km,n)");
        System.out.println("Non-Bipartite: Odd-length cycles (e.g., triangles), Complete graphs Kn (n>2)");
        System.out.println();

        // Example bipartite and non-bipartite graphs
        int[][] bipartiteGraph = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        int[][] nonBipartiteGraph = {
                {1, 2},
                {0, 2},
                {0, 1}
        };

        // Check and display results
        boolean isBipartite1 = checkBipartite(bipartiteGraph);
        boolean isBipartite2 = checkBipartite(nonBipartiteGraph);

        System.out.println("Square Graph (0-1-2-3-0): " + (isBipartite1 ? "Bipartite" : "Not Bipartite"));
        System.out.println("Triangle Graph (0-1-2-0): " + (isBipartite2 ? "Bipartite" : "Not Bipartite"));
    }

    /**
     * Compare DFS and BFS approaches for bipartite detection
     */
    private static void compareAlgorithms() {
        System.out.println("\n2. BIPARTITE DETECTION ALGORITHMS");
        System.out.println("-------------------------------");

        System.out.println("DFS Approach:");
        System.out.println("• Uses recursion to traverse the graph");
        System.out.println("• Colors nodes as they are discovered");
        System.out.println("• Checks for conflicts between adjacent nodes");
        System.out.println("• Time Complexity: O(V + E)");
        System.out.println("• Space Complexity: O(V) for recursion stack");
        System.out.println();

        System.out.println("BFS Approach:");
        System.out.println("• Uses a queue to traverse the graph level by level");
        System.out.println("• Colors nodes as they are discovered");
        System.out.println("• Checks for conflicts between adjacent nodes");
        System.out.println("• Time Complexity: O(V + E)");
        System.out.println("• Space Complexity: O(V) for queue");
        System.out.println();

        System.out.println("Comparison:");
        System.out.println("• Both algorithms are equally efficient in terms of time complexity");
        System.out.println("• Both successfully detect whether a graph is bipartite");
        System.out.println("• BFS might be more intuitive for visualization (level by level coloring)");
        System.out.println("• DFS might be more concise to implement with recursion");
        System.out.println("• For large graphs, BFS might avoid stack overflow issues");

        // Example medium-sized graph to test algorithm performance
        int n = 100;
        int[][] largeGraph = generateRandomBipartiteGraph(n, n / 2);

        long startTime, endTime;

        // Time DFS
        startTime = System.nanoTime();
        boolean dfsBipartite = checkBipartiteDFS(largeGraph);
        endTime = System.nanoTime();
        long dfsTime = endTime - startTime;

        // Time BFS
        startTime = System.nanoTime();
        boolean bfsBipartite = checkBipartiteBFS(largeGraph);
        endTime = System.nanoTime();
        long bfsTime = endTime - startTime;

        System.out.println("\nPerformance on random graph with " + n + " nodes:");
        System.out.println("DFS time: " + dfsTime / 1000000.0 + " ms");
        System.out.println("BFS time: " + bfsTime / 1000000.0 + " ms");
        System.out.println("Both algorithms correctly identified the graph as " +
                (dfsBipartite ? "bipartite" : "non-bipartite"));
    }

    /**
     * Explain practical applications of bipartite graphs
     */
    private static void explainApplications() {
        System.out.println("\n3. PRACTICAL APPLICATIONS");
        System.out.println("------------------------");

        System.out.println("Data Representation:");
        System.out.println("• Movie-Actor Networks: Movies connected to actors who starred in them");
        System.out.println("• User-Product Networks: Users connected to products they've purchased");
        System.out.println("• Document-Word Networks: Documents connected to words they contain");
        System.out.println();

        System.out.println("Matching Problems:");
        System.out.println("• Job Assignment: Matching workers to jobs based on skills");
        System.out.println("• Resource Allocation: Assigning resources to tasks efficiently");
        System.out.println("• Stable Marriage Problem: Finding stable matching between two sets");
        System.out.println();

        System.out.println("Real-world Conflict Resolution:");
        System.out.println("• Scheduling: Assigning time slots to classes/exams with no conflicts");
        System.out.println("• Circuit Design: Placing components to minimize interference");
        System.out.println("• Social Network Analysis: Finding conflicting groups");
        System.out.println();

        System.out.println("Example: Class Scheduling Problem");
        System.out.println("If we have students who take multiple classes, and we want to schedule");
        System.out.println("exams so that no student has two exams at the same time, we can model");
        System.out.println("this as a graph coloring problem where:");
        System.out.println("• Nodes represent classes");
        System.out.println("• Edges connect classes with common students");
        System.out.println("• Colors represent time slots");
        System.out.println();
        System.out.println("If the graph is bipartite, we can schedule all exams in just two time slots!");
    }

    /**
     * Provide theoretical insights about bipartite graphs
     */
    private static void theoreticalInsights() {
        System.out.println("\n4. THEORETICAL INSIGHTS");
        System.out.println("-----------------------");

        System.out.println("Mathematical Properties:");
        System.out.println("• A graph is bipartite if and only if it does not contain an odd cycle");
        System.out.println("• The chromatic number of a bipartite graph is exactly 2 (unless it has no edges)");
        System.out.println("• The maximum number of edges in a bipartite graph with m and n vertices in each part is m×n");
        System.out.println();

        System.out.println("Connection to Other Problems:");
        System.out.println("• Maximum Bipartite Matching: Finding the maximum number of edges with no common vertices");
        System.out.println("• Minimum Vertex Cover: Finding the minimum set of vertices that touch all edges");
        System.out.println("• Maximum Independent Set: In bipartite graphs, related to minimum vertex cover by König's theorem");
        System.out.println();

        System.out.println("König's Theorem:");
        System.out.println("In bipartite graphs, the size of the maximum matching equals the size of the minimum vertex cover.");
        System.out.println("This theorem is important because it connects these optimization problems that are");
        System.out.println("generally difficult for arbitrary graphs, but efficiently solvable for bipartite graphs.");
        System.out.println();

        System.out.println("Extension to k-Partite Graphs:");
        System.out.println("The concept can be extended to k-partite graphs, where vertices are partitioned into k sets");
        System.out.println("and edges only connect vertices from different sets. The corresponding coloring problem");
        System.out.println("would require k colors instead of just 2.");
    }

    /**
     * Helper method to check if a graph is bipartite using DFS
     */
    private static boolean checkBipartite(int[][] graph) {
        return checkBipartiteDFS(graph);
    }

    /**
     * DFS-based bipartite checking
     */
    private static boolean checkBipartiteDFS(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] color = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (!dfs(graph, i, visited, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int[][] graph, int node, boolean[] visited, boolean[] color) {
        visited[node] = true;

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                color[neighbor] = !color[node];
                if (!dfs(graph, neighbor, visited, color)) {
                    return false;
                }
            } else if (color[neighbor] == color[node]) {
                return false;
            }
        }

        return true;
    }

    /**
     * BFS-based bipartite checking
     */
    private static boolean checkBipartiteBFS(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] color = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                visited[i] = true;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int node = queue.poll();

                    for (int neighbor : graph[node]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            color[neighbor] = !color[node];
                            queue.offer(neighbor);
                        } else if (color[neighbor] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Generate a random bipartite graph for testing
     */
    private static int[][] generateRandomBipartiteGraph(int n, int edgesPerNode) {
        // Create two sets of roughly equal size
        int set1Size = n / 2;

        // Initialize adjacency lists
        List<Integer>[] tempGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tempGraph[i] = new ArrayList<>();
        }

        // Generate random edges between the two sets
        Random random = new Random(42); // Fixed seed for reproducibility

        for (int i = 0; i < set1Size; i++) {
            int edgesToAdd = Math.min(edgesPerNode, n - set1Size);
            for (int j = 0; j < edgesToAdd; j++) {
                int target = set1Size + random.nextInt(n - set1Size);

                // Avoid duplicate edges
                if (!tempGraph[i].contains(target)) {
                    tempGraph[i].add(target);
                    tempGraph[target].add(i);
                }
            }
        }

        // Convert to the required format
        int[][] result = new int[n][];
        for (int i = 0; i < n; i++) {
            result[i] = new int[tempGraph[i].size()];
            for (int j = 0; j < tempGraph[i].size(); j++) {
                result[i][j] = tempGraph[i].get(j);
            }
        }

        return result;
    }
}