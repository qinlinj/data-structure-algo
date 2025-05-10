package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._633_dijkstra_algorithm; /**
 * Correctness Proof and Analysis of Dijkstra's Algorithm
 * <p>
 * Knowledge Points:
 * 1. Correctness Principle:
 * - Dijkstra's algorithm is based on the principle of "expanding certainty"
 * - When a node is first dequeued, its distance is guaranteed to be minimal
 * <p>
 * 2. Proof Strategy:
 * - Start with what we know with certainty (distance to source = 0)
 * - Expand our certainty by always choosing the node with smallest known distance
 * - Prove that this greedy approach guarantees optimal solutions
 * <p>
 * 3. Why Negative Edges Break the Algorithm:
 * - With negative edges, the assumption that adding more edges increases path length fails
 * - A longer path might become shorter by incorporating negative-weight edges
 * <p>
 * 4. Application of the Greedy Principle:
 * - At each step, we make the locally optimal choice (node with smallest distance)
 * - This local optimality leads to global optimality (shortest paths to all nodes)
 */

import java.util.*;

public class _633_c_DijkstraCorrectness {

    public static void main(String[] args) {
        System.out.println("Correctness Proof and Analysis of Dijkstra's Algorithm");
        System.out.println("=====================================================");

        // Create a demonstration graph
        Graph graph = createExampleGraph();

        // Demonstrate the proof of correctness
        proveCorrectness(graph);

        // Show why negative edges break the algorithm
        negativeEdgeExample();
    }

    /**
     * Create an example graph for demonstration
     */
    private static Graph createExampleGraph() {
        Graph graph = new Graph(5);

        // Add edges (from, to, weight)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 7);

        return graph;
    }

    /**
     * Demonstrate the proof of correctness with a step-by-step explanation
     */
    private static void proveCorrectness(Graph graph) {
        System.out.println("\n1. THE PRINCIPLE OF EXPANDING CERTAINTY");
        System.out.println("---------------------------------------");
        System.out.println("Dijkstra's algorithm follows a principle of 'expanding certainty':");
        System.out.println("1. We start with what we know for certain: distance to source = 0");
        System.out.println("2. At each step, we choose the node with the smallest known distance");
        System.out.println("3. When we choose a node, we can prove its current distance is optimal");
        System.out.println("4. We then expand our certainty to its neighbors");

        System.out.println("\nLet's demonstrate this with an example graph:");
        printGraph(graph);

        System.out.println("\n2. STEP-BY-STEP PROOF WITH EXAMPLE");
        System.out.println("----------------------------------");

        // Demonstration using the example graph
        int source = 0;

        // Phase 1: Initial certainty
        System.out.println("\nPhase 1: Initial Certainty");
        System.out.println("- We know with certainty: distance(0) = 0");
        System.out.println("- Known edges from node 0:");
        System.out.println("  0 -> 1 with weight 4");
        System.out.println("  0 -> 2 with weight 3");
        System.out.println("- Without negative edges, no path to 1 or 2 can be shorter than direct edges");
        System.out.println("- Candidates for next node: (1, 4) and (2, 3)");

        // Phase 2: First expansion
        System.out.println("\nPhase 2: First Expansion");
        System.out.println("- Node 2 has the smallest distance (3)");
        System.out.println("- We now know with certainty: distance(2) = 3");
        System.out.println("- Proof: Any other path to 2 must use more edges, which with non-negative");
        System.out.println("  weights would result in a greater distance");
        System.out.println("- Examining node 2's edges:");
        System.out.println("  2 -> 1 with weight 1 gives path 0->2->1 with total weight 3+1=4");
        System.out.println("  2 -> 3 with weight 4 gives path 0->2->3 with total weight 3+4=7");
        System.out.println("  2 -> 4 with weight 5 gives path 0->2->4 with total weight 3+5=8");
        System.out.println("- Candidates for next node: (1, 4), (1, 4 from 2), (3, 7), (4, 8)");

        // Phase 3: Second expansion
        System.out.println("\nPhase 3: Second Expansion");
        System.out.println("- Nodes 1 via 0->1 and 1 via 0->2->1 both have distance 4");
        System.out.println("- Let's say 1 via 0->2->1 is dequeued first");
        System.out.println("- We now know with certainty: distance(1) = 4");
        System.out.println("- Proof: We've already examined all possible paths to node 1 through");
        System.out.println("  nodes with finalized distances (0 and 2)");
        System.out.println("- Any other path would need to go through nodes 3 or 4, which have");
        System.out.println("  distances greater than 4, so they can't provide a shorter path to 1");

        System.out.println("\nWe continue this process, always selecting the node with the smallest");
        System.out.println("known distance. Since we only expand from nodes whose shortest distances");
        System.out.println("are already certain, each new selection is also guaranteed to be optimal.");

        System.out.println("\n3. FORMAL PROOF BY INDUCTION");
        System.out.println("--------------------------");
        System.out.println("We can prove Dijkstra's correctness more formally by induction:");

        System.out.println("\nBase Case:");
        System.out.println("- The distance to the source node is correctly set to 0");

        System.out.println("\nInductive Hypothesis:");
        System.out.println("- Assume that for some k≥0, the algorithm has found the shortest paths");
        System.out.println("  to k different nodes");

        System.out.println("\nInductive Step:");
        System.out.println("- When we select the (k+1)th node u with current distance d:");
        System.out.println("  1. This distance d must be the true shortest path to u");
        System.out.println("  2. Proof by contradiction: If there were a shorter path to u,");
        System.out.println("     it would have to go through some node v not yet processed");
        System.out.println("  3. But if that were true, then v would have a shorter distance than u");
        System.out.println("     and would have been selected before u");
        System.out.println("  4. This contradicts our selection of u, proving that d is optimal");

        System.out.println("\nBy induction, Dijkstra's algorithm correctly finds the shortest path");
        System.out.println("to all reachable nodes.");
    }

