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
    private int v;
    private int w;
    private int weight;

    /**
     * Constructs a weighted edge between vertices v and w with the specified weight.
     *
     * @param v      First vertex of the edge
     * @param w      Second vertex of the edge
     * @param weight The weight or cost associated with this edge
     *               <p>
     *               Time Complexity: O(1) - constant time operation
     *               <p>
     *               WeightedEdge road = new WeightedEdge(0, 1, 215);
     *               // Creates an edge between vertex 0 (New York) and vertex 1 (Boston) with a weight of 215 miles
     */
    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.getWeight();
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
