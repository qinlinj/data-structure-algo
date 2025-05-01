package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Postorder Traversal Optimization
 * <p>
 * This class focuses on optimization techniques for tree algorithms using postorder traversal,
 * comparing inefficient and efficient approaches to the same problems.
 * <p>
 * Key optimization principles:
 * 1. Avoiding "recursion inside recursion":
 * - Inefficient: Calling multiple recursive functions within a recursive function
 * - Efficient: Combining multiple calculations into a single traversal
 * <p>
 * 2. Information consolidation:
 * - Using compound return types (arrays, objects) to propagate multiple values
 * - Returning all necessary information in a single pass
 * <p>
 * 3. Computation reuse:
 * - Storing computed values to avoid redundant calculations
 * - Using postorder position to build results from already computed subtree results
 * <p>
 * The optimizations shown here can reduce time complexity from O(n²) to O(n) for many
 * tree-based algorithms, demonstrating the power of effective postorder traversal design.
 */

public class _419_e_PostorderOptimization {

    public static void main(String[] args) {
        _419_e_PostorderOptimization optimizer = new _419_e_PostorderOptimization();

        // Create sample trees
        TreeNode smallTree = optimizer.createLargeTestTree(3); // Height 3, ~15 nodes
        TreeNode largeTree = optimizer.createLargeTestTree(10); // Height 10, ~2000 nodes

        System.out.println("Testing optimization techniques for postorder traversal:\n");

        // Compare approaches on small tree first
        System.out.println("Small tree (height 3) performance comparison:");
        optimizer.comparePerformance(smallTree);

        // Compare property calculation approaches
        System.out.println("\nComparing property calculation approaches on small tree:");

        System.out.println("\nInefficient approach (multiple traversals):");
        optimizer.calculateProperties_inefficient(smallTree);

        System.out.println("\nEfficient approach (single traversal):");
        TreeStats stats = optimizer.calculateProperties_efficient(smallTree);
        System.out.println(stats);

        // Large tree comparison
        System.out.println("\nLarge tree (height 10) performance comparison:");
        optimizer.comparePerformance(largeTree);

        // Summary
        System.out.println("\nKey optimization principles demonstrated:");
        System.out.println("1. Avoid 'recursion inside recursion' pattern");
        System.out.println("2. Use compound return types to consolidate information");
        System.out.println("3. Calculate and propagate multiple properties in a single pass");
        System.out.println("4. Leverage postorder position to build upon subtree results");
        System.out.println("\nThese optimizations transform O(n²) algorithms into O(n) algorithms,");
        System.out.println("with the performance difference becoming dramatic as tree size increases.");
    }

    /**
     * Example 1: Maximum Sum BST
     * Comparing inefficient and efficient approaches
     */

    // Inefficient approach: O(n²)
    public int maxSumBST_inefficient(TreeNode root) {
        int[] maxSum = new int[1];
        traverse(root, maxSum);
        return maxSum[0];
    }

    private void traverse(TreeNode root, int[] maxSum) {
        if (root == null) return;

        if (isBST(root)) {
            int sum = sumOfNodes(root);
            maxSum[0] = Math.max(maxSum[0], sum);
        }

        traverse(root.left, maxSum);
        traverse(root.right, maxSum);
    }

    // Helper methods for inefficient approach
    private boolean isBST(TreeNode root) {
        return isBSTHelper(root, null, null);
    }

