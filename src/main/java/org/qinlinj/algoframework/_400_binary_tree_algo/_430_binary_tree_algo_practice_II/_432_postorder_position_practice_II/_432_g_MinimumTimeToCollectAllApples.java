package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

import java.util.*;

/**
 * Problem 1443: Minimum Time to Collect All Apples in a Tree
 * <p>
 * Description:
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples
 * in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time
 * in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming
 * back to this vertex.
 * <p>
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means
 * that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array
 * hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not
 * have any apple.
 * <p>
 * Key Concepts:
 * - Converts edge list to adjacency list representation of a tree
 * - Uses post-order traversal to compute minimum collection time
 * - Handles undirected graph representation
 * - Optimizes traversal by visiting only nodes that lead to apples
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n) for the adjacency list and recursion stack
 */
public class _432_g_MinimumTimeToCollectAllApples {
    /**
     * Main function to calculate minimum time to collect all apples
     *
     * @param n        Number of nodes in the tree
     * @param edges    Array of edges representing the tree
     * @param hasApple Boolean array indicating which nodes have apples
     * @return Minimum time to collect all apples
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // Create adjacency list representation of the tree
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }

        // Add edges to the adjacency list (undirected)
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adjacencyList.get(a).add(b);
            adjacencyList.get(b).add(a);  // Tree is undirected
        }

        // Set to track visited nodes (avoid cycles)
        Set<Integer> visited = new HashSet<>();

        // Start DFS from node 0
        int totalTime = dfs(0, adjacencyList, hasApple, visited);

        // Return the total time (or 0 if no apples are found)
        return totalTime;
    }

    /**
     * Helper function to perform DFS and calculate collection time
     *
     * @param node          Current node being visited
     * @param adjacencyList Adjacency list representation of the tree
     * @param hasApple      Boolean list indicating which nodes have apples
     * @param visited       Set to track visited nodes
     * @return Time needed to collect apples in the subtree rooted at node
     */
    private int dfs(int node, Map<Integer, List<Integer>> adjacencyList,
                    List<Boolean> hasApple, Set<Integer> visited) {
        // Mark current node as visited
        visited.add(node);

        // Track time needed for current subtree
        int totalTime = 0;

        // Visit all unvisited children
        for (int child : adjacencyList.get(node)) {
            if (!visited.contains(child)) {
                // Get time needed for this child's subtree
                int childTime = dfs(child, adjacencyList, hasApple, visited);

                // If childTime > 0, it means we found apples in this subtree
                // Add the time for traversing to and from this child (2 seconds)
                if (childTime > 0) {
                    totalTime += childTime + 2;
                }
            }
        }

        // Post-order position: determine if this subtree has apples

        // If this node has an apple and it's not the root (node 0),
        // we need to visit it
        if (totalTime == 0 && hasApple.get(node) && node != 0) {
            return 0;  // Time will be added by parent
        }

        return totalTime;
    }

    /**
     * Alternative implementation with more explicit apple checking
     */
    public int minTimeAlternative(int n, int[][] edges, List<Boolean> hasApple) {
        // Create adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // Call helper function starting from node 0 with parent -1 (no parent)
        return collectApples(0, -1, adjacencyList, hasApple);
    }

    /**
     * Helper function for alternative implementation
     * Returns the time required to collect all apples in the subtree rooted at node
     */
    private int collectApples(int node, int parent, List<List<Integer>> adjacencyList, List<Boolean> hasApple) {
        int totalTime = 0;

        // Visit all children except parent
        for (int child : adjacencyList.get(node)) {
            if (child != parent) {
                int childTime = collectApples(child, node, adjacencyList, hasApple);

                // If child subtree has apples or the child itself has an apple
                if (childTime > 0 || hasApple.get(child)) {
                    // Add time to go to child and back (2 seconds)
                    totalTime += childTime + 2;
                }
            }
        }

        return totalTime;
    }

    /**
     * Implementation with explicit visited check instead of parent tracking
     */
    public int minTimeWithVisitedCheck(int n, int[][] edges, List<Boolean> hasApple) {
        // Create adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Start DFS from node 0
        Set<Integer> visited = new HashSet<>();
        int result = dfsWithCheck(0, graph, hasApple, visited);

        // If the result is -1, it means no apples were found
        return Math.max(0, result);
    }

    /**
     * Helper method that returns:
     * - Time needed to collect apples in the subtree
     * - -1 if no apples are found in the subtree
     */
    private int dfsWithCheck(int node, Map<Integer, List<Integer>> graph,
                             List<Boolean> hasApple, Set<Integer> visited) {
        visited.add(node);

        int totalTime = 0;
        boolean foundApple = false;

        for (int child : graph.get(node)) {
            if (!visited.contains(child)) {
                int childTime = dfsWithCheck(child, graph, hasApple, visited);

                if (childTime != -1) {
                    foundApple = true;
                    totalTime += childTime + 2;  // Time to visit child and return
                }
            }
        }

        if (hasApple.get(node)) {
            foundApple = true;
        }

        return foundApple ? totalTime : -1;
    }
}