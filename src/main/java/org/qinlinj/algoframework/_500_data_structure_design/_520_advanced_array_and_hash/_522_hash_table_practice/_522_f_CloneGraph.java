package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_f_CloneGraph
 * <p>
 * LeetCode #133: Clone Graph
 * <p>
 * Problem:
 * Given a reference to a node in a connected undirected graph, return a deep copy
 * of the graph. Each node in the graph contains a value and a list of its neighbors.
 * <p>
 * Approach:
 * This is similar to the copy list with random pointer problem but with graph traversal.
 * We use a depth-first search (DFS) approach with a HashMap to:
 * 1. Keep track of nodes we've already cloned to avoid cycles
 * 2. Map original nodes to their cloned counterparts
 * <p>
 * During our traversal:
 * - For each node, create a clone if it doesn't exist
 * - Recursively clone all neighbors
 * - Connect the cloned node to its cloned neighbors
 * <p>
 * Time Complexity: O(N + E) where N is the number of nodes and E is the number of edges
 * Space Complexity: O(N) for the HashMap and the recursion stack
 */

import java.util.*;

public class _522_f_CloneGraph {

    // Map to track visited nodes (original -> clone)
    private HashMap<Node, Node> visited = new HashMap<>();

    // Utility method to create a test graph
    private static Node createTestGraph(int[][] adjList) {
        if (adjList.length == 0) {
            return null;
        }

        // Create nodes
        Node[] nodes = new Node[adjList.length];
        for (int i = 0; i < adjList.length; i++) {
            nodes[i] = new Node(i + 1);
        }

        // Connect edges
        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList[i].length; j++) {
                nodes[i].neighbors.add(nodes[adjList[i][j] - 1]);
            }
        }

        return nodes[0];
    }

    // Utility method to print a graph (BFS traversal)
    private static void printGraph(Node node) {
        if (node == null) {
            System.out.println("Empty graph");
            return;
        }

        HashMap<Node, Boolean> visited = new HashMap<>();
        printGraphDFS(node, visited);
        System.out.println();
    }

    private static void printGraphDFS(Node node, HashMap<Node, Boolean> visited) {
        if (visited.containsKey(node)) {
            return;
        }

        visited.put(node, true);

        System.out.print(node.val + " -> [");
        for (int i = 0; i < node.neighbors.size(); i++) {
            System.out.print(node.neighbors.get(i).val);
            if (i < node.neighbors.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("] ");

        for (Node neighbor : node.neighbors) {
            printGraphDFS(neighbor, visited);
        }
    }

    public static void main(String[] args) {
        _522_f_CloneGraph solution = new _522_f_CloneGraph();

        // Test case: adjList = [[2,4],[1,3],[2,4],[1,3]]
        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};

        Node originalGraph = createTestGraph(adjList);
        System.out.println("Original graph:");
        printGraph(originalGraph);

        Node clonedGraph = solution.cloneGraph(originalGraph);
        System.out.println("Cloned graph:");
        printGraph(clonedGraph);

        // Verify deep copy
        System.out.println("Is deep copy? " + (originalGraph != clonedGraph));
    }

    public Node cloneGraph(Node node) {
        // Handle edge case
        if (node == null) {
            return null;
        }

        // If already visited, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new clone node
        Node cloneNode = new Node(node.val, new ArrayList<>());

        // Add to visited map before recursion to avoid cycles
        visited.put(node, cloneNode);

        // Recursively clone neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    /**
     * Alternative implementation that separates node creation from connection
     */
    public Node cloneGraphTwoPass(Node node) {
        if (node == null) {
            return null;
        }

        // Map from original node to its clone
        HashMap<Node, Node> originalToClone = new HashMap<>();

        // First pass: Create all nodes via DFS
        createNodes(node, originalToClone);

        // Second pass: Connect all edges
        connectEdges(node, originalToClone, new HashMap<>());

        // Return the clone of the starting node
        return originalToClone.get(node);
    }

    private void createNodes(Node node, HashMap<Node, Node> map) {
        // If already visited, return
        if (map.containsKey(node)) {
            return;
        }

        // Create a new clone node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Recursively create nodes for all neighbors
        for (Node neighbor : node.neighbors) {
            createNodes(neighbor, map);
        }
    }

    private void connectEdges(Node node, HashMap<Node, Node> map, HashMap<Node, Boolean> visited) {
        // If already processed, return
        if (visited.containsKey(node)) {
            return;
        }

        // Mark as visited
        visited.put(node, true);

        Node clone = map.get(node);

        // Connect all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(map.get(neighbor));
            connectEdges(neighbor, map, visited);
        }
    }

    // Definition for a Node in the graph
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}