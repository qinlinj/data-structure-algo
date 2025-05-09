package org.qinlinj.algoframework._600_graph_algo._620_union_find._621_union_find_algorithm;

/**
 * Union-Find Algorithm: Introduction
 * <p>
 * This class introduces the Union-Find (Disjoint Set) data structure,
 * which is a specialized algorithm for handling dynamic connectivity problems.
 * <p>
 * Key Concepts:
 * 1. Dynamic Connectivity - Determining if two elements are connected
 * 2. Equivalence Relations - Properties of connectivity (reflexive, symmetric, transitive)
 * 3. Connected Components - Groups of connected elements
 */
public class _621_a_UnionFindIntro {

    /**
     * Main method to demonstrate and explain the Union-Find algorithm concept
     */
    public static void main(String[] args) {
        System.out.println("UNION-FIND ALGORITHM INTRODUCTION");
        System.out.println("=================================");

        // Explain the dynamic connectivity problem
        explainDynamicConnectivity();

        // Describe the API and operations
        describeAPI();

        // Explain equivalence relations
        explainEquivalenceRelations();

        // Show a simple example
        demonstrateExample();
    }

    /**
     * Explains the concept of dynamic connectivity
     */
    private static void explainDynamicConnectivity() {
        System.out.println("\n1. DYNAMIC CONNECTIVITY");
        System.out.println("------------------------");
        System.out.println("Dynamic connectivity deals with maintaining connected components");
        System.out.println("in a graph as connections are added. It answers the question:");
        System.out.println("\"Are two elements connected?\" as the graph evolves over time.");
        System.out.println();
        System.out.println("Consider 10 nodes labeled 0-9 that are initially disconnected:");
        System.out.println();
        System.out.println("    0   1   2   3   4   5   6   7   8   9");
        System.out.println();
        System.out.println("If we connect nodes 0 and 1, they form a connected component:");
        System.out.println();
        System.out.println("   0-1   2   3   4   5   6   7   8   9");
        System.out.println();
        System.out.println("If we then connect 1 and 2, all three nodes become connected:");
        System.out.println();
        System.out.println("  0-1-2   3   4   5   6   7   8   9");
        System.out.println();
    }

    /**
     * Describes the API of a Union-Find data structure
     */
    private static void describeAPI() {
        System.out.println("\n2. UNION-FIND API");
        System.out.println("----------------");
        System.out.println("The Union-Find data structure provides the following operations:");
        System.out.println();
        System.out.println("- union(p, q):     Connect elements p and q");
        System.out.println("- connected(p, q): Check if p and q are connected");
        System.out.println("- count():         Return the number of connected components");
        System.out.println();
        System.out.println("A basic API would look like this:");
        System.out.println();
        System.out.println("class UF {");
        System.out.println("    // Connect p and q");
        System.out.println("    public void union(int p, int q);");
        System.out.println("");
        System.out.println("    // Check if p and q are connected");
        System.out.println("    public boolean connected(int p, int q);");
        System.out.println("");
        System.out.println("    // Return number of connected components");
        System.out.println("    public int count();");
        System.out.println("}");
        System.out.println();
    }

    /**
     * Explains the properties of equivalence relations
     */
    private static void explainEquivalenceRelations() {
        System.out.println("\n3. EQUIVALENCE RELATION PROPERTIES");
        System.out.println("---------------------------------");
        System.out.println("The 'connected' relation in Union-Find is an equivalence relation,");
        System.out.println("meaning it has the following properties:");
        System.out.println();
        System.out.println("1. Reflexive:   Every element is connected to itself");
        System.out.println("   - Node p is connected to p");
        System.out.println();
        System.out.println("2. Symmetric:   If p is connected to q, then q is connected to p");
        System.out.println("   - If p and q are connected, then q and p are connected");
        System.out.println();
        System.out.println("3. Transitive:  If p is connected to q and q is connected to r,");
        System.out.println("                then p is connected to r");
        System.out.println("   - If p and q are connected, and q and r are connected,");
        System.out.println("     then p and r are connected");
        System.out.println();
    }

    /**
     * Demonstrates a simple example of Union-Find operations
     */
    private static void demonstrateExample() {
        System.out.println("\n4. SIMPLE EXAMPLE");
        System.out.println("----------------");
        System.out.println("Starting with 10 isolated nodes (0-9):");
        System.out.println("- Initial connected components: 10");
        System.out.println("- connected(0, 1)? false");
        System.out.println();
        System.out.println("After calling union(0, 1):");
        System.out.println("- Connected components: 9");
        System.out.println("- connected(0, 1)? true");
        System.out.println();
        System.out.println("After calling union(1, 2):");
        System.out.println("- Connected components: 8");
        System.out.println("- connected(0, 2)? true (transitivity)");
        System.out.println();
        System.out.println("This allows us to efficiently track connectivity in large networks,");
        System.out.println("such as social networks, computer networks, or image processing.");
    }

    /**
     * Placeholder of the Union-Find API for reference
     */
    public interface UnionFind {
        /**
         * Connect elements p and q
         */
        void union(int p, int q);

        /**
         * Check if elements p and q are connected
         */
        boolean connected(int p, int q);

        /**
         * Return the number of connected components
         */
        int count();
    }
}