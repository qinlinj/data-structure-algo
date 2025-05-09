package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._612_celebrity_problem;

import java.util.*;

/**
 * Brute Force Solution to the Celebrity Problem
 * <p>
 * Approach:
 * - Check each person as a potential celebrity candidate
 * - For each candidate, verify whether they satisfy both celebrity conditions:
 * 1. Everyone else knows the candidate
 * 2. The candidate knows no one else
 * <p>
 * Time Complexity: O(n²)
 * - For each of the n candidates, we check their relationship with all other n-1 people
 * - This requires O(n) checks per candidate, resulting in O(n²) overall
 * <p>
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space
 * <p>
 * The brute force approach is straightforward but inefficient for large groups.
 */
public class _612_b_CelebrityProblemBruteForce {

    /**
     * Test cases to demonstrate the brute force solution
     */
    public static void main(String[] args) {
        // Example 1: Person 2 is the celebrity
        int[][] graph1 = {
                {0, 1, 1},
                {0, 0, 1},
                {0, 0, 0}
        };

        Solution solution1 = new Solution(graph1);
        int celebrity1 = solution1.findCelebrity(3);
        System.out.println("Example 1 - Adjacency Matrix:");
        printMatrix(graph1);
        System.out.println("Celebrity: " + celebrity1); // Expected: 2
        System.out.println();

        // Example 2: No celebrity
        int[][] graph2 = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };

        Solution solution2 = new Solution(graph2);
        int celebrity2 = solution2.findCelebrity(3);
        System.out.println("Example 2 - Adjacency Matrix:");
        printMatrix(graph2);
        System.out.println("Celebrity: " + celebrity2); // Expected: -1
        System.out.println();

        // Example 3: Person 0 is the celebrity
        int[][] graph3 = {
                {0, 0, 0},
                {1, 0, 0},
                {1, 0, 0}
        };

        Solution solution3 = new Solution(graph3);
        int celebrity3 = solution3.findCelebrity(3);
        System.out.println("Example 3 - Adjacency Matrix:");
        printMatrix(graph3);
        System.out.println("Celebrity: " + celebrity3); // Expected: 0
    }

    /**
     * Helper method to print the adjacency matrix
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Abstract class that provides the knows API
     * This is similar to what would be provided in LeetCode Problem 277
     */
    public static abstract class Relation {
        /**
         * Returns whether person i knows person j
         */
        public abstract boolean knows(int i, int j);
    }

    /**
     * Solution implementation that extends the Relation class
     */
    public static class Solution extends Relation {
        private final int[][] graph; // Adjacency matrix representation

        /**
         * Constructor that initializes the graph
         */
        public Solution(int[][] relationGraph) {
            this.graph = relationGraph;
        }

        /**
         * Implementation of the knows API
         */
        @Override
        public boolean knows(int i, int j) {
            return graph[i][j] == 1;
        }

        /**
         * Finds the celebrity in the group
         *
         * @param n Number of people
         * @return ID of the celebrity or -1 if none exists
         */
        public int findCelebrity(int n) {
            // Try each person as a potential celebrity candidate
            for (int candidate = 0; candidate < n; candidate++) {
                int otherPerson;

                // Check if this candidate could be the celebrity
                for (otherPerson = 0; otherPerson < n; otherPerson++) {
                    if (candidate == otherPerson) continue; // Skip self-comparison

                    // If candidate knows other person OR other person doesn't know candidate
                    // Then candidate cannot be the celebrity
                    if (knows(candidate, otherPerson) || !knows(otherPerson, candidate)) {
                        break;
                    }
                }

                // If we checked all other people and none violated our conditions
                // Then we found our celebrity
                if (otherPerson == n) {
                    return candidate;
                }
            }

            // No celebrity found
            return -1;
        }
    }
}