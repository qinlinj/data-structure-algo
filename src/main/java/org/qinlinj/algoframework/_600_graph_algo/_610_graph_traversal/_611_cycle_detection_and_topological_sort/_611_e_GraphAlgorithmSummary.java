package org.qinlinj.algoframework._600_graph_algo._610_graph_traversal._611_cycle_detection_and_topological_sort;

/**
 * Graph Algorithms: Cycle Detection and Topological Sort Summary
 * <p>
 * This class provides a comprehensive overview of the graph algorithms covered in the previous examples.
 * It summarizes key concepts, compares different approaches, and highlights practical applications.
 * <p>
 * 1. CYCLE DETECTION ALGORITHMS
 * - Definition: Algorithms to determine if a directed graph contains any cycles
 * - Applications:
 * * Dependency resolution in build systems
 * * Deadlock detection in resource allocation
 * * Course prerequisite verification
 * <p>
 * A. DFS Approach (_611_a_CycleDetectionDFS):
 * - Uses a recursive depth-first traversal
 * - Maintains two arrays:
 * * onPath[] - tracks nodes in current exploration path
 * * visited[] - tracks nodes that have been fully processed
 * - Cycle is detected when a node already on the current path is encountered again
 * - Time Complexity: O(V + E) where V is number of vertices, E is number of edges
 * - Space Complexity: O(V) for the tracking arrays and recursion stack
 * <p>
 * B. BFS Approach (_611_c_CycleDetectionBFS):
 * - Uses Kahn's algorithm (in-degree based approach)
 * - Starts with nodes having zero in-degree (no dependencies)
 * - Processes these nodes and removes their outgoing edges
 * - If not all nodes can be processed, a cycle exists
 * - Time Complexity: O(V + E)
 * - Space Complexity: O(V) for the queue and in-degree array
 * <p>
 * 2. TOPOLOGICAL SORT ALGORITHMS
 * - Definition: Process of arranging nodes in a directed acyclic graph (DAG) in a linear order
 * such that for every directed edge (u, v), node u comes before node v
 * - Properties:
 * * Only possible if the graph has no cycles (is a DAG)
 * * Multiple valid orderings may exist for the same graph
 * - Applications:
 * * Course scheduling
 * * Task scheduling with dependencies
 * * Build systems (determining compilation order)
 * * Package installations with dependencies
 * <p>
 * A. DFS Approach (_611_b_TopologicalSortDFS):
 * - Based on the property: reverse of post-order traversal gives topological order
 * - Performs a DFS traversal and records nodes in post-order
 * - Reverses the post-order list to get the topological sort
 * - Time Complexity: O(V + E)
 * - Space Complexity: O(V) for the result list and tracking arrays
 * <p>
 * B. BFS Approach (_611_d_TopologicalSortBFS):
 * - Uses Kahn's algorithm
 * - Processes nodes in order of their dependency level (level-by-level)
 * - Records the order of processed nodes to get the topological sort
 * - Time Complexity: O(V + E)
 * - Space Complexity: O(V) for the queue, result array, and in-degree array
 * <p>
 * 3. CHOOSING BETWEEN DFS AND BFS APPROACHES
 * - DFS Advantages:
 * * More intuitive for understanding recursive dependencies
 * * Often easier to implement for graph traversal
 * * Better for finding specific paths in a graph
 * <p>
 * - BFS Advantages:
 * * Provides a more "level-ordered" topological sort (nodes grouped by dependency depth)
 * * Often more intuitive for breadth-based problems
 * * Easier to parallelize (nodes at same level can be processed concurrently)
 * * Typically more efficient for sparse graphs
 * <p>
 * 4. IMPLEMENTATION CONSIDERATIONS
 * - Graph Representation: Adjacency lists often preferred for sparse graphs
 * - Edge Direction: Critical to define properly (this implementation uses "from prerequisite to dependent")
 * - Cycle Handling: Important to detect cycles before attempting topological sort
 * - Multiple Solutions: Be aware that multiple valid topological orderings may exist
 */
public class _611_e_GraphAlgorithmSummary {

    public static void main(String[] args) {
        System.out.println("Graph Algorithms: Cycle Detection and Topological Sort");
        System.out.println("=======================================================");

        // Example graph for demonstration
        int numNodes = 6;
        int[][] edges = {
                {1, 0}, // 0 is prerequisite for 1
                {2, 0}, // 0 is prerequisite for 2
                {3, 1}, // 1 is prerequisite for 3
                {3, 2}, // 2 is prerequisite for 3
                {4, 3}, // 3 is prerequisite for 4
                {5, 4}  // 4 is prerequisite for 5
        };

        System.out.println("Graph Description:");
        System.out.println("- 6 nodes (0-5)");
        System.out.println("- Directed edges representing prerequisites");
        System.out.println("- Example: [1,0] means node 0 is a prerequisite for node 1");
        System.out.println("- This graph forms a linear dependency: 0 → (1,2) → 3 → 4 → 5");
        System.out.println();

        // Demonstrate cycle detection approaches
        System.out.println("Cycle Detection:");
        demonstrateCycleDetection(numNodes, edges);

        // Demonstrate topological sort approaches
        System.out.println("\nTopological Sort:");
        demonstrateTopologicalSort(numNodes, edges);

        // Add a cycle to the graph and test again
        System.out.println("\nModifying the graph to introduce a cycle...");
        int[][] edgesWithCycle = new int[edges.length + 1][2];
        System.arraycopy(edges, 0, edgesWithCycle, 0, edges.length);
        edgesWithCycle[edges.length] = new int[]{0, 5}; // Add edge 5 -> 0, creating a cycle

        System.out.println("Cycle Detection on Modified Graph:");
        demonstrateCycleDetection(numNodes, edgesWithCycle);

        System.out.println("\nTopological Sort on Modified Graph:");
        demonstrateTopologicalSort(numNodes, edgesWithCycle);
    }

    private static void demonstrateCycleDetection(int numNodes, int[][] edges) {
        // DFS approach
        _611_a_CycleDetectionDFS dfsSolution = new _611_a_CycleDetectionDFS();
        boolean dfsCycleResult = dfsSolution.canFinish(numNodes, edges);
        System.out.println("DFS Approach: " +
                (dfsCycleResult ? "No cycle detected" : "Cycle detected"));

        // BFS approach
        _611_c_CycleDetectionBFS bfsSolution = new _611_c_CycleDetectionBFS();
        boolean bfsCycleResult = bfsSolution.canFinish(numNodes, edges);
        System.out.println("BFS Approach: " +
                (bfsCycleResult ? "No cycle detected" : "Cycle detected"));
    }

    private static void demonstrateTopologicalSort(int numNodes, int[][] edges) {
        // DFS approach
        _611_b_TopologicalSortDFS dfsSolution = new _611_b_TopologicalSortDFS();
        int[] dfsOrder = dfsSolution.findOrder(numNodes, edges);
        System.out.print("DFS Approach: ");
        printArray(dfsOrder);

        // BFS approach
        _611_d_TopologicalSortBFS bfsSolution = new _611_d_TopologicalSortBFS();
        int[] bfsOrder = bfsSolution.findOrder(numNodes, edges);
        System.out.print("BFS Approach: ");
        printArray(bfsOrder);
    }

    private static void printArray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("[] (No valid topological order, cycle detected)");
            return;
        }

        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}