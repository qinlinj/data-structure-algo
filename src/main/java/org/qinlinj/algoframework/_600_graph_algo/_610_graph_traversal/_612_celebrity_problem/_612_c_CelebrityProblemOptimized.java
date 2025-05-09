package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._612_celebrity_problem;

import java.util.*;

/**
 * Optimized Solution to the Celebrity Problem
 * <p>
 * Key Insight:
 * If we compare any two people A and B, at least one of them cannot be a celebrity:
 * - If A knows B, then A cannot be a celebrity (celebrities don't know anyone)
 * - If A doesn't know B, then B cannot be a celebrity (everyone knows a celebrity)
 * <p>
 * Approach:
 * 1. Start with all people as candidates
 * 2. Repeatedly take two candidates, compare them, and eliminate one
 * 3. Continue until only one candidate remains
 * 4. Verify if this final candidate is actually a celebrity
 * <p>
 * Time Complexity: O(n)
 * - While eliminating candidates: O(n) comparisons
 * - Final verification: O(n) comparisons
 * - Overall: O(n)
 * <p>
 * Space Complexity: O(n)
 * - We use a queue to store all candidates
 * <p>
 * This approach significantly improves upon the brute force solution by
 * leveraging the property that at most one celebrity can exist in the group.
 */
public class _612_c_CelebrityProblemOptimized {

    /**
     * Test cases to demonstrate the optimized solution
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
         * Finds the celebrity in the group using optimized approach
         *
         * @param n Number of people
         * @return ID of the celebrity or -1 if none exists
         */
        public int findCelebrity(int n) {
            if (n == 1) return 0; // With only one person, they are the default celebrity

            // Initialize queue with all candidates
            LinkedList<Integer> candidateQueue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                candidateQueue.addLast(i);
            }

            // Continue eliminating candidates until only one remains
            while (candidateQueue.size() >= 2) {
                // Take two candidates to compare
                int candidateA = candidateQueue.removeFirst();
                int candidateB = candidateQueue.removeFirst();

                // Determine which one to eliminate
                if (knows(candidateA, candidateB) || !knows(candidateB, candidateA)) {
                    // candidateA cannot be the celebrity (either knows someone or isn't known by someone)
                    // Keep candidateB as a potential celebrity
                    candidateQueue.addFirst(candidateB);
                } else {
                    // candidateB cannot be the celebrity
                    // Keep candidateA as a potential celebrity
                    candidateQueue.addFirst(candidateA);
                }
            }

            // The final remaining candidate
            int potentialCelebrity = candidateQueue.removeFirst();

            // Verify if this candidate is actually a celebrity by checking all relationships
            for (int other = 0; other < n; other++) {
                if (other == potentialCelebrity) continue; // Skip self-comparison

                // Check both conditions:
                // 1. Celebrity doesn't know anyone else
                // 2. Everyone else knows the celebrity
                if (knows(potentialCelebrity, other) || !knows(other, potentialCelebrity)) {
                    return -1; // Not a celebrity
                }
            }

            // Confirmed celebrity
            return potentialCelebrity;
        }
    }
}