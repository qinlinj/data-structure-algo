package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Detonate the Maximum Bombs (LeetCode 2101)
 * -----------------------------------------
 * <p>
 * Summary:
 * This problem involves bombs positioned in a 2D plane, each with a specific detonation radius.
 * When a bomb explodes, it detonates all bombs within its radius, causing a chain reaction.
 * The goal is to find which single bomb to detonate first to cause the maximum number of bombs
 * to explode.
 * <p>
 * Key Concepts:
 * 1. Directed graph construction from geometric relationships
 * 2. BFS/DFS to explore explosion chains (connected components)
 * 3. Determining if one bomb is within another's explosion radius
 * 4. Finding the most influential node (bomb) in the graph
 * <p>
 * Approach:
 * - Construct a directed graph where an edge from bomb A to bomb B means detonating A
 * will also detonate B
 * - For each bomb, perform BFS to count how many bombs would detonate in a chain reaction
 * - Return the maximum count found
 * <p>
 * Time Complexity: O(n³) where n is the number of bombs
 * - O(n²) to build the graph
 * - O(n²) to perform BFS for each bomb
 * Space Complexity: O(n²) for the adjacency list
 */

import java.util.*;

public class _753_c_DetonateMaximumBombs {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_c_DetonateMaximumBombs solution = new _753_c_DetonateMaximumBombs();

        // Example 1: bombs = [[2,1,3],[6,1,4]]
        int[][] bombs1 = {{2, 1, 3}, {6, 1, 4}};
        System.out.println("Example 1: " + solution.maximumDetonation(bombs1)); // Expected: 2

        // Example 2: bombs = [[1,1,5],[10,10,5]]
        int[][] bombs2 = {{1, 1, 5}, {10, 10, 5}};
        System.out.println("Example 2: " + solution.maximumDetonation(bombs2)); // Expected: 1

        // Example 3: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
        int[][] bombs3 = {{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}};
        System.out.println("Example 3: " + solution.maximumDetonation(bombs3)); // Expected: 5

        // Additional example
        int[][] bombs4 = {
                {1, 1, 1},
                {2, 2, 1},
                {3, 3, 1},
                {4, 4, 1},
                {5, 5, 10}
        };
        System.out.println("Additional Example: " + solution.maximumDetonation(bombs4));
    }

    /**
     * Find the maximum number of bombs that can be detonated by detonating a single bomb
     * @param bombs Array where bombs[i] = [x, y, r] represents position (x,y) and radius r
     * @return Maximum number of bombs that can be detonated
     */
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;

        // Build the directed graph as an adjacency list
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Determine which bombs are within each bomb's radius
        for (int i = 0; i < n; i++) {
            long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                long x2 = bombs[j][0], y2 = bombs[j][1];

                // Check if bomb j is within bomb i's detonation radius
                // Use long to avoid potential overflow with large coordinates
                if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= r1 * r1) {
                    graph[i].add(j);
                }
            }
        }

        int maxBombs = 0;

        // Try detonating each bomb and find the maximum chain reaction
        for (int i = 0; i < n; i++) {
            int detonated = bfs(graph, i);
            maxBombs = Math.max(maxBombs, detonated);
        }

        return maxBombs;
    }

    /**
     * BFS to count how many bombs can be detonated starting from a given bomb
     * @param graph Adjacency list representing which bombs detonate which
     * @param start Starting bomb to detonate
     * @return Number of bombs detonated
     */
    private int bfs(List<Integer>[] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        queue.offer(start);
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int bomb = queue.poll();
            count++;

            // Add all bombs that would be detonated
            for (int neighbor : graph[bomb]) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return count;
    }

    /**
     * Alternative DFS implementation
     */
    private int dfs(List<Integer>[] graph, int bomb, boolean[] visited) {
        visited[bomb] = true;
        int count = 1;

        for (int neighbor : graph[bomb]) {
            if (!visited[neighbor]) {
                count += dfs(graph, neighbor, visited);
            }
        }

        return count;
    }
}