    private boolean isBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        return isBSTHelper(node.left, min, node.val) &&
                isBSTHelper(node.right, node.val, max);
    }

    private int sumOfNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    // Efficient approach: O(n)
    public int maxSumBST_efficient(TreeNode root) {
        int[] maxSum = new int[1];
        findMaxMinSum(root, maxSum);
        return maxSum[0];
    }

    // Return: [isBST, min, max, sum]
    private int[] findMaxMinSum(TreeNode root, int[] maxSum) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = findMaxMinSum(root.left, maxSum);
        int[] right = findMaxMinSum(root.right, maxSum);

        int[] result = new int[4];

        // Check if this is a valid BST
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {

            result[0] = 1; // Is BST
            result[1] = Math.min(left[1], root.val); // Min value
            result[2] = Math.max(right[2], root.val); // Max value
            result[3] = left[3] + right[3] + root.val; // Sum

            maxSum[0] = Math.max(maxSum[0], result[3]);
        } else {
            result[0] = 0; // Not a BST
        }

        return result;
    }

    /**
     * Example 2: Bottom-up property calculation
     * Comparing inefficient and efficient approaches
     */

    // Inefficient approach: Multiple traversals
    public void calculateProperties_inefficient(TreeNode root) {
        System.out.println("Height: " + height(root));
        System.out.println("Size: " + size(root));
        System.out.println("Is balanced: " + isBalanced(root));
        System.out.println("Max path sum: " + maxPathSum(root));
    }

    private int height(TreeNode root) {
        if (root == null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private int size(TreeNode root) {
        if (root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }

    private boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        // Option 1: Maximum path through left subtree
        int leftMax = maxPathSum(root.left);

        // Option 2: Maximum path through right subtree
        int rightMax = maxPathSum(root.right);

        // Option 3: Path that connects left subtree -> root -> right subtree
        int leftBranch = Math.max(0, maxBranchSum(root.left));
        int rightBranch = Math.max(0, maxBranchSum(root.right));
        int connectPath = leftBranch + rightBranch + root.val;

        return Math.max(Math.max(leftMax, rightMax), connectPath);
    }

    private int maxBranchSum(TreeNode root) {
        if (root == null) return 0;

        int leftSum = Math.max(0, maxBranchSum(root.left));
        int rightSum = Math.max(0, maxBranchSum(root.right));

        return root.val + Math.max(leftSum, rightSum);
    }

    public TreeStats calculateProperties_efficient(TreeNode root) {
        return calculateStats(root);
    }

    private TreeStats calculateStats(TreeNode root) {
        if (root == null) {
            TreeStats stats = new TreeStats();
            stats.height = -1;
            stats.size = 0;
            stats.isBalanced = true;
            stats.maxPathSum = 0;
            stats.maxBranchSum = 0;
            return stats;
        }

        // Process left and right subtrees
        TreeStats left = calculateStats(root.left);
        TreeStats right = calculateStats(root.right);

        // Postorder position: Calculate current node's stats
        TreeStats stats = new TreeStats();

        // Height calculation
        stats.height = Math.max(left.height, right.height) + 1;

        // Size calculation
        stats.size = left.size + right.size + 1;

        // Balance check
        stats.isBalanced = left.isBalanced && right.isBalanced &&
                Math.abs(left.height - right.height) <= 1;

        // Max branch sum (path from this node to leaf)
        int leftBranch = Math.max(0, left.maxBranchSum);
        int rightBranch = Math.max(0, right.maxBranchSum);
        stats.maxBranchSum = root.val + Math.max(leftBranch, rightBranch);

        // Max path sum
        int connectPath = root.val + leftBranch + rightBranch;
        stats.maxPathSum = Math.max(Math.max(left.maxPathSum, right.maxPathSum), connectPath);

        return stats;
    }

    /**
     * Example 3: Performance measurement
     * Comparing execution times for both approaches
     */
    public void comparePerformance(TreeNode root) {
        // Measure inefficient approach
        long startTime = System.nanoTime();
        int result1 = maxSumBST_inefficient(root);
        long endTime = System.nanoTime();
        long inefficientTime = endTime - startTime;

        // Measure efficient approach
        startTime = System.nanoTime();
        int result2 = maxSumBST_efficient(root);
        endTime = System.nanoTime();
        long efficientTime = endTime - startTime;

        System.out.println("Results comparison:");
        System.out.println("  Inefficient approach result: " + result1);
        System.out.println("  Efficient approach result: " + result2);

        System.out.println("\nPerformance comparison:");
        System.out.println("  Inefficient approach time: " + inefficientTime / 1000000 + " ms");
        System.out.println("  Efficient approach time: " + efficientTime / 1000000 + " ms");
        System.out.println("  Speedup factor: " + (double) inefficientTime / efficientTime);
    }

    /**
     * Utility method to create a large test tree
     */
    private TreeNode createLargeTestTree(int height) {
        return createBalancedTree(height);
    }

    private TreeNode createBalancedTree(int height) {
        if (height <= 0) return null;

        TreeNode root = new TreeNode((int) (Math.random() * 100));
        root.left = createBalancedTree(height - 1);
        root.right = createBalancedTree(height - 1);

        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Efficient approach: Single traversal with compound return type
    public static class TreeStats {
        int height;
        int size;
        boolean isBalanced;
        int maxPathSum;
        int maxBranchSum;

        @Override
        public String toString() {
            return "Height: " + height +
                    ", Size: " + size +
                    ", Is balanced: " + isBalanced +
                    ", Max path sum: " + maxPathSum;
        }
    }
}
