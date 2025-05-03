package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._433_postorder_position_practice_III; /**
 * Problem 2049: Count Nodes With the Highest Score (Medium)
 * <p>
 * Problem Description:
 * Given a binary tree with n nodes (labeled from 0 to n-1), and represented by a parents array
 * where parents[i] is the parent of node i (with parents[0] = -1 for the root). The score of a node
 * is defined as the product of the sizes of all the non-empty subtrees after removing that node and
 * its connected edges. Return the number of nodes with the highest score.
 * <p>
 * Key Concepts:
 * - Problem-solving pattern: This problem demonstrates the "divide and conquer" thinking pattern for
 * binary trees as opposed to the "traversal" pattern.
 * - Post-order traversal application: We need to calculate subtree sizes, which is perfectly suited
 * for the post-order position.
 * - Score calculation formula:
 * node_score = (left_subtree_size) * (right_subtree_size) * (remaining_nodes_size)
 * where remaining_nodes_size = n - left_subtree_size - right_subtree_size - 1
 * - Tree representation conversion: The problem provides a parents array instead of a traditional
 * binary tree structure, so we need to convert it to an adjacency list representation first.
 * - Integer overflow handling: Be careful with score calculation as it can overflow Integer range.
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for storing the tree and score counts
 */

import java.util.*;

class _433_d_CountHighestScoreNodes {
    // Adjacency list representation of the binary tree
    private int[][] tree;

    // Map to count occurrences of each score
    private HashMap<Long, Integer> scoreToCount = new HashMap<>();

    // Main method for testing
    public static void main(String[] args) {
        _433_d_CountHighestScoreNodes solution = new _433_d_CountHighestScoreNodes();

        // Example 1: parents = [-1,2,0,2,0]
        int[] parents1 = {-1, 2, 0, 2, 0};
        System.out.println("Example 1 Output: " + solution.countHighestScoreNodes(parents1));
        // Expected: 3

        // Example 2: parents = [-1,2,0]
        int[] parents2 = {-1, 2, 0};
        System.out.println("Example 2 Output: " + solution.countHighestScoreNodes(parents2));
        // Expected: 2
    }

    public int countHighestScoreNodes(int[] parents) {
        // Convert parents array to binary tree adjacency list
        this.tree = buildTree(parents);

        // Calculate node counts and scores
        countNode(0);

        // Find the maximum score
        long maxScore = 0;
        for (long score : scoreToCount.keySet()) {
            maxScore = Math.max(maxScore, score);
        }

        // Return the count of nodes with maximum score
        return scoreToCount.get(maxScore);
    }

    /**
     * Count the number of nodes in the subtree rooted at the given node
     * and calculate scores for each node in the subtree
     *
     * @param root The root of the current subtree
     * @return The total number of nodes in this subtree
     */
    private int countNode(int root) {
        if (root == -1) {
            return 0;
        }

        // Total number of nodes in the tree
        int n = tree.length;

        // Count nodes in left and right subtrees
        int leftCount = countNode(tree[root][0]);
        int rightCount = countNode(tree[root][1]);

        // Post-order position: Calculate the score for current node
        // The score is the product of the sizes of all non-empty subtrees after removing this node
        int otherCount = n - leftCount - rightCount - 1;

        // IMPORTANT: Convert to long to avoid integer overflow
        // Also, use Math.max to handle cases where a subtree might be empty (size=0)
        long score = (long) Math.max(leftCount, 1)
                * Math.max(rightCount, 1)
                * Math.max(otherCount, 1);

        // Update the score counter
        scoreToCount.put(score, scoreToCount.getOrDefault(score, 0) + 1);

        // Return the total number of nodes in this subtree
        return leftCount + rightCount + 1;
    }

    /**
     * Convert the parents array to a binary tree adjacency list representation
     *
     * @param parents The parents array where parents[i] is the parent of node i
     * @return A 2D array where tree[i][0] is the left child and tree[i][1] is the right child of node i
     */
    private int[][] buildTree(int[] parents) {
        int n = parents.length;

        // Initialize the tree structure
        // tree[i][0] is the left child and tree[i][1] is the right child of node i
        // -1 indicates no child
        int[][] tree = new int[n][2];

        // Initialize all children as -1 (no child)
        for (int i = 0; i < n; i++) {
            tree[i][0] = tree[i][1] = -1;
        }

        // Build the tree from the parents array
        // Skip the root (index 0) since it has no parent
        for (int i = 1; i < n; i++) {
            int parent = parents[i];

            // Assign the current node as either the left or right child of its parent
            if (tree[parent][0] == -1) {
                tree[parent][0] = i;  // Left child is empty, assign here
            } else {
                tree[parent][1] = i;  // Left child is taken, assign as right child
            }
        }

        return tree;
    }
}