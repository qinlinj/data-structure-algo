package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._612_celebrity_problem;

/**
 * Introduction to the Celebrity Problem
 * <p>
 * The Celebrity Problem is a classic graph algorithm problem:
 * <p>
 * Definition:
 * In a group of n people, a "celebrity" is someone who:
 * 1. Is known by everyone else in the group
 * 2. Doesn't know anyone else in the group
 * <p>
 * Graph Representation:
 * - Each person is a node in a directed graph
 * - A directed edge from person A to person B means "A knows B"
 * - A celebrity node has:
 * * In-degree of n-1 (everyone knows the celebrity)
 * * Out-degree of 0 (celebrity knows no one)
 * <p>
 * Problem Statement:
 * Given a graph representing the "knows" relationship between n people,
 * determine if there is a celebrity. If so, return their ID; otherwise, return -1.
 * <p>
 * The graph is represented as an adjacency matrix where:
 * - graph[i][j] = 1 means person i knows person j
 * - graph[i][j] = 0 means person i doesn't know person j
 * <p>
 * In LeetCode's version (Problem 277), instead of directly accessing the adjacency matrix,
 * an API function boolean knows(int i, int j) is provided to check relationships.
 */
public class _612_a_CelebrityProblemIntro {

    /**
     * Problem description and examples
     */
    public static void main(String[] args) {
        System.out.println("THE CELEBRITY PROBLEM");
        System.out.println("=====================");
        System.out.println("In a group of n people, a 'celebrity' is someone who:");
        System.out.println("1. Is known by everyone else");
        System.out.println("2. Doesn't know anyone else");
        System.out.println();

        System.out.println("Example:");
        System.out.println("Consider a group of 3 people (0, 1, 2) with the following relationships:");
        System.out.println("- Person 0 knows person 1");
        System.out.println("- Person 0 knows person 2");
        System.out.println("- Person 1 knows person 2");
        System.out.println("- Person 2 knows no one");
        System.out.println();

        System.out.println("This can be represented as the adjacency matrix:");
        System.out.println("  | 0 1 2");
        System.out.println("--+------");
        System.out.println("0 | 0 1 1");
        System.out.println("1 | 0 0 1");
        System.out.println("2 | 0 0 0");
        System.out.println();

        System.out.println("Where graph[i][j] = 1 means person i knows person j");
        System.out.println();

        System.out.println("In this example, person 2 is the celebrity because:");
        System.out.println("- Everyone else knows person 2 (columns for person 2 are all 1s except self)");
        System.out.println("- Person 2 knows no one (row for person 2 is all 0s)");
        System.out.println();

        System.out.println("The problem can be visualized as a directed graph:");
        System.out.println("  0 --→ 1");
        System.out.println("  ↓     ↓");
        System.out.println("  └--→ 2");
        System.out.println();
        System.out.println("Where node 2 has in-degree 2 (everyone points to it) and out-degree 0 (it points to no one)");
    }

    /**
     * Interface to represent the "knows" relationship API
     * (similar to what would be provided by LeetCode)
     */
    public interface Relation {
        /**
         * Returns whether person i knows person j
         *
         * @param i Person ID
         * @param j Person ID
         * @return true if i knows j, false otherwise
         */
        boolean knows(int i, int j);
    }
}