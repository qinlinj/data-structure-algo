package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Minimum Height Trees (LeetCode 310)
 * ----------------------------------
 * <p>
 * Summary:
 * This problem asks us to find the root node(s) that would result in a tree with minimum height.
 * A tree is a connected graph with no cycles. The height of a tree is the maximum distance from
 * the root to any leaf node.
 * <p>
 * Key Concepts:
 * 1. The root node(s) that would create a minimum height tree will be the "middle" node(s) of the graph
 * 2. BFS starting from leaf nodes (nodes with only one connection)
 * 3. Topological sorting technique where we repeatedly remove leaf nodes
 * <p>
 * Approach:
 * - Convert the edges array into an adjacency list representation
 * - Identify all leaf nodes (nodes with only one neighbor)
 * - Use BFS to repeatedly remove leaf nodes, layer by layer
 * - After each layer removal, new leaf nodes will emerge
 * - Continue until 1 or 2 nodes remain (these will be our answer)
 * <p>
 * Intuition:
 * - If we pick any node as root and the resulting tree has height h, then the farthest node
 * will be at distance h from the root
 * - If we pick that farthest node as the new root, the resulting tree height will be at most h
 * - The optimal root(s) will be at the "center" of the graph, which we find by repeatedly
 * removing leaf nodes
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for the adjacency list and queue
 */

import java.util.*;

public class _752_e_MinimumHeightTrees {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_e_MinimumHeightTrees solution = new _752_e_MinimumHeightTrees();

        // Example 1: n = 4, edges = [[1,0],[1,2],[1,3]]
        // Tree structure:
        //    0
        //    |
        //    1
        //   / \
        //  2   3
        int n1 = 4;
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        List<Integer> result1 = solution.findMinHeightTrees(n1, edges1);
        System.out.println("Example 1 result: " + result1); // Should output [1]

        // Example 2: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
        // Tree structure:
        //    0    5
        //    |    |
        //    3 -- 4
        //   / \
        //  1   2
        int n2 = 6;
        int[][] edges2 = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        List<Integer> result2 = solution.findMinHeightTrees(n2, edges2);
        System.out.println("Example 2 result: " + result2); // Should output [3, 4]

        // Additional example for visualization
        int n3 = 7;
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}};
        List<Integer> result3 = solution.findMinHeightTrees(n3, edges3);
        System.out.println("Linear path example result: " + result3); // Should output [3] for a linear path
    }

    /**
     * Find the root nodes that would result in minimum height trees
     * @param n Number of nodes in the tree
     * @param edges Edges between nodes
     * @return List of node labels that could be roots of minimum height trees
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Handle edge cases
        if (n == 1) {
            // If there's only one node, it must be the root
            return Collections.singletonList(0);
        }

        // Step 1: Build the adjacency list representation of the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            // Add edges in both directions (undirected graph)
            adjacencyList.get(a).add(b);
            adjacencyList.get(b).add(a);
        }

        // Step 2: Find all leaf nodes (nodes with only one neighbor)
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (adjacencyList.get(i).size() == 1) {
                leaves.offer(i);
            }
        }

        // Step 3: Repeatedly remove leaf nodes until 1 or 2 nodes remain
        int remainingNodes = n;
        while (remainingNodes > 2) {
            int leafCount = leaves.size();
            remainingNodes -= leafCount;

            for (int i = 0; i < leafCount; i++) {
                int leaf = leaves.poll();

                // Get the neighbor of this leaf
                int neighbor = adjacencyList.get(leaf).get(0);

                // Remove the leaf from the neighbor's adjacency list
                adjacencyList.get(neighbor).remove(Integer.valueOf(leaf));

                // If the neighbor becomes a leaf, add it to the queue
                if (adjacencyList.get(neighbor).size() == 1) {
                    leaves.offer(neighbor);
                }
            }
        }

        // The remaining nodes in the queue are the roots of minimum height trees
        List<Integer> result = new ArrayList<>();
        while (!leaves.isEmpty()) {
            result.add(leaves.poll());
        }

        return result;
    }
}