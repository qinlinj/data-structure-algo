package org.qinlinj.algoframework._600_graph_algo._630_graph_optimization._634_dijkstra_practice;

/**
 * Summary of Dijkstra's Algorithm Practice Problems
 * <p>
 * Knowledge Points:
 * 1. Adapting Dijkstra's algorithm to different problem domains:
 * - Network delay (finding maximum of all shortest paths)
 * - Path with minimum effort (minimizing maximum edge weight)
 * - Path with maximum probability (maximizing product of edge weights)
 * <p>
 * 2. Key Insights from Practice:
 * - Dijkstra finds "optimal" paths, not just shortest paths
 * - The algorithm works when edge weights consistently increase or decrease path value
 * - The priority queue comparison function can be inverted to find maximums
 * - Edge weights can represent various metrics: time, height difference, probability
 * <p>
 * 3. Modifications to Standard Dijkstra:
 * - For minimum effort: track max edge weight instead of sum
 * - For maximum probability: invert priority queue ordering
 * - For grid problems: cells are nodes, adjacent cells are connected by edges
 * <p>
 * 4. Common Pattern in Solutions:
 * - Identify what constitutes nodes and edges in the problem
 * - Determine what the edge weights represent
 * - Define the path optimization criteria (min sum, min max, max product)
 * - Adjust Dijkstra's algorithm accordingly
 */

public class _634_d_DijkstraPracticeSummary {

    public static void main(String[] args) {
        System.out.println("Dijkstra's Algorithm Practice Summary");
        System.out.println("=====================================");

        compareProblemTypes();
        discussModifications();
        presentUnifiedFramework();
    }

    /**
     * Compare and contrast the different problem types
     */
    private static void compareProblemTypes() {
        System.out.println("\n1. COMPARISON OF PROBLEM TYPES");
        System.out.println("----------------------------");

        System.out.println("A. Network Delay Time (LeetCode 743)");
        System.out.println("   - Graph representation: Regular directed weighted graph");
        System.out.println("   - Edge weights: Signal transmission times (delay)");
        System.out.println("   - Optimization goal: Minimize sum of edge weights");
        System.out.println("   - Final answer: Maximum of all shortest paths from source");
        System.out.println("   - Why Dijkstra works: Each added edge increases the total delay");

        System.out.println("\nB. Path with Minimum Effort (LeetCode 1631)");
        System.out.println("   - Graph representation: Grid where cells are nodes");
        System.out.println("   - Edge weights: Absolute height differences between adjacent cells");
        System.out.println("   - Optimization goal: Minimize maximum edge weight along path");
        System.out.println("   - Final answer: Minimum possible maximum effort");
        System.out.println("   - Why Dijkstra works: Each path's 'effort' is determined by its maximum edge,");
        System.out.println("     and we're still finding a minimum value");

        System.out.println("\nC. Path with Maximum Probability (LeetCode 1514)");
        System.out.println("   - Graph representation: Regular undirected weighted graph");
        System.out.println("   - Edge weights: Success probabilities (between 0 and 1)");
        System.out.println("   - Optimization goal: Maximize product of edge weights");
        System.out.println("   - Final answer: Maximum probability path from start to end");
        System.out.println("   - Why Dijkstra works: Each added edge decreases the overall probability");
        System.out.println("     (since all weights are ≤ 1), and we invert the priority queue to find maximums");
    }

    /**
     * Discuss the modifications needed for each problem type
     */
    private static void discussModifications() {
        System.out.println("\n2. MODIFICATIONS TO STANDARD DIJKSTRA");
        System.out.println("-----------------------------------");

        System.out.println("A. For Standard Shortest Paths (Network Delay):");
        System.out.println("   - Priority queue: Sort by increasing distance");
        System.out.println("   - Update rule: distTo[next] = distTo[current] + weight");
        System.out.println("   - Comparison: if (newDist < distTo[next])");
        System.out.println("   - This minimizes the sum of edge weights");

        System.out.println("\nB. For Minimum Maximum Edge (Minimum Effort):");
        System.out.println("   - Priority queue: Sort by increasing effort");
        System.out.println("   - Update rule: effortTo[next] = max(effortTo[current], current_edge_weight)");
        System.out.println("   - Comparison: if (newEffort < effortTo[next])");
        System.out.println("   - This minimizes the maximum edge weight along any path");

        System.out.println("\nC. For Maximum Product (Maximum Probability):");
        System.out.println("   - Priority queue: Sort by DECREASING probability");
        System.out.println("   - Update rule: probTo[next] = probTo[current] * edge_prob");
        System.out.println("   - Comparison: if (newProb > probTo[next])");
        System.out.println("   - This maximizes the product of edge weights (probabilities)");

        System.out.println("\nKey differences:");
        System.out.println("1. The optimization metric (sum, max, product)");
        System.out.println("2. The priority queue ordering (min vs max)");
        System.out.println("3. The update rule for new paths");
    }

