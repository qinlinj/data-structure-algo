package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._612_celebrity_problem;

import java.util.*;

/**
 * Summary of the Celebrity Problem and Its Solutions
 * <p>
 * This class provides a comprehensive summary of the Celebrity Problem,
 * including implementation of all three approaches (brute force, optimized, and optimal)
 * and a demonstration of their performance on various test cases.
 * <p>
 * The Celebrity Problem demonstrates how careful analysis of problem constraints
 * can lead to significant algorithmic improvements, reducing the time complexity
 * from O(n²) to O(n) and space complexity from O(n) to O(1).
 */
public class _612_f_CelebrityProblemSummary {

    /**
     * Brute Force Solution: O(n²) time, O(1) space
     */
    public static int findCelebrityBruteForce(Relation relation, int n) {
        for (int candidate = 0; candidate < n; candidate++) {
            int other;
            for (other = 0; other < n; other++) {
                if (candidate == other) continue;
                if (relation.knows(candidate, other) || !relation.knows(other, candidate)) {
                    break;
                }
            }
            if (other == n) {
                return candidate;
            }
        }
        return -1;
    }

    /**
     * Optimized Solution: O(n) time, O(n) space
     * Uses a queue-based elimination approach
     */
    public static int findCelebrityOptimized(Relation relation, int n) {
        if (n == 1) return 0;

        // Initialize queue with all candidates
        java.util.LinkedList<Integer> candidates = new java.util.LinkedList<>();
        for (int i = 0; i < n; i++) {
            candidates.addLast(i);
        }

        // Eliminate candidates until only one remains
        while (candidates.size() >= 2) {
            int a = candidates.removeFirst();
            int b = candidates.removeFirst();

            if (relation.knows(a, b) || !relation.knows(b, a)) {
                // a cannot be celebrity
                candidates.addFirst(b);
            } else {
                // b cannot be celebrity
                candidates.addFirst(a);
            }
        }

        // Verify the remaining candidate
        int candidate = candidates.removeFirst();
        for (int other = 0; other < n; other++) {
            if (candidate == other) continue;
            if (relation.knows(candidate, other) || !relation.knows(other, candidate)) {
                return -1;
            }
        }

        return candidate;
    }

    /**
     * Optimal Solution: O(n) time, O(1) space
     */
    public static int findCelebrityOptimal(Relation relation, int n) {
        // Start with first person as candidate
        int candidate = 0;

        // Eliminate candidates
        for (int other = 1; other < n; other++) {
            if (relation.knows(candidate, other) || !relation.knows(other, candidate)) {
                candidate = other;
            }
        }

        // Verify the candidate
        for (int other = 0; other < n; other++) {
            if (candidate == other) continue;
            if (relation.knows(candidate, other) || !relation.knows(other, candidate)) {
                return -1;
            }
        }

        return candidate;
    }

    /**
     * Main method to demonstrate and compare all three approaches
     */
    public static void main(String[] args) {
        System.out.println("CELEBRITY PROBLEM SUMMARY");
        System.out.println("=========================");

        // Test cases
        int[][][] testCases = {
                // Example 1: Person 2 is the celebrity
                {
                        {0, 1, 1},
                        {0, 0, 1},
                        {0, 0, 0}
                },
                // Example 2: No celebrity
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 0, 0}
                },
                // Example 3: Person 0 is the celebrity
                {
                        {0, 0, 0},
                        {1, 0, 0},
                        {1, 0, 0}
                },
                // Example 4: Larger example, person 4 is the celebrity
                {
                        {0, 1, 1, 1, 1},
                        {0, 0, 1, 1, 1},
                        {0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0}
                }
        };

        // Test each approach on all test cases
        for (int i = 0; i < testCases.length; i++) {
            int[][] graph = testCases[i];
            int n = graph.length;

            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Adjacency Matrix:");
            for (int[] row : graph) {
                System.out.println(Arrays.toString(row));
            }

            // Create a relation implementation for this test case
            RelationImplementation relation = new RelationImplementation(graph);

            // Test brute force approach
            relation.resetApiCallCount();
            int resultBrute = findCelebrityBruteForce(relation, n);
            int bruteCalls = relation.getApiCallCount();

            // Test optimized approach
            relation.resetApiCallCount();
            int resultOptimized = findCelebrityOptimized(relation, n);
            int optimizedCalls = relation.getApiCallCount();

            // Test optimal approach
            relation.resetApiCallCount();
            int resultOptimal = findCelebrityOptimal(relation, n);
            int optimalCalls = relation.getApiCallCount();

            // Report results
            System.out.println("\nResults:");
            System.out.println("Brute Force: Celebrity = " + resultBrute + ", API Calls = " + bruteCalls);
            System.out.println("Optimized:   Celebrity = " + resultOptimized + ", API Calls = " + optimizedCalls);
            System.out.println("Optimal:     Celebrity = " + resultOptimal + ", API Calls = " + optimalCalls);

            // Verify all approaches return the same result
            if (resultBrute == resultOptimized && resultOptimized == resultOptimal) {
                System.out.println("All approaches agree on the result.");
            } else {
                System.out.println("WARNING: Approaches returned different results!");
            }

            // Compare API call efficiency
            System.out.println("\nEfficiency Comparison:");
            System.out.println("Brute Force / Optimal = " + (bruteCalls / (double) optimalCalls) + "x more API calls");
            System.out.println("Optimized / Optimal = " + (optimizedCalls / (double) optimalCalls) + "x more API calls");
            System.out.println("------------------------------");
        }

        // Summary
        System.out.println("\nKEY TAKEAWAYS");
        System.out.println("=============");
        System.out.println("1. The Celebrity Problem demonstrates how smart algorithm design can significantly");
        System.out.println("   improve performance from O(n²) to O(n) time complexity.");
        System.out.println("2. The optimal solution uses a simple elimination technique that requires only two passes");
        System.out.println("   through the data, keeping space complexity at O(1).");
        System.out.println("3. This problem showcases the importance of identifying opportunities to eliminate");
        System.out.println("   candidates efficiently based on problem constraints.");
        System.out.println("4. In real-world applications where the 'knows' relationship might be expensive to check");
        System.out.println("   (e.g., database queries), the optimal solution provides substantial performance benefits.");
    }

    /**
     * Interface for the "knows" API
     */
    public interface Relation {
        boolean knows(int i, int j);
    }

    /**
     * Implementation of the Relation interface using an adjacency matrix
     */
    public static class RelationImplementation implements Relation {
        private final int[][] graph;
        private int apiCallCount = 0;

        public RelationImplementation(int[][] graph) {
            this.graph = graph;
        }

        @Override
        public boolean knows(int i, int j) {
            apiCallCount++; // Track API calls for performance analysis
            return graph[i][j] == 1;
        }

        public int getApiCallCount() {
            return apiCallCount;
        }

        public void resetApiCallCount() {
            apiCallCount = 0;
        }
    }
}