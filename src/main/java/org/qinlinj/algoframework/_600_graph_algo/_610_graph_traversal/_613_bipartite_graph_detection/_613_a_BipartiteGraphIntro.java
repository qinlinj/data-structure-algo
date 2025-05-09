package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._613_bipartite_graph_detection;

/**
 * Introduction to Bipartite Graphs
 * <p>
 * A bipartite graph is a graph whose vertices can be divided into two disjoint sets
 * such that:
 * 1. Every edge connects vertices from different sets
 * 2. No edge connects vertices within the same set
 * <p>
 * Conceptually, a bipartite graph can be visualized through the "two-coloring problem":
 * Can we color all vertices using only two colors such that no adjacent vertices
 * have the same color?
 * <p>
 * Real-world Applications:
 * - Matching problems (e.g., job assignments, marriage matching)
 * - Representing relationships between different types of entities
 * - Storage optimization for certain types of data (e.g., movie-actor relationships)
 */
public class _613_a_BipartiteGraphIntro {

    public static void main(String[] args) {
        System.out.println("BIPARTITE GRAPHS");
        System.out.println("================");
        System.out.println();

        System.out.println("Definition:");
        System.out.println("A bipartite graph is a graph whose vertices can be divided into two");
        System.out.println("disjoint sets such that every edge connects a vertex from the first set");
        System.out.println("to a vertex in the second set, with no edges connecting vertices within");
        System.out.println("the same set.");
        System.out.println();

        System.out.println("Alternative Definition (Two-Coloring Problem):");
        System.out.println("A graph is bipartite if its vertices can be colored using only two colors");
        System.out.println("such that no adjacent vertices share the same color.");
        System.out.println();

        System.out.println("Visual Examples:");
        displayBipartiteExample();
        displayNonBipartiteExample();

        System.out.println("Practical Applications:");
        System.out.println("1. Data Storage Optimization");
        System.out.println("   Example: Movie-Actor Relationships");
        System.out.println("   - Movies and actors naturally form a bipartite structure");
        System.out.println("   - Each edge connects a movie to an actor who appeared in it");
        System.out.println("   - No edges connect movies to movies or actors to actors");
        System.out.println("   - This structure is more efficient than using two separate hash maps");
        System.out.println();

        System.out.println("2. Matching Problems");
        System.out.println("   - Job assignments (matching workers to jobs)");
        System.out.println("   - Resource allocation");
        System.out.println("   - Stable marriage problem");
        System.out.println();

        System.out.println("3. Algorithm Foundations");
        System.out.println("   - Used in advanced graph algorithms like maximum flow");
        System.out.println("   - Important in network optimization problems");
    }

    private static void displayBipartiteExample() {
        System.out.println("\nExample of a Bipartite Graph:");
        System.out.println("   A --- B");
        System.out.println("  /|     |\\");
        System.out.println(" / |     | \\");
        System.out.println("C  |     |  D");
        System.out.println(" \\ |     | /");
        System.out.println("  \\|     |/");
        System.out.println("   E --- F");
        System.out.println();
        System.out.println("This is bipartite because we can color {A, D, E} with one color");
        System.out.println("and {B, C, F} with another color, and no adjacent vertices have the same color.");
        System.out.println();
    }

    private static void displayNonBipartiteExample() {
        System.out.println("Example of a Non-Bipartite Graph:");
        System.out.println("    A --- B");
        System.out.println("   /|     |");
        System.out.println("  / |     |");
        System.out.println(" C--+-----+");
        System.out.println();
        System.out.println("This graph contains an odd-length cycle (A-B-C-A), making it non-bipartite.");
        System.out.println("It's impossible to color it with just two colors while ensuring");
        System.out.println("that adjacent vertices have different colors.");
        System.out.println();
    }
}