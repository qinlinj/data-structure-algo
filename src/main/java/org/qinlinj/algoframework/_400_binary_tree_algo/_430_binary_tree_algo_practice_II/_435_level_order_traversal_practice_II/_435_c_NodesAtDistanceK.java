package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._435_level_order_traversal_practice_II; /**
 * Problem 863: All Nodes Distance K in Binary Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, a target node, and an integer k, return an array of the values
 * of all nodes that have a distance k from the target node.
 * <p>
 * Key Concepts:
 * - Tree-to-Graph Conversion: Convert the binary tree to an undirected graph by adding parent references
 * - Breadth-First Search (BFS) from Target: Use BFS to find nodes at exactly distance k from the target
 * - Visited Node Tracking: Prevent revisiting nodes during traversal
 * - Three-Way Path Expansion: Explore in all directions (left, right, parent) from each node
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) for storing the parent references and visited set
 */

import java.util.*;

class _435_c_NodesAtDistanceK {
    // Map to store parent references for each node
    private Map<Integer, TreeNode> parentMap = new HashMap<>();

    // Main method for testing
    public static void main(String[] args) {
        _435_c_NodesAtDistanceK solution = new _435_c_NodesAtDistanceK();

        // Create example tree: [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = solution.new TreeNode(3);
        TreeNode target = solution.new TreeNode(5);
        root.left = target;
        root.right = solution.new TreeNode(1);
        root.left.left = solution.new TreeNode(6);
        root.left.right = solution.new TreeNode(2);
        root.right.left = solution.new TreeNode(0);
        root.right.right = solution.new TreeNode(8);
        root.left.right.left = solution.new TreeNode(7);
        root.left.right.right = solution.new TreeNode(4);

        // Find nodes at distance 2 from target
        List<Integer> result = solution.distanceK(root, target, 2);
        Collections.sort(result); // Sort for consistent output

        System.out.println("Nodes at distance 2 from target (value 5): " + result);
        // Expected output: [1, 4, 7] (order may vary)

        // Test with the alternative approach
        List<Integer> resultAlt = solution.distanceKAlternative(root, target, 2);
        Collections.sort(resultAlt); // Sort for consistent output

        System.out.println("Alternative approach result: " + resultAlt);
        // Expected output: [1, 4, 7] (order may vary)

        // Example 2: Edge case with k = 0
        List<Integer> resultK0 = solution.distanceK(root, target, 0);
        System.out.println("Nodes at distance 0 from target: " + resultK0);
        // Expected output: [5] (just the target itself)

        // Example 3: Edge case with k greater than tree height
        List<Integer> resultLargeK = solution.distanceK(root, target, 10);
        System.out.println("Nodes at distance 10 from target: " + resultLargeK);
        // Expected output: [] (no nodes at such a large distance)
    }

    /**
     * Main function to find all nodes at distance k from target
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Step 1: Populate the parent map by traversing the tree
        buildParentMap(root, null);

        // Step 2: Perform BFS starting from the target node
        Queue<TreeNode> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        // Initialize BFS with the target node
        queue.offer(target);
        visited.add(target.val);
        int distance = 0;

        // BFS loop
        while (!queue.isEmpty() && distance <= k) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // If we've reached distance k, add current node value to result
                if (distance == k) {
                    result.add(current.val);
                }

                // Explore in all three directions (left, right, parent)

                // Left child
                if (current.left != null && !visited.contains(current.left.val)) {
                    visited.add(current.left.val);
                    queue.offer(current.left);
                }

                // Right child
                if (current.right != null && !visited.contains(current.right.val)) {
                    visited.add(current.right.val);
                    queue.offer(current.right);
                }

                // Parent
                TreeNode parent = parentMap.get(current.val);
                if (parent != null && !visited.contains(parent.val)) {
                    visited.add(parent.val);
                    queue.offer(parent);
                }
            }

            // Increment distance as we move to the next level
            distance++;
        }

        return result;
    }

    /**
     * Helper method to build parent references for all nodes
     */
    private void buildParentMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        // Store parent reference
        parentMap.put(node.val, parent);

        // Recursively process children
        buildParentMap(node.left, node);
        buildParentMap(node.right, node);
    }

    /**
     * Alternative approach using DFS to build the graph and find nodes
     */
    public List<Integer> distanceKAlternative(TreeNode root, TreeNode target, int k) {
        // Build adjacency list representation of the tree
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        // Perform BFS from target node using the graph
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        queue.offer(target.val);
        visited.add(target.val);
        int distance = 0;

        while (!queue.isEmpty() && distance <= k) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                int currentVal = queue.poll();

                if (distance == k) {
                    result.add(currentVal);
                }

                // Explore all neighbors
                List<Integer> neighbors = graph.getOrDefault(currentVal, new ArrayList<>());
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            distance++;
        }

        return result;
    }

    /**
     * Helper method to build graph representation of the tree
     */
    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }

        // Initialize adjacency list for current node if needed
        if (!graph.containsKey(node.val)) {
            graph.put(node.val, new ArrayList<>());
        }

        // Add edges in both directions (since we need to traverse up and down)
        if (parent != null) {
            // Add edge from current node to parent
            graph.get(node.val).add(parent.val);

            // Add edge from parent to current node
            if (!graph.containsKey(parent.val)) {
                graph.put(parent.val, new ArrayList<>());
            }
            graph.get(parent.val).add(node.val);
        }

        // Process children
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}