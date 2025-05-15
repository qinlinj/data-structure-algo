package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * All Nodes Distance K in Binary Tree (LeetCode 863)
 * -------------------------------------------------
 * <p>
 * Summary:
 * This problem asks us to find all nodes that are at a distance K from a given target node in a binary tree.
 * The distance is defined as the number of edges that need to be traversed to reach from one node to another.
 * <p>
 * Key Concepts:
 * 1. Converting a binary tree to an undirected graph
 * 2. BFS traversal to find nodes at a specific distance
 * 3. Tracking parent-child relationships to enable upward traversal
 * <p>
 * Approach:
 * 1. First, we build a mapping from each node to its parent
 * (binary trees only have references to children, not parents)
 * 2. Then, starting from the target node, we use BFS to explore in all directions:
 * - Left child
 * - Right child
 * - Parent node
 * 3. We track visited nodes to avoid cycles and count distance as we go
 * 4. When we reach distance K, we collect those nodes as our answer
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) for the parent mapping and the queue
 */

import java.util.*;

public class _752_d_NodesDistanceK {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_d_NodesDistanceK solution = new _752_d_NodesDistanceK();

        // Create a binary tree:
        //        3
        //       / \
        //      5   1
        //     / \ / \
        //    6  2 0  8
        //      / \
        //     7   4
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Target is node with value 5, and K=2
        List<Integer> result = solution.distanceK(root, root.left, 2);

        System.out.println("Nodes at distance 2 from node 5:");
        System.out.println(result); // Should output [7, 4, 1]

        // Another example
        // Target is node with value 3, and K=3
        result = solution.distanceK(root, root, 3);

        System.out.println("Nodes at distance 3 from node 3:");
        System.out.println(result); // Should output [7, 4]
    }

    /**
     * Find all nodes at distance K from the target node
     * @param root Root of the binary tree
     * @param target Target node
     * @param k Distance
     * @return List of values of all nodes at distance K from target
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Map to store parent references (node.val -> parent node)
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        // Build the parent mapping
        buildParentMap(root, null, parentMap);

        // BFS to find nodes at distance K
        Queue<TreeNode> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        // Start BFS from the target node
        queue.offer(target);
        visited.add(target.val);

        int distance = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // If we've reached distance K, collect all nodes at this level
            if (distance == k) {
                for (int i = 0; i < levelSize; i++) {
                    result.add(queue.poll().val);
                }
                return result;
            }

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Explore in all three directions: left, right, and parent

                // Try left child
                if (current.left != null && !visited.contains(current.left.val)) {
                    visited.add(current.left.val);
                    queue.offer(current.left);
                }

                // Try right child
                if (current.right != null && !visited.contains(current.right.val)) {
                    visited.add(current.right.val);
                    queue.offer(current.right);
                }

                // Try parent node
                TreeNode parent = parentMap.get(current.val);
                if (parent != null && !visited.contains(parent.val)) {
                    visited.add(parent.val);
                    queue.offer(parent);
                }
            }

            // Increment distance after processing each level
            distance++;
        }

        // If we exit the loop, we didn't find K nodes
        return result;
    }

    /**
     * Builds a mapping from each node to its parent
     * @param node Current node
     * @param parent Parent of the current node
     * @param parentMap Map to store the parent references
     */
    private void buildParentMap(TreeNode node, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (node == null) {
            return;
        }

        // Store the parent reference
        parentMap.put(node.val, parent);

        // Recursively process children
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }
}