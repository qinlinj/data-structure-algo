package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._632_prim_minimum_spanning_tree;

/**
 * Prim's Algorithm Concepts
 * <p>
 * Knowledge Points:
 * 1. Prim's algorithm is a greedy algorithm used to find the minimum spanning tree in a weighted graph
 * <p>
 * 2. Comparison with Kruskal's algorithm:
 * - Both are greedy algorithms for finding minimum spanning trees
 * - Kruskal's processes edges in sorted order (weight) and uses Union-Find to avoid cycles
 * - Prim's uses BFS-like approach and grows a tree from a starting vertex
 * - Kruskal's sorts all edges at the beginning, while Prim's uses a priority queue for dynamic ordering
 * <p>
 * 3. Properties of a Minimum Spanning Tree (MST):
 * - It is a tree (no cycles) that includes all vertices of the graph
 * - It has exactly (n-1) edges where n is the number of vertices
 * - The sum of edge weights is minimized
 * <p>
 * 4. Time Complexity:
 * - O(E log E) where E is the number of edges
 * - Can be optimized with specialized priority queue implementations
 * <p>
 * 5. Space Complexity:
 * - O(V + E) where V is the number of vertices
 */
public class _632_a_PrimConcept {

    public static void main(String[] args) {
        System.out.println("Prim's Algorithm for Minimum Spanning Tree");
        System.out.println("===========================================");

        printDefinition();
        printComparison();
        printComplexity();
        printApplications();
    }

    private static void printDefinition() {
        System.out.println("\n1. WHAT IS PRIM'S ALGORITHM?");
        System.out.println("----------------------------");
        System.out.println("Prim's algorithm is a greedy algorithm used to find the minimum spanning tree");
        System.out.println("in a connected, undirected, weighted graph. It starts from a single vertex and");
        System.out.println("grows the tree one vertex at a time, always adding the lowest-weight edge that");
        System.out.println("connects a vertex in the tree to a vertex outside the tree.");

        System.out.println("\nCore properties of a Minimum Spanning Tree (MST):");
        System.out.println("- It is a tree (contains no cycles)");
        System.out.println("- It includes all vertices in the graph");
        System.out.println("- The sum of edge weights is minimized");
        System.out.println("- It contains exactly (n-1) edges, where n is the number of vertices");
    }

    private static void printComparison() {
        System.out.println("\n2. PRIM'S vs KRUSKAL'S ALGORITHM");
        System.out.println("-------------------------------");
        System.out.println("Both algorithms find the minimum spanning tree, but with different approaches:");

        System.out.println("\nKruskal's Algorithm:");
        System.out.println("- Sorts all edges by weight at the beginning");
        System.out.println("- Processes edges in order of increasing weight");
        System.out.println("- Uses Union-Find data structure to avoid cycles");
        System.out.println("- Builds a forest that gradually merges into a single MST");

        System.out.println("\nPrim's Algorithm:");
        System.out.println("- Starts from a single vertex and grows outward");
        System.out.println("- Uses a priority queue to dynamically determine the next lowest-weight edge");
        System.out.println("- Uses a boolean array (similar to 'visited' in BFS) to avoid cycles");
        System.out.println("- Always maintains a single connected tree");

        System.out.println("\nAnalogy:");
        System.out.println("- Kruskal's: Building a network by connecting the closest cities first,");
        System.out.println("  regardless of their location");
        System.out.println("- Prim's: Starting from one city and expanding by connecting to the");
        System.out.println("  nearest neighboring city not yet in the network");
    }

    private static void printComplexity() {
        System.out.println("\n3. TIME & SPACE COMPLEXITY");
        System.out.println("-------------------------");
        System.out.println("Time Complexity:");
        System.out.println("- O(E log E) where E is the number of edges");
        System.out.println("- The main operation is extracting the minimum weight edge from the");
        System.out.println("  priority queue, which takes O(log E) time, and we do this for each edge");

        System.out.println("\nSpace Complexity:");
        System.out.println("- O(V + E) where V is the number of vertices");
        System.out.println("- We need O(V) space for the boolean array and O(E) space for the priority queue");

        System.out.println("\nPerformance Characteristics:");
        System.out.println("- Prim's algorithm tends to perform better for dense graphs");
        System.out.println("- Kruskal's algorithm tends to perform better for sparse graphs");
        System.out.println("- Both have the same asymptotic time complexity: O(E log E)");
    }

    private static void printApplications() {
        System.out.println("\n4. APPLICATIONS");
        System.out.println("---------------");
        System.out.println("Prim's algorithm is used in various applications including:");
        System.out.println("- Network design (electricity, telecommunications, transportation)");
        System.out.println("- Circuit design optimization");
        System.out.println("- Cluster analysis in data mining");
        System.out.println("- Approximation algorithms for traveling salesman problem");
        System.out.println("- Image segmentation in computer vision");
        System.out.println("- Designing road/railway networks with minimum total length");
    }
}