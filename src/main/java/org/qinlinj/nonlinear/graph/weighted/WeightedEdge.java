package org.qinlinj.nonlinear.graph.weighted;

/**
 * WeightedEdge Class
 * <p>
 * Concept and Principles:
 * A weighted edge represents a connection between two vertices in a graph where the connection has an associated weight or cost.
 * Weights can represent various real-world metrics such as distances, costs, capacities, or any numerical value that quantifies
 * the relationship between vertices.
 * <p>
 * Advantages of Weighted Edges:
 * 1. More Realistic Modeling: Many real-world problems have connections with varying strengths or costs.
 * For example, roads between cities have different distances, network links have different bandwidths.
 * 2. Optimization Capability: Allows algorithms to find optimal paths (shortest, cheapest, fastest) rather than just any path.
 * 3. Priority Determination: The weight helps determine which edges to consider first in algorithms like Dijkstra's or Prim's.
 * 4. Quantitative Analysis: Enables precise measurement of path costs rather than just counting the number of edges.
 * <p>
 * Example Visualization:
 * Consider a road network where:
 * - Vertex 0 represents New York
 * - Vertex 1 represents Boston
 * - Vertex 2 represents Washington DC
 * - Edge (0,1) with weight 215 represents the 215-mile road from New York to Boston
 * - Edge (0,2) with weight 225 represents the 225-mile road from New York to Washington DC
 * <p>
 * In this class, the edge between New York and Boston would be represented as:
 * new WeightedEdge(0, 1, 215)
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
    private int v;        // First vertex of the edge
    private int w;        // Second vertex of the edge
    private int weight;   // Weight/cost of the edge

    /**
     * Constructs a weighted edge between vertices v and w with the specified weight.
     *
     * @param v      First vertex of the edge
     * @param w      Second vertex of the edge
     * @param weight The weight or cost associated with this edge
     *               <p>
     *               Time Complexity: O(1) - constant time operation
     *               <p>
     *               Example:
     *               WeightedEdge road = new WeightedEdge(0, 1, 215);
     *               // Creates an edge between vertex 0 (New York) and vertex 1 (Boston) with a weight of 215 miles
     */
    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the first vertex of this edge.
     *
     * @return The first vertex identifier
     * <p>
     * Time Complexity: O(1) - constant time operation
     * <p>
     * Example:
     * WeightedEdge road = new WeightedEdge(0, 1, 215);
     * int city1 = road.getV(); // Returns 0 (New York)
     */
    public int getV() {
        return v;
    }

    /**
     * Returns the second vertex of this edge.
     *
     * @return The second vertex identifier
     * <p>
     * Time Complexity: O(1) - constant time operation
     * <p>
     * Example:
     * WeightedEdge road = new WeightedEdge(0, 1, 215);
     * int city2 = road.getW(); // Returns 1 (Boston)
     */
    public int getW() {
        return w;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The weight or cost associated with this edge
     * <p>
     * Time Complexity: O(1) - constant time operation
     * <p>
     * Example:
     * WeightedEdge road = new WeightedEdge(0, 1, 215);
     * int distance = road.getWeight(); // Returns 215 (miles)
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Compares this edge with another edge based on their weights.
     * This method is essential for algorithms that need to process edges in order of their weights,
     * such as Kruskal's algorithm for finding minimum spanning trees.
     *
     * @param o The other weighted edge to compare with
     * @return A negative value if this edge's weight is less than the other edge's weight,
     * zero if they are equal, or a positive value if this edge's weight is greater
     * <p>
     * Time Complexity: O(1) - constant time operation
     * <p>
     * Example:
     * WeightedEdge road1 = new WeightedEdge(0, 1, 215); // New York to Boston (215 miles)
     * WeightedEdge road2 = new WeightedEdge(0, 2, 225); // New York to Washington DC (225 miles)
     * int comparison = road1.compareTo(road2); // Returns -10, indicating road1 has lower weight
     * <p>
     * Note: This implementation may cause integer overflow for very large weight differences.
     * A more robust implementation would be: return Integer.compare(weight, o.getWeight());
     */
    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.getWeight();
    }

    /**
     * Returns a string representation of this weighted edge.
     * Useful for debugging and logging purposes.
     *
     * @return A string representing this edge in the format "WeightedEdge{v=x, w=y, weight=z}"
     * <p>
     * Time Complexity: O(1) - constant time operation
     * <p>
     * Example:
     * WeightedEdge road = new WeightedEdge(0, 1, 215);
     * String description = road.toString(); // Returns "WeightedEdge{v=0, w=1, weight=215}"
     */
    @Override
    public String toString() {
        return "WeightedEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