    /**
     * Demonstrate why negative edges break Dijkstra's algorithm
     */
    private static void negativeEdgeExample() {
        System.out.println("\n4. WHY NEGATIVE EDGES BREAK DIJKSTRA'S ALGORITHM");
        System.out.println("------------------------------------------------");

        // Create a graph with a negative edge
        Graph negativeGraph = new Graph(4);
        negativeGraph.addEdge(0, 1, 1);
        negativeGraph.addEdge(0, 2, 5);
        negativeGraph.addEdge(1, 2, -3); // Negative edge!
        negativeGraph.addEdge(1, 3, 2);
        negativeGraph.addEdge(2, 3, 1);

        System.out.println("Consider this graph with a negative edge:");
        printGraph(negativeGraph);

        System.out.println("\nDijkstra's Algorithm Execution (will be incorrect):");
        System.out.println("1. Start at node 0 with distance 0");
        System.out.println("2. Explore neighbors: (1, 1) and (2, 5)");
        System.out.println("3. Dequeue node 1 (smallest distance)");
        System.out.println("4. Set distance(1) = 1 (FINAL)");
        System.out.println("5. Explore neighbors of 1: update distance(2) = 1+(-3) = -2, distance(3) = 1+2 = 3");
        System.out.println("6. Dequeue node 2 (distance -2)");
        System.out.println("7. Set distance(2) = -2 (FINAL)");
        System.out.println("8. Explore neighbors of 2: update distance(3) = -2+1 = -1");

        System.out.println("\nHowever, when processing node 1, we've already marked its distance as FINAL");
        System.out.println("But later we discover a shorter path: 0->2->1 with total weight 5+(-3) = 2");
        System.out.println("This contradicts our earlier finalization of distance(1) = 1");

        System.out.println("\nThe Problem:");
        System.out.println("- Dijkstra's greedy approach assumes adding more edges increases path length");
        System.out.println("- With negative edges, a longer path (more edges) might have smaller total weight");
        System.out.println("- This breaks the 'expanding certainty' principle");

        System.out.println("\nSolution for Negative Edges:");
        System.out.println("- Use Bellman-Ford algorithm instead (handles negative edges)");
        System.out.println("- Or Floyd-Warshall for all-pairs shortest paths");
    }

    /**
     * Helper method to print the graph structure
     */
    private static void printGraph(Graph graph) {
        System.out.println("\nGraph Structure:");
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("Node " + i + " -> ");
            List<Edge> neighbors = graph.getNeighbors(i);
            if (neighbors.isEmpty()) {
                System.out.println("No outgoing edges");
                continue;
            }

            for (int j = 0; j < neighbors.size(); j++) {
                Edge edge = neighbors.get(j);
                System.out.print(edge.to + " (weight " + edge.weight + ")");
                if (j < neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Simple graph class for visualization and demonstration
     */
    static class Graph {
        private int numNodes;
        private List<Edge>[] adjList;

        @SuppressWarnings("unchecked")
        public Graph(int numNodes) {
            this.numNodes = numNodes;
            adjList = new ArrayList[numNodes];
            for (int i = 0; i < numNodes; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int weight) {
            adjList[from].add(new Edge(from, to, weight));
        }

        public List<Edge> getNeighbors(int node) {
            return adjList[node];
        }

        public int size() {
            return numNodes;
        }
    }

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}