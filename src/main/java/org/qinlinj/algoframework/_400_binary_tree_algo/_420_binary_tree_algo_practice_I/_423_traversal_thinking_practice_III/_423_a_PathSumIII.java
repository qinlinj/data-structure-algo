package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

/**
 * Problem 437: Path Sum III
 * <p>
 * Description:
 * Given the root of a binary tree and an integer targetSum, return the number of paths
 * where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards
 * (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Combines binary tree traversal with the prefix sum technique
 * - Uses a HashMap to track prefix sums and their frequencies
 * - Properly maintains the prefix sum state using pre/post-order operations
 * - The logic: if pathSum - targetSum exists in our HashMap, it means there's
 * a subpath that sums to targetSum
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _423_a_PathSumIII {
    // HashMap to store prefix sums and their frequencies
    // Key: prefix sum, Value: frequency of this prefix sum
    private HashMap<Long, Integer> prefixSumCount = new HashMap<>();
    // Accumulated path sum
    private long pathSum;
    // Target sum to find
    private int targetSum;
    // Result counter
    private int result = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        this.pathSum = 0;
        this.targetSum = targetSum;

        // Initialize with path sum 0 having frequency 1
        // This handles the case where the path starts from the root
        this.prefixSumCount.put(0L, 1);

        // Start traversal
        traverse(root);

        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder position: update path sum with current node value
        pathSum += root.val;

        // Check if there exists a prefix sum such that pathSum - prefix = targetSum
        // If so, it means there's a path ending at current node with sum = targetSum
        result += prefixSumCount.getOrDefault(pathSum - targetSum, 0);

        // Update prefix sum count
        prefixSumCount.put(pathSum, prefixSumCount.getOrDefault(pathSum, 0) + 1);

        // Continue traversal
        traverse(root.left);
        traverse(root.right);

        // Postorder position: restore prefix sum count before moving to a different branch
        prefixSumCount.put(pathSum, prefixSumCount.get(pathSum) - 1);
        pathSum -= root.val;
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
