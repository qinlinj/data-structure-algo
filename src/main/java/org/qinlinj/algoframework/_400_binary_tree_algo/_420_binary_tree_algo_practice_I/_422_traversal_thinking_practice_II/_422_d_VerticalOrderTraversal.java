package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

import java.util.*;

/**
 * Problem 987: Vertical Order Traversal of a Binary Tree
 * <p>
 * Description:
 * Given the root of a binary tree, calculate the vertical order traversal of the tree.
 * For each node at position (row, col), its left and right children will be at positions
 * (row+1, col-1) and (row+1, col+1) respectively. The root starts at position (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column,
 * where each column is sorted by row. If multiple nodes have the same position, sort them by value.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Assigns coordinates (row, col) to each node during traversal
 * - Sorts nodes based on column, then row, then value
 * - Groups nodes by column for the final result
 * <p>
 * Time Complexity: O(N log N), where N is the number of nodes in the tree
 * (due to sorting all nodes)
 * Space Complexity: O(N) for storing coordinates of all nodes
 */
public class _422_d_VerticalOrderTraversal {
    // List to store all nodes with their positions
    private ArrayList<NodePosition> nodes = new ArrayList<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Traverse the tree to record node positions
        traverse(root, 0, 0);

        // Sort nodes by column, then row, then value
        Collections.sort(nodes, (a, b) -> {
            if (a.col == b.col) {
                if (a.row == b.row) {
                    // If column and row are the same, sort by node value
                    return a.node.val - b.node.val;
                }
                // If only column is the same, sort by row
                return a.row - b.row;
            }
            // Otherwise, sort by column
            return a.col - b.col;
        });

        // Organize nodes into the required result format
        LinkedList<List<Integer>> result = new LinkedList<>();
        int prevCol = Integer.MIN_VALUE;

        for (NodePosition np : nodes) {
            if (np.col != prevCol) {
                // Start a new column
                result.addLast(new LinkedList<>());
                prevCol = np.col;
            }
            // Add node value to the current column
            result.getLast().add(np.node.val);
        }

        return result;
    }

    private void traverse(TreeNode root, int row, int col) {
        if (root == null) {
            return;
        }

        // Record node position
        nodes.add(new NodePosition(root, row, col));

        // Left child is at (row+1, col-1)
        traverse(root.left, row + 1, col - 1);
        // Right child is at (row+1, col+1)
        traverse(root.right, row + 1, col + 1);
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

    // Class to store node with its coordinates
    private class NodePosition {
        TreeNode node;
        int row, col;

        NodePosition(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
}
