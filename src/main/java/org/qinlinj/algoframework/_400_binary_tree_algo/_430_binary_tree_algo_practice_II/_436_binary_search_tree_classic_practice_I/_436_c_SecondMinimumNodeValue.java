package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I;

/**
 * Problem 671: Second Minimum Node In a Binary Tree (Easy)
 * <p>
 * Problem Description:
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node
 * in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value
 * is the smaller of its two sub-nodes' values. Given such a binary tree, you need to output the second
 * minimum value in the set of all the nodes' values in the whole tree.
 * If no such second minimum value exists, output -1 instead.
 * <p>
 * Key Concepts:
 * - Special Tree Property: Each node's value equals the minimum of its children
 * - Root Value Significance: The root has the minimum value in the entire tree
 * - Second Minimum Search: The second minimum must be a child of some node with the minimum value
 * - Recursive Search: Use divide-and-conquer to find the second minimum in each subtree
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _436_c_SecondMinimumNodeValue {
    // Main method for testing
    public static void main(String[] args) {
        _436_c_SecondMinimumNodeValue solution = new _436_c_SecondMinimumNodeValue();

        // Example 1: [2,2,5,null,null,5,7]
        TreeNode root1 = solution.new TreeNode(2);
        root1.left = solution.new TreeNode(2);
        root1.right = solution.new TreeNode(5);
        root1.right.left = solution.new TreeNode(5);
        root1.right.right = solution.new TreeNode(7);

        int result1 = solution.findSecondMinimumValue(root1);
        System.out.println("Example 1 - Second minimum value: " + result1);
        // Expected output: 5

        // Also test with alternative implementation
        int result1Alt = solution.findSecondMinimumValueAlternative(root1);
        System.out.println("Alternative implementation result: " + result1Alt);

        // Example 2: [2,2,2]
        TreeNode root2 = solution.new TreeNode(2);
        root2.left = solution.new TreeNode(2);
        root2.right = solution.new TreeNode(2);

        int result2 = solution.findSecondMinimumValue(root2);
        System.out.println("\nExample 2 - Second minimum value: " + result2);
        // Expected output: -1

        // Also test with alternative implementation
        int result2Alt = solution.findSecondMinimumValueAlternative(root2);
        System.out.println("Alternative implementation result: " + result2Alt);
    }

    /**
     * Main function to find the second minimum value in the binary tree
     *
     * @param root The root of the binary tree
     * @return The second minimum value, or -1 if it doesn't exist
     */
    public int findSecondMinimumValue(TreeNode root) {
        // Special case: empty tree or a leaf node
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }

        // Initialize left and right values
        int left = root.left.val;
        int right = root.right.val;

        // If left child has the same value as root, we need to look deeper in the left subtree
        if (root.val == root.left.val) {
            left = findSecondMinimumValue(root.left);
        }

        // If right child has the same value as root, we need to look deeper in the right subtree
        if (root.val == root.right.val) {
            right = findSecondMinimumValue(root.right);
        }

        // If both subtrees found valid second minimums
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }

        // If only one subtree found a valid second minimum
        if (left != -1) {
            return left;
        }
        if (right != -1) {
            return right;
        }

        // If no valid second minimum was found
        return -1;
    }

    /**
     * Alternative implementation using a more direct approach
     */
    public int findSecondMinimumValueAlternative(TreeNode root) {
        // The minimum value is at the root
        int rootValue = root.val;

        // Find the second minimum value
        long secondMin = Long.MAX_VALUE;
        boolean found = false;

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // If current node's value is greater than root's value, it's a candidate for second minimum
            if (node.val > rootValue) {
                secondMin = Math.min(secondMin, node.val);
                found = true;
            }

            // Add children to the queue
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return found ? (int) secondMin : -1;
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