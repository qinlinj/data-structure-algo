package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

import java.util.*;

/**
 * Possible Bipartition Problem
 * <p>
 * This class solves LeetCode Problem 886: Possible Bipartition
 * <p>
 * Problem Statement:
 * Given a set of n people (numbered 1 to n), we want to split them into two groups.
 * Some people may dislike others, indicated by dislikes[i] = [a, b], meaning
 * person a dislikes person b. When this is the case, they must be in different groups.
 * <p>
 * Return true if we can split everyone into two groups, and false otherwise.
 * <p>
 * Approach:
 * This problem is equivalent to determining if the "dislike" graph is bipartite:
 * - Each person is a node
 * - Each dislike relationship forms an edge
 * - We need to check if the nodes can be divided into two groups such that
 * no two adjacent nodes (people who dislike each other) are in the same group
 * <p>
 * Time Complexity: O(N + E) where N is the number of people and E is the number of dislike relationships
 * Space Complexity: O(N + E) for the adjacency list and color/visited arrays
 */
public class _613_e_PossibleBipartition {

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("POSSIBLE BIPARTITION PROBLEM");
        System.out.println("============================");

        // Example 1: Bipartition is possible
        // Person 1 dislikes 2, 1 dislikes 3, 2 dislikes 4
        int[][] dislikes1 = {{1, 2}, {1, 3}, {2, 4}};
        int n1 = 4;

        System.out.println("\nExample 1:");
        System.out.println("n = " + n1 + ", dislikes = [[1,2], [1,3], [2,4]]");
        System.out.println("Dislike relationships:");
        System.out.println("1 --X-- 2 --X-- 4");
        System.out.println("|");
        System.out.println("X");
        System.out.println("|");
        System.out.println("3");

        boolean result1 = solution.possibleBipartition(n1, dislikes1);
        System.out.println("Can be divided into two groups? " + result1);
        System.out.println("Possible grouping: Group 1 = {1, 4}, Group 2 = {2, 3}");

        // Reset solution for next example
        solution = new Solution();

        // Example 2: Bipartition is impossible (triangle of dislikes)
        int[][] dislikes2 = {{1, 2}, {1, 3}, {2, 3}};
        int n2 = 3;

        System.out.println("\nExample 2:");
        System.out.println("n = " + n2 + ", dislikes = [[1,2], [1,3], [2,3]]");
        System.out.println("Dislike relationships:");
        System.out.println("  1");
        System.out.println(" / \\");
        System.out.println("X   X");
        System.out.println("/     \\");
        System.out.println("2 --X-- 3");

        boolean result2 = solution.possibleBipartition(n2, dislikes2);
        System.out.println("Can be divided into two groups? " + result2);
        System.out.println("This is impossible because we have a 'dislike triangle' where everyone dislikes everyone else.");

        // Reset solution for next example
        solution = new Solution();

        // Example 3: Bipartition is impossible (more complex)
        int[][] dislikes3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        int n3 = 5;

        System.out.println("\nExample 3:");
        System.out.println("n = " + n3 + ", dislikes = [[1,2], [2,3], [3,4], [4,5], [1,5]]");
        System.out.println("Dislike relationships:");
        System.out.println("1 --X-- 2");
        System.out.println("|        |");
        System.out.println("X        X");
        System.out.println("|        |");
        System.out.println("5 --X-- 4 --X-- 3");

        boolean result3 = solution.possibleBipartition(n3, dislikes3);
        System.out.println("Can be divided into two groups? " + result3);
        System.out.println("This is impossible because we have an odd cycle (1-2-3-4-5-1) of length 5.");

        System.out.println("\nKey Insights:");
        System.out.println("1. This problem is equivalent to checking if the 'dislike graph' is bipartite");
        System.out.println("2. People who dislike each other must be in different groups (different colors)");
        System.out.println("3. The solution fails when there's an odd-length cycle in the dislike relationships");
        System.out.println("4. The algorithm uses DFS, but BFS would work equally well for this problem");
    }

    /**
     * Solution class for the Possible Bipartition problem
     */
    public static class Solution {
        private boolean[] color;  // Represents the group assignment
        private boolean[] visited;  // Tracks visited nodes
        private boolean isPossible = true;  // Result flag

        /**
         * Main function to determine if bipartition is possible
         *
         * @param n        Number of people (nodes)
         * @param dislikes Dislike relationships (edges)
         * @return true if bipartition is possible, false otherwise
         */
        public boolean possibleBipartition(int n, int[][] dislikes) {
            // People are numbered from 1 to n
            color = new boolean[n + 1];
            visited = new boolean[n + 1];

            // Build the graph from dislike relationships
            List<Integer>[] graph = buildGraph(n, dislikes);

            // Check each component (handle disconnected graph)
            for (int person = 1; person <= n; person++) {
                if (!visited[person]) {
                    dfs(graph, person);
                }
            }

            return isPossible;
        }

        /**
         * Builds the adjacency list representation of the dislike graph
         *
         * @param n        Number of people
         * @param dislikes Array of dislike relationships
         * @return Adjacency list representation of the graph
         */
        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            // Create adjacency list (1-indexed)
            List<Integer>[] graph = new LinkedList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new LinkedList<>();
            }

            // Add edges for each dislike relationship (undirected)
            for (int[] edge : dislikes) {
                int a = edge[0];
                int b = edge[1];
                // Dislike is mutual - add edges in both directions
                graph[a].add(b);
                graph[b].add(a);
            }

            return graph;
        }

        /**
         * Depth-First Search to check if bipartition is possible
         *
         * @param graph  Adjacency list representation of the graph
         * @param person Current person (node) being processed
         */
        private void dfs(List<Integer>[] graph, int person) {
            // If already determined impossible, return early
            if (!isPossible) return;

            visited[person] = true;

            // Check all people that this person dislikes
            for (int disliked : graph[person]) {
                if (!visited[disliked]) {
                    // Assign opposite group to disliked person
                    color[disliked] = !color[person];
                    dfs(graph, disliked);
                } else {
                    // If already visited, check if groups are compatible
                    if (color[disliked] == color[person]) {
                        // Same group conflict - bipartition impossible
                        isPossible = false;
                        return;
                    }
                }
            }
        }
    }
}