    /**
     * Present a unified framework for applying Dijkstra to different problems
     */
    private static void presentUnifiedFramework() {
        System.out.println("\n3. UNIFIED FRAMEWORK FOR APPLYING DIJKSTRA");
        System.out.println("---------------------------------------");

        System.out.println("Step 1: Identify the graph structure");
        System.out.println("- What are the nodes? (Points, cells, network nodes)");
        System.out.println("- What are the edges? (Connections, adjacent cells)");
        System.out.println("- What do edge weights represent? (Time, difference, probability)");

        System.out.println("\nStep 2: Determine the optimization goal");
        System.out.println("- Minimize sum of weights? (Classic shortest path)");
        System.out.println("- Minimize maximum weight? (Bottleneck problems)");
        System.out.println("- Maximize product of weights? (Probability problems)");
        System.out.println("- Some other optimization criteria?");

        System.out.println("\nStep 3: Adjust the Dijkstra algorithm accordingly");
        System.out.println("- Define the State class with appropriate fields");
        System.out.println("- Set the priority queue comparison function");
        System.out.println("- Implement the correct update rule for new paths");
        System.out.println("- Determine the termination condition");

        System.out.println("\nStep 4: Extract the final answer");
        System.out.println("- For all-pairs problems: process all nodes and find min/max/etc.");
        System.out.println("- For point-to-point problems: return result when destination is reached");

        System.out.println("\nGeneral Dijkstra Template for diverse problems:");
        printDijkstraTemplate();
    }

    /**
     * Print a generic Dijkstra template that can be adapted for different problems
     */
    private static void printDijkstraTemplate() {
        String template =
                "```java\n" +
                        "// Generic Dijkstra template adaptable to various problems\n" +
                        "public OptimalValue dijkstra(Graph graph, Node start, Node end) {\n" +
                        "    // Initialize optimal values for all nodes\n" +
                        "    OptimalValue[] optimalTo = new OptimalValue[graph.size()];\n" +
                        "    for (int i = 0; i < optimalTo.length; i++) {\n" +
                        "        optimalTo[i] = WORST_POSSIBLE_VALUE; // Infinity, -1, 0.0, etc.\n" +
                        "    }\n" +
                        "    \n" +
                        "    // Priority queue with appropriate comparison function\n" +
                        "    // For minimization problems:\n" +
                        "    PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> {\n" +
                        "        return a.value.compareTo(b.value); // Increasing order\n" +
                        "    });\n" +
                        "    // For maximization problems:\n" +
                        "    // return b.value.compareTo(a.value); // Decreasing order\n" +
                        "    \n" +
                        "    // Start from the source node\n" +
                        "    pq.offer(new State(start, INITIAL_VALUE)); // 0, 1.0, etc.\n" +
                        "    \n" +
                        "    while (!pq.isEmpty()) {\n" +
                        "        State state = pq.poll();\n" +
                        "        Node current = state.node;\n" +
                        "        OptimalValue currentValue = state.value;\n" +
                        "        \n" +
                        "        // Skip if we've found a better path already\n" +
                        "        if (IS_WORSE_THAN(currentValue, optimalTo[current])) {\n" +
                        "            continue;\n" +
                        "        }\n" +
                        "        \n" +
                        "        // Record the optimal value for this node\n" +
                        "        optimalTo[current] = currentValue;\n" +
                        "        \n" +
                        "        // For point-to-point problems, return when destination is reached\n" +
                        "        if (current.equals(end)) {\n" +
                        "            return currentValue;\n" +
                        "        }\n" +
                        "        \n" +
                        "        // Process all neighbors\n" +
                        "        for (Edge edge : graph.getNeighbors(current)) {\n" +
                        "            Node next = edge.to;\n" +
                        "            Weight weight = edge.weight;\n" +
                        "            \n" +
                        "            // Calculate new value based on the problem type:\n" +
                        "            // - For min sum: newValue = currentValue + weight\n" +
                        "            // - For min max: newValue = max(currentValue, weight)\n" +
                        "            // - For max product: newValue = currentValue * weight\n" +
                        "            OptimalValue newValue = CALCULATE_NEW_VALUE(currentValue, weight);\n" +
                        "            \n" +
                        "            // Add to queue if we've found a better path\n" +
                        "            if (IS_BETTER_THAN(newValue, optimalTo[next])) {\n" +
                        "                pq.offer(new State(next, newValue));\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "    \n" +
                        "    // For all-paths problems, process the final result\n" +
                        "    // (e.g., find maximum of all shortest paths)\n" +
                        "    return PROCESS_FINAL_RESULT(optimalTo);\n" +
                        "}\n" +
                        "```";

        System.out.println(template);

        System.out.println("\nThis template can be adapted to all the problem types we've seen:");
        System.out.println("- For Network Delay: OptimalValue is int, INITIAL_VALUE is 0, calculate sum of weights");
        System.out.println("- For Minimum Effort: OptimalValue is int, INITIAL_VALUE is 0, calculate max of weights");
        System.out.println("- For Maximum Probability: OptimalValue is double, INITIAL_VALUE is 1.0, calculate product of weights");
    }
}