package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._435_level_order_traversal_practice_II; /**
 * Problem 872: Leaf-Similar Trees (Easy)
 * <p>
 * Problem Description:
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * Return true if and only if the two given trees are leaf-similar.
 * <p>
 * Key Concepts:
 * - Leaf Node Collection: Identify and collect leaf nodes in left-to-right order
 * - Iterator Design Pattern: Create an iterator to generate leaf sequences on-demand
 * - Stack-based Tree Traversal: Use stack to simulate recursive pre-order traversal
 * - Sequence Comparison: Compare two sequences element by element
 * <p>
 * Time Complexity: O(n + m) where n and m are the number of nodes in the two trees
 * Space Complexity: O(h1 + h2) where h1 and h2 are the heights of the two trees (stack space)
 */

import java.util.*;

class _435_b_LeafSimilarTrees {
    // Main method for testing
    public static void main(String[] args) {
        _435_b_LeafSimilarTrees solution = new _435_b_LeafSimilarTrees();

        // Example 1: Leaf-similar trees
        // Tree 1: [3,5,1,6,2,9,8,null,null,7,4]
        TreeNode root1 = solution.new TreeNode(3);
        root1.left = solution.new TreeNode(5);
        root1.right = solution.new TreeNode(1);
        root1.left.left = solution.new TreeNode(6);
        root1.left.right = solution.new TreeNode(2);
        root1.right.left = solution.new TreeNode(9);
        root1.right.right = solution.new TreeNode(8);
        root1.left.right.left = solution.new TreeNode(7);
        root1.left.right.right = solution.new TreeNode(4);

        // Tree 2: [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
        TreeNode root2 = solution.new TreeNode(3);
        root2.left = solution.new TreeNode(5);
        root2.right = solution.new TreeNode(1);
        root2.left.left = solution.new TreeNode(6);
        root2.left.right = solution.new TreeNode(7);
        root2.right.left = solution.new TreeNode(4);
        root2.right.right = solution.new TreeNode(2);
        root2.right.right.left = solution.new TreeNode(9);
        root2.right.right.right = solution.new TreeNode(8);

        boolean result1 = solution.leafSimilar(root1, root2);
        System.out.println("Example 1 - Are the trees leaf-similar?: " + result1);
        // Expected output: true (leaf sequence: [6,7,4,9,8])

        // Example 2: Non-leaf-similar trees
        // Tree 3: [1,2,3]
        TreeNode root3 = solution.new TreeNode(1);
        root3.left = solution.new TreeNode(2);
        root3.right = solution.new TreeNode(3);

        // Tree 4: [1,3,2]
        TreeNode root4 = solution.new TreeNode(1);
        root4.left = solution.new TreeNode(3);
        root4.right = solution.new TreeNode(2);

        boolean result2 = solution.leafSimilar(root3, root4);
        System.out.println("Example 2 - Are the trees leaf-similar?: " + result2);
        // Expected output: false (leaf sequences: [2,3] vs [3,2])

        // Test the recursive solution as well
        boolean result1Recursive = solution.leafSimilarRecursive(root1, root2);
        boolean result2Recursive = solution.leafSimilarRecursive(root3, root4);
        System.out.println("Recursive - Example 1: " + result1Recursive);
        System.out.println("Recursive - Example 2: " + result2Recursive);
    }

    /**
     * Main function to check if two trees are leaf-similar
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // Create iterators for both trees
        LeafIterator iterator1 = new LeafIterator(root1);
        LeafIterator iterator2 = new LeafIterator(root2);

        // Compare leaf sequences
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next().val != iterator2.next().val) {
                return false;  // Different leaf values found
            }
        }

        // Both iterators should be exhausted for trees to be leaf-similar
        return !iterator1.hasNext() && !iterator2.hasNext();
    }

    /**
     * Alternative recursive solution using DFS to collect all leaves first
     */
    public boolean leafSimilarRecursive(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        collectLeaves(root1, leaves1);
        collectLeaves(root2, leaves2);

        // Compare leaf sequences
        if (leaves1.size() != leaves2.size()) {
            return false;
        }

        for (int i = 0; i < leaves1.size(); i++) {
            if (!leaves1.get(i).equals(leaves2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper method to collect all leaf values in a tree
     */
    private void collectLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }

        // If current node is a leaf, add its value
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }

        // Recursive calls
        collectLeaves(root.left, leaves);
        collectLeaves(root.right, leaves);
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

    /**
     * Iterator class that generates leaf nodes in left-to-right order
     */
    class LeafIterator {
        // Stack to simulate recursive traversal
        private Stack<TreeNode> stack = new Stack<>();

        /**
         * Initialize the iterator with the root of a tree
         */
        public LeafIterator(TreeNode root) {
            if (root != null) {
                stack.push(root);
            }
        }

        /**
         * Check if there are more leaf nodes to be visited
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Get the next leaf node in the sequence
         */
        public TreeNode next() {
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();

                // If current node is a leaf, return it
                if (node.left == null && node.right == null) {
                    return node;
                }

                // Otherwise, push children to stack in reverse order
                // (right first, then left, to ensure left-to-right traversal when popping)
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return null;  // No more leaves
        }
    }
}