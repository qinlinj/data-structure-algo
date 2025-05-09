package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._612_celebrity_problem;

import java.util.*;

/**
 * Optimal Solution to the Celebrity Problem
 * <p>
 * This approach further optimizes the elimination process by avoiding the use
 * of additional data structures like a queue.
 * <p>
 * Key Insight:
 * We can use a single variable to track the current celebrity candidate and
 * eliminate candidates one by one by comparing them.
 * <p>
 * Approach:
 * 1. Start by assuming person 0 is the celebrity candidate
 * 2. Compare with each subsequent person and update candidate accordingly:
 * - If current candidate knows another person, they can't be a celebrity
 * - If current candidate doesn't know another person, the other person can't be a celebrity
 * 3. After all comparisons, verify if our final candidate is actually a celebrity
 * <p>
 * Time Complexity: O(n)
 * - Initial elimination pass: O(n) comparisons
 * - Final verification: O(n) comparisons
 * - Overall: O(n)
 * <p>
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space
 * <p>
 * This approach achieves the optimal solution in terms of both time and space efficiency.
 */
public class _612_d_CelebrityProblemOptimal {

    /**
     * Test cases to demonstrate the optimal solution
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

        // Example 4: Larger example with person 3 as celebrity
        int[][] graph4 = {
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        Solution solution4 = new Solution(graph4);
        int celebrity4 = solution4.findCelebrity(4);
        System.out.println("\nExample 4 - Adjacency Matrix (4x4):");
        printMatrix(graph4);
        System.out.println("Celebrity: " + celebrity4); // Expected: 3
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
         * Finds the celebrity in the group using optimal approach
         *
         * @param n Number of people
         * @return ID of the celebrity or -1 if none exists
         */
        public int findCelebrity(int n) {
            // Start with the assumption that person 0 is the celebrity
            int candidateCelebrity = 0;

            // Compare the candidate with every other person
            for (int other = 1; other < n; other++) {
                if (knows(candidateCelebrity, other) || !knows(other, candidateCelebrity)) {
                    // If our current candidate knows someone or isn't known by someone
                    // Then they can't be the celebrity - update our candidate
                    candidateCelebrity = other;
                }
                // Otherwise, other cannot be the celebrity, so we continue with our current candidate
            }

            // At this point, candidateCelebrity is our potential celebrity
            // But we need to verify against all people
            for (int other = 0; other < n; other++) {
                if (candidateCelebrity == other) continue; // Skip self-comparison

                // Validate that:
                // 1. Candidate doesn't know anyone else
                // 2. Everyone else knows the candidate
                if (knows(candidateCelebrity, other) || !knows(other, candidateCelebrity)) {
                    return -1; // Not a celebrity
                }
            }

            // Verified celebrity
            return candidateCelebrity;
        }
    }
}