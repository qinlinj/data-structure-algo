package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_framework._751_bfs_algo; /**
 * BFS Algorithm Summary
 * --------------------
 * <p>
 * This class provides a comprehensive overview of the BFS algorithm concepts
 * covered in the previous implementation classes. It includes comparisons between
 * different approaches and a summary of key takeaways.
 * <p>
 * Key Concepts Summarized:
 * <p>
 * 1. The Essence of BFS
 * - BFS is fundamentally a tree/graph traversal algorithm
 * - It expands level by level, exploring all nodes at a current depth before moving deeper
 * - Perfect for finding shortest paths in unweighted graphs
 * <p>
 * 2. Abstraction Process
 * - The key skill is abstracting real-world problems into graph structures
 * - Nodes represent states, edges represent valid transitions between states
 * - A visited set prevents cycles and repeated exploration
 * <p>
 * 3. Implementation Approaches
 * - Standard BFS: Uses a queue, expands from source until reaching target
 * - Bidirectional BFS: Expands from both source and target, stops when fronts meet
 * - Time complexity remains O(V+E) for both, but bidirectional BFS often performs better in practice
 * <p>
 * 4. Problem-Solving Strategy
 * - Identify the states (nodes) and transitions (edges)
 * - Determine how to represent states efficiently (strings, numbers, etc.)
 * - Apply the BFS framework to find the shortest path
 * - Optimize with bidirectional BFS when applicable
 */

public class _751_e_BFSSummary {

    /**
     * Compares different BFS implementation approaches on the same problem
     */
    public static void main(String[] args) {
        System.out.println("BFS Algorithm Summary and Comparison");
        System.out.println("====================================");

        // Compare standard BFS vs bidirectional BFS
        compareBFSImplementations();

        // Demonstrate problem abstraction
        demonstrateProblemAbstraction();
    }

    /**
     * Compares standard BFS and bidirectional BFS implementations
     * on the Open Lock problem
     */
    private static void compareBFSImplementations() {
        System.out.println("\n1. Standard BFS vs Bidirectional BFS:");
        System.out.println("-----------------------------------");

        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        // Create instances of both implementations
        _751_c_OpenLock standardBFS = new _751_c_OpenLock();
        _751_d_BidirectionalBFS bidirectionalBFS = new _751_d_BidirectionalBFS();

        // Time standard BFS
        long startTime = System.currentTimeMillis();
        int standardResult = standardBFS.openLock(deadends, target);
        long standardDuration = System.currentTimeMillis() - startTime;

        // Time bidirectional BFS
        startTime = System.currentTimeMillis();
        int bidirectionalResult = bidirectionalBFS.openLock(deadends, target);
        long bidirectionalDuration = System.currentTimeMillis() - startTime;

        System.out.println("Problem: Open Lock with target = " + target);
        System.out.println("Standard BFS result: " + standardResult + " steps (took " + standardDuration + " ms)");
        System.out.println("Bidirectional BFS result: " + bidirectionalResult + " steps (took " + bidirectionalDuration + " ms)");

        if (standardDuration > 0 && bidirectionalDuration > 0) {
            System.out.println("Performance ratio: Bidirectional is approximately " +
                    Math.round((double) standardDuration / bidirectionalDuration * 100) / 100.0 +
                    "x faster in this case");
        }

        System.out.println("\nWhy Bidirectional BFS is often faster:");
        System.out.println("- Reduces the search space significantly");
        System.out.println("- Instead of exploring all nodes within distance d from start,");
        System.out.println("  it explores nodes within distance d/2 from both start and target");
        System.out.println("- For a branching factor b, standard BFS explores O(b^d) nodes");
        System.out.println("  while bidirectional BFS explores O(2b^(d/2)) ≈ O(sqrt(b^d)) nodes");
    }

    /**
     * Demonstrates how to abstract real-world problems into graph structures
     * for BFS traversal
     */
    private static void demonstrateProblemAbstraction() {
        System.out.println("\n2. Problem Abstraction Process:");
        System.out.println("------------------------------");

        System.out.println("Example 1: Sliding Puzzle");
        System.out.println("- States (Nodes): Each possible arrangement of the puzzle");
        System.out.println("- Transitions (Edges): Moving the empty space in valid directions");
        System.out.println("- State Representation: Flattened string \"123450\" for target state");
        System.out.println("- Challenge: Mapping 2D positions to 1D string indices");

        System.out.println("\nExample 2: Open Lock");
        System.out.println("- States (Nodes): Each possible combination \"0000\" to \"9999\"");
        System.out.println("- Transitions (Edges): Rotating one digit up or down");
        System.out.println("- State Representation: String of 4 digits");
        System.out.println("- Challenge: Handling deadends and avoiding cycles");

        System.out.println("\n3. Key Takeaways:");
        System.out.println("-----------------");
        System.out.println("1. BFS is ideal for finding shortest paths in unweighted graphs");
        System.out.println("2. The critical skill is abstracting problems into graph structures");
        System.out.println("3. Use standard BFS for simplicity, bidirectional for performance");
        System.out.println("4. Always track visited states to prevent cycles and repeated work");
        System.out.println("5. Choose appropriate state representations for efficient storage and comparison");
    }